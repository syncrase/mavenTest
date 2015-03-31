package app;

import java.io.FileInputStream;
import java.util.Properties;


public class AuthoringTest {

	public static void main(String[] args) throws Exception {

		

		// this will list the current system properties
		// Properties p = System.getProperties();
		// p.list(System.out);

		// set up new properties object
		// from file "myProperties.txt"
		
		// p2.list(System.out);
		// System.out.println("sytem property : "+System.getProperty("test.properties"));
		// System.out.println("local property : "+p2.getProperty("test.properties"));
		// set the system properties
		
		// // display new properties
		 System.getProperties().list(System.out);

		// System.out.println(System.getProperty("key.key"));
		// System.out.println(System.getProperty("issueCheck"));

//		System.out.println(System.getProperty("java.home"));
		
		
		
//		FileInputStream propFile = new FileInputStream("myProperties.txt");
//		Properties p2 = new Properties(System.getProperties());// System.getProperties()
//		p2.load(propFile);
//		System.setProperties(p2);
//		
//		
////		String url = "https://sapjira.wdf.sap.corp/browse/BITBIWEBISL2-1542";
//		
////		JiraWatcher.Issue issue = new JiraWatcher.Issue(url);
//		CWBWatcher.Issue issue = new CWBWatcher.Issue();
	}

	
	
	
	
}
