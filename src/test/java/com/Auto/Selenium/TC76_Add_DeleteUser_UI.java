package com.hcl.redcap;

import Resources.*;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.ProjMgmtPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

@Listeners({ListenerClass.class})
public class TC76_Add_DeleteUser_UI extends base {

    @Test(dataProvider = "getData")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the user able to add user and delete user from the redcap platform UI")
    @Story("Project management")
    public void TC76_PROJMGMT_ADMIN_DM_Project_Add_Delete_User(String username, String password, String Role) throws IOException, InterruptedException {
        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pm = new ProjMgmtPage(driver);
        ExtentManager Ex = new ExtentManager();

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String User1 = map.get("User1");
        String UI_Role = map.get("UI_Role");

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify the user able to add user and delete user from the redcap platform UI")
                .assignAuthor(Constant.Author).assignCategory(Constant.Project_Management);

        LP.login(username, password, Role);
        LP.navigateToProj_Management();
        pm.select_Project_OverviewElipse(Constant.UI_Project1);
        pm.Navigate_To_ProjecAccess();
        pm.Select_Associate_Users(User1, UI_Role);
        pm.verify_AssocUsers_Add_SuccessMsg(Constant.Add_User_SuccessMsg);
        pm.Delete_New_User(User1);
        pm.verify_AssocUsers_Delete_SuccessMsg(Constant.Delete_User_SuccessMsg);
        LP.LogOut();
    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
                {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager}
        };
        return data;
    }
}




