package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ComboBoxUtility {
	
	// when we write below code then we need to create a new object of this class
	// Now i am creating a static class for handling without creating object
	/*
	WebDriver dr;
	By by;
	 Select st = new Select(reusableComponent.getElement(dr, by));
	
	//constructor for initialising dropdown
	public dropDown(WebDriver dr, By by){
		this.dr = dr;
		this.by = by;
	}
	*/
	public static void selectByValue(WebDriver dr, By by,String value,String strDropDownLOgicalName){
		System.out.println(" Selecting value in Dropdown "+ strDropDownLOgicalName);
		WebElement dropDownObj =  ReusableComponent.getElement(dr, by);
		if(dropDownObj != null){
			System.out.println("The desired dropdown object exists and so selcting value in it");
			Select st = new Select(dropDownObj);
			st.selectByValue(value);
		}
		else{
			System.out.println("The desired dropdown object "+ strDropDownLOgicalName +" does not exist. Unable to select value in the dropdown");
		}
			
		
	}
	
	public static void selectByValue(WebDriver dr, By by,String value){
		System.out.println(" Selecting value in Dropdown ");
		WebElement dropDownObj =  ReusableComponent.getElement(dr, by);
		if(dropDownObj != null){
			System.out.println("The desired dropdown object exists and so selcting value in it");
			Select st = new Select(dropDownObj);
			st.selectByValue(value);
		}
		else{
			System.out.println("The desired dropdown object does not exist. Unable to select value in the dropdown");
		}
			
		
	}	
	public static void selectByVisibleText(WebDriver dr, By by,String value,String strDropDownLOgicalName){
		
		System.out.println(" Selecting value in Dropdown "+ strDropDownLOgicalName);
		WebElement dropDownObj =  ReusableComponent.getElement(dr, by);
		if(dropDownObj != null){
			System.out.println("The desired dropdown object exists and so selcting value in it");
			Select st = new Select(dropDownObj);
			st.selectByVisibleText(value);
		}
		else{
			System.out.println("The desired dropdown object "+ strDropDownLOgicalName +" does not exist. Unable to select value in the dropdown");
		}
		

	}
	
	public static void selectByVisibleText(WebDriver dr, By by,String value){
		
		System.out.println(" Selecting value in Dropdown ");
		WebElement dropDownObj =  ReusableComponent.getElement(dr, by);
		if(dropDownObj != null){
			System.out.println("The desired dropdown object exists and so selcting value in it");
			Select st = new Select(dropDownObj);
			st.selectByVisibleText(value);
		}
		else{
			System.out.println("The desired dropdown object does not exist. Unable to select value in the dropdown");
		}
		

	}
}
