
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
import java.util.Map;

import static com.hcl.redcap.TC178_VerifyRawSeq_UI_Ingestion.*;

@Listeners({ListenerClass.class})
public class TC190_Add_File_From_Case extends base {

    @Test(dataProvider = "getData")
    @Description("Add File From a Case")
    public void TC190_Add_File_From_Case(String username, String password, String Role) throws IOException, InterruptedException, ParseException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Add File From a Case").
                assignAuthor(Constant.Author).assignCategory(Constant.DI_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Excel_FileName = map.get("FileName");
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
        String Read_Group = map.get("Read_Group");
        String Immune_Profile = map.get("Immune_Profile");
        String IP_WorkFlow = map.get("IP_WorkFlow");
        String Slide_Image = map.get("Slide_Image");
        String SI_Species = map.get("SI_Species");
        String SI_Tumour = map.get("SI_Tumour");
        String SI_Tissue = map.get("SI_Tissue");
        String SI_Mark = map.get("SI_Mark");
        String SI_Sample = map.get("SI_Sample");
        String Caseid = "Case" + randomNumber();
        String FileName = "File" + randomNumber() + ".fastq";
        int FileCount = 0;
        String Inputs = map.get("Inputs");
        String[] Input = Inputs.split(",");
        String Excel_Caseid = map.get("Case_Sub_Id");

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
                pmp.ManifestSave();
                pmp.fill_mandatory_tables("190");
                pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
                pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
                pmp.Navigate_To_AppsCatalog();
                CMP.navigate_To_Casedetails(Caseid);
                pmp.AddFile_Case(Role, "Sequence");
                //pmp.CM_Seq_Non_Seq(Input[0]);
                pmp.verify_non_editable_case_fields();
                pmp.verify_non_editable_project_fields();
                pmp.verify_can_save_ingestBtn();
                pmp.validate_Case_Addfile_Error_Msg(Samp_Sub_Id[0], Aliq_Sub_Id[0], Excel_FileName);
                pmp.MCSample_MultipleInputs(Samp_Sub_Id[1], Sample_Tumor_Descriptor, Sample_Composition, Sample_BAS, Sample_Bio_laterality);
                pmp.MCAliquot_MultipleInputs(Aliq_Sub_Id[1], Aliq_Amount, Source_center, Analyte_Type, Concentration);
                pmp.MC_FileInput(FileName);
                pmp.MC_ReadGroup_Inputs(Read_Group);

                //Slide_Image Validation- TC196
                pmp.navigate_to_slideimage();
                pmp.verify_SpeciesDrpDwn(SI_Species);
                pmp.verify_markDrpDwn(SI_Mark);
                pmp.verify_TumourtypeDrpDwn(SI_Tumour);
                pmp.verify_TissueytypeDrpDwn(SI_Tissue);
                pmp.verify_SampletypeDrpDwn(SI_Sample);
                pmp.veriy_SlideImage_maxplus1(Cons2.SI_SlideID_MaxPlus1, Cons2.SI_BlockID_MaxPlus1, Cons2.SI_TimePointCode, Cons2.SI_ProtocolNumber
                        , Cons2.SI_ProtocolName, Cons2.SI_StudyId, Cons2.SI_Collectiontype, Cons2.SI_Notes, Slide_Image);
                pmp.verify_SlideImage_min(Cons2.SI_SlideID_MaxPlus1, Cons2.SI_BlockID_MaxPlus1, Cons2.SI_TimePointCode, Cons2.SI_ProtocolNumber
                        , Cons2.SI_ProtocolName, Cons2.SI_StudyId, Cons2.SI_Collectiontype, Cons2.SI_Notes, Slide_Image);
                pmp.DF_Slide_Image(Slide_Image);
                //TC197-New Attributes Addition Validation
                pmp.verify_date_picker();
                pmp.verify_CS_MT();
                pmp.verify_RG_TargetcaptureKit(Cons2.RG_CaptureKit);
                pmp.ManifestSave();
                pmp.fill_mandatory_tables("190");
                pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
                pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
                int FileCount1 = pmp.verify_file_count(FileCount);
                CMP.minimize_arrow();
                CMP.Grid_expand_Samples();
                System.out.println("First Validation:");
                Ex.Extent_Info("First Validation:");
                CMP.Validate_UI_SampleDetails(Samp_Sub_Id[1], Sample_Tumor_Descriptor, Sample_Composition, Sample_BAS, Sample_Bio_laterality);
                CMP.minimize_arrow();
                CMP.Grid_expand_Aliquots();
                CMP.Validate_UI_Aliquotdetails(Aliq_Sub_Id[1], Aliq_Amount, Source_center, Analyte_Type, Concentration);
                awsupload_datafile(Constant.Aws_Bucket, FileName, UploadPath);
                pmp.AddFile_Case(Role, "Sequence");
                //pmp.CM_Seq_Non_Seq(Input[1]);
                pmp.MCSample_MultipleInputs(Samp_Sub_Id[2], Sample_Tumor_Descriptor, Sample_Composition, Sample_BAS, Sample_Bio_laterality);
                pmp.MCAliquot_MultipleInputs(Aliq_Sub_Id[2], Aliq_Amount, Source_center, Analyte_Type, Concentration);
                pmp.DF_Aligned_reads(Aligned_Reads);
                pmp.MC_FileInput(FileName);
                pmp.ManifestSave();
                pmp.fill_mandatory_tables("190");
                pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
                pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
                driver.navigate().refresh();
                int FileCount2 = pmp.verify_file_count(FileCount1);
                CMP.minimize_arrow();
                CMP.Grid_expand_Samples();
                Ex.Extent_Info("Second Validation:");
                CMP.Validate_UI_SampleDetails(Samp_Sub_Id[2], Sample_Tumor_Descriptor, Sample_Composition, Sample_BAS, Sample_Bio_laterality);
                CMP.minimize_arrow();
                CMP.Grid_expand_Aliquots();
                CMP.Validate_UI_Aliquotdetails(Aliq_Sub_Id[2], Aliq_Amount, Source_center, Analyte_Type, Concentration);
                awsupload_datafile(Constant.Aws_Bucket, FileName, UploadPath);
                pmp.AddFile_Case(Role, "Non-Sequence");
                //pmp.CM_Seq_Non_Seq(Input[1]);
                pmp.MCSample_MultipleInputs(Samp_Sub_Id[3], Sample_Tumor_Descriptor, Sample_Composition, Sample_BAS, Sample_Bio_laterality);
                pmp.MCAliquot_MultipleInputs(Aliq_Sub_Id[3], Aliq_Amount, Source_center, Analyte_Type, Concentration);
                //TC195-Immune Profile Validation
                pmp.validate_MCImmuneprofile_Values();
                pmp.validate_MC_IPWorkflowValues();
                pmp.MC_Immuneprofile_Input(Immune_Profile);
                pmp.MC_Immune_WFInput(IP_WorkFlow);
                pmp.MC_FileInput(FileName);
                String FileId = pmp.getFileId();
                pmp.ManifestSave();
                pmp.fill_mandatory_tables("190");
                pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
                pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
                driver.navigate().refresh();
                pmp.verify_file_count(FileCount2);
                CMP.minimize_arrow();
                CMP.Grid_expand_Samples();
                System.out.println("Third Validation:");
                Ex.Extent_Info("Third Validation:");
                CMP.Validate_UI_SampleDetails(Samp_Sub_Id[3], Sample_Tumor_Descriptor, Sample_Composition, Sample_BAS, Sample_Bio_laterality);
                CMP.minimize_arrow();
                CMP.Grid_expand_Aliquots();
                CMP.Validate_UI_Aliquotdetails(Aliq_Sub_Id[3], Aliq_Amount, Source_center, Analyte_Type, Concentration);
                pmp.Navigate_To_AppsCatalog();
               /* CMP.navigate_To_FileDeatils(FileName);
                pmp.validate_UI_ImmuneProfile(Immune_Profile);*/
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
            pmp.Sel_AddAliquot(Role);
            LP.LogOut();
        }
    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
               {Constant.Admin_Name, Constant.Password, Constant.Admin},
              /*  {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Unassigned_User_Name, Constant.Password, Constant.Unassigned_User},
                {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},
                {Constant.Privacy_Officer_Name, Constant.Password, Constant.Privacy_Officer}*/
        };
        return data;
    }


}





