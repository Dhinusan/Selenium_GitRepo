
package com.hcl.redcap;

import Resources.*;
import com.redcapPage.CatalogManagementPage;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.ProjMgmtPage;
import com.redcapPage.UserMgmtPage;
import io.qameta.allure.Description;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;

@Listeners({ListenerClass.class})
public class TC262_UserManage_BreadCrumbs extends base {

    @Test(dataProvider = "getData")
    @Description( "Verify as a REDCAP Platform Administrator, Data Manager, Data User I or Data User II, wants to view breadcrumbs on User Management module, so that I can navigate to any previous page easily.")
    public void TC262_UsabilityImprovements_AllRoles_View_Breadcrumbs_UserMgmt_01(String username, String password, String Role) throws IOException, InterruptedException, ParseException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        UserMgmtPage UMP = new UserMgmtPage(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify as a REDCAP Platform Administrator, Data Manager, Data User I or Data User II, wants to view breadcrumbs on User Management module, so that I can navigate to any previous page easily.").
                assignAuthor(Constant.Author).assignCategory(Constant.User_Management);

        //Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
    if (Role.equals(Constant.Admin)){
        LP.login(username, password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToUser_Management();
        UMP.validate_Um_BreadCrumbs_AddUser();
        UMP.validate_Um_BreadCrumbs_EditUser();
        UMP.validate_Um_BreadCrumbs_ViewProject();
        LP.LogOut();
    }
    else{
        LP.login(username, password, Role);
        LP.verify_Lp_Attributes();
        LP.verify_um_access(Role);
    }



    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
                /*{Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Unassigned_User_Name, Constant.Password, Constant.Unassigned_User},
                {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},
                {Constant.Privacy_Officer_Name, Constant.Password, Constant.Privacy_Officer}*/

        };
        return data;
    }


}





