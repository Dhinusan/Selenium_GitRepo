package com.hcl.redcap;

import Resources.*;
import com.redcapPage.AWS;
import com.redcapPage.CatalogManagementPage;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.ProjMgmtPage;
import io.qameta.allure.Description;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

@Listeners({ListenerClass.class})
public class TC184_Verify_CreateMetaData extends base


{

    @Test(dataProvider = "getData")
    @Description("Verify Data Ingestion only for metadata \n" +
            "1.For manifest file only with metadata + Required table  \n" +
            "2.For manifest file only with Metadata + Required table + Optional table")
    public void  TC184 (String Username,String Password,String Role,String Aws_username) throws IOException, InterruptedException, AWTException, ParseException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() +"_"+ Role, "TC184_Verify_DataIngestion_Only_for_metadata").
                assignAuthor(Constant.Author).assignCategory(Constant.DI_Management);

        Map<String,String> map =base.ExcelData.get(getClass().getSimpleName());
        //String IP_WorkFlow = map.get("IP_WorkFlow");
        String Caseid = "Case" + randomNumber();


            LP.login(Username, Password, Role);
            LP.verify_Lp_Attributes();
            LP.navigateToProj_Management();
            pmp.select_Project_OverviewElipse(Constant.UI_Project1);
            pmp.Navigate_To_DataMgmtIngest();
            pmp.Navigate_To_CreateMetaData();
            pmp.MCCase_Input(Caseid);
            //pmp.MC_Immune_WFInput(IP_WorkFlow);
            pmp.ManifestSave();
            pmp.fill_mandatory_tables("184");
            pmp.ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
            CMP.deleteAllexisitingFiles(DownloadPath);
            String Manifest_ID =pmp.fetch_Manifest_ID();
            pmp.Download_Json_Manifest();
            driver.navigate().refresh();
            Common_Functions.normalwait(5000);
            String Filename =Common_Functions.get_filename(DownloadPath);
            awsupload_meta_datafile(Constant.Aws_Bucket,Filename,DownloadPath);
            CMP.deleteAllexisitingFiles(DownloadPath);
            pmp.Navigate_To_AppsCatalog();
            CMP.fetchcaseid(Caseid);
            CMP.navigate_to_casedetails();


    }

    @DataProvider
    public Object[][] getData()
    {
        Object[][] data =new Object[][] {
                {Constant.Admin_Name,Constant.Password,Constant.Admin, Constant.Aws_Admin_UserName}

        };
        return data;
    }






}




