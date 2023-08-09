
package com.hcl.redcap;

import Resources.*;
import com.redcapPage.AWS;
import io.qameta.allure.Description;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.CatalogManagementPage;
import com.redcapPage.ProjMgmtPage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import static Resources.Constant.Aws_Bucket;

@Listeners({ListenerClass.class})
public class TC210_Verify_CaseDetailsPage extends base


{
    @Test(dataProvider = "getData")
    @Description("Catalog Managemnet UI - Verify Users can view additional details of a case on \"Case details\" page")
    public void  TC210RawView_View_AdditionalDetails_Of_Case(String username,String password,String Role) throws IOException, InterruptedException, ParseException, AWTException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        CatalogManagementPage CMP= new CatalogManagementPage(driver);
        ExtentManager Ex = new ExtentManager();

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() +"_"+ Role," Verify Users can view additional details of a case on Case details page").
                assignAuthor(Constant.Author).assignCategory(Constant.Cataloge_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Primary_site = map.get("Primary_site");
        String Disease_type = map.get("Disease_type");
        String Consent_type = map.get("Consent_type");
        String S3_Project =map.get("S3_Project");
        String Aligned_Reads = map.get("Aligned_Reads");
        String Aliquot_Sub_Id = map.get("Aliquot_Sub_Id");
        String Read_Group = map.get("Read_Group");
        String Excel_FileName = map.get("FileName");
        String Case_Sub_Id = "Case"+randomNumber();
        String FileName ="File"+randomNumber()+".fastq";
        String SSI ="SSI210"+randomNumber();

        try{
            Change_File_Name(UploadPath, Excel_FileName, FileName);
            awsupload_datafile(Aws_Bucket, FileName, UploadPath);
            LP.login(username,password,Role);
            LP.verify_Lp_Attributes();
            LP.navigateToProj_Management();
            pmp.select_Project_OverviewElipse(Constant.UI_Project1);
            pmp.Navigate_To_DataMgmtIngest();
            pmp.Navigate_To_CreateManifest();
            pmp.Navigate_To_RawSequence();
            pmp.MC_FileInput(FileName);
            pmp.MCCase_MultipleInputs(Case_Sub_Id, Primary_site, Disease_type, Consent_type);
            pmp.MCSample_MandatoryInput(SSI);
            pmp.MCAlliquot_Mandatory_Input(Aliquot_Sub_Id);
            pmp.MC_ReadGroup_Inputs(Read_Group);
            pmp.DF_Aligned_reads(Aligned_Reads);
            pmp.ManifestSave();
            pmp.fill_mandatory_tables("210");
            pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
            pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
            pmp.Navigate_To_AppsCatalog();
            CMP.navigate_To_FileDeatils(SSI);
            Common_Functions.normalwait(4000);
            CMP.selectcaseid();
            CMP.minimize_arrow();
            CMP.Grid_expand_case();
            CMP.Validate_UI_CaseDetails(Consent_type, Disease_type,Primary_site);
            LP.LogOut();
        }
        finally {
            Change_File_Name(UploadPath, FileName, Excel_FileName);
        }


    }


    @DataProvider
    public Object[][] getData()
    {
        Object[][] data =new Object[][] {
                {Constant.Admin_Name,Constant.Password,Constant.Admin},
               /* {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},*/

        };
        return data;
    }







}





