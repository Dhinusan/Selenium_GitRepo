package com.hcl.redcap;

import Resources.*;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.ProjMgmtPage;
import com.redcapPage.TechnicalSettingsPage;

import java.io.IOException;
import java.util.Map;

@Listeners({ListenerClass.class})
public class TC101_Verify_TechnicalSettings_DU1_DU2 extends base {

    @Test(dataProvider = "getData")
    @Severity(SeverityLevel.MINOR)
    @Description("verify roles and privileges for Data User I & Data User II in Project Management - Technical Settings page")
    @Epic("User management")

    public void  TC101_TechnicalSettings_Access_DU1_DU2(String Username,String Password,String Role,String AWS_Name) throws IOException, InterruptedException
    {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        TechnicalSettingsPage tsp = new TechnicalSettingsPage(driver);
        ExtentManager Ex = new ExtentManager();

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() +"_"+ Role, "verify roles and privileges for Data User I & Data User II in Project Management - Technical Settings page").
                assignAuthor(Constant.Author).assignCategory(Constant.User_Management);

        Map<String,String> map =base.ExcelData.get(getClass().getSimpleName());
        String EC2_Instance = map.get("EC2_Instance");
        String EC2_Size = map.get("EC2_Size");

        //Need to Update Aws Container & Source URl & Aws Link As per Currently used project
        LP.login(Username,Password,Role);
        LP.verify_Lp_Attributes();
        LP.navigateToProj_Management();
        pmp.select_Project_OverviewElipse(Constant.UI_Project1);
        tsp.navigate_to_technicalsettings();
        tsp.verify_techincal_settings();
        tsp.verify_hpc_settings(AWS_Name);
        tsp.request_ec2_instance_HPC(EC2_Instance,EC2_Size);
        tsp.validate_EC2Instance_Info(EC2_Size,EC2_Instance,Username);
        tsp.verify_terminate_instance_HPCSettings();
        LP.LogOut();

    }
    @DataProvider
    public Object[][] getData()
    {
        Object[][] data =new Object[][] {
                {Constant.Data_User1_Name,Constant.Password,Constant.Data_User1,Constant.Aws_DU1_UserName},
                {Constant.Data_User2_Name,Constant.Password,Constant.Data_User2,Constant.Aws_DU2_UserName}
        };
        return data;
    }

}
