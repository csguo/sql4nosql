/**
 * 
 */
package com.sohu.sql4nosql;

import java.util.ArrayList;
import java.util.List;

/**
 * the struct of the query sql
 * @author Administrator
 *
 */
public class QuerySqlStruct {
	/**
	 * �Ƿ��ǲ�ѯ����
	 */
	private boolean selectAll;
	/**
	 * ��ѯ���ֶ�
	 */
	private List<String> selectFields;
	private String whereFieldName;
	private String option;
	private String fieldValue;
	private String tableName;
	
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getWhereFieldName() {
		return whereFieldName;
	}
	public void setWhereFieldName(String whereFieldName) {
		this.whereFieldName = whereFieldName;
	}
	public boolean isSelectAll() {
		return selectAll;
	}
	public void setSelectAll(boolean selectAll) {
		this.selectAll = selectAll;
	}
	public List<String> getSelectFields() {
		if(this.selectFields == null){
			return null;
		}
		List<String> fields = new ArrayList<String>();//����ȥ���ո�֮����ֶ���
		for(String fieldName : selectFields){
			fields.add(fieldName.trim());
		}
		return fields;
	}
	public void setSelectFields(List<String> selectFields) {
		this.selectFields = selectFields;
	}
	public boolean hasWhere(){
		return this.getWhereFieldName()!=null && !this.getWhereFieldName().trim().equals("");
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
