<?
require("/classia/classes/weather.php");
include("/classia/classes/weatherMetar.php");

	$maceioWeather = new Weather();
	$metar = get_metar('SBMO');
	$data  = process_metar($metar);
?>
