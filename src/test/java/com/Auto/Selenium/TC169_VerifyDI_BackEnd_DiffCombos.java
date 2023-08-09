package com.hcl.redcap;

import Resources.*;
import com.redcapPage.AWS;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Listeners({ListenerClass.class})
public class TC169_VerifyDI_BackEnd_DiffCombos extends base {

    @Test(dataProvider = "getData")
    @Description("Verify Data Ingestion via direct file drop \n" +
            "1.If All primary keys are not available in manifest file\n" +
            "2.If All Only case Id is available in manifest file\n" +
            "3.If case Id and Sample Id are available in manifest file\n" +
            "\n" +
            "4.If Case ID, Sample ID and Aliquot ID are available in manifest file")
    public void TC169_Verify_DataIngestion_via_direct_filedrop(String Username, String Password, String Role, String RedcapUsername) throws IOException, InterruptedException, ParseException, AWTException, AWTException, ParseException {
        Login_LandingPage LP = new Login_LandingPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        AWS aws = new AWS(driver);
        ExtentManager Ex = new ExtentManager();
        ProjMgmtPage pmp = new ProjMgmtPage(driver);

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "TC169_Verify_DataIngestion_via_directfiledrop").
                assignAuthor(Constant.Author).assignCategory(Constant.DI_Management);

        //TC169.fastq,TC169-1.fastq,TC169-2.fastq,TC169-3.fastq
        //Json-raw_ns.json,raw_ns_case.json,raw_ns_sample.json,raw_ns_aliquot.json
        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String AWS_Role = map.get("AWS_Role");
        String S3_Project = map.get("S3_Project");
        String FileName = map.get("FileName");
        String[] File = FileName.split(",");
        String JsonFile = map.get("JsonFile");
        String[] Json_File = JsonFile.split(",");
        String View = map.get("View");
        String Sample_Sub_Id =map.get("Sample_Sub_Id");
        String Aliquot_Sub_Id =map.get("Aliquot_Sub_Id");
        String Immune_Profile =map.get("Immune_Profile");
        String IP_WorkFlow =map.get("IP_WorkFlow");
        String Inputs =map.get("Inputs");
        String Caseid = "Case" + randomNumber();
        ArrayList<String> AllFiles = new ArrayList<>();
        String FileName1 = "File" + randomNumber() + ".fastq";
        String FileName2 = "File" + randomNumber() + ".fastq";
        String FileName3 = "File" + randomNumber() + ".fastq";
        String FileName4 = "File" + randomNumber() + ".fastq";
        AllFiles.add(FileName1);
        AllFiles.add(FileName2);
        AllFiles.add(FileName3);
        AllFiles.add(FileName4);
        HashMap<String,String> HM = new HashMap<>();

        try {
            Change_File_Name(UploadPath, File[0], FileName1);
            Change_File_Name(UploadPath, File[1], FileName2);
            Change_File_Name(UploadPath, File[2], FileName3);
            Change_File_Name(UploadPath, File[3], FileName4);
            awsupload_datafile(Constant.Aws_Bucket, FileName1, UploadPath);
            awsupload_datafile(Constant.Aws_Bucket, FileName2, UploadPath);
            awsupload_datafile(Constant.Aws_Bucket, FileName3, UploadPath);
            awsupload_datafile(Constant.Aws_Bucket, FileName4, UploadPath);
          /*  CMP.UpdateFileName(UploadPath, Json_File[0], FileName1);
            CMP.UpdateFileName(UploadPath, Json_File[1], FileName2);
            CMP.UpdateFileName(UploadPath, Json_File[2], FileName3);
            CMP.UpdateFileName(UploadPath, Json_File[3], FileName4);*/

            LP.login(RedcapUsername,Password,Role);
            LP.verify_Lp_Attributes();
            LP.navigateToProj_Management();
            pmp.select_Project_OverviewElipse(Constant.UI_Project1);
            pmp.Navigate_To_DataMgmtIngest();
            pmp.verify_ManifestPage_RawNonSequence();
            pmp.MC_FileInput(FileName1);
            String FileId = pmp.getFileId();
            String ProjId = pmp.getprojectId();
            pmp.MCCase_Input(Caseid);
            pmp.MCSample_MandatoryInput(Sample_Sub_Id);
            pmp.MCAlliquot_Mandatory_Input(Aliquot_Sub_Id);
            pmp.MC_Immuneprofile_Input(Immune_Profile);
            pmp.MC_Immune_WFInput(IP_WorkFlow);
            pmp.ManifestSave();
            pmp.fill_mandatory_tables("169");
            pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
            Common_Functions.normalwait(5000);
            pmp.validate_ManifestTable(FileName1);
            pmp.Download_Json_Manifest();
            driver.navigate().refresh();
            Common_Functions.normalwait(5000);
            pmp.Edit_Manifest();
            pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
            pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
            pmp.Navigate_To_AppsCatalog();
            HM = CMP.fetch_file_details(FileName1);
            CMP.UpdateFile_WithCaseId(DownloadPath,FileId+".json",HM);
            CMP.UpdateFileName(DownloadPath, FileId+".json", FileName2);
            Common_Functions.normalwait(5000);
            awsupload_meta_datafile(Constant.Aws_Bucket,FileId+".json",DownloadPath);
            Common_Functions.normalwait(5000);
            CMP.UpdateFile_WithCase_SampleId(DownloadPath,FileId+".json",HM);
            CMP.UpdateFileName(DownloadPath, FileId+".json", FileName3);
            Common_Functions.normalwait(5000);
            awsupload_meta_datafile(Constant.Aws_Bucket,FileId+".json",DownloadPath);
            Common_Functions.normalwait(5000);
            CMP.UpdateFile_WithCase_Sample_AliqId(DownloadPath,FileId+".json",HM);
            CMP.UpdateFileName(DownloadPath, FileId+".json", FileName4);
            Common_Functions.normalwait(5000);;
            awsupload_meta_datafile(Constant.Aws_Bucket,FileId+".json",DownloadPath);
            Common_Functions.normalwait(5000);
            pmp.Navigate_To_AppsCatalog();
            CMP.Select_View(View);
            CMP.validate_UIFilename1(AllFiles);
            LP.LogOut();
        } finally {
        Change_File_Name(UploadPath, FileName1, File[0]);
            Change_File_Name(UploadPath, FileName2, File[1]);
            Change_File_Name(UploadPath, FileName3, File[2]);
            Change_File_Name(UploadPath, FileName4, File[3]);

        }

    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Aws_Admin_UserName, Constant.Password, Constant.Admin, Constant.Admin_Name},


        };
        return data;
    }


}





