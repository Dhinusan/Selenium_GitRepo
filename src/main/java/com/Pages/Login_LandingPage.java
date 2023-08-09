package com.redcapPage;

import Resources.Common_Functions;
import Resources.Constant;
import Resources.ExtentManager;
import com.aventstack.extentreports.ExtentTest;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;


public class Login_LandingPage extends Common_Functions {
    WebDriverWait wait = new WebDriverWait(driver, 20);
    ExtentManager Ex = new ExtentManager();

    public Login_LandingPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Email address']")
    public WebElement mail_address;

    @FindBy(xpath = "//span[text()='Continue']")
    public WebElement Continue_Btn;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement Pass_word;

    @FindBy(xpath = "//span[text()='Sign in']")
    public WebElement Sign_In;

    @FindBy(xpath = "//figcaption[contains(text(),'Project Management')]")
    public WebElement Project_Management_Icon;

    @FindBy(xpath = "//*[text()='Project Overview']")
    public WebElement Project_Overview;

    @FindBy(xpath = " //figcaption[contains(text(),'User Management')]")
    public WebElement User_Management_Icon;

    @FindBy(xpath = " //span[@class='page-title']")
    public WebElement LandingPage_Title;

    @FindBy(xpath = " //header[@class='divider-shadow']")
    public WebElement LanPage_Header;

    @FindBy(xpath = " //span[contains(text(),'App Library')]")
    public WebElement LanPage_SubTitle;

    @FindBy(xpath = "//mat-icon[contains(text(),'account_circle')]")
    public WebElement User_Icon;

    @FindBy(xpath = "//span[@class='logout-image']")
    public WebElement LogOut;

    @FindBy(xpath = "//a[text()='AWS password reset']")
    public WebElement AWS_Pw_Reset;

    @FindBy(xpath = "//figcaption[contains(text(),'Data')]")
    public WebElement Catalog_Management_Icon;

    @FindBy(xpath = "//span[contains(text(),'Catalog Management (Raw Files)')]")
    public WebElement Raw_Title;

    @FindBy(xpath = "//span[contains(text(),'Save')]")
    public WebElement Save_btn;
    @FindBy(xpath = "//simple-snack-bar[contains(@class,'mat-simple-snackbar')]/span")
    public WebElement Notification_Msg;

    @FindBy(xpath = "//mat-icon[text()='account_circle']")
    public WebElement Profile_Icon;

    @FindBy(xpath = "//a[text()='AWS password reset']")
    public WebElement Aws_PassWord_Reset;

    @FindBy(xpath = "//span[text()='REDCAP AWS Console Password Reset']")
    public WebElement Aws_PW_Reset_Header;

    @FindBy(xpath = "//input[@formcontrolname='pwd']/..//li")
    public WebElement New_Pw_ErrorMsg;

    @FindBy(xpath = "//input[@formcontrolname='confirmpwd']/..//div")
    public WebElement Retype_Pw_ErrorMsg;

    @FindBy(xpath = "//input[@formcontrolname='confirmpwd']")
    public WebElement Retype_Pw_Input;

    @FindBy(xpath = "//input[@formcontrolname='pwd']")
    public WebElement New_Pw_Input;

    @FindBy(xpath = "//span[text()='Cancel']")
    public WebElement Cancel_btn;

    @FindBy(xpath = "//div[text()='No data found']")
    public WebElement No_Data_Found;

    @FindBy(xpath = " //figcaption[contains(text(),'User Management')]")
    public List<WebElement> UM_Icon_Size;

    @Step("Verification Of User Management Access")
    public void verify_um_access(String User){
        int count =UM_Icon_Size.size();
        Assert.assertEquals(count,0);
        Ex.Pass_ScreenShot("User Management Not Displayed To "+User+" AS Expected ");
        screenshot();
    }

