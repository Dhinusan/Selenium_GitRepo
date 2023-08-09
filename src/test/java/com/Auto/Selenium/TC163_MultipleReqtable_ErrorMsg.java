package com.hcl.redcap;

import Resources.*;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.ProjMgmtPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;

@Listeners({ListenerClass.class})
public class TC163_MultipleReqtable_ErrorMsg extends base {

    @Test(dataProvider = "getData")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Project management")
    @Description("Verify that while filling two tables from at least any one required table list during ingestion from UI.")
    public void TC163_Admin_fill_atleast_two_tables_from_required_Manifest(String username, String password, String Role) throws IOException, InterruptedException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        ExtentManager Ex = new ExtentManager();

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify that while filling two tables from at least any one required table list during ingestion from UI.").
                assignAuthor(Constant.Author).assignCategory(Constant.Project_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Case_Sub_Id = map.get("Case_Sub_Id");
        String Sample_Sub_Id = map.get("Sample_Sub_Id");
        String Read_Group = map.get("Read_Group");
        String Aliquot_Sub_Id = map.get("Aliquot_Sub_Id");
        String Aligned_Reads = map.get("Aligned_Reads");
        String FileName = map.get("FileName");
        String Aligned_Read_Index = map.get("Aligned_Read_Index");

        //multiple_data_file_Upload();
        LP.login(username, password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToProj_Management();
        pmp.select_Project_OverviewElipse(Constant.UI_Project1);
        pmp.Navigate_To_DataMgmtIngest();
        pmp.Navigate_To_CreateManifest();
        pmp.Navigate_To_RawSequence();
        pmp.MCCase_Input(Case_Sub_Id);
        pmp.MCSample_MandatoryInput(Sample_Sub_Id);
        pmp.MC_ReadGroup_Inputs(Read_Group);
        pmp.MCAlliquot_Mandatory_Input(Aliquot_Sub_Id);
        pmp.MC_FileInput(FileName);
        pmp.DF_Aligned_reads(Aligned_Reads);
        pmp.ManifestSave();
        pmp.fill_mandatory_tables("163");
        pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
        pmp.Aligned_reads_index(Aligned_Read_Index);
        pmp.verify_MultipleDF_ErrorMsg(Constant.Multiple_DF_ErrorMsg);
        LP.LogOut();

    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
        };
        return data;
    }


}




