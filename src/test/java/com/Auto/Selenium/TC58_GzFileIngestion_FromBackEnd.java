package com.hcl.redcap;

import Resources.*;
import com.redcapPage.AWS;
import com.redcapPage.CatalogManagementPage;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.ProjMgmtPage;
import io.qameta.allure.*;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.util.Map;

@Listeners({ListenerClass.class})
public class TC58_GzFileIngestion_FromBackEnd extends base {
    @Test(dataProvider = "getData", description = "TCIT_DATAINGMGMT_ADMIN DM DU1_Drop Only Manifest_Drop Only Data File_Ingestion Inference_01")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify Data Ingestion for .gz file via direct file drop into ingest S3 bucket" + "1.Ingest first manifest file then data file into ingest S3 bucket" + "2.Ingest first data file then manifest file into ingest S3 bucket")
    @Epic("Data Ingestion management")
    public void TC58_Verify_Ingestion_gzfiles_IntoS3Bucket(String Username, String Password, String Role, String RedcapUsername) throws IOException, InterruptedException, ParseException, AWTException {
        Login_LandingPage LP = new Login_LandingPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        AWS aws = new AWS(driver);
        ExtentManager Ex = new ExtentManager();

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "TC58_Verify_Ingestion_gzfiles_IntoS3Bucket").
                assignAuthor(Constant.Author).assignCategory(Constant.DI_Management);


        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String ExcelFileName = map.get("FileName");
        String[] Excel_FileName = ExcelFileName.split(",");
        String Sample_Sub_Id =map.get("Sample_Sub_Id");
        String Aliquot_Sub_Id =map.get("Aliquot_Sub_Id");
        String Read_Group =map.get("Read_Group");
        String Aligned_Reads =map.get("Aligned_Reads");
        String Primary_site = map.get("Primary_site");
        String Disease_type = map.get("Disease_type");
        String Consent_type = map.get("Consent_type");
        String Caseid = "Case" + randomNumber();
        String FileName1 = "File" + randomNumber() + ".gz";
        String FileName2 = "File" + randomNumber() + ".gz";
        //File_Name=TC58.gz,TC58_1.gz ,JsonFile=Testcase58.json
        Change_File_Name(UploadPath, Excel_FileName[0], FileName1);
        Change_File_Name(UploadPath, Excel_FileName[1], FileName2);
        try {
            // scenario 1st when metadata file is uploaded first then data file
            /*aws.AWS_Login(Username, Password);
            aws.select_role(AwsRole, Role);
            CMP.UpdateFileName(UploadPath, JsonFile, FileName1);
            aws.navigate_to_S3Bucket();
            aws.S3_Input(S3_Project);
            aws.upload_metadata_file(JsonFile, UploadPath);
            aws.Verify_Upload_Success_msg();
            Common_Functions.normalwait(5000);
            aws.navigate_to_S3Bucket();
            aws.S3_Input(S3_Project);
            aws.upload_data_files(FileName1, UploadPath);
            aws.Verify_Upload_Success_msg();
            Common_Functions.normalwait(30000);
            LP.login(RedcapUsername, Password, Role);
            LP.verify_Lp_Attributes();
            LP.navigateToCatlog_Management();
            CMP.NavigateToCatalogFileDetailandBack(FileName1);
            CMP.NavigateToCaseTabAndClickonCaseIDandverify();
            CMP.VerifyCaseTableOnCaseDetailPage(UploadPath, JsonFile);
            LP.LogOut();*/

            // scenario 2nd when data file is uploaded first then metadata
            aws_files();
            awsupload_datafile(Constant.Aws_Bucket, FileName2, UploadPath);
            LP.login(RedcapUsername, Password,Role);
            LP.verify_Lp_Attributes();
            LP.navigateToProj_Management();
            pmp.select_Project_OverviewElipse(Constant.UI_Project1);
            pmp.Navigate_To_DataMgmtIngest();
            pmp.Navigate_To_CreateManifest();
            pmp.Navigate_To_RawSequence();
            pmp.MCCase_MultipleInputs(Caseid, Primary_site, Disease_type, Consent_type);
            pmp.MCSample_MandatoryInput(Sample_Sub_Id);
            pmp.MCAlliquot_Mandatory_Input(Aliquot_Sub_Id);
            pmp.MC_ReadGroup_Inputs(Read_Group);
            pmp.DF_Aligned_reads(Aligned_Reads);
            pmp.MC_FileInput(FileName2);
            String FileId = pmp.getFileId();
            pmp.ManifestSave();
            pmp.fill_mandatory_tables("58");
            pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
            pmp.validate_ManifestTable(FileName2);
            Common_Functions.normalwait(3000);
            pmp.Download_Json_Manifest();
            aws.awsupload_metadatafile(Constant.Aws_Bucket,FileId + ".json", DownloadPath);
            Common_Functions.normalwait(30000);
            pmp.Navigate_To_AppsCatalog();
            Common_Functions.normalwait(5000);
            CMP.ClickOnFileTab();
            CMP.NavigateToCatalogFileDetailandBack(FileName2);
            CMP.NavigateToCaseTabAndClickonCaseIDandverify();
            CMP.VerifyCaseTableOnCaseDetailPage(DownloadPath, FileId + ".json");
            LP.LogOut();
        } finally {
            Change_File_Name(UploadPath, FileName1, Excel_FileName[0]);
            Change_File_Name(UploadPath, FileName2, Excel_FileName[1]);
            System.out.println("Test Case Completed with "+Role+ getClass().getSimpleName());
        }

    }


    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Aws_Admin_UserName, Constant.Password, Constant.Admin, Constant.Admin_Name}
        };
        return data;
    }


}
