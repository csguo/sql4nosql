/**
 * 
 */
package com.sohu.sql4nosql.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com.sohu.sql4nosql.QuerySqlStruct;
import com.sohu.sql4nosql.Sql4nosqlConstants;
import com.sohu.sql4nosql.SqlConstants;



/**
 * this is a utils like commons-lang but more function
 * @author user
 *
 */
public class CommonUtils {

	public static void setProperty(Object obj,String name,Object value){
		try {
			BeanUtils.setProperty(obj, name, value);
		} catch (Exception e) {
			throw new RuntimeException("can not set property value which propertyName:"+name+",value:"+value,e);
		} 
	}
	public static String getPropertyString(Object obj,String name){
		try {
			return BeanUtils.getProperty(obj, name);
		} catch (Exception e) {
			throw new RuntimeException("can not get property string value which propertyName:"+name,e);
		} 
	}
	public static Object getProperty(Object obj,String name){
		try {
			return PropertyUtils.getProperty(obj, name);
		} catch (Exception e) {
			throw new RuntimeException("can not get property value which propertyName:"+name,e);
		} 
	}
	

	/**
	 * �ж��ַ�������Ч��
	 * @param str Ҫ�жϵ��ַ���
	 * @return �Ƿ���Ч,true:��Ч
	 */
	public static boolean isValidString(String str){
		return str!=null&&str.trim().length()>0;
	}
	/**
	 * �õ����С�����ʽ���ַ������ʼһ��
	 * @param name �ַ���
	 * @return ��ȡ����ַ���
	 */
	public static String getFirstName(String name){
		if(name.indexOf(".")!=-1){
			String temp=name.substring(0,name.indexOf("."));
			return temp;
		}else{
			return name;
		}
	}
	/**
	 * �����ֶ���������ֶ�,֧������
	 * @param clazz ָ��Ҫ����ֶε���
	 * @param name �ֶ���,�༶�õ�Ÿ���
	 * @return �ҵ����ֶ�,���û�о�Ϊ��
	 */
	public static Field getFieldByString(Class<?> clazz,String name){
		try {
			if(name.indexOf(".")==-1){
				return clazz.getDeclaredField(name);
			}
			Field field=clazz.getDeclaredField(name.substring(0,name.indexOf(".")));
			Class<?> cla=field.getType();
			return cla.getDeclaredField(name.substring(name.indexOf(".")+1));
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * ������ĸ��д
	 * @param str Ҫ��д�ĵ���
	 * @return ����ĸ��д��ĵ���
	 */
	public static String getFirstUpper(String str){
		return str.substring(0, 1).toUpperCase()+str.substring(1);
	}
	public static QuerySqlStruct buildSqlStruct(Map<String,String> sqlMap){
		QuerySqlStruct sqlStruct = new QuerySqlStruct();
		String selectFieldNames = sqlMap.get(Sql4nosqlConstants.SELECTFIELDNAME);
		if(SqlConstants.SIGN_OF_SELECT_ALL.equals(selectFieldNames.trim())){
			sqlStruct.setSelectAll(true);
		}else{
			sqlStruct.setSelectFields(Arrays.asList(selectFieldNames.split(",")));
		}
		sqlStruct.setWhereFieldName(sqlMap.get(Sql4nosqlConstants.WHEREFIELDNAME));
		sqlStruct.setOption(sqlMap.get(Sql4nosqlConstants.OPTION));
		sqlStruct.setFieldValue(sqlMap.get(Sql4nosqlConstants.FIELDVALUE));
		sqlStruct.setTableName(sqlMap.get(Sql4nosqlConstants.TABLENAME));
		return sqlStruct;
	}
}
