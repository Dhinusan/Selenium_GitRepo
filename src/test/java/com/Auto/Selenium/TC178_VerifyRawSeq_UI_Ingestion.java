
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
import java.util.Map;

import static com.hcl.redcap.TC179_VerifyRawNonSeq_UI_Ingestion.File;
;
@Listeners({ListenerClass.class})
public class TC178_VerifyRawSeq_UI_Ingestion extends base {

    public static  String case_id_178;
    public static  String file_id_178;
    @Test(dataProvider = "getData")
    @Description("Verify the RAW SEQ Manifest file with  Required + optional tables ingestion in UI and also verify  the ingested file in the catalog UI")
    public void TC178_RawSeq_UI_Ingestion(String username, String password, String Role, String Aws_Username) throws IOException, InterruptedException, ParseException, AWTException {
        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ExtentManager Ex = new ExtentManager();

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "TC178_RawSeq_UI_Ingestion").
                assignAuthor(Constant.Author).assignCategory(Constant.DI_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Excel_FileName = map.get("FileName");
        String Sample_Sub_Id = map.get("Sample_Sub_Id");
        String Aliquot_Sub_Id = map.get("Aliquot_Sub_Id");
        String Aligned_Reads = map.get("Aligned_Reads");
        String Read_Group = map.get("Read_Group");
        String IP_WorkFlow = map.get("IP_WorkFlow");
        String Inputs = map.get("Inputs");
        String FileName = "File" + randomNumber() + ".fastq";
        String Caseid = "Case" + randomNumber();

        try {
            Change_File_Name(UploadPath, Excel_FileName, FileName);
            awsupload_datafile(Constant.Aws_Bucket, FileName, UploadPath);
            LP.login(username, password, Role);
            LP.verify_Lp_Attributes();
            LP.navigateToProj_Management();
            pmp.select_Project_OverviewElipse(Constant.UI_Project1);
            pmp.Navigate_To_DataMgmtIngest();
            pmp.Navigate_To_CreateManifest();
            pmp.Navigate_To_RawSequence();
            pmp.MCCase_Input(Caseid);
            pmp.MCSample_MandatoryInput(Sample_Sub_Id);
            pmp.MCAlliquot_Mandatory_Input(Aliquot_Sub_Id);
            pmp.DF_Aligned_reads(Aligned_Reads);
            pmp.MC_ReadGroup_Inputs(Read_Group);
            pmp.MC_FileInput(FileName);
            String FileId = pmp.getFileId();
            String ProjId = pmp.getprojectId();
            pmp.MC_Immune_WFInput(IP_WorkFlow);
            pmp.ManifestSave();
            pmp.fill_mandatory_tables("178");
            pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
            pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
            pmp.Navigate_To_AppsCatalog();
            case_id_178 = CMP.fetchcaseid(FileName);
            CMP.navigate_to_file_header();
            //CMP.navigate_To_FileDeatils(FileName);
            CMP.verify_filedetails_headers();
            CMP.Validate_FileValues(FileId, FileName, Sample_Sub_Id, Inputs, Aligned_Reads);
            CMP.verify_FileSummary_Values(ProjId, Inputs,FileName, username, Aligned_Reads, IP_WorkFlow, FileId);
            LP.LogOut();
        } finally {
            Change_File_Name(UploadPath, FileName, Excel_FileName);
        }
    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin, Constant.Aws_Admin_UserName},
                /*{Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager, Constant.Aws_Admin_UserName},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1, Constant.Aws_Admin_UserName}*/

        };

        return data;
    }


}





