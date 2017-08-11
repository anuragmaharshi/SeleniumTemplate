package testNGSuite;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class InitTest {
  @Test
  public void f() {
	  System.out.println("Test 1");
  }
  @BeforeSuite
  public void beforeSuite() {
	  //init logger
	  utility.ReusableComponent.log4jFileAndConsole("hello");
  }

  @AfterSuite
  public void afterSuite() {
  }

}
