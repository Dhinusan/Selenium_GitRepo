
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
public class TC261_Csv_Upload_RawFiles extends base {

    @Test(dataProvider = "getData")
    @Description("Verify as REDCAP system, the user want to process the .CSV file after successful upload, so that ingestion process can be completed.")
    public void TC261_BulkManifestCreationthroughCSV_ADMIN_DM_DU1_UploadManifestinCSV_Func_01(String username, String password, String Role) throws IOException, InterruptedException, ParseException, AWTException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ExtentManager Ex = new ExtentManager();
        ProjMgmtPage pmp = new ProjMgmtPage(driver);

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify as REDCAP system, the user want to process the .CSV file after successful upload, so that ingestion process can be completed.").
                assignAuthor(Constant.Author).assignCategory(Constant.Project_Management);
        // CMP.update_csv_Values("jwjoomrmor4waht088bkzu.csv");
        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Non_Seq_RawFiles = map.get("FileName");
        String Seq_RawFiles = map.get("FileName2");
        String JsonFile = map.get("JsonFile");
        String[] CsvFile = JsonFile.split(",");
        String S3_Project = map.get("S3_Project");
        String Input = map.get("Inputs");
        String[] Case_Id = Input.split(",");
        String Input2 = map.get("Case_Sub_Id");
        String[] Case_Id_2 = Input2.split(",");


        //Raw Non-Seq
        LP.login(username, password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToProj_Management();
        pmp.Navigate_To_AppsCatalog();
        CMP.navigate_To_Casedetails(Case_Id[0]);
        CMP.minimize_arrow();
        CMP.Grid_expand_Aliquots();
        int Aliquot_FileCount1 = pmp.get_aliquot_filecount();
        pmp.Navigate_To_AppsCatalog();
        CMP.navigate_To_Casedetails(Case_Id[1]);
        int Case_FileCount1 = CMP.fetch_totalFilecount();
        pmp.Navigate_To_AppsCatalog();
        CMP.navigate_To_Casedetails(Case_Id[2]);
        int Sample_FileCount1 = CMP.fetch_totalFilecount();
        awsupload_datafile(S3_Project, Non_Seq_RawFiles, UploadPath);
        pmp.Navigate_To_Project_AppIcon();
        pmp.select_Project_OverviewElipse(Constant.UI_Project1);
        pmp.Navigate_To_DataMgmtIngest();
        pmp.upload_csvfile(UploadPath, CsvFile[0]);
        pmp.verify_csvupload_successmsg();
        String Manifest_ID_RawNS = pmp.verify_ManifestOverviewData(CsvFile[0], username);
        pmp.Verify_Ingestion_MO(CsvFile[0], Manifest_ID_RawNS);


        pmp.Navigate_To_AppsCatalog();
        CMP.navigate_To_Casedetails(Case_Id[0]);
        CMP.minimize_arrow();
        CMP.Grid_expand_Aliquots();
        pmp.verify_Aliquotfile_count(Aliquot_FileCount1);
        pmp.Navigate_To_AppsCatalog();
        CMP.navigate_To_Casedetails(Case_Id[1]);
        CMP.verify_file_count(Case_FileCount1);
        pmp.Navigate_To_AppsCatalog();
        CMP.navigate_To_Casedetails(Case_Id[2]);
        CMP.verify_file_count(Sample_FileCount1);
        LP.LogOut();


        //Raw File Seq Validation
        /*LP.login(username, password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToProj_Management();
        pmp.Navigate_To_AppsCatalog();
        CMP.navigate_To_Casedetails(Case_Id_2[0]);
        CMP.minimize_arrow();
        CMP.Grid_expand_Aliquots();
        int Aliquot_FileCount = pmp.get_aliquot_filecount();
        pmp.Navigate_To_AppsCatalog();
        CMP.navigate_To_Casedetails(Case_Id_2[1]);
        int Case_FileCount = CMP.fetch_totalFilecount();
        pmp.Navigate_To_AppsCatalog();
        CMP.navigate_To_Casedetails(Case_Id_2[2]);
        int Sample_FileCount = CMP.fetch_totalFilecount();
        awsupload_datafile(S3_Project, Seq_RawFiles, UploadPath);
        pmp.Navigate_To_Project_AppIcon();
        pmp.select_Project_OverviewElipse(UI_Project);
        pmp.Navigate_To_DataMgmtIngest();
        pmp.upload_csvfile(UploadPath, CsvFile[1]);
        pmp.verify_csvupload_successmsg();
        String Manifest_ID_RawSeq = pmp.verify_ManifestOverviewData(CsvFile[1], username);
        pmp.Verify_Ingestion_MO(CsvFile[1], Manifest_ID_RawSeq);


        pmp.Navigate_To_AppsCatalog();
        CMP.navigate_To_Casedetails(Case_Id_2[0]);
        CMP.minimize_arrow();
        CMP.Grid_expand_Aliquots();
        pmp.verify_Aliquotfile_count(Aliquot_FileCount);
        pmp.Navigate_To_AppsCatalog();
        CMP.navigate_To_Casedetails(Case_Id_2[1]);
        CMP.verify_file_count(Case_FileCount);
        pmp.Navigate_To_AppsCatalog();
        CMP.navigate_To_Casedetails(Case_Id_2[2]);
        CMP.verify_file_count(Sample_FileCount);*/


    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
                /*{Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Unassigned_User_Name, Constant.Password, Constant.Unassigned_User},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},
                {Constant.Privacy_Officer_Name, Constant.Password, Constant.Privacy_Officer}*/
        };
        return data;
    }


}






