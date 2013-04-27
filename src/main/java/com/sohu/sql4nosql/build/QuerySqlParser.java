// $ANTLR 3.4 /E:/workspace/sql4nosql/target/classes/QuerySql.g 2013-04-27 17:14:44

package com.sohu.sql4nosql.build;

import java.util.Arrays;
import com.sohu.sql4nosql.QuerySqlStruct;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class QuerySqlParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "FIELDVALUE", "FROM", "INT", "LIMIT", "NAME", "OFFSET", "OPTION", "SELECT", "WHERE", "WS", "'*'", "','"
    };

    public static final int EOF=-1;
    public static final int T__14=14;
    public static final int T__15=15;
    public static final int FIELDVALUE=4;
    public static final int FROM=5;
    public static final int INT=6;
    public static final int LIMIT=7;
    public static final int NAME=8;
    public static final int OFFSET=9;
    public static final int OPTION=10;
    public static final int SELECT=11;
    public static final int WHERE=12;
    public static final int WS=13;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public QuerySqlParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public QuerySqlParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return QuerySqlParser.tokenNames; }
    public String getGrammarFileName() { return "/E:/workspace/sql4nosql/target/classes/QuerySql.g"; }



    // $ANTLR start "querySql"
    // /E:/workspace/sql4nosql/target/classes/QuerySql.g:13:1: querySql returns [QuerySqlStruct result = new QuerySqlStruct()] : selectFromStatement[result] ( whereStatement[result] )? ( limitStatement[result] )? ;
    public final QuerySqlStruct querySql() throws RecognitionException {
        QuerySqlStruct result =  new QuerySqlStruct();


        try {
            // /E:/workspace/sql4nosql/target/classes/QuerySql.g:14:2: ( selectFromStatement[result] ( whereStatement[result] )? ( limitStatement[result] )? )
            // /E:/workspace/sql4nosql/target/classes/QuerySql.g:14:4: selectFromStatement[result] ( whereStatement[result] )? ( limitStatement[result] )?
            {
            pushFollow(FOLLOW_selectFromStatement_in_querySql27);
            selectFromStatement(result);

            state._fsp--;


            // /E:/workspace/sql4nosql/target/classes/QuerySql.g:14:32: ( whereStatement[result] )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==WHERE) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // /E:/workspace/sql4nosql/target/classes/QuerySql.g:14:33: whereStatement[result]
                    {
                    pushFollow(FOLLOW_whereStatement_in_querySql31);
                    whereStatement(result);

                    state._fsp--;


                    }
                    break;

            }


            // /E:/workspace/sql4nosql/target/classes/QuerySql.g:14:58: ( limitStatement[result] )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==LIMIT) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // /E:/workspace/sql4nosql/target/classes/QuerySql.g:14:58: limitStatement[result]
                    {
                    pushFollow(FOLLOW_limitStatement_in_querySql36);
                    limitStatement(result);

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return result;
    }
    // $ANTLR end "querySql"



    // $ANTLR start "selectFromStatement"
    // /E:/workspace/sql4nosql/target/classes/QuerySql.g:16:1: selectFromStatement[QuerySqlStruct result] : selectStatement[result] FROM NAME ( WS )? ;
    public final void selectFromStatement(QuerySqlStruct result) throws RecognitionException {
        Token NAME1=null;

        try {
            // /E:/workspace/sql4nosql/target/classes/QuerySql.g:17:2: ( selectStatement[result] FROM NAME ( WS )? )
            // /E:/workspace/sql4nosql/target/classes/QuerySql.g:17:4: selectStatement[result] FROM NAME ( WS )?
            {
            pushFollow(FOLLOW_selectStatement_in_selectFromStatement49);
            selectStatement(result);

            state._fsp--;


            match(input,FROM,FOLLOW_FROM_in_selectFromStatement52); 

            NAME1=(Token)match(input,NAME,FOLLOW_NAME_in_selectFromStatement54); 

            // /E:/workspace/sql4nosql/target/classes/QuerySql.g:17:38: ( WS )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==WS) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // /E:/workspace/sql4nosql/target/classes/QuerySql.g:17:38: WS
                    {
                    match(input,WS,FOLLOW_WS_in_selectFromStatement56); 

                    }
                    break;

            }



            		result.tableName = (NAME1!=null?NAME1.getText():null);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "selectFromStatement"



    // $ANTLR start "selectStatement"
    // /E:/workspace/sql4nosql/target/classes/QuerySql.g:20:1: selectStatement[QuerySqlStruct result] : SELECT ( '*' | selectFieldName[result] ( ',' selectFieldName[result] )* ) ( WS )+ ;
    public final void selectStatement(QuerySqlStruct result) throws RecognitionException {
        try {
            // /E:/workspace/sql4nosql/target/classes/QuerySql.g:21:2: ( SELECT ( '*' | selectFieldName[result] ( ',' selectFieldName[result] )* ) ( WS )+ )
            // /E:/workspace/sql4nosql/target/classes/QuerySql.g:21:4: SELECT ( '*' | selectFieldName[result] ( ',' selectFieldName[result] )* ) ( WS )+
            {
            match(input,SELECT,FOLLOW_SELECT_in_selectStatement68); 

            // /E:/workspace/sql4nosql/target/classes/QuerySql.g:21:11: ( '*' | selectFieldName[result] ( ',' selectFieldName[result] )* )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==14) ) {
                alt5=1;
            }
            else if ( (LA5_0==NAME) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }
            switch (alt5) {
                case 1 :
                    // /E:/workspace/sql4nosql/target/classes/QuerySql.g:21:12: '*'
                    {
                    match(input,14,FOLLOW_14_in_selectStatement71); 

                    }
                    break;
                case 2 :
                    // /E:/workspace/sql4nosql/target/classes/QuerySql.g:21:16: selectFieldName[result] ( ',' selectFieldName[result] )*
                    {
                    pushFollow(FOLLOW_selectFieldName_in_selectStatement73);
                    selectFieldName(result);

                    state._fsp--;


                    // /E:/workspace/sql4nosql/target/classes/QuerySql.g:21:40: ( ',' selectFieldName[result] )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==15) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // /E:/workspace/sql4nosql/target/classes/QuerySql.g:21:41: ',' selectFieldName[result]
                    	    {
                    	    match(input,15,FOLLOW_15_in_selectStatement77); 

                    	    pushFollow(FOLLOW_selectFieldName_in_selectStatement79);
                    	    selectFieldName(result);

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    }
                    break;

            }


            // /E:/workspace/sql4nosql/target/classes/QuerySql.g:21:72: ( WS )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==WS) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /E:/workspace/sql4nosql/target/classes/QuerySql.g:21:72: WS
            	    {
            	    match(input,WS,FOLLOW_WS_in_selectStatement85); 

            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "selectStatement"



    // $ANTLR start "selectFieldName"
    // /E:/workspace/sql4nosql/target/classes/QuerySql.g:22:1: selectFieldName[QuerySqlStruct result] : NAME ;
    public final void selectFieldName(QuerySqlStruct result) throws RecognitionException {
        Token NAME2=null;

        try {
            // /E:/workspace/sql4nosql/target/classes/QuerySql.g:23:2: ( NAME )
            // /E:/workspace/sql4nosql/target/classes/QuerySql.g:23:4: NAME
            {
            NAME2=(Token)match(input,NAME,FOLLOW_NAME_in_selectFieldName97); 


            		result.selectFields.add((NAME2!=null?NAME2.getText():null));
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "selectFieldName"



    // $ANTLR start "whereStatement"
    // /E:/workspace/sql4nosql/target/classes/QuerySql.g:26:1: whereStatement[QuerySqlStruct result] : WHERE NAME OPTION FIELDVALUE ;
    public final void whereStatement(QuerySqlStruct result) throws RecognitionException {
        Token NAME3=null;
        Token OPTION4=null;
        Token FIELDVALUE5=null;

        try {
            // /E:/workspace/sql4nosql/target/classes/QuerySql.g:27:3: ( WHERE NAME OPTION FIELDVALUE )
            // /E:/workspace/sql4nosql/target/classes/QuerySql.g:27:5: WHERE NAME OPTION FIELDVALUE
            {
            match(input,WHERE,FOLLOW_WHERE_in_whereStatement110); 

            NAME3=(Token)match(input,NAME,FOLLOW_NAME_in_whereStatement112); 

            OPTION4=(Token)match(input,OPTION,FOLLOW_OPTION_in_whereStatement114); 

            FIELDVALUE5=(Token)match(input,FIELDVALUE,FOLLOW_FIELDVALUE_in_whereStatement116); 


             		result.whereFieldName = (NAME3!=null?NAME3.getText():null);
             		result.option = (OPTION4!=null?OPTION4.getText():null);
             		result.fieldValue = (FIELDVALUE5!=null?FIELDVALUE5.getText():null);
             	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "whereStatement"



    // $ANTLR start "limitStatement"
    // /E:/workspace/sql4nosql/target/classes/QuerySql.g:33:1: limitStatement[QuerySqlStruct result] : LIMIT ( OFFSET )? INT ;
    public final void limitStatement(QuerySqlStruct result) throws RecognitionException {
        Token OFFSET6=null;
        Token INT7=null;

        try {
            // /E:/workspace/sql4nosql/target/classes/QuerySql.g:34:2: ( LIMIT ( OFFSET )? INT )
            // /E:/workspace/sql4nosql/target/classes/QuerySql.g:34:5: LIMIT ( OFFSET )? INT
            {
            match(input,LIMIT,FOLLOW_LIMIT_in_limitStatement130); 

            // /E:/workspace/sql4nosql/target/classes/QuerySql.g:34:11: ( OFFSET )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==OFFSET) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /E:/workspace/sql4nosql/target/classes/QuerySql.g:34:11: OFFSET
                    {
                    OFFSET6=(Token)match(input,OFFSET,FOLLOW_OFFSET_in_limitStatement132); 

                    }
                    break;

            }


            INT7=(Token)match(input,INT,FOLLOW_INT_in_limitStatement135); 


            		if((OFFSET6!=null?OFFSET6.getText():null) != null) {
            			result.offset = Integer.parseInt((OFFSET6!=null?OFFSET6.getText():null).trim());
            		}
            		result.rowLimit = Integer.parseInt((INT7!=null?INT7.getText():null));
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "limitStatement"

    // Delegated rules


 

    public static final BitSet FOLLOW_selectFromStatement_in_querySql27 = new BitSet(new long[]{0x0000000000001082L});
    public static final BitSet FOLLOW_whereStatement_in_querySql31 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_limitStatement_in_querySql36 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_selectStatement_in_selectFromStatement49 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_FROM_in_selectFromStatement52 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_NAME_in_selectFromStatement54 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_WS_in_selectFromStatement56 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SELECT_in_selectStatement68 = new BitSet(new long[]{0x0000000000004100L});
    public static final BitSet FOLLOW_14_in_selectStatement71 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_selectFieldName_in_selectStatement73 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_15_in_selectStatement77 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_selectFieldName_in_selectStatement79 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_WS_in_selectStatement85 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_NAME_in_selectFieldName97 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHERE_in_whereStatement110 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_NAME_in_whereStatement112 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_OPTION_in_whereStatement114 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_FIELDVALUE_in_whereStatement116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LIMIT_in_limitStatement130 = new BitSet(new long[]{0x0000000000000240L});
    public static final BitSet FOLLOW_OFFSET_in_limitStatement132 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_INT_in_limitStatement135 = new BitSet(new long[]{0x0000000000000002L});

}