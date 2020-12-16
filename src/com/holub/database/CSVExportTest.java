package com.holub.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.junit.Test;

public class CSVExportTest {
	@Test
	public void testExport() throws IOException {
		Database db = new Database("c:/dp2020");
		
		String[] values = new String[2];
		values[0] = "aaa";
		values[1] = "bbb";
		
		Table current = TableFactory.create("sun", values);
		Writer out =
				new FileWriter(
						new File("c:/dp2020", current.name() + ".csv"));
			current.export( new CSVExporter(out) );
			out.close();
	}
}
