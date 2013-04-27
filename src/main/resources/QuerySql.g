grammar QuerySql;

@header{
package com.sohu.sql4nosql.build;

import java.util.Arrays;
import com.sohu.sql4nosql.QuerySqlStruct;
}
@lexer::header{
package com.sohu.sql4nosql.build;
}

querySql returns [QuerySqlStruct result = new QuerySqlStruct()]
	:	selectFromStatement[result] (whereStatement[result])? limitStatement[result]?;

selectFromStatement [QuerySqlStruct result]
	:	selectStatement[result] FROM NAME WS?{
		$result.tableName = $NAME.text;
	};
selectStatement [QuerySqlStruct result]
	:	SELECT ('*'|selectFieldName[result] (',' selectFieldName[result])*) WS+ ;
selectFieldName [QuerySqlStruct result]
	:	NAME {
		$result.selectFields.add($NAME.text);
	};
whereStatement [QuerySqlStruct result]
 	:	WHERE NAME OPTION FIELDVALUE{
 		$result.whereFieldName = $NAME.text;
 		$result.option = $OPTION.text;
 		$result.fieldValue = $FIELDVALUE.text;
 	};
	
limitStatement [QuerySqlStruct result]
	:	 LIMIT OFFSET? INT {
		if($OFFSET.text != null) {
			result.offset = Integer.parseInt($OFFSET.text.trim());
		}
		result.rowLimit = Integer.parseInt($INT.text);
	};

// more small range more prior
SELECT:('select'|'SELECT')WS+ ;
LIMIT :  WS+ ('limit'|'LIMIT') WS+;
FROM : ('from'|'FROM') WS+;
WHERE : WS+ ('where'|'WHERE') WS+;

INT : ('0'..'9')+;
NAME:('a'..'z'|'A'..'Z'|'_')+ ;
FIELDVALUE :	('\''NAME'\'')|INT;
OPTION	:	WS? ('>'|'<'|'=') WS?;
WS : (' ' |'\t' |'\n' |'\r' );
OFFSET : INT WS+;
