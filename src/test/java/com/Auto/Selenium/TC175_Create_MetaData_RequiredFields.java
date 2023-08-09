
package com.hcl.redcap;

import Resources.*;
import com.redcapPage.CatalogManagementPage;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.ProjMgmtPage;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;

@Listeners({ListenerClass.class})
public class TC175_Create_MetaData_RequiredFields extends base {


    @Test(dataProvider = "getData")
    @Description("Verify the Create  Metadata without data file in UI and also verify the ingested metadata entries in the catalog UI.\n" +
            "Verify manifest creation page for Reference file type.")
    public void TCIT_DATAINGMGMT_175(String username, String password, String Role) throws IOException, InterruptedException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ExtentManager Ex = new ExtentManager();

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify the Create  Metadata without data file in UI and also verify the ingested metadata entries in the catalog UI.").
                assignAuthor(Constant.Author).assignCategory(Constant.DI_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Immune_Profile = map.get("Immune_Profile");
        String IP_WorkFlow = map.get("IP_WorkFlow");
        String View = map.get("View");
        String Aligned_Read_Index = map.get("Aligned_Read_Index");
        String Caseid = "Case" + randomNumber();
        String Filename = "File" + randomNumber();

        LP.login(username, password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToProj_Management();
        pmp.select_Project_OverviewElipse(Constant.UI_Project1);
        pmp.Navigate_To_DataMgmtIngest();
       // pmp.Navigate_To_CreateManifest();
        pmp.Navigate_To_CreateMetaData();
        //Create_metadata
        pmp.MCCase_Input(Caseid);
        //pmp.find_mandatory_tables_man();
        pmp.MC_Immuneprofile_Input(Immune_Profile);
        pmp.MC_Immune_WFInput(IP_WorkFlow);
        pmp.ManifestSave();
        pmp.fill_mandatory_tables("175");
        pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
        pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
        //validate_Negative_scenario
        pmp.Navigate_To_AppsCatalog();
        CMP.navigate_To_Casedetails(Caseid);
        pmp.Navigate_To_AppsCatalog();
        CMP.Select_View(View);
        CMP.validate_casetable_headers_raw();
        pmp.Navigate_To_Project_AppIcon();
        Common_Functions.normalwait(3000);
        pmp.select_Project_OverviewElipse(Constant.UI_Project1);
        pmp.Navigate_To_DataMgmtIngest();
        pmp.Navigate_To_CreateManifest();
        pmp.verify_Manifest_Reference();
        pmp.MC_FileInput(Filename);
        //pmp.fill_mandatory_fields();
        pmp.Aligned_reads_index(Aligned_Read_Index);
        pmp.ManifestSave();
        pmp.fill_mandatory_tables("175");
        pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
        pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
        pmp.Navigate_To_Project_AppIcon();
        pmp.select_Project_OverviewElipse(Constant.UI_Project1);
        pmp.Navigate_To_DataMgmtIngest();
        pmp.Verify_Manifestdata(Filename);
        LP.LogOut();

    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                //{Constant.Admin_Name, Constant.Password, Constant.Admin},
                {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                //{Constant.Data_User1_Name, Constant.Password, Constant.Data_User1}

        };
        return data;
    }


}





