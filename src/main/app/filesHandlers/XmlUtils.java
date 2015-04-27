package app.filesHandlers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.Charsets;

public class XmlUtils {

	public static void main(String[] args) {

		bla();

	}

	private static void bla() {
		String path = "test-defects.xml";
		File xmlFile = new File(path);

		try {

			Iterator<String> it = Files.lines(xmlFile.toPath(), Charsets.UTF_8).iterator();
			String s = "";
			while (it.hasNext()) {
				s = it.next();
				System.out.println(s);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

/**
 * A testcases item contains as many testcase items as there are testcase in the builded project
 */
class Testcases {

	private List<Testcase> testcases;

	public Testcases() {
		testcases = new ArrayList<Testcase>();
		// Get items in the xml file

	}
}

/**
 * A testcase item contains a list of key/value and sometimes a defect
 */
class Testcase {

	public static String[] testcaseAttributesNames = { "actualStatus", "classname", "message", "name", "status" };
	private Map<String, String> testcaseAttributesMap;
	private Defect defect;

	public Testcase() {
		testcaseAttributesMap = new HashMap<String, String>(testcaseAttributesNames.length);
		for (String s : testcaseAttributesNames) {
			testcaseAttributesMap.put(s, "");
		}
	}
}

/**
 *
 */
class Defect {

	public static String[] defectAttributesNames = { "expectedMessage", "link", "status", "type" };
	private Map<String, String> defectAttributesMap;

	public Defect() {
		defectAttributesMap = new HashMap<String, String>(defectAttributesNames.length);
		for (String s : defectAttributesNames) {
			defectAttributesMap.put(s, "");
		}
	}
}
