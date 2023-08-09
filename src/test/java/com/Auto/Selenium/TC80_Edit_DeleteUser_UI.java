package com.hcl.redcap;

import Resources.*;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.redcapPage.Login_LandingPage;
import com.redcapPage.UserMgmtPage;

import java.io.IOException;
import java.util.Map;

@Listeners({ListenerClass.class})
public class TC80_Edit_DeleteUser_UI extends base {

    @Test(dataProvider = "getData")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify the user can able to edit the user and Remove the user from redcap platform")
    @Story("User management")
    public void TC80_USRMGMT_Admin_Edit_Users_Delete_User_Display_01(String username, String password, String Role) throws IOException, InterruptedException {
        Login_LandingPage LP = new Login_LandingPage(driver);
        UserMgmtPage UMP = new UserMgmtPage(driver);
        ExtentManager Ex = new ExtentManager();

        Ex.test = Ex.extent.createTest(getClass().getSimpleName() + "_" + Role, "TC80_USRMGMT_Admin_Edit_Users_Delete_User_Display").
                assignAuthor(Constant.Author).assignCategory(Constant.User_Management);

        Map<String, String> map = base.ExcelData.get(getClass().getSimpleName());
        String First_Name = map.get("FirstName");
        String Last_Name = map.get("LastName");
        String User1 = map.get("User1");
        String Organisation = map.get("Organisation");
        String AWS_UserName = map.get("AWS_UserName");
        String Inputs = map.get("Inputs");
        String[] Input = Inputs.split(",");

        LP.login(username, password, Role);
        LP.navigateToUser_Management();
        UMP.verify_UM_Attributes();
        UMP.navigate_To_AddNewUser();
        UMP.Create_UM_NewUser(User1, First_Name, Last_Name, Organisation, AWS_UserName);
        UMP.verify_UMCreate_SuccessMsg();
        UMP.UM_EditUser(User1, Input[0], Input[1], Input[2], AWS_UserName);
        UMP.verifyEdited_SuccessMsg();
        UMP.verify_UM_Table(User1, Input[0], Input[1], Input[2], AWS_UserName);
        UMP.UM_DeleteUser(User1);
        //Verify that redcap platform has at least one Platform admin role user(super admin) and it cannot be removed
        //User should able to view the platform admin role in the Redcap Platform and it should not able to remove by the user from UI
        //Close the browser
        LP.LogOut();
    }


    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[][]{
                {Constant.Admin_Name, Constant.Password, Constant.Admin},
        };
        return data;
    }


}




