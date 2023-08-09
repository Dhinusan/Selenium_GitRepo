package com.redcapPage;

import Resources.Common_Functions;
import Resources.Constant;
import Resources.ExtentManager;
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

public class UserMgmtPage extends Common_Functions {
    WebDriverWait wait = new WebDriverWait(driver,5);
    ExtentManager Ex = new ExtentManager();
    public UserMgmtPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//span[@class='mat-button-wrapper'][normalize-space()='Edit']")
    public WebElement UM_EditBtn;

    @FindBy(xpath = "//span[contains(text(),'View Projects')]")
    public WebElement UM_ViewProjects;

    @FindBy(xpath = "//span[normalize-space()='Add New User']")
    public WebElement UM_AddNewUsr;

    @FindBy(xpath = "//input[@formcontrolname='username']")
    public WebElement UM_UserMailName;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    public WebElement UM_FirstName;

    @FindBy(xpath = "//input[@formcontrolname=\"lastname\"]")
    public WebElement UM_LastName;

    @FindBy(xpath = "//input[@placeholder='Organization']")
    public WebElement UM_Organization;

    @FindBy(xpath = "//input[@placeholder='AWS Username']")
    public WebElement UM_Aws_UserName;


    @FindBy(xpath = "//span[normalize-space()='Cancel']")
    public WebElement UM_CancelBtn;

    @FindBy(xpath = "//span[normalize-space()='Save']")
    public WebElement UM_SaveBtn;

    @FindBy(xpath = "//div[@class='mat-checkbox-inner-container']")
    public WebElement UM_PlatAdminCB;

    @FindBy(xpath = "//input[@placeholder='User Name']/../../..//mat-error")
    public WebElement UM_MailNotification;

    @FindBy(xpath = "//input[@placeholder='First Name']/../../..//mat-error")
    public WebElement UM_FrstNmNotify;

    @FindBy(xpath = "//input[@placeholder='Last Name']/../../..//mat-error")
    public WebElement UM_LastNmNotify;

    @FindBy(xpath = "//input[@placeholder='Organization']/../../..//mat-error")
    public WebElement UM_OrgNotification;

    @FindBy(xpath = "//input[@placeholder='AWS Username']/../../..//mat-error")
    public WebElement UM_AwsUserNotification;

    @FindBy(xpath = "//input[@placeholder='Search here']")
    public WebElement UM_Search;

    @FindBy(xpath = "//span[text()='Delete']")
    public WebElement UM_Delete;

    @FindBy(xpath = "//span[text()='Yes']")
    public WebElement UM_Yes;

    @FindBy(xpath = "//simple-snack-bar[contains(@class,'mat-simple-snackbar')]/span")
    public WebElement Notification_Msg;

    @FindBy(xpath = "//a[text()='Apps']/../..//li[text()='User Management']")
    public WebElement Apps_UserMangmnt_BreadCrumb;

    @FindBy(xpath = "//a[text()='Apps']")
    public WebElement Apps_BreadCrumb;

    @FindBy(xpath = "//span[text()='Cancel']")
    public WebElement Cancel_btn;

    @FindBy(xpath = " //figcaption[contains(text(),'User Management')]")
    public WebElement User_Management_Icon;

    @FindBy(xpath = " //div[@class='create-user']//div[contains(text(),'AWS Username already exists. Please change')]")
    public WebElement Aws_UserExist_ErrorMsg;


