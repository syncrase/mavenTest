package app;

import app.maths.*;
import app.net.ConnectionTest;

public class Main {

	public static void main(String[] args) {
		
//		System.out.println("Ce programme est un test qui teste les tests.");
		try {
			ConnectionTest.googleConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println("Entre autre");
		
		
		//(int a, int b) -> {  return a + b; }
		 
		//() -> System.out.println("Hello World");
		 
		//(String s) -> { System.out.println(s); }
		 
		//() -> 42
		 
		//() -> { return 3.1415 };
		
		//a -> return a*a
		
		
		
	}

}
