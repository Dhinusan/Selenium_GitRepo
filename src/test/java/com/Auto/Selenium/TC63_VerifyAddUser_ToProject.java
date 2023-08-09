package com.hcl.redcap;

import Resources.*;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.ProjMgmtPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;

@Listeners(ListenerClass.class)
public class TC63_VerifyAddUser_ToProject extends base {


    @Test(dataProvider = "getData", description = "Add Assigned User And Check For Email Verification")
    public void TC63_Admin_Assign_Add_Edit_User_EmailVerify(String username, String password, String Role) throws IOException, InterruptedException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        ExtentManager Ex = new ExtentManager();

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String User1 = map.get("User1");
        String User2 = map.get("User2");
        String UI_Role = map.get("UI_Role");

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Add Assigned User And Check For Email Verification").
                assignAuthor(Constant.Author).assignCategory(Constant.Project_Management);


        LP.login(username, password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToProj_Management();
        pmp.select_Project_OverviewElipse(Constant.UI_Project1);
        pmp.Navigate_To_ProjecAccess();
        pmp.verify_MultiUserSelectCB(User1);
        pmp.Select_Associate_Users(User2, UI_Role);
        pmp.verify_AssocUsers_Add_SuccessMsg(Constant.Add_User_SuccessMsg);
        pmp.verify_new_user(User2);
        driver.navigate().refresh();
        pmp.Navigate_To_Project_AppIcon();
        pmp.select_Project_OverviewElipse(Constant.UI_Project1);
        pmp.Navigate_To_ProjecAccess();
        pmp.Delete_New_User(User2);
        pmp.verify_AssocUsers_Delete_SuccessMsg(Constant.Delete_User_SuccessMsg);
        //Pending With Email Validation Steps
        LP.LogOut();
    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin}
        };
        return data;
    }


}




