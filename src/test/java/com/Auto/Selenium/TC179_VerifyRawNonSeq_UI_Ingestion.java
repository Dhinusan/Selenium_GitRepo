
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
public class TC179_VerifyRawNonSeq_UI_Ingestion extends base


{
    public static String File;
    @Test(dataProvider = "getData")
    @Description("Verify the RAW NONSEQ Manifest file with  Required + optional tables ingestion in UI and also verify  the ingested file in the catalog UI")
    public void  TC179_Verify_RawNonSeq_UI_Ingestion(String RedcapUsername,String Password,String Role,String Aws_username) throws IOException, InterruptedException, AWTException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        AWS aws = new AWS(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() +"_"+ Role, "TC179_Verify_RawNonSeq_UI_Ingestion").
                assignAuthor(Constant.Author).assignCategory(Constant.DI_Management);

        Map<String,String> map =base.ExcelData.get(getClass().getSimpleName());
        String Excel_FileName = map.get("FileName");
        String Sample_Sub_Id =map.get("Sample_Sub_Id");
        String Aliquot_Sub_Id =map.get("Aliquot_Sub_Id");
        String Immune_Profile =map.get("Immune_Profile");
        String IP_WorkFlow =map.get("IP_WorkFlow");
        String Inputs =map.get("Inputs");
        String Caseid = "Case" + randomNumber();
        String Filename ="File"+randomNumber()+".fastq";
        Change_File_Name(UploadPath, Excel_FileName, Filename);
        try{
            awsupload_datafile(Constant.Aws_Bucket, Filename, UploadPath);
            LP.login(RedcapUsername,Password,Role);
            LP.verify_Lp_Attributes();
            LP.navigateToProj_Management();
            pmp.select_Project_OverviewElipse(Constant.UI_Project1);
            pmp.Navigate_To_DataMgmtIngest();
            pmp.verify_ManifestPage_RawNonSequence();
            pmp.MC_FileInput(Filename);
            String FileId = pmp.getFileId();
            String ProjId = pmp.getprojectId();
            pmp.MCCase_Input(Caseid);
            pmp.MCSample_MandatoryInput(Sample_Sub_Id);
            pmp.MCAlliquot_Mandatory_Input(Aliquot_Sub_Id);
            pmp.MC_Immuneprofile_Input(Immune_Profile);
            pmp.MC_Immune_WFInput(IP_WorkFlow);
            pmp.ManifestSave();
            pmp.fill_mandatory_tables("179");
            pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
            pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
            pmp.Navigate_To_AppsCatalog();
            CMP.navigate_To_FileDeatils(Filename);
            CMP.verify_filedetails_headers();
            CMP.Validate_FileValues(FileId, Filename, Sample_Sub_Id,Inputs,Immune_Profile);
            CMP.verify_FileSummary_Values(ProjId,Inputs, Filename, RedcapUsername, Immune_Profile,IP_WorkFlow, FileId);
            LP.LogOut();
        }
        finally {
            Change_File_Name(UploadPath, Filename,Excel_FileName);
        }

    }

    @DataProvider
    public Object[][] getData()
    {
        Object[][] data =new Object[][] {
                //{Constant.Admin_Name,Constant.Password,Constant.Admin, Constant.Aws_Admin_UserName},
                {Constant.Data_Manager_Name,Constant.Password,Constant.Data_Manager,Constant.Aws_Admin_UserName},
                //{Constant.Data_User1_Name,Constant.Password,Constant.Data_User1,Constant.Aws_Admin_UserName}
        };
        return data;
    }






}





