package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import static Resources.base.driver;
import static Resources.base.randomNumber;

public class ExtentManager {
    public static ExtentSparkReporter spark;
    public static ExtentReports extent;
    public static ExtentTest test;

    String Report = "Report_"+Common_Functions.getcurrent_date_time();
    public void Initialize_Extent_Report() {
        extent = new ExtentReports();
        spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/Extent-Report/" +Report+ ".html");
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("Automation Report");
        extent.attachReporter(spark);
        extent.setSystemInfo("Environment", "EUC1");

    }

    public void Pass_ScreenShot(String Description) {
        test.pass(Description, MediaEntityBuilder.createScreenCaptureFromBase64String(getbase64()).build());
    }
    public void Fail_ScreenShot() {

        test.fail(getClass().getSimpleName() +"TestCase Failed", MediaEntityBuilder.createScreenCaptureFromBase64String(getbase64()).build());
    }
    public void Info_ScreenShot(String Description ){
        test.info(Description, MediaEntityBuilder.createScreenCaptureFromBase64String(getbase64()).build());
    }
    public void Extent_Pass(String Description) {
        test.log(Status.PASS, Description);
    }

    public void Extent_Fail_ScreenShot(ITestResult result) {
        test.log(Status.FAIL,"TestCase Failed Step: "+ result.getThrowable(),MediaEntityBuilder.createScreenCaptureFromBase64String(getbase64()).build());
    }

    public void Extent_Fail_Info(String Message) {
        test.log(Status.FAIL,Message);
    }

    public void Extent_Info(String Description) {
        test.log(Status.INFO, Description);
    }

    public String getbase64() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
}
