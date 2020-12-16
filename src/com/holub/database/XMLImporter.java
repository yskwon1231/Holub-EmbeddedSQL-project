package com.holub.database;

import com.holub.tools.ArrayIterator;

import java.io.*;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLImporter implements Table.Importer {
	private String  filePath;
	private String[] columnNames;
	private String tableName;
	
	// XML document parsing
	private DocumentBuilderFactory factory;
	private DocumentBuilder documentBuilder;
	private Document document;
	private NodeList rowNodeList;
	private int rowCount;
	
	public XMLImporter(String filePath) throws ParserConfigurationException, SAXException, IOException {
		this.filePath = filePath;
		
		factory = DocumentBuilderFactory.newInstance();
		documentBuilder = factory.newDocumentBuilder();
		document = documentBuilder.parse(filePath);
		System.out.println(">>> file path : " + filePath);
	}
	
	public void startTable() throws IOException {
		// Get table name.
		Element root = document.getDocumentElement();
		this.tableName = root.getNodeName();
		System.out.println(">>> table name : " + tableName);
		
		// Get column name.
		NodeList columnNodeList = document.getElementsByTagName("column");
		Node columnNode = columnNodeList.item(0);
		String tmp = columnNode.getTextContent();
		this.columnNames = tmp.split(",");
		System.out.println(">>> column : " + columnNode.getNodeName());
		System.out.println(">>> column : " + columnNames.length);
		
		// Get rows.
		this.rowNodeList = document.getElementsByTagName("row");
		this.rowCount = 0;
	}
	
	public String loadTableName() throws IOException {
		return tableName;
	}
	
	public int loadWidth() throws IOException {
		return columnNames.length;
	}
	
	public Iterator loadColumnNames() throws IOException {
		return new ArrayIterator(columnNames);
	}
	
	public Iterator loadRow() throws IOException {
		Iterator row = null;
		String [] values = null;
		
		// Get Rows
		if (rowCount < rowNodeList.getLength()) {
			Node node = rowNodeList.item(rowCount);
			for (node.getFirstChild(); node != null; node = node.getNextSibling()) {
				System.out.println(">>> row : ");
				String tmp = node.getTextContent().trim();
				System.out.println(tmp);
				if(tmp != null)
					values = tmp.split("\\s");
			}
		}
		row = new ArrayIterator(values);
		return row;
	}
	
	public void endTable() throws IOException {
		
	}
}
