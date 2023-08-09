
package com.hcl.redcap;

import Resources.Constant;
import Resources.ExtentManager;
import Resources.base;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.redcapPage.AWS;
import com.redcapPage.Login_LandingPage;


import java.util.Map;


public class TC221_Reset_AWS_Pw_UI extends base {

    @Test(dataProvider = "getData", description = "TCIT_USRMGMT_Admin_DM_DUI_DUII_PO_Reset_AWS_Password_01")
    @Description("REDCAP UI - Verify all users are able to reset AWS console password from REDCAP UI")
    public void TCIT_USRMGMT_Admin_DM_DUI_DUII_PO_Reset_AWS_Password_01(String Username, String Password, String Role, String RedcapUsername) throws InterruptedException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        AWS aws = new AWS(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify all users are able to reset AWS console password from REDCAP UI").
                assignAuthor(Constant.Author).assignCategory(Constant.User_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Incorrect_Password = map.get("Incorrect_Password");
        String[] Incrct_Password = Incorrect_Password.split(",");
        String PassWord = map.get("Password");
        String[] PassWrd = PassWord.split(",");
        String AWS_Role = map.get("AWS_Role");


        LP.login(RedcapUsername, Password, Role);
        LP.verify_Lp_Attributes();
        LP.navigate_to_aws_pw_reset();
        LP.validate_Aws_pwreset_fields();
        LP.navigate_to_aws_pw_reset();
        LP.validate_empty_pw_Errormsg();
        LP.validate_different_pw_Errormsg(PassWrd[0], PassWrd[1]);
        LP.val_no_Alphabets_Number_SpeicalChar(Incrct_Password[0], Incrct_Password[1], Incrct_Password[2]);
        LP.validate_min_max_pwLength(Incrct_Password[3], Incrct_Password[4]);
        LP.validate_aws_pwreset(Password);
        LP.LogOut();
        aws.AWS_Login(Username, Password);
        aws.select_role("REUC1_MAY191684481557461_PROJECTADMIN", Role);


    }


    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Aws_Admin_UserName, Constant.Password, Constant.Admin, Constant.Admin_Name},
              // {Constant.Aws_DM_UserName, Constant.Password, Constant.Data_Manager, Constant.Data_Manager_Name},
               // {Constant.Aws_DU1_UserName, Constant.Password, Constant.Data_User1, Constant.Data_User1_Name},
                /*{Constant.Aws_DU2_UserName, Constant.Password, Constant.Data_User2, Constant.Data_User2_Name},
                {Constant.Aws_PO_UserName, Constant.Password, Constant.Privacy_Officer, Constant.Privacy_Officer_Name},*/

        };
        return data;
    }


}
