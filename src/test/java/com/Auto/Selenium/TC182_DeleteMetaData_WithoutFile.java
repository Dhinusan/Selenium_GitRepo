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
public class TC182_DeleteMetaData_WithoutFile extends base {



    @Test(dataProvider = "getData")
    @Description("Verify Delete  operation in direct file drop\n" +
            "Verify Delete  operation in direct file drop with respect to Aliquot ID changes")
    public void TC182_2AWS_Delete_Multiple_Files(String Username, String Password, String Role, String RedcapUsername) throws IOException, InterruptedException, ParseException, AWTException, java.text.ParseException {
        Login_LandingPage LP = new Login_LandingPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        AWS aws = new AWS(driver);
        ExtentManager Ex = new ExtentManager();
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() +"_"+ Role, "TC182_2AWS_Delete_Multiple_Files").
                assignAuthor(Constant.Author).assignCategory(Constant.DI_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String AWS_Role = map.get("AWS_Role");
        String Excel_FileName = map.get("FileName");
        String[] Excel_FileNames = Excel_FileName.split(",");
        String S3_Project = map.get("S3_Project");
        String JsonFile = map.get("JsonFile");
        String[] Json_File = JsonFile.split(",");
        String View = map.get("View");
        String Sample_Sub_Id =map.get("Sample_Sub_Id");
        String Aliquot_Sub_Id =map.get("Aliquot_Sub_Id");
        String Immune_Profile =map.get("Immune_Profile");
        String IP_WorkFlow =map.get("IP_WorkFlow");
        String Inputs =map.get("Inputs");
        String Caseid = "Case" + randomNumber();
        HashMap<String,String> HM = new HashMap<>();
        String FileName1 = "File" + randomNumber() + ".fastq";
        String FileName2 = "File" + randomNumber() + ".fastq";

        try{
            Change_File_Name(UploadPath, Excel_FileNames[0], FileName1);
            Change_File_Name(UploadPath, Excel_FileNames[1], FileName2);
            awsupload_datafile(Constant.Aws_Bucket, FileName1, UploadPath);
            awsupload_datafile(Constant.Aws_Bucket, FileName2, UploadPath);
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
            pmp.fill_mandatory_tables("182");
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
            LP.LogOut();

            CMP.UpdateFileName(DownloadPath, FileId+".json", FileName2);
            CMP.UpdateFile_WithOut_File(DownloadPath, FileId+".json", HM);
            awsupload_meta_datafile(Constant.Aws_Bucket,FileId+".json",DownloadPath);
            LP.login(RedcapUsername, Password, Role);
            LP.verify_Lp_Attributes();
            LP.navigateToCatlog_Management();
            CMP.Select_View(View);
            HashMap HM1 = CMP.fetch_file_details(FileName2);
            LP.LogOut();
            aws.AWS_Login(Username, Password);
            aws.select_role("RUSE1_SEP621662455112153_PROJECTADMIN",Role);
            aws.navigate_to_S3Bucket();
            aws.S3_Input(Constant.Aws_Bucket);
            CMP.UpdateFile_To_Delete(DownloadPath, FileId+".json");
            CMP.UpdateFileName(DownloadPath, FileId+".json", FileName2);
            CMP.UpdateFile_AllDetails(DownloadPath,  FileId+".json", HM1);
            awsupload_meta_datafile(Constant.Aws_Bucket,FileId+".json",DownloadPath);
            LP.login(RedcapUsername, Password, Role);
            LP.verify_Lp_Attributes();
            LP.navigateToCatlog_Management();
            CMP.Select_View(View);
            CMP.validate_delete_file_UI(FileName2);
            LP.LogOut();
            CMP.UpdateFileName(DownloadPath, FileId+".json", FileName1);
            CMP.UpdateFile_AllDetails(DownloadPath, FileId+".json", HM);
            awsupload_meta_datafile(Constant.Aws_Bucket,FileId+".json",DownloadPath);
            LP.login(RedcapUsername, Password, Role);
            LP.verify_Lp_Attributes();
            LP.navigateToCatlog_Management();
            CMP.Select_View(View);
            CMP.validate_delete_file_UI(FileName1);
            LP.LogOut();
        }
       finally{
            Change_File_Name(UploadPath, FileName1, Excel_FileNames[0]);
            Change_File_Name(UploadPath, FileName2, Excel_FileNames[1]);
        }



    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Aws_Admin_UserName,Constant.Password, Constant.Admin,Constant.Admin_Name},

        };
        return data;
    }


}





