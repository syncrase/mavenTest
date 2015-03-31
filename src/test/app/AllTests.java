package app;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import app.net.ConnectionTestTest;

@RunWith(Suite.class)
@SuiteClasses({ AllTestsSecondCat.class, AllTestsFirstCat.class, ConnectionTestTest.class })
public class AllTests {

}
