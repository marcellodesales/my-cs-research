<?

/* This controls the databaseformat PHP Weather uses. Set them both to

   0 to turn database-support off. Then PHP Weather will then retrieve

   a new METAR everytime the script is called. */



/* If useDBM is set to 1, the program will create the appropriate

   files, but the owner will be set to the user running the webserver,

   usually nobody. This will fail if that user does not have write

   permission to the current directory. */

/* set to 1 to use MySQL database */

$useMySQL = 1;

/* set to 1 to use DBM database */

$useDBM = 0;

/* set to 1 to use PostgreSQL database */

$usePSQL = 0;

$dbmMetar = 0;

$dbmTimestamp = 0;

if ($useMySQL) {

 //   Here's a good place to connect to the MySQL database. You do
   // this by executing the following code::
    //These variables are used to connect to the database.

    $db_hostname = 'localhost';
    $db_name = 'weather';
    $db_username = .php';
    $db_password = '51527981';

    mysql_pconnect($db_hostname, $db_username, $db_password) OR DIE ("<p>Unable to connect to database</p>\n</body>\n</html>");

    @mysql_select_db($db_name) or die ("<p>Unable to select database</p>\n</body>\n</html>");

   // That should work. This is what I use a my site, you should remove
    //the line, and instead use the code above
  //include("db_connect.inc");

} elseif ($useDBM) {

  $dbmMetar = dbmopen ("metar", "c");

  $dbmTimestamp = dbmopen ("metarTimestamp", "c");

  if (!$dbmMetar || !dbmTimestamp) {

    echo ("<br>Unable to open DBM files<br>");

    $useDBM = 0; /* turn off so rest of program won't use */

  }

} elseif($usePSQL) {

  $db_name = 'database_name';

  $conn = pg_Connect("", "", "", "", $db_name);

  if (!$conn) { echo "Error \n" ;

  exit;

  }
}

/*

 * Various convenience functions

 */

function store_temp($temp,&$destination,$temp_cname,$temp_fname) {

global $maceioWeather;

  /* Temperature measured in Celsius, coded to tenth of degree */

  $temp = number_format($temp/10,1);

  if ($temp >100.0) { /* first digit = 1 means minus temperature */

     $temp = -($temp - 100.0);

  }

  $destination[$temp_cname] = $temp;

  /* The temperature in Fahrenheit. */

  $destination[$temp_fname] = round($temp * (9/5) + 32,1);

}

function pretty_print_precip($precip_mm, $precip_in) {

  /*

   * Returns amount if $precip_mm > 0, otherwise "trace" (see Federal

   * Meteorological Handbook No. 1 for code groups P, 6 and 7) used in

   * several places, so standardized in one function.

   */

  if ($precip_mm>0) {

    $amount = "<b>$precip_mm</b> mm (<b>$precip_in</b> inches)";

  } else {

    $amount = "a trace";

  }

  return "There was $amount of precipitation ";

}

function store_speed($value, $windunit, &$meterspersec, &$knots, &$milesperhour) {

global $maceioWeather;

  /*

   * Helper function to convert and store speed based on unit.

   * &$meterspersec, &$knots and &$milesperhour are passed on

   * reference

   */

  if ($windunit == 'KT') {

    /* The windspeed measured in knots: */

    $knots = number_format($value);

    /* The windspeed measured in meters per second, rounded to one decimal place: */

    $meterspersec = number_format($value * 0.51444, 1);

    /* The windspeed measured in miles per hour, rounded to one decimal place: */

    $milesperhour = number_format($value * 1.1507695060844667, 1);

  } elseif ($windunit == 'MPS') {

    /* The windspeed measured in meters per second: */

    $meterspersec = number_format($value);

    /* The windspeed measured in knots, rounded to one decimal place: */

    $knots = number_format($value / 0.51444, 1);

    /* The windspeed measured in miles per hour, rounded to one decimal place: */

    $milesperhour = number_format($value / 0.51444 * 1.1507695060844667, 1);

  } elseif ($windunit == 'KMH') {

    /* The windspeed measured in kilometers per hour: */

    $meterspersec = number_format($value * 1000 / 3600, 1);

    $knots = number_format($value * 1000 / 3600 / 0.51444, 1);

    /* The windspeed measured in miles per hour, rounded to one decimal place: */

    $milesperhour = number_format($knots * 1.1507695060844667, 1);

  }
  
}

