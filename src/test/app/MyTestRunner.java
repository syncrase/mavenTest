package app;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MyTestRunner {

	public static void main(String[] args) {

		Result result = JUnitCore.runClasses(AllTestsSecondCat.class);
	    for (Failure failure : result.getFailures()) {
	      System.out.println(failure.toString());
	    }
	    System.out.println(result.getFailureCount());
	    System.out.println(result.getIgnoreCount());
	    System.out.println(result.getRunCount());
	    System.out.println(result.getRunTime());
	}

}
