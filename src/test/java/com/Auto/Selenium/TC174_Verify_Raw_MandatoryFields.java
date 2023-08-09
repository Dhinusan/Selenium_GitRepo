
package com.hcl.redcap;

import Resources.*;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.ProjMgmtPage;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;

@Listeners({ListenerClass.class})
public class TC174_Verify_Raw_MandatoryFields extends base {

    @Test(dataProvider = "getData")
    @Description("Verify the user is able to view the required fields available for Raw sequencing  and Raw Non sequencing.\n" +
            "Verify “Submiited_unaligned_reads” change to “Unaligned_reads” in the manifest creation page.\n" +
            "Verify “data model” changes to exsiting table for all attributes in the manifest creation page.")
    public void TC174_DATAINGMGMT_System_Enforces_Required_Fields_Raw_Seq_Not_Seq_02(String username, String password, String Role) throws IOException, InterruptedException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        ExtentManager Ex = new ExtentManager();

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "TC174_DATAINGMGMT_System_Enforces_Required_Fields_Raw_Seq_Not_Seq").
                assignAuthor(Constant.Author).assignCategory(Constant.DI_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Case_Sub_Id = map.get("Case_Sub_Id");
        String Sample_Sub_Id = map.get("Sample_Sub_Id");
        String Read_Group = map.get("Read_Group");
        String Aliquot_Sub_Id = map.get("Aliquot_Sub_Id");
        String Aligned_Reads = map.get("Aligned_Reads");
        String FileName = map.get("FileName");

        LP.login(username, password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToProj_Management();
        pmp.select_Project_OverviewElipse(Constant.UI_Project1);
        pmp.Navigate_To_DataMgmtIngest();
        pmp.Navigate_To_CreateManifest();
        pmp.Navigate_To_RawSequence();
        pmp.Validate_RawSequencing_Mandatory_Fields();
        pmp.MCCase_Input(Case_Sub_Id);
        pmp.MCSample_MandatoryInput(Sample_Sub_Id);
        pmp.MC_ReadGroup_Inputs(Read_Group);
        pmp.MCAlliquot_Mandatory_Input(Aliquot_Sub_Id);
        pmp.MC_FileInput(FileName);
        pmp.ManifestSave();
        pmp.fill_mandatory_tables("174");
        pmp.verify_MandatoryRawSeqDF_Msg(Constant.ReqTable_RawSeq_ErrorMsg);
        pmp.DF_Aligned_reads(Aligned_Reads);
        pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
        pmp.validate_ManifestTable(FileName);
        pmp.verify_ManifestPage_RawNonSequence();
        pmp.Validate_RawNonSequencing_Mandatory_Fields();
        pmp.MCCase_Input(Case_Sub_Id);
        pmp.MCSample_MandatoryInput(Sample_Sub_Id);
        pmp.MCAlliquot_Mandatory_Input(Aliquot_Sub_Id);
        pmp.MC_FileInput(FileName);
        pmp.ManifestSave();
        pmp.fill_mandatory_tables("174");
        pmp.verify_MandatoryRawNonSeqDF_Msg(Constant.ReqTable_RawNonSeq_ErrorMsg);
        pmp.DF_Aligned_reads(Aligned_Reads);
        pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
        LP.LogOut();
    }


    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
                {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1}
        };
        return data;
    }


}





