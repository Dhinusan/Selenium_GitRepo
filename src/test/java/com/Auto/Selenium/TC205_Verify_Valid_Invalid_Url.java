package com.hcl.redcap;

import Resources.*;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.ProjMgmtPage;
import io.qameta.allure.*;
import org.testng.annotations.*;
import java.io.IOException;
import java.util.Map;


@Listeners({ListenerClass.class})
public class TC205_Verify_Valid_Invalid_Url extends base {

    @Test(dataProvider = "getData")
    @Description("Verify the invalid / valid URL values are entered in the \"File Name/URL\" table")
    public void TC205_Raw_Ref_ManifestCreation_Enter_Valid_Invalid_FileURL
            (String Username, String Password, String Role) throws IOException, InterruptedException {
        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName() );
        String Excel_FileName = map.get("FileName");
        String Incorrect_FileName = map.get("Incorrect_FileName");
        ExtentManager Ex = new ExtentManager();

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() +"_"+ Role,"Verify the invalid / valid URL values are entered in the \"File Name/URL\" table").
                assignAuthor(Constant.Author).assignCategory(Constant.DI_Management);

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);


        LP.login(Username, Password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToProj_Management();
        pmp.select_Project_OverviewElipse(Constant.UI_Project1);
        pmp.Navigate_To_DataMgmtIngest();
        pmp.Navigate_To_CreateManifest();
        pmp.Navigate_To_RawSequence();
        pmp.verify_IncorrectFileName(Incorrect_FileName);
        pmp.Save_Valid_File(Excel_FileName);
        LP.LogOut();

    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
             /* {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},*/
        };
        return data;
    }


}
