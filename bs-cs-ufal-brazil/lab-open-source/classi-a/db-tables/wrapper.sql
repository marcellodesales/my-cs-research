# phpMyAdmin MySQL-Dump
# http://phpwizard.net/phpMyAdmin/
#
# Host: localhost Database : wrapper

# --------------------------------------------------------
#
# Table structure for table 'economy'
#

CREATE TABLE economy (
   economyID varchar(20) NOT NULL,
   wrapperID varchar(20) NOT NULL,
   commercialDollarPurchase varchar(10) NOT NULL,
   commercialDollarSale varchar(10) NOT NULL,
   parallelDollarPurchase varchar(10) NOT NULL,
   parallelDollarSale varchar(10) NOT NULL,
   tourismDollarPurchase varchar(10) NOT NULL,
   tourismDollarSale varchar(10) NOT NULL,
   saving varchar(10) NOT NULL,
   TR varchar(10) NOT NULL,
   gold varchar(10) NOT NULL,
   PRIMARY KEY (economyID),
   KEY newsID (economyID)
);

#
# Dumping data for table 'economy'
#

INSERT INTO economy VALUES ( '1003440628', '1003431927', 'R$ 2.779', 'R$ 2.779', 'R$ 2.800', 'R$ 2.850', 'R$ 2.680', 'R$ 2.800', '0.7776%', '0.28', 'R$ 24.50/g');

# --------------------------------------------------------
#
# Table structure for table 'economywrapperflags'
#

CREATE TABLE economywrapperflags (
   wrapperID varchar(20) NOT NULL,
   explodeFlags varchar(255) NOT NULL,
   dollarBreak varchar(255) NOT NULL,
   commercialDollarPurchaseFlags varchar(255),
   commercialDollarSaleFlags varchar(255) NOT NULL,
   parallelDollarPurchaseFlags varchar(255),
   parallelDollarSaleFlags varchar(255) NOT NULL,
   tourismDollarPurchaseFlags varchar(255),
   tourismDollarSaleFlags varchar(255) NOT NULL,
   savingFlags varchar(255) NOT NULL,
   TRFlags varchar(255) NOT NULL,
   goldFlags varchar(255) NOT NULL,
   PRIMARY KEY (wrapperID),
   KEY wrapperID (wrapperID)
);

#
# Dumping data for table 'economywrapperflags'
#

INSERT INTO economywrapperflags VALUES ( '1003431927', '<font face=\"Verdana, Arial\" size=\"1\"><b>', '<font face=\"Arial, Verdana\"', 'size=\"1\"><flag></font></td>', 'size=\"1\"><flag></font></td>', 'size=\"1\"><flag></font></td>', 'size=\"1\"><flag></font></td>', 'size=\"1\"><flag></font></td>', 'size=\"1\"><flag></font></td>', 'size=\"1\"><flag>&nbsp; </font></td>', 'size=\"1\"><flag>&nbsp;', 'size=\"1\"><flag>/gr');

# --------------------------------------------------------
#
# Table structure for table 'news'
#

CREATE TABLE news (
   newsID varchar(20) NOT NULL,
   wrapperID varchar(20) NOT NULL,
   category varchar(100),
   source varchar(100),
   date varchar(10),
   title varchar(255) NOT NULL,
   description text NOT NULL,
   link varchar(255) NOT NULL,
   PRIMARY KEY (newsID),
   KEY newsID (newsID)
);

#
# Dumping data for table 'news'
#


# --------------------------------------------------------
#
# Table structure for table 'newswrapperflags'
#

CREATE TABLE newswrapperflags (
   wrapperID varchar(20) NOT NULL,
   explodeFlags varchar(255) NOT NULL,
   categoryFlags varchar(255),
   sourceFlags varchar(255),
   dateFlags varchar(255),
   titleFlags varchar(255) NOT NULL,
   descriptionFlags varchar(255) NOT NULL,
   linkFlags varchar(255) NOT NULL,
   PRIMARY KEY (wrapperID),
   KEY wrapperID (wrapperID)
);

#
# Dumping data for table 'newswrapperflags'
#

INSERT INTO newswrapperflags VALUES ( '1003442991', '<font size=-2><b>', '<font size=-2><b><flag></b><br>', '</b><br><flag> <!-- --><br>', '<!-- --><br><flag></font></td>', '_blank\"><font size=-1><flag></font></a>', '<br><font size=-2><flag></font><br></td>', '<a href=\"<flag>\" class=');

# --------------------------------------------------------
#
# Table structure for table 'wrapper'
#

CREATE TABLE wrapper (
   wrapperID varchar(20) NOT NULL,
   URL varchar(255) NOT NULL,
   startTag varchar(255) NOT NULL,
   endTag varchar(255) NOT NULL,
   description varchar(255),
   PRIMARY KEY (wrapperID),
   KEY wrapperID (wrapperID)
);

#
# Dumping data for table 'wrapper'
#

INSERT INTO wrapper VALUES ( '1003431927', 'http://localhost/wrapper/gazetawebcom.html', '<html>', '</html>', 'Gazeta Web Ponto Com');
INSERT INTO wrapper VALUES ( '1003442991', 'http://localhost/wrapper/un.htm', '<html>', '</html>', 'Site Últimas notícias UN');
