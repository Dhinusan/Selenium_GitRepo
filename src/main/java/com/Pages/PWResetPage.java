package com.redcapPage;

import Resources.Common_Functions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PWResetPage extends Common_Functions {
    WebDriverWait wait = new WebDriverWait(driver,5);
    public PWResetPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//span[text()='REDCAP AWS Console Password Reset']")
    public WebElement Pw_Reset_Header;

    @FindBy(xpath = "//span[contains(text(),'Cancel')]")
    public WebElement Cancel_Btn;

    @FindBy(xpath = "//mat-label[text()='New Password*']/../../..//input")
    public WebElement Pw_Input;

    @FindBy(xpath = "//mat-label[text()='Re-type Password*']/../../..//input")
    public WebElement Retype_PwInput;

    @FindBy(xpath = "//p[text()='Username']")
    public WebElement UserName_Field;

    @FindBy(xpath = "//span[contains(text(),'Save')]")
    public WebElement Save_Btn;

    @FindBy(xpath = "//li[contains(text(),'Provide password')]")
    public WebElement Empty_Error1;

    @FindBy(xpath = "//div[contains(text(),'Provide confirm password')]")
    public WebElement Empty_Error2;


    public void Validate_ResetPw_Attributes(){
        Assert.assertEquals(Pw_Reset_Header.getText(),"REDCAP AWS Console Password Reset'");
        Assert.assertTrue(UserName_Field.isDisplayed());
        Assert.assertTrue(Pw_Input.isDisplayed());
        Assert.assertTrue(Retype_PwInput.isDisplayed());
        System.out.println("AWS Fields Are Validated");
    }

    public void Validate_CancelBtn(String Pw , String Retype_Pw){
        send_keys(Pw_Input,Pw);
        send_keys(Pw_Input,Retype_Pw);
        Ele_Click(Cancel_Btn);
    }

    public void Validate_Empty_Pw() throws InterruptedException {
        Ele_Click(Save_Btn);
        Thread.sleep(1000);
        Assert.assertTrue(Empty_Error1.isDisplayed());
        Assert.assertTrue(Empty_Error2.isDisplayed());
        System.out.println(" Empty Error Notifications Are Printed As Expected");

    }

    public void Validate_Invalid_Pw(String Password){
       String[] Pw = Password.split(",");

        for(int i=0;i<Pw.length;i++){
            send_keys(Pw_Input,Pw[i]);
            String regex = "^(?=.*[0-9])"+ "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "{8,16}$";
            Pattern p = Pattern.compile(regex);
            Matcher m =p.matcher(Password);
            System.out.println(m);

        }

    }
}
