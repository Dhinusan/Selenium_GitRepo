
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

import java.awt.*;
import java.io.IOException;
import java.util.Map;

@Listeners({ListenerClass.class})
public class TC261_Csv_Upload_Bulk_Reference extends base {

    @Test(dataProvider = "getData")
    @Description("Verify as REDCAP system, the user want to process the .CSV file after successful upload, so that ingestion process can be completed.")
    public void TC261_BulkManifestCreationthroughCSV_ADMIN_DM_DU1_UploadManifestinCSV_Func_01(String username, String password, String Role) throws IOException, InterruptedException, ParseException, AWTException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ExtentManager Ex = new ExtentManager();
        ProjMgmtPage pmp = new ProjMgmtPage(driver);

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify as REDCAP system, the user want to process the .CSV file after successful upload, so that ingestion process can be completed.").
                assignAuthor(Constant.Author).assignCategory(Constant.Project_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Bulk_Files = map.get("FileName2");
        String Ref_DataFile = map.get("FileName");
        String S3_Project = map.get("S3_Project");
        String JsonFile = map.get("JsonFile");
        String[] JsonFiles = JsonFile.split(",");
        String View = map.get("View");
        String Files = map.get("JsonFile");
        String[] Json_Files = Files.split(",");
        String Filename = "File" + randomNumber() + ".fastq";
        String Case_Sub_Id = "Case" + randomNumber();
        //Reference
        try {
            //Change_File_Name(UploadPath, Ref_DataFile, Filename);
            //awsupload_datafile(Constant.Aws_Bucket, Filename, UploadPath);
            LP.login(username, password, Role);
            LP.verify_Lp_Attributes();
            LP.navigateToProj_Management();
            pmp.select_Project_OverviewElipse(Constant.UI_Project1);
            pmp.Navigate_To_DataMgmtIngest();
          /* pmp.Navigate_To_CreateManifest();
            pmp.verify_Manifest_Reference();
            pmp.MC_FileInput(Filename);
            String FileId = pmp.getFileId();
            pmp.ManifestSave();
            pmp.fill_mandatory_tables("261");
            pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
            pmp.validate_ManifestTable(Filename);
            pmp.Download_csv_Manifest();
            driver.navigate().refresh();*/
            /*LP.login(username, password, Role);
            LP.verify_Lp_Attributes();
            LP.navigateToProj_Management();
            pmp.select_Project_OverviewElipse(Constant.UI_Project1);
            pmp.Navigate_To_DataMgmtIngest();*/
            Common_Functions.normalwait(3000);
            pmp.upload_csvfile_1(DownloadPath,"File"+".csv");
            pmp.verify_csvupload_successmsg();
            String Manifest_Id = pmp.verify_ManifestOverviewData(Filename, username);
            pmp.Verify_Ingestion_MO(Filename, Manifest_Id);
            pmp.Navigate_To_AppsCatalog();
            CMP.Select_View(View);
            CMP.Verify_csv_reference(Filename);
            LP.LogOut();


            //Bulk Upload-15 Files
            /*awsupload_datafile(S3_Project, Bulk_Files, UploadPath);
            CMP.update_csv_values_bulk(JsonFiles[1], Case_Sub_Id);
            LP.login(username, password, Role);
            LP.verify_Lp_Attributes();
            LP.navigateToProj_Management();
            pmp.select_Project_OverviewElipse(UI_Project);
            pmp.Navigate_To_DataMgmtIngest();
            pmp.upload_csvfile(UploadPath, JsonFiles[1]);
            pmp.verify_csvupload_successmsg();
            String Bulk_ManifestID = pmp.verify_ManifestOverviewData(JsonFiles[1], username);
            pmp.Verify_Ingestion_MO(JsonFiles[1], Bulk_ManifestID);
            pmp.Navigate_To_AppsCatalog();
            CMP.navigate_To_FileDeatils(Case_Sub_Id);
            CMP.verify_bulk_upload();*/

        } finally {
            Change_File_Name(UploadPath, Filename, "TC261_Reference.fastq");
        }


    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
                /*{Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Unassigned_User_Name, Constant.Password, Constant.Unassigned_User},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},
                {Constant.Privacy_Officer_Name, Constant.Password, Constant.Privacy_Officer}*/
        };
        return data;
    }


}






