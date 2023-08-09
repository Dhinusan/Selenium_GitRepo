package com.hcl.redcap;

import Resources.*;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.ProjMgmtPage;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;


@Listeners({ListenerClass.class})
public class TC84_Verify_RawSeq_NonSeq_Ref_Under_CreateManifest extends base {

    @Test(dataProvider = "getData", description = "TCIT_DATAINGMGMT_ADMIN DM DU1_UI_Raw & Reference Selection_Raw Seq & Non Seq Selection Display_01")
    @Severity(SeverityLevel.MINOR)
    @Story("As a REDCAP Platform Administrator, Data Manager or Data User I, I want to confirm if the raw data is from sequencing or not so that I can proceed with manifest creation process.")
    @Epic("Data management")


    public void TC84_Verify_RawSeq_NonSeqOption_ForManifest_Creation(String Username, String Password, String Role) throws IOException, InterruptedException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());

        ExtentManager Ex = new ExtentManager();

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "TC84_Verify_RawSeq_NonSeqOption_ForManifest_Creation").
                assignAuthor(Constant.Author).assignCategory(Constant.DI_Management);
        LP.login(Username, Password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToProj_Management();
        pmp.select_Project_OverviewElipse(Constant.UI_Project1);
        pmp.Navigate_To_DataMgmtIngest();
        pmp.Navigate_To_CreateManifest();
        pmp.Navigate_To_RawSequence();
        pmp.Navigate_To_Project_AppIcon();
        pmp.select_Project_OverviewElipse(Constant.UI_Project1);
        pmp.Navigate_To_DataMgmtIngest();
        pmp.Navigate_To_CreateManifest();
        pmp.verify_Manifest_Reference();
        pmp.Navigate_To_Project_AppIcon();
        pmp.select_Project_OverviewElipse(Constant.UI_Project1);
        pmp.Navigate_To_DataMgmtIngest();
        pmp.verify_ManifestPage_RawNonSequence();
        pmp.Navigate_To_Project_AppIcon();
        LP.LogOut();

    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},
                {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Admin_Name, Constant.Password, Constant.Admin},

        };
        return data;
    }

}
