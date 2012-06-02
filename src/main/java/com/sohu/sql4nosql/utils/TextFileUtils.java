/**
 * 
 */
package com.sohu.sql4nosql.utils;

/**
 * @author liaohongliu
 *
 * 2012-2-23 ����09:33:02
 */
/**
 * $Id: TextFileUtils.java 17852 2012-02-15 10:20:35Z hongliuliao $
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TextFileUtils {
	
	private final static Log log = LogFactory.getLog(TextFileUtils.class);
	/**
	 * ����������������ʱ��ʹ��,��ʾ����һ����෵�صļ�¼��
	 */
	private static int READ_BATCH_SIZE = 10000;
	/**
	 * ��ȡ�ļ��е��ı���Ϣ
	 * @param filePath �ļ�·��
	 * @param handler ��ÿ���ı����еĴ���
	 */
	public static void readFileText(String filePath,String fileEncode,SingleLineHandler handler) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),fileEncode));
			String line = null;
			while((line = reader.readLine()) != null) {
				handler.handleLine(line);
			}
		} catch (Exception e) {
			log.error("read file error which path:"+filePath,e);
		} finally {
			try {
				if(reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				log.error("close file error which path:"+filePath,e);
			}
		}
	}
	/**
	 * ������ȡ�ı��ļ�
	 * @param filePath �ļ�·��
	 * @param batchSize һ�������Ĵ�С
	 * @param handler ��Ӧ������
	 */
	public static void readFileTextBatch(String filePath,String fileEncode,final int batchSize,final MultiLineHandler handler) {
		final List<String> lines = new ArrayList<String>();
		readFileText(filePath,fileEncode, new SingleLineHandler() {
			
			public void handleLine(String line) {
				if(lines.size() < batchSize) {
					lines.add(line);
				}else {
					handler.handleMultiLines(lines);
					lines.clear();
					lines.add(line);
				}
			}
		});
		//�������Ҫ�����
		if(lines.size() != 0) {
			handler.handleMultiLines(lines);
		}
	}
	/**
	 * ������ȡ�ı��ļ�
	 * @param filePath �ļ�·��
	 * @param handler ��Ӧ������
	 */
	public static void readFileTextBatch(String filePath,String fileEncode,final MultiLineHandler handler) {
		readFileTextBatch(filePath, fileEncode, READ_BATCH_SIZE, handler);
	}
	/**
	 * ÿ���ı���¼�Ĵ�����
	 * 
	 * CreateDate:2012-2-10 ����05:16:57
	 */
	public interface SingleLineHandler{
		/**
		 * �������ı�
		 * @param line �����ı�
		 */
		void handleLine(String line);
	}
	/**
	 * �����ı���¼�Ĵ�����
	 * 
	 * CreateDate:2012-2-10 ����05:32:43
	 */
	public interface MultiLineHandler {
		/**
		 * ��������ı�
		 * @param lines �����ı�
		 */
		void handleMultiLines(List<String> lines);
	}
	
	
}

