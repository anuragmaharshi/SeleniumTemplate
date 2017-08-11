package utility;


import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.Reporter;

public class ReusableComponent {
	
	public static boolean isElementPresent(WebDriver dr1,By by) {
	    boolean isPresent = true;
	   
	    //search for elements and check if list is empty
	    if (dr1.findElements(by).isEmpty()) {
	        isPresent = false;
	    }

	   return isPresent;
	}

	public static WebElement getElement(WebDriver dr1,By by){
		boolean checkFlag = false;
		WebElement wb ;
		checkFlag = isElementPresent(dr1,by);
		if(checkFlag){
			 wb = dr1.findElement(by);
		}
		else{
			log4jFileAndConsole("Element not found at runtime "+by.toString());
			wb = null;
		}
		return wb;
	}
// this function is overloaded. It has 2 variants 
// strTextBoxLogicalName gives the name of text box 

public static boolean setValueInTextBox(WebDriver dr1,By by,String strValue,String strTextBoxLogicalName){
	
	boolean resultOfOpeartion = false;
	WebElement wbTextBox = getElement(dr1,by);
	if(wbTextBox != null ){
		if(wbTextBox.isEnabled()){
			logBothTestNgAndLog4j("Setting value in text box "+ strTextBoxLogicalName);
			wbTextBox.clear();
			wbTextBox.sendKeys(strValue,Keys.TAB);
			resultOfOpeartion =  true;
		}else{
			log4jFileAndConsole(strTextBoxLogicalName + " is not enabled");
		}
	}
	else{
		logBothTestNgAndLog4j(strTextBoxLogicalName + " does not exist in the application");
	}
	return resultOfOpeartion;
}


public static boolean setValueInTextBox(WebDriver dr1,WebElement wbTextBox,String strValue,String strTextBoxLogicalName){
	
	boolean resultOfOpeartion = false;
	if(wbTextBox != null ){
		if(wbTextBox.isEnabled()){
			logBothTestNgAndLog4j("Setting value in text box "+ strTextBoxLogicalName);
			wbTextBox.clear();
			wbTextBox.sendKeys(strValue,Keys.TAB);
			resultOfOpeartion =  true;
		}else{
			log4jFileAndConsole(strTextBoxLogicalName + " is not enabled");
		}
	}
	else{
		logBothTestNgAndLog4j(strTextBoxLogicalName + " does not exist in the application");
	}
	return resultOfOpeartion;
}

public static boolean setValueInTextBox(WebDriver dr1,WebElement wbTextBox,String strValue){
	
	boolean resultOfOpeartion = false;
	if(wbTextBox != null ){
		if(wbTextBox.isEnabled()){
			logBothTestNgAndLog4j("Setting value in text box ");
			wbTextBox.clear();
			wbTextBox.sendKeys(strValue,Keys.TAB);
			resultOfOpeartion =  true;
		}else{
			log4jFileAndConsole("Object is not enabled");
		}
	}
	else{
		logBothTestNgAndLog4j("Object does not exist in the application");
	}
	return resultOfOpeartion;
}
public static boolean setValueInTextBox(WebDriver dr1,By by,String strValue){
	
	boolean resultOfOpeartion = false;
	WebElement wbTextBox = getElement(dr1,by);
	if(wbTextBox != null ){
		if(wbTextBox.isEnabled()){
			logBothTestNgAndLog4j("Setting value in text box ");
			wbTextBox.clear();
			wbTextBox.sendKeys(strValue,Keys.TAB);
			resultOfOpeartion =  true;
		}else{
			log4jFileAndConsole("Textbox is not enabled");
		}
	}
	else{
		logBothTestNgAndLog4j("Textbox does not exist in the application");
	}
	return resultOfOpeartion;
}

public static boolean setValueInComboTextBox(WebDriver dr1,By by,String strValue){
	
	boolean resultOfOpeartion = false;
	WebElement wbTextBox = getElement(dr1,by);
	if(wbTextBox != null ){
		if(wbTextBox.isEnabled()){
			logBothTestNgAndLog4j("Setting value in text box ");
			wbTextBox.sendKeys(strValue,Keys.TAB);
			resultOfOpeartion =  true;
		}else{
			log4jFileAndConsole("Textbox is not enabled");
		}
	}
	else{
		logBothTestNgAndLog4j("Textbox does not exist in the application");
	}
	return resultOfOpeartion;
}
 public static void clickOnObject(WebDriver dr1,By by,String strValue){
	 
	 WebElement wbObj = getElement(dr1,by);
	 if(wbObj != null ){
		 if(wbObj.isEnabled()){
			 logBothTestNgAndLog4j("Clicking on Object "+ strValue);
			 wbObj.click();
		 }else{
			 log4jFileAndConsole(strValue + " is not enabled");
		 }
	 }
	 else{
		 logBothTestNgAndLog4j(strValue + " does not exist. So unable to click on it");
	 }
		 
	 
 }
 public static List<WebElement> getAllElements(WebDriver dr1,By by){
	boolean checkFlag = isElementPresent(dr1,by);
	List<WebElement> allElements = new ArrayList<WebElement>();
		if(checkFlag){
			allElements = dr1.findElements(by);
		}
		else{
	
		} 
	return allElements;
 }
 public static void testNglog(String messageToLog){
		//Reporter.log("\n");
		Reporter.log("<br>" + messageToLog + "<br>",true);
	}
	public static void log4jFileAndConsole(String messageToLog){
		Logger log4j =Log4jCode.createLogger();
		log4j.info(messageToLog);
	}
	public static void logBothTestNgAndLog4j(String messageToLog){
		testNglog(messageToLog);
		log4jFileAndConsole(messageToLog);
	}
public static String getText(WebDriver dr1,By by){
	String text="";
	WebElement element = getElement(dr1,by);
	if(element != null ){
		text = element.getText();
	}
	else{
		log4jFileAndConsole("Webelement not found at run time.");
	}
	return text;
}
public static String getAttribute(WebDriver dr1,By by,String attribute){
	String text="";
	try{
	WebElement element = getElement(dr1,by);
	if(element != null ){
		text = element.getAttribute(attribute);
	}
	}catch(Exception e){
		log4jFileAndConsole("Unable to get attribute "+attribute);
	}
	
	return text;
}
public static String getAttribute(WebElement element,String attribute){
	String text="";
	try{
	if(element != null ){
		text = element.getAttribute(attribute);
	}
	}catch(Exception e){
		log4jFileAndConsole("Unable to get attribute "+attribute);
	}
	
	return text;
}

public static boolean checkWebElement(WebElement element){
	boolean checkFlag = false;
	if(element!= null){
		if(element.isDisplayed() && element.isEnabled()){
			checkFlag = true;
		}		
	}
	return checkFlag;
}
public static boolean checkWebElement(WebDriver dr, By by){
	boolean checkFlag = false;
	WebElement element = getElement(dr,by);
	if(element!= null){
		if(element.isDisplayed() && element.isEnabled()){
			
			Point point = element.getLocation();
			if(point.x == 0 || point.y == 0){
				log4jFileAndConsole(by.toString() + " exists but is not displayed in application.");
			}
			else{
				checkFlag = true;
			}
		}		
	}
	return checkFlag;
}
public static String  getDateString(){
	Date date = new Date();
    final String dateAndTimeFormat = "MM-dd-yyyy_hh-mm-ss";
	DateFormat dateFormat = new SimpleDateFormat(dateAndTimeFormat);
    //String dateTime = DateAndTime.getFormattedCurrentDateAndTime(dateAndTimeFormat);
    String dateTime =dateFormat.format(date);
    return dateTime;
}
public static String captureScreen(WebDriver driver) {
	
	String folderPath = System.getProperty("ScreenShots");
	String path = folderPath + getCallingMethodName() + "_" +getDateString() +".jpg";
    try {
        //WebDriver augmentedDriver = new Augmenter().augment(driver);
        File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        
        FileUtils.copyFile(source, new File(path)); 
    }
    catch(Exception e) {
      
        BufferedImage image;
		try {
			image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(image, "png", new File(path));
		}catch(Exception ex){
			path = "Failed to capture screenshot: " + e.getMessage();
		}
        
        
        log4jFileAndConsole(path);   
    }
    return path;
}
public static String getCallingMethodName(){
	String callingMethodName="";
	try{
	  final StackTraceElement[] ste = new Throwable().getStackTrace();
	  callingMethodName = ste[2].getMethodName();
	}
	catch(Exception e){
		System.out.println("Unable to get calling method name");
	}
	return callingMethodName;
}

public static String getTextFromCollection(WebDriver dr1,By by){
	List<WebElement> elements = dr1.findElements(by);
	String text = "";
	for(WebElement ele : elements){
		text = text + " " + ele.getText();
	}	
	return text;
}
}
