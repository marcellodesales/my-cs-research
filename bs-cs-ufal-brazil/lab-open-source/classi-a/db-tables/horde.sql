# MySQL dump 8.13
#
# Host: localhost    Database: horde
#--------------------------------------------------------
# Server version	3.23.36-log

#
# Table structure for table 'active_sessions'
#

CREATE TABLE active_sessions (
  sid varchar(32) NOT NULL default '',
  name varchar(32) NOT NULL default '',
  val text,
  changed varchar(14) NOT NULL default '',
  PRIMARY KEY  (name,sid),
  KEY changed (changed)
) TYPE=MyISAM;

#
# Dumping data for table 'active_sessions'
#

INSERT INTO active_sessions VALUES ('aa6bc6cbc7466625cf844e6f808f0815','HordeSession','base64:JHRoaXMtPmluID0gJyc7ICR0aGlzLT5wdCA9IGFycmF5KCk7IA==','20011013024217');
INSERT INTO active_sessions VALUES ('d6696a3ae91bee76bab10080b2279a20','HordeSession','base64:JHRoaXMtPmluID0gJyc7ICR0aGlzLT5wdCA9IGFycmF5KCk7IA==','20011013024221');
INSERT INTO active_sessions VALUES ('912fa5f792308a12f1623607225b869f','HordeSession','base64:JHRoaXMtPmluID0gJyc7ICR0aGlzLT5wdCA9IGFycmF5KCk7IA==','20011013024222');
INSERT INTO active_sessions VALUES ('404eebca3c0b36faa1f08e8bdcd7131b','HordeSession','base64:JHRoaXMtPmluID0gJyc7ICR0aGlzLT5wdCA9IGFycmF5KCk7IA==','20011013024224');

#
# Table structure for table 'imp_addr'
#

CREATE TABLE imp_addr (
  user varchar(120) NOT NULL default '',
  address varchar(120) NOT NULL default '',
  nickname varchar(255) default NULL,
  fullname varchar(255) default NULL,
  PRIMARY KEY  (user,address)
) TYPE=MyISAM;

#
# Dumping data for table 'imp_addr'
#


#
# Table structure for table 'imp_pref'
#

CREATE TABLE imp_pref (
  user varchar(120) NOT NULL default '',
  fullname varchar(70) default NULL,
  replyto varchar(70) default NULL,
  lang varchar(30) default NULL,
  sig text,
  PRIMARY KEY  (user)
) TYPE=MyISAM;

#
# Dumping data for table 'imp_pref'
#


