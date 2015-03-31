package app.printing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;

import app.FirstCat;
import app.SecondCat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InFileTest {

	@Before
	public void testInit() {
		System.out.println("------------------------------TestInit------------------------------");
	}

	@After
	public void testEnding() {
		// Exécution du dernier before en premier
		System.out.println("--------------------------------------------------------------------");
	}

	@BeforeClass
	public static void testClasseInit() {
		// Must be static and public
		System.out.println("******************************Test '" + InFileTest.class + "' beginning******************************");
	}

	@AfterClass
	public static void testClasseEnding() {
		System.out.println("******************************* END OF THE TEST CLASS *******************************************");
	}

	
	@Test
	@Category(FirstCat.class)
	public void testDivisionByZero() {
		System.out.println("testDivisionByZero");
		try {
			int n = 2 / 0;
			fail("Divisione per zero.");
		} catch (ArithmeticException success) {
			assertNotNull(success.getMessage());
			assertTrue("L'exception n'est pas celle attendue", success.getMessage().equals("/ by zero"));
		}
	}

	@Test(expected = ArithmeticException.class)
	@Category(SecondCat.class)
	public void betterTestDivisionByZero() {
		System.out.println("betterTestDivisionByZero");
		int n = 2 / 0;
	}

	@Test
	@Category(FirstCat.class)
	public void testWriteString() {
		System.out.println("testWriteString");
		// fail("Not yet implemented");
		assertEquals(10, 10);
	}

	@Test(timeout = 500)
	public void testWait() {
		System.out.println("testWait");
		try {
			// It works until this execution time does not exceed 500ms, eg 500 works, 501 none
			Thread.sleep(490);
			// if sleep time is 501. The InterruptedException isn't raised
			// test failed but no exception raised
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

	@Ignore
	@Test
	public void method() {
		fail("Not yet implemented");
	}

}
