package com.icss.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AppContext {
	
	private static String dbfile;
	private static Map<String,Object> beans;
	
	static {		
		try {
			beans = new HashMap<String,Object>();
			//¶ÁÈ¡beans.xmlÎÄ¼þ
			String path = DbInfo.class.getResource("/").toURI().getPath() + "beans.xml";
//			String path = "C:\\Users\\Administrator\\Desktop\\108\\JDBC\\code\\StaffUserIOC\\bin\\beans.xml";;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(new File(path));							
			Node node = document.getElementsByTagName("dbfile").item(0);
			dbfile = node.getTextContent();
			NodeList nodes =  document.getElementsByTagName("bean");
			for(int i=0;i<nodes.getLength();i++){
				Node n = nodes.item(i);
				Element ele = (Element)n;
				String id = ele.getAttribute("id");
				String ctype = ele.getAttribute("class");
				Class<?> aa = Class.forName(ctype);
				Object obj = aa.newInstance();
				beans.put(id,obj);				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	public static String getDbFile() {
		return dbfile;
	}
	
	public static Object getBean(String bname) {
		return beans.get(bname);
	}
	

}
