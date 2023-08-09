
package com.hcl.redcap;

import Resources.*;
import com.redcapPage.*;
import io.qameta.allure.Description;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;

@Listeners({ListenerClass.class})
public class TC283_MaxInstanceCreation_EC2_Rstudio_Jupyter extends base {

    @Test(dataProvider = "getData")
    @Description("Verify As a REDCAP Platform admin, I want to create max instance in DA settings ")
    public void TCIT_ADMIN_DM_DU1_DU2_EC2InstancewithRSTUDIO_Jupyter_Max_Instance_Creation_DASettings_01(String username, String password, String Role) throws IOException, InterruptedException, ParseException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        DataMgmtPage dm = new DataMgmtPage(driver);
        TechnicalSettingsPage tcp = new TechnicalSettingsPage(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify As a REDCAP Platform admin, I want to create max instance in DA settings").
                assignAuthor(Constant.Author).assignCategory(Constant.Cataloge_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String EC2_Instance = map.get("EC2_Instance");
        String EC2_Size = map.get("EC2_Size");
        String Disable_EC2_Instance = map.get("FirstName");
        String Disable_EC2_Size = map.get("LastName");
        String Inputs = map.get("Inputs");
        String[] EC2 =Inputs.split(">");
        String EC2_Instance_2 = map.get("FileName");
        String EC2_Size_2 = map.get("FileName2");

        LP.login(username, password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToProj_Management();
        pmp.select_Project_OverviewElipse(Constant.UI_Project2);
        tcp.navigate_to_infrasettings();
       // tcp.change_rstuido_status_disbaled();
        //tcp.verify_rstudio_disabled_da();
        tcp.change_jupyter_rstudio_hpc_status_enabled();
        pmp.Navigate_To_Project_AppIcon();
        pmp.select_Project_OverviewElipse(Constant.UI_Project2);
        tcp.navigate_to_technicalsettings();
        tcp.navigate_to_analyticsettings();
        // 2 Jupyter 3 Standalone
        tcp.request_ec2_instance(EC2_Instance,EC2_Size);
        tcp.verify_req_EC2instance_disabled();
        tcp.navigate_to_analyticsettings();
        tcp.verify_rstudio_btn_attributes();
        tcp.verify_create_rstudio();
        tcp.verify_rstudio_profile_creation_UI();
        tcp.validate_EC2Instance_Info_AnaSettings(Disable_EC2_Size,Disable_EC2_Instance,username);
        tcp.verify_terminate_instance_AnaSettings();
        tcp.request_ec2_instance(EC2[0],EC2[1]);
        tcp.verify_req_EC2instance_disabled();
        tcp.terminate_all_instance_analytics_settings();
        pmp.Navigate_To_Project_AppIcon();
        pmp.select_Project_OverviewElipse(Constant.UI_Project2);
        tcp.navigate_to_infrasettings();
        tcp.change_rstuido_status_disbaled();
        tcp.verify_rstudio_disabled_da();
        tcp.change_jupyter_rstudio_hpc_status_enabled();
        pmp.Navigate_To_Project_AppIcon();
        pmp.select_Project_OverviewElipse(Constant.UI_Project2);
        tcp.navigate_to_technicalsettings();
        tcp.navigate_to_analyticsettings();
        //3 jupyter 2 standalone 1 RStudio
        tcp.request_ec2_instance(EC2_Instance_2,EC2_Size_2);
        tcp.verify_req_EC2instance_disabled();
        tcp.validate_EC2Instance_Info_AnaSettings(Disable_EC2_Size,Disable_EC2_Instance,username);
        tcp.verify_terminate_instance_AnaSettings();
        tcp.verify_rstudio_btn_attributes();
        tcp.verify_create_rstudio();
        tcp.verify_rstudio_profile_creation_UI();
        tcp.request_ec2_instance(EC2[0],EC2[1]);
        tcp.verify_req_EC2instance_disabled();
        tcp.terminate_all_instance_analytics_settings();
        pmp.Navigate_To_Project_AppIcon();
        pmp.select_Project_OverviewElipse(Constant.UI_Project2);
        //1 rstudio 3 jupyter 2 EC2
        tcp.navigate_to_infrasettings();
        tcp.change_rstuido_status_disbaled();
        tcp.verify_rstudio_disabled_da();
        tcp.change_jupyter_rstudio_hpc_status_enabled();
        pmp.Navigate_To_Project_AppIcon();
        pmp.select_Project_OverviewElipse(Constant.UI_Project2);
        tcp.navigate_to_technicalsettings();
        tcp.navigate_to_analyticsettings();
        tcp.verify_rstudio_btn_attributes();
        tcp.verify_create_rstudio();
        tcp.verify_rstudio_profile_creation_UI();
        tcp.request_ec2_instance(EC2_Instance_2,EC2_Size_2);
        tcp.verify_req_EC2instance_disabled();
        tcp.validate_EC2Instance_Info_AnaSettings(Disable_EC2_Size,Disable_EC2_Instance,username);
        tcp.verify_terminate_instance_AnaSettings();
        tcp.request_ec2_instance(EC2[0],EC2[1]);
        tcp.verify_req_EC2instance_disabled();
        tcp.terminate_all_instance_analytics_settings();




    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
               /* {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},

                {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2},
                {Constant.Privacy_Officer_Name, Constant.Password, Constant.Privacy_Officer}*/
        };
        return data;
    }


}