function get_metar($station) {

  /*
   * Looks in the database, and fetches a new metar is nesceary. You
   * should pass a ICAO station identifier, eg. 'EKYT' for Aalborg,
   * Denmark.  */

  global $useMySQL, $useDBM, $usePSQL,$conn, $dbmMetar, $dbmTimestamp;

  /* initialize $metar and $timestamp to empty values */

  $metar = "";

  $timestamp = 0;

  if ($useMySQL) {

    $query = "SELECT metar, UNIX_TIMESTAMP(timestamp) FROM metars WHERE station = '$station'";

    $result = mysql_query($query);

    if (mysql_num_rows($result)) { /* found station */

      list($metar, $timestamp) = mysql_fetch_row($result);

    }

  } elseif ($usePSQL) {

    $query = "SELECT metar,timestamp FROM metars WHERE station='$station'";

    $result = pg_exec($conn,$query);

    $num=pg_numrows($result);

    if (pg_numrows($result)) {

      list($metar,$timestamp) = pg_fetch_row($result,0);

    }

  } elseif ($useDBM) {

    if (dbmexists($dbmMetar, $station) && dbmexists ($dbmTimestamp, $station)) { /* found station */

      $metar = dbmfetch ($dbmMetar, $station);

      $timestamp = dbmfetch ($dbmTimestamp, $station);

    }

  } /* elseif ($useDBM) */

  if ($metar) { /* found station */

    if ($timestamp > time() - 3600 - date('Z')) {

      /* We found the station, and the data is less than 1 hour old. */

      return $metar;

    } else {

      /* The data is old, we fetch a new METAR */

      return fetch_metar($station, 0);

    }

  } else {

    /* The station is new - we fetch a new METAR */

      return fetch_metar($station, 1);

  }
}

function fetch_metar($station, $new) {

  /*

   * Fetches a new metar from weather.noaa.gov. If the $new variable

   * is true, the metar is inserted, else it will replace the old

   * metar.

   */

  global $useMySQL, $useDBM, $usePSQL, $conn, $dbmMetar, $dbmTimestamp, $maceioWeather;

  $metar = "";

  /* Retrieves the METAR from weather.noaa.gov and insert the data

   * into the database. If $new is true, insert the metar, else just

   * update. Returns the METAR.

   */

  $station = strtoupper($station);

  $file   = file("ftp://weather.noaa.gov/data/observations/metar/stations/$station.TXT");

  list($i, $date) = each($file);
  $date = trim($date);

  while (list($i, $line) = each($file)) {

    $metar .= ' ' . trim($line);

  }

  $metar = str_replace('  ', ' ', $metar);

  if (!ereg('[0-9]{6}Z', $metar)) {

    /* Some reports dont even have a time-part, so we insert the

     * current time. This might not be the time of the report, but it

     * was broken anyway :-)

     */

    $metar = gmdate('dHi') . 'Z ' . $metar;

  }


  if ($useMySQL) {

    if ($new) {

      $query = "INSERT INTO metars SET station = '$station', metar = '$metar', timestamp = '$date'";

    } else {

      $query = "UPDATE metars SET metar = '$metar', timestamp = '$date' WHERE station = '$station'";

    }

    mysql_query($query);

  } elseif ($usePSQL) {

    if ($new) {

      $query = "INSERT INTO metars (station,metar,timestamp) VALUES ('$station','$metar','$date')";

    } else {

      $query = "UPDATE metars SET metar='$metar', timestamp='$date' WHERE station='$station'";

    }

    pg_exec($conn,$query);

  } elseif ($useDBM) {

    if ($new) {

      dbminsert ($dbmMetar, $station, $metar);

      dbminsert ($dbmTimestamp, $station, $date);

    } else {

      dbmreplace ($dbmMetar, $station, $metar);

      dbmreplace ($dbmTimestamp, $station, $date);

    }

  }

  return $metar;

}



