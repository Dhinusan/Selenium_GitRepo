

package com.hcl.redcap;

import Resources.*;
import com.redcapPage.AWS;
import io.qameta.allure.*;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.CatalogManagementPage;
import com.redcapPage.ProjMgmtPage;

import java.awt.*;
import java.io.IOException;
import java.util.Map;

@Listeners({ListenerClass.class})
public class TC215_Add_MultipleFiles_UI extends base {

    @Test(dataProvider = "getData", description = "TCIT_DATAINGMGMT_ADMIN DM DU1_UI_Manifest Creation_Add_MultipleFiles_01")
    @Severity(SeverityLevel.NORMAL)
    @Description("Data Ingestion: Verify user able to add max of 4 filenames while creating manifest file from UI")
    @Story("As a REDCAP Platform admin, Data Manager and Data User-I, I want to enter multiple file names on a manifest that I create on UI, so that I can re-use the metadata for ingesting the files.")
    @Epic("Data management")


    public void TC215_DATAINGMGMT_ADMIN_DM_DU1_UI_Manifest_Creation_Add_MultipleFiles(String Username, String Password, String Role) throws IOException, InterruptedException, ParseException, AWTException {
        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ExtentManager Ex = new ExtentManager();

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify user able to add max of 4 filenames while creating manifest file from UI").
                assignAuthor(Constant.Author).assignCategory(Constant.DI_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Excel_FileName = map.get("FileName");
        String[] Exfile = Excel_FileName.split(",");
        String Aligned_Reads = map.get("Aligned_Reads");
        String S3_Project = map.get("S3_Project");
        String Incorrect_FileName = map.get("Incorrect_FileName");
        String Primary_site = map.get("Primary_site");
        String Disease_type = map.get("Disease_type");
        String Consent_type = map.get("Consent_type");
        String Read_Group = map.get("Read_Group");
        String Aliquot_Sub_Id = map.get("Aliquot_Sub_Id");
        String SSI = "SSI215" + randomNumber();
        String Case_Sub_Id = map.get("Case_Sub_Id");


        LP.login(Username, Password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToProj_Management();
        pmp.select_Project_OverviewElipse(Constant.UI_Project1);
        pmp.Navigate_To_DataMgmtIngest();
        pmp.Navigate_To_CreateManifest();
        pmp.Navigate_To_RawSequence();
        awsupload_datafile(Constant.Aws_Bucket, Exfile[1], UploadPath);
        pmp.verifyInvalid_Filename_onAll4Files(Incorrect_FileName);
        pmp.Valid_Filename_onAll4Files(Excel_FileName);
        pmp.MCCase_MultipleInputs(Case_Sub_Id, Primary_site, Disease_type, Consent_type);
        pmp.MCSample_MandatoryInput(SSI);
        pmp.MC_ReadGroup_Inputs(Read_Group);
        pmp.MCAlliquot_Mandatory_Input(Aliquot_Sub_Id);
        pmp.DF_Aligned_reads(Aligned_Reads);
        pmp.Navigate_MCFile();
        pmp.ManifestSave();
        pmp.fill_mandatory_tables("215");
        pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
        pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
        pmp.Navigate_To_AppsCatalog();
        CMP.navigate_To_FileDeatils(SSI);
        Common_Functions.normalwait(4000);
        CMP.selectcaseid();
        CMP.minimize_arrow();
        CMP.Grid_expand_case();
        CMP.Validate_UI_CaseDetails(Consent_type, Disease_type, Primary_site);
        LP.LogOut();
    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name,Constant.Password,Constant.Admin},
               /* {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},*/
        };
        return data;
    }


}

