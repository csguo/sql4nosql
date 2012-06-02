/**
 * 
 */
package com.sohu.sql4pojo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanMap;

import com.sohu.sql4nosql.QuerySqlStruct;
import com.sohu.sql4nosql.SqlConstants;
import com.sohu.sql4nosql.utils.AntlrUtils;
import com.sohu.sql4nosql.utils.CommonUtils;

/**
 * @author Administrator
 *
 */
public class Sql4PojoListConverter {
	
	@SuppressWarnings("unchecked")
	public List<Map<String,?>> queryFromPojoList(String sql,List<?> pojoList){
		List<Map<String,?>> result = new ArrayList<Map<String,?>>();
		if(pojoList == null || pojoList.size() == 0){
			return result;
		}
		try {
			QuerySqlStruct sqlStruct = AntlrUtils.buildSqlStruct(sql);
			//�ȹ���
			pojoList = this.filterPojo(pojoList, sqlStruct);
			if(sqlStruct.isSelectAll()){
				for (Object pojo : pojoList) {
					BeanMap beanMap = new BeanMap(pojo);
					result.add(beanMap);
				}
			}else{
				List<String> fieldNames = sqlStruct.getSelectFields();
				for (Object pojo : pojoList) {
					Map<String, Object> values = new LinkedHashMap<String, Object>();
					for(String fieldName : fieldNames){
						Object value = CommonUtils.getProperty(pojo, fieldName);
						values.put(fieldName, value);
					}
					result.add(values);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Get sql map error which sql:"+sql,e);
		}
		return result;
	}
	private <T> List<T> filterPojo(List<T> pojoList,QuerySqlStruct querySqlStruct){
		//���û��where����ֱ�Ӱ�ԭ���ķ���
		if(!CommonUtils.isValidString(querySqlStruct.getWhereFieldName())){
			return pojoList;
		}
		List<T> filteredPojos = new ArrayList<T>();
		String queryWhereFieldValue = querySqlStruct.getFieldValue();
		if(queryWhereFieldValue.indexOf("'") != -1){//�����ѯ�����ַ������͵�Ҫȥ����������
			queryWhereFieldValue = queryWhereFieldValue.replaceAll("'", "");
		}
		for (T pojo : pojoList) {
			Object pojoWhereFieldValue = CommonUtils.getProperty(pojo, querySqlStruct.getWhereFieldName());
			if(pojoWhereFieldValue == null){
				continue;
			}
			if(SqlConstants.SIGN_OF_EQUAL.equals(querySqlStruct.getOption())){
				if(queryWhereFieldValue.equals(pojoWhereFieldValue)){
					filteredPojos.add(pojo);
				}
			}
		}
		return filteredPojos;
	}
	
}
