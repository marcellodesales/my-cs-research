// Comparator Utilities
// Copyright (c) 2002 Alex Blewitt <Alex.Blewitt@ioshq.com>
// Copyright (c) 2002 JavaWorld
// First published by JavaWorld in December 2002
// http://www.javaworld.com/javaworld/jw-12-2002/jw-1227-sort.html
//
//    This library is free software; you can redistribute it and/or 
//    modify it under the terms of the GNU Lesser General Public
//    License as published by the Free Software Foundation; either
//    version 2.1 of the License, or (at your option) any later version.
//
//    This library is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
//    Lesser General Public License for more details.
//
//    You should have received a copy of the GNU Lesser General Public
//    License along with this library; if not, write to the Free Software
//    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
//  
//    Alternatively, it is downloadable at http://www.gnu.org/licenses/lgpl.txt
//
//  For more information, see the COPYRIGHT notice in the root directory 

package br.ufpe.cin.stp.global.comparator;

import java.util.Comparator;
// NOTE: Case insensitive comparisons can be locale specific.
//       For example, in some locales e is equivalent as e with
//       an accent.
//
//       For more information, see the Collators in the java.text
//       package.
//
// Note: default usage will be to access CaseInsensitiveComparator.getInstance()

/** Provides an example of a Comparator, using String case-insensitive comparison.
 */
public class CaseInsensitiveComparator implements Comparator {
  /** Provides the singleton instance for this class
   */
  private static final Comparator INSTANCE = new CaseInsensitiveComparator();
  /** Accesses the singleton instance for this class
   * @return a singleton instance of <code>CaseInsensitiveComparator</code>
   */
  public static Comparator getInstance() {
    return INSTANCE;
  }
  /** Compares two strings by converting both objects 
   * <code>{@link java.lang.Object#toString()}</code> 
   * and then <code>{@link java.lang.String#toUpperCase()}</code>, and
   * compared using <code>{@link java.lang.String#compareTo} method. 
   * @return the result of <code>{@link java.lang.String#compareTo}</code>
   */
  public int compare(Object o1, Object o2) {
    String s1 = o1.toString().toUpperCase();
    String s2 = o2.toString().toUpperCase();
    return s1.compareTo(s2);
  }
}
