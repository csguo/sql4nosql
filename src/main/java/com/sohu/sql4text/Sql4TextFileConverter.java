/**
 * 
 */
package com.sohu.sql4text;

import java.util.ArrayList;
import java.util.List;

import com.sohu.sql4nosql.QuerySqlStruct;
import com.sohu.sql4nosql.utils.AntlrUtils;
import com.sohu.sql4nosql.utils.TextFileUtils;
import com.sohu.sql4nosql.utils.TextFileUtils.MultiLineHandler;

/**
 * @author liaohongliu
 *
 * 2012-2-23 ����09:22:22
 */
public class Sql4TextFileConverter {
	/**
	 * The fieldName which used in sql and the sort order must match the order which split by splitChar
	 */
	private String[] fieldNames;
	/**
	 * the orgin file path
	 */
	private String filePath;
	/**
	 * the orgin file encode default is utf-8
	 */
	private String fileEncode = "utf-8";
	/**
	 * the split char which split the file each line default is ","
	 */
	private String splitChar = ",";
	
	public Sql4TextFileConverter(String[] fieldNames, String filePath) {
		super();
		this.fieldNames = fieldNames;
		this.filePath = filePath;
	}
	public List<String> queryForTextFile(final String sql){
		if(fieldNames == null || fieldNames.length == 0){
			throw new IllegalArgumentException("arg fieldNames can not be null!");
		}
		final List<String> fieldNameList = new ArrayList<String>();
		for (String fieldName : fieldNames) {
			fieldNameList.add(fieldName);
		}
		final List<String> result = new ArrayList<String>();
		TextFileUtils.readFileTextBatch(filePath, fileEncode, new MultiLineHandler() {
			
			public void handleMultiLines(List<String> lines) {
				QuerySqlStruct sqlStruct = AntlrUtils.buildSqlStruct(sql);
				String whereFieldName = sqlStruct.getWhereFieldName();
				int index = fieldNameList.indexOf(whereFieldName);
				List<String> tempLines = new ArrayList<String>();
				if(!sqlStruct.hasWhere()){
					tempLines = lines;
				}else{
					for (String line : lines) {
						if(index == -1){
							throw new IllegalArgumentException("can not find field which fieldName:"+whereFieldName);
						}
						String[] fieldValues = line.split(splitChar);
						String fieldValue = fieldValues[index];
						String queryWhereFieldValue = sqlStruct.getFieldValue();
						if(queryWhereFieldValue.indexOf("'") != -1){//�����ѯ�����ַ������͵�Ҫȥ����������
							queryWhereFieldValue = queryWhereFieldValue.replaceAll("'", "");
						}
						if(queryWhereFieldValue.equals(fieldValue)){
							tempLines.add(line);
						}
					}
				}
				if(sqlStruct.isSelectAll()){
					result.addAll(tempLines);
					return ;
				}else{
					for (String line : tempLines) {
						StringBuffer outLine = new StringBuffer();
						List<String> selectFields = sqlStruct.getSelectFields();
						for (int i=0;i<selectFields.size();i++) {
							String selectFieldName = selectFields.get(i);
							int tempIndex = fieldNameList.indexOf(selectFieldName);
							if(tempIndex == -1){
								throw new IllegalArgumentException("can not find field which fieldName:"+selectFieldName);
							}
							outLine.append(line.split(splitChar)[tempIndex]);
							if(i!=selectFields.size() - 1){
								outLine.append(",");
							}
						}
						result.add(outLine.toString());
					}
				}
			}
		});
		return result;
	}
	/**
	 * ���ı���ִ��sql���
	 * @param sql Ҫִ�е�sql���
	 * @param filePath �ļ�·��
	 * @param fieldNames �ļ���ÿ���ָ����������Ӧ���ֶ���
	 * @return ��ѯ���ļ�¼
	 */
	public List<String> queryForTextFile(final String sql,String filePath,String[] fieldNames){
		return this.queryForTextFile(sql);
	}
	public void setFileEncode(String fileEncode) {
		this.fileEncode = fileEncode;
	}
	public void setSplitChar(String splitChar) {
		this.splitChar = splitChar;
	}
}
