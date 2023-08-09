
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
public class TC256_RawFiles_Csv_Download extends base {

    @Test(dataProvider = "getData")
    @Description("Verify as a REDCAP user (all roles)  able to view the files mapped to the Aliquot ID in the case details page and can be used for further analysis")
    public void TC256_CatalogDataDownloadinCSV_format_AllRoles_RawFiles_CSVDownload_01(String username, String password, String Role) throws IOException, InterruptedException, ParseException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify as a REDCAP user (all roles)  able to view the files mapped to the Aliquot ID in the case details page and can be used for further analysis").
                assignAuthor(Constant.Author).assignCategory(Constant.Cataloge_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Inputs = map.get("Inputs");
        String[] Input = Inputs.split(",");
        String FileName = map.get("FileName");
        String[] FIle_Name = FileName.split(",");


        LP.login(username, password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToCatlog_Management();
        pmp.Navigate_To_AppsCatalog();

        if (Role.equalsIgnoreCase(Constant.Admin)) {
            CMP.deleteAllexisitingFiles(DownloadPath);
            CMP.SelectFiles_DataCategory("Biospecimen");
            Common_Functions.normalwait(3000);
            ArrayList<String> Headers = CMP.fetch_CM_TableHeaders(Role);
            HashMap<String, List<String>> UI_Values = CMP.fetch_CMTable_Values();
            CMP.verify_csv_download();
            Common_Functions.normalwait(3000);
            CMP.verify_csv_headers_WithUi(FIle_Name[0], Headers);
            CMP.verify_csv_Values(DownloadPath, FIle_Name[0], UI_Values);
            LP.LogOut();
        }
        else if (Role.equalsIgnoreCase(Constant.Data_Manager)){
            System.out.println("Entere Dm");
            CMP.SelectFiles_DataType(Input[1]);
            System.out.println("Entere Dm1");
            HashMap<String, List<String>> Data_Type_UI_Values = CMP.fetch_CMTable_Values();
            Common_Functions.normalwait(5000);
            CMP.verify_csv_download();
            Common_Functions.normalwait(5000);
            CMP.verify_csv_Values(DownloadPath,FIle_Name[1], Data_Type_UI_Values);
            LP.LogOut();
        }
        else if (Role.equalsIgnoreCase(Constant.Data_User2_Name)||(Role.equalsIgnoreCase(Constant.Data_User1_Name))) {
            CMP.SelectFiles_DataFormat(Input[2]);
            HashMap<String, List<String>> Data_Format_UI_Values = CMP.fetch_CMTable_Values();
            //CMP.deleteAllexisitingFiles(DownloadPath);
            CMP.verify_csv_download();
            Common_Functions.normalwait(3000);
            CMP.verify_csv_Values(DownloadPath, FIle_Name[0], Data_Format_UI_Values);
            LP.LogOut();
        }
        else if (Role.equalsIgnoreCase(Constant.Privacy_Officer)||(Role.equalsIgnoreCase(Constant.Unassigned_User))) {
            HashMap<String, List<String>> FirstRow_UI_Values = CMP.fetch_CMTable_Values_FirstRow();
            CMP.verify_csv_download();
            Common_Functions.normalwait(3000);
            CMP.verify_csv_Values(DownloadPath, FIle_Name[0], FirstRow_UI_Values);
            CMP.verify_overall_CBMsg();
            LP.LogOut();
        }




    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{

                {Constant.Admin_Name, Constant.Password, Constant.Admin},
              //{Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
              /*  {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2},
               {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},
               {Constant.Privacy_Officer_Name, Constant.Password, Constant.Privacy_Officer},
               {Constant.Unassigned_User_Name, Constant.Password, Constant.Unassigned_User}*/
        };
        return data;
    }


}





