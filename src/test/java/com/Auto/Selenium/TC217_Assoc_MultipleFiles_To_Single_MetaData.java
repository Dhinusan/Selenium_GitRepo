
package com.hcl.redcap;

import Resources.*;
import io.qameta.allure.*;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.CatalogManagementPage;
import com.redcapPage.ProjMgmtPage;

import java.io.IOException;
import java.util.Map;

@Listeners({ListenerClass.class})
public class TC217_Assoc_MultipleFiles_To_Single_MetaData extends base {

    @Test(dataProvider = "getData", description = "TCIT_DATAINGMGMT_ADMIN DM DU1_UI_View Same Metadata_For MultipleFile")
    @Severity(SeverityLevel.NORMAL)
    @Description("Data Ingestion: Verify system associates multiple files with the same metadata entered in a single manifest")
    @Story("As a REDCAP Platform admin, Data Manager and Data User-I, I want to enter multiple file names on a manifest that I create on UI, so that I can re-use the metadata for ingesting the files.")
    @Epic("Data management")


    public void TC217_DATAINGMGMT_ADMIN_DM_DU1_UI_View_Same_Metadata_For_MultipleFile(String Username, String Password, String Role) throws IOException, InterruptedException, ParseException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify system associates multiple files with the same metadata entered in a single manifest").
                assignAuthor(Constant.Author).assignCategory(Constant.DI_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Excel_FileName = map.get("FileName");
        String[] Exfile = Excel_FileName.split(",");
        String Aligned_Reads = map.get("Aligned_Reads");
        String S3_Project = map.get("S3_Project");
        String Primary_site = map.get("Primary_site");
        String Disease_type = map.get("Disease_type");
        String Consent_type = map.get("Consent_type");
        String Read_Group = map.get("Read_Group");
        String Aliquot_Sub_Id = map.get("Aliquot_Sub_Id");
        String Case_Sub_Id = "Case" + randomNumber();
        String SSI = "SSI217" + randomNumber();



        LP.login(Username, Password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToProj_Management();
        pmp.select_Project_OverviewElipse(Constant.UI_Project1);
        pmp.Navigate_To_DataMgmtIngest();
        pmp.Navigate_To_CreateManifest();
        pmp.Navigate_To_RawSequence();
        awsupload_datafile(Constant.Aws_Bucket, Exfile[1], UploadPath);
        awsupload_datafile(Constant.Aws_Bucket, Exfile[2], UploadPath);
        awsupload_datafile(Constant.Aws_Bucket, Exfile[3], UploadPath);
        awsupload_datafile(Constant.Aws_Bucket, Exfile[4], UploadPath);
        //enter Presigned URL in file 3 and file 4
        pmp.Valid_Filename_onAll4Files(Excel_FileName);
        pmp.MCCase_MultipleInputs(Case_Sub_Id, Primary_site, Disease_type, Consent_type);
        pmp.MCSample_MandatoryInput(SSI);
        pmp.MC_ReadGroup_Inputs(Read_Group);
        pmp.MCAlliquot_Mandatory_Input(Aliquot_Sub_Id);
        pmp.DF_Aligned_reads(Aligned_Reads);
        pmp.Navigate_MCFile();
        pmp.ManifestSave();
        pmp.fill_mandatory_tables("217");
        pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
        pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
        pmp.Navigate_To_AppsCatalog();
        Common_Functions.normalwait(120000);
        pmp.Navigate_To_AppsCatalog();
        CMP.navigate_To_FileDeatils(SSI);
        CMP.validate_Aligned_reads_Values(Aligned_Reads);
        CMP.selectcaseid();
        CMP.minimize_arrow();
        CMP.Grid_expand_case();
        CMP.Validate_UI_CaseDetails(Consent_type, Disease_type, Primary_site);
        //.Verify4FilesWhenBackFromCaseDetails(Aligned_Reads);
        LP.LogOut();


    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
              /* {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
               {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},*/

        };
        return data;
    }


}

