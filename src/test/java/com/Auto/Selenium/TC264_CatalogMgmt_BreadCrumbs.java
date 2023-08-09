
package com.hcl.redcap;

import Resources.*;
import com.redcapPage.CatalogManagementPage;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.ProjMgmtPage;
import io.qameta.allure.Description;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;

@Listeners({ListenerClass.class})
public class TC264_CatalogMgmt_BreadCrumbs extends base {

    @Test(dataProvider = "getData")
    @Description("Verify as a REDCAP Platform Administrator, Data Manager, Data User I or Data User II, wants to view breadcrumbs on Catalog module, so that I can navigate to any previous page easily.")
    public void TC264_UsabilityImprovements_AllRoles_View_Breadcrumbs_CatalogMgmt_01(String username, String password, String Role) throws IOException, InterruptedException, ParseException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify as a REDCAP Platform Administrator, Data Manager, Data User I or Data User II, wants to view breadcrumbs on Catalog module, so that I can navigate to any previous page easily.").
                assignAuthor(Constant.Author).assignCategory(Constant.Cataloge_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String View = map.get("View");
        String[] Type = View.split(",");

        LP.login(username, password, Role);
        LP.verify_Lp_Attributes();
        pmp.Navigate_To_AppsCatalog();
        CMP.verify_CM_File_ProjectDetailsBreadcrumb();
        CMP.verify_CM_CasedetailsBC();
        CMP.verify_Accessory_breadcrumb(Type[0]);
        CMP.verify_referencefile_breadcrumb(Type[1]);
        LP.LogOut();

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





