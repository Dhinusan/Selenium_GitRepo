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
public class TC64_Verify_DM_PO_DU1_DU2_Project_Listviewoptions extends base {


    @Test(dataProvider = "getData", description = "Verify the user able to view the project management activates")
    public void TC64_PROJMGMT_DM_PO_DU1_DU2_Project_ListView_Options(String username, String password, String Role) throws IOException, InterruptedException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        ExtentManager Ex = new ExtentManager();

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify the user able to view the project management activates").
                assignAuthor(Constant.Author).assignCategory(Constant.Project_Management);

        LP.login(username, password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToProj_Management();
        pmp.Verify_ProjOverview_Headers();
        pmp.select_Project_OverviewElipse(Constant.UI_Project1);
        pmp.verify_projectelipse_access(Role);
        pmp.verify_PO_SortFunction();
        LP.LogOut();
    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},
                {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2},
                {Constant.Privacy_Officer_Name, Constant.Password, Constant.Privacy_Officer}
        };
        return data;
    }


}




