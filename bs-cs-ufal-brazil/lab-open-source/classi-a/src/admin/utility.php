<?

class Utility{

      function Utility(){

      }
      
      function setCookieFirstName($firstName){
      		setcookie("firstName",$firstName,time()+time());
      }

      function getKindOfAnnouncePerCategory($a){
               $selectedCategories = array (3  => array(1,5,6),
                                            9  => array(2,5),
                                            11 => array(1,3,5,6),
                                            13 => array(1,2,5,6),
                                            14 => array(1,3,4,5,6),
                                            16 => array(1,5,6)
                                     );
               $catAll     = array(1,2,4,5,7,17,19,21,22,23,24,25,26,27);
               $catNeither = array(6,8,10,12,15,18,20);

               if (in_array($a,$catAll))
                     return 'A';
                     else if (in_array($a,$catNeither))
                          return 'N';
                              else {
                                    $value = $selectedCategories[$a];
                                    return $value;
                              }
      }

      function getPrintableDate($date){
                $newDate = substr($date, 8, 2);
                $newDate = $newDate . "/" . substr($date, 5, 2);
                $newDate = $newDate . "/" . substr($date, 0, 4);
                return $newDate;
      }

      function translateTimeStamp($datePassed){
                $ENweek = array(0=>"Monday",
                                1=>"Tuesday",
                                2=>"Wednesday",
                                3=>"Thursday",
                                4=>"Friday",
                                5=>"Saturday",
                                6=>"Sunday");

                $week = array("Monday" =>"Segunda",
                              "Tuesday" =>"Terça",
                              "Wednesday"=>"Quarta",
                              "Thursday"=>"Quinta",
                              "Friday"=>"Sexta",
                              "Saturday"=>"Sábado",
                              "Sunday"=>"Domingo");

                $ENMonth = array( 0=>"January",
                                  1=>"February",
                                  2=>"March",
                                  3=>"April",
                                  4=>"May",
                                  5=>"June",
                                  6=>"July",
                                  7=>"August",
                                  8=>"September",
                                  9=>"October",
                                  10=>"November",
                                  11=>"December");

                $month = array("January"=>"Janeiro",
                               "February"=>"Fevereiro",
                               "March"=>"Março",
                               "April"=>"Abril",
                               "May"=>"Maio",
                               "June"=>"Junho",
                               "July"=>"Julho",
                               "August"=>"Agosto",
                               "September"=>"Setembro",
                               "October"=>"Outubro",
                               "November"=>"Novembro",
                               "December"=>"Dezembro");

                for($i = 0; $i < sizeof($ENweek); $i++){
                     if (ereg($ENweek[$i],$datePassed)){
                         $datePassed = ereg_replace($ENweek[$i],$week[$ENweek[$i]],$datePassed);
                         break;
                     }
                }

                for($i = 0; $i < sizeof($ENMonth); $i++){
                     if (ereg($ENMonth[$i],$datePassed)){
                         $datePassed = ereg_replace($ENMonth[$i],$month[$ENMonth[$i]],$datePassed);
                         break;
                     }
                }
                return $datePassed;
      }

      function getPrintableTimeStamp($timeStamp){
              $timeStamp = strftime("%A, %d de %B de %Y %H:%M:%S",$timeStamp);
              $timeStamp = $this->translateTimeStamp($timeStamp);
              return $timeStamp;
      }

      function quantMenu(){
                $checkUser = "SELECT count(*) FROM t_categoria_anuncio";
                $this->query($checkUser);
                $linhas = $this->affected_rows();
                return ($linhas == 1);
      }

      function getDate() {
            $week = date("D");
            $day  = date("d");
//            $time = date("H");
//            $min  = date("i");
//            $hr   = date("a");
            $month = date("F");
            $year = date("Y");

            if ($week == "Mon") { $week = "Segunda"; }
            if ($week == "Tue") { $week = "Terça"; }
            if ($week == "Wed") { $week = "Quarta"; }
            if ($week == "Thu") { $week = "Quinta"; }
            if ($week == "Fri") { $week = "Sexta"; }
            if ($week == "Sat") { $week = "Sábado"; }
            if ($week == "Sun") { $week = "Domingo"; }


            if ($month == "January")   { $month = "Janeiro"; }
            if ($month == "February")  { $month = "Fevereiro"; }
            if ($month == "March")     { $month = "Março"; }
            if ($month == "April")     { $month = "Abril"; }
            if ($month == "May")       { $month = "Maio"; }
            if ($month == "June")      { $month = "Junho"; }
            if ($month == "July")      { $month = "Julho"; }
            if ($month == "August")    { $month = "Agosto"; }
            if ($month == "September") { $month = "Setembro"; }
            if ($month == "October")   { $month = "Outubro"; }
            if ($month == "November")  { $month = "Novembro"; }
            if ($month == "December")  { $month = "Dezembro"; }

            return "$week, $day de $month de $year";
  }

  function getNewId($length = 13) {
              // all the chars we want to use
	   $newId = "";
         $allValues = explode( " ",
                    "a b c d e f g h i j k l m n o p q r s t u v w x y z "
                    . "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z "
                    . "0 1 2 3 4 5 6 7 8 9 -");

         for ($i = 0; $i < $length; $i++) {
                    srand((double)microtime()*1000000);
                    $randy = rand(0, 61);
                    $newId .= $allValues[$randy];
         }
         return $newId;
  }

  function getRemoteDNS($ip){

  	if(!$ip){
    		$ip = getenv('REMOTE_ADDR');
 	}
  	if(!$ip){
    		$ip = $REMOTE_ADDR;
  	}
  	if(!$ip){
    		$ip = $REMOTE_HOST;
  	}

  	$host = @GetHostByAddr($ip);
	return $host;
  }

  function getUsPrice($price){
           return ereg_replace(",",".",$price);
  }

  function getBrPrice($price){
           return ereg_replace("\\.",",",$price);
  }

  function getDbDate(){
           return date("Y-m-d");
  }

  function getFutureDbDate($quantDays){
           return date("Y-m-d", mktime (0,0,0,date("m"),date("d")+$quantDays,date("Y")));
  }
}

?>
