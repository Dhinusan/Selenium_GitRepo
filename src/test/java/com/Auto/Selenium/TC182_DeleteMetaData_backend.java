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
import java.util.HashMap;
import java.util.Map;

@Listeners({ListenerClass.class})
public class TC182_DeleteMetaData_backend extends base {



    @Test(dataProvider = "getData")
    @Description("Verify Delete  operation in direct file drop\n" +
            "Verify Delete  operation in direct file drop with respect to Aliquot ID changes")
    public void TC182(String Username, String Password, String Role, String RedcapUsername) throws IOException, InterruptedException, ParseException, AWTException, java.text.ParseException {
        Login_LandingPage LP = new Login_LandingPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        AWS aws = new AWS(driver);
        ExtentManager Ex = new ExtentManager();
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() +"_"+ Role, "TCIT_DATAINGMGMT_181_MetaDataSave").
                assignAuthor(Constant.Author).assignCategory(Constant.DI_Management);
        String OldTime = "none";

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Excel_FileName = map.get("FileName");
        String View = map.get("View");
        String Sample_Sub_Id =map.get("Sample_Sub_Id");
        String Aliquot_Sub_Id =map.get("Aliquot_Sub_Id");
        String Immune_Profile =map.get("Immune_Profile");
        String IP_WorkFlow =map.get("IP_WorkFlow");
        String Inputs =map.get("Inputs");
        String Caseid = "Case" + randomNumber();
        String FileName = "File" + randomNumber() + ".fastq";

        HashMap<String,String> HM = new HashMap<>();
        try{
            Change_File_Name(UploadPath, Excel_FileName, FileName);
            awsupload_datafile(Constant.Aws_Bucket, FileName, UploadPath);
            LP.login(RedcapUsername,Password,Role);
            LP.verify_Lp_Attributes();
            LP.navigateToProj_Management();
            pmp.select_Project_OverviewElipse(Constant.UI_Project1);
            pmp.Navigate_To_DataMgmtIngest();
            pmp.verify_ManifestPage_RawNonSequence();
            pmp.MC_FileInput(FileName);
            String FileId = pmp.getFileId();
            String ProjId = pmp.getprojectId();
            pmp.MCCase_Input(Caseid);
            pmp.MCSample_MandatoryInput(Sample_Sub_Id);
            pmp.MCAlliquot_Mandatory_Input(Aliquot_Sub_Id);
            pmp.MC_Immuneprofile_Input(Immune_Profile);
            pmp.MC_Immune_WFInput(IP_WorkFlow);
            pmp.ManifestSave();
            pmp.fill_mandatory_tables("182");
            pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
            Common_Functions.normalwait(5000);
            pmp.validate_ManifestTable(FileName);
            pmp.Download_Json_Manifest();
            driver.navigate().refresh();
            Common_Functions.normalwait(5000);
            pmp.Edit_Manifest();
            pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
            pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
            pmp.Navigate_To_AppsCatalog();
            HM = CMP.fetch_file_details(FileName);
            aws.AWS_Login(Username, Password);
            aws.select_role("REUC1_MAY191684481557461_PROJECTADMIN",Role);
            aws.navigate_to_S3Bucket();
            aws.S3_Input(Constant.Aws_Bucket);
            String Aws_Time_1 = aws.validate_Error_folder(FileId+".json", OldTime);
            aws.navigate_to_S3Bucket_url();
            aws.S3_Input(Constant.Aws_Bucket);
            CMP.UpdateFile_To_Delete(DownloadPath, FileId+".json");
            CMP.UpdateFile_WithOut_Case(DownloadPath, FileId+".json", HM);
            Ex.Extent_Info("Delete MetaData Without Case id");
            awsupload_meta_datafile(Constant.Aws_Bucket,FileId+".json",DownloadPath);
            Common_Functions.normalwait(5000);
        aws.navigate_to_S3Bucket_url();
        aws.S3_Input(Constant.Aws_Bucket);
        String Aws_Time_2 = aws.validate_Error_folder(FileId+".json", Aws_Time_1);
        aws.navigate_to_S3Bucket_url();
        aws.S3_Input(Constant.Aws_Bucket);
        Ex.Extent_Info("Delete MetaData Without Sample id");
        CMP.UpdateFile_WithOut_Sample(DownloadPath, FileId+".json", HM);
        awsupload_meta_datafile(Constant.Aws_Bucket,FileId+".json",DownloadPath);
            Common_Functions.normalwait(5000);
        aws.navigate_to_S3Bucket_url();
        aws.S3_Input(Constant.Aws_Bucket);
        String Aws_Time_3 = aws.validate_Error_folder(FileId+".json", Aws_Time_2);
        aws.navigate_to_S3Bucket_url();
        aws.S3_Input(Constant.Aws_Bucket);
        Ex.Extent_Info("Delete MetaData Without Aliquot id");
        CMP.UpdateFile_WithOut_Aliquot(DownloadPath, FileId+".json", HM);
        awsupload_meta_datafile(Constant.Aws_Bucket,FileId+".json",DownloadPath);
        Common_Functions.normalwait(5000);
        aws.navigate_to_S3Bucket_url();
        aws.S3_Input(Constant.Aws_Bucket);
        String Aws_Time_4 = aws.validate_Error_folder(FileId+".json", Aws_Time_3);
        aws.navigate_to_S3Bucket_url();
        aws.S3_Input(Constant.Aws_Bucket);
        CMP.UpdateFile_WithOut_File(DownloadPath, FileId+".json", HM);
        awsupload_meta_datafile(Constant.Aws_Bucket,FileId+".json",DownloadPath);
        Common_Functions.normalwait(5000);
        aws.navigate_to_S3Bucket_url();
        aws.S3_Input(Constant.Aws_Bucket);
        aws.validate_Error_folder(FileId+".json", Aws_Time_4);
        aws.navigate_to_S3Bucket_url();
        aws.S3_Input(Constant.Aws_Bucket);
         Ex.Extent_Info("Delete MetaData With All Details:");
        CMP.UpdateFile_AllDetails(DownloadPath, FileId+".json", HM);
        awsupload_meta_datafile(Constant.Aws_Bucket,FileId+".json",DownloadPath);
        Common_Functions.normalwait(5000);
        LP.login(RedcapUsername, Password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToCatlog_Management();
        CMP.Select_View(View);
        CMP.validate_delete_file_UI(FileName);
        LP.LogOut();
        }
        finally {
            Change_File_Name(UploadPath, FileName, Excel_FileName);

        }


    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Aws_Admin_UserName,Constant.Password, Constant.Admin,Constant.Admin_Name}

        };
        return data;
    }

}





