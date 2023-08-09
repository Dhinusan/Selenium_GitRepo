
package com.hcl.redcap;

import Resources.*;
import com.redcapPage.CatalogManagementPage;
import com.redcapPage.DataMgmtPage;
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
public class TC294_Dwnld_Domain_SchemaTemplate extends base {

    @Test(dataProvider = "getData")
    @Description("Verify as a REDCAP Platform Admin or Data Manager, I want to download the schema template for the new domain so that I can modify the schema offline and upload the changes.")
    public void TCIT_DataModelchangesonthefly_ADMIN_DM_Download_DomainSchema_Template(String username, String password, String Role) throws IOException, InterruptedException, ParseException {

        Login_LandingPage LP = new Login_LandingPage(driver);
        ProjMgmtPage pmp = new ProjMgmtPage(driver);
        DataMgmtPage dm = new DataMgmtPage(driver);
        ExtentManager Ex = new ExtentManager();
        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "Verify as a REDCAP Platform Admin or Data Manager, I want to download the schema template for the new domain so that I can modify the schema offline and upload the changes.").
                assignAuthor(Constant.Author).assignCategory(Constant.Cataloge_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String Files = map.get("FileName");
        String[] File = Files.split(",");

        if (Role.equalsIgnoreCase(Constant.Admin) || Role.equalsIgnoreCase(Constant.Data_Manager)) {
            LP.login(username, password, Role);
            LP.verify_Lp_Attributes();
            dm.navigate_to_datamanagement();
            dm.verify_create_schema_bc(Role);
            dm.verify_create_schema_UI_data();
            dm.deleteAllexisitingFiles(base.DownloadPath);
            dm.verify_download_template();
            HashMap<Integer, String> HM1 = dm.readJsonFile(UploadPath, File[0]);
            HashMap<Integer, String> HM2 = dm.readJsonFile(base.DownloadPath, File[1]);
            dm.compare_json_files(HM1, HM2);
        }
        else if (Role.equalsIgnoreCase(Constant.Data_User1)){
            LP.login(username, password, Role);
            LP.verify_Lp_Attributes();
            dm.navigate_to_datamanagement();
            dm.verify_create_schema_bc(Role);
        }
        else{
            LP.login(username, password, Role);
            LP.verify_Lp_Attributes();
            dm.verify_du2_po_da_Access();
        }
    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
               /* {Constant.Data_Manager_Name, Constant.Password, Constant.Data_Manager},
                {Constant.Data_User2_Name, Constant.Password, Constant.Data_User2},
                {Constant.Data_User1_Name, Constant.Password, Constant.Data_User1},
                {Constant.Privacy_Officer_Name, Constant.Password, Constant.Privacy_Officer}*/
        };
        return data;
    }

}




