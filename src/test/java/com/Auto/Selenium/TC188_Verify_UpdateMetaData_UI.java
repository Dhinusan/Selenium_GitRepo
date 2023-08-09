
package com.hcl.redcap;

import Resources.*;
import com.redcapPage.AWS;
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
public class TC188_Verify_UpdateMetaData_UI extends base {
    public static  String Case_Id_188;
    @Test(dataProvider = "getData")
    @Description("Verify the RAW NONSEQ Manifest file with  Required + optional tables ingestion in UI and also verify  the ingested file in the catalog UI")
    public void TC188_Verify_Update_MetaDataUI(String RedcapUsername, String Password, String Role, String Aws_username) throws IOException, InterruptedException, AWTException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        AWS aws = new AWS(driver);
        ExtentManager Ex = new ExtentManager();

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify the RAW NONSEQ Manifest file with  Required + optional tables ingestion in UI and also verify  the ingested file in the catalog UI").
                assignAuthor(Constant.Author).assignCategory(Constant.Cataloge_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Excel_FileName = map.get("FileName");
        String Sample_Sub_Id = map.get("Sample_Sub_Id");
        String[] Samp_Sub_Id = Sample_Sub_Id.split(",");
        String Aliquot_Sub_Id = map.get("Aliquot_Sub_Id");
        String[] Aliq_Sub_Id = Aliquot_Sub_Id.split(",");
        String Immune_Profile = map.get("Immune_Profile");
        String Case_Sub_Id = map.get("Case_Sub_Id");
        String[] Case_sub_Id = Case_Sub_Id.split(",");
        String IP_WorkFlow = map.get("IP_WorkFlow");
        String Immune_Profile2 = map.get("Immune_Profile2");
        String Inputs = map.get("Inputs");
        String Filename = "File" + randomNumber() + ".fastq";

        if (Role.equalsIgnoreCase(Constant.Admin) || Role.equalsIgnoreCase(Constant.Data_Manager) || Role.equalsIgnoreCase(Constant.Data_User1)) {
            try {
                Change_File_Name(UploadPath, Excel_FileName, Filename);
                awsupload_datafile(Constant.Aws_Bucket, Filename, UploadPath);
                LP.login(RedcapUsername, Password, Role);
                LP.verify_Lp_Attributes();
                LP.navigateToProj_Management();
                pmp.select_Project_OverviewElipse(Constant.UI_Project1);
                pmp.Navigate_To_DataMgmtIngest();
                pmp.verify_ManifestPage_RawNonSequence();
                pmp.MC_FileInput(Filename);
                pmp.MCCase_Input(Case_sub_Id[0]);
                pmp.MCSample_MandatoryInput(Samp_Sub_Id[0]);
                pmp.MCAlliquot_Mandatory_Input(Aliq_Sub_Id[0]);
                pmp.MC_Immuneprofile_Input(Immune_Profile);
                pmp.MC_Immune_WFInput(IP_WorkFlow);
                pmp.ManifestSave();
                pmp.fill_mandatory_tables("188");
                pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
                pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
                pmp.Navigate_To_AppsCatalog();
                CMP.navigate_To_FileDeatils(Filename);
                Case_Id_188=pmp.getCaseId();
                CMP.select_update_metadata(Filename, Role);
                pmp.validate_metadata_values(Case_sub_Id[0], Samp_Sub_Id[0], Aliq_Sub_Id[0], IP_WorkFlow);
                pmp.MCCase_Input(Case_sub_Id[1]);
                pmp.MCSample_MandatoryInput(Samp_Sub_Id[1]);
                pmp.MCAlliquot_Mandatory_Input(Aliq_Sub_Id[1]);
                pmp.MC_Immuneprofile_Input(Immune_Profile2);
                pmp.MC_Immune_WFInput(IP_WorkFlow);
                pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
                pmp.Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
                Common_Functions.normalwait(90000);
                CMP.navigate_To_FileDeatils(Filename);
                CMP.select_update_metadata(Filename, Role);
                pmp.validate_metadata_values(Case_sub_Id[1], Samp_Sub_Id[1], Aliq_Sub_Id[1], IP_WorkFlow);
                LP.LogOut();
            } finally {
                Change_File_Name(UploadPath, Filename, Excel_FileName);
            }
        } else {
            LP.login(RedcapUsername, Password, Role);
            LP.verify_Lp_Attributes();
            pmp.Navigate_To_AppsCatalog();
            CMP.navigate_To_FileDeatils(Inputs);
            CMP.select_update_metadata(Inputs, Role);
        }
    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{

                {Constant.Admin_Name, Constant.Password, Constant.Admin, Constant.Aws_Admin_UserName},
              /* {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager, Constant.Aws_Admin_UserName},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1, Constant.Aws_Admin_UserName},
                {Constant.Unassigned_User_Name, Constant.Password, Constant.Unassigned_User,Constant.Aws_Admin_UserName},
                {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2,Constant.Aws_Admin_UserName},
                {Constant.Privacy_Officer_Name, Constant.Password, Constant.Privacy_Officer,Constant.Aws_Admin_UserName}*/
        };
        return data;
    }


}





