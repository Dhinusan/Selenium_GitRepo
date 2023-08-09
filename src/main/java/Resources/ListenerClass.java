package Resources;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static Resources.base.driver;

public class ListenerClass implements ITestListener {

    ExtentManager Ex = new ExtentManager();
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        screenshot();
        Ex.Extent_Fail_ScreenShot(iTestResult);

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext context) {

    }


    @Override
    public void onFinish(ITestContext iTestContext) {

    }


    @Override
    public void onTestStart(ITestResult iTestResult) {
        String className = iTestResult.getClass().toString().trim();
        System.out.println("Test Case "+className+ " Has Started");
    }

    @Override
    public void onTestSuccess(ITestResult arg0) {

        Ex.test.pass(MarkupHelper.createLabel("Test Case Passed SuccessFully", ExtentColor.GREEN));
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] screenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    /*public void getScreenshotPath(String testcaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String c = testcaseName;
        DateFormat df = new SimpleDateFormat("yyyy_MMM_ddhh_mm_ss");
        Date d = new Date();
        String time = df.format(d);
        String destination = System.getProperty("user.dir") + "\\reports\\" + testcaseName + "screenshot" + time + ".png";
        FileUtils.copyFile(source, new File(destination));


    }*/

    /*public String getbase64() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }*/

}
