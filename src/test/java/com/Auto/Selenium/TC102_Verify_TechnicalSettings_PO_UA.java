package com.hcl.redcap;

import Resources.*;
import io.qameta.allure.*;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.ProjMgmtPage;

import java.io.IOException;
import java.util.Map;


@Listeners({ListenerClass.class})
public class TC102_Verify_TechnicalSettings_PO_UA extends base {

    @Test(dataProvider = "getData")
    @Description("verify roles & privileges for privacy officer & unassigned user in Project Management - Technical Settings page")
    public void TC102_VerifyTechnicalSettings_PO_Unassigner_User(String username, String password, String Role) throws IOException, InterruptedException, ParseException {

        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        Login_LandingPage LP = new Login_LandingPage(driver);
        ExtentManager Ex = new ExtentManager();

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() +"_"+ Role, "TC102_VerifyTechnicalSettings_PO_Unassigner_User").
                assignAuthor(Constant.Author).assignCategory(Constant.User_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());

        if (Role.equalsIgnoreCase(Constant.Privacy_Officer)) {
            LP.login(username, password, Role);
            LP.verify_Lp_Attributes();
            LP.navigateToProj_Management();
            pmp.select_Project_OverviewElipse(Constant.UI_Project1);
            pmp.verify_PO_TechnicalSettings();
            LP.LogOut();
        } else if (Role.equalsIgnoreCase(Constant.Unassigned_User)) {
            LP.login(username, password, Role);
            LP.verify_Lp_Attributes();
            LP.navigateToProj_Management();
            pmp.validate_PO_UnassignedUser();
            LP.LogOut();
        }
    }


    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Privacy_Officer_Name, Constant.Password, Constant.Privacy_Officer},
                {Constant.Unassigned_User_Name, Constant.Password, Constant.Unassigned_User}
        };
        return data;
    }


}
