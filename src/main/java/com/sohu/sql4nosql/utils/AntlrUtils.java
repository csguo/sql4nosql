/**
 * 
 */
package com.sohu.sql4nosql.utils;

import java.util.Map;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;

import com.sohu.sql4nosql.QuerySqlStruct;
import com.sohu.sql4nosql.build.QuerySqlLexer;
import com.sohu.sql4nosql.build.QuerySqlParser;

/**
 * @author liaohongliu
 *
 * 2012-2-23 ����09:35:10
 */
public class AntlrUtils {
	
	@SuppressWarnings("unchecked")
	public static QuerySqlStruct buildSqlStruct(String sql){
		try {
			QuerySqlLexer lexer = new QuerySqlLexer(new ANTLRStringStream(sql));
			CommonTokenStream tokens = new CommonTokenStream(lexer); 
			QuerySqlParser parser = new QuerySqlParser(tokens);
			Map<String,String> sqlMap = parser.querySql();
			QuerySqlStruct sqlStruct = CommonUtils.buildSqlStruct(sqlMap);
			return sqlStruct;
		} catch (Exception e) {
			throw new RuntimeException("build sql struct error which sql:"+sql,e);
		}
		
	}
}