    @Step("Validate Landing Page Attributes")
    public void verify_Lp_Attributes() {
        normalwait(2000);
        wait.until(ExpectedConditions.visibilityOf(LanPage_SubTitle));
        Assert.assertEquals(LandingPage_Title.getText(), "  REDCAP Platform");
        Assert.assertEquals(LanPage_SubTitle.getText(), " | App Library");
        Assert.assertTrue(LanPage_Header.isDisplayed());
        Assert.assertTrue(LanPage_SubTitle.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Landing Page Attributes Are Verified");


    }

    @Step("No Data Found is Displayed For Unassigned User As Expected")
    public void verify_UAUser_projectmanagement(){
        Ele_Click(Project_Management_Icon);
        normalwait(3000);
        Assert.assertTrue(No_Data_Found.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("No Data Found is Displayed For Unassigned User As Expected");
    }

    @Step("Navigate To Project Management")
    public void navigateToProj_Management() {
        Ele_Click(Project_Management_Icon);
        Element_ToBe_Clickable(Project_Overview);
        page_loader();
        screenshot();
        Ex.Pass_ScreenShot("Navigated To Project Management");
    }

    ExtentTest Logger;

    @Test(description = "Login")
    public void login(String MailAddress, String Password, String Role) throws InterruptedException {
        logger.info("Logged In User:"+MailAddress+ " , "+"Logged In Role: "+Role);
        try {
            if (!driver.getCurrentUrl().contains(Constant.Url)) {
                driver.get(Constant.Euc1_Url);
            }
            Element_ToBe_Clickable(mail_address);
            send_keys(mail_address, MailAddress);
            Ele_Click(Continue_Btn);
            Ele_Click(Continue_Btn);
            Element_ToBe_Clickable(Pass_word);
            send_keys(Pass_word, Password);
            Ele_Click(Sign_In);
            screenshot();
            Ex.Pass_ScreenShot("User Logged In As " + Role);
        }
        catch (Exception e){
           Ex.Extent_Fail_Info("Test Case Was Failed at LoginStep");
        }

    }

    public void navigateToUser_Management() {
        Ele_Click(User_Management_Icon);
    }

    public void LogOut() throws InterruptedException {
        jsclick(User_Icon);
        page_loader();
        jsclick(LogOut);
        Ex.Pass_ScreenShot("user Logged Out");
        normalwait(2000);
    }

    public void Navigate_To_Aws_Pw_Reset() throws InterruptedException {
        Ele_Click(User_Icon);
        Thread.sleep(2000);
        Ele_Click(AWS_Pw_Reset);

    }

    public void navigateToCatlog_Management() throws InterruptedException {
        Element_ToBe_Clickable(Catalog_Management_Icon);
        jsclick(Catalog_Management_Icon);
        Element_ToBe_Clickable(Raw_Title);
        System.out.println("Clicked on Data catalog on landing page");
    }


    @Step("Navigate to AWS Pw Reset")
    public void navigate_to_aws_pw_reset() {
        Ele_Click(Profile_Icon);
        Ele_Click(Aws_PassWord_Reset);
        screenshot();
    }

    @Step("Validate Password Reset Fields")
    public void validate_Aws_pwreset_fields() {
        Assert.assertTrue(Aws_PW_Reset_Header.isDisplayed());
        System.out.println("REDCAP AWS Console Password Reset Is Displayed As Expected ");
        Assert.assertTrue(New_Pw_Input.isDisplayed());
        System.out.println("New Password Is Displayed As Expected ");
        Assert.assertTrue(Retype_Pw_Input.isDisplayed());
        System.out.println("Reconfirm Password Is Displayed As Expected ");
        screenshot();
        Ele_Click(Cancel_btn);
        Ex.Pass_ScreenShot("Validate Password Reset Fields");
    }

    @Step("Validate Empty Pw Error Msg")
    public void validate_empty_pw_Errormsg() {
        Ele_Click(Save_btn);
        Assert.assertEquals(New_Pw_ErrorMsg.getText().trim(), Constant.Empty_Password);
        System.out.println("Provide password Error Msg Displayed As Expected");
        Assert.assertEquals(Retype_Pw_ErrorMsg.getText().trim(), Constant.Empty_Confirm_Password);
        System.out.println("Provide confirm password Error Msg Displayed As Expected");
        screenshot();
        Ex.Pass_ScreenShot("Validate Empty Pw Error Msg");
    }

    @Step("Validate Different Pw Error Msg")
    public void validate_different_pw_Errormsg(String Pw1, String Pw2) {
        send_keys(New_Pw_Input, Pw1);
        send_keys(Retype_Pw_Input, Pw2);
        Ele_Click(Save_btn);
        Assert.assertEquals(Retype_Pw_ErrorMsg.getText().trim(), Constant.Different_Password);
        screenshot();
        Ex.Pass_ScreenShot("Validate Different Pw Error Msg");
        System.out.println("passwords didn't match Error Msg Displayed As Expected for different pw");
        New_Pw_Input.clear();
        Retype_Pw_Input.clear();
    }

    @Step("Validate No Alphabets,Numbers,Special Character")
    public void val_no_Alphabets_Number_SpeicalChar(String Pw1, String Pw2, String Pw3) {
        send_keys(New_Pw_Input, Pw1);
        Assert.assertEquals(New_Pw_ErrorMsg.getText().trim(), Constant.No_Alphabet_Pw);
        Ex.Pass_ScreenShot("Validate No Alphabets");
        screenshot();
        New_Pw_Input.clear();
        send_keys(New_Pw_Input, Pw2);
        Assert.assertEquals(New_Pw_ErrorMsg.getText().trim(), Constant.No_Number_Pw);
        screenshot();
        Ex.Pass_ScreenShot("Validate No Numbers");
        New_Pw_Input.clear();
        send_keys(New_Pw_Input, Pw3);
        Assert.assertEquals(New_Pw_ErrorMsg.getText().trim(), Constant.No_SpecialCharacter_Pw);
        screenshot();
        Ex.Pass_ScreenShot("Validate No Special Character");
        New_Pw_Input.clear();
    }

    @Step("Validate Max - Min Pw Length")
    public void validate_min_max_pwLength(String Pw1, String Pw2) {
        send_keys(New_Pw_Input, Pw1);
        Assert.assertEquals(New_Pw_ErrorMsg.getText().trim(), Constant.Pw_Min_Msg);
        Ex.Pass_ScreenShot("Validate Min Password length");
        New_Pw_Input.clear();
        send_keys(New_Pw_Input, Pw2);
        Assert.assertEquals(New_Pw_ErrorMsg.getText().trim(), Constant.Pw_Max_Msg);
        Ex.Pass_ScreenShot("Validate Max Password length");
        New_Pw_Input.clear();
    }


    @Step("Validate Password Reset")
    public void validate_aws_pwreset(String Pw) {
        send_keys(New_Pw_Input, Pw);
        send_keys(Retype_Pw_Input, Pw);
        Ele_Click(Save_btn);
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg, Constant.AWS_Pw_Reset);
        Ex.Pass_ScreenShot("Validate Password Reset");
        screenshot();
    }

    



}
