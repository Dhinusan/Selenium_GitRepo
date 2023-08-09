package com.hcl.redcap;

import Resources.*;
import io.qameta.allure.*;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.ProjMgmtPage;

import java.io.IOException;
import java.util.Map;

@Listeners({ListenerClass.class})
public class TC216_Verify_Remove_AddedFiles_UI extends base {

    @Test(dataProvider = "getData", description = "TCIT_DATAINGMGMT_ADMIN DM DU1_UI_Manifest Creation_Remove_Added Files")
    @Severity(SeverityLevel.NORMAL)
    @Description("Data Ingestion: Verify user able to add multiple files from UI")
    @Story("As a REDCAP Platform admin, Data Manager and Data User-I, I want to enter multiple file names on a manifest that I create on UI, so that I can re-use the metadata for ingesting the files.")
    @Epic("Data management")


    public void TC216_DATAINGMGMT_ADMIN_DM_DU1_UI_Manifest_Creation_Remove_Added_Files(String Username, String Password, String Role) throws IOException, InterruptedException, ParseException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        ExtentManager Ex = new ExtentManager();

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify user able to add multiple files from UI").
                assignAuthor(Constant.Author).assignCategory(Constant.DI_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Excel_FileName = map.get("FileName");


        LP.login(Username, Password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToProj_Management();
        pmp.select_Project_OverviewElipse(Constant.UI_Project1);
        pmp.Navigate_To_DataMgmtIngest();
        pmp.Navigate_To_CreateManifest();
        pmp.Navigate_To_RawSequence();
        pmp.Valid_Filename_onAll4Files(Excel_FileName);
        pmp.DeleteAllFileCardsAndVerify();
        LP.LogOut();
    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
             /* {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},*/

        };
        return data;
    }


}
