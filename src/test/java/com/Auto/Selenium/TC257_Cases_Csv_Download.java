
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Listeners({ListenerClass.class})
public class TC257_Cases_Csv_Download extends base {

    @Test(dataProvider = "getData")
    @Description("Verify as a REDCAP all users want to download the cases from Catalog in CSV, so that further analysis can be done offline")
    public void TC257_CatalogDataDownloadinCSV_format_AllRoles_Cases_CSVDownload_01(String username, String password, String Role) throws IOException, InterruptedException, ParseException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify as a REDCAP all users want to download the cases from Catalog in CSV, so that further analysis can be done offline").
                assignAuthor(Constant.Author).assignCategory(Constant.Cataloge_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Inputs = map.get("Inputs");
        String[] Input = Inputs.split(",");
        String FileName = map.get("FileName");
        String[] FIle_Name = FileName.split(",");

        LP.login(username, password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToCatlog_Management();
        if (Role.equalsIgnoreCase(Constant.Admin)) {
            CMP.SelectCases_ProjId(Input[0]);
            Common_Functions.normalwait(3000);
            CMP.navitocasetab();
            ArrayList<String> Headers = CMP.fetch_CM_TableHeaders(Role);
            HashMap<String, List<String>> Project_UI_Values = CMP.fetch_CMTable_Values();
            CMP.deleteAllexisitingFiles(DownloadPath);
            CMP.verify_csv_download();
            Common_Functions.normalwait(3000);
            CMP.verify_csv_headers_WithUi(FIle_Name[0], Headers);
            CMP.verify_csv_Values(DownloadPath, FIle_Name[0], Project_UI_Values);
        }
     /*   driver.navigate().refresh();
        CMP.SelectCases_CaseId(Input[1]);
        CMP.navitocasetab();
        HashMap<String, List<String>> Case_UI_Values = CMP.fetch_CMTable_Values();
        CMP.verify_csv_download();
        CMP.verify_csv_Values(DownloadPath,FIle_Name[1], Case_UI_Values);*/
        /*driver.navigate().refresh();
        CMP.SelectCases_PimarySite(Input[2]);
        CMP.navitocasetab();
        HashMap<String, List<String>> Primary_Site_UI_Values = CMP.fetch_CMTable_Values();
        CMP.verify_csv_download();
        CMP.verify_csv_Values(FIle_Name[2], Primary_Site_UI_Values);
        driver.navigate().refresh();
        CMP.SelectCases_Arbor(Input[3]);
        CMP.navitocasetab();
        HashMap<String, List<String>> Ardor_UI_Values = CMP.fetch_CMTable_Values();
        CMP.verify_csv_download();
        CMP.verify_csv_Values(FIle_Name[3], Ardor_UI_Values);
        driver.navigate().refresh();
        CMP.navitocasetab();
        Common_Functions.normalwait(3000);
        HashMap<String, List<String>> FirstRow_UI_Values = CMP.fetch_CMTable_Values_FirstRow();
        CMP.verify_csv_download();
        CMP.verify_csv_Values(FIle_Name[4], FirstRow_UI_Values);
        CMP.verify_overall_CBMsg();*/
        LP.LogOut();

    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
               // {Constant.Unassigned_User_Name, Constant.Password, Constant.Unassigned_User},
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
                /*{Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},
                {Constant.Privacy_Officer_Name, Constant.Password, Constant.Privacy_Officer}*/

        };
        return data;
    }


}





