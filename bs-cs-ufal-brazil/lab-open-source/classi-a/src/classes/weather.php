<?

class Weather{

    var $cloudLayer1Condition;
    var $cloudLayer1AltitudeMeter;
    var $cloudLayer1AltitudeFeet;
    var $cloudLayer2Condition;
    var $cloudLayer2AltitudeMeter;
    var $cloudLayer2AltitudeFeet;
    var $cloudLayer3Condition;
    var $cloudLayer3AltitudeMeter;
    var $cloudLayer3AltitudeFeet;
    var $skyString;

    var $visibilityKilometer;
    var $visibilityMiles;
    var $visibilityString;

    var $windMetersPerSecond;
    var $windMilesPerHour;
    var $windKnots;
    var $windAngle;
    var $windDirection;

    var $snowMM;
    var $snowIn;
    var $precipitationString;

    var $tempMax6hCe;
    var $tempMin6hCe;
    var $tempMax6hFa;
    var $tempMin6hFa;
    var $tempMax24hCe;
    var $tempMin24hCe;
    var $tempMax24hFa;
    var $tempMin24hFa;
    var $temperatureString;

    var $weather;
    var $date;

    var $minutesOld;
    var $gmTime;
    var $location;
    var $temperatureInC;
    var $temperatureInF;
    var $altimeterHpa;
    var $altimeterInhg;
    var $relativeHumidity;

    function Weather(){

    }

    function setCloudLayer1Condition($value){
    	$this->cloudLayer1Condition = $value;
    }

    function setCloudLayer1AltitudeMeter($value){
    	$this->cloudLayer1AltitudeMeter = $value;
    }

    function setCloudLayer1AltitudeFeet($value){
        $this->cloudLayer1AltitudeFeet = $value;
    }

    function setCloudLayer2Condition($value){
        $this->cloudLayer2Condition = $value;
    }

    function setCloudLayer2AltitudeMeter($value){
        $this->cloudLayer2AltitudeMeter = $value;
    }

    function setCloudLayer2AltitudeFeet($value){
        $this->cloudLayer2AltitudeFeet = $value;
    }

    function setCloudLayer3Condition($value){
        $this->cloudLayer3Condition = $value;
    }

    function setCloudLayer3AltitudeMeter($value){
        $this->cloudLayer3AltitudeMeter = $value;
    }

    function setCloudLayer3AltitudeFeet($value){
        $this->cloudLayer3AltitudeFeet = $value;
    } 

    function setSkyString($value){
        $this->skyString = $value;
    }

    function setVisibilityKilometer($value){
        $this->visibilityKilometer = $value;
    }
 
    function setVisibilityMiles($value){
        $this->visibilityMiles = $value;
    }   

    function setVisibilityString($value){
        $this->visibilityString = $value;
    }   

    function setWindMetersPerSecond($value){
        $this->windMetersPerSecond = $value;
    }

    function setWindKnots($value){
        $this->windKnots = $value;
    }

    function setWindMilesPerHour($value){
        $this->windMilesPerHour = $value;
    }

    function setWindDirection($value){
        $this->windDirection = $value;
    }

    function setWindAngle($value){
        $this->windAngle = $value;
    }  

    function setSnowMM($value){
        $this->snowMM = $value;
    }

    function setSnowIn($value){
        $this->snowIn = $value;
    }

    function setPrecipitationString($value){
        $this->precipitationString = $value;
    }

    function setTempMax6hCe($value){
        $this->tempMax6hCe = $value;
    }

    function setTempMin6hCe($value){
        $this->tempMin6hCe = $value;
    }

    function setTempMax6hFa($value){
        $this->tempMax6hFa = $value;
    }

    function setTempMin6hFa($value){
        $this->tempMin6hFa = $value;
    }

    function setTempMax24hCe($value){
        $this->tempMax6hCe = $value;
    }

    function setTempMin24hCe($value){
        $this->tempMin6hCe = $value;
    }

    function setTempMax24hFa($value){
        $this->tempMax6hFa = $value;
    }

    function setTempMin24hFa($value){
        $this->tempMin6hFa = $value;
    }

    function setTemperatureString($value){
        $this->temperatureString = $value;
    }

    function setWeather($value){
        $this->weather = $value;
    }

    function setDate($date){
        $this->date = $date;
    }

    function setMinutesOld($value){
        $this->minutesOld = $value;
    }

    function setGmTime($value){
        $this->gmTime = $value;
    }

    function setLocation($value){
        $this->location = $value;
    }

    function setLocation($value){
        $this->location = $value;
    }
    
    function setTemperatureInC($value){
        $this->temperatureInC = $value;
    }

    function setTemperatureInF($value){
        $this->temperatureInF = $value;
    }

    function setAltimeterHpa($value){
        $this->altimeterHpa = $value;
    }

    function setAltimeterInhg($value){
        $this->altimeterInhg = $value;
    }

    function setRelativeHumidity($value){
        $this->relativeHumidity = $value;
    }
}

?>
