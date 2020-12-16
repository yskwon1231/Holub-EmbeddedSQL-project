package com.holub.database;

import java.io.*;
import java.util.*;

public class HTMLExporter implements Table.Exporter {
	private final Writer out;
	private int width;
	private int height;
	
	public HTMLExporter(Writer out) {
		this.out = out;
	}
	
	public void storeMetadata(String tableName, int width, int height, Iterator columnNames) throws IOException {
		this.width = width;
		this.height = height;
		
		out.write("<head>");
		out.write("<title>");
		
		out.write(tableName == null ? "<anonymous>" : tableName);
		
		out.write("</title>");
		out.write("</head>");
		
		
		storeRow(columnNames);
		
	}
	
	public void storeRow(Iterator data) throws IOException {
		int i = width;
		
		out.write("<table border=\"1\" width=\"500\" align=\"center\">");
		out.write("<tr>");
		while (data.hasNext()) {
			Object datum = data.next();
			System.out.println(">>> table: " + datum.toString());
			out.write("<td width=\"250\" align=\"center\">");
			out.write(datum.toString());
			out.write("</td>");
		}
		out.write("</tr>");
		out.write("</table>");
	}
	
	public void startTable() throws IOException {
		// html start
		out.write("<!doctype html>");
		out.write("<html>");
	}
	public void endTable() throws IOException {
		// end of html
		out.write("</html>");
	}
}
