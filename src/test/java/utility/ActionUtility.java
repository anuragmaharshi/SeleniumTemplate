package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionUtility {
	WebDriver wd;
	public ActionUtility(WebDriver wd){
		this.wd = wd;
	}
	public void hover1(By mainItem, By subItem){
		Actions action = new Actions(wd);
		WebElement hover = ReusableComponent.getElement(wd,mainItem);
		action.moveToElement(hover);
		
		WebElement subElement = ReusableComponent.getElement(wd,subItem);
		action.moveToElement(subElement);
		action.click().build().perform();
	}
	public void hover2(By mainItem, By subItem){
		Actions action = new Actions(wd);
		WebElement hover = ReusableComponent.getElement(wd,mainItem);
		action.moveToElement(hover);
		action.moveToElement(hover).moveToElement(wd.findElement(subItem)).click().build().perform();
	}
	
	public void hover3(By mainItem, By subItem){
		Actions action = new Actions(wd);
		WebElement hover = ReusableComponent.getElement(wd,mainItem);
		action.moveToElement(hover).build().perform();
		ReusableComponent.clickOnObject(wd, subItem, "Hover");
	}
	
}
