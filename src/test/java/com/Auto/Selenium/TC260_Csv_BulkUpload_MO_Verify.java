
package com.hcl.redcap;

import Resources.*;
import io.qameta.allure.*;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.CatalogManagementPage;
import com.redcapPage.ProjMgmtPage;

import java.awt.*;
import java.io.IOException;
import java.util.Map;

@Listeners({ListenerClass.class})
public class TC260_Csv_BulkUpload_MO_Verify extends base {

    @Test(dataProvider = "getData")
    @Description("Verify as a REDCAP Platform Administrator, Data Manager, Data User I , want to upload the manifest in csv format, so that easily create manifest for many data files together")
    public void TC260_BulkManifestCreationthroughCSV_ADMIN_DM_DU1_UploadManifestinCSV_UI_01
            (String Username, String Password, String Role) throws IOException, InterruptedException, ParseException, AWTException {
        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ExtentManager Ex = new ExtentManager();

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify as a REDCAP Platform Administrator, Data Manager, Data User I , want to upload the manifest in csv format, so that easily create manifest for many data files together").
                assignAuthor(Constant.Author).assignCategory(Constant.Project_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Incorrect_FileName = map.get("Incorrect_FileName");
        String[] InvalidFile = Incorrect_FileName.split(",");
        String FileName = map.get("FileName");

        if (Role.equalsIgnoreCase(Constant.Data_Manager) || Role.equalsIgnoreCase(Constant.Admin) || Role.equalsIgnoreCase(Constant.Data_User1)) {
            LP.login(Username, Password, Role);
            LP.verify_Lp_Attributes();
            LP.navigateToProj_Management();
            pmp.select_Project_OverviewElipse(Constant.UI_Project1);
            pmp.Navigate_To_DataMgmtIngest();
            pmp.Navigate_To_UploadManifest_CSV();
            pmp.verify_uploadpopup_attributes();
            pmp.verify_csvUpload_EmptyFile();
            pmp.verify_csv_sizefile(UploadPath, InvalidFile[0]);
            pmp.verify_csvinvalidformatfile(UploadPath, InvalidFile[1]);
            pmp.upload_csvfile(UploadPath, FileName);
            pmp.verify_csvupload_successmsg();
            pmp.verify_ManifestOverviewData(FileName, Username);
            LP.LogOut();
        } else {
            LP.login(Username, Password, Role);
            LP.verify_Lp_Attributes();
            LP.navigateToProj_Management();
            pmp.select_Project_OverviewElipse(Constant.UI_Project1);
            pmp.verify_DataMgmt_PO_DU1_UA(Role);
        }
    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
               /* {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},
                {Constant.Privacy_Officer_Name, Constant.Password, Constant.Privacy_Officer}*/

        };
        return data;
    }

}

