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
public class TC146_InstanceTermination_DA extends base {

    @Test(dataProvider = "getData")
    @Severity(SeverityLevel.MINOR)
    @Description("1. Verify REDCAP Platform Admin and Data Manager should be able to terminate any instance belonging to a project.\n" +
            "2. Verify Data User I and Data User II should only be able to terminate the instances that they have requested for.")
    @Epic("User management")

    public void TC146_Data_Analytics_System_Admin_DM_DU1_DU2_ViewEC2InstanceInfo(String Username, String Password, String Role, String AWS_Name) throws IOException, InterruptedException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        TechnicalSettingsPage tsp = new TechnicalSettingsPage(driver);
        ExtentManager Ex = new ExtentManager();

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "1. Verify REDCAP Platform Admin and Data Manager should be able to terminate any instance belonging to a project.\n" +
                        "2. Verify Data User I and Data User II should only be able to terminate the instances that they have requested for.").
                assignAuthor(Constant.Author).assignCategory(Constant.User_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String EC2_Instance = map.get("EC2_Instance");
        String EC2_Size = map.get("EC2_Size");

        if (Role.equalsIgnoreCase(Constant.Admin)) {
            LP.login(Username, Password, Role);
            LP.verify_Lp_Attributes();
            LP.navigateToProj_Management();
            pmp.select_Project_OverviewElipse(Constant.UI_Project1);
            tsp.navigate_to_technicalsettings();
            tsp.verify_techincal_settings();
            tsp.navigate_to_analyticsettings();
            tsp.verify_EC2InstanceHeaders_Aanlytics();
            tsp.request_ec2_instance(EC2_Instance, EC2_Size);
            tsp.validate_EC2Instance_Info_AnaSettings(EC2_Size, EC2_Instance, Username);
            LP.LogOut();
        } else {
            LP.login(Username, Password, Role);
            LP.verify_Lp_Attributes();
            LP.navigateToProj_Management();
            pmp.select_Project_OverviewElipse(Constant.UI_Project1);
            tsp.navigate_to_technicalsettings();
            tsp.verify_techincal_settings();
            tsp.navigate_to_analyticsettings();
            tsp.verify_EC2InstanceHeaders_Aanlytics();
            tsp.verify_terminate_otheruser_Instance(Role);
            LP.LogOut();
        }


    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin, Constant.Aws_Admin_UserName},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1, Constant.Aws_DU1_UserName},
                {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2, Constant.Aws_DU2_UserName},
                {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager, Constant.Aws_DM_UserName}
        };
        return data;
    }

}
