
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
import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.hcl.redcap.TC178_VerifyRawSeq_UI_Ingestion.file_id_178;

@Listeners({ListenerClass.class})
public class TC193_Add_File_From_Aliquot extends base {

    @Test(dataProvider = "getData")
    @Description("Add File From Aliquot")
    public void TC193_DATAINGMGMT_Add_File_From_Aliquot(String username, String password, String Role) throws IOException, InterruptedException, ParseException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Add File From Aliquot").
                assignAuthor(Constant.Author).assignCategory(Constant.DI_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Excel_FileName = map.get("FileName");
        String Read_Group = map.get("Read_Group");
        String IP_WorkFlow = map.get("IP_WorkFlow");
        String Sample_Sub_Id = map.get("Sample_Sub_Id");
        String Aliquot_Sub_Id = map.get("Aliquot_Sub_Id");
        String Immune_Profile = map.get("Immune_Profile");
        String Inputs = map.get("Inputs");
        String[] Input = Inputs.split(",");
        String FileName = "File" + randomNumber() + ".fastq";

        String Caseid = "Case" + randomNumber();
        int FileCount = 0;

        if (Role.equalsIgnoreCase(Constant.Data_Manager) || Role.equalsIgnoreCase(Constant.Admin) || Role.equalsIgnoreCase(Constant.Data_User1)) {
            try {
                Change_File_Name(UploadPath, Excel_FileName, FileName);
                awsupload_datafile(Constant.Aws_Bucket, FileName, UploadPath);;
                pmp.MCCase_Input(Caseid);
                LP.login(username, password, Role);
                LP.verify_Lp_Attributes();
                LP.navigateToProj_Management();
                pmp.select_Project_OverviewElipse(Constant.UI_Project1);
                pmp.Navigate_To_DataMgmtIngest();
                //pmp.Navigate_To_CreateManifest();
                pmp.Navigate_To_CreateMetaData();
                pmp.MCCase_Input(Caseid);
                pmp.MCSample_MandatoryInput(Sample_Sub_Id);
                pmp.MCAlliquot_Mandatory_Input(Aliquot_Sub_Id);
                pmp.ManifestSave();
                pmp.fill_mandatory_tables("193");
                pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
                pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
                pmp.Navigate_To_AppsCatalog();
                CMP.navigate_To_Casedetails(Caseid);
                CMP.minimize_arrow();
                CMP.Grid_expand_Aliquots();
                pmp.AddFile_Aliquot(Role);
                pmp.CM_Seq_Non_Seq(Input[0]);
                pmp.verify_non_editable_case_fields();
                pmp.verify_non_editable_project_fields();
                pmp.verify_non_editable_sample_fields();
                pmp.verify_non_editable_aliquot_fields();
                pmp.verify_can_save_ingestBtn();
                pmp.Create_Manifest_Cancel_No();
                pmp.validate_Aliquot_Addfile_Error_Msg_WithoutData();
                pmp.validate_Aliquot_Addfile_Error_Msg_WithData(FileName, Input[0], Read_Group);
                pmp.validate_MC_IPWorkflowValues();
                pmp.MC_Immuneprofile_Input(Immune_Profile);
                pmp.MC_Immune_WFInput(IP_WorkFlow);
                pmp.MC_FileInput(FileName);
                String FileId = pmp.getFileId();
                pmp.ManifestSave();
                pmp.fill_mandatory_tables("193");
                pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
                pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
                Common_Functions.normalwait(60000);
                int FileCount1 = pmp.verify_file_count(FileCount);
                pmp.Navigate_To_AppsCatalog();
                CMP.navigate_To_FileDeatils(FileName);
                pmp.validate_UI_ImmuneProfile(Immune_Profile);
                LP.LogOut();
            } finally {
                Change_File_Name(UploadPath, FileName, Excel_FileName);
            }

        } else {
            //Roles That Dont Have Access
            LP.login(username, password, Role);
            LP.verify_Lp_Attributes();
            pmp.Navigate_To_AppsCatalog();
            CMP.Select_Case(file_id_178);
            CMP.minimize_arrow();
            CMP.Grid_expand_Aliquots();
            pmp.AddFile_Aliquot(Role);
            LP.LogOut();
        }


    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
                //{Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                /*{Constant.Unassigned_User_Name, Constant.Password, Constant.Unassigned_User},
                {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},
                {Constant.Privacy_Officer_Name, Constant.Password, Constant.Privacy_Officer}*/

        };
        return data;
    }


}





