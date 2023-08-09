
package com.hcl.redcap;

import Resources.*;
import com.redcapPage.AWS;
import com.redcapPage.CatalogManagementPage;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.ProjMgmtPage;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.util.Map;

@Listeners({ListenerClass.class})
public class TC181_Verify_MetaDataSaved_Ingested extends base


{

    private static final Logger log= LogManager.getLogger(base.class.getName());


    @Test(dataProvider = "getData")
    @Description("1.Ensure that already the Metadata is saved on REDCAP Platform as a draft by the user." +
            "2.Ensure that already Metadata is submitted to Ingestion S3 bucket." +
            "3.Once the successfull ingestion, user not able to see  the Submit for ingestion status in the meta data table")
    public void  TCIT_DATAINGMGMT_181_MetaDataSave(String username,String password,String Role) throws IOException, InterruptedException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() +"_"+ Role, "TCIT_DATAINGMGMT_181_MetaDataSave").
                assignAuthor(Constant.Author).assignCategory(Constant.DI_Management);

        Map<String,String> map =base.ExcelData.get(getClass().getSimpleName());
        String Excel_FileName = map.get("FileName");
        String Sample_Sub_Id =map.get("Sample_Sub_Id");
        String Aliquot_Sub_Id =map.get("Aliquot_Sub_Id");
        String Read_Group =map.get("Read_Group");
        String Aligned_Reads =map.get("Aligned_Reads");
        String Caseid = "Case" + randomNumber();
        String FileName ="File"+randomNumber()+".fastq";

        try {
            if (Role.equalsIgnoreCase(Constant.Data_Manager) || Role.equalsIgnoreCase(Constant.Admin)) {

                Change_File_Name(UploadPath, Excel_FileName, FileName);
                awsupload_datafile(Constant.Aws_Bucket, FileName, UploadPath);
                LP.login(username, password,Role);
                LP.verify_Lp_Attributes();
                LP.navigateToProj_Management();
                pmp.select_Project_OverviewElipse(Constant.UI_Project1);
                pmp.Navigate_To_DataMgmtIngest();
                pmp.verify_manifest_overview_fields();
                pmp.Navigate_To_CreateManifest();
                pmp.Navigate_To_RawSequence();
                pmp.MCCase_Input(Caseid);
                pmp.MCSample_MandatoryInput(Sample_Sub_Id);
                pmp.MCAlliquot_Mandatory_Input(Aliquot_Sub_Id);
                pmp.MC_ReadGroup_Inputs(Read_Group);
                pmp.DF_Aligned_reads(Aligned_Reads);
                pmp.MC_FileInput(FileName);
                pmp.ManifestSave();
                pmp.fill_mandatory_tables("181");
                pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
                pmp.validate_MO_SavedStatus(FileName);
                pmp.verify_MO_Subforingestion(FileName);
                pmp.Navigate_To_AppsCatalog();
                CMP.navigate_To_Casedetails(Caseid);
                LP.LogOut();
            } else {
                Change_File_Name(UploadPath, Excel_FileName, FileName);
                awsupload_datafile(Constant.Aws_Bucket, FileName, UploadPath);
                LP.login(username, password,Role);
                LP.verify_Lp_Attributes();
                LP.navigateToProj_Management();
                pmp.select_Project_OverviewElipse(Constant.UI_Project1);
                pmp.Navigate_To_DataMgmtIngest();
                pmp.verify_manifest_overview_fields();
                pmp.verify_ManifestPage_RawNonSequence();
                pmp.MCCase_Input(Caseid);
                pmp.MCSample_MandatoryInput(Sample_Sub_Id);
                pmp.MCAlliquot_Mandatory_Input(Aliquot_Sub_Id);
                pmp.DF_Aligned_reads(Aligned_Reads);
                pmp.MC_FileInput(FileName);
                pmp.ManifestSave();
                pmp.fill_mandatory_tables("181");
                pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
                pmp.validate_MO_SavedStatus(FileName);
                pmp.verify_MO_Subforingestion(FileName);
                pmp.Navigate_To_AppsCatalog();
                CMP.navigate_To_Casedetails(Caseid);
                LP.LogOut();
            }
        } finally
            {
                Change_File_Name(UploadPath, FileName, Excel_FileName);
            }


    }




    @DataProvider
    public Object[][] getData()
    {
        Object[][] data =new Object[][] {
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
               /* {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Data_User1_Name, Constant.Password,Constant.Data_User1}*/


        };
        return data;
    }






}