function process_metar($metar) {

/* initialization */

global $maceioWeather;

$decoded_metar['temp_visibility_miles'] = "";

$cloud_layers = 0;

$decoded_metar['remarks'] = "";



  /* Here we define the strings we need later.

     We put them in arrays, so that we can retrieve them efficiently. */

  $wind_dir_eng_short = array('N',

                        'N/NE',

                        'NE',

                        'E/NE',

                        'E',

                        'E/SE',

                        'SE',

                        'S/SE',

                        'S',

                        'S/SW',

                        'SW',

                        'W/SW',

                        'W',

                        'W/NW',

                        'NW',

                        'N/NW',

                        'N');

  $wind_dir_eng = array('Norte',
                        'Norte/Nord.',
                        'Nordeste',
                        'Leste/Nord.',
                        'Leste',
                        'Leste/Suld.',
                        'Suldeste',
                        'Sul/Suld.',
                        'Sul',
                        'Sul/Suldo.',
                        'Suldoeste',
                        'Oeste/Suldo.',
                        'Oeste',
                        'Oeste/Noro.',
                        'Noroeste',
                        'Norte/Noro.',
                        'Norte');

  $weather = array('PR' => 'Parcial ',
                   'BC' => 'Remendos ',
                   'DR' => 'Baixo Acúmulo ',
                   'BL' => 'Ventania ',
                   'SH' => 'Shower(s) ',
                   'TS' => 'Tempestade ',
                   'FZ' => 'Gelado',
                   'DZ' => 'Garôa ',
                   'RA' => 'Chuva ',
                   'SN' => 'Gelo ',
                   'SG' => 'Grãos de Neve ',
                   'IC' => 'Cristais de Gelo ',
                   'PE' => 'Pedritas de Neve ',
                   'GR' => 'Granizo ',
                   'GS' => 'Pequenos Granizo e/ou Pedridas de Neve ',
                   'UP' => 'Desconhecido ',
                   'BR' => 'Névoa ',
                   'FG' => 'Névoa ',
                   'FU' => 'Fumaça ',
                   'VA' => 'Cinza Vulcânica ',
                   'DU' => 'Pó Difundido ',
                   'SA' => 'Areia ',
                   'HZ' => 'Neblina ',
                   'PY' => 'Spray',
                   'PO' => 'Torando bem desenvolvido ',
                   'SQ' => 'Rajadas ',
                   'FC' => 'Tornado em funil ',
                   'SS' => 'Tornado ');

  $cloud_condition = array('SKC' => 'clear',
                           'CLR' => 'clear',
                           'VV'  => 'vertical visibility',
                           'FEW' => 'a few',
                           'SCT' => 'scattered',
                           'BKN' => 'broken',
                           'OVC' => 'overcast');

  $cloud_coverage = array('SKC' => '0',
                          'CLR' => '0',
                          'VV'  => '8/8',
                          'FEW' => '1/8 - 2/8',
                          'SCT' => '3/8 - 4/8',
                          'BKN' => '5/8 - 7/8',
                          'OVC' => '8/8');

  $decoded_metar['metar'] = $metar;

  $parts = explode(' ', $metar);

  $num_parts = count($parts);

  for ($i = 0; $i < $num_parts; $i++) {

    $part = $parts[$i];

    if ($part == 'METAR') {

      /*

       * Type of Report: METAR

       */

      $decoded_metar['type'] = 'METAR';

    } elseif ($part == 'SPECI') {

      /*

       * Type of Report: SPECI

       */

      $decoded_metar['type'] = 'SPECI';

    } elseif (ereg('^[A-Z]{4}$', $part) && ! isset($decoded_metar['station']))  {

      /*

       * Station Identifier

       */

      $decoded_metar['station'] = $part;

    } elseif (ereg('([0-9]{2})([0-9]{2})([0-9]{2})Z', $part, $regs)) {

      /*

       * Date and Time of Report

       * We return a standard Unix UTC/GMT timestamp suitable for

       * gmdate()

       * There has been a report about the time beeing wrong. If you

       * experience this, then change the next line. You should

       * add/subtract some hours to $regs[2], e.g. if all your times

       * are 960 minutes off (16 hours) then add 16 to $regs[2].

       */

      $decoded_metar['time'] = gmmktime($regs[2], $regs[3], 0, date('m'), $regs[1], date('Y'));	
	
      $gmtime = gmdate('H:i',$decoded_metar['time'] - 10800 * 1);
	
      $maceioWeather->setGmTime($gmtime);	
       
    } elseif ($part == 'AUTO') {

      /*

       * Report Modifier: AUTO

       */

      $decoded_metar['report_mod'] = 'AUTO';

    } elseif ($part == 'COR') {

      /*

       * Report Modifier: COR

       */

      $decoded_metar['report_mod'] = 'COR';

    } elseif (ereg('([0-9]{3}|VRB)([0-9]{2,3}).*(KT|MPS|KMH)', $part, $regs)) {

      /* Wind Group */

	      $windunit = $regs[3];  /* do ereg in two parts to retrieve unit first */

      /* now do ereg to get the actual values */

      ereg("([0-9]{3}|VRB)([0-9]{2,3})(G([0-9]{2,3})?$windunit)", $part, $regs);

      if ($regs[1] == 'VRB') {

        $decoded_metar['wind_deg'] = 'variable directions';

        $decoded_metar['wind_dir_eng'] = 'variable directions';

        $decoded_metar['wind_dir_eng_short'] = 'VAR';

      } else {

        $decoded_metar['wind_deg'] = $regs[1];

        $decoded_metar['wind_dir_eng'] = $wind_dir_eng[round($regs[1]/22.5)];

        $decoded_metar['wind_dir_eng_short'] = $wind_dir_eng_short[round($regs[1]/22.5)];

      }

      $maceioWeather->setWindAngle($regs[1]);	
      $maceioWeather->setWindDirection($decoded_metar['wind_dir_eng']);

      store_speed($regs[2],

                  $windunit,

                  $decoded_metar['wind_meters_per_second'],

                  $decoded_metar['wind_knots'],

                  $decoded_metar['wind_miles_per_hour']

                  );

      $maceioWeather->setWindMilesPerHour($decoded_metar['wind_miles_per_hour']);
      $maceioWeather->setWindMetersPerSecond($decoded_metar['wind_meters_per_second']);
      $maceioWeather->setWindKnots($decoded_metar['wind_knots']);

      if ($regs[4] != '') {

	/* We have a report with information about the gust.

           First we have the gust measured in knots: */

        store_speed($regs[4],$windunit,

          $decoded_metar['wind_gust_meters_per_second'],

          $decoded_metar['wind_gust_knots'],

          $decoded_metar['wind_gust_miles_per_hour']);

      }

//      $maceioWeather->setWindGustMilesPerHour($decoded_metar['wind_gust_miles_per_hour']);
//      $maceioWeather->setWindGustMetersPerSecond($decoded_metar['wind_gust_meters_per_second']);

    } elseif (ereg('([0-9]{3})V([0-9]{3})', $part, $regs)) {

      /*

       * Variable wind-direction

       */

      $decoded_metar['wind_var_beg'] = $regs[1];

      $decoded_metar['wind_var_end'] = $regs[2];

    } elseif ($part == 9999) {

      /* A strange value. When you look at other pages you see it

         interpreted like this (where I use > to signify 'Greater

         than'): */

      $decoded_metar['visibility_miles'] = '>7';

      $decoded_metar['visibility_km']    = '>11.3';

    } elseif(ereg('^([0-9]{4})$', $part, $regs)) {

      /*

       * Visibility in meters (4 digits only)

       */

      /* The visibility measured in kilometers, rounded to one decimal place. */

      $decoded_metar['visibility_km'] = number_format($regs[1]/1000, 1);

      /* The visibility measured in miles, rounded to one decimal place. */

      $decoded_metar['visibility_miles'] = number_format( ($regs[1]/1000) / 1.609344, 1);

    } elseif (ereg('^[0-9]$', $part)) {

      /*

       * Temp Visibility Group, single digit followed by space

       */

      $decoded_metar['temp_visibility_miles'] = $part;

    } elseif (ereg('^M?(([0-9]?)[ ]?([0-9])(/?)([0-9]*))SM$', $decoded_metar['temp_visibility_miles'].' '.$parts[$i], $regs)) {

      /*

       * Visibility Group

       */

      if ($regs[4] == '/') {

        $vis_miles = $regs[2] + $regs[3]/$regs[5];

      } else {

        $vis_miles = $regs[1];

      }

      if ($regs[0][0] == 'M') {

        /* The visibility measured in miles, prefixed with < to indicate 'Less than' */

        $decoded_metar['visibility_miles'] = '<' . number_format($vis_miles, 1);

        /* The visibility measured in kilometers. The value is rounded

           to one decimal place, prefixed with < to indicate 'Less than' */

        $decoded_metar['visibility_km']    = '<' . number_format($vis_miles * 1.609344, 1);

      } else {

        /* The visibility measured in mile.s */

        $decoded_metar['visibility_miles'] = number_format($vis_miles, 1);

        /* The visibility measured in kilometers, rounded to one decimal place. */

        $decoded_metar['visibility_km']    = number_format($vis_miles * 1.609344, 1);

      }

    } elseif (ereg('^(-|\+|VC)?(TS|SH|FZ|BL|DR|MI|BC|PR|RA|DZ|SN|SG|GR|GS|PE|IC|UP|BR|FG|FU|VA|DU|SA|HZ|PY|PO|SQ|FC|SS|DS)+$', $part)) {


    $maceioWeather->setVisibilityKilometer($decoded_metar['visibility_km']);
    $maceioWeather->setVisibilityMiles($decoded_metar['visibility_miles']);

      /*

       * Current weather-group

       */

      if ($part[0] == '-') {

        /* A light phenomenon */

        $decoded_metar['weather'] .= 'Light ';

        $part = substr($part, 1);

      } elseif ($part[0] == '+') {

        /* A heavy phenomenon */

        $decoded_metar['weather'] .= 'Heavy ';

        $part = substr($part, 1);

      } elseif ($part[0].$part[1] == 'VC') {

        /* Proximity Qualifier */

        $decoded_metar['weather'] .= 'Nearby ';

        $part = substr($part, 2);

      } else {

        /* no intensity code => moderate phenomenon */

        $decoded_metar['weather'] .= 'Moderate ';

      }



      while ($bite = substr($part, 0, 2)) {

        /* Now we take the first two letters and determine what they

           mean. We append this to the variable so that we gradually

           build up a phrase. */

        $decoded_metar['weather'] .= $weather[$bite];

        /* Here we chop off the two first letters, so that we can take

           a new bite at top of the while-loop. */

        $part = substr($part, 2);

      }

    } elseif (ereg('(SKC|CLR)', $part, $regs)) {

      /*

       * Cloud-layer-group.

       * There can be up to three of these groups, so we store them as

       * cloud_layer1, cloud_layer2 and cloud_layer3.

       */

      $cloud_layers++;

      /* Again we have to translate the code-characters to a

         meaningful string. */

      $decoded_metar['cloud_layer'. $cloud_layers.'_condition']  = $cloud_condition[$regs[1]];

      $decoded_metar['cloud_layer'.$cloud_layers.'_coverage']    = $cloud_coverage[$regs[1]];

    } elseif (ereg('^(VV|FEW|SCT|BKN|OVC)([0-9]{3})(CB|TCU)?$', $part, $regs)) {

      /* We have found (another) a cloud-layer-group. There can be up

         to three of these groups, so we store them as cloud_layer1,

         cloud_layer2 and cloud_layer3. */

      $cloud_layers++;

      /* Again we have to translate the code-characters to a meaningful string. */

      if ($regs[3] == 'CB') {

        /* cumulonimbus (CB) clouds were observed. */

        $decoded_metar['cloud_layer'.$cloud_layers.'_condition'] =

          $cloud_condition[$regs[1]] . ' cumulonimbus clouds';

      } elseif ($regs[3] == 'TCU') {

        /* towering cumulus (TCU) clouds were observed. */

        $decoded_metar['cloud_layer'.$cloud_layers.'_condition'] =

          $cloud_condition[$regs[1]] . ' towering cumulus clouds';

      } else {

        $decoded_metar['cloud_layer'.$cloud_layers.'_condition'] =

          $cloud_condition[$regs[1]] . ' clouds';

      }

      $decoded_metar['cloud_layer'.$cloud_layers.'_coverage']    = $cloud_coverage[$regs[1]];

      $decoded_metar['cloud_layer'.$cloud_layers.'_altitude_ft'] = $regs[2] *100;

      $decoded_metar['cloud_layer'.$cloud_layers.'_altitude_m']  = round($regs[2] * 30.48);

    } elseif (ereg('^(M?[0-9]{2})/(M?[0-9]{2})?$', $part, $regs)) {

      /*

       * Temperature/Dew Point Group

       * The temperature and dew-point measured in Celsius.

       */

      $decoded_metar['temp_c'] = number_format(strtr($regs[1], 'M', '-'));

      $decoded_metar['dew_c']  = number_format(strtr($regs[2], 'M', '-'));

      /* The temperature and dew-point measured in Fahrenheit, rounded to the nearest degree. */

      $decoded_metar['temp_f'] = round(strtr($regs[1], 'M', '-') * (9/5) + 32);

      $decoded_metar['dew_f']  = round(strtr($regs[2], 'M', '-') * (9/5) + 32);

    } elseif(ereg('A([0-9]{4})', $part, $regs)) {

      /*

       * Altimeter

       * The pressure measured in inHg

       */

      $decoded_metar['altimeter_inhg'] = number_format($regs[1]/100, 2);

      /* The pressure measured in mmHg, hPa and atm */

      $decoded_metar['altimeter_mmhg'] = number_format($regs[1] * 0.254, 1);

      $decoded_metar['altimeter_hpa']  = number_format($regs[1] * 0.33863881578947);

      $decoded_metar['altimeter_atm']  = number_format($regs[1] * 3.3421052631579e-4, 3);

    } elseif(ereg('Q([0-9]{4})', $part, $regs)) {

      /*

       * Altimeter

       * This is strange, the specification doesnt say anything about

       * the Qxxxx-form, but it's in the METARs.

       */

      /* The pressure measured in hPa */

      $decoded_metar['altimeter_hpa']  = number_format($regs[1]);

      /* The pressure measured in mmHg, inHg and atm */

      $decoded_metar['altimeter_mmhg'] = number_format($regs[1] * 0.7500616827, 1);

      $decoded_metar['altimeter_inhg'] = number_format($regs[1] * 0.0295299875, 2);

      $decoded_metar['altimeter_atm']  = number_format($regs[1] * 9.869232667e-4, 3);

    } elseif (ereg('^T([0-9]{4})([0-9]{4})', $part, $regs)) {

      /*

       * Temperature/Dew Point Group, coded to tenth of degree.

       * The temperature and dew-point measured in Celsius.

       */

      store_temp($regs[1],$decoded_metar,'temp_c','temp_f');

      store_temp($regs[2],$decoded_metar,'dew_c','dew_f');

    } elseif (ereg('^T([0-9]{4}$)', $part, $regs)) {

      store_temp($regs[1],$decoded_metar,'temp_c','temp_f');

    } elseif (ereg('^1([0-9]{4}$)', $part, $regs)) {

      /*

       * 6 hour maximum temperature Celsius, coded to tenth of degree

       */

      store_temp($regs[1],$decoded_metar,'temp_max6h_c','temp_max6h_f');

    } elseif (ereg('^2([0-9]{4}$)', $part, $regs)) {

      /*

       * 6 hour minimum temperature Celsius, coded to tenth of degree

       */

      store_temp($regs[1],$decoded_metar,'temp_min6h_c','temp_min6h_f');

    } elseif (ereg('^4([0-9]{4})([0-9]{4})$', $part, $regs)) {

      /*

       * 24 hour maximum and minimum temperature Celsius, coded to

       * tenth of degree

       */

      store_temp($regs[1],$decoded_metar,'temp_max24h_c','temp_max24h_f');

      store_temp($regs[2],$decoded_metar,'temp_min24h_c','temp_min24h_f');

    } elseif(ereg('^P([0-9]{4})', $part, $regs)) {

      /*

       * Precipitation during last hour in hundredths of an inch

       * (store as inches)

       */

      $decoded_metar['precip_in'] = number_format($regs[1]/100, 2);

      $decoded_metar['precip_mm'] = number_format($regs[1]*0.254, 2);

    } elseif(ereg('^6([0-9]{4})', $part, $regs)) {

      /*

       * Precipitation during last 3 or 6 hours in hundredths of an

       * inch  (store as inches)

       */

      $decoded_metar['precip_6h_in'] = number_format($regs[1]/100, 2);

      $decoded_metar['precip_6h_mm'] = number_format($regs[1]*0.254, 2);

    } elseif(ereg('^7([0-9]{4})', $part, $regs)) {

      /*

       * Precipitation during last 24 hours in hundredths of an inch

       * (store as inches)

       */

      $decoded_metar['precip_24h_in'] = number_format($regs[1]/100, 2);

      $decoded_metar['precip_24h_mm'] = number_format($regs[1]*0.254, 2);

    } elseif(ereg('^4/([0-9]{3})', $part, $regs)) {

      /*

       * Snow depth in inches

       */

      $decoded_metar['snow_in'] = number_format($regs[1]);

      $decoded_metar['snow_mm'] = number_format($regs[1] * 25.4);

    } else {

      /*

       * If we couldn't match the group, we assume that it was a

       * remark.

       */

      $decoded_metar['remarks'] .= ' ' . $part;

    }

  }

  /*

   * Relative humidity

   */

  $decoded_metar['rel_humidity'] = number_format(100 *

    (

     610.710701 +

     44.4293573 * $decoded_metar['dew_c'] +

     1.41696846 * pow($decoded_metar['dew_c'], 2) +

     0.0274759545 * pow($decoded_metar['dew_c'], 3) +

     2.61145937E-4 * pow($decoded_metar['dew_c'], 4) +

     2.85993708E-6 * pow($decoded_metar['dew_c'], 5)

     )

    /

    (

     610.710701 +

     44.4293573 * $decoded_metar['temp_c'] +

     1.41696846 * pow($decoded_metar['temp_c'], 2) +

     0.0274759545 * pow($decoded_metar['temp_c'], 3) +

     2.61145937E-4 * pow($decoded_metar['temp_c'], 4) +

     2.85993708E-6 * pow($decoded_metar['temp_c'], 5)

     ), 1);

     $maceioWeather->setRelativeHumidity($decoded_metar['rel_humidity']);
     $maceioWeather->setLocation($decoded_metar['location']);
     $maceioWeather->setTemperatureInC($decoded_metar['temp_c']);
     $maceioWeather->setTemperatureInF($decoded_metar['temp_f']);
     $maceioWeather->setAltimeterHpa($decoded_metar['altimeter_hpa']);
     $maceioWeather->setAltimeterInhg($decoded_metar['altimeter_inhg']);

  return $decoded_metar;

}

?>
