package Resources;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;




public class Log {

    private static Logger Log = Logger.getLogger(Log.class.getName());

    public static void startTestCase(String TestCaseName){
        DOMConfigurator.configure(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\Log4j.xml");
        Log.info("Test Case "+TestCaseName+" started");
    }

    public static void info(String message) {
        DOMConfigurator.configure(System.getProperty("user.dir")+"\\src\\main\\java\\Log4j.xml");
        Log.info(message);

    }

}