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

	public static void main(String[] args) {

		Testcases xmlFileToObject = new Testcases();;
		String path = "test-defects.xml";
		File xmlFile = new File(path);

		try {

			Iterator<String> it = Files.lines(xmlFile.toPath(), Charsets.UTF_8).iterator();
			String s = "";
			while (it.hasNext()) {
				s = it.next();
				// System.out.println(s);

				if (s.contains("testcases")) {
					xmlFileToObject = new Testcases();
					continue;
				} else if (s.contains("testcase")) {
					xmlFileToObject.addTestcase(s);
		
					continue;
				} else if (s.contains("defect")) {
					// Get last testcase added and set in it the defect
					continue;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			String firstattr = getValue(applyRegex("<testcase actualStatus=\"failed\" classname=\"test.sap.sl.sdk.authoring.IssueWatcherTest\" "
					+ "message=\"Error\" name=\"failingTest3_ClassDefect\" status=\"passed\">", "classname=\"(.*?)\""));

		} catch (Exception e) {
			e.printStackTrace();
		}
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
		System.out.println("Tested string : " + stringToParse);
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
				+ "un raton laveur\n" + "\n" + "une douzaine d'huîtres un citron un pain\n" + "un rayon de soleil\n" + "une lame de fond\n" + "six musiciens\n"
				+ "une porte avec son paillasson\n" + "un monsieur décoré de la légion d'honneur\n" + "\n" + "un autre raton laveur";

		/*
		 * 
		 * Les patterns : - un : signifie que je cherche tous les *un* dans la chaîne - \\bun\\b : signifie que je cherches exactement 'un' dans ma chaîne -
		 * (\\bun\\b)|(\\bune\\b) : soit 'un' soit 'une' - \\bune?\\b : 'un' suivie (ou pas) d'un 'e' - \\br.*\\b : Tous les mots commençant par 'b' suivis de
		 * n'importe quelle suite de caractères -> retourne toute la fin de la phrase - \\br.* : même résultat - \\br\\w*\\b : retourne tous les mots commençant
		 * par 'r' - \\bh\\w*\\b : retourne tous les mots commençant par h, mais pas huître parceque î E/ \\w - \\bh\\p{javaLetter}*\\b : retourne tous les mots
		 * commençants par 'r' quelque soient les lettres qui les composent. Appel de la méthode Character.isLetter() Pattern.MULTILINE : permet de spécifier
		 * que l'on prend en compte les débuts et fins de lignes - ^d.*$ : toute ligne commençant par 'd' - ^d\\p{javaLetter}*$ : 1er mot de toute ligne
		 * commençant par 'd' - \\p{javaLetter}+$ : les derniers mots de chaque ligne. Signifie : suite non nulle de lettre suivie d'une fin de ligne
		 * 
		 * A savoir: - \\b représente un début ou une fin de mot - \\w représente n'importe quel caractère contenu dans un mot anglais (incluant chiffres et
		 * '_', excluant les lettres accentuées - lorsque flag multiline '^' -> début de ligne et '$' -> fin de ligne
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

	public void addTestcase(String s) throws Exception {
		testcases.add(new Testcase(s));
		
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
		String value ="";
		for (String attribute : testcaseAttributesNames) {
			//Penser au cas où l'attribut n'existe pas dans la ligne xml
			// Ne pas throw tout le temps des exception -> il faut les gérer mieux que ça
			value = XmlUtils.getValue(XmlUtils.applyRegex(xmlLine, attribute+"=\"(.*?)\""));
			testcaseAttributesMap.put(attribute, value);
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
