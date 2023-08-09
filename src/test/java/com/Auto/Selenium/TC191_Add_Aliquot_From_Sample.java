
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


@Listeners({ListenerClass.class})
public class TC191_Add_Aliquot_From_Sample extends base {

    @Test(dataProvider = "getData")
    @Description("Add Aliquot From Sample")
    public void TC191_DATAINGMGMT_Add_Aliquot_From_Sample(String username, String password, String Role) throws IOException, InterruptedException, ParseException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Add Aliquot From Sample").
                assignAuthor(Constant.Author).assignCategory(Constant.DI_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Excel_FileName = map.get("FileName");
        String JsonFile = map.get("JsonFile");
        String Sample_Sub_Id = map.get("Sample_Sub_Id");
        String[] Samp_Sub_Id = Sample_Sub_Id.split(",");
        String Sample_Tumor_Descriptor = map.get("Sample_Tumor_Descriptor");
        String Sample_Composition = map.get("Sample_Composition");
        String Sample_BAS = map.get("Sample_BAS");
        String Sample_Bio_laterality = map.get("Sample_Bio_laterality");
        String Aliquot_Sub_Id = map.get("Aliquot_Sub_Id");
        String[] Aliq_Sub_Id = Aliquot_Sub_Id.split(",");
        String Aliq_Amount = map.get("Aliq_Amount");
        String Immune_Profile = map.get("Immune_Profile");
        String IP_WorkFlow = map.get("IP_WorkFlow");
        String Source_center = map.get("Source_center");
        String Analyte_Type = map.get("Analyte_Type");
        String Concentration = map.get("Concentration");
        String Aligned_Reads = map.get("Aligned_Reads");
        String Slide_Image = map.get("Slide_Image");
        String SI_Species = map.get("SI_Species");
        String SI_Tumour = map.get("SI_Tumour");
        String SI_Tissue = map.get("SI_Tissue");
        String SI_Mark = map.get("SI_Mark");
        String SI_Sample = map.get("SI_Sample");
        String Inputs = map.get("Inputs");
        String Caseid = "Case" + randomNumber();
        String FileName = "File" + randomNumber() + ".fastq";


        ArrayList<String> Array = new ArrayList<>();

        if (Role.equalsIgnoreCase(Constant.Data_Manager) || Role.equalsIgnoreCase(Constant.Admin) || Role.equalsIgnoreCase(Constant.Data_User1)) {
            try {
                Change_File_Name(UploadPath, Excel_FileName, FileName);
                //CMP.UpdateFileName(UploadPath, JsonFile, FileName);
                awsupload_datafile(Constant.Aws_Bucket, FileName, UploadPath);
                //awsupload_meta_datafile(Constant.Aws_Bucket, JsonFile, UploadPath);
                LP.login(username,password,Role);
                LP.verify_Lp_Attributes();
                LP.navigateToProj_Management();
                pmp.select_Project_OverviewElipse(Constant.UI_Project1);
                pmp.Navigate_To_DataMgmtIngest();
                pmp.verify_ManifestPage_RawNonSequence();
                pmp.MC_FileInput(FileName);
                String FileId = pmp.getFileId();
                String ProjId = pmp.getprojectId();
                pmp.MCCase_Input(Caseid);
                pmp.MCSample_MandatoryInput(Sample_Sub_Id);
                pmp.MCAlliquot_Mandatory_Input(Aliquot_Sub_Id);
                pmp.MC_Immuneprofile_Input(Immune_Profile);
                pmp.MC_Immune_WFInput(IP_WorkFlow);
                pmp.ManifestSave();
                pmp.fill_mandatory_tables("191");
                pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
                pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
                pmp.Navigate_To_AppsCatalog();
                CMP.navigate_To_Casedetails(FileName);
                CMP.minimize_arrow();
                CMP.Grid_expand_Aliquots();
                CMP.Add_Grid_Values(Array);
                CMP.minimize_arrow();
                CMP.Grid_expand_Samples();
                pmp.Sel_AddAliquot(Role);
                pmp.non_editable_fields_Aliquot();
                pmp.verify_can_save_ingestBtn();
                pmp.validate_Aliq_ErrorMsg();
                pmp.Create_Manifest_Cancel_No();
                //Slide_Image Validation- TC196
                pmp.navigate_to_slideimage();
                pmp.DF_Slide_Image(Slide_Image);
                pmp.verify_SpeciesDrpDwn(SI_Species);
                pmp.verify_markDrpDwn(SI_Mark);
                pmp.verify_TumourtypeDrpDwn(SI_Tumour);
                pmp.verify_TissueytypeDrpDwn(SI_Tissue);
                pmp.verify_SampletypeDrpDwn(SI_Sample);
                pmp.veriy_SlideImage_maxplus1(Cons2.SI_SlideID_MaxPlus1, Cons2.SI_BlockID_MaxPlus1, Cons2.SI_TimePointCode, Cons2.SI_ProtocolNumber
                        , Cons2.SI_ProtocolName, Cons2.SI_StudyId, Cons2.SI_Collectiontype, Cons2.SI_Notes, Slide_Image);
                pmp.verify_SlideImage_min(Cons2.SI_SlideID_MaxPlus1, Cons2.SI_BlockID_MaxPlus1, Cons2.SI_TimePointCode, Cons2.SI_ProtocolNumber
                        , Cons2.SI_ProtocolName, Cons2.SI_StudyId, Cons2.SI_Collectiontype, Cons2.SI_Notes, Slide_Image);

                //TC197-New Attributes Addition Validation
                pmp.verify_date_picker();
                pmp.verify_CS_MT();
                pmp.verify_RG_TargetcaptureKit(Cons2.RG_CaptureKit);

                pmp.MCAliquot_MultipleInputs(Aliq_Sub_Id[0], Aliq_Amount, Source_center, Analyte_Type, Concentration);
                pmp.ManifestSave();
                pmp.fill_mandatory_tables("191");
                pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
                pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
                Common_Functions.normalwait(5000);
                driver.navigate().refresh();
                CMP.minimize_arrow();
                CMP.Grid_expand_Aliquots();
                CMP.verify_new_added_Details_Al(Aliq_Sub_Id[0], Aliq_Amount, Source_center, Analyte_Type, Concentration, Array);
                pmp.Sel_AddSampAliquot(Role);
                pmp.MCSample_MultipleInputs(Samp_Sub_Id[0], Sample_Tumor_Descriptor, Sample_Composition, Sample_BAS, Sample_Bio_laterality);
                pmp.MCAliquot_MultipleInputs(Aliq_Sub_Id[1], Aliq_Amount, Source_center, Analyte_Type, Concentration);
                pmp.DF_Aligned_reads(Aligned_Reads);
                pmp.ManifestSave();
                pmp.fill_mandatory_tables("191");
                pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
                pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
                Common_Functions.normalwait(5000);
                driver.navigate().refresh();
                CMP.minimize_arrow();
                CMP.Grid_expand_Aliquots();
                CMP.verify_new_added_Details_Al(Aliq_Sub_Id[1], Aliq_Amount, Source_center, Analyte_Type, Concentration, Array);
                CMP.minimize_arrow();
                CMP.Grid_expand_Samples();
                CMP.verify_new_added_Details_Samples(Samp_Sub_Id[0], Sample_Tumor_Descriptor, Sample_Composition, Sample_BAS, Sample_Bio_laterality, Array);
                pmp.Sel_AddAliquot(Role);
                //TC195-Immune Profile Validation
                pmp.validate_MCImmuneprofile_Values();
                pmp.validate_MC_IPWorkflowValues();
                pmp.MC_Immuneprofile_Input(Immune_Profile);
                pmp.MC_Immune_WFInput(IP_WorkFlow);
                pmp.MCAliquot_MultipleInputs(Aliq_Sub_Id[2], Aliq_Amount, Source_center, Analyte_Type, Concentration);
                pmp.ManifestSave();
                pmp.fill_mandatory_tables("191");
                pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
                pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
                Common_Functions.normalwait(5000);
                driver.navigate().refresh();
                CMP.minimize_arrow();
                CMP.Grid_expand_Aliquots();
                CMP.verify_new_added_Details_Al(Aliq_Sub_Id[2], Aliq_Amount, Source_center, Analyte_Type, Concentration, Array);
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
             /*   {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Unassigned_User_Name, Constant.Password, Constant.Unassigned_User},
                {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},
                {Constant.Privacy_Officer_Name, Constant.Password, Constant.Privacy_Officer}*/
        };
        return data;
    }


}