    @Step("Verify Aws Invalid Inputs")
    public void Verify_Aws_Invalid_Inputs(String Max_Length_Awsusername,String Inccrct_Awsusername,String Exist_Username,String Valid_Username) throws InterruptedException {
        UM_Aws_UserName.sendKeys(Max_Length_Awsusername);
        Ele_Click(UM_SaveBtn);
        Assert.assertEquals(UM_AwsUserNotification.getText(),Constant.Incorrect_AWS_UserName);
        screenshot();
        Ex.Pass_ScreenShot("Aws Max Length Error Message");
        UM_Aws_UserName.clear();
        UM_Aws_UserName.sendKeys(Inccrct_Awsusername);
        Ele_Click(UM_SaveBtn);
        Assert.assertEquals(UM_AwsUserNotification.getText(),Constant.Incorrect_AWS_UserName_SpecialChar);
        screenshot();
        Ex.Pass_ScreenShot("Aws Invalid Character Error Message");
        UM_Aws_UserName.clear();
        UM_Aws_UserName.sendKeys(Exist_Username);
        Ele_Click(UM_SaveBtn);
        normalwait(3000);
        Assert.assertTrue(Aws_UserExist_ErrorMsg.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Aws Already Existing User Error Message");
        UM_Aws_UserName.clear();
        UM_Aws_UserName.sendKeys(Valid_Username);
        Ele_Click(UM_SaveBtn);
    }


    @Step("User Details")
    public void Create_UM_UserDetails(String MailId, String FirstName , String LastName , String Org) throws InterruptedException {
        UM_UserMailName.sendKeys(MailId);
        UM_FirstName.sendKeys(FirstName);
        UM_LastName.sendKeys(LastName);
        UM_Organization.sendKeys(Org);
        screenshot();
    }





    @Step("Validate Apps > User Management Bredcrumbs For View Projects")
    public void validate_Um_BreadCrumbs_ViewProject(){
        jsclick(UM_ViewProjects);
        Assert.assertTrue(Apps_UserMangmnt_BreadCrumb.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Apps_UserMangmnt_BreadCrumb Is Displayed In Background for View Projects");
        jsclick(Cancel_btn);
    }

    @Step("Validate Apps > User Management Bredcrumbs For Edit User")
    public void validate_Um_BreadCrumbs_EditUser(){
        jsclick(UM_EditBtn);
        Assert.assertTrue(Apps_UserMangmnt_BreadCrumb.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Apps_UserMangmnt_BreadCrumb Is Displayed In Background for Edit User");
        jsclick(Cancel_btn);
    }


    @Step("Validate Apps > User Management Bredcrumbs For Add User")
    public void validate_Um_BreadCrumbs_AddUser(){
        jsclick(Apps_BreadCrumb);
        Ele_Click(User_Management_Icon);
        normalwait(3000);
        Ele_Click(UM_AddNewUsr);
        Assert.assertTrue(Apps_UserMangmnt_BreadCrumb.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Apps_UserMangmnt_BreadCrumb Is Displayed In Background for Add User");
        jsclick(Cancel_btn);
    }

    @Step("Verify User Management Attributes")
    public void verify_UM_Attributes(){
        wait.until(ExpectedConditions.visibilityOf(UM_EditBtn));
        Assert.assertTrue(UM_ViewProjects.isDisplayed(),"View Project button is present on page");
        Assert.assertTrue(UM_EditBtn.isDisplayed(),"Edit button is present on page");
        Assert.assertTrue(UM_AddNewUsr.isDisplayed(),"Add new user button is present on page");
    }

    @Step("Verify Add New User Attributes")
    public void verify_UM_AddNewUser_Attributes(){
        Ele_Click(UM_AddNewUsr);
        Assert.assertTrue(Apps_UserMangmnt_BreadCrumb.isDisplayed());
        Assert.assertTrue(UM_UserMailName.isEnabled(),"username is enabled on page");
        Assert.assertTrue(UM_FirstName.isEnabled(),"firstname is enabled on page");
        Assert.assertTrue(UM_LastName.isEnabled(),"lastname is enabled on page");
        Assert.assertTrue(UM_Organization.isEnabled(),"organization is enabled on page");
        Assert.assertTrue(UM_Aws_UserName.isEnabled(),"AWS username is enabled on page");
        Assert.assertTrue(UM_PlatAdminCB.isEnabled(),"Platform admin checkbox is present on page");
        Assert.assertTrue(UM_CancelBtn.isEnabled(),"cancel button is enabled on page");
        Assert.assertTrue(UM_SaveBtn.isEnabled(),"save button is enabled on page");
        screenshot();
        Ex.Pass_ScreenShot("Verify Add New User Attributes");

    }

    public void navigate_To_AddNewUser() throws InterruptedException {
        Ele_Click(UM_AddNewUsr);
    }
    @Step("User Management Empty Error Message")
    public void verify_UM_Empty_ErrorMsg() throws InterruptedException {
        Ele_Click(UM_SaveBtn);
        Element_ToBe_Clickable(UM_MailNotification);
        Assert.assertEquals(UM_MailNotification.getText().trim(),Constant.Empty_UserName,"error message for username is been displayed");
        Assert.assertEquals(UM_FrstNmNotify.getText().trim(),Constant.Empty_FirstName,"error message for first is been displayed");
        Assert.assertEquals(UM_LastNmNotify.getText().trim(),Constant.Empty_LastName,"error message for lastname is been displayed");
        Assert.assertEquals(UM_OrgNotification.getText().trim(),Constant.Empty_Organisation,"error message for organization is  displayed");
        Assert.assertEquals(UM_AwsUserNotification.getText().trim(),Constant.Empty_AWS_UserName,"error message for AWS user is  displayed");
        screenshot();
        Ex.Pass_ScreenShot("User Management Empty Error Message");

    }


    @Step("User Mangement Incorrect Error Msg")
    public void verify_UM_Incorrect_ErrorMsg(String MailId, String FirstName , String LastName , String Org,String Awsusername) throws InterruptedException {
        UM_UserMailName.sendKeys(MailId);
        Assert.assertEquals(UM_MailNotification.getText(),Constant.Incorrect_UserName,"error message for invalid email id has been displayed");
        UM_FirstName.sendKeys(FirstName);
        Assert.assertEquals(UM_FrstNmNotify.getText(),Constant.Incorrect_FirstName,"error message for max 16 char for firstname has been displayed");
        UM_LastName.sendKeys(LastName);
        Assert.assertEquals(UM_LastNmNotify.getText(),Constant.Incorrect_LastName,"error message for max 16 char for lastname has been displayed");
        UM_Organization.sendKeys(Org);
        Assert.assertEquals(UM_OrgNotification.getText(),Constant.Incorrect_Organisation,"error message for max 16 char for organization has been displayed");
        UM_Aws_UserName.sendKeys(Awsusername);
        Assert.assertEquals(UM_AwsUserNotification.getText(),Constant.Incorrect_AWS_UserName,"error message for max 20 char for organization has been displayed");
        screenshot();
        Ex.Pass_ScreenShot("User Mangement Incorrect Error Msg");
    }
@Step("Create New User Under User Management")
    public void Create_UM_NewUser(String MailId, String FirstName , String LastName , String Org,String AWSUsername){
        UM_UserMailName.clear();
        UM_UserMailName.sendKeys(MailId);
        UM_FirstName.clear();
        UM_FirstName.sendKeys(FirstName);
        UM_LastName.clear();
        UM_LastName.sendKeys(LastName);
        UM_Organization.clear();
        UM_Organization.sendKeys(Org);
        UM_Aws_UserName.clear();
        UM_Aws_UserName.sendKeys(AWSUsername);
        Ele_Click(UM_SaveBtn);
        screenshot();
    }

    @Step("User Management Add Success Message")
    public void verify_UMCreate_SuccessMsg() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg, Constant.User_Creation_Msg);
        screenshot();
        Ex.Pass_ScreenShot("User Management Add Success Message");
    }

    @Step("Verify User Management Details")
    public void verify_UM_Table(String MailId, String FirstName , String LastName , String Org,String Awsusername) throws InterruptedException {
        normalwait(5000);
        UM_Search.clear();
        send_keys(UM_Search,MailId);
        normalwait(2000);
        Assert.assertEquals(MailId,driver.findElement(By.xpath("//mat-table/mat-row/mat-cell[1]")).getText(),"Username email reflecting on table");
        Assert.assertEquals(Awsusername,driver.findElement(By.xpath("//mat-table/mat-row/mat-cell[2]")).getText(),"first name reflecting on table");
        Assert.assertEquals(FirstName,driver.findElement(By.xpath("//mat-table/mat-row/mat-cell[3]")).getText(),"first name reflecting on table");
        Assert.assertEquals(LastName,driver.findElement(By.xpath("//mat-table/mat-row/mat-cell[4]")).getText(),"Last name reflecting on table");
        Assert.assertEquals(Org,driver.findElement(By.xpath("//mat-table/mat-row/mat-cell[5]")).getText(),"Company name reflecting on table");
        Ex.Pass_ScreenShot("Verify User Management Details");
    }

    public void verify_PlatformAdmin() throws InterruptedException {
        Ele_Click(UM_EditBtn);
        normalwait(2000);
        Assert.assertFalse(UM_PlatAdminCB.isSelected(),"By default PA checkbox is not checked");
        Ex.Pass_ScreenShot("verify_PlatformAdmin");
        Ele_Click(UM_CancelBtn);
        wait.until(ExpectedConditions.visibilityOf(UM_EditBtn));

    }

    public void verify_UM_Edit_SuccessMsg(){
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg, Constant.Delete_User_SuccessMsg);

    }
    public void verify_UMDelete_SuccessMsg(){
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg,Constant.Delete_User_SuccessMsg);
        Ex.Pass_ScreenShot("User Management Delete Message");

    }

