
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
public class TC255_MultipleFiles_Mapped_To_Aliquot extends base {

    @Test(dataProvider = "getData")
    @Description("Verify as a REDCAP user (all roles)  able to view the files mapped to the Aliquot ID in the case details page and can be used for further analysis")
    public void TC255_UsabilityImprovements_AllRoles_View_FilesMapToAliquot(String username, String password, String Role) throws IOException, InterruptedException, ParseException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        CatalogManagementPage CMP =new CatalogManagementPage(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify as a REDCAP user (all roles)  able to view the files mapped to the Aliquot ID in the case details page and can be used for further analysis").
                assignAuthor(Constant.Author).assignCategory(Constant.Cataloge_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String FileName = map.get("FileName");
        String Inputs= map.get("Inputs");
        String Sample_Sub_Id =map.get("Sample_Sub_Id");
        String Aliquot_Sub_Id =map.get("Aliquot_Sub_Id");
        String Immune_Profile =map.get("Immune_Profile");
        String IP_WorkFlow =map.get("IP_WorkFlow");
        String Caseid = "Case" + randomNumber();


        LP.login(username, password, Role);
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
        pmp.fill_mandatory_tables("255");
        pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
        pmp.validate_ManifestTable(FileName);
        pmp.Download_Json_Manifest();
        pmp.Navigate_To_AppsCatalog();
        CMP.navigate_To_Casedetails("6pweturxpnnvw5m8tkac5c");
        CMP.minimize_arrow();
        CMP.Grid_expand_Aliquots();
        String Aliquot_FileCount =CMP.Aliq_AssociatedFiles();
        CMP.verify_aliq_filecount(Aliquot_FileCount);
        String F1 =CMP.fetch_FirstFilename();
        HashMap<String,String> HM=CMP.fetch_fileuuid_details();
        CMP.UpdateFile_To_Delete(DownloadPath, FileId+".json");
        CMP.UpdateFile_AllDetails_WithFilename(DownloadPath, FileId+".json", HM);
        awsupload_meta_datafile(Constant.Aws_Bucket,FileId+".json",DownloadPath);
        pmp.Navigate_To_AppsCatalog();
        CMP.validate_delete_file_UI(F1);
        LP.LogOut();

    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
            /*    {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
               {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},*/
                {Constant.Admin_Name, Constant.Password, Constant.Admin}

        };
        return data;
    }


}





