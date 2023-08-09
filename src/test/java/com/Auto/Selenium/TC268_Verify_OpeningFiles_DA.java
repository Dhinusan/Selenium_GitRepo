
package com.hcl.redcap;

import Resources.*;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.ProjMgmtPage;
import com.redcapPage.TechnicalSettingsPage;
import io.qameta.allure.Description;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;

@Listeners({ListenerClass.class})
public class TC268_Verify_OpeningFiles_DA extends base {

    @Test(dataProvider = "getData")
    @Description("Verify as a REDCAP Platform Administrator, Data Manager, Data User I or Data User II, wants to view and open the files in DA Scratch FSx storage")
    public void TC268_OpeningFilesfromHPCandDASystem(String username, String password, String Role) throws IOException, InterruptedException, ParseException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        TechnicalSettingsPage tcp = new TechnicalSettingsPage(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify as a REDCAP Platform Administrator, Data Manager, Data User I or Data User II, wants to view and open the files in DA Scratch FSx storage").
                assignAuthor(Constant.Author).assignCategory(Constant.Project_Management);

       Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String FileName = map.get("FileName");
        String[] FIle_Name = FileName.split(",");


        LP.login(username, password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToProj_Management();
        pmp.select_Project_OverviewElipse(Constant.UI_Project1);
        tcp.navigate_to_technicalsettings();
        tcp.verify_Analytics_scratchfsx_Attributes();
        tcp.verify_download_files(FIle_Name[0]);
        tcp.verify_new_window(FIle_Name[1]);
        tcp.verify_download_files(FIle_Name[3]);
        tcp.folder_navigation();



    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},
                {Constant.Admin_Name, Constant.Password, Constant.Admin},

        };
        return data;
    }


}





