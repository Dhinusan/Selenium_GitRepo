
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

@Listeners({ListenerClass.class})
public class TC258_Verify_AliquotID_CMPage extends base {

    @Test(dataProvider = "getData")
    @Description( "Verify as a REDCAP user(all roles), I want to view the aliquot ID of corresponding file in Catalog File details page")
    public void TC258_UsabilityImprovements_AllRoles_View_AliquotID_FileDetailsPage(String username, String password, String Role) throws IOException, InterruptedException, ParseException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify as a REDCAP user(all roles), I want to view the aliquot ID of corresponding file in Catalog File details page").
                assignAuthor(Constant.Author).assignCategory(Constant.Cataloge_Management);

        //Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());

                LP.login(username, password, Role);
                LP.verify_Lp_Attributes();
                LP.navigateToProj_Management();
                pmp.Navigate_To_AppsCatalog();
                CMP.Validate_AliquotID_CatalogDetails();
                LP.LogOut();


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





