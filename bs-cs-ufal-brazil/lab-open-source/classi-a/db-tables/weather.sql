# MySQL dump 8.13
#
# Host: localhost    Database: weather
#--------------------------------------------------------
# Server version	3.23.36-log

#
# Table structure for table 'metars'
#

CREATE TABLE metars (
  metar varchar(255) NOT NULL default '',
  timestamp timestamp(14) NOT NULL,
  station varchar(4) NOT NULL default '',
  PRIMARY KEY  (station),
  UNIQUE KEY station (station)
) TYPE=MyISAM;

#
# Dumping data for table 'metars'
#

INSERT INTO metars VALUES ('171404Z',00000000000000,'SBMO');

