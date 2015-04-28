package app.filesHandlers;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.Charsets;

public class XmlUtils {

	public static void main(String[] args) throws Exception {

		Testcases xmlFileStoredInAnObject = new Testcases();
		String path = "test-defects.xml";
		File xmlFile = new File(path);

//		String str = "<testcase classname=\"test.sap.sl.sdk.authoring.IssueWatcherTest\" name=\"failingTestNoDefect\" status=\"failed\"/>";
//		String test = applyRegex("<testcases>","<\\btestcases\\b(.*=\".*\")?/?>");
//		System.out.println("result : "+test);
//		test = applyRegex("<testcase actualStatus=\"failed\" classname=\"test.sap.sl.sdk.authoring.IssueWatcherTest\" message=\"Error\" name=\"failingTest3_ClassDefect\" status=\"passed\">","<\\btestcase\\b(.*=\".*\")/?>");
//		System.out.println("result : "+test);
		
		try {

			Iterator<String> it = Files.lines(xmlFile.toPath(), Charsets.UTF_8).iterator();
			String s = "";
			while (it.hasNext()) {
				s = it.next();
				// System.out.println(s);

				if (!"".equals(applyRegex(s, "<\\btestcases\\b(.*=\".*\")*/?>"))) {
					xmlFileStoredInAnObject = new Testcases();
					continue;
				} else if (!"".equals(applyRegex(s, "<\\btestcase\\b(.*=\".*\")*/?>"))) {
					xmlFileStoredInAnObject.addTestcase(s);

					continue;
				} else if (!"".equals(applyRegex(s, "<\\bdefect\\b(.*=\".*\")*/?>"))) {
					// Get last testcase added and set in it the defect
					xmlFileStoredInAnObject.addDefect(s);
					continue;
				}
			}

			System.out.println(xmlFileStoredInAnObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// try {
		// String firstattr = getValue(applyRegex("<testcase actualStatus=\"failed\" classname=\"test.sap.sl.sdk.authoring.IssueWatcherTest\" "
		// + "message=\"Error\" name=\"failingTest3_ClassDefect\" status=\"passed\">", "\\bname=\"(.*?)\""));
		// System.out.println(firstattr);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * Apply a regex on a String
	 * 
	 * @param stringToParse
	 * @param regex
	 * @return The result of the regex
	 * @throws Exception
	 */
	public static String applyRegex(String stringToParse, String regex) throws Exception {
		// String issue = execute(issueRestUrl);
		/**
		 * Qui fonctionnent : - (?<=<[\\/?]?)\\w+(?::\\w+)? : retourne le tag - classname=\"(.*?)\" : retourne l'attribut et se valeur
		 */
		// System.out.println("Tested string : " + stringToParse);
		// String regex = "classname=\"(.*?)\"";
		// .*\"status\":\\{.*?\"name\":\"(.*?)\".*?\\}.*
		// .?[a-z]*\"
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(stringToParse);

		if (m.find()) {
			return m.group();
		} else {
			return "";
		}

	}

	/**
	 * 
	 * @param stringToParse
	 *            The format of string is : <String>="<String>"
	 * @return The second String contained in the stringToParse
	 * @throws Exception
	 */
	public static String getValue(String stringToParse) throws Exception {
		// Validate the format
		if (!"".equals(applyRegex(stringToParse, ".*=\".*\""))) {
			// System.out.println("parse ok");
			stringToParse = stringToParse.split("=")[1];
			stringToParse = stringToParse.replace("\"", "");
			// System.out.println(stringToParse);
			return stringToParse;
		} else {
			// System.out.println("parse failed");
			return "";
		}

	}

	private static String testRegex(String s) throws Exception {

		String prevert = "Une pierre\n" + "deux maisons\n" + "trois ruines\n" + "quatre fossoyeurs\n" + "un jardin\n" + "des fleurs\n" + "\n"
				+ "un raton laveur\n" + "\n" + "une douzaine d'hu�tres un citron un pain\n" + "un rayon de soleil\n" + "une lame de fond\n" + "six musiciens\n"
				+ "une porte avec son paillasson\n" + "un monsieur d�cor� de la l�gion d'honneur\n" + "\n" + "un autre raton laveur";

		/*
		 * 
		 * Les patterns : - un : signifie que je cherche tous les *un* dans la cha�ne - \\bun\\b : signifie que je cherches exactement 'un' dans ma cha�ne -
		 * (\\bun\\b)|(\\bune\\b) : soit 'un' soit 'une' - \\bune?\\b : 'un' suivie (ou pas) d'un 'e' - \\br.*\\b : Tous les mots commen�ant par 'b' suivis de
		 * n'importe quelle suite de caract�res -> retourne toute la fin de la phrase - \\br.* : m�me r�sultat - \\br\\w*\\b : retourne tous les mots commen�ant
		 * par 'r' - \\bh\\w*\\b : retourne tous les mots commen�ant par h, mais pas hu�tre parceque � E/ \\w - \\bh\\p{javaLetter}*\\b : retourne tous les mots
		 * commen�ants par 'r' quelque soient les lettres qui les composent. Appel de la m�thode Character.isLetter() Pattern.MULTILINE : permet de sp�cifier
		 * que l'on prend en compte les d�buts et fins de lignes - ^d.*$ : toute ligne commen�ant par 'd' - ^d\\p{javaLetter}*$ : 1er mot de toute ligne
		 * commen�ant par 'd' - \\p{javaLetter}+$ : les derniers mots de chaque ligne. Signifie : suite non nulle de lettre suivie d'une fin de ligne
		 * 
		 * A savoir: - \\b repr�sente un d�but ou une fin de mot - \\w repr�sente n'importe quel caract�re contenu dans un mot anglais (incluant chiffres et
		 * '_', excluant les lettres accentu�es - lorsque flag multiline '^' -> d�but de ligne et '$' -> fin de ligne
		 * 
		 * Source : http://blog.paumard.org/cours/java-api/chap03-expression-regulieres-syntaxe.html
		 */
		Pattern pattern = Pattern.compile("\\p{javaLetter}+$", Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(prevert);

		while (matcher.find()) {
			System.out.println(matcher.group());
		}

		return "";
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

	public void addDefect(String s) throws Exception {

		testcases.get(testcases.size() - 1).addDefect(s);
	}

	public void addTestcase(String s) throws Exception {
		testcases.add(new Testcase(s));

	}

	@Override
	public String toString() {
		String stringReturned = "";

		for (Testcase t : testcases) {
			stringReturned += t.toString() + "\n";
		}
		return stringReturned;
	}
}

/**
 * A testcase item contains a list of key/value and sometimes a defect
 */
class Testcase {

	public static String[] testcaseAttributesNames = { "actualStatus", "classname", "message", "name", "status" };
	private Map<String, String> testcaseAttributesMap;
	private Defect defect;

	public Testcase(String xmlLine) throws Exception {
		testcaseAttributesMap = new HashMap<String, String>(testcaseAttributesNames.length);
		String value = "";
		for (String attribute : testcaseAttributesNames) {
			// Penser au cas o� l'attribut n'existe pas dans la ligne xml
			// Ne pas throw tout le temps des exception -> il faut les g�rer mieux que �a
			value = XmlUtils.getValue(XmlUtils.applyRegex(xmlLine, "\\b" + attribute + "=\"(.*?)\""));
			testcaseAttributesMap.put(attribute, value);
		}
	}

	public void addDefect(String s) throws Exception {
		defect = new Defect(s);
	}

	@Override
	public String toString() {
		String stringReturned = "";

		stringReturned += testcaseAttributesMap.toString();
		if (defect != null) {
			stringReturned += "\n\t" + defect.toString();
		}
		return stringReturned;
	}
}

/**
 *
 */
class Defect {

	public static String[] defectAttributesNames = { "expectedMessage", "link", "status", "type" };
	private Map<String, String> defectAttributesMap;

	public Defect(String xmlLine) throws Exception {
		defectAttributesMap = new HashMap<String, String>(defectAttributesNames.length);
		String value = "";
		for (String attribute : defectAttributesNames) {
			value = XmlUtils.getValue(XmlUtils.applyRegex(xmlLine, "\\b" + attribute + "=\"(.*?)\""));
			defectAttributesMap.put(attribute, value);
		}

	}

	@Override
	public String toString() {
		return defectAttributesMap.toString();
	}
}