    @Step("Edit User Details")
    public void UM_EditUser(String Mailid,String FirstName,String LastName,String Organization,String awsusername){
        normalwait(3000);
        send_keys(UM_Search,Mailid + Keys.ENTER);
    Ele_Click(UM_EditBtn);
    Assert.assertFalse(UM_UserMailName.isEnabled(),"disabled email textbox is in disabled condition As Expected");
    UM_FirstName.clear();
    send_keys(UM_FirstName,FirstName);
    UM_LastName.clear();
    send_keys(UM_LastName,LastName);
    UM_Organization.clear();
    send_keys(UM_Organization,Organization);
    if(UM_PlatAdminCB.isSelected()){
        System.out.println("User is a platform admin");
    }
    else{
        System.out.println("User is not a platform admin");
        Ele_Click(UM_PlatAdminCB);
        System.out.println("checked Radio button of PA");
        Ele_Click(UM_PlatAdminCB);
        System.out.println("Unchecked Radio button of PA");
    }
        Ele_Click(UM_SaveBtn);

    }

    @Step("User Management Edit Success Message")
    public void verifyEdited_SuccessMsg() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        //Assert.assertEquals(Success_Msg,"User details edited successfully","User details edited successfully");
        screenshot();
    }

    @Step("User Management Delete Message")
    public void UM_DeleteUser(String MailId) throws InterruptedException {
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOf(UM_EditBtn));
        send_keys(UM_Search,MailId);
        Ele_Click(UM_EditBtn);
        Ele_Click(UM_Delete);
        Ele_Click(UM_Yes);
        verify_UM_Edit_SuccessMsg();
        normalwait(2000);
        verify_UMDelete_SuccessMsg();
        screenshot();

    }

}
