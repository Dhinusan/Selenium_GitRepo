
package com.hcl.redcap;

import Resources.*;
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
public class TC223_Add_More_MetaData_DM extends base {

    @Test(dataProvider = "getData")
    @Description("Verify as a REDCAP Platform admin, Data Manager and Data User-I, wants to add more metadata to a previously ingested record, so that the same is reflected in the system.")
    public void TC223_DATAINGMGMT_ADMIN_Add_Metadata(String RedcapUsername, String Password, String Role) throws IOException, InterruptedException, AWTException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName(), "Verify as a REDCAP Platform admin, Data Manager and Data User-I, wants to add more metadata to a previously ingested record, so that the same is reflected in the system.").
                assignAuthor(Constant.Author).assignCategory(Constant.DI_Management);

        //Sequence
        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Excel_FileName = map.get("FileName");
        String S3_Project = map.get("S3_Project");
        String Sample_Sub_Id = map.get("Sample_Sub_Id");
        String[] Samp_Sub_Id = Sample_Sub_Id.split(",");
        String Sample_Tumor_Descriptor = map.get("Sample_Tumor_Descriptor");
        String[] Samp_Tum_Des = Sample_Tumor_Descriptor.split(",");
        String Sample_Composition = map.get("Sample_Composition");
        String[] Samp_Composition = Sample_Composition.split(",");
        String Sample_BAS = map.get("Sample_BAS");
        String[] Samp_BAS = Sample_BAS.split(",");
        String Sample_Bio_laterality = map.get("Sample_Bio_laterality");
        String[] Samp_Bio_laterality = Sample_Bio_laterality.split(",");
        String Aliquot_Sub_Id = map.get("Aliquot_Sub_Id");
        String[] Aliq_Sub_Id = Aliquot_Sub_Id.split(",");
        String Aliquot_Amount = map.get("Aliq_Amount");
        String[] Ali_Amount = Aliquot_Amount.split(",");
        String Source_center = map.get("Source_center");
        String[] Aliq_Source_center = Source_center.split(",");
        String Analyte_Type = map.get("Analyte_Type");
        String[] Aliq_Analyte_Type = Analyte_Type.split(",");
        String Concentration = map.get("Concentration");
        String[] Aliq_Concentration = Concentration.split(",");
        String Aligned_Reads = map.get("Aligned_Reads");
        String IP_WorkFlow = map.get("IP_WorkFlow");
        String Inputs = map.get("Inputs");
        String Case_Sub_Id = map.get("Case_Sub_Id");
        String[] Case_sub_Id = Case_Sub_Id.split(",");
        String Primary_site = map.get("Primary_site");
        String[] Case_Primary_site = Primary_site.split(",");
        String Disease_type = map.get("Disease_type");
        String[] Case_Disease_type = Disease_type.split(",");
        String Consent_type = map.get("Consent_type");
        String[] Case_Consent_type = Consent_type.split(",");
        String Filename = "File" + randomNumber() + ".fastq";
        String Read_Group = map.get("Read_Group");
        String Read_Group2 = map.get("Read_Group2");
        String Slide_Image = map.get("Slide_Image");
        String Alignment_WorkFlow = map.get("Alignment_WorkFlow");


        //Single File Raw  Seq Combination
        //This TC contains TC224,TC227 Steps

            try {
                Change_File_Name(UploadPath, Excel_FileName, Filename);
                awsupload_datafile(Constant.Aws_Bucket, Filename, UploadPath);
                LP.login(RedcapUsername, Password, Role);
                LP.verify_Lp_Attributes();
                LP.navigateToProj_Management();
                pmp.select_Project_OverviewElipse(Constant.UI_Project1);
                pmp.Navigate_To_DataMgmtIngest();
                pmp.Navigate_To_CreateManifest();
                pmp.Navigate_To_RawSequence();
                pmp.MC_ReadGroup_Inputs(Read_Group);
                pmp.MC_FileInput(Filename);
                pmp.MCCase_MultipleInputs(Case_sub_Id[0], Case_Primary_site[0], Case_Disease_type[0], Case_Consent_type[0]);
                pmp.MCSample_MultipleInputs(Samp_Sub_Id[0], Samp_Tum_Des[0], Samp_Composition[0], Samp_BAS[0], Samp_Bio_laterality[0]);
                pmp.MCAliquot_MultipleInputs(Aliq_Sub_Id[0], Ali_Amount[0], Aliq_Source_center[0], Aliq_Analyte_Type[0], Aliq_Concentration[0]);
                pmp.DF_Aligned_reads(Aligned_Reads);
                pmp.MC_Immune_WFInput(IP_WorkFlow);
                pmp.ManifestSave();
                pmp.fill_mandatory_tables("223");
                pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
                pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
                driver.navigate().refresh();
                pmp.Navigate_To_AppsCatalog();
                CMP.navigate_To_FileDeatils(Filename);
                CMP.select_update_metadata(Filename, Role);
                CMP.select_Addmoremetadata();
                pmp.verify_update_metadata_attributes();
                pmp.update_Md_CancelBtn_Verify();
                CMP.navigate_To_FileDeatils(Filename);
                CMP.select_update_metadata(Filename, Role);
                CMP.select_Addmoremetadata();
                pmp.validate_mdupdate_error_msg();
                String FileId = pmp.getFileId();
                String ProjId = pmp.getprojectId();
                pmp.MCCase_MultipleInputs(Case_sub_Id[1], Case_Primary_site[1], Case_Disease_type[1], Case_Consent_type[1]);
                pmp.MCSample_MultipleInputs(Samp_Sub_Id[1], Samp_Tum_Des[1], Samp_Composition[1], Samp_BAS[1], Samp_Bio_laterality[1]);
                pmp.MCAliquot_MultipleInputs(Aliq_Sub_Id[1], Ali_Amount[1], Aliq_Source_center[1], Aliq_Analyte_Type[1], Aliq_Concentration[1]);
                pmp.MC_ReadGroup_Inputs(Read_Group2);
                pmp.Clear_Aligned_Reads();
                pmp.Clear_IPWorkFlow();
                pmp.requeiredtbale_Error_msg_Seq();
                pmp.navigate_to_slideimage();
                pmp.DF_Slide_Image(Slide_Image);
                pmp.MC_Alignment_WFInput(Alignment_WorkFlow);
                pmp.ManifestSave();
                pmp.fill_mandatory_tables("223");
                pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
                pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
                Common_Functions.normalwait(90000);
                pmp.Navigate_To_AppsCatalog();
                CMP.navigate_To_FileDeatils(Filename);
                CMP.all_cart_delete();
                CMP.validate_cart_values(FileId, Filename, Inputs, Slide_Image);
                CMP.Validate_FileValues(FileId, Filename, Samp_Sub_Id[1], Inputs, Slide_Image);
                CMP.verify_FileSummary_Values(ProjId, Inputs, Filename, RedcapUsername, Slide_Image, Alignment_WorkFlow, FileId);
                pmp.Navigate_To_AppsCatalog();
                CMP.navigate_To_FileDeatils(Filename);
                CMP.selectcaseid();
                CMP.minimize_arrow();
                CMP.Grid_expand_case();
                CMP.Validate_UI_CaseDetails(Case_Consent_type[1], Case_Disease_type[1],Case_Primary_site[1]);
                CMP.minimize_arrow();
                CMP.Grid_expand_Samples();
                CMP.Validate_UI_SampleDetails(Samp_Sub_Id[1], Samp_Tum_Des[1], Samp_Composition[1], Samp_BAS[1], Samp_Bio_laterality[1]);
                CMP.minimize_arrow();
                CMP.Grid_expand_Aliquots();
                CMP.Validate_UI_Aliquotdetails(Aliq_Sub_Id[1], Ali_Amount[1], Aliq_Source_center[1], Aliq_Analyte_Type[1], Aliq_Concentration[1]);
                CMP.navigate_back();
                CMP.select_update_metadata(Filename, Role);
                pmp.validate_metadata_values(Case_sub_Id[1], Samp_Sub_Id[1], Aliq_Sub_Id[1], Alignment_WorkFlow);
                pmp.validate_read_group(Read_Group2);
                pmp.validate_Slide_Image(Slide_Image);
                pmp.validate_AlignmentWorkFlow(Alignment_WorkFlow);
                pmp.validate_optionaltable_remove();
                LP.LogOut();

            } finally {
                Change_File_Name(UploadPath, Filename, Excel_FileName);
            }
        }



    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{


                {Constant.Data_Manager_Name,Constant.Password,Constant.Data_Manager},



        };
        return data;
    }


}





