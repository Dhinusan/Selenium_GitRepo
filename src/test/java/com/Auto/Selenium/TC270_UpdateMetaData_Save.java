
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
public class TC270_UpdateMetaData_Save extends base {

    @Test(dataProvider = "getData")
    @Description("Verify as a REDCAP Platform admin wants to add more metadata to a previously ingested record, so that the same is reflected in the system.")
    public void TC270_UsabilityImprovements_ADMIN_DM_DU1_UpdateMetadata_Download_01(String RedcapUsername, String Password, String Role, String Aws_username) throws IOException, InterruptedException, AWTException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        AWS aws = new AWS(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify system associates multiple files with the same metadata entered in a single manifest").
                assignAuthor(Constant.Author).assignCategory(Constant.Cataloge_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Excel_FileName = map.get("FileName");
        String Sample_ID = "Sample" + randomNumber();
        String Filename = "File" + randomNumber() + ".fastq";
        String Sample_Sub_Id = map.get("Sample_Sub_Id");
        String Aliquot_Sub_Id = map.get("Aliquot_Sub_Id");
        String Immune_Profile = map.get("Immune_Profile");
        String IP_WorkFlow = map.get("IP_WorkFlow");
        String Inputs = map.get("Inputs");
        String Caseid = "Case" + randomNumber();
        try {
            Change_File_Name(UploadPath, Excel_FileName, Filename);
            awsupload_datafile(Constant.Aws_Bucket, Filename, UploadPath);
            LP.login(RedcapUsername, Password, Role);
            LP.verify_Lp_Attributes();
            LP.navigateToProj_Management();
            pmp.select_Project_OverviewElipse(Constant.UI_Project1);
            pmp.Navigate_To_DataMgmtIngest();
            pmp.verify_ManifestPage_RawNonSequence();
            pmp.MC_FileInput(Filename);
            pmp.MCCase_Input(Caseid);
            pmp.MCSample_MandatoryInput(Sample_Sub_Id);
            pmp.MCAlliquot_Mandatory_Input(Aliquot_Sub_Id);
            pmp.MC_Immuneprofile_Input(Immune_Profile);
            pmp.MC_Immune_WFInput(IP_WorkFlow);
            pmp.ManifestSave();
            pmp.fill_mandatory_tables("270");
            pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
            pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
            pmp.Navigate_To_AppsCatalog();
            CMP.navigate_To_FileDeatils(Filename);
            CMP.select_update_metadata(Filename, Role);
            pmp.MCSample_MandatoryInput(Sample_ID);
            pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
            Common_Functions.normalwait(5000);
            pmp.update_Md_CancelBtn_Verify();
            Common_Functions.normalwait(3000);
            pmp.Navigate_To_Project_AppIcon();
            pmp.select_Project_OverviewElipse(Constant.UI_Project1);
            pmp.Navigate_To_DataMgmtIngest();
            pmp.verify_saved_manifest_forupdatemetadata(Filename);

        } finally {
            Change_File_Name(UploadPath, Filename, Excel_FileName);
        }
    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin, Constant.Aws_Admin_UserName},
        };
        return data;
    }


}





