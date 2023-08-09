
package com.hcl.redcap;

import Resources.*;
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

@Listeners({ListenerClass.class})
public class TC261_Csv_Upload_MetaData extends base {

    @Test(dataProvider = "getData")
    @Description("Verify as REDCAP system, the user want to process the .CSV file after successful upload, so that ingestion process can be completed.")
    public void TC261_BulkManifestCreationthroughCSV_ADMIN_DM_DU1_UploadManifestinCSV_Func_01(String username, String password, String Role) throws IOException, InterruptedException, ParseException, AWTException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ExtentManager Ex = new ExtentManager();
        ProjMgmtPage pmp = new ProjMgmtPage(driver);

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify as REDCAP system, the user want to process the .CSV file after successful upload, so that ingestion process can be completed.").
                assignAuthor(Constant.Author).assignCategory(Constant.Project_Management);
        String Case1 = "Case" + randomNumber();
        String Case2 = "Case" + randomNumber();
        String Case3 = "Case" + randomNumber();
        String Case4 = "Case" + randomNumber();
        String Sample2 = "Sample" + randomNumber();
        String Sample4 = "Sample" + randomNumber();
        String Aliquot2 = "Aliquot" + randomNumber();
        String Aliquot4 = "Aliquot" + randomNumber();

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Excel_FileName = map.get("FileName");

        //MetaData File Validation
        LP.login(username, password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToProj_Management();
        pmp.select_Project_OverviewElipse(Constant.UI_Project1);
        pmp.Navigate_To_DataMgmtIngest();
        // pmp.Navigate_To_CreateManifest();
        pmp.Navigate_To_CreateMetaData();
        pmp.MCCase_Input(Case3);
        pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
        pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
        //pmp.Navigate_To_CreateManifest();
        pmp.Navigate_To_CreateMetaData();
        pmp.MCCase_Input(Case4);
        pmp.MCSample_MandatoryInput(Sample4);
        pmp.MCAlliquot_Mandatory_Input(Aliquot4);
        pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
        pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
        pmp.Navigate_To_AppsCatalog();
        CMP.navigate_To_Casedetails(Case3);
        String New_CaseID = pmp.fetch_caseid_catalog();
        System.out.println(New_CaseID);
        pmp.Navigate_To_AppsCatalog();
        CMP.navigate_To_Casedetails(Case4);
        String Case_Id2 = pmp.fetch_caseid_catalog();
        CMP.minimize_arrow();
        CMP.Grid_expand_Samples();
        String Sample_Id = pmp.fetch_sample_catalog();
        System.out.println(Sample_Id);
        CMP.update_csv_Values(Excel_FileName, Case1, Case2, Sample2, Aliquot2, Case3, New_CaseID, Case_Id2, Sample4, Aliquot4, Sample_Id);
        pmp.Navigate_To_Project_AppIcon();
        pmp.select_Project_OverviewElipse(Constant.UI_Project1);
        pmp.Navigate_To_DataMgmtIngest();
        pmp.upload_csvfile(UploadPath, Excel_FileName);
        pmp.verify_csvupload_successmsg();
        String Manifest_MetaData = pmp.verify_ManifestOverviewData(Excel_FileName, username);
        pmp.Verify_Ingestion_MO(Excel_FileName, Manifest_MetaData);
        //Verify MetaData In UI

        pmp.Navigate_To_AppsCatalog();
        CMP.navigate_To_Casedetails(Case1);
        pmp.Navigate_To_AppsCatalog();
        CMP.navigate_To_Casedetails(Case2);
        CMP.minimize_arrow();
        CMP.Grid_expand_Samples();
        CMP.Validate_UI_sample_MandatoryDetail(Sample2);
        CMP.minimize_arrow();
        CMP.Grid_expand_Aliquots();
        CMP.Validate_UI_AliquotMandatorydetails(Aliquot2);
        pmp.Navigate_To_AppsCatalog();
        CMP.navigate_To_Casedetails(Case3);
        CMP.minimize_arrow();
        CMP.Grid_expand_Samples();
        CMP.Validate_UI_sample_MandatoryDetail(Sample2);
        CMP.minimize_arrow();
        CMP.Grid_expand_Aliquots();
        CMP.Validate_UI_AliquotMandatorydetails(Aliquot2);
        pmp.Navigate_To_AppsCatalog();
        CMP.navigate_To_Casedetails(Case_Id2);
        CMP.minimize_arrow();
        CMP.Grid_expand_Aliquots();
        CMP.Validate_UI_AliquotMandatorydetails(Aliquot4);


    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
               /* {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Unassigned_User_Name, Constant.Password, Constant.Unassigned_User},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},
                {Constant.Privacy_Officer_Name, Constant.Password, Constant.Privacy_Officer}*/
        };
        return data;
    }


}






