package com.hcl.redcap;

import Resources.*;
import com.redcapPage.AWS;
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

@Listeners({ListenerClass.class})
public class TC180_Verify_Ref_UI_Ingestion extends base {

    @Test(dataProvider = "getData")
    @Description("Verify the Reference Manifest file with  Required + optional tables ingestion in UI and also verify  the ingested file in the catalog UI")
    public void TC180_Verify_Reference_UI_Ingestion(String Username, String Password, String Role) throws IOException, InterruptedException, AWTException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        AWS aws = new AWS(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "TC180_Verify_Reference_UI_Ingestion").
                assignAuthor(Constant.Author).assignCategory(Constant.DI_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Excel_FileName = map.get("FileName");
        String View = map.get("View");
        String Filename = "file" + randomNumber() + ".fastq";
        Change_File_Name(UploadPath, Excel_FileName, Filename);
        try {
            awsupload_datafile(Constant.Aws_Bucket, Filename, UploadPath);
            LP.login(Username, Password, Role);
            LP.verify_Lp_Attributes();
            LP.navigateToProj_Management();
            pmp.select_Project_OverviewElipse(Constant.UI_Project1);
            pmp.Navigate_To_DataMgmtIngest();
            pmp.Navigate_To_CreateManifest();
            pmp.verify_Manifest_Reference();
            pmp.MC_FileInput(Filename);
            String FileId = pmp.getFileId();
            pmp.ManifestSave();
            pmp.fill_mandatory_tables("180");
            pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
            pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
            pmp.Navigate_To_AppsCatalog();
            CMP.Select_View(View);
            CMP.navigate_To_FileDeatils_Reference(Filename, FileId);
            CMP.file_header_reference();
            CMP.Validate_FileValues_Reference(FileId, Filename, Username);
            LP.LogOut();
        } finally {
            Change_File_Name(UploadPath, Filename, Excel_FileName);
        }
    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
             /*  {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1}*/
        };
        return data;
    }


}




