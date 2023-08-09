package com.hcl.redcap;

import Resources.*;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.CatalogManagementPage;

import java.io.IOException;
import java.util.Map;

@Listeners({ListenerClass.class})
public class TC160_RequestAccess_AccessoryFiles extends base {

    @Test(dataProvider = "getData")
    @Description("To verify Admin, PO, Data Manager, Data User I, Data User II and unassigned users should be able to request access for the accessory files")
    public void TC160_Verify_RequestAccess_For_AccessoryFile(String username, String password, String Role) throws IOException, InterruptedException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ExtentManager Ex = new ExtentManager();

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "TC160_Verify_RequestAccess_For_AccessoryFile").
                assignAuthor(Constant.Author).assignCategory(Constant.Cataloge_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String View = map.get("View");

        LP.login(username, password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToCatlog_Management();
        CMP.Select_View(View);
        CMP.selectNoofItems();
        CMP.Select_File_Elipse(Constant.UI_Project3);
        CMP.Verify_RequestAccess_Attributes();
        CMP.Verify_Request_Access_No(Constant.UI_Project3);
        CMP.Verify_Request_Access_Yes(View, Constant.UI_Project3);
        //Pending With Gmail Validation
        LP.LogOut();
    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Unassigned_User_Name, Constant.Password, Constant.Unassigned_User},
                {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},
                {Constant.Privacy_Officer_Name, Constant.Password, Constant.Privacy_Officer}
        };
        return data;
    }


}




