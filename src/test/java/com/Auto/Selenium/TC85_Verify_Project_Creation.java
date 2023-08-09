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
public class TC85_Verify_Project_Creation extends base {


    @Test(dataProvider = "getData", description = "Verify the user able to view the project list status")
    public void TC85_User_ProjectCreation_View(String username, String password, String Role) throws IOException, InterruptedException {

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String UI_Project = map.get("UI_Project");
        String Inputs = map.get("Inputs");
        String[] Input = Inputs.split(",");

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        ExtentManager Ex = new ExtentManager();

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify the user able to view the project list status").
                assignAuthor(Constant.Author).assignCategory(Constant.Project_Management);


        LP.login(username, password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToProj_Management();
        pmp.Create_Porject(UI_Project, Input[0], Input[1], Input[2]);
        pmp.verify_ProjectCreate_SuccessMsg();
        pmp.verify_Project_table(UI_Project, Input[0], Input[2]);
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




