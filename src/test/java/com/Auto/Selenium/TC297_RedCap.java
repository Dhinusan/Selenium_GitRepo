
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
public class TC297_RedCap extends base {

    @Test(dataProvider = "getData")
    @Description("Verify as a As a REDCAP Platform Admin, Data Manager, Data User I or Data User II, I want to access RStudio from the project's DA system settings page, so that I can use RStudio environment for my analysis")
    public void TCIT_Allow_additionalfeatures_or_services_to_be_integrated_when_the_project_is_being_created_or_later_through_UI_access_RStudio_from_the_project_DA_system_settingspage(String username, String password, String Role) throws IOException, InterruptedException, ParseException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        DataMgmtPage dm = new DataMgmtPage(driver);
        TechnicalSettingsPage tcp = new TechnicalSettingsPage(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify as a As a REDCAP Platform Admin, Data Manager, Data User I or Data User II, I want to access RStudio from the project's DA system settings page, so that I can use RStudio environment for my analysis").
                assignAuthor(Constant.Author).assignCategory(Constant.Cataloge_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String View = map.get("View");
        String[] Type = View.split(",");

        LP.login(username, password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToProj_Management();
        pmp.select_Project_OverviewElipse(Constant.UI_Project2);
        tcp.navigate_to_technicalsettings();
        tcp.verify_Analytics_scratchfsx_Attributes();
        tcp.verify_rstudio_disabled_da();
        pmp.Navigate_To_Project_AppIcon();
        pmp.select_Project_OverviewElipse("autom1651117170020");

        tcp.change_rstudio_status_enabled();
        pmp.Navigate_To_Project_AppIcon();
        pmp.select_Project_OverviewElipse("autom1651117170020");
        tcp.verify_Analytics_scratchfsx_Attributes();
        tcp.verify_rstudio_btn_attributes();
        tcp.verify_create_rstudio();


    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
                {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2},
                {Constant.Privacy_Officer_Name, Constant.Password, Constant.Privacy_Officer}
        };
        return data;
    }


}





