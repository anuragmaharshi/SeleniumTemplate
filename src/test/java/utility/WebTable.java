package utility;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebTable {
	WebDriver dr;
	By by;
	WebElement wt;
	public WebTable(WebDriver dr,By by){
		this.dr = dr;
		this.by = by;
		wt = ReusableComponent.getElement(dr,by);
		System.out.println("Printing class of object"+wt.getAttribute("Class"));

		//dr.findElement(by);
	}
	public int getRow(){
		//WebElement wt =  reusableComponent.getElement(dr,by);
		if(wt != null){
			List<WebElement> rows = wt.findElements(By.tagName("tr"));
			System.out.println("No of rows in web table are :" + rows.size());
			return rows.size();
		}
		else{
			System.out.println("Webtable does not exist");
			return -1;
		}		
	}
	public int getColumnInRow(int rowNo){
		if(wt != null){
		 List<WebElement> rows = wt.findElements(By.tagName("tr"));	
		 //int rowNo = 2;	
		 int rowCount = getRow();
		 if(rowNo >= rowCount){
			 System.out.println("Invalid row no."+rowNo+". Total no of rows in webtable is " +rowCount );
			 return -1;
		 }
		 else{
		 List<WebElement> columns = rows.get(rowNo).findElements(By.tagName("td"));
		 System.out.println("No of columns =" + columns.size());
		 return columns.size();
		 }
		}
		else{
			System.out.println("Webtable does not exist");
			return -1;
		}
	}
public void printWebTable(){
	
	List<WebElement> rows = wt.findElements(By.tagName("tr"));	
	//iterating for each row in webatble
	for(int i =0;i<rows.size();i++){
		//accessing each columns of the row
		System.out.println("*******************************************************");
		List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));
		for(int j =0;j<columns.size();j++){
			//getting text of each cell
			String cellText = columns.get(j).getText();
			//getting tag of each cell. It is usually td. 
			//need to add code to return subclass of td ie link, image etc
			String cellType = columns.get(j).getTagName();
			System.out.println("The value for cell with row no "+ i + " and column no "+ j + " is "+ cellText+" and the cell object type is "+cellType);
		}
		System.out.println("##############################################################");
	}
}

public String[] getRowInArray(int columnNo){
	String[] arrayItems;
	List<WebElement> rows = wt.findElements(By.tagName("tr"));

	arrayItems = new String[rows.size()];
	//iterating for each row in webatble
	for(int i =0;i<rows.size();i++){
		//accessing each columns of the row
		List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));
		if(columnNo >= columns.size()){
			System.out.println("Column no is invalid. Column no referred is " + columnNo + " while total column size is " + columns.size());
			arrayItems[i] = null;
			//break;
		}
		else{
		//getting text of each cell
		String cellText = columns.get(columnNo).getText();
		//System.out.println("The value for cell with row no "+ i + cellText);
		arrayItems[i] = cellText;
		}
	}
	return	arrayItems;
}
public String[] getColumnInArray(int rowNo){
	String[] arrayItems;
	List<WebElement> rows = wt.findElements(By.tagName("tr"));
	if(rowNo >= rows.size()){
		System.out.println("Row no is invalid. Row no referred is " + rowNo + " while total column size is " + rows.size());
		arrayItems = new String[0];
		return arrayItems;
	}
	else{
		//accessing each columns of the row
		List<WebElement> columns = rows.get(rowNo).findElements(By.tagName("td"));
		arrayItems = new String[columns.size()];
		//getting text of each cell	
		for(int j =0;j<columns.size();j++){
			//getting text of each cell
			String cellText = columns.get(j).getText();
			arrayItems[j] = cellText;	
			
		}
	}
	return	arrayItems;	
}

public void clickOnCell(int rowNo,int columnNo){
	System.out.println("Clicking on cell with row "+ rowNo + " and column no " + columnNo );
	List<WebElement> rows = wt.findElements(By.tagName("tr"));
	if(rowNo >= rows.size()){
		System.out.println("Row no is invalid. Row no referred is " + rowNo + " while total column size is " + rows.size());
	}
	else{
		List<WebElement> columns = rows.get(rowNo).findElements(By.tagName("td"));
		if(columnNo >= columns.size()){
			System.out.println("Column no is invalid. Column no referred is " + columnNo + " while total column size is " + columns.size());
		}
		else{
			System.out.println("Attempting to click on cell with row and column no");
			columns.get(columnNo).click();
		}
	}
}


public void clickOnCell(int rowNo,int columnNo,String tagName){
	System.out.println("Clicking on cell with row "+ rowNo + " and column no " + columnNo );
	List<WebElement> rows = wt.findElements(By.tagName("tr"));
	if(rowNo >= rows.size()){
		System.out.println("Row no is invalid. Row no referred is " + rowNo + " while total row size is " + rows.size());
	}
	else{
		List<WebElement> columns = rows.get(rowNo).findElements(By.tagName("td"));
		if(columnNo >= columns.size()){
			System.out.println("Column no is invalid. Column no referred is " + columnNo + " while total column size is " + columns.size());
		}
		else{
			System.out.println("Attempting to click on cell with row and column no with tag name");
			columns.get(columnNo).findElement(By.tagName(tagName)).click();
		}
	}
}

public int getRowNoForObject(int colNum,String objValue){
	String[] allRows = getRowInArray(colNum);
	for(int i =0;i<allRows.length;i++){
		
		if(allRows[i] != null && allRows[i].equals(objValue)){
			return i;
		}
	}
	return -1;
}

public void clickOnCell(String objValue,int columnNo,String tagName){
	System.out.println("Clicking on cell with value "+ objValue + " and for column no " + columnNo );
	int rowNum = getRowNoForObject(columnNo,objValue);
	
	if(rowNum != -1){
		System.out.println(objValue + " exists in table. Clicking on it");
		clickOnCell(rowNum,columnNo,tagName);
	}
	else{
		System.out.println(objValue + " does not exists in web table. So unable to click on that");
	}
}

public void clickOnCell(String objValue,int columnNo){
	System.out.println("Clicking on cell with value "+ objValue + " and for column no " + columnNo );
	int rowNum = getRowNoForObject(columnNo,objValue);
	
	if(rowNum != -1){
		System.out.println(objValue + " exists in table. Clicking on it");
		clickOnCell(rowNum,columnNo);
	}
	else{
		System.out.println(objValue + " does not exists in web table. So unable to click on that");
	}
}

}
