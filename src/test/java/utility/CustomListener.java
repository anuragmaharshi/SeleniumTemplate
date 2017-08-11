package utility;

import java.util.List;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


/*
 * This Class extends Class TestListenerAdapter. Same thing can be implemented by using interface ITestListener.
 * This interface have following methods
 * onFinish-- run after all the tests
 * onStart -- run before all the tests
 * onTestFailedButWithinSuccessPercentage -- pass with some percent
 */
public class CustomListener extends TestListenerAdapter {

	@Override
	public List<ITestResult> getFailedTests() {
		// TODO Auto-generated method stub
		return super.getFailedTests();
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		ITestNGMethod method = tr.getMethod();
		ReusableComponent.logBothTestNgAndLog4j("For testname "+ tr.getName() + ","+method.getDescription() + " is Fail");
		ReusableComponent.log4jFileAndConsole("____________________________________________________________________________");
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		ITestNGMethod method = tr.getMethod();
		ReusableComponent.logBothTestNgAndLog4j("For testname "+ tr.getName() + ","+method.getDescription() + " did not run");
		ReusableComponent.log4jFileAndConsole("************************************************************************");

	}

	@Override
	public void onTestStart(ITestResult result) {
		ReusableComponent.log4jFileAndConsole("-------------------------------------------------------------------------");
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		
		ITestNGMethod method = tr.getMethod();
		ReusableComponent.logBothTestNgAndLog4j("For testname "+ tr.getName() + ","+method.getDescription() + " is Pass");
		ReusableComponent.log4jFileAndConsole("************************************************************************");
		
	}

}
