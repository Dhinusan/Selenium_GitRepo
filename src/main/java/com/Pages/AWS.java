package com.redcapPage;

import Resources.Common_Functions;
import Resources.Constant;
import Resources.ExtentManager;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class AWS extends Common_Functions {
    WebDriverWait wait = new WebDriverWait(driver, 20);
    Properties prop = new Properties();
    ExtentManager Ex = new ExtentManager();
    public AWS(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }
    //Catalog_Management_Page

    @FindBy(xpath = "//input[@id='wdc_username']")
    public WebElement AWS_userName;

    @FindBy(xpath = "//input[@id='wdc_password']")
    public WebElement AWS_Password;

    @FindBy(xpath = "//button[@id='wdc_login_button']")
    public WebElement AWS_LoginBtn;

    @FindBy(xpath = "//h4[text()='To log into the AWS Console, select the desired role']")
    public WebElement AWS_Roleheader;

    @FindBy(xpath = "//h1[normalize-space()='AWS Management Console']")
    public WebElement AWS_header;

    @FindBy(xpath = "(//span[text()='S3'])[1]")
    public WebElement S3_bucket;

    @FindBy(xpath = "//input[@placeholder='Find buckets by name']")
    public WebElement S3_bucket_Input;

    @FindBy(xpath = "//span[text()='accessories/']")
    public WebElement AWSAccessories_Icon;

    @FindBy(xpath = "//span[text()='data/']")
    public WebElement AWSData_Icon;

    @FindBy(xpath = "//span[text()='metadata/']/..")
    public WebElement AWSMetaData_Icon;

    @FindBy(xpath = "//span[text()='error/']")
    public WebElement AWSError_Icon;

    @FindBy(xpath = "//span[text()='Upload']/..")
    public WebElement AWS_upload;

    @FindBy(xpath = "//span[normalize-space()='Add files']/..")
    public WebElement Add_Files;

    @FindBy(xpath = "//button[@type='submit']//span[text()='Upload']")
    public WebElement AWS_FileUpload;

    @FindBy(xpath = "//div[text()='Upload succeeded']")
    public WebElement Success_Msg;

    @FindBy(xpath = "//span[text()='Close']/..")
    public WebElement Close_Btn;

    @FindBy(xpath = "//input[@placeholder='Search for services, features, blogs, docs, and more']")
    public WebElement Services_Search;

    @FindBy(linkText = "(//span[text()='Scalable Storage in the Cloud']/..//span)[1]")
    public WebElement S3_Link;

    @FindBy(xpath = "((//table)[2]//tbody//tr//td)[4]//img")
    public WebElement Aws_Role_ArrowBtn;

    @Step("Validate AWS Project Field")
    public void Validate_aws_projectfields(){
        normalwait(2000);
        Assert.assertTrue(AWSMetaData_Icon.isDisplayed());
        Assert.assertTrue(AWSAccessories_Icon.isDisplayed());
        Assert.assertTrue(AWSData_Icon.isDisplayed());
        Assert.assertTrue(AWSError_Icon.isDisplayed());
        Ex.Pass_ScreenShot("Validate AWS Project Field");
        screenshot();
        System.out.println("AWS Objects Meta Data,Data,Error,Accesories Are Displayed AS Expected");
    }


    @FindBy(xpath = "(//table[@role='table']//tr[@class='awsui-table-row'])")
    public List<WebElement> Aws_File_Count;
    public void fetch_file(String filename){
        String File_name;
        int count =Aws_File_Count.size();
        for (int i=1;i<count;i++){
             String Current_File_name=driver.findElement(By.xpath("(//table[@role='table']//tr[@class='awsui-table-row'])["+i+"]//td//span[@class='name object latest object-name']")).getText();
            if (!Current_File_name.equalsIgnoreCase(filename)){
                File_name =  Current_File_name;
            }
        }

    }

    @Step("AWS Login Page")
    public void AWS_Login(String UserName,String Password){
        driver.get(Constant.AwsUrl);
        send_keys(AWS_userName,UserName);
        send_keys(AWS_Password,Password);
        Ele_Click(AWS_LoginBtn);
        System.out.println("Logged Into Aws Console");
        screenshot();
        Ex.Pass_ScreenShot("User Logged Into AWS Console :"  + UserName);
    }

    @Step("Role Selection")
    public void select_role(String Role,String Role1){
        //if(Role1.equalsIgnoreCase("Admin")){
            wait.until(ExpectedConditions.visibilityOf(AWS_Roleheader));
            int count ;
            driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
            int count_1 =driver.findElements(By.xpath("//div[text()='"+Role+"']")).size();
            driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        long starttime = System.currentTimeMillis();
        long waittime = 120000;
        long endtime = starttime + waittime;
            if (count_1==0) {
                do {
                    ActionClick(Aws_Role_ArrowBtn);
                    normalwait(1000);
                    count = driver.findElements(By.xpath("//div[text()='" + Role + "']")).size();
                }
                while (count == 0 && System.currentTimeMillis() < endtime  );
            }
            WebElement User_Role =driver.findElement(By.xpath("//div[text()='"+Role+"']"));
            Ele_Click(User_Role);
            screenshot();
            Ex.Extent_Info("AWS User Role Is: "+Role);
        //}

    }

    public void navigate_to_S3Bucket()throws InterruptedException, AWTException{
        page_loader();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='S3']")));
        //jsclick(driver.findElement(By.xpath("//a[text()='S3']")));
        driver.navigate().to(Constant.S3_url);
        wait.until(ExpectedConditions.elementToBeClickable(S3_bucket_Input));
    }

    public void navigate_to_S3Bucket_url()throws InterruptedException, AWTException{
        page_loader();
        driver.navigate().to(Constant.S3_url);
        wait.until(ExpectedConditions.elementToBeClickable(S3_bucket_Input));
    }

    @Step("Bucket Selection")
    public void S3_Input(String BucketName){
        send_keys(S3_bucket_Input,BucketName);
       WebElement Bucket= driver.findElement(By.xpath("//a[text()='"+BucketName+"']"));
       jsclick(Bucket);
       screenshot();
       Ex.Extent_Info("User Logged InTo Bucket: "+BucketName);
    }

    public void navigate_To_data(){
        page_loader();
        jsclick(AWSData_Icon);
    }

    @Step("Upload AWS Data Files")
    public void upload_data_files(String Files, String FilePath) throws AWTException, InterruptedException {
        navigate_To_data();
        jsclick(AWS_upload);
        upload_files(Files,FilePath);
        screenshot();
    }

    @Step("Multiple Data Files Upload From AWS")
    public void upload_Multipledata_files(ArrayList<String> Files, String FilePath) throws AWTException, InterruptedException {
        navigate_To_data();
        jsclick(AWS_upload);
        upload_multiplefiles(Files,FilePath);
        screenshot();
    }

    @Step("Upload AWS MetaData Files")
    public  void upload_metadata_file(String Files, String FilePath) throws AWTException, InterruptedException {
        navigate_To_Metadata();
        jsclick(AWS_upload);
        upload_files(Files,FilePath);
        screenshot();
        System.out.println("MetaData File "+ Files +" Uploaded");
        //Log.INFO("MetaData File "+ Files +" Uploaded");
    }

    public void upload_files(String Files, String FilePath) throws InterruptedException, AWTException {
        String[] Total_Files =Files.split(",");
       // Ele_Click(Add_Files);
        for(String File : Total_Files){
            Ele_Click(Add_Files);
            normalwait(3000);
            String fp = FilePath+File;
            System.out.println(fp);
            StringSelection selection = new StringSelection(fp);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            normalwait(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            normalwait(1000);
            Ex.Extent_Info("Uploaded AWS FileName :"+File);
        }


    }

    @Step("AWS Files Upload Success Message")
    public void Verify_Upload_Success_msg(){
        normalwait(2000);
        ScrollView(AWS_FileUpload);
        jsclick(AWS_FileUpload);
        wait.until(ExpectedConditions.visibilityOf(Success_Msg));
        String Scs = Success_Msg.getText();
        Assert.assertEquals(Scs,Constant.Aws_UploadMsg);
        System.out.println(" AWS Files Are Uploaded Successfully As Expected");
        //Log.INFO(" AWS Files Are Uploaded Successfully As Expected");
        screenshot();
        Ex.Pass_ScreenShot("AWS File Uploaded Success Message");
    }

    public void Navigate_To_Bucket(String BucketName){
            Ele_Click(Close_Btn);
            Ele_Click(driver.findElement(By.xpath("//span[text()='"+BucketName+"']")));
    }

    public void navigate_To_Metadata(){
        page_loader();
        jsclick(AWSMetaData_Icon);
    }

    public void navigate_To_Error(){
        page_loader();
        jsclick(AWSError_Icon);
        page_loader();
        Ex.Pass_ScreenShot("Navigated To AWS Error Folder");
        screenshot();
        navigate_To_Metadata();
    }

    @Step("Navigate And Validate Error Folder")
    public String validate_Error_folder(String Filename,String Oldtime) throws InterruptedException, ParseException {
        navigate_To_Error();
        List<WebElement> Files = driver.findElements(By.xpath("(//span[text()='"+Filename+"']//ancestor::tr)//span[@class='column-LastModified']"));
        String Aws_Time = "none";
        if (Files.size()>0){
            WebElement File =  driver.findElement(By.xpath("(//span[text()='"+Filename+"']//ancestor::tr)//span[@class='column-LastModified']"));
            ScrollView_Action(File);
            String Fulltime = File.getText();
            String[] Check =Fulltime.split(" ");
            Aws_Time =Check[3].replace(" ","");
            validate_time_difference(Aws_Time,Oldtime);

        }
        Ex.Pass_ScreenShot("Error Folder ScreenShot");
        screenshot();
        return Aws_Time;
    }

    public void validate_time_difference(String AWS_OldTime,String AWS_CurrentTime){
        if (AWS_OldTime.equals(AWS_CurrentTime)){
            System.out.println("Files Has not Moved to Error Folder As Expected");
            Ex.Extent_Info("Files Has not Moved to Error Folder As Expected since "+AWS_OldTime+"  and "+AWS_CurrentTime+" are same ");
            Assert.assertTrue(false);
        }
        else{
            Ex.Extent_Info("Files Has  Moved to Error Folder As Expected since "+AWS_OldTime+"  and "+AWS_CurrentTime+" are not  same ");
            System.out.println("Files Has  Moved to Error Folder As Expected");
            Assert.assertTrue(true);
        }
    }
    public String getcurrenttime(){
        LocalDateTime instance = LocalDateTime.now();

        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");

        String formattedString = formatter.format(instance);
        System.out.println(formattedString);
        String[] time = formattedString.split(" ");
        String Time = time[1];

        return Time;
    }
    public void Validate_time_difference(String Aws_Time) throws ParseException {
        String Current_Time =getcurrenttime();
        SimpleDateFormat simpleDateFormat
                = new SimpleDateFormat("HH:mm:ss");
        Date date1 = simpleDateFormat.parse(Current_Time);
        Date date2 = simpleDateFormat.parse(Aws_Time);
        long differenceInMilliSeconds
                = Math.abs(date2.getTime() - date1.getTime());
        long differenceInSeconds
                = (differenceInMilliSeconds / 1000) % 60;
        System.out.println(differenceInSeconds);
    }

    public void upload_multiplefiles(ArrayList<String> Files, String FilePath) throws InterruptedException, AWTException {

        for(String File : Files){
            Ele_Click(Add_Files);
            normalwait(3000);
            String fp = FilePath+File;
            StringSelection selection = new StringSelection(fp);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            normalwait(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            normalwait(1000);
            Ex.Extent_Info("Uploaded AWS FileName :"+File);
        }

    }



}















