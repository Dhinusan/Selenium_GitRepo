
package com.hcl.redcap;

import Resources.*;
import com.redcapPage.ProjMgmtPage;
import io.qameta.allure.*;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.CatalogManagementPage;

import java.io.IOException;
import java.util.Map;

@Listeners({ListenerClass.class})
public class TC211_Verify_UpdateCase extends base {

    @Test(dataProvider = "getData")
    @Description("Catalog Managemnet UI - Verify Users can view updated details of a case on Case details page (With UPDATE action)")
    public void TC211_RawView_View_AdditionalDetails_Of_Updated_Case
            (String Username, String Password, String Role) throws IOException, InterruptedException, ParseException {
        Login_LandingPage LP = new Login_LandingPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ExtentManager Ex = new ExtentManager();
        ProjMgmtPage pmp = new ProjMgmtPage(driver);

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify Users can view updated details of a case on Case details page (With UPDATE action)").
                assignAuthor(Constant.Author).assignCategory(Constant.Cataloge_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Excel_FileName = map.get("FileName");
        String Primary_site = map.get("Primary_site");
        String Disease_type = map.get("Disease_type");
        String Consent_type = map.get("Consent_type");
        String Read_Group = map.get("Read_Group");
        String Aligned_Reads = map.get("Aligned_Reads");
        String Sample_Sub_Id = map.get("Sample_Sub_Id");
        String Aliquot_Sub_Id = map.get("Aliquot_Sub_Id");
        String Case_Sub_Id = "Case"+randomNumber();
        String FileName = "File" + randomNumber() + ".fastq";


        try{
            Change_File_Name(UploadPath, Excel_FileName, FileName);
            awsupload_datafile(Constant.Aws_Bucket, FileName, UploadPath);
            LP.login(Username,Password,Role);
            LP.verify_Lp_Attributes();
            LP.navigateToProj_Management();
            pmp.select_Project_OverviewElipse(Constant.UI_Project1);
            pmp.Navigate_To_DataMgmtIngest();
            pmp.Navigate_To_CreateManifest();
            pmp.Navigate_To_RawSequence();
            pmp.MC_FileInput(FileName);
            String FileId = pmp.getFileId();
            pmp.MCCase_MultipleInputs(Case_Sub_Id, Primary_site, Disease_type, Consent_type);
            pmp.MCSample_MandatoryInput(Sample_Sub_Id);
            pmp.MCAlliquot_Mandatory_Input(Aliquot_Sub_Id);
            pmp.MC_ReadGroup_Inputs(Read_Group);
            pmp.DF_Aligned_reads(Aligned_Reads);
            pmp.ManifestSave();
            pmp.fill_mandatory_tables("211");
            pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
            pmp.validate_ManifestTable(FileName);
            pmp.Download_Json_Manifest();
            driver.navigate().refresh();
            Common_Functions.normalwait(5000);
            pmp.Edit_Manifest();
            pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
            pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
            pmp.Navigate_To_AppsCatalog();
            CMP.navigate_To_FileDeatils(FileName);
            CMP.NavigateToCaseTabAndClickonCaseIDandverify();
            CMP.VerifyCaseTableOnCaseDetailPage(DownloadPath, FileId+".json");
            CMP.UpdateFile_To_Delete(DownloadPath, FileId+".json");
            CMP.Manipulate_Json(DownloadPath, FileId+".json");
            awsupload_meta_datafile(Constant.Aws_Bucket,FileId+".json",DownloadPath);
            LP.LogOut();
            Common_Functions.normalwait(60000);
            LP.login(Username, Password, Role);
            LP.verify_Lp_Attributes();
            LP.navigateToCatlog_Management();
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
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
             /*   {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},*/

        };
        return data;
    }

}

