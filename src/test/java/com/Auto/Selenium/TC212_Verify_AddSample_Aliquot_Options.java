
package com.hcl.redcap;

import Resources.*;
import com.redcapPage.CatalogManagementPage;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.ProjMgmtPage;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

import static com.hcl.redcap.TC207_ViewAliquots_Belonging_To_Case.case_id_207;

@Listeners({ListenerClass.class})
public class TC212_Verify_AddSample_Aliquot_Options extends base {

    @Test(dataProvider = "getData")
    @Description("Catalog Managemnet UI - Verify users can view options for adding a sample, aliquot and file on Case Details page")
    public void TC212(String RedcapUsername, String Password, String Role, String Aws_username) throws IOException, InterruptedException, AWTException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        ExtentManager Ex = new ExtentManager();

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Catalog Managemnet UI - Verify users can view options for adding a sample, aliquot and file on Case Details page").
                assignAuthor(Constant.Author).assignCategory(Constant.Cataloge_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Inputs = map.get("Inputs");


        if (Role.equalsIgnoreCase(Constant.Admin) || Role.equalsIgnoreCase(Constant.Data_Manager) || Role.equalsIgnoreCase(Constant.Data_User1)) {
            LP.login(RedcapUsername, Password, Role);
            LP.verify_Lp_Attributes();
            LP.navigateToProj_Management();
            pmp.select_Project_OverviewElipse(Constant.UI_Project1);
            pmp.Navigate_To_AppsCatalog();
            CMP.navigate_To_Casedetails(case_id_207);
            pmp.AddFile_Case(Role,"Sequence");
            pmp.navigate_cat_case_details();
            pmp.Sel_AddSampAliquot(Role);
            pmp.navigate_cat_case_details();
            CMP.minimize_arrow();
            CMP.Grid_expand_Samples();
            pmp.Sel_AddAliquot(Role);
            pmp.navigate_cat_case_details();
            pmp.AddFile_Sample(Role,"Sequence");
            pmp.navigate_cat_case_details();
            CMP.minimize_arrow();
            CMP.Grid_expand_Aliquots();
            pmp.AddFile_Aliquot(Role);
            LP.LogOut();
        } else {
            LP.login(RedcapUsername, Password, Role);
            LP.verify_Lp_Attributes();
            LP.navigateToProj_Management();
            pmp.select_Project_OverviewElipse(Constant.UI_Project1);
            pmp.Navigate_To_AppsCatalog();
            CMP.navigate_To_Casedetails(case_id_207);
            pmp.AddFile_Case(Role,"Sequence");
            pmp.Sel_AddSampAliquot(Role);
            CMP.minimize_arrow();
            CMP.Grid_expand_Samples();
            pmp.Sel_AddAliquot(Role);
            pmp.AddFile_Sample(Role,"Sequence");
            CMP.minimize_arrow();
            CMP.Grid_expand_Aliquots();
            pmp.AddFile_Aliquot(Role);
            LP.LogOut();
        }
    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{

                {Constant.Admin_Name, Constant.Password, Constant.Admin, Constant.Aws_Admin_UserName},
              /*  {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager, Constant.Aws_Admin_UserName},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1, Constant.Aws_Admin_UserName},
                {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2, Constant.Aws_Admin_UserName},
                {Constant.Privacy_Officer_Name, Constant.Password, Constant.Privacy_Officer, Constant.Aws_Admin_UserName}*/
        };
        return data;
    }


}




