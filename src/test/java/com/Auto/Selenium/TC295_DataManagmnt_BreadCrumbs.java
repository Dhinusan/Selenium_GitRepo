
package com.hcl.redcap;

import Resources.*;
import com.redcapPage.CatalogManagementPage;
import com.redcapPage.DataMgmtPage;
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
public class TC295_DataManagmnt_BreadCrumbs extends base {

    @Test(dataProvider = "getData")
    @Description("Verify as a REDCAP Platform Administrator, Data Manager, Data User I or Data User II, wants to view breadcrumbs on Data Management module, so that I can navigate to any previous page easily.")
    public void TCIT_UsabilityImprovements_AllRoles_View_Breadcrumbs_DataMgmt_01(String username, String password, String Role) throws IOException, InterruptedException, ParseException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        DataMgmtPage dm = new DataMgmtPage(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify as a REDCAP Platform Administrator, Data Manager, Data User I or Data User II, wants to view breadcrumbs on Catalog module, so that I can navigate to any previous page easily.").
                assignAuthor(Constant.Author).assignCategory(Constant.Cataloge_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String View = map.get("View");
        String[] Type = View.split(",");

        if (Role.equalsIgnoreCase(Constant.Admin) || Role.equalsIgnoreCase(Constant.Data_Manager) || Role.equalsIgnoreCase(Constant.Data_User1)) {
            LP.login(username, password, Role);
            LP.verify_Lp_Attributes();
            dm.navigate_to_datamanagement();
            dm.verify_dm_Breadcrumb();
            dm.verify_apps_bc();
            LP.verify_Lp_Attributes();
            dm.navigate_to_datamanagement();
            dm.verify_updateschema_bc(Role);
            dm.verify_domainschema_bc();
            dm.verify_allversions_bc();
            dm.verify_domainschema_bc();
            dm.verify_create_schema_bc(Role);
            dm.verify_domainschema_bc();
            dm.verify_global_Schema_bc();
            dm.verify_gobal_updatschema_bc();
            LP.LogOut();
        } else {
            LP.login(username, password, Role);
            LP.verify_Lp_Attributes();
            dm.verify_du2_po_da_Access();
        }
    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
              /*{Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},

                {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2},
                {Constant.Privacy_Officer_Name, Constant.Password, Constant.Privacy_Officer}*/
        };
        return data;
    }


}





