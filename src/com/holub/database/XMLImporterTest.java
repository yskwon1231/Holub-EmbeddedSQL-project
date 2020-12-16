package com.holub.database;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.holub.database.XMLImporter;
import com.holub.database.ConcreteTable;
import com.holub.database.Database;
import com.holub.database.Table;

public class XMLImporterTest {
	@Test
	public void testExport() throws IOException, ParserConfigurationException, SAXException {
		// Create Database.
		Database db = new Database("c:/dp2020");
				
		// Create Table for test.
		Table t = new ConcreteTable(new XMLImporter("c:/dp2020/test.xml"));
	}
}

