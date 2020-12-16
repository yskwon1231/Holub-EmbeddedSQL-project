package junit.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.junit.Test;

import com.holub.database.HTMLExporter;
import com.holub.database.Database;
import com.holub.database.Table;
import com.holub.database.TableFactory;

public class HTMLExporterTest {
	@Test
	public void testExport() throws IOException {
		// Create Database.
		Database db = new Database("c:/dp2020");
		
		// Create Table for test.
		String[] values = new String[2];
		values[0] = "aaa";
		values[1] = "bbb";
		
		Table current = TableFactory.create("sun", values);
		
		// Test Exporter
		Writer out =
				new FileWriter(
						new File("c:/dp2020", current.name() + ".html"));
			current.export( new HTMLExporter(out) );
			out.close();
	}
}
