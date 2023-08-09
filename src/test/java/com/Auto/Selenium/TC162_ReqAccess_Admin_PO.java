package com.hcl.redcap;

import Resources.*;
import com.redcapPage.CatalogManagementPage;
import com.redcapPage.Login_LandingPage;
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
public class TC162_ReqAccess_Admin_PO extends base


{

    @Test(dataProvider = "getData") @Severity(SeverityLevel.CRITICAL) @Story("Project management")
    @Description("Verify the users  Admin and PO is able to view the  Request for  Access option")
    public void  TC162_Verify_Admin_PO_AcccessRequest(String username,String password,String Role) throws IOException, InterruptedException




    {

        Login_LandingPage LP = new Login_LandingPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ExtentManager Ex = new ExtentManager();

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() +"_"+ Role, "TC162_Verify_Admin_PO_AcccessRequest").
                assignAuthor(Constant.Author).assignCategory(Constant.Cataloge_Management);

        Map<String,String> map =base.ExcelData.get(getClass().getSimpleName());

        LP.login(username,password,Role);
        LP.verify_Lp_Attributes();
        LP.navigateToCatlog_Management();
        CMP.SelectCases_ProjId(Constant.UI_Project2);
        CMP.navitocasetab();
        CMP.verify_roleAccess(Role,Constant.UI_Project2);
        LP.LogOut();

    }

    @DataProvider
    public Object[][] getData()
    {
        Object[][] data =new Object[][] {
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
                {Constant.Privacy_Officer_Name, Constant.Password, Constant.Privacy_Officer}
        };
        return data;
    }






}




