
package com.hcl.redcap;

import Resources.*;
import com.redcapPage.*;
import io.qameta.allure.Description;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.util.Map;

@Listeners({ListenerClass.class})
public class TC279_HPC_Disable_CheckOut extends base {

    @Test(dataProvider = "getData")
    @Description("Verify as a REDCAP Platform Admin, Data Manager, Data User I or Data User II, I want to view error message when I checkout the data to the disabled HPC of a project so that copy action shall be restricted")
    public void TCIT_ADMIN_DM_DU1_DU2_EC2_HPC_Disabled_Checkout(String username, String password, String Role) throws IOException, InterruptedException, ParseException, AWTException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        CatalogManagementPage CMP = new CatalogManagementPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        DataMgmtPage dm = new DataMgmtPage(driver);
        TechnicalSettingsPage tcp = new TechnicalSettingsPage(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify as a REDCAP Platform Admin, Data Manager, Data User I or Data User II, I want to view error message when I checkout the data to the disabled HPC of a project so that copy action shall be restricted").
                assignAuthor(Constant.Author).assignCategory(Constant.Cataloge_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Raw_Files = map.get("FileName");
        AWS aws = new AWS(driver);
        String Raw_File[] =Raw_Files.split(",");
        String Reference_Files = map.get("FileName2");

        String Reference_File[] =Reference_Files.split(",");
        String View = map.get("View");
        String Accessory_Files = map.get("Incorrect_FileName");
        String Accessory_File[] =Accessory_Files.split(",");

        //P1- HPC Disabled P2- HPC Enabled
        LP.login(username, password, Role);
        LP.verify_Lp_Attributes();
        LP.navigateToCatlog_Management();
        //Add Raw Files P1,P2 Copy To HPC System
        CMP.all_cart_delete();
        CMP.navigate_To_FileDeatils(Raw_File[0]);
        CMP.Add_To_Cart();
        Common_Functions.navigate_back();
        CMP.navigate_To_FileDeatils(Raw_File[1]);
        CMP.Add_To_Cart();
        CMP.verify_copy_to_successmsg(Constant.P1Disabled_P2Enabled_Msg);
        CMP.verify_hpc_disabled_ui(Raw_File[0]);
        LP.LogOut();

        //Add Reference Files P1,P2 Copy To HPC System
        Common_Functions.navigate_back();
        CMP.Select_View("Reference");
        CMP.all_cart_delete();
        CMP.navigate_To_FileDeatils_Reference(Reference_File[0]);
        CMP.Add_To_Cart();
        Common_Functions.navigate_back();
        CMP.navigate_To_FileDeatils_Reference(Reference_File[1]);
        CMP.Add_To_Cart();
        CMP.select_proj_cart(Reference_File[0],Constant.UI_Project1);
        CMP.select_proj_cart(Reference_File[1],Constant.UI_Project2);
        CMP.verify_copy_to_successmsg(Constant.P1Disabled_P2Enabled_Msg);
        CMP.verify_hpc_disabled_ui(Reference_File[0]);
        //Add Reference File P1 Copy to Both System
        CMP.Select_View("Reference");
        CMP.all_cart_delete();
        CMP.navigate_To_FileDeatils_Reference(Reference_File[1]);
        CMP.Add_To_Cart();
        CMP.select_proj_cart(Reference_File[1],Constant.UI_Project2);
        CMP.verify_copy_to_successmsg(Constant.P1Disabled_CopyToBoth_Msg);

        //Add Accessory Files For Proj 1 To HPC Disabled
        pmp.Navigate_To_AppsCatalog();
        CMP.Select_View("Accessory file");
        CMP.all_cart_delete();
        CMP.navigate_To_FileDeatils_Reference(Accessory_File[0]);
        CMP.Add_To_Cart();
        CMP.verify_copy_to_successmsg(Constant.P1Disabled_Copy_To_HPC_Msg);
        CMP.verify_hpc_disabled_ui(Accessory_File[0]);

        //Add Accessory File For Proj 1 & P2 Copy To Both
        pmp.Navigate_To_AppsCatalog();
        CMP.Select_View("Accessory file");
        CMP.all_cart_delete();
        CMP.navigate_To_FileDeatils_Reference(Accessory_File[0]);
        CMP.Add_To_Cart();
        Common_Functions.navigate_back();
        CMP.navigate_To_FileDeatils_Reference(Accessory_File[1]);
        CMP.Add_To_Cart();
        CMP.verify_copy_to_both_msg(Constant.P1Disabled_P2Enabled_CopyToBoth_Msg);
        CMP.verify_hpc_disabled_ui(Accessory_File[0]);

        //Add Accessory File For Enabled Project2 Copy To Both

        pmp.Navigate_To_AppsCatalog();
        CMP.Select_View("Accessory file");
        CMP.all_cart_delete();
        CMP.navigate_To_FileDeatils_Reference(Accessory_File[1]);
        CMP.Add_To_Cart();
        CMP.verify_copy_to_both_msg(Constant.P2Enabled_CopyToBoth_Msg);
        CMP.verify_hpc_enabled_ui();





    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
                /* {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                 {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2},
                 {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},

                 {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2},
                 {Constant.Privacy_Officer_Name, Constant.Password, Constant.Privacy_Officer}*/
        };
        return data;
    }


}





