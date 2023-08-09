
package com.hcl.redcap;

import Resources.Constant;
import Resources.ExtentManager;
import Resources.base;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.redcapPage.Login_LandingPage;


import java.util.Map;

import com.redcapPage.ProjMgmtPage;


public class TC214_Verify_Invalid_Projectname_ErrorMsg extends base {

    @Test(dataProvider = "getData", description = "Project Management: Verify project creation fails when the user provides invalid project name.")
    public void TC214_VerifyProjectCreation_InvalidName(String Username, String Password, String Role, String RedcapUsername) throws InterruptedException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() +"_"+ Role, "Project Management: Verify project creation fails when the user provides invalid project name.").
                assignAuthor(Constant.Author).assignCategory(Constant.Project_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Incorrect_Password = map.get("Incorrect_Password");
        String[] Incrct_Password = Incorrect_Password.split(",");





        LP.login(RedcapUsername, Password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToProj_Management();
        pmp.select_Project_OverviewElipse(Constant.UI_Project1);
        pmp.verify_invalidProjectname(Incrct_Password[0],Incrct_Password[1],Incrct_Password[2],Incrct_Password[3],Incrct_Password[4]);
        LP.LogOut();

    }


    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Aws_Admin_UserName,Constant.Password,Constant.Admin,Constant.Admin_Name},

        };
        return data;
    }


}
