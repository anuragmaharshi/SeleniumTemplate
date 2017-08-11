package utility;

import java.io.File;
import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelReader {

	String excelFilePath = null;
	FileInputStream inputStream=null;
	Workbook workbook = null;
	Sheet newSheet =  null;
	//Constructor with sheet name
	public ExcelReader(String excelFilePath,String strSheetName){
		this.excelFilePath = excelFilePath;
		try {
			inputStream = new FileInputStream(new File(excelFilePath));
		    workbook = new XSSFWorkbook(inputStream);   
		    int index= workbook.getSheetIndex(strSheetName);
		    if(index==-1)
		    	throw new Exception("Sheet name "+ strSheetName + " not found in workbook") ;
		    
		    newSheet = workbook.getSheetAt(index);
			 } 
		catch (Exception e){
				System.out.println("unable to create excel object"); 
				System.out.println(e.getMessage());
			}	
	}
	//Constructor with sheet no. Sheet no starts with 0.
	public ExcelReader(String excelFilePath,int intSheetNum){
		this.excelFilePath = excelFilePath;
		try {
			inputStream = new FileInputStream(new File(excelFilePath));
		    workbook = new XSSFWorkbook(inputStream);
		    newSheet = workbook.getSheetAt(intSheetNum);
	    }catch (Exception e) {
		  System.out.println("Unable to create excel object");
		  System.out.println(e.getMessage());
	    }	
	}

	//print excel in console
	public void printExcelData(){
		try{
	    Iterator<Row> iterator = newSheet.iterator();
	     
	    while (iterator.hasNext()) {
	        Row nextRow = iterator.next();
	        Iterator<Cell> cellIterator = nextRow.cellIterator();
	         
	        while (cellIterator.hasNext()) {
	            Cell cell = cellIterator.next();
	            switch (cell.getCellType()) {
	                case Cell.CELL_TYPE_STRING:
	                    System.out.print(cell.getStringCellValue());
	                    break;
	                case Cell.CELL_TYPE_BOOLEAN:
	                    System.out.print(cell.getBooleanCellValue());
	                    break;
	                case Cell.CELL_TYPE_NUMERIC:
	                	boolean bool = isDate(cell);
	                	if(bool)
	                		System.out.println(cell.getDateCellValue());
	                	else
	                		System.out.print(cell.getNumericCellValue());
	                    break;
	                
	            }
	            System.out.print(" - ");
	        }
	        System.out.println();
	    }
	    inputStream.close();
		}
		catch(Exception e){
			System.out.println("Unable to print all element of excel.");
		}   
	}

	//getting single row data 
	public List<String> getRowData(int intRowNum){
		//Creating empty list
		List<String> rowData = new ArrayList<String>();
		if(newSheet==null)
			return rowData;
		try{ 
			 if(intRowNum > newSheet.getLastRowNum()){
				 return rowData;
			 }
			 for(int i = 0;i<newSheet.getRow(intRowNum).getLastCellNum();i++){
				 rowData.add(getCellData(intRowNum,i));
			 }	 
			 return rowData;
		}catch(Exception e){
			return rowData;
		}
	}

	//must call this function when disposing the object to close the stream
	public void closeAll(){
		try {
			inputStream.close();
		} catch (Exception e) {
			System.out.println("File stream not opened so unable to close it");
		}
		}

	//get all columns data
	public List<String> getColumnData(int intColumnNo){
		List<String> columnData = new ArrayList<String>();
		try{	
			if(newSheet==null || intColumnNo<0)
				return columnData;
		  //returning if column no entered is greater than last column no
			 if(intColumnNo >= newSheet.getRow(0).getLastCellNum()){
				 return columnData;
			 }

			 for(int i = 0;i<newSheet.getLastRowNum();i++){
				 columnData.add(getCellData(i,intColumnNo));		 
			 }
			 return columnData;	 
		}catch(Exception e){
			return columnData;
		}
	}

	public String getCellData(int intRowNum,int intColNum){
		String strData = "";
		if(newSheet == null)
			return strData;
		try{
			Cell data = newSheet.getRow(intRowNum).getCell(intColNum);
			 switch (data.getCellType()) {
	         case Cell.CELL_TYPE_STRING:
	        	 strData = data.getStringCellValue();
	        	 break;
	         case Cell.CELL_TYPE_BOOLEAN:
	        	 Boolean value = data.getBooleanCellValue();
	        	 strData = value.toString();
	        	 break;
	         case Cell.CELL_TYPE_NUMERIC:
	        	 boolean bool = isDate(data);//checking if it is date 
             	 if(bool){
             		 Date date = data.getDateCellValue();
             		 strData = date.toString();
             	 }else{
             		Double doubleValue=data.getNumericCellValue();
   	        	    strData =  doubleValue.toString();     
             	 }
	        	 break;
			 }	
		return strData;
		}catch (Exception e) {
			return strData;
		}
}

	//return row number by searching in 
	public int getRowNumForData(String strColumnValue,int columnNo){	
		if(newSheet == null )
			return -1;
		int row =-1;
		try{
			//column no search is greater than last column, return -1
			if(columnNo > newSheet.getRow(0).getLastCellNum())
				return -1;
			
			for(int i = 0;i<newSheet.getLastRowNum();i++){
				 if(strColumnValue.equalsIgnoreCase(getCellData(i,columnNo))){
					row = i;
					break;
				 }
			}
			return row;
		}catch(Exception e){
			return row;
		}	
	}
	
	//scenario for special case where first column contains test scenario first column start with 0.
	public int getRowNumForData(String strColumnValue){
		return getRowNumForData(strColumnValue,0);
	}

	//can be used for fetching data in Map
	public Map<String,String> getRowDataInMap(int rowNumber){
		Map<String,String> Data = new HashMap<String,String>();
		
		//first column should be header so its value should be 0
		List<String> Header = getRowData(0);
		
		//Getting specified row data in list
		List<String> RowData = getRowData(rowNumber);

		//when header is blank loop will not run. it means there is no data
		//when data is present in header but row data is not present, update empty string.
		if(RowData.size()==0 )// element you want to select in not present
			return Data;
		for(int i =0 ;i<Header.size();i++){
			if(RowData.size()>=i)
				Data.put(Header.get(i),RowData.get(i));
			else
				Data.put(Header.get(i),"");//return empty string if 
		}
		return Data;
	}
	
	//return Map with column no and index.
	public Map<String,String> getRowDataInMap(String strColumnValue,int columnNumber){
		int rowNumber = getRowNumForData(strColumnValue,columnNumber);
		 return getRowDataInMap(rowNumber);
	}
	
	public boolean isDate(Cell cell){
		if(DateUtil.isCellDateFormatted(cell))
		   {
		       return true;
		   }
		else{
			return false;
		}
	}
}
