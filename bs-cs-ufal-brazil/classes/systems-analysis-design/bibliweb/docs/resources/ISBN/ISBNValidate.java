package cmp.ISBN;
import java.util.BitSet;

/**
* Tools to validate ISBNs International Standard Book Numbers.
*
* Typical valid ISBNs look like this:
*
* 0-13-625666-X
* 0-9600688-8-0
* 1-56592-269-7
* 1-57610-056-1
*
* The lead 0 is significant. They always contain 10 digits.
*/

public class ISBNValidate
   {

   /**
    * Tidies up an ISBN by removing stray dashes and non-numerics.
    * Validates checksum, checks publisher number,
    * and inserts dashes where they belong
    * e.g.
    * @param raw raw text to be tidied.  null and empty ok.
    */
   public static String tidyWithDashes (String raw)
      {
      if ( raw == null || raw.length() == 0 )
         {
         return null;
         }
      String trouble = tidyPacked(raw);
      if ( trouble.length() != 10 )
         {
         return trouble;
         }
      else
         {
         return leadJunk + insertDashes(clean) + trailJunk;
         }
      } // end tidyWithDashes

   /**
   * Tidies up an ISBN by removing stray dashes and non-numerics.
   * Validates checksum, checks publisher number,
   * dones not insert any dashes
   * @param raw raw text to be tidied.  null and empty ok.
   */
   public static String tidyPacked (String raw)
      {
      if ( raw == null || raw.length() == 0 )
         {
         return null;
         }
      clean = strip(raw);
      if ( clean.length() != 10 )
         {
         java.awt.Toolkit.getDefaultToolkit().beep();
         return leadJunk
         + "[" + clean + "]"
         + " is INVALID. Must be 10 digits! "
         + trailJunk;
         }
      // ensure group is ok
      if ( ! isGroupValid(clean) )
         {
         java.awt.Toolkit.getDefaultToolkit().beep();
         return leadJunk
         + "[" + clean + "]"
         + " is INVALID. Bad group number! "
         + trailJunk;
         }

      // ensure publisher is ok
      if ( ! isPublisherValid(clean) )
         {
         java.awt.Toolkit.getDefaultToolkit().beep();
         return leadJunk
         + "[" + clean + "]"
         + " is INVALID. Bad publisher number! "
         + trailJunk;
         }

      // ensure check digit ok
      if ( ! isValid(clean) )
         {
         java.awt.Toolkit.getDefaultToolkit().beep();
         return leadJunk
         + "[" + clean + "]"
         + " is INVALID. Check digit failure! "
         + trailJunk;
         }
      return leadJunk + clean + trailJunk;
      }

   // extra chars ahead of ISBN
   private static String leadJunk;

   // extra chars after ISBN
   private static String trailJunk;

   // packed ISBN, complete 10 chars, without dashes
   private static String clean;

   /**
    * strips out extraneous characters, leaving 10 digits (possibly X for last)
    */
   private static String strip (String raw)
      {

      // find where where lead junk ends
      leadJunk = "";
      for ( int i=0; i< raw.length(); i++ )
         {
         char c = raw.charAt(i);
         if ( "0123456789".indexOf(c) >= 0 )
            {
            // We have found the beginning of the ISBN
            // Should really just have to look for 0 or 1, but
            // user might think you can leave off lead 0.
            leadJunk = raw.substring(0, i);
            raw = raw.substring(i);
            break;
            }
         } // end for

      // find where where trailing junk ends
      trailJunk = "";
      for ( int i=raw.length()-1; i>=0 ; i-- )
         {
         char c = raw.charAt(i);
         if ( "0123456789Xx".indexOf(c) >= 0 )
            {
            // we have found the end of the ISBN
            trailJunk = raw.substring(i+1);
            raw = raw.substring(0, i+1);
            break;
            }
         } // end for

      // remove embedded junk
      StringBuffer cooked = new StringBuffer(10);

      for ( int i=0; i< raw.length(); i++ )
         {
         char c = raw.charAt(i);
         if ( "0123456789".indexOf(c) >= 0 )
            {
            cooked.append(c);
            }
         else if ( "Xx".indexOf(c) >=0 )
            {
            cooked.append('X');
            }
         else
            {
            /* ignore */

            }
         } // end for
      return cooked.toString();
      } // end strip

   /**
    * Validates checkdigit
    *
    * @see http://www.cwi.nl/~dik/english/codes/isbn.html
    * for details
    *
    * @param raw must be 10 chars long digits 0-9 possibly last char X,
    * no dashes.
    */
   public static boolean isValid (String raw)
      {
      // ensure weighted mod 11 checkdigit is correct.
      int result = 0;
      for ( int weight=1; weight<=10; weight++ )
         {
         char c = raw.charAt(10 - weight);
         // X counts as digit 10
         int digit = ( c != 'X') ? c - '0' : 10;
         result += digit * weight;
         } // end for
      return result % 11 == 0;
      } // end isvalid

   /**
    * Calculate the expected checkdigit from the first 9 digits.
    * Does not insert dashes.
    *
    * @param base first 9 digits expressed as String.
    * May not contain any extra lead/trail chars.
    *
    * @return complete ISBN with checkdigit, but no dashes.
    */
   public static String addCheckDigit(String base)
      {
      if ( base.length() == 8 )
         {
         base = '0' + base;
         }
      if ( base.length() != 9 )
         {
         return
         "[" + base + "]"
         + " is INVALID. Must be 9 digits! ";
         }
      int sum = 0;

      if ( ! isGroupValid(base) )
         {
         return
         "[" + base + "]"
         + " is INVALID. Bad group number! ";
         }

      if ( ! isPublisherValid(base) )
         {
         return
         "[" + base + "]"
         + " is INVALID. Bad publisher number! ";
         }

      for ( int weight=2; weight<=10; weight++ )
         {
         char c = base.charAt(10 - weight);
         int digit = c - '0';
         sum += digit * weight;
         } // end for
      sum = sum % 11;
      switch ( sum )
         {
         case 0: return tidyWithDashes(base+'0');
         case 1: return tidyWithDashes(base+'X');
         default: return tidyWithDashes(base+(char)((11-sum) + '0'));
         }
      }  // end addCheckDigit

   /**
    * Inserts dashes between group, publisher, title and checkdigit.
    *
    * For correct presentation, the 10 digits of an ISBN must
    * be divided, by hyphens, into four parts:
    * Part 1: The country or group of countries identifier
    * Part 2: The publisher identifier
    * Part 3: The title identifier
    * Part 4: The check digit
    * @see
    * http://www.bowker.com/standards/home/isbn/international/hyphenation-instructions.html
    * for details.
    * For info on group codes see:
    * http://www.bowker.com/standards/home/isbn/international/group_agencies.html
    *
    *  Group                    Insert Hyphens
    *  Identifier "0"            After
    *  -----------------------------------------
    *  00.......19          1st  3rd  9th digit
    *  200......699          "   4th       "
    *  7000.....8499         "   5th       "
    *  85000....89999        "   6th       "
    *  900000...949999       "   7th       "
    *  9500000..9999999      "   8th       "
    *
    *
    *  Group                  Insert Hyphens
    *  Identifier "1"           After
    *  ----------------------------------------
    *  0........54999           illegal
    *  55000....86979      1st  6th  9th digit
    *  869800...998999      "   7th       "
    *  9990000..9999999     "   8th       "
    *
    *  For other groups, 2 .. 99999 properly we would need to find out the rules
    *  separately for each country. For now we simply leave out the dash between
    *  publisher and title
    *
    *
    *  The following table gives the range       remainder
    *  distribution of the group identifiers:    for pub+title
    *
    *                             0 - 7          9
    *                            80 - 94         8
    *                           950 - 995        7
    *                          9960 - 9989       6
    *                         99900 - 99999      5
    *
    * @param raw must be 10 chars long digits 0-9 possibly last char X.
    * must be a valid ISBN number.
    */
   public static String insertDashes (String raw)
      {

      // first 9 digits
      int range = 0;
      try
         {
         range = Integer.parseInt(raw.substring(0, 9));
         }
      catch ( NumberFormatException e )
         {
         return raw;
         }
      int whereFirstDash = -1;
      int whereMidDash = -1;
      // never varies since always single-digit check digit.
      final int whereLastDash = 8;
      // how to format an ISBN, after categorising it into a range of numbers.
      // number is high+1 for the band.
      int [] bands = {
         // --------0  0-00-bbbbbb-x group 0
         +20000000,
         // --------1  0-200-bbbbb-x
         +70000000,
         // --------2  0-7000-bbbb-x
         +85000000,
         // --------3  0-85000-bbb-x
         +90000000,
         // --------4  0-90000-bb-x
         +95000000,
         // --------5  0-950000-b-x
         100000000,
         // --------6  1-1000-bbbb-x  group 1
         155000000,
         // --------7  1-55000-bbb-x
         186980000,
         // --------8  1-869800-bb-x
         199900000,
         // --------9  1-1999000-b-x
         200000000,
         // -------10  2-rrrrrrrr-x groups 2 .. 7
         800000000,
         // -------11  80-rrrrrrr-x groups 80 .. 94
         950000000,
         // -------12  950-rrrrrr-x groups 950 .. 995
         996000000,
         // -------13  9960-rrrrr-x groups 9960 .. 9989
         999000000,
         // -------14   groups 99900 .. 99999
         1000000000};
      boolean tryAgain = false;

      do
         {

         // figure out where middle dash goes
         int band=0;
         for ( int i=0; i<bands.length; i++ )
            {
            if ( range < bands[i] )
               {
               band = i;
               break;
               } // end if
            } // end for

         switch ( band )
            {

            /* cases 0..5 handle the standard publisher pattern, for group 0 */
            case 0:
               /* publisher 00 .. 19 : 0-00-bbbbbb-x */
               whereFirstDash = 0;
               whereMidDash = whereFirstDash + 2;
               break;

            case 1:
               /* publisher 200 .. 699 : 0-200-bbbbb-x */
               whereFirstDash = 0;
               whereMidDash = whereFirstDash + 3;
               break;

            case 2:
               /* publisher 7000 .. 8499 : 0-7000-bbbb-x */
               whereFirstDash = 0;
               whereMidDash = whereFirstDash + 4;
               break;

            case 3:
               /* publisher 85000 .. 89999 : 0-85000-bbb-x  */
               whereFirstDash = 0;
               whereMidDash = whereFirstDash + 5;
               break;

            case 4:
               /* publisher 900000 .. 94999 : 0-90000-bb-x */
               whereFirstDash = 0;
               whereMidDash = whereFirstDash + 6;
               break;

            case 5:
               /* publisher 9500000 .. 9999999 : 0-950000-b-x */
               whereFirstDash = 0;
               whereMidDash = whereFirstDash + 7;
               break;

               /* cases 6..9 : 1-1000-bbbb-x handle nonstandard publisher pattern of group-1 */
            case 6:
               whereFirstDash = 0;
               whereMidDash = 4;
               break;

            case 7: /*  1-55000-bbb-x  */
               whereFirstDash = 0;
               whereMidDash = 5;
               break;

            case 8:  /* 1-55000-bbb-x */
               whereFirstDash = 0;
               whereMidDash = 6;
               break;

            case 9: /* 1-1999000-b-x */
               whereFirstDash = 0;
               whereMidDash = 7;
               break;

            case 10: /* group codes 2 .. 7 : 2-rrrrrrrr-x */

               /* leave out dash between publisher and title */
               whereFirstDash = 0;
               break;

            case 11: /* group codes 80 .. 94:  80-rrrrrrr-x */
               /* leave out dash between publisher and title  */
               whereFirstDash = 1;
               break;

            case 12: /* group codes 950..995 : 950-rrrrrr-x */
               /* leave out dash between publisher and title */
               whereFirstDash = 2;
               break;

            case 13: /* group codes 9960 .. 9989 : 9960-rrrrr-x */
               /* leave out dash between publisher and title */
               whereFirstDash = 3;
               break;

            case 14: /* group codes 99900 .. 99999 : 99900-rrrr-x */
               whereFirstDash = 4;
               break;

            } // end switch

         } while ( tryAgain );

      // insert the three dashes
      StringBuffer cooked = new StringBuffer(13);
      for ( int i=0; i<10; i++ )
         {
         char c = raw.charAt(i);
         cooked.append (c);
         if ( i == whereFirstDash || i == whereMidDash || i == whereLastDash )
            {
            cooked.append('-');
            }
         }// end for
      return cooked.toString();
      } // end insertDashes

   /*
    * Some group numbers are invalid.
    * @param raw must be 10 chars long digits 0-9 possibly last char X,
    * no dashes.
    *
    * @return true if the group number in the ISBN is valid.
    */
   public static boolean isGroupValid (String raw)
      {

      // first 5 digits
      int group = 0;
      try
         {
         group = Integer.parseInt(raw.substring(0, 5));
         }
      catch ( NumberFormatException e )
         {
         return false;
         }

      return groupValidLookup.get(group);
      } // end isGroupValid

   /*
    * Some publisher numbers are invalid. They would be too long to leave
    * room for a book number.
    *
    * @param raw must be 10 chars long digits 0-9 possibly last char X.
    *
    * @return true if the Publisher number in the ISBN is valid.
    */
   public static boolean isPublisherValid (String raw)
      {

      // first 9 digits
      int range = 0;
      try
         {
         range = Integer.parseInt(raw.substring(0, 9));
         }
      catch ( NumberFormatException e )
         {
         return false;
         }

      // The only known range of invalid publisher numbers is in group 1.
      // There are none in group 0; in other groups I don't know.
      return ! (100000000 <= range && range <= 154999999);
      } // end isPublisherValid

   /** bit map of all valid groups, indexed by first 5 digits of ISBN */
   private static final java.util.BitSet groupValidLookup;

   /**
    * Build a BitSet to check if a group is valid, static initialisation
    */
   private static java.util.BitSet buildGroupValidLookup()
      {
      /* index by first 5 digits of ISBN to see if represents a valid group */
      java.util.BitSet groupValidLookup = new BitSet ( 100000 );
      for ( int i=0; i< validGroups.length; i++ )
         {
         int group = validGroups[i];
         // how many digits wide
         int width = Integer.toString(group).length();

         switch ( width )
            {
            case 5: groupValidLookup.set(group);
               break;
            case 4:
               for ( int j=group*10; j<group*10+9; j++ )
                  {
                  groupValidLookup.set(j);
                  }
               break;
            case 3:
               for ( int j=group*100; j<group*100+99; j++ )
                  {
                  groupValidLookup.set(j);
                  }
               break;
            case 2:
               for ( int j=group*1000; j<group*1000+999; j++ )
                  {
                  groupValidLookup.set(j);
                  }
               break;
            case 1:
               for ( int j=group*10000; j<group*10000+9999; j++ )
                  {
                  groupValidLookup.set(j);
                  }
               break;
            default:throw new IllegalArgumentException ("bug: bad validGroups table");
            } // end switch

         } // end for
      return groupValidLookup;
      }

   /* List of legal groups from http://www.isbn.spk-berlin.de/html/prefix/allpref.htm
    as of 2001 Feb 8 */
   private static int[] validGroups = {
      0,
      1,
      2,
      3,
      4,
      5,
      7,
      80,
      81,
      82,
      83,
      84,
      85,
      86,
      87,
      88,
      89,
      90,
      91,
      92,
      93,
      950,
      951,
      952,
      953,
      954,
      955,
      956,
      957,
      958,
      959,
      960,
      961,
      962,
      963,
      964,
      965,
      966,
      967,
      968,
      969,
      970,
      971,
      972,
      973,
      974,
      975,
      976,
      977,
      978,
      979,
      980,
      981,
      982,
      983,
      984,
      985,
      986,
      987,
      9952,
      9953,
      9954,
      9955,
      9956,
      9957,
      9958,
      9959,
      9960,
      9961,
      9962,
      9963,
      9964,
      9965,
      9966,
      9967,
      9968,
      9970,
      9971,
      9972,
      9973,
      9974,
      9975,
      9976,
      9977,
      9978,
      9979,
      9980,
      9982,
      9983,
      9984,
      9985,
      9986,
      9987,
      9988,
      9989,
      99901,
      99903,
      99904,
      99905,
      99906,
      99908,
      99909,
      99910,
      99911,
      99912,
      99913,
      99914,
      99915,
      99916,
      99917,
      99918,
      99919,
      99920,
      99921,
      99922,
      99923,
      99924,
      99925,
      99926,
      99927,
      99928,
      99929,
      99930,
      99931,
      99932,
      99933,
      99934,
      99935,
      99936,
      99937,
   };

   static
   {
      // can't use until validGroups table built
      groupValidLookup = buildGroupValidLookup();
   }

   } // end class ISBNValidate

