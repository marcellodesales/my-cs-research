
package pl.criteria;
/**
*  Last Updated $Date: 2002/07/08 20:46:43 $
*  Last Committed by $Author: rodrigo $
*  Revision $Revision: 1.1.1.1 $
*  $Log: RegExpNoCaseCriteria.java,v $
*  Revision 1.1.1.1  2002/07/08 20:46:43  rodrigo
*  Versao do Lab
*
*  Revision 1.1  2001/07/31 08:34:12  artyomr
*  no message
*
*
*	Property of Intrinity Inc.
*	All rights reserved
*/

public class RegExpNoCaseCriteria extends SelectionCriteria
{

  public RegExpNoCaseCriteria(pl.map.ClassMap classMap, pl.map.AttributeMap attributeMap)
  {
    super(classMap, attributeMap);
  }

  public void fillSqlStatement(pl.sql.SqlStatement statement, java.util.Iterator parameters) throws pl.PlException
  {
    pl.map.ColumnMap cm = getAttributeMap().getColumnMap();
    statement.addSqlClause(cm.getFullyQualifiedName() + " ~* ?");
    statement.addParameter(parameters.next(), cm.getPlType());
  }

}

