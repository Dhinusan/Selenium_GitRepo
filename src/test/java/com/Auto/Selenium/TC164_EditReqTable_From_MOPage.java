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
public class TC164_EditReqTable_From_MOPage extends base {


    @Test(dataProvider = "getData")
    @Description("Verify that user able to clears the entered data in the manifest creation table," +
            "Verify file Name/URL is displayed corectly in the manifest table.")
    public void TC164_Admin_Clears_entered_data_Manifest(String username, String password, String Role) throws IOException, InterruptedException, ParseException {
        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        ExtentManager Ex = new ExtentManager();
        CatalogManagementPage CMP = new CatalogManagementPage(driver);

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "TC164_Admin_Clears_entered_data_Manifest")
                .assignAuthor(Constant.Author).assignCategory(Constant.Project_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Case_Sub_Id = map.get("Case_Sub_Id");
        String Sample_Sub_Id = map.get("Sample_Sub_Id");
        String Read_Group = map.get("Read_Group");
        String Aliquot_Sub_Id = map.get("Aliquot_Sub_Id");
        String Aligned_Reads = map.get("Aligned_Reads");
        String FileName = map.get("FileName");
        String Aligned_Read_Index = map.get("Aligned_Read_Index");
        String Inputs = map.get("Inputs");

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
        pmp.fill_mandatory_tables("164");
        pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
        pmp.validate_ManifestTable(FileName);
        pmp.Edit_Manifest();
        pmp.Aligned_reads_index(Aligned_Read_Index);
        String FileId = pmp.getFileId();
        pmp.Clear_AlignedReads();
        pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
        Common_Functions.normalwait(5000);
        pmp.validate_ManifestTable(FileName);
        pmp.Download_Json_Manifest();
        HashMap<String, String> JsonValues = CMP.jsonread(DownloadPath, FileId + ".json", Inputs);
        pmp.verify_Download_json(Aligned_Read_Index, JsonValues);
        LP.LogOut();
    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin}
        };
        return data;
    }


}




