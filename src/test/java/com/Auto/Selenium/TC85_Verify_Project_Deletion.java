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
public class TC85_Verify_Project_Deletion extends base {


    @Test(dataProvider = "getData", description = "Verify the user able to view the project list status And Delete It")
    public void TC85_User_ProjectDeletion_View(String username, String password, String Role) throws IOException, InterruptedException {

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String UI_Project = map.get("UI_Project");

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        ExtentManager Ex = new ExtentManager();

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify the user able to Delete the project").
                assignAuthor(Constant.Author).assignCategory(Constant.Project_Management);


        LP.login(username, password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToProj_Management();
        pmp.delete_project(UI_Project);
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




