
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
import java.util.ArrayList;
import java.util.Map;

import static com.hcl.redcap.TC178_VerifyRawSeq_UI_Ingestion.case_id_178;
import static com.hcl.redcap.TC178_VerifyRawSeq_UI_Ingestion.file_id_178;

@Listeners({ListenerClass.class})
public class TC192_Add_File_From_Sample extends base {

    @Test(dataProvider = "getData")
    @Description("Add file from sample")
    public void TC192_DATAINGMGMT_Add_File_From_Sample(String username, String password, String Role) throws IOException, InterruptedException, ParseException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Add file from sample").
                assignAuthor(Constant.Author).assignCategory(Constant.DI_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Excel_FileName = map.get("FileName");
        String Read_Group = map.get("Read_Group");
        String S3_Project = map.get("S3_Project");
        String IP_WorkFlow = map.get("IP_WorkFlow");
        String Sample_Sub_Id = map.get("Sample_Sub_Id");
        String[] Samp_Sub_Id = Sample_Sub_Id.split(",");
        String Sample_Tumor_Descriptor = map.get("Sample_Tumor_Descriptor");
        String Sample_Composition = map.get("Sample_Composition");
        String Sample_BAS = map.get("Sample_BAS");
        String Sample_Bio_laterality = map.get("Sample_Bio_laterality");
        String Aliquot_Sub_Id = map.get("Aliquot_Sub_Id");
        String[] Aliq_Sub_Id = Aliquot_Sub_Id.split(",");
        String Aliq_Amount = map.get("Aliq_Amount");
        String Source_center = map.get("Source_center");
        String Analyte_Type = map.get("Analyte_Type");
        String Concentration = map.get("Concentration");
        String Aligned_Reads = map.get("Aligned_Reads");
        String Immune_Profile = map.get("Immune_Profile");
        String Inputs = map.get("Inputs");
        String Excel_Case_Id = map.get("Case_Sub_Id");
        String[] Input = Inputs.split(",");
        String FileName = "File" + randomNumber() + ".fastq";
        String Caseid = "Case" + randomNumber();
        ArrayList<String> Array = new ArrayList<>();
        int FileCount = 0;


        if (Role.equalsIgnoreCase(Constant.Data_Manager) || Role.equalsIgnoreCase(Constant.Admin) || Role.equalsIgnoreCase(Constant.Data_User1)) {
            try {
                Change_File_Name(UploadPath, Excel_FileName, FileName);
                awsupload_datafile(Constant.Aws_Bucket, FileName, UploadPath);
                LP.login(username, password, Role);
                LP.verify_Lp_Attributes();
                LP.navigateToProj_Management();
                pmp.select_Project_OverviewElipse(Constant.UI_Project1);
                pmp.Navigate_To_DataMgmtIngest();
                //pmp.Navigate_To_CreateManifest();
                pmp.Navigate_To_CreateMetaData();
                pmp.MCCase_Input(Caseid);
                pmp.MCSample_MandatoryInput(Samp_Sub_Id[0]);
                pmp.ManifestSave();
                pmp.fill_mandatory_tables("192");
                pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
                pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
                pmp.Navigate_To_AppsCatalog();
                CMP.navigate_To_Casedetails(Caseid);
                CMP.minimize_arrow();
                CMP.Grid_expand_Samples();
                pmp.AddFile_Sample(Role,"Sequence");
                //pmp.CM_Seq_Non_Seq(Input[0]);
                pmp.verify_non_editable_case_fields();
                pmp.verify_non_editable_project_fields();
                pmp.verify_non_editable_sample_fields();
                pmp.verify_can_save_ingestBtn();
                pmp.Create_Manifest_Cancel_No();
                pmp.validate_Sample_Addfile_Error_Msg_WithoutData();
                pmp.validate_Sample_Addfile_Error_Msg_WithData(Aliq_Sub_Id[0], FileName, Input[0], Aligned_Reads, Read_Group);
                pmp.MCAliquot_MultipleInputs(Aliq_Sub_Id[0], Aliq_Amount, Source_center, Analyte_Type, Concentration);
                pmp.DF_Aligned_reads(Aligned_Reads);
                pmp.MC_FileInput(FileName);
                pmp.MC_ReadGroup_Inputs(Read_Group);
                pmp.MC_Immune_WFInput(IP_WorkFlow);
                pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
                pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
                int FileCount1 = pmp.verify_file_count(FileCount);
                CMP.minimize_arrow();
                CMP.Grid_expand_Aliquots();
                CMP.Validate_UI_Aliquotdetails_new(Aliq_Sub_Id[0], Aliq_Amount, Source_center, Analyte_Type, Concentration);
                pmp.Sel_AddSampAliquot(Role);
                pmp.MCSample_MultipleInputs(Samp_Sub_Id[1], Sample_Tumor_Descriptor, Sample_Composition, Sample_BAS, Sample_Bio_laterality);
                pmp.MCAliquot_MultipleInputs(Aliq_Sub_Id[1], Aliq_Amount, Source_center, Analyte_Type, Concentration);
                pmp.DF_Aligned_reads(Aligned_Reads);
                pmp.ManifestSave();
                pmp.fill_mandatory_tables("192");
                pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
                pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
                Common_Functions.normalwait(30000);
                driver.navigate().refresh();
                CMP.minimize_arrow();
                CMP.Grid_expand_Samples();
                CMP.Validate_UI_SampleDetails(Samp_Sub_Id[1], Sample_Tumor_Descriptor, Sample_Composition, Sample_BAS, Sample_Bio_laterality);
                CMP.minimize_arrow();
                CMP.Grid_expand_Aliquots();
                CMP.Add_Grid_Values(Array);
                awsupload_datafile(Constant.Aws_Bucket, FileName, UploadPath);
                CMP.minimize_arrow();
                CMP.Grid_expand_Samples();
                pmp.AddFile_Sample(Role,"Non-Sequence");
                //pmp.CM_Seq_Non_Seq(Input[1]);
                pmp.MCAliquot_MultipleInputs(Aliq_Sub_Id[2], Aliq_Amount, Source_center, Analyte_Type, Concentration);
                //TC-195
                pmp.validate_MCImmuneprofile_Values();
                pmp.validate_MC_IPWorkflowValues();
                pmp.MC_Immuneprofile_Input(Immune_Profile);
                pmp.MC_Immune_WFInput(IP_WorkFlow);
                pmp.MC_FileInput(FileName);
                String FileId = pmp.getFileId();
                pmp.ManifestSave();
                pmp.fill_mandatory_tables("192");
                pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
                pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
                Common_Functions.normalwait(30000);
                pmp.verify_file_count(FileCount1);
                CMP.minimize_arrow();
                CMP.Grid_expand_Aliquots();
                CMP.verify_new_added_Details_Al(Aliq_Sub_Id[2], Aliq_Amount, Source_center, Analyte_Type, Concentration, Array);
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
            CMP.Select_Case(case_id_178);
            pmp.AddFile_Sample(Role,"Sequence");
            LP.LogOut();
        }


    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
             /*   {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Unassigned_User_Name, Constant.Password, Constant.Unassigned_User},
                {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},
                {Constant.Privacy_Officer_Name, Constant.Password, Constant.Privacy_Officer}*/
        };
        return data;
    }


}





