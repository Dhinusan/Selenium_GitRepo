package com.redcapPage;

import Resources.Common_Functions;
import Resources.Cons2;
import Resources.Constant;
import Resources.ExtentManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TechnicalSettingsPage extends Common_Functions {
    WebDriverWait wait = new WebDriverWait(driver, 5);
    ExtentManager Ex = new ExtentManager();

    public TechnicalSettingsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(),'Technical Settings')]")
    public WebElement Technical_Settings;

    @FindBy(xpath = "//div[text()='Project settings']")
    public WebElement Project_Settings;

    @FindBy(xpath = "//div[text()='HPC system settings']")
    public WebElement HPC_Settings;

    @FindBy(xpath = "//div[text()='Analytics system settings']")
    public WebElement Analytics_Settings;

    @FindBy(xpath = "//h3[text()='Master Node Information']")
    public WebElement Master_Node_Info;

    @FindBy(xpath = "//h3[text()='Login Instance Information']")
    public WebElement Login_Instance_Info;

    @FindBy(xpath = "//span[contains(text(),'Request EC2 instance')]")
    public WebElement Req_EC2_Instance;

    @FindBy(xpath = "//span[contains(text(),'Request EC2 instance')]//parent::button[@disabled='true']")
    public List<WebElement> Req_EC2_Instance_Disabled;

    @FindBy(xpath = "//mat-label[text()='Select instance size']")
    public WebElement Instance_Size_DrpDwn;

    @FindBy(xpath = "//simple-snack-bar[contains(@class,'mat-simple-snackbar')]/span")
    public WebElement Notification_Msg;

    @FindBy(xpath = "//span[text()='Request']")
    public WebElement EC2_Instance_Request;

    @FindBy(xpath = "(//p[text()='IP address']/..//p)[2]")
    public WebElement IP_Address;

    @FindBy(xpath = "(//p[text()='Instance ID']/..//p)[2]")
    public WebElement Instance_ID;

    @FindBy(xpath = "(//p[text()='AWS Container Registry URL']/..//p)[2]")
    public WebElement AWS_Container_URL;

    @FindBy(xpath = "(//p[text()='AWS Source Repository https URL']/..//p)[2]")
    public WebElement AWS_Source_URL;

    @FindBy(xpath = "//span[contains(text(),'View AWS Link')]")
    public WebElement View_AWS_Link;

    @FindBy(xpath = "//h2[contains(text(),'AWS link')]/../..//a")
    public WebElement AWS_Link;

    @FindBy(xpath = "//span[text()='Close']")
    public WebElement AWS_Link_Close;

    @FindBy(xpath = "//mat-table[contains(@class,'tech-table')]//mat-row")
    public List<WebElement> EC2_InfoRow_Count;

    @FindBy(xpath = "//a[contains(text(),'Terminate Instance')]")
    public WebElement Terminate_Instance;

    @FindBy(xpath = "//a[contains(text(),'Terminate Instance')]")
    public List<WebElement> Terminate_Instance_Count;

    @FindBy(xpath = "//span[text()='Yes']")
    public WebElement Yes_Btn;

    @FindBy(xpath = "//mat-header-cell[contains(text(),'EC2 Instance ID')]")
    public WebElement EC2_Header_Analytics_Settings;

    @FindBy(xpath = "//mat-header-cell[contains(text(),'Size')]")
    public WebElement Size_Header_Analytics_Settings;

    @FindBy(xpath = "//mat-header-cell[contains(text(),'Status')]")
    public WebElement Status_Header_Analytics_Settings;

    @FindBy(xpath = "//mat-header-cell[contains(text(),'Type of Instance')]")
    public WebElement TOI_Header_Analytics_Settings;

    @FindBy(xpath = "//mat-header-cell[contains(text(),'HTTPS link')]")
    public WebElement HTTPSLink_Header_Analytics_Settings;

    @FindBy(xpath = "//mat-header-cell[contains(text(),'Requested By')]")
    public WebElement RequestedBy_Header_Analytics_Settings;

    @FindBy(xpath = "//mat-header-cell[contains(text(),'Requested Date')]")
    public WebElement RequestedDate_Header_Analytics_Settings;

    @FindBy(xpath = "//a[text()='Apps']/../..//li//a[contains(text(),'Project Management')]/../..//li[text()='Project Settings']")
    public WebElement ProjectSettings_BreadCrumb;

    @FindBy(xpath = "//a[text()='Apps']/../..//li//a[contains(text(),'Project Management')]/../..//li[text()='HPC system settings']")
    public WebElement HPCSettings_BreadCrumb;

    @FindBy(xpath = "//span[text()='View files in Scratch FSx storage']")
    public WebElement ViewFiles_ScratchFX;

    @FindBy(xpath = "//a[text()='Apps']/../..//li//a[contains(text(),'Project Management')]/../..//li//a[text()='HPC system settings']/../..//li[text()='HPC Scratch FSx Storage']")
    public WebElement HPCScratch_Breadcrumb;

    @FindBy(xpath = "//a[text()='Apps']/../..//li//a[contains(text(),'Project Management')]/../..//li[text()='Analytics system settings']")
    public WebElement AnalyticsSys_Breadcrumb;

    @FindBy(xpath = "//a[text()='Apps']/../..//li//a[contains(text(),'Project Management')]/../..//li//a[text()='Analytics system settings']/../..//li[text()='Data Analytics Scratch FSx Storage']")
    public WebElement AnalyticsScratch_Breadcrumb;

    @FindBy(xpath = "//mat-header-cell[text()=' Name ']")
    public WebElement HPC_NameHeader;

    @FindBy(xpath = "//mat-header-cell[text()=' Type ']")
    public WebElement HPC_TypeHeader;

    @FindBy(xpath = "//mat-header-cell[text()=' Last Modified ']")
    public WebElement HPC_LastModified_Header;

    @FindBy(xpath = "//p[text()='HPC Scratch FSx Storage']")
    public WebElement HPC_ScratchFSx_Storage_Header;

    @FindBy(xpath = "//p[text()='Data Analytics Scratch FSx Storage']")
    public WebElement   Data_Analytics_ScratchFSx_Storage_Header;

    @FindBy(xpath = "//mat-icon[text()='folder_open']")
    public WebElement Folder_Icon;

    @FindBy(xpath = "//mat-icon[text()='folder_open']/../../..//a")
    public WebElement Folder_Name;

    @FindBy(xpath = "//span[text()='root/']/../..//span[@class='active ng-star-inserted']")
    public WebElement Folder_Name_Validation;


    @FindBy(xpath = "//a[text()='Infra Settings']")
    public WebElement Infra_Settings;




    @FindBy(xpath = "//mat-label[text()=' IRDB ']/../..//div[@class='mat-slide-toggle-bar']")
    public WebElement IRDB_ToggleBar;

    @FindBy(xpath = "//mat-label[text()=' RStudio ']/../..//div[@class='mat-slide-toggle-bar']")
    public WebElement RStudio_ToggleBar;

    @FindBy(xpath = "//mat-label[text()=' Jupyter ']/../..//div[@class='mat-slide-toggle-bar']")
    public WebElement Jupyter_ToggleBar;

    @FindBy(xpath = "//mat-label[text()=' HPC ']/../..//div[@class='mat-slide-toggle-bar']")
    public WebElement HPC_ToggleBar;


    @FindBy(xpath = "//span[text()='Cancel']")
    public WebElement Cancel_Btn;

    @FindBy(xpath = "//span[text()='Save']")
    public WebElement Save_Btn;

    @FindBy(xpath = "//button[contains(text(),' SageMaker with Jupyter')]")
    public WebElement SageMaker_with_Jupyter;

    @FindBy(xpath = "//mat-label[text()=' Jupyter ']/../..//span[@class='mat-slide-toggle-content']")
    public WebElement Jupyter_Side_Content;

    @FindBy(xpath = "//mat-label[text()=' RStudio ']/../..//span[@class='mat-slide-toggle-content']")
    public WebElement RStudio_Slide_Content;

    @FindBy(xpath = "//span[contains(text(),' Go to RStudio')]/..")
    public WebElement Go_To_RStudio_Btn;

    @FindBy(xpath = "//mat-label[contains(text(),' RStudio ')]/../..//span")
    public WebElement RStudio_Status;

    @FindBy(xpath = "//span[contains(text(),'RStudio')]/../..//button[@disabled='true']")
    public WebElement RStudio_Status_Disabled;

    @FindBy(xpath = "//span[contains(text(),'RStudio')]/../..//button[@disabled='true']")
    public List<WebElement> RStudio_Status_Disabled_size;

    @FindBy(xpath = "//mat-label[contains(text(),' HPC')]/../..//span")
    public WebElement HPC_Status;

    @FindBy(xpath = "//mat-label[contains(text(),'Jupyter')]/../..//span")
    public WebElement Jupyter_Status;

    @FindBy(xpath = "//h2[contains(text(),'Create User Profile')]")
    public WebElement RStudio_Header;

    @FindBy(xpath = "//h2[contains(text(),'Create User Profile')]/..//p")
    public WebElement RStudio_Description;

    @FindBy(xpath = "//mat-icon[text()='close']")
    public WebElement Close_Icon;

    @FindBy(xpath = "//span[text()='Create']")
    public WebElement Create_Btn;

    @FindBy(xpath = "//div[contains(@class,'mat-tooltip') and text()='User profile is being created. Try launching RStudio after sometime.']")
    public WebElement Rstudio_Tooltip;

    @FindBy(xpath = "(//mat-table[contains(@class,'tech-table')]//mat-row)//mat-cell[8]//mat-icon")
    public WebElement Elipse_Icon;

    @FindBy(xpath = "(//mat-table[contains(@class,'tech-table')]//mat-row)//mat-cell[8]//mat-icon")
    public List<WebElement> Elipse_Icon_size;

    @FindBy(xpath = "//mat-icon[text()='refresh ']")
    public WebElement Refresh_Icon;

        public void verify_rstudio_profile_creation_UI(){
          int count =0;
            do{
                navigate_to_analyticsettings();
                normalwait(2000);
                driver.navigate().refresh();
                navigate_to_analyticsettings();
                ScrollView_Action(View_AWS_Link);
                count =RStudio_Status_Disabled_size.size();
            }
            while (count!=0);
        }
    public void verify_create_rstudio(){
        navigate_to_rstudio();
        jsclick(Create_Btn);
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg,Constant.Rstudio_Create_SuccessMsg);
        screenshot();
        Ex.Pass_ScreenShot("Rstudio Create Success Message");
        //verify_rstudio_disabled_da();
        //mouse_hover(Go_To_RStudio_Btn);
        Assert.assertTrue(Rstudio_Tooltip.isDisplayed());
        Ex.Pass_ScreenShot("Expected Rstudio mouse hover msg displayed as expected");
    }
    public void navigate_to_rstudio(){
        jsclick(Go_To_RStudio_Btn);
        normalwait(2000);
    }
    public void verify_rstudio_btn_attributes(){
        navigate_to_rstudio();
        Assert.assertTrue(RStudio_Header.isDisplayed());
        String Description =RStudio_Description.getText().trim();
        Assert.assertEquals(Description, Cons2.Rstudio_Description);
        Assert.assertTrue(Close_Icon.isDisplayed());
        Assert.assertTrue(Cancel_Btn.isDisplayed());
        Assert.assertTrue(Create_Btn.isDisplayed());
        Ex.Pass_ScreenShot("All Rstudio Attributes Are Displayed As Expected.");
        jsclick(Close_Icon);


    }
    public void verify_rstudio_disabled_da(){

            String status=null;
        do{
            normalwait(2000);
            driver.navigate().refresh();
            status =RStudio_Slide_Content.getText().trim();
            System.out.print(status);
        }
        while (status.equalsIgnoreCase("disable in progress"));
        Ex.Pass_ScreenShot("RStudio Btn Moved to Diabled Status");
    }

    @Step("Change RStudio To Disabled")
    public void change_rstuido_status_disbaled(){
        //jsclick(Infra_Settings);
        normalwait(3000);
        ActionClick(RStudio_ToggleBar);
        jsclick(Yes_Btn);
        jsclick(Save_Btn);
        Infra_SettingsSaveSuccessMsg();
        jsclick(Save_Btn);
        Assert.assertTrue(RStudio_Slide_Content.getText().trim().contains("Disable"));
        screenshot();
        Ex.Pass_ScreenShot("Change Jupyter To Disabled");
    }
    @Step("Change Jupyter To Disabled")
    public void change_jupyter_status_disabled(){
        jsclick(Infra_Settings);
        normalwait(2000);
        jsclick(Jupyter_ToggleBar);
        jsclick(Yes_Btn);
        jsclick(Save_Btn);
        Infra_SettingsSaveSuccessMsg();
       Assert.assertEquals(Jupyter_Side_Content.getText().trim(),"Disable – In progress");
       screenshot();
       Ex.Pass_ScreenShot("Change Jupyter To Disabled");
    }
    @Step("Verify Jupyter Instance In HPC,DA ")
    public void verify_jupyterinHPC_DA(){
        jsclick(HPC_Settings);
        ScrollView_Action(Req_EC2_Instance);
        jsclick(Req_EC2_Instance);
        Assert.assertTrue(SageMaker_with_Jupyter.isDisplayed(),"Jupyter Dropdown Displayed in HPC As Expected");
        screenshot();
        Ex.Pass_ScreenShot("Jupyter Dropdown Displayed in HPC As Expected");
        jsclick(Analytics_Settings);
        ScrollView_Action(Req_EC2_Instance);
        jsclick(Req_EC2_Instance);
        Assert.assertTrue(SageMaker_with_Jupyter.isDisplayed(),"Jupyter Dropdown Displayed in DA As Expected");
        screenshot();
        Ex.Pass_ScreenShot("Jupyter Dropdown Displayed in DA As Expected");
    }


    @Step("Infra Settings Success Message")
    public void Infra_SettingsSaveSuccessMsg(){
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg,Constant.Infra_Settings_SuccessMsg);
        screenshot();
        Ex.Pass_ScreenShot("Infra Settings Success Message");
    }
    public void navigate_to_infrasettings() throws InterruptedException {
        jsclick(Infra_Settings);
    }


    @Step("Verify Infra Settings Page Attributes")
    public void verify_Infra_Attributes(){
    jsclick(Infra_Settings);
    Assert.assertTrue(HPC_ToggleBar.isDisplayed(),"HPC Toggle Bar Not Displayed As Expected");
        Assert.assertTrue(IRDB_ToggleBar.isDisplayed(),"IRDB Toggle Bar Not Displayed As Expected");
        Assert.assertTrue(RStudio_ToggleBar.isDisplayed(),"R Studio Toggle Bar Not Displayed As Expected");
        Assert.assertTrue(Jupyter_ToggleBar.isDisplayed(),"Jupyter Toggle Bar Not Displayed As Expected");
        Assert.assertTrue(Cancel_Btn.isDisplayed());
        Assert.assertTrue(Save_Btn.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("HPC,IRDB,RStudio,Jupyter Toggle Bars Diplayed In Infra Settings Page");
    }
    @Step("Verify Data Analytics Scratch Fsx Attributes Are Present")
    public void verify_Analytics_scratchfsx_Attributes() {
        jsclick(Analytics_Settings);
        normalwait(1000);
        jsclick(ViewFiles_ScratchFX);
        normalwait(5000);
        Assert.assertTrue(AnalyticsScratch_Breadcrumb.isDisplayed());
        Assert.assertTrue(HPC_NameHeader.isDisplayed());
        Assert.assertTrue(HPC_TypeHeader.isDisplayed());
        Assert.assertTrue(HPC_LastModified_Header.isDisplayed());
        Assert.assertTrue(Data_Analytics_ScratchFSx_Storage_Header.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Verify Data Analytics Scratch Fsx Attributes Are Present");


    }

    @Step("root/Foldernamexxx is displayed as expected")
    public void folder_navigation(){
        String FolderName =Folder_Name.getText();
        System.out.println(FolderName);
        jsclick(Folder_Icon);
        String FolderName2 =Folder_Name_Validation.getText();
        if (FolderName.equalsIgnoreCase(FolderName2)){
            Assert.assertTrue(true);
            screenshot();
            Ex.Pass_ScreenShot("root/Foldernamexxx is displayed as expected");
        }
        else{
            Assert.assertTrue(false);
        }
    }

    public boolean isFileDownloaded(String DownloadPath, String fileName) {
        boolean flag = false;
        File dir = new File(DownloadPath);
        File[] dir_contents = dir.listFiles();

        for (int i = 0; i < dir_contents.length; i++) {
            if (dir_contents[i].getName().equals(fileName))
                return flag=true;
        }

        return flag;
    }
    public void verify_download_files(String filename){
        driver.findElement(By.xpath("//a[text()='"+filename+"']")).click();
        normalwait(2000);
        jsclick(Yes_Btn);
        normalwait(10000);
        Assert.assertTrue(isFileDownloaded(DownloadPath,filename), "Failed to download Expected document");
        Ex.Extent_Pass(filename+" File Has Been Downloaded");
    }

    @Step("Verification Of New Tab ")
    public void verify_new_window(String filename) {
        String pageTitle=null;
        String MainWindow = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[text()='"+filename+"']")).click();
        normalwait(3000);
        Set<String> s1 = driver.getWindowHandles();
        int allWindowHandles = s1.size();
        System.out.println(allWindowHandles);
        Iterator<String> i1 = s1.iterator();
        while (i1.hasNext()) {
            String ChildWindow = i1.next();
            if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
             pageTitle = driver.switchTo().window(ChildWindow).getTitle();
                if (pageTitle.contains(filename)){
                Assert.assertTrue(true);
                screenshot();
                Ex.Pass_ScreenShot("New Tab For "+filename+" has been opened as Expected");
                }
                driver.close();
                driver.switchTo().window(MainWindow);

            }
        }

    }


    @Step("Verify HPC Scratch Fsx Attributes Are Present")
    public void verify_hpc_scratchfsx_Attributes() {
        jsclick(HPC_Settings);
        normalwait(1000);
        jsclick(ViewFiles_ScratchFX);
        normalwait(5000);
        Assert.assertTrue(HPCScratch_Breadcrumb.isDisplayed());
        Assert.assertTrue(HPC_NameHeader.isDisplayed());
        Assert.assertTrue(HPC_TypeHeader.isDisplayed());
        Assert.assertTrue(HPC_LastModified_Header.isDisplayed());
        Assert.assertTrue(HPC_ScratchFSx_Storage_Header.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Verify HPC Scratch Fsx Attributes Are Present");


    }

    @Step("Manifest Creation BreadCrumb Displayed in Create MetaData Page")
    public void verify_Pm_Breadcrumbs_technicalsettings() {
        jsclick(Technical_Settings);
        normalwait(3000);
        Assert.assertTrue(ProjectSettings_BreadCrumb.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Project Management BreadCrumb Displayed in Technical Settings Page");
        jsclick(HPC_Settings);
        Assert.assertTrue(HPCSettings_BreadCrumb.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("HPC Settings BreadCrumb Displayed in HPC Settings  Page");
        /*jsclick(ViewFiles_ScratchFX);
        Assert.assertTrue(HPCScratch_Breadcrumb.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("HPC Scratch BreadCrumb Displayed in HPC Scratch  Page");*/
        jsclick(Analytics_Settings);
        Assert.assertTrue(AnalyticsSys_Breadcrumb.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Analytics Syatem Settings BreadCrumb Displayed in Analytics Settings  Page");
        jsclick(ViewFiles_ScratchFX);
        Assert.assertTrue(AnalyticsScratch_Breadcrumb.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Analytics Scratch Settings BreadCrumb Displayed in Analytics Scratch  Page");
    }

    public void navigate_to_technicalsettings() throws InterruptedException {
        jsclick(Technical_Settings);
    }

    @Step("Technical Setting Headers Project,HPC,Anlytics Are Displayed As Expected")
    public void verify_techincal_settings() {
        Assert.assertTrue(Project_Settings.isDisplayed());
        Assert.assertTrue(HPC_Settings.isDisplayed());
        Assert.assertTrue(Analytics_Settings.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Technical Setting Headers Project,HPC,Anlytics Are Displayed As Expected");

    }

    public void verify_req_EC2instance_disabled(){
            driver.navigate().refresh();
            navigate_to_analyticsettings();
        int count =Req_EC2_Instance_Disabled.size();
        Assert.assertEquals(count,1);
        Ex.Pass_ScreenShot("Req Ec2 Instance Btn Disabled Since 5 instance are created already");
    }

    @Step("Reqesting EC2 Instance and Success Message In  HPC")
    public void request_ec2_instance_HPC(String EC2_Instance, String size) throws InterruptedException {
        String[] Instances =EC2_Instance.split(">");
        String[] sizes =size.split(">");

        for (int i=0;i<Instances.length;i++) {
            ScrollView_Action(HPC_Settings);
            ActionClick(HPC_Settings);
            ScrollView(Req_EC2_Instance);
            jsclick(Req_EC2_Instance);
            WebElement EC2_InstanceType = driver.findElement(By.xpath("//button[contains(text(),'" + Instances[i] + "')]"));
            jsclick(EC2_InstanceType);
            normalwait(3000);
            ActionClick(Instance_Size_DrpDwn);
            normalwait(1000);
            WebElement ele = driver.findElement(By.xpath("//span[contains(text(),'" + sizes[i] + "')]"));
            jsclick(ele);
            jsclick(EC2_Instance_Request);
            verify_EC2Ins_SuccessMsg();
            normalwait(3000);
            driver.navigate().refresh();
        }

    }

    @Step("Reqesting EC2 Instance and Success Message")
    public void request_ec2_instance(String EC2_Instance, String size) throws InterruptedException {
        String[] Instances =EC2_Instance.split(">");
        String[] sizes =size.split(">");

        for (int i=0;i<Instances.length;i++) {
            ScrollView_Action(Analytics_Settings);
            ActionClick(Analytics_Settings);
            ScrollView(Req_EC2_Instance);
            jsclick(Req_EC2_Instance);
            WebElement EC2_InstanceType = driver.findElement(By.xpath("//button[contains(text(),'" + Instances[i] + "')]"));
            jsclick(EC2_InstanceType);
            normalwait(3000);
            ActionClick(Instance_Size_DrpDwn);
            normalwait(1000);
            WebElement ele = driver.findElement(By.xpath("//span[contains(text(),'" + sizes[i] + "')]"));
            jsclick(ele);
            jsclick(EC2_Instance_Request);
            verify_EC2Ins_SuccessMsg();
            normalwait(3000);
            driver.navigate().refresh();
        }

    }

    @Step("Validate EC2 Instance Information")
    public void validate_EC2Instance_Info(String Size, String InstanceType, String user) {

        jsclick(HPC_Settings);
        int Count = EC2_InfoRow_Count.size();

        WebElement EC2_Info_Size = driver.findElement(By.xpath("(//mat-table[contains(@class,'tech-table')]//mat-row)[" + Count + "]//mat-cell[2]"));
        Assert.assertEquals(EC2_Info_Size.getAttribute("innerText"), Size);
        ScrollView_Action(EC2_Info_Size);
        WebElement EC2_Info_Status = driver.findElement(By.xpath("(//mat-table[contains(@class,'tech-table')]//mat-row)[" + Count + "]//mat-cell[3]"));
        Assert.assertEquals(EC2_Info_Status.getAttribute("innerText"), "Pending");

        WebElement EC2_Info_InstanceType = driver.findElement(By.xpath("(//mat-table[contains(@class,'tech-table')]//mat-row)[" + Count + "]//mat-cell[4]"));
        Assert.assertEquals(EC2_Info_InstanceType.getAttribute("innerText"), InstanceType);

        WebElement EC2_Info_RequestedBy = driver.findElement(By.xpath("(//mat-table[contains(@class,'tech-table')]//mat-row)[" + Count + "]//mat-cell[6]"));
        Assert.assertEquals(EC2_Info_RequestedBy.getAttribute("innerText"), user);
        screenshot();
        Ex.Pass_ScreenShot("Validate EC2 Instance Information");
        String status = null;
        do {
            normalwait(2000);
            driver.navigate().refresh();
            jsclick(HPC_Settings);
            WebElement New_Status = driver.findElement(By.xpath("(//mat-table[contains(@class,'tech-table')]//mat-row)[" + Count + "]//mat-cell[3]"));
            status = New_Status.getAttribute("innerText");
            ScrollView_Action(New_Status);
        }
        while (status.equalsIgnoreCase("Pending"));

        Ex.Pass_ScreenShot("Ec2 Instance Moved To Running Status");
    }


    @Step("Verifying Terminating EC2 Instance Success Message")
    public void verify_terminate_instance_AnaSettings() {
        driver.navigate().refresh();
        jsclick(Analytics_Settings);
        int Count = EC2_InfoRow_Count.size();
        WebElement Instance_ID = driver.findElement(By.xpath("(//mat-table[contains(@class,'tech-table')]//mat-row)[" + Count + "]//mat-cell[1]"));
        String Instance_Id = Instance_ID.getAttribute("innerText");
        WebElement Elipse = driver.findElement(By.xpath("(//mat-table[contains(@class,'tech-table')]//mat-row)[" + Count + "]//mat-cell[8]//mat-icon"));
        ScrollView_Action(Elipse);
        jsclick(Elipse);
        jsclick(Terminate_Instance);
        jsclick(Yes_Btn);
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg, "" + Instance_Id + " termination in progress");
        screenshot();
        Ex.Pass_ScreenShot("Verifying Terminating EC2 Instance Success Message");
    }

    public void verify_EC2Ins_SuccessMsg() {
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg, Constant.EC2_Instance_SuccessMsg);
        screenshot();
        Ex.Pass_ScreenShot("EC2 instance Creation Success Message");
    }

    @Step("Validate HPC Settings Attribbutes")
    public void verify_hpc_settings(String Aws_Username) {
        jsclick(HPC_Settings);
        normalwait(3000);
        String[] Aws_Container = AWS_Container_URL.getAttribute("innerText").split(" ");
        String[] Aws_Source = AWS_Source_URL.getAttribute("innerText").split(" ");
        Assert.assertFalse(IP_Address.getText().isEmpty(), "IP Address Is Empty Which is Not As Expeceted");
        Assert.assertFalse(Instance_ID.getText().isEmpty(), "Instance ID Is Empty Which is Not As Expeceted");
        Assert.assertEquals(Aws_Container[1], Constant.AWS_Container_URL);
        Assert.assertEquals(Aws_Source[1], Constant.AWS_Source_URL);
        screenshot();
        Ex.Pass_ScreenShot("IP Address,Instance ID,AWS Container,Source Validated");
        jsclick(View_AWS_Link);
        normalwait(3000);
        Assert.assertEquals(AWS_Link.getAttribute("href"), Constant.AWS_Link);
        WebElement User = driver.findElement(By.xpath("//div[contains(text(),' AWS Username: " + Aws_Username + " ')]"));
        Assert.assertTrue(User.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("View_AWS_Link Validated");
        jsclick(AWS_Link_Close);

    }

    public void navigate_to_analyticsettings() {
        ScrollView_Action(Analytics_Settings);
        ActionClick(Analytics_Settings);
    }

    @Step("EC2 Table Headers Validation -Analytics Settings Page")
    public void verify_EC2InstanceHeaders_Aanlytics() {
        ScrollView_Action(EC2_Header_Analytics_Settings);
        Assert.assertTrue(EC2_Header_Analytics_Settings.isDisplayed());
        Assert.assertTrue(Size_Header_Analytics_Settings.isDisplayed());
        Assert.assertTrue(Status_Header_Analytics_Settings.isDisplayed());
        Assert.assertTrue(TOI_Header_Analytics_Settings.isDisplayed());
        Assert.assertTrue(HTTPSLink_Header_Analytics_Settings.isDisplayed());
        Assert.assertTrue(RequestedBy_Header_Analytics_Settings.isDisplayed());
        Assert.assertTrue(RequestedDate_Header_Analytics_Settings.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("EC2 Table Headers Validation -Analytics Settings Page");

    }

    public void verify_terminate_otheruser_Instance(String Role) {
        if (Role.equalsIgnoreCase(Constant.Data_User1) || Role.equalsIgnoreCase(Constant.Data_User2)) {
            jsclick(Analytics_Settings);
            int Count = EC2_InfoRow_Count.size();
            WebElement Instance_ID = driver.findElement(By.xpath("(//mat-table[contains(@class,'tech-table')]//mat-row)[" + Count + "]//mat-cell[1]"));
            String Instance_Id = Instance_ID.getAttribute("innerText");
            List<WebElement> Elipse = driver.findElements(By.xpath("(//mat-table[contains(@class,'tech-table')]//mat-row)[" + Count + "]//mat-cell[8]//mat-icon"));
            Assert.assertEquals(Elipse.size(), 0);
            screenshot();
            Ex.Pass_ScreenShot(Role + " Does Not Have Access To terminate instance created by another user as expected");
        } else if (Role.equalsIgnoreCase(Constant.Data_Manager)) {
            jsclick(Analytics_Settings);
            int Count = EC2_InfoRow_Count.size();
            WebElement Instance_ID = driver.findElement(By.xpath("(//mat-table[contains(@class,'tech-table')]//mat-row)[" + Count + "]//mat-cell[1]"));
            String Instance_Id = Instance_ID.getAttribute("innerText");
            WebElement Elipse = driver.findElement(By.xpath("(//mat-table[contains(@class,'tech-table')]//mat-row)[" + Count + "]//mat-cell[8]//mat-icon"));
            ScrollView_Action(Elipse);
            jsclick(Elipse);
            Assert.assertEquals(Terminate_Instance_Count.size(), 1);
            screenshot();
            Ex.Pass_ScreenShot(Role + "  Have Access To terminate instance created by another user as expected");
            jsclick(Terminate_Instance);
            jsclick(Yes_Btn);
            wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
            String Success_Msg = Notification_Msg.getText();
            Assert.assertEquals(Success_Msg, "" + Instance_Id + " termination in progress");
            screenshot();
            Ex.Pass_ScreenShot("Verifying Terminating EC2 Instance Success Message");
        }

    }

    @Step("Validate EC2 Instance Information")
    public void validate_EC2Instance_Info_AnaSettings(String Size, String InstanceType, String user) {

        jsclick(Analytics_Settings);
        int Count = EC2_InfoRow_Count.size();

        WebElement EC2_Info_Size = driver.findElement(By.xpath("(//mat-table[contains(@class,'tech-table')]//mat-row)[" + Count + "]//mat-cell[2]"));
        Assert.assertEquals(EC2_Info_Size.getAttribute("innerText"), Size);
        ScrollView_Action(EC2_Info_Size);
        WebElement EC2_Info_Status = driver.findElement(By.xpath("(//mat-table[contains(@class,'tech-table')]//mat-row)[" + Count + "]//mat-cell[3]"));
        Assert.assertEquals(EC2_Info_Status.getAttribute("innerText"), "Pending");

        WebElement EC2_Info_InstanceType = driver.findElement(By.xpath("(//mat-table[contains(@class,'tech-table')]//mat-row)[" + Count + "]//mat-cell[4]"));
        Assert.assertEquals(EC2_Info_InstanceType.getAttribute("innerText"), InstanceType);

        WebElement EC2_Info_RequestedBy = driver.findElement(By.xpath("(//mat-table[contains(@class,'tech-table')]//mat-row)[" + Count + "]//mat-cell[6]"));
        Assert.assertEquals(EC2_Info_RequestedBy.getAttribute("innerText"), user);
        screenshot();
        Ex.Pass_ScreenShot("Validate EC2 Instance Information");
        String status = null;
        do {
            normalwait(2000);
            driver.navigate().refresh();
            jsclick(Analytics_Settings);
            WebElement New_Status = driver.findElement(By.xpath("(//mat-table[contains(@class,'tech-table')]//mat-row)[" + Count + "]//mat-cell[3]"));
            status = New_Status.getAttribute("innerText");
            ScrollView_Action(New_Status);
        }
        while (status.equalsIgnoreCase("Pending"));

        Ex.Pass_ScreenShot("Ec2 Instance Moved To Running Status");
    }

    @Step("Verifying Terminating EC2 Instance Success Message")
    public void verify_terminate_instance_HPCSettings() {
        driver.navigate().refresh();
        jsclick(HPC_Settings);
        normalwait(2000);
        int Count = EC2_InfoRow_Count.size();
        WebElement Instance_ID = driver.findElement(By.xpath("(//mat-table[contains(@class,'tech-table')]//mat-row)[" + Count + "]//mat-cell[1]"));
        String Instance_Id = Instance_ID.getAttribute("innerText");
        WebElement Elipse = driver.findElement(By.xpath("(//mat-table[contains(@class,'tech-table')]//mat-row)[" + Count + "]//mat-cell[8]//mat-icon"));
        ScrollView_Action(Elipse);
        jsclick(Elipse);
        jsclick(Terminate_Instance);
        jsclick(Yes_Btn);
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg, "" + Instance_Id + " termination in progress");
        screenshot();
        Ex.Pass_ScreenShot("Verifying Terminating EC2 Instance Success Message");
    }

    public void change_rstudio_status_enabled(){
        jsclick(Infra_Settings);
        normalwait(2000);
        String status =RStudio_Status.getText().trim();
        if(status.equalsIgnoreCase("Disabled")){
            jsclick(RStudio_ToggleBar);
            jsclick(Save_Btn);
            Infra_SettingsSaveSuccessMsg();
        }

    }
    public void change_jupyter_status_enabled(){
        jsclick(Infra_Settings);
        normalwait(2000);
        jsclick(Jupyter_ToggleBar);
        jsclick(Save_Btn);
        Infra_SettingsSaveSuccessMsg();
    }
    public void change_jupyter_rstudio_hpc_status_enabled(){
        normalwait(2000);
        String RStudio_status =RStudio_Status.getText().trim();
        String Jupyter_status =Jupyter_Status.getText().trim();
        String HPC_status =HPC_Status.getText().trim();

        if(RStudio_status.equalsIgnoreCase("Disabled")){
            jsclick(RStudio_ToggleBar);
        }
        if(Jupyter_status.equalsIgnoreCase("Disabled")){
            jsclick(Jupyter_ToggleBar);
        }
        if(HPC_status.equalsIgnoreCase("Disabled")){
            jsclick(HPC_ToggleBar);
        }

        jsclick(Save_Btn);
        Infra_SettingsSaveSuccessMsg();
    }



    @Step("Verifying Terminating EC2 Instance Success Message")
    public void terminate_all_instance_analytics_settings() {
        driver.navigate().refresh();
        jsclick(Analytics_Settings);
        int Count = 0;
        do {
            driver.navigate().refresh();
            jsclick(Analytics_Settings);
            ScrollView_Action(Elipse_Icon);
            jsclick(Elipse_Icon);
            jsclick(Terminate_Instance);
            jsclick(Yes_Btn);
            wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
            screenshot();
            Ex.Pass_ScreenShot("Verifying Terminating EC2 Instance Success Message");
            jsclick(Refresh_Icon);
            normalwait(2000);
            Count= Elipse_Icon_size.size();
        }
        while (Count!=0);

    }

}
