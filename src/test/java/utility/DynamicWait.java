package utility;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DynamicWait {
	WebDriver dr;
	WebDriverWait wait;
	public DynamicWait(WebDriver dr , int defaultTimeOut) {
		this.dr = dr;
		dr.manage().timeouts().pageLoadTimeout(defaultTimeOut, TimeUnit.SECONDS);
		wait  = new WebDriverWait(dr,defaultTimeOut);
		ReusableComponent.log4jFileAndConsole("creating wait obj");
	}
	
	  public void waitForObjectToEnable(By by,int timeout){
		  ReusableComponent.log4jFileAndConsole("Waiting for object to be enabled" + by.toString());
		  //WebDriverWait waitNew = new WebDriverWait(dr, timeout);
		  int cnt = 0;
		  while(cnt < timeout){
			  System.out.println(cnt);
			  if(getWaitelement(by) != null){
				  break;
			  }
			  cnt =cnt +1;
		  }
		  
	  }
     
     public  void pageLoad(){
    	 try{
    	// WebDriverWait wait1 = new WebDriverWait(dr, 30);
    	 wait.until(new ExpectedCondition<Boolean>() {
    	        public Boolean apply(WebDriver dr) {
    	            return ((JavascriptExecutor) dr).executeScript(
    	                "return document.readyState"
    	            ).equals("complete");
    	        }
     });}
    	 catch(Exception e){
    		 System.out.println("unexpected exception in page load");
    	 }
}
     
     
     public  WebElement getWaitelement(By locator){
     
     		  WebElement element = dr.findElement(locator);
              return (element != null && element.isDisplayed() && element.isEnabled()) ? element : null;
          }
 
     
     public void waitForObjClickable(By by,int timeout){
    	 try{
    	ReusableComponent.log4jFileAndConsole("Waiting for object for it to be clickable "+ by.toString());
    	 WebDriverWait waitn =  new WebDriverWait(dr,timeout);
    	 waitn.until(ExpectedConditions.elementToBeClickable(by));
    	 }catch(Exception e){
    		 ReusableComponent.log4jFileAndConsole("Object not loaded "+ by.toString() + "till timeout " + timeout);
    	 }
     }
     
     public void waitForObjLocated(By by,int timeout){
    	 try{
    	 ReusableComponent.log4jFileAndConsole("Waiting for object for it to be located "+ by.toString());
    	 WebDriverWait waitn =  new WebDriverWait(dr,timeout);
    	 waitn.until(ExpectedConditions.presenceOfElementLocated(by));
    	 }
    	 catch(TimeoutException e){
    		 ReusableComponent.log4jFileAndConsole("Object "+ by.toString() + " not located till timeout " + timeout);
    	 }
     }
     
     public void waitForObjVisible(By by,int timeout){
    	 try{
    	 ReusableComponent.log4jFileAndConsole("Waiting for object for it to be visible "+ by.toString());
    	 WebDriverWait waitn =  new WebDriverWait(dr,timeout);
    	 waitn.until(ExpectedConditions.visibilityOfElementLocated(by));
    	 }
    	 catch(TimeoutException e){
    		 ReusableComponent.log4jFileAndConsole("Object "+ by.toString() + " not visible till timeout " + timeout);
    	 }
     }
     public void waitForObjInvisible(By by,int timeout){
    	 try{
    	 ReusableComponent.log4jFileAndConsole("Waiting for object for it to be visible "+ by.toString());
    	 WebDriverWait waitn =  new WebDriverWait(dr,timeout);
    	 waitn.until(ExpectedConditions.invisibilityOfElementLocated(by));
    	 }
    	 catch(TimeoutException e){
    		 ReusableComponent.log4jFileAndConsole("Object "+ by.toString() + " not invisible till timeout " + timeout);
    	 }
     }
     
     public void waitForObjStale(By by,int timeout){
    	 try{
    	 ReusableComponent.log4jFileAndConsole("Waiting for object for it to be stale "+ by.toString());
    	 WebDriverWait waitn =  new WebDriverWait(dr,timeout);
    	 waitn.until(ExpectedConditions.stalenessOf(ReusableComponent.getElement(dr, by)));
    	 }
    	 catch(TimeoutException e){
    		 ReusableComponent.log4jFileAndConsole("Object "+ by.toString() + " not stale till timeout " + timeout);
    	 }
    	 
     }
     
     public void waitForAlert(int timeout){
    	 try{
    	 WebDriverWait waitn =  new WebDriverWait(dr,timeout);
    	 waitn.until(ExpectedConditions.alertIsPresent());
    	 }catch(Exception e){
    		 ReusableComponent.log4jFileAndConsole("Alert does not appear till timeout of " +timeout+" seconds." );
    	 }
     }
}