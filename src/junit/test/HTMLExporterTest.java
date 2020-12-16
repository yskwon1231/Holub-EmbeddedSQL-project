package junit.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

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
		ArrayList<String> columns = new ArrayList<String>();
		columns.add("aaa");
		columns.add("bbb");
		String [] colArr = new String[columns.size()];
		colArr = columns.toArray(colArr);
		
		Table t = TableFactory.create("test", colArr);
		
		Object [] val1 = {"00", "01"};
		t.insert( colArr, val1 );
		
		Object [] val2 = {"10", "11"};
		t.insert( colArr, val2 );
		
		// Test Exporter
		Writer out =
				new FileWriter(
						new File("c:/dp2020", t.name() + ".html"));
			t.export( new HTMLExporter(out) );
			out.close();
	}
}
