/**
 * 
 */
package com.sohu.sql4nosql.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
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

	public static List<Class<?>> constType=new ArrayList<Class<?>>();
	static{
		constType.add(Long.class);
		constType.add(Integer.class);
		constType.add(Double.class);
		constType.add(Float.class);
		constType.add(java.util.Date.class);
		constType.add(java.sql.Date.class);
		constType.add(String.class);
		constType.add(Boolean.class);
		constType.add(Timestamp.class);
		constType.add(BigDecimal.class);
		constType.add(long.class);
		constType.add(int.class);
		constType.add(double.class);
		constType.add(boolean.class);
		constType.add(float.class);
	}
	public static void setProperty(Object obj,String name,Object value){
		try {
			BeanUtils.setProperty(obj, name, value);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public static String getPropertyString(Object obj,String name){
		try {
			return BeanUtils.getProperty(obj, name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	public static Object getProperty(Object obj,String name){
		try {
			return PropertyUtils.getProperty(obj, name);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
	 * �õ����С�����ʽ���ַ��������һ��
	 * @param name �ַ���
	 * @return ��ȡ����ַ���
	 */
	public static String getSimpleName(String name){
		if(name.lastIndexOf(".")!=-1){
			String temp=name.substring(name.lastIndexOf(".")+1);
			return temp;
		}else{
			return name;
		}
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
	 * ����ѯ������װ�Ķ���ת��Ϊmap
	 * @param obj
	 * @return map��keyΪ������,valueΪ����ֵ
	 */
	public static Map<String,Object> Object2Map(Object obj){
		return getFieldValueEx(obj,new LinkedHashMap<String,Object>());
	}
	/**
	 * �ݹ�Ļ�����Զ�Ӧ��ֵ(��ͬ���ǲ����Ϣ��ʧ,���Ҷ��󲻻���Ϊmap������)
	 * @param obj Ҫ���ֵ�Ķ���
	 * @param valuesMap Ϊ�˵ݹ��ʵ��,����ʹ��new HashMap();
	 * @return map��keyΪ������,valueΪֵ
	 */
	private static Map<String,Object> getFieldValueEx(Object obj,Map<String,Object> valuesMap){
		Class<?> clazz=obj.getClass();
		try {
			Field[] fields=clazz.getDeclaredFields();
			for(int i=0;i<fields.length;i++){
				if(constType.contains(fields[i].getType())){
					Object temp=CommonUtils.getPropertyString(obj, fields[i].getName());
					if(temp!=null){
						valuesMap.put(fields[i].getName(), temp);
					}
				}else{
					Object oo=CommonUtils.getPropertyString(obj, fields[i].getName());
					if(oo!=null){
						getFieldValueEx(oo, valuesMap);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valuesMap;
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
		return sqlStruct;
	}
}
