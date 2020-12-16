package com.holub.database;

import java.io.*;
import java.util.*;

public class XMLExporter implements Table.Exporter {
	private final Writer out;
	private String tableName;
	private ArrayList<String> columnNames = new ArrayList<String>();
	
	public XMLExporter (Writer out) {
		this.out = out;
	}
	
	public void storeMetadata (String tableName, int width, int height, Iterator columnNames) throws IOException {
		// Store table name.
		if (tableName == null)
			this.tableName = "anonymous";
		else
			this.tableName = tableName;
		
		System.out.println(">>> table name : " + this.tableName);
		
		// Store column names.
		System.out.println(">>> XMLExporter - 1");
		while (columnNames.hasNext()) {
			System.out.println(">>> XMLExporter - while loop");
			Object col = columnNames.next();
			System.out.println(col.toString());
			this.columnNames.add(col.toString());
		}
		System.out.println(">>> XMLExporter : " + this.columnNames.toString());
	}
	
	public void storeRow (Iterator data) throws IOException {
		out.write("<" + this.tableName + ">");
		out.write("\n");
		
		Iterator colData = this.columnNames.iterator();
		while (data.hasNext()) {
			Object datum = data.next();
			Object column = colData.next();
			
			out.write("\t");
			out.write("<" + column.toString() + ">");
			out.write(datum.toString());
			out.write("</" + column.toString() + ">");
			out.write("\n");
		}
		out.write("</" + this.tableName + ">");
		out.write("\n");
	}
	
	public void startTable() throws IOException {
		// XML start
		out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.write("\n");
	}
	public void endTable() throws IOException {
		// nothing to do.
	}
}
