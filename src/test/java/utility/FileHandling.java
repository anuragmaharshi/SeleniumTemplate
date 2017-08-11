package utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.commons.io.FileUtils;

public class FileHandling {

	String fileName ;
	String line = null;
	public FileHandling(String fileName){
		this.fileName = fileName;
	}
	public FileHandling(){
		this.fileName = "";
	}
	public void fileReadBufferReader(){
	try {
		
        // FileReader reads text files in the default encoding.
        FileReader fileReader =  new FileReader(fileName);
        //using buffered reader class
        BufferedReader bufferedReader =  new BufferedReader(fileReader);
       // System.out.println(bufferedReader.markSupported());
        
        while((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }  
        bufferedReader.close();   
     
    }
    catch(Exception ex) {
        System.out.println(
            "Unable to open file '" + 
            fileName + "'");    
       System.out.println(ex.toString());
    }
	}
	public void fileReadFileReader(){
	    
        //directly using file reader object
		// FileReader reads text files in the default encoding.
        FileReader fileReader;
		try {
			fileReader = new FileReader(fileName);
		    char [] buf = new char[10];
		    int readCount = fileReader.read(buf, 1, 2);	
		    System.out.println("characters read " + readCount);
		    fileReader.close();
		    for(char c : buf)
		    	System.out.println(c);
		}
        catch (Exception e) {
			// TODO Auto-generated catch block
		
		}   
	}
	public void fileReadFileInputStream(){
		FileInputStream in = null;
		 try {
			in = new FileInputStream(fileName);
		
		 int c;
		 System.out.println(in.available());
		 while ((c = in.read()) != -1) {
	          System.out.print((char)c);
	         }

		 in.close();
		 }
		 catch (Exception e) {
			}
	}
	
	public void fileReadInputStreamReader() {
		InputStreamReader reader;
		try {
			int character;
			reader = new InputStreamReader(new FileInputStream(fileName)); 
			while ((character = reader.read()) != -1) {
                System.out.print((char) character);
			}
			reader.close();
		}
		catch (Exception e) {
		}
	}
	
	public void fileWriteFileWriter(String stringToAdd){
		try {
			/*
			 * FileWriter class have 2 constructors,
			 * IF we dont pass parameter as true by default it will overwrite existing
			 * content.
			 * 
			 */
			FileWriter fileWriter = new FileWriter(fileName,true);
			//fileWriter.append(stringToAdd);
			fileWriter.write(stringToAdd);
			fileWriter.close();
		} 
		catch (Exception e) {
		}
	}
		public void fileWriteFileWriter(CharSequence intToAdd){
			try {
				/*
				 * FileWriter class have 2 constructors,
				 * IF we dont pass parameter as true by default it will overwrite existing
				 * content.
				 * 
				 */
				FileWriter fileWriter = new FileWriter(fileName,true);
				fileWriter.append(intToAdd);
				//fileWriter.write(intToAdd);
				fileWriter.close();
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
	public void fileWriterBufferedWriter(String strToAdd){
		try {
			FileWriter fileWriter = new FileWriter(fileName,true);
			BufferedWriter bw = new BufferedWriter(fileWriter);
			bw.write(strToAdd);
			bw.close();
		
		} 
		catch (IOException e) {

		}
	}
	public void fileWriteFileOutputStream(String strToAdd){
		FileOutputStream fout = null;
		try {
			//Second parameter should be true to append in existing file
			fout = new FileOutputStream(fileName,true);
			byte[] bout = strToAdd.getBytes();
			fout.write(bout);
			fout.close();
		} 
		catch (Exception e) {

		}
	}
	
	public void FileWriteOutputStreamWriter(String strToAdd){
		OutputStreamWriter os;
		try {
			os = new OutputStreamWriter(new FileOutputStream(fileName,true) );
			os.append(strToAdd);
			os.close();
		} 
		catch (Exception e) {
		}
	}
	
	public void createNewFile(){
		File file = new File(fileName);
		boolean check = file.exists();
		System.out.println("FIle exist = " +check);
		if(!check){
			System.out.println("File Not  exists");
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
		}
		else
			System.out.println("File  exists");
	}

	public static void printAllFiles(){
		File folder = new File(System.getProperty("user.dir") + "\\test-output");
		File[] listOfFiles = folder.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		        System.out.println("File " + listOfFiles[i].getName());
		      } else if (listOfFiles[i].isDirectory()) {
		        System.out.println("Directory " + listOfFiles[i].getName());
		      }
		    }
	}
	
	public static File getLastFolderInDirectory(){
		File folder = new File(System.getProperty("user.dir") + "\\test-output\\Logs");
		if(!folder.exists()){
			folder.mkdir();
		}
		File[] listOfFiles = folder.listFiles();
		File lastFile = null;
		long lastCreated =0;
		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isDirectory()) {
		        if(!(lastCreated>listOfFiles[i].lastModified())){
		        	lastCreated = listOfFiles[i].lastModified();
		        	lastFile = listOfFiles[i];
		        }
		      } 
		    }
		 if(lastFile != null){
			 
		 }
		 else{
			 System.out.println("No folder.So creating new folder with name last log ");
			 String folderPath =System.getProperty("user.dir") + "\\test-output\\Logs\\";
			  lastFile = new File(folderPath+"LastLog");
		      if (!lastFile.exists()) {
		    	  lastFile.mkdir();
		      }
		 }
		return lastFile;
	}

	public static void copyTestNgLog(File destination){
		File folder = new File(System.getProperty("user.dir") + "\\test-output");
		File[] listOfFiles = folder.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  try {
					copyFileUsingApacheCommonsIO(listOfFiles[i],destination);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		      
		      }
	}
	private static void copyFileUsingApacheCommonsIO(File source, File dest) throws IOException {
	    FileUtils.copyFileToDirectory(source, dest, true);
	}
}
  


