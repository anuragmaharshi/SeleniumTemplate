package utility;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;




import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jCode {

private static Logger _logger;
private static final String fileName = "defaultlog";
private static final String dateAndTimeFormat = "MM-dd-yyyy_hh.mm.ss";
private static final String logProperttFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\log4j.properties";
private static final String folderPath =System.getProperty("logFolderPath") ;// for same directory use "targets/logs"

static {
    /**
     * This is the static block which appends the log file name with the
     * timestamp to make it unique
     */
    try {
    	Date date = new Date();
    	DateFormat dateFormat = new SimpleDateFormat(dateAndTimeFormat);
        //String dateTime = DateAndTime.getFormattedCurrentDateAndTime(dateAndTimeFormat);
        String dateTime =dateFormat.format(date);
        String FileName = fileName + "-" + dateTime + ".log";
        System.out.println(FileName);
    	if(System.getProperty("logFolderPath")!=null)
    		System.setProperty("filename4j",folderPath + FileName);	
    	else
    		System.setProperty("filename4j",System.getProperty("user.dir") + "\\test-output\\"+FileName);
        File file = new File(System.getProperty("filename4j"));
        if (file.createNewFile()) {
        
        	System.out.println(System.getProperty("filename4j"));
            PropertyConfigurator.configure(logProperttFilePath);

        }
    } catch (IOException ex) {
        ex.printStackTrace();
        System.out.print("IO Exception in static method of Logger Class. "
                + ex.getMessage());
        System.exit(-1);
    }

}

/**
 * This method creates instance of the Logger class coming from log4j jar by
 * implementing a singelton
 * 
 * @return _logger - new instance if no instance exist else an existing
 *         instance if the method is invoked previously
 */
public static Logger createLogger() {
    if (_logger == null) {
        _logger = LogManager.getLogger(Log4jCode.class);
        return _logger;
    } else
        return _logger;
}
}
