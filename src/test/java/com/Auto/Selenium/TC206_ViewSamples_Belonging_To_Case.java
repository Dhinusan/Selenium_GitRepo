
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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Listeners({ListenerClass.class})
public class  TC206_ViewSamples_Belonging_To_Case extends base {
    public static  String case_id_206;
    public static  String file_id_206;
    @Test(dataProvider = "getData")
    @Description("Catalog Management UI - Verify users can view details of samples belonging to a case on Case details page to identify the sample they need.")

    public void TC206__RawView_ViewSamples_belongingto_Case(String username, String password, String Role) throws IOException, InterruptedException, ParseException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ExtentManager Ex = new ExtentManager();
        ProjMgmtPage pmp = new ProjMgmtPage(driver);

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Catalog Management UI - Verify users can view details of samples belonging to a case on Case details page to identify the sample they need.").
                assignAuthor(Constant.Author).assignCategory(Constant.Cataloge_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Excel_FileName = map.get("FileName");
        String View = map.get("View");
        String Inputs = map.get("Inputs");
        String FileName = "File" + randomNumber() + ".fastq";
        String Sample_Sub_Id = map.get("Sample_Sub_Id");
        String Aliquot_Sub_Id = map.get("Aliquot_Sub_Id");
        String IP_WorkFlow = map.get("IP_WorkFlow");
        String Casesubid = "Case" + randomNumber();
        String Immune_Profile =map.get("Immune_Profile");
        String Sample_Tumor_Descriptor = map.get("Sample_Tumor_Descriptor");
        String Sample_Composition = map.get("Sample_Composition");
        String Sample_BAS = map.get("Sample_BAS");
        String Sample_Bio_laterality = map.get("Sample_Bio_laterality");

        if (Role.equalsIgnoreCase(Constant.Admin)) {
        try {
            Change_File_Name(UploadPath, Excel_FileName, FileName);
            awsupload_datafile(Constant.Aws_Bucket, FileName, UploadPath);
            LP.login(username,password,Role);
            LP.verify_Lp_Attributes();
            LP.navigateToProj_Management();
            pmp.select_Project_OverviewElipse(Constant.UI_Project1);
            pmp.Navigate_To_DataMgmtIngest();
            pmp.verify_ManifestPage_RawNonSequence();
            pmp.MC_FileInput(FileName);
            file_id_206 = pmp.getFileId();
            pmp.MCCase_Input(Casesubid);
            pmp.MCSample_MultipleInputs(Sample_Sub_Id, Sample_Tumor_Descriptor, Sample_Composition, Sample_BAS, Sample_Bio_laterality);
            pmp.MCAlliquot_Mandatory_Input(Aliquot_Sub_Id);
            pmp.MC_Immuneprofile_Input(Immune_Profile);
            pmp.MC_Immune_WFInput(IP_WorkFlow);
            pmp.ManifestSave();
            pmp.fill_mandatory_tables("206");
            pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
            pmp.validate_ManifestTable(FileName);
            pmp.Download_Json_Manifest();
            driver.navigate().refresh();
            Common_Functions.normalwait(5000);
            pmp.Edit_Manifest();
            pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
            pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
            pmp.Navigate_To_AppsCatalog();
            CMP.Select_View(View);
            case_id_206 = CMP.fetchcaseid(FileName);
            jsonfileupdate(case_id_206, DownloadPath, file_id_206+".json");
            awsupload_datafile(Constant.Aws_Bucket, FileName, UploadPath);
            Common_Functions.normalwait(5000);
            awsupload_meta_datafile(Constant.Aws_Bucket,file_id_206+".json",DownloadPath);
            Common_Functions.normalwait(5000);
            awsupload_datafile(Constant.Aws_Bucket, FileName, UploadPath);
            Common_Functions.normalwait(5000);
            awsupload_meta_datafile(Constant.Aws_Bucket,file_id_206+".json",DownloadPath);
            Common_Functions.normalwait(5000);
            awsupload_datafile(Constant.Aws_Bucket, FileName, UploadPath);
            Common_Functions.normalwait(5000);
            awsupload_meta_datafile(Constant.Aws_Bucket,file_id_206+".json",DownloadPath);
            Common_Functions.normalwait(5000);
            CMP.navigate_to_casedetails();
            CMP.minimize_arrow();
            CMP.Grid_expand_Samples();
            HashMap<String, String> JsonValues = CMP.jsonread(DownloadPath, file_id_206+".json", Inputs);
            System.out.println(JsonValues);
            CMP.fetch_UI_Sampledetails(JsonValues);
            LP.LogOut();
        } finally {
            Change_File_Name(UploadPath, FileName, Excel_FileName);
        }
        } else {
            //Roles That Dont Have Access
            LP.login(username, password, Role);
            LP.verify_Lp_Attributes();
            pmp.Navigate_To_AppsCatalog();
            Common_Functions.page_loader();
            CMP.Select_Case(case_id_206);
            CMP.minimize_arrow();
            CMP.Grid_expand_Samples();
            HashMap<String, String> JsonValues = CMP.jsonread(DownloadPath, file_id_206+".json", Inputs);
            System.out.println(JsonValues);
            CMP.fetch_UI_Sampledetails(JsonValues);
            LP.LogOut();
        }
    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
                /*{Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},
                {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2},
                {Constant.Privacy_Officer_Name, Constant.Password, Constant.Privacy_Officer}*/
        };
        return data;
    }


}





