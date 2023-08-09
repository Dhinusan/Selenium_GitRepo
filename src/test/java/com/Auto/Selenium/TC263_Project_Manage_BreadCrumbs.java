
package com.hcl.redcap;

import Resources.*;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.ProjMgmtPage;
import com.redcapPage.TechnicalSettingsPage;
import io.qameta.allure.Description;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;

@Listeners({ListenerClass.class})
public class TC263_Project_Manage_BreadCrumbs extends base {

    @Test(dataProvider = "getData")
    @Description("Verify as a REDCAP Platform Administrator, Data Manager, Data User I or Data User II, wants to view breadcrumbs on Project Management module, so that I can navigate to any previous page easily.")
    public void TC263_UsabilityImprovements_AllRoles_View_Breadcrumbs_ProjectMgmt_01(String username, String password, String Role) throws IOException, InterruptedException, ParseException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        TechnicalSettingsPage tcp = new TechnicalSettingsPage(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify as a REDCAP Platform Administrator, Data Manager, Data User I or Data User II, wants to view breadcrumbs on Project Management module, so that I can navigate to any previous page easily.").
                assignAuthor(Constant.Author).assignCategory(Constant.Project_Management);

         Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Inputs = map.get("Inputs");
        if (Role.equalsIgnoreCase(Constant.Admin) || Role.equalsIgnoreCase(Constant.Data_Manager)) {
            LP.login(username, password, Role);
            LP.verify_Lp_Attributes();
            LP.navigateToProj_Management();
            if (Role.equalsIgnoreCase(Constant.Admin)) {
                pmp.validate_Pm_BreadCrumbs_DeleteProject_Onboard();
                pmp.select_Project_OverviewElipse(Constant.UI_Project1);
                pmp.validate_Pm_Breadcrumbs_Infra_Settings(Constant.UI_Project1_Name);
            }
            pmp.select_Project_OverviewElipse(Constant.UI_Project1);
            pmp.validate_Pm_Breadcrumbs_DataIngestion_Manage1(Constant.UI_Project1_Name);
            pmp.verify_Pm_Breadcrumbs_createmanifest(Constant.UI_Project1_Name);
            pmp.verify_Pm_Breadcrumbs_createmetadata(Constant.UI_Project1_Name);
            pmp.select_Project_OverviewElipse(Constant.UI_Project1_Name);
            pmp.verify_projectaccess_breadcrumb();
            pmp.verify_Pm_Breadcrumbs_projectaccess(Constant.UI_Project1_Name,Inputs);
            pmp.Navigate_To_Project_AppIcon();
            pmp.select_Project_OverviewElipse(Constant.UI_Project1_Name);
            tcp.verify_Pm_Breadcrumbs_technicalsettings();
            LP.LogOut();
        } else if (Role.equalsIgnoreCase(Constant.Data_User1) || Role.equalsIgnoreCase(Constant.Data_User2) || Role.equalsIgnoreCase(Constant.Privacy_Officer)) {
            LP.login(username, password, Role);
            LP.verify_Lp_Attributes();
            LP.navigateToProj_Management();
            if (Role.equalsIgnoreCase(Constant.Data_User1)) {
                pmp.select_Project_OverviewElipse(Constant.UI_Project1);
                pmp.validate_Pm_Breadcrumbs_DataIngestion_Manage1(Constant.UI_Project1_Name);
                pmp.verify_Pm_Breadcrumbs_createmanifest(Constant.UI_Project1_Name);
                pmp.verify_Pm_Breadcrumbs_createmetadata(Constant.UI_Project1_Name);
            }
            pmp.select_Project_OverviewElipse(Constant.UI_Project1);
            pmp.verify_projectaccess_breadcrumb();
            driver.navigate().back();
            if (Role.equalsIgnoreCase(Constant.Data_User1) || Role.equalsIgnoreCase(Constant.Data_User2)) {
                pmp.Navigate_To_Project_AppIcon();
                pmp.select_Project_OverviewElipse(Constant.UI_Project1);
                tcp.verify_Pm_Breadcrumbs_technicalsettings();
            }
            LP.LogOut();
        } else if (Role.equalsIgnoreCase(Constant.Unassigned_User)) {
            LP.login(username, password, Role);
            LP.verify_Lp_Attributes();
            LP.verify_UAUser_projectmanagement();
            LP.LogOut();
        }


    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
               /* {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},

              {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2},
                {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2},
                {Constant.Privacy_Officer_Name, Constant.Password, Constant.Privacy_Officer}*/

        };
        return data;
    }


}





