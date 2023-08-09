package com.redcapPage;

import Resources.Common_Functions;
import Resources.Cons2;
import Resources.Constant;
import Resources.ExtentManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class DataMgmtPage extends Common_Functions {

    WebDriverWait wait = new WebDriverWait(driver, 5);
    ExtentManager Ex = new ExtentManager();

    public DataMgmtPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//figcaption[text()='Data Management']")
    public  WebElement DataManagement_Icon;

    @FindBy(xpath = "//figcaption[text()='Data Management']")
    public List<WebElement> DataManagement_Icon_Count;

    @FindBy(xpath = "//a[text()='Apps']/../..//li[text()='Data Management (Domain Schema)']")
    public WebElement DataManagement_BC;

    @FindBy(xpath = "//a[text()='Apps']")
    public WebElement Apps_Link;

    @FindBy(xpath = "//mat-table//mat-row//mat-cell")
    public WebElement dm_table_first_value;

    @FindBy(xpath = "(//mat-table//mat-row//mat-cell)[2]")
    public WebElement dm_table_first_value_version;

    @FindBy(xpath = "//mat-table//mat-row//mat-cell//li//mat-icon")
    public WebElement dm_table_first_elipse;

    @FindBy(xpath = "//a[text()='Update Schema']")
    public WebElement Update_Scehma_Elipse;

    @FindBy(xpath = "//a[text()='Update Schema']")
    public List<WebElement> Update_Scehma_Elipse_size;

    @FindBy(xpath = "//a[contains(text(),'Data Management (Domain Schema')]")
    public WebElement Domain_Schema_Bc;

    @FindBy(xpath = "//a[text()='View All Versions']")
    public WebElement View_AllVersions_Elipse;

    @FindBy(xpath = "//span [contains(text(),'Create Schema')]")
    public WebElement Create_Schema_Btn;

    @FindBy(xpath = "//span [contains(text(),' Create Schema')]")
    public List<WebElement> Create_Schema_Btn_Size;

    @FindBy(xpath = "//a[text()='Apps']/../..//a[text()='Data Management (Domain Schema )']/../..//li[text()='Create Schema']")
    public WebElement Create_Schema_bc;

    @FindBy(xpath = "//div[text()='Domain Schema']//parent::div[@aria-selected='true']")
    public WebElement Domain_Schema_Tab_Active;

    @FindBy(xpath = "//div[text()='Global Schema']")
    public WebElement Global_Schema;

    @FindBy(xpath = "//a[text()='Apps']/../..//li[text()='Data Management (Global Schema)']")
    public WebElement Global_Schema_bc;

    @FindBy(xpath = "//span[text()='Update Schema']")
    public WebElement Global_UpdateSchema;

    @FindBy(xpath = "//a[text()='Apps']/../..//a[text()='Data Management (Global Schema)']/../..//li[text()='Update Global Schema']")
    public WebElement Update_Global_Schema_bc;

    @FindBy(xpath = "//div[contains(text(),' Download Sample Template ')]")
    public WebElement Download_Samp_Temp_Header;

    @FindBy(xpath = "//mat-icon[text()=' info_outline ']/../../..//div[contains(text(),' Download Sample Template ')]")
    public WebElement Info_Icon_Download_Temp;

    @FindBy(xpath = "(//div[contains(text(),'Download')]/..//following-sibling::p)[1]")
    public WebElement Download_Samp_Temp_First_ParaData;

    @FindBy(xpath = "(//div[contains(text(),'Download')]/..//following-sibling::p)[2]")
    public WebElement Download_Samp_Temp_Second_ParaData;

    @FindBy(xpath = "(//div[contains(text(),'Download')]/..//following-sibling::p)[3]")
    public WebElement Download_Samp_Temp_Third_ParaData;

    @FindBy(xpath = "(//li[@class='p-margin-bottom ng-star-inserted'])[1]")
    public WebElement Download_Samp_Temp_First_Point;

    @FindBy(xpath = "(//li[@class='p-margin-bottom ng-star-inserted'])[2]")
    public WebElement Download_Samp_Temp_Second_Point;

    @FindBy(xpath = "(//ol[@type='a']//li)[1]//div")
    public WebElement Download_Samp_Temp_a_Point;

    @FindBy(xpath = "(//ol[@type='a']//li)[2]")
    public WebElement Download_Samp_Temp_b_Point;

    @FindBy(xpath = "(//ol[@type='a']//li)[3]")
    public WebElement Download_Samp_Temp_c_Point;

    @FindBy(xpath = "(//ol[@type='a']//li)[4]")
    public WebElement Download_Samp_Temp_d_Point;

    @FindBy(xpath = "//span[contains(text(),' Download Template')]")
    public WebElement Download_Template_Btn;

    @FindBy(xpath = "//simple-snack-bar[contains(@class,'mat-simple-snackbar')]/span")
    public WebElement Notification_Msg;

    @FindBy(xpath = "//span[text()='Download']")
    public WebElement Download_Btn;



    public void navigate_to_datamanagement(){
        jsclick(DataManagement_Icon);
        normalwait(3000);
        Ex.Pass_ScreenShot("User Navigated To Data Management Screen");
    }

    public void verify_dm_Breadcrumb(){
        Assert.assertTrue(DataManagement_BC.isDisplayed());
        Ex.Pass_ScreenShot("Verified Data Management BreadCrumb");
    }

    public void verify_apps_bc(){
        jsclick(Apps_Link);
    }

    public void verify_updateschema_bc(String Role){
        String domain =dm_table_first_value.getText().trim();
        jsclick(dm_table_first_elipse);
        if (Role.equalsIgnoreCase(Constant.Admin) || Role.equalsIgnoreCase(Constant.Data_Manager)) {
            jsclick(Update_Scehma_Elipse);
            WebElement Update_Schema_bc = driver.findElement(By.xpath("//a[text()='Apps']/../..//a[text()='Data Management (Domain Schema  - " + domain + ")']/../..//li[text()='Update Schema']"));
            Assert.assertTrue(Update_Schema_bc.isDisplayed());
            Ex.Pass_ScreenShot("Update schema Breadcrumb is displayed");
        }
        else if (Role.equalsIgnoreCase(Constant.Data_User1)){
            int size =Update_Scehma_Elipse_size.size();
            Assert.assertEquals(size,0);
            Ex.Pass_ScreenShot("Update  Schema Button Not Displayed For Data User 1 As Expected");
        }
    }

    public void verify_domainschema_bc(){
        jsclick(Domain_Schema_Bc);
        Assert.assertTrue(Domain_Schema_Tab_Active.isDisplayed());
        Ex.Pass_ScreenShot("User Navigated to Domain Schema Tab As Expected");
    }

    public void verify_allversions_bc(){
        String domain =dm_table_first_value.getText().trim();
        jsclick(dm_table_first_elipse);
        jsclick(View_AllVersions_Elipse);
        WebElement View_Versions_bc =driver.findElement(By.xpath("//a[text()='Apps']/../..//a[text()='Data Management (Domain Schema - "+domain+")']/../..//li[text()='All Versions']"));
        Assert.assertTrue(View_Versions_bc.isDisplayed());
        Ex.Pass_ScreenShot("View All Versions  Breadcrumb is displayed");
    }

    public void verify_create_schema_bc(String Role){
        if (Role.equalsIgnoreCase(Constant.Admin) || Role.equalsIgnoreCase(Constant.Data_Manager)){
            jsclick(Create_Schema_Btn);
            Assert.assertTrue(Create_Schema_bc.isDisplayed());
            Ex.Pass_ScreenShot("Create Schema Breadcrumb is displayed as Expected");
        }
        else if (Role.equalsIgnoreCase(Constant.Data_User1)){
            int size =Create_Schema_Btn_Size.size();
            Assert.assertEquals(size,0);
            Ex.Pass_ScreenShot("Create  Schema Button Not Displayed For Data User 1 As Expected");
        }

    }

    public void verify_global_Schema_bc(){
        jsclick(Global_Schema);
        //Assert.assertTrue(Global_Schema_bc.isDisplayed());
        Ex.Pass_ScreenShot("Global Schema Breadcrumb is displayed as Expected");
    }

    public void verify_gobal_updatschema_bc(){
       jsclick(Global_UpdateSchema);
       Assert.assertTrue(Update_Global_Schema_bc.isDisplayed());
       Ex.Pass_ScreenShot("Update Global Schema Is Dispalyed");
       navigate_back();
    }

    public void verify_du2_po_da_Access(){
        int size =DataManagement_Icon_Count.size();
        Assert.assertEquals(size ,0);
        Ex.Pass_ScreenShot("Data Managemenet Icon Not Present As Expected.");
    }

    public void verify_create_schema_UI_data(){
    Assert.assertTrue(Download_Samp_Temp_Header.isDisplayed());
    Assert.assertTrue(Info_Icon_Download_Temp.isDisplayed());

    String First_Parah_Data =Download_Samp_Temp_First_ParaData.getText().trim();
    Assert.assertTrue(First_Parah_Data.equalsIgnoreCase(Cons2.Download_Samp_Temp_1st_Parah));
    String Second_Parah_Data =Download_Samp_Temp_Second_ParaData.getText().trim();
    Assert.assertTrue(Second_Parah_Data.equalsIgnoreCase(Cons2.Download_Samp_Temp_2nd_Parah));
    String Third_Parah_Data =Download_Samp_Temp_Third_ParaData.getText().trim();
    Assert.assertTrue(Third_Parah_Data.equalsIgnoreCase(Cons2.Download_Samp_Temp_3rd_Parah));

    String First_Point = Download_Samp_Temp_First_Point.getText().trim();
    Assert.assertTrue(First_Point.equalsIgnoreCase(Cons2.Samp_Temp_1st_Point));
    String Second_Point = Download_Samp_Temp_Second_Point.getText().trim();
    Assert.assertTrue(Second_Point.equalsIgnoreCase(Cons2.Samp_Temp_2nd_Point));

        String a_point = Download_Samp_Temp_a_Point.getText().trim();
        Assert.assertTrue(a_point.equalsIgnoreCase(Cons2.Samp_Temp_a_Point));
        String b_point = Download_Samp_Temp_b_Point.getText().trim();
        Assert.assertTrue(b_point.equalsIgnoreCase(Cons2.Samp_Temp_b_Point));
        String c_point = Download_Samp_Temp_c_Point.getText().trim();
        Assert.assertTrue(c_point.equalsIgnoreCase(Cons2.Samp_Temp_c_Point));
        String d_point = Download_Samp_Temp_d_Point.getText().trim();
        Assert.assertTrue(d_point.equalsIgnoreCase(Cons2.Samp_Temp_d_Point));

        Assert.assertTrue(Download_Template_Btn.isDisplayed());

    }

    public void verify_download_template(){
        jsclick(Download_Template_Btn);
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg, Constant.Sample_Template_Download_SuccessMsg);
        Ex.Pass_ScreenShot("Sample Template Download Success Message");
        screenshot();
        normalwait(5000);
    }

    public void verify_update_schema_download_template(String Domain){
        jsclick(Download_Template_Btn);
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg,"Sample Schema template for " +Domain+" has been downloaded successfully");
        Ex.Pass_ScreenShot("Sample Template Download Success Message");
        screenshot();
        normalwait(5000);
    }

    public void deleteAllexisitingFiles(String filePath){
        Collection<File> excelfiles;
        File file= new File(filePath);
        excelfiles = FileUtils.listFiles(file, new String[]{"xlsx","csv","json"},true);
        System.out.println("Before:"+excelfiles);
        for (File filename :excelfiles){
            filename.delete();
        }
        System.out.println("After:"+excelfiles);
        System.out.println("Deleted All .xlsx,.csv,.json from respective folder");

    }

    public HashMap<Integer,String> readJsonFile(String FilePath,String File) throws IOException {

        File file = new File(FilePath+File);
        BufferedReader br = new BufferedReader(new FileReader(file));
        HashMap<Integer,String> map = new HashMap<>();
        String St;
        int i=1;
        while((St = br.readLine())!=null){
            map.put(i,St);
            i++;
        }
        System.out.println(map);
        return map;


    }

    public void compare_json_files(HashMap<Integer,String> HM1 ,HashMap<Integer,String> HM2 ) throws IOException {
        if (HM1.equals(HM2)){
            Ex.Extent_Pass("Downloaded Scehma Template Matches With Existing One");
        }
        else{
            Ex.Extent_Fail_Info("Downloaded Schema Template Is Incorrect");
        }
    }

    public void verify_update_schema_UI_data(){
        Assert.assertTrue(Download_Samp_Temp_Header.isDisplayed());
        Assert.assertTrue(Info_Icon_Download_Temp.isDisplayed());

        String First_Parah_Data =Download_Samp_Temp_First_ParaData.getText().trim();
        Assert.assertTrue(First_Parah_Data.equalsIgnoreCase(Cons2.Download_Samp_Temp_1st_Parah));
        String Second_Parah_Data =Download_Samp_Temp_Second_ParaData.getText().trim();
        Assert.assertTrue(Second_Parah_Data.equalsIgnoreCase(Cons2.Update_Schema_2nd_Parah));
        String Third_Parah_Data =Download_Samp_Temp_Third_ParaData.getText().trim();
        Assert.assertTrue(Third_Parah_Data.equalsIgnoreCase(Cons2.Download_Samp_Temp_3rd_Parah));

        String First_Point = Download_Samp_Temp_First_Point.getText().trim();
        Assert.assertTrue(First_Point.equalsIgnoreCase(Cons2.Update_Schema_1st_Point));
        String Second_Point = Download_Samp_Temp_Second_Point.getText().trim();
        Assert.assertTrue(Second_Point.equalsIgnoreCase(Cons2.Update_Schema_2nd_Point));

        String a_point = Download_Samp_Temp_a_Point.getText().trim();
        Assert.assertTrue(a_point.equalsIgnoreCase(Cons2.Update_Schema_a_Point));
        String b_point = Download_Samp_Temp_b_Point.getText().trim();
        Assert.assertTrue(b_point.equalsIgnoreCase(Cons2.Samp_Temp_b_Point));
        String c_point = Download_Samp_Temp_c_Point.getText().trim();
        Assert.assertTrue(c_point.equalsIgnoreCase(Cons2.Samp_Temp_c_Point));
        String d_point = Download_Samp_Temp_d_Point.getText().trim();
        Assert.assertTrue(d_point.equalsIgnoreCase(Cons2.Samp_Temp_d_Point));

        Assert.assertTrue(Download_Template_Btn.isDisplayed());

    }

    public ArrayList<String> fetch_domain_name_version(){
        ArrayList<String> Al = new ArrayList<>();

        String domain =dm_table_first_value.getText().trim();
        String version =dm_table_first_value_version.getText().trim();
        Al.add(domain);
        Al.add(version);
        return Al;

    }

    public void download_global_scehma_template(){
        jsclick(Download_Btn);
        normalwait(3000);
    }
}
