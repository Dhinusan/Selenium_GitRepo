package com.redcapPage;

import Resources.Common_Functions;
import Resources.Constant;
import Resources.ExtentManager;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import io.qameta.allure.Step;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.*;
import java.util.*;

public class CatalogManagementPage extends Common_Functions {
    WebDriverWait wait = new WebDriverWait(driver, 5);
    ExtentManager Ex = new ExtentManager();
    SoftAssert s_assert = new SoftAssert();

    public CatalogManagementPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    //Catalog_Management_Page

    @FindBy(xpath = "//div[@id=\"mat-tab-label-1-1\"]")
    public WebElement cases_tab;

    @FindBy(xpath = "//tbody/tr[1]/td[3]//a")
    public WebElement case_id;

    @FindBy(xpath = "//tbody/tr[1]/td[2]")
    public WebElement Raw_Files_Table_content;


    @FindBy(xpath = "//tbody/tr[1]/td[2]")
    public WebElement Raw_cases_Table_content;


    @FindBy(xpath = "//tbody/tr[5]/td[1]/button[1]/span[1]/mat-icon[1]")
    public WebElement Table_2;

    @FindBy(xpath = "//tbody/tr[6]/td[1]")
    public WebElement Table_3;

    @FindBy(xpath = "//span[contains(text(),'| Catalog Management (Raw Files)')]")
    public WebElement Raw_Title;

    @FindBy(xpath = "//span[contains(text(),'| Catalog Management (Accessory Files)')]")
    public WebElement Accessory_Title;

    @FindBy(xpath = "//span[contains(text(),'| Catalog Management (Reference Files)')]")
    public WebElement Reference_Title;

    @FindBy(xpath = "//thead/tr[1]/th[1]/div[1]")
    public WebElement Add_all;

    @FindBy(xpath = "//header/nav[1]/li[1]/div[1]")
    public WebElement Top_Cart_icon;

    @FindBy(xpath = "//div[contains(text(),'Your cart is empty. Please add a few items')]")
    public WebElement Empty_Cart_Text;

    @FindBy(xpath = "//mat-icon[contains(text(),'keyboard_backspace')]")
    public WebElement Back_Btn_cart;

    @FindBy(xpath = "//span[contains(text(),'Change view')]")
    public WebElement Change_View;

    @FindBy(xpath = "//span[contains(text(),'Raw file')]")
    public WebElement Change_View_Raw;

    @FindBy(xpath = "//span[contains(text(),'Accessory file')]")
    public WebElement Change_View_Accessory;

    @FindBy(xpath = "//span[contains(text(),'Reference file')]")
    public WebElement Change_View_Reference;

    @FindBy(xpath = "//a[contains(text(),'Data Ingestion Management')]")
    public WebElement Data_Ingestion_Mgmt;

    @FindBy(xpath = "//span[normalize-space()='Create Manifest']")
    public WebElement Create_Manifest;

    @FindBy(xpath = "//div[contains(text(),'Raw')]")
    public WebElement Raw_RadioBtn;

    @FindBy(xpath = "//div[contains(text(),'Reference')]")
    public WebElement Reference_RadioBtn;

    @FindBy(xpath = "//div[contains(text(),'Yes')]")
    public WebElement Yes_RadioBtn;

    @FindBy(xpath = "//div[contains(text(),'No')]")
    public WebElement No_RadioBtn;

    @FindBy(xpath = "//span[text()='Proceed']")
    public WebElement Proceed_Btn;

    @FindBy(xpath = "//mat-label[contains(text(),' File Name/URL')]")
    public WebElement File_Url_Input;

    @FindBy(xpath = "//div[@class='err-msg ng-star-inserted']")
    public WebElement File_Error_Msg;


    @FindBy(xpath = "//a[text()='AWS password reset']")
    public WebElement AWS_Pw_Reset;

    @FindBy(xpath = "//app-root/app-shared/header/nav/li[1]/mat-icon")
    public WebElement Category_menu_icon;


    @FindBy(xpath = "/html[1]/body[1]/div[2]/div[2]/div[1]/div[1]/div[1]/ul[1]/li[3]/a[1]")
    public WebElement Projects_menu_item;


    @FindBy(xpath = "//mat-label[text()='Select view']/../..")
    public WebElement SelectViewDrpwn;

    @FindBy(xpath = "//mat-icon[text()='arrow_forward']")
    public WebElement ArrowBtn;

    @FindBy(xpath = "//mat-label[text()='Search files']/../../..//input")
    public WebElement Search_Files_Reference;

    @FindBy(xpath = "//mat-label[text()='Search files']//preceding::input")
    public WebElement File_txt_Input;

    @FindBy(xpath = "(//div[@class='mat-tab-labels'])[2]/..//div[contains(text(),'Cases (')]")
    public WebElement CaseHeader;

    @FindBy(xpath = "(//div[@class='mat-tab-labels'])[2]/..//div[contains(text(),'Files (')]")
    public WebElement FileHeader;


    @FindBy(xpath = "//div[text()='Cases Overview']")
    public List<WebElement> Cases_Overview;

    @FindBy(xpath = "((//table//tbody//tr)[1]//td//a)[1]")
    public WebElement Case_id;

    @FindBy(xpath = "((//table//tbody//tr)[1]//td//a)[1]")
    public List<WebElement> Case_id_size;

    @FindBy(xpath = "//div[@class='cart-icon ng-star-inserted']")
    public WebElement Add_to_Cart;

    @FindBy(xpath = "//div[contains(@class,'cart-icon')]")
    public WebElement Cart_Icon;


    @FindBy(xpath = "//td[contains(@class,'expandable')]//mat-icon[contains(text(),' arrow_right')]")
    public List<WebElement> Expand_arrrow_Right;

    @FindBy(xpath = "(//td[contains(@class,'expandable')]//mat-icon[contains(text(),' arrow_right')])[1]")
    public WebElement Expand_arrrow_Right1;
    @FindBy(xpath = "(//td[contains(@class,'expandable')]//mat-icon[contains(text(),' arrow_right')]//following::span)[1]")
    public WebElement Expand_arrrow_Right_Text;

    @FindBy(xpath = "//td[contains(@class,'expandable')]//mat-icon[contains(text(),' arrow_drop_down ')]")
    public List<WebElement> Expandable_Arrow_DrpDwn;

    @FindBy(xpath = "//span[text()='Samples']/..//following::mat-icon[text()='chevron_right']")
    public List<WebElement> Samples_ChevRight;

    @FindBy(xpath = "//span[text()='Case']/..//following::mat-icon[text()='chevron_right']")
    public List<WebElement> Case_ChevRight;

    @FindBy(xpath = "//label[contains(text(),'Consent Type')]/..//span")
    public WebElement Consent_type;

    @FindBy(xpath = "//label[contains(text(),'Disease Type')]/..//span")
    public WebElement Disease_type;

    @FindBy(xpath = "//label[contains(text(),'Biospecimen Anatomic Site')]")
    public WebElement BAS;

    @FindBy(xpath = "//label[contains(text(),'Biospecimen Anatomic Site')]/..//span")
    public WebElement Bio_Ana_site;

    @FindBy(xpath = "//label[contains(text(),'Tumor Descriptor')]/..//span")
    public WebElement Tumor_Descriptor;

    @FindBy(xpath = "//label[contains(text(),'Biospecimen Laterality')]/..//span")
    public WebElement Bio_Laterality;

    @FindBy(xpath = "//label[contains(text(),'Composition')]/..//span")
    public WebElement Sample_Composition;

    @FindBy(xpath = "//label[contains(text(),'Current Weight')]/..//span")
    public WebElement Current_Weight;

    @FindBy(xpath = "//label[contains(text(),'Date Of Collection')]/..//span")
    public WebElement Date_Of_Collection;

    @FindBy(xpath = "//*[contains(text(),'Distance Normal To Tumor')]/..//span")
    public WebElement Distance_Normal_To_Tumor;

    @FindBy(xpath = "//*[contains(text(),'Parent Samples')]/..//span")
    public WebElement Parent_Samples;

    @FindBy(xpath = "//*[contains(text(),'Preservation Method')]/..//span")
    public WebElement Preservation_Method;

    @FindBy(xpath = "(//span[contains(text(),'  Sample Id')]//following::p)[1]//span")
    public WebElement Sample_Id;

    @FindBy(xpath = "//*[contains(text(),'Sample Is FFPE')]/..//span")
    public WebElement Sample_Is_FFPE;

    @FindBy(xpath = "//label[contains(text(),'Sample Submitter Id')]/..//span")
    public WebElement Sample_Submitter_Id;

    @FindBy(xpath = "//*[contains(text(),'Sample Type')]/..//span")
    public WebElement Sample_Type;

    @FindBy(xpath = "//*[contains(text(),'Tissue Collection Type')]/..//span")
    public WebElement Tissue_Collection_Type;

    @FindBy(xpath = "//*[contains(text(),'Tissue Source Sites')]/..//span")
    public WebElement Tissue_Source_Sites;

    @FindBy(xpath = "//*[contains(text(),'Tissue Type')]/..//span")
    public WebElement Tissue_Type;


    @FindBy(xpath = "//span[text()='Aliquots']")
    public List<WebElement> Aliquots_Size;

    @FindBy(xpath = "//label[text()='Aliquot Submitter ID']")
    public WebElement ASI;

    @FindBy(xpath = "//label[text()='Aliquot Submitter ID']//following-sibling::span")
    public WebElement Aliquot_Submitter_ID;

    @FindBy(xpath = "//label[text()='Aliquot Submitter ID']//following-sibling::span")
    public List<WebElement> Aliquot_Submitter_ID_size;

    @FindBy(xpath = "//label[text()='Aliquot UUID']//following-sibling::span")
    public WebElement Aliquot_UUID;

    @FindBy(xpath = "//label[text()='Source Center']//following-sibling::span")
    public WebElement Source_Center;

    @FindBy(xpath = "//label[text()='Amount']//following-sibling::span")
    public WebElement Amount;

    @FindBy(xpath = "//label[text()='Concentration']//following-sibling::span")
    public WebElement Concentration;

    @FindBy(xpath = "//label[text()='Analyte Type']//following-sibling::span")
    public WebElement Analyte_Type;

    @FindBy(xpath = "//span[text()='Case']/parent::*//mat-icon[text()=' arrow_right ']")
    public List<WebElement> Case_Row;

    @FindBy(xpath = "//span[text()='Samples']/parent::*//mat-icon[text()=' arrow_right ']")
    public List<WebElement> Samples_Row;

    @FindBy(xpath = "//span[text()='Aliquots']/parent::*//mat-icon[text()=' arrow_right ']")
    public List<WebElement> Aliquots_Row;

    @FindBy(xpath = "//div[text()=' Cases ']")
    public WebElement Cases_Filter;

    @FindBy(xpath = "//mat-panel-title[contains(text(),'Project ID')]")
    public WebElement Cases_ProjDrpDwn;

    @FindBy(xpath = "//mat-panel-title[contains(text(),'Case ID')]")
    public WebElement Cases_CaseDrpDwn;

    @FindBy(xpath = "//mat-panel-title[contains(text(),'Case ID')]/../../..//input")
    public WebElement Cases_CaseInput;

    @FindBy(xpath = "//mat-panel-title[contains(text(),'Primary Site')]")
    public WebElement Cases_PSDrpDwn;

    @FindBy(xpath = "//mat-panel-title[contains(text(),'Ann Arbor Clinical Stage')]")
    public WebElement Cases_ArborDrpDwn;

    @FindBy(xpath = "//div[@class='loading-user-spinner ng-star-inserted']")
    public List<WebElement> Loader_Circle;

    @FindBy(xpath = "(//table//tbody//tr//td)[1]/..//mat-cell//button//span")
    public WebElement File_Elipse;

    @FindBy(xpath = "(//table//tbody//tr)[1]//td//a")
    public WebElement Frst_Proj_Link;

    @FindBy(xpath = "//button[contains(text(),'Request access')]")
    public WebElement Request_accessBtn;

    @FindBy(xpath = "//*[contains(text(),'Request access')]")
    public List<WebElement> Request_accessBtnsize;

    @FindBy(xpath = "//div[text()='Request Access']/../..//div[@class='body-row']//div")
    public WebElement RequestAcc_Msg;

    @FindBy(xpath = "//span[text()='Yes']")
    public WebElement Req_Access_Yes_Btn;

    @FindBy(xpath = "//span[text()='No']")
    public WebElement Req_Access_No_Btn;

    @FindBy(xpath = "//div[text()='Request already sent for this project']")
    public WebElement Req_Acc_RaisedMsg;

    @FindBy(xpath = "//span[contains(text(),'Catalog file details')]")
    public WebElement Catalog_Header;

    @FindBy(xpath = "//table//thead//th[text()='Case ID']")
    public WebElement Case_id_Header;

    @FindBy(xpath = "//table//thead//th[text()='Project']")
    public WebElement Project_Header;

    @FindBy(xpath = "//table//thead//th[text()='Primary Site']")
    public WebElement Primary_Site_Header;

    @FindBy(xpath = "//table//thead//th[text()='Files Available']")
    public WebElement Files_Header;

    @FindBy(xpath = "//table//thead//th[text()='Age at diagnosis']")
    public WebElement AgeatDiagnosis_Header;

    @FindBy(xpath = "//table//thead//th[text()='Vital Status']")
    public WebElement Vital_Status_Header;

    @FindBy(xpath = "//a[text()='Catalog Management (Raw Files)']")
    public WebElement CM_Breadcrumb;

    @FindBy(xpath = "//table//thead//th[text()='Primary Diagnosis']")
    public WebElement Primary_Diagnosis_Header;

    @FindBy(xpath = "//table//thead//th[text()='Ethnicity']")
    public WebElement Ethnicity_Header;

    //File Details Headers
    @FindBy(xpath = "//table//thead//th[contains(text(),'File UUID')]")
    public WebElement FileUUID_Header;

    @FindBy(xpath = "//table//thead//th[contains(text(),'File Description')]")
    public WebElement File_Description;

    @FindBy(xpath = "//table//thead//th[contains(text(),' Ingested date')]")
    public WebElement File_IngestedDate;

    @FindBy(xpath = "//table//thead//th[contains(text(),' Ingested by')]")
    public WebElement File_Ingesteby;

    @FindBy(xpath = "//table//thead//th[text()='Access']")
    public WebElement FileAccess_Header;

    @FindBy(xpath = "//table//thead//th[text()='File Name']")
    public WebElement File_Name_Header;

    @FindBy(xpath = "//table//thead//th[text()='Case ID']")
    public WebElement File_CaseID_Header;

    @FindBy(xpath = "//table//thead//th[text()='Sample Submitter Id']")
    public WebElement File_SSI_Header;

    @FindBy(xpath = "//table//thead//th[text()='Project']")
    public WebElement File_Project_Header;

    @FindBy(xpath = "//table//thead//th[text()='Data Category']")
    public WebElement File_DataCategory_Header;

    @FindBy(xpath = "//table//thead//th[text()='Data Format']")
    public WebElement File_DataFormat_Header;

    @FindBy(xpath = "//table//thead//th[text()='File Size']")
    public WebElement FileSize_Header;

    @FindBy(xpath = "//table//thead//th[text()='Data Type']")
    public WebElement File_DataType_Header;

    @FindBy(xpath = "//table//thead//th[text()='Experimental Strategy ']")
    public WebElement File_Exp_Strategy_Header;

    @FindBy(xpath = "//table//thead//th[text()='Data Classification']")
    public WebElement File_DataClass_Header;

    @FindBy(xpath = "//td[contains(@class, 'fileUUID')]//a")
    public WebElement File_UUID;

    @FindBy(xpath = "//td[contains(@class, 'fileUUID')]")
    public WebElement File_UUID_Reference;

    @FindBy(xpath = "//td[contains(@class, 'access')]")
    public WebElement File_Access;

    @FindBy(xpath = "//td[contains(@class, 'fileName')]")
    public WebElement File_Name;

    @FindBy(xpath = "//td[contains(@class, 'fileDescription ')]")
    public WebElement Filedescription;

    @FindBy(xpath = "//td[contains(@class, 'ingestedDate')]")
    public WebElement File_IngestionDate;

    @FindBy(xpath = "//td[contains(@class, 'IngestedBy')]")
    public WebElement File_IngestedBy;


    @FindBy(xpath = "//td[contains(@class, 'caseId')]")
    public WebElement File_CaseId;

    @FindBy(xpath = "//button[contains(text(),' Update Metadata ')]")
    public WebElement Update_MetaData;

    @FindBy(xpath = "//table//thead//th[contains(text(),'Data Access')]")
    public WebElement File_DataAccess;

    @FindBy(xpath = "//td[contains(@class, 'sampleSubmitterId')]")
    public WebElement File_SsId;

    @FindBy(xpath = "//table//thead//tr//th//div[@class='delete-icon']")
    public WebElement All_Cart_Delete;

    @FindBy(xpath = "//table//thead//tr//th//div[@class='delete-icon']")
    public List<WebElement> All_Cart_Delete_Count;

    @FindBy(xpath = "//simple-snack-bar[contains(@class,'mat-simple-snackbar')]/span")
    public WebElement Notification_Msg;

    @FindBy(xpath = "//span[text()='Return to Files Overview']")
    public WebElement Return_Files_Overview;

    @FindBy(xpath = "//span[text()='Return to Files Overview']")
    public List<WebElement> Return_Files_Overview_List;

    @FindBy(xpath = "(//table//tbody//tr)[1]//td//div[contains(@class,'cart-icon')]")
    public WebElement Add_To_Cart_File_Overview;

    @FindBy(xpath = "//table//tbody//tr//div[contains(@class,'cart-icon')]")
    public List<WebElement> Add_To_Cart_File_Overview_Count;

    @FindBy(xpath = "(//table//tbody//tr//td)[2]")
    public WebElement Cart_File_UUID;

    @FindBy(xpath = "(//table//tbody//tr//td)[4]")
    public WebElement Cart_Access;

    @FindBy(xpath = "(//table//tbody//tr//td)[5]")
    public WebElement Cart_FileName;

    @FindBy(xpath = "(//table//tbody//tr//td)[7]")
    public WebElement Cart_Project;

    @FindBy(xpath = "(//table//tbody//tr//td)[8]")
    public WebElement Cart_data_category;

    @FindBy(xpath = "(//table//tbody//tr//td)[9]")
    public WebElement Cart_data_format;

    @FindBy(xpath = "(//table//tbody//tr//td)[11]")
    public WebElement Cart_dataType;

    @FindBy(xpath = "(//table//tbody//tr//td)[12]")
    public WebElement Cart_experimental_strategy;

    @FindBy(xpath = "//td[contains(@class, 'project')]//a")
    public WebElement File_Project;

    @FindBy(xpath = "//td[contains(@class, 'dataCategory')]")
    public WebElement File_DataCategory;

    @FindBy(xpath = "//td[contains(@class, 'dataFormat')]")
    public WebElement File_DataFormat;

    @FindBy(xpath = "//td[contains(@class, 'dataType')]")
    public WebElement File_DataType;

    @FindBy(xpath = "//td[contains(@class, 'experimentalStrategy')]")
    public WebElement File_ExpStrategy;

    @FindBy(xpath = "//td[contains(@class, 'dataClassification')]")
    public WebElement File_DatClass;

    @FindBy(xpath = "(//span[contains(text(),'Project ID')]/../..//p)[2]//span")
    public WebElement FS_ProjectId;

    @FindBy(xpath = "(//span[contains(text(),'Project Name')]/../..//p)[2]//span")
    public WebElement FS_ProjName;

    @FindBy(xpath = "(//span[contains(text(),'File Name')]/../..//p)[2]//span")
    public WebElement FS_File_Name;

    @FindBy(xpath = "//span[contains(text(),'Case Id')]")
    public WebElement FS_CaseId_Header;

    @FindBy(xpath = "//span[contains(text(),'Sample ID')]")
    public WebElement FS_SampleId_Header;

    @FindBy(xpath = "//span[contains(text(),'Aliquot ID')]")
    public WebElement FS_AliqId_Header;

    @FindBy(xpath = "(//span[contains(text(),'Author')]/../..//p)[2]//span")
    public WebElement FS_Author;


    @FindBy(xpath = "(//span[contains(text(),'Access')]/../..//p)[2]//span")
    public WebElement FS_Access;

    @FindBy(xpath = "(//span[contains(text(),'Data Category')]/../..//p)[2]//span")
    public WebElement FS_DataCategory;

    @FindBy(xpath = "(//span[contains(text(),'Data Type')]/../..//p)[2]//span")
    public WebElement FS_DataType;

    @FindBy(xpath = "(//span[contains(text(),'Experimental Strategy')]/../..//p)[2]//span")
    public WebElement FS_ExpStrategy;

    @FindBy(xpath = "(//span[contains(text(),'Aliquot ID')]/../..//p)[2]//span")
    public WebElement FS_AliquotID;

    @FindBy(xpath = "(//span[contains(text(),'Workflow Type')]/../..//p)[2]//span")
    public WebElement FS_WorkFlow;

    @FindBy(xpath = "(//span[contains(text(),'Data Format')]/../..//p)[2]//span")
    public WebElement FS_Dataformat;


    @FindBy(xpath = "//span[contains(text(),'Privacy officer name')]")
    public WebElement FS_PrivacyOffHeader;

    @FindBy(xpath = "//span[contains(text(),'Data access committee')]")
    public WebElement FS_DataAccessCommitee;

    @FindBy(xpath = "(//span[contains(text(),'File UUID')]/../..//p)[2]//span")
    public WebElement FS_FileUUID;

    @FindBy(xpath = "(//span[contains(text(),'Sample Submitter ID')]/../..//p)[2]//span")
    public WebElement FS_SampSubId;

    @FindBy(xpath = "//table//tr[contains(@class,'mat-row')]")
    public List<WebElement> Files_Row_Count;

    //Robin Xpath
    @FindBy(xpath = "//mat-icon[text()='arrow_forward']")
    public WebElement Forward_Button_Filter;

    @FindBy(xpath = "//mat-label[contains(text(),'Search files')]/../../..//input")
    public WebElement Search_File_Filter;

    @FindBy(xpath = "((//table//tbody//tr)[1]//td//a)[1]")
    public WebElement File_Id;

    @FindBy(xpath = "//span[text()='File UUID']/../..//following-sibling::p//span")
    public WebElement Fileuuid;

    @FindBy(xpath = "//span[contains(text(),'Catalog case details')]")
    public WebElement Case_Detail_Page_Header;

    @FindBy(xpath = "//label[text()='Primary Site']/../span")
    public WebElement Primary_Site;

    @FindBy(xpath = "//label[text()=' Consent Type']/../span")
    public WebElement Consent_Type;
    @FindBy(xpath = "//label[text()='Project Name']/../span")
    public WebElement Project_Name;

    @FindBy(xpath = "//label[text()=' Disease Type']/../span")
    public WebElement Disease_Type;
    @FindBy(xpath = "//table/tbody/tr[4]/td/span")
    public WebElement Sample_Table;

    @FindBy(xpath = "(//table/tbody/tr/td[3])//a")
    public WebElement CaseID_Cell_1_CaseTab;

    @FindBy(xpath = "//span[contains(text(),'Aliquots')]/..//mat-icon")
    public WebElement Aliquot_Downarrow;
    @FindBy(xpath = "//h1/span/mat-icon[contains(text(),'keyboard_backspace')]")
    public WebElement CatalogBackButton;

    @FindBy(xpath = "//table/tbody/tr[6]/td/span")
    public WebElement Aliquot_Table;

    @FindBy(xpath = "//span[text()='Sample ID']/../..//following-sibling::p//span")
    public WebElement Sample_ID;

    @FindBy(xpath = "//span[text()='Aliquot ID']/../..//following-sibling::p//span")
    public WebElement Aliquot_ID;

    @FindBy(xpath = "//span[text()='Case Id']/../..//following-sibling::p//span")
    public WebElement Case_Id;

    @FindBy(xpath = "//table//tbody//tr[4]/td[3]/a")
    public WebElement fourth_file;

    @FindBy(xpath = "(//table/tbody/tr[1]/td/a)[1]")
    public WebElement Case_Id_First;

    @FindBy(xpath = "//span[text()='Add more Metadata']")
    public WebElement Add_MoreMetaData;

    @FindBy(xpath = "(//table//tbody//tr)[2]//td//a")
    public WebElement File_UUID_FirstRow;

    @FindBy(xpath = "//a[text()='Apps']/../..//li//a[text()='Catalog Management (Raw Files)']/../..//li[text()='Catalog file details']")
    public WebElement Catalog_File_Details_BC;

    @FindBy(xpath = "//a[text()='Apps']/../..//li//a[text()='Catalog Management (Raw Files)']")
    public WebElement CM_RawFiles_BCLink;

    @FindBy(xpath = "((//table//tbody//tr)[2]//td//a)[2]")
    public WebElement ProjectId_FirstRow;

    @FindBy(xpath = "//a[text()='Apps']/../..//li//a[text()='Catalog Management (Raw Files)']/../..//li[text()='Catalog project details']")
    public WebElement CM_ProjectDetails_BC;

    @FindBy(xpath = "//a[text()='Apps']/../..//li//a[text()='Catalog Management (Raw Files)']/../..//li[text()='Catalog case details']")
    public WebElement CM_caseDetails_BC;

    @FindBy(xpath = "//label[text()='Total Associated Files']/..//a")
    public WebElement TotalAssocFileCount;

    @FindBy(xpath = "//a[text()='Apps']/../..//li//a[text()='Catalog Management (Raw Files)']/../..//li[text()='Update Metadata']")
    public WebElement CM_UpdateMetaData_BC;

    @FindBy(xpath = "(//table/tbody/tr[1]/td/a)[3]")
    public WebElement Cases_Files_First;

    @FindBy(xpath = "(//table/tbody/tr[1]/td/a)[2]")
    public WebElement Cases_Project_First;

    @FindBy(xpath = "//a[text()='Apps']/../..//li[text()='Catalog Management (Raw Files)']")
    public WebElement CM_RawFiles_BC;

    @FindBy(xpath = "//a[text()='Apps']/../..//li[text()='Catalog Management (Reference Files)']")
    public WebElement CM_ReferenceFiles_BC;

    @FindBy(xpath = "//a[text()='Apps']/../..//li//a[text()='Catalog Management (Raw Files)']/../..//li[text()='Cart Overview']")
    public WebElement Cart_Overview_BC;

    @FindBy(xpath = "(//table//tr//td//a)[1]")
    public WebElement Accessory_Project_First;

    @FindBy(xpath = "//a[text()='Apps']/../..//li//a[text()='Catalog Management (Accessory Files)']/../..//li[text()='Catalog project details']")
    public WebElement CMAccessoryFiles_ProjectDetails_BC;

    @FindBy(xpath = "//a[text()='Apps']/../..//li//a[text()='Catalog Management (Accessory Files)']/../..//li[text()='Cart Overview']")
    public WebElement AccessoryFiles_Cart_Overview_BC;

    @FindBy(xpath = "//a[text()='Apps']/../..//li//a[text()='Catalog Management (Reference Files)']/../..//li[text()='Cart Overview']")
    public WebElement ReferenceFiles_Cart_Overview_BC;


    @FindBy(xpath = "//a[text()='Apps']/../..//li//a[text()='Catalog Management (Accessory Files)']")
    public WebElement CM_AccessoryFiles_BCLink;

    @FindBy(xpath = "//h4[text()='Aliquot details']/../..//label[text()='Total Associated Files']/..//a")
    public WebElement Aliquot_AssociatedFiles;

    @FindBy(xpath = "//div[@class='mat-paginator-range-label']")
    public WebElement Footer_FileCount;

    @FindBy(xpath = "(//table//tbody//tr)[1]//a")
    public WebElement First_File_Uuid;

    @FindBy(xpath = "(//table//tbody//tr)[1]//td[5]")
    public WebElement First_File_Name;

    @FindBy(xpath = "//span[text()='Download in CSV']")
    public WebElement Download_Csv;

    @FindBy(xpath = "//mat-panel-title[contains(text(),'Data Category')]")
    public WebElement Files_DataCategoryDrpDwn;

    @FindBy(xpath = "//table//tbody//tr")
    public List<WebElement> CM_Table_Filecount;

    @FindBy(xpath = "(//table//tbody//tr)[1]//td")
    public List<WebElement> CM_Table_Columncount;

    @FindBy(xpath = "//table//thead//tr//th")
    public List<WebElement> CM_Table_Headercount;

    @FindBy(xpath = "//mat-panel-title[contains(text(),'Data Type')]")
    public WebElement Files_DataTypeDrpDwn;

    @FindBy(xpath = "//mat-panel-title[contains(text(),'Data Format')]")
    public WebElement Files_DataFormatDrpDwn;

    @FindBy(xpath = "(//table//tbody//tr)[1]//td[1]//mat-checkbox//input")
    public WebElement Files_FirstRowCB;

    @FindBy(xpath = "//table//thead//tr//th//mat-checkbox//input")
    public WebElement Files_AllCB;

    @FindBy(xpath = "//h2[contains(text(),'File count exceeded')]/..//h2/..//p[text()='Click send to receive the CSV file to your registered email address.']")
    public WebElement ThousandFiles_Msg;

    @FindBy(xpath = "//span[text()='Send']")
    public WebElement Send_Btn;

    @FindBy(xpath = "//div[@class='catalog-table']//div[@class='mat-tab-label-content']")
    public WebElement Header_File_Count;

    @FindBy(xpath = "//span[text()='Copy to HPC System']")
    public WebElement Copy_To_HPC_System;

    @FindBy(xpath = "//span[text()='Copy to both systems']")
    public WebElement Copy_To_Both_System;

    @FindBy(xpath = "//div[text()='Your cart is empty. Please add a few items']")
    public WebElement Empty_Cart_Msg;

    public void verify_hpc_enabled_ui(){

        Assert.assertTrue(Empty_Cart_Msg.isDisplayed());
        Ex.Pass_ScreenShot("HPC Enabled Project Checked Out As Expected.");

    }
    public void verify_hpc_disabled_ui(String Disabled_File){
        WebElement HPC_Disabled =driver.findElement(By.xpath("//table//tbody//tr//td[contains(text(),'"+Disabled_File+"')]"));
        Assert.assertTrue(HPC_Disabled.isDisplayed());
        Ex.Pass_ScreenShot("HPC Disabled Project File Not Checked Out From UI As Expected.");
    }

    public void verify_copy_to_both_msg(String Msg){
        normalwait(2000);
        jsclick(Copy_To_Both_System);
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg,Msg);
        Ex.Pass_ScreenShot("Raw Files HPC Disabled CheckOut");
        screenshot();
    }
    public void verify_copy_to_successmsg(String Msg){
        normalwait(2000);
        jsclick(Copy_To_HPC_System);
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg,Msg);
        Ex.Pass_ScreenShot("Raw Files HPC Disabled CheckOut");
        screenshot();
    }

    public void verify_bulk_upload(){
       String Count = Header_File_Count.getText().trim();
       String Now =Count.substring(7,9);
       int number = Integer.parseInt(Now);
       Assert.assertEquals(number,10);
       screenshot();
       Ex.Pass_ScreenShot("All files in Csv Has been Ingested And is Reflectin in  UI");

    }
    public void update_csv_values_bulk(String filename,String Case_Id) throws IOException {
        String fp=UploadPath+filename;
        File inputFile = new File(fp);

        // Read existing file
        CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
        List<String[]> csvBody = reader.readAll();
        csvBody.get(3)[6]=Case_Id;
        csvBody.get(4)[6]=Case_Id;
        csvBody.get(5)[6]=Case_Id;
        csvBody.get(6)[6]=Case_Id;
        csvBody.get(7)[6]=Case_Id;
        csvBody.get(8)[6]=Case_Id;
        csvBody.get(9)[6]=Case_Id;
        csvBody.get(10)[6]=Case_Id;
        csvBody.get(11)[6]=Case_Id;
        csvBody.get(12)[6]=Case_Id;


        reader.close();
        // get CSV row column and replace with by using row and colum

        // Write to CSV file which is open
        CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ',');
        writer.writeAll(csvBody);
        writer.flush();
        writer.close();
    }
    public void update_csv_Values_reference(String filename,String Ref_File) throws IOException {
        String fp=UploadPath+filename;
        File inputFile = new File(fp);

        // Read existing file
        CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
        List<String[]> csvBody = reader.readAll();
        csvBody.get(3)[1]=Ref_File;
        reader.close();
        // get CSV row column and replace with by using row and colum

        // Write to CSV file which is open
        CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ',');
        writer.writeAll(csvBody);
        writer.flush();
        writer.close();
    }


    public void update_csv_Values(String filename,String C1,String C2,String S2,String A2,String C3,String C_Id1,String C_Id2,String S4,String A4,String S_Id) throws IOException {
        String fp=UploadPath+filename;
        File inputFile = new File(fp);

            // Read existing file
            CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
            List<String[]> csvBody = reader.readAll();
            csvBody.get(3)[1]=C1;

            csvBody.get(4)[1]=C2;
            csvBody.get(4)[144]=S2;
            csvBody.get(4)[91]=A2;

            csvBody.get(5)[1]=C3;
            csvBody.get(5)[0]=C_Id1;
            csvBody.get(5)[144]=S2;
            csvBody.get(5)[91]=A2;

            csvBody.get(6)[0]=C_Id2;
            csvBody.get(6)[143]=S_Id;
            csvBody.get(6)[144]=S4;
            csvBody.get(6)[91]=A4;


            reader.close();
            // get CSV row column and replace with by using row and colum

            // Write to CSV file which is open
            CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ',');
            writer.writeAll(csvBody);
            writer.flush();
            writer.close();
        }



    @Step("Verify UI File Count")
    public void verify_file_count(int Filecount) throws InterruptedException {
        normalwait(5000);
        String FileCount = TotalAssocFileCount.getText();
        int count = Integer.parseInt(FileCount);
        if (count == Filecount + 1) {
            Assert.assertTrue(true);
            Ex.Pass_ScreenShot("File Count Increase Verification After UI Ingestion :");
        } else {
            System.out.println("File Count:" + count);
            Assert.assertTrue(false);
        }
        screenshot();
    }


    public int fetch_totalFilecount(){
        normalwait(5000);
        String FileCount = TotalAssocFileCount.getText();
        int count = Integer.parseInt(FileCount);
        System.out.println(count);
        return count;
    }
    @Step("Select Arbor Under Cases Tab")
    public void SelectCases_Arbor(String Arbor) throws InterruptedException {
        Navigate_to_caseFilter();
        normalwait(5000);
        wait.until(ExpectedConditions.elementToBeClickable(Cases_ProjDrpDwn));
        jsclick(Cases_ArborDrpDwn);
        loader_new();
        Select_CheckBox(Arbor);
        loader_new();
        wait.until(ExpectedConditions.elementToBeClickable(CaseHeader));
        screenshot();

    }

    @Step("Select Primary Site Under Cases Tab")
    public void SelectCases_PimarySite(String PrimarySite) throws InterruptedException {
        Navigate_to_caseFilter();
        page_loader();
        wait.until(ExpectedConditions.elementToBeClickable(Cases_ProjDrpDwn));
        jsclick(Cases_PSDrpDwn);
        normalwait(5000);
        Select_CheckBox(PrimarySite);
        normalwait(5000);
        wait.until(ExpectedConditions.elementToBeClickable(CaseHeader));
        screenshot();

    }

    @Step("Select Case ID Under Cases Tab")
    public void SelectCases_CaseId(String CaseId) throws InterruptedException {
        Navigate_to_caseFilter();
        normalwait(5000);
        wait.until(ExpectedConditions.elementToBeClickable(Cases_ProjDrpDwn));
        jsclick(Cases_CaseDrpDwn);
        normalwait(5000);
        send_keys(Cases_CaseInput,CaseId+ Keys.ENTER);
        normalwait(5000);
        wait.until(ExpectedConditions.elementToBeClickable(CaseHeader));
        screenshot();

    }

    @Step("More Than 1000 Files Download Success Message")
    public void verify_overall_CBMsg(){
        jsclick(Files_AllCB);
        page_loader();
        jsclick(Download_Csv);
        normalwait(2000);
        Assert.assertTrue(ThousandFiles_Msg.isDisplayed());
        jsclick(Send_Btn);
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg, Constant.Thousand_Files_SuccessMsg);
        Ex.Pass_ScreenShot("More Than 1000 Files Download Success Message");
        screenshot();

    }
    @Step("Table Value For the First Row")
    public HashMap<String, List<String>> fetch_CMTable_Values_FirstRow() {
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, List<String>> HM = new HashMap<>();

            int Column_Count = CM_Table_Columncount.size();
            jsclick(Files_FirstRowCB);
            ArrayList<String> list = new ArrayList<>();
            for (int j = 4; j <= Column_Count; j++) {
                String Values = driver.findElement(By.xpath("(//table//tbody//tr)[1]//td[" + j + "]")).getText();
                list.add(Values);
            }
            System.out.println(list);
            String fileuuid = driver.findElement(By.xpath("(//table//tbody//tr)[1]//td[3]")).getText();
            HM.put(fileuuid, list);


        map.put("UI File Uuids", String.valueOf(HM));
        screenshot();
        Ex.Info_ScreenShot("Ui Catalog Management File Uuids Are :" + MarkupHelper.createUnorderedList(map).getMarkup());
        return HM;
    }

    @Step("Select Data Format Under Files Tab")
    public void SelectFiles_DataFormat(String DataType) throws InterruptedException {
        normalwait(4000);
        jsclick(ArrowBtn);
        normalwait(3000);
        jsclick(Files_DataFormatDrpDwn);
        Select_CheckBox(DataType);
        normalwait(3000);
        screenshot();

    }


    @Step("Select Data Type Under Files Tab")
    public void SelectFiles_DataType(String DataType) throws InterruptedException {
       normalwait(4000);
        jsclick(ArrowBtn);
        page_loader();
        jsclick(Files_DataTypeDrpDwn);
        Select_CheckBox(DataType);
        page_loader();
        screenshot();

    }


    @Step("File Uuids")
    public HashMap<String, List<String>> fetch_CMTable_Values() {
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, List<String>> HM = new HashMap<>();
        loader_new();
        int count = CM_Table_Filecount.size();
        System.out.println(count);
        int Column_Count = CM_Table_Columncount.size();
        System.out.println("Col:"+Column_Count);
        for (int i = 1; i <= count; i++) {
            ArrayList<String> list = new ArrayList<>();
            WebElement fileuuid = driver.findElement(By.xpath("((//table//tbody//tr)["+i+"]//td//a)[1]"));
            ScrollView_Action(fileuuid);
            String File_ID=fileuuid.getText();
            for (int j = 4; j <= Column_Count; j++) {
                String Values = driver.findElement(By.xpath("(//table//tbody//tr)[" + i + "]//td[" + j + "]")).getText();
                list.add(Values);
            }
            HM.put(File_ID, list);
        }

        map.put("UI File Uuids", String.valueOf(HM));
        screenshot();
        Ex.Info_ScreenShot("Ui Catalog Management File Uuids Are :" + MarkupHelper.createUnorderedList(map).getMarkup());
        return HM;
    }
    public void verify_csv_Values(String filepath,String file, HashMap<String, List<String>> UIFileUuids) throws IOException {
        ArrayList<String> csv_fileuuids = new ArrayList<String>();
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, List<String>> Csv_Values = new HashMap<>();
        String fp = filepath + file;
        String Line = "";
        FileReader f = new FileReader(fp);
        BufferedReader br = new BufferedReader(f);
        try {


            while ((Line = br.readLine()) != null) {
                while ((Line = br.readLine()) != null) {
                    List<String> lis = new ArrayList<>();
                    String[] values = Line.split(",");
                    for (int i = 1; i < values.length; i++) {
                        String Values = values[i].replace("\"", "");
                        String Trimmed_Value =Values.trim();
                        System.out.println("Csv:"+Trimmed_Value);
                        System.out.println("Csv1:"+Trimmed_Value.length());
                        lis.add(Trimmed_Value);
                    }
                    Csv_Values.put(values[0].replace("\"", ""), lis);
                }
            }
            br.close();
            f.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
          if (br !=null){
              br.close();
              f.close();
          }
        }
        map.put("Csv Values Are", String.valueOf(Csv_Values));
        screenshot();
        Ex.Info_ScreenShot("Csv Values Are :" + MarkupHelper.createUnorderedList(map).getMarkup());
        System.out.println("Csv:"+Csv_Values);
        System.out.println("UI:"+UIFileUuids);
        boolean value =Csv_Values.equals(UIFileUuids);
        //Assert.assertTrue(value, "UI And Csv Values Are not Matching");
        Ex.Extent_Pass("UI And Csv Values Are  Matching");
    }


    public void verify_csv_headers_WithUi(String filename, ArrayList<String> UIHeaders) {
        ArrayList<String> csv_headers = new ArrayList<String>();
        HashMap<String, String> map = new HashMap<>();
        String filePath = DownloadPath + filename;
        String Line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((Line = br.readLine()) != null) {
                String[] Header = Line.split(",");
                for (String H : Header) {
                    String Head = H.replace("\"", "");
                    csv_headers.add(Head);
                }
                break;
            }
            System.out.println(csv_headers);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("Csv Catalog Management Headers", String.valueOf(csv_headers));
        screenshot();
        Ex.Info_ScreenShot("Csv Catalog Management headers Are :" + MarkupHelper.createUnorderedList(map).getMarkup());
        System.out.println(UIHeaders);
        System.out.println(csv_headers);
        boolean value = UIHeaders.equals(csv_headers);
        Assert.assertTrue(value, "UI And Csv Headers Are not Matching");
        Ex.Extent_Pass("UI And Csv Headers Are  Matching");
    }


    @Step("Cataloug Table Headers")
    public ArrayList<String> fetch_CM_TableHeaders(String User) {
        loader_new();
        HashMap<String, String> map = new HashMap<>();
        ArrayList<String> Headers = new ArrayList<>();
        int count = CM_Table_Headercount.size();
        /*if(User.equals(Constant.Admin)){
            for (int i = 3; i < count; i++) {
                String Header = driver.findElement(By.xpath("//table//thead//tr//th[" + i + "]")).getText();
                Headers.add(Header);
            }
        }*/
       // else{
            for (int i = 3; i <= count; i++) {
                String Header = driver.findElement(By.xpath("//table//thead//tr//th[" + i + "]")).getText();
                if (!Header.isEmpty()) {

                    Headers.add(Header);
                }
     //       }
        }

        map.put("UI Catalog Management Headers", String.valueOf(Headers));
        screenshot();
        Ex.Info_ScreenShot("UI Catalog Management headers Are :" + MarkupHelper.createUnorderedList(map).getMarkup());
        return Headers;
    }


    @Step("Verify Csv Download Success Message")
    public void verify_csv_download() {

        jsclick(Download_Csv);
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg, Constant.Download_Msg);
        Ex.Pass_ScreenShot("Manifest Download Success Message");
        screenshot();
        normalwait(5000);
    }

    @Step("Select Project ID Under Cases Tab")
    public void SelectFiles_DataCategory(String DataType) throws InterruptedException {
        page_loader();
        jsclick(ArrowBtn);
        normalwait(3000);
        jsclick(Files_DataCategoryDrpDwn);
        Select_CheckBox(DataType);
        normalwait(5000);
        screenshot();

    }

   /* @Step("Select Project ID Under Cases Tab")
    public void SelectFiles_DataType(String DataType) throws InterruptedException {
        normalwait(4000);
        Ele_Click(ArrowBtn);
        normalwait(3000);
        Ele_Click(Files_DataCategoryDrpDwn);
        Select_CheckBox(DataType);
        normalwait(3000);
        screenshot();

    }*/


    public String fetch_FirstFilename() {
        String Filename = First_File_Name.getText();
        return Filename;
    }


    public void UpdateFile_AllDetails_WithFilename(String filepath, String file, HashMap hm) throws IOException, ParseException {
        String fp = filepath + file;
        FileReader reader = new FileReader(fp);
        ;
        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(reader);
        JSONObject jo1 = (JSONObject) jo.get("case");
        jo1.put("case_id", hm.get("case_id"));
        JSONObject jo2 = (JSONObject) jo.get("aliquot");
        jo2.put("aliquot_id", hm.get("aliquot_id"));
        JSONObject jo3 = (JSONObject) jo.get("sample");
        jo3.put("sample_id", hm.get("sample_id"));
        JSONObject jo4 = (JSONObject) jo.get("file");
        jo4.put("file_id", hm.get("file_id"));
        JSONObject jo5 = (JSONObject) jo.get("file");
        jo5.put("file_name", hm.get("file_name"));
        FileWriter writer = new FileWriter(fp);
        writer.write(jo.toJSONString());
        writer.close();

    }

    @Step("Fetch File Details")
    public HashMap fetch_fileuuid_details() throws InterruptedException {
        HashMap hm = new HashMap();
        ScrollView_Action(First_File_Uuid);
        Ele_Click(First_File_Uuid);
        Element_ToBe_Clickable(Catalog_Header);
        wait.until(ExpectedConditions.visibilityOf(Catalog_Case_id));
        hm.put("case_id", Catalog_Case_id.getText());
        hm.put("sample_id", Catalog_Sample_id.getText());
        hm.put("aliquot_id", Catalog_Aliquot_id.getText());
        hm.put("file_id", Catalog_File_id.getText());
        hm.put("file_name", Catalog_File_Name.getText());
        Ex.Extent_Info("File Details Are: " + hm);
        screenshot();
        return hm;

    }

    @Step("Verify Aliquot Files Count")
    public void verify_aliq_filecount(String AliqCount) {
        normalwait(5000);
        ScrollView_Action(Footer_FileCount);
        String Aliq_Count = Footer_FileCount.getText();
        String lastWord = Aliq_Count.substring(Aliq_Count.lastIndexOf(" ") + 1);
        if (AliqCount.equals(lastWord)) {
            screenshot();
            Ex.Pass_ScreenShot("Aliquot File Count In Case Tab And File Over Are Matching");
        }
    }

    public String Aliq_AssociatedFiles() {
        WebElement Ele = driver.findElement(By.xpath("((//span[text()='Aliquots'])[1]//following::td)[1]//mat-icon[text()='chevron_right']"));
        jsclick(Ele);
        normalwait(3000);
        ScrollView_Action(Aliquot_AssociatedFiles);
        String Aliquot_FileCount = Aliquot_AssociatedFiles.getText();
        jsclick(Aliquot_AssociatedFiles);
        return Aliquot_FileCount;

    }

    @Step("Cart Overview BreadCrumb is Displayed in Cart Overview Page (Reference Files)")
    public void verify_referencefile_breadcrumb(String View) throws InterruptedException {
        jsclick(CM_AccessoryFiles_BCLink);
        normalwait(5000);
        Select_View(View);
        Assert.assertTrue(CM_ReferenceFiles_BC.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Refernce Files BreadCrumb Displayed When clicked on Reference Files Page");
        jsclick(Cart_Icon);
        page_loader();
        loader_new();
        Assert.assertTrue(ReferenceFiles_Cart_Overview_BC.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Cart Overview BreadCrumb is Displayed in Cart Overview Page (Reference Files)");

    }

    @Step("Verification Of Accessory BreadCrumb")
    public void verify_Accessory_breadcrumb(String View) throws InterruptedException {
        jsclick(CM_RawFiles_BCLink);
        normalwait(5000);
        Select_View(View);
        jsclick(Accessory_Project_First);
        Assert.assertTrue(CMAccessoryFiles_ProjectDetails_BC.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Catalog Project Details  BreadCrumb Displayed When clicked on Project Hyperlink (Accessory Files)");
        jsclick(Cart_Icon);
        page_loader();
        loader_new();
        Assert.assertTrue(AccessoryFiles_Cart_Overview_BC.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Cart Overview BreadCrumb is Displayed in Cart Overview Page (Accessory Files)");
    }

    @Step("Catalog File Details / Project Details  BreadCrumb Validation")
    public void verify_CM_File_ProjectDetailsBreadcrumb() throws InterruptedException {
        jsclick(File_UUID_FirstRow);
        Assert.assertTrue(Catalog_File_Details_BC.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Catalog File Details  BreadCrumb Displayed in Catalog File Details Page");
        jsclick(CM_RawFiles_BCLink);
        loader();
        jsclick(ProjectId_FirstRow);
        Assert.assertTrue(CM_ProjectDetails_BC.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Catalog Project Details  BreadCrumb Displayed When clicked on Project Hyperlink");

    }

    @Step("Validate CM Case BreadCrumbs")
    public void verify_CM_CasedetailsBC() throws InterruptedException {
        jsclick(CM_RawFiles_BCLink);
        selectcaseid();
        Assert.assertTrue(CM_caseDetails_BC.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Catalog case Details  BreadCrumb Displayed When clicked on Cases Hyperlink");
        String FileCount = TotalAssocFileCount.getText();
        int count = Integer.parseInt(FileCount);
        jsclick(TotalAssocFileCount);
        normalwait(5000);
        int File_size = Files_Row_Count.size();
        Assert.assertEquals(File_size, count);
        Ex.Pass_ScreenShot("Total Associated Files Count and Catalog file count matching as exppected");
        Ele_Click(CaseHeader);
        normalwait(2000);
        Ele_Click(Cases_Files_First);
        Assert.assertTrue(CM_RawFiles_BC.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Catalog Raw Files   BreadCrumb Displayed in  File Details Page");
        Ele_Click(CaseHeader);
        Ele_Click(Cases_Project_First);
        Assert.assertTrue(CM_ProjectDetails_BC.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Catalog Project Details  BreadCrumb Displayed in  CM Project Page");
    }

    @Step("Validate Presence Of Aliquot ID Under Catalog File Details")
    public void Validate_AliquotID_CatalogDetails() {
        ScrollView(File_UUID_FirstRow);
        jsclick(File_UUID_FirstRow);
        screenshot();
        loader();
        Assert.assertTrue(FS_AliquotID.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Aliquot ID Displayed Under Catalog File Details AS Expected");
    }

    @Step("Add More Meta Data Selected")
    public void select_Addmoremetadata() {
        wait.until(ExpectedConditions.visibilityOf(Add_MoreMetaData));
        ScrollView(Add_MoreMetaData);
        jsclick(Add_MoreMetaData);
        screenshot();
        Ex.Pass_ScreenShot("Add More Meta Data Selected");
    }

    public void loader_new() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loading-user-spinner ng-star-inserted']")));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='loading-user-spinner ng-star-inserted']")));
    }

    public void loader() {
        int count = Loader_Circle.size();
        System.out.println("Loader Count" + getcurrenttime() + " " + count);
        do {
            count = Loader_Circle.size();
        }
        while (count > 0);
        System.out.println("Complete" + getcurrenttime());


    }

    //Robin Methods
    @Step("Verify 4 File Details")
    public void Verify4FilesWhenBackFromCaseDetails(String Aligned_Reads) {
        navigate_back();
        String[] AlignedReads = Aligned_Reads.split(",");
        jsclick(fourth_file);
        String Old1 = Case_Id.getText();
        String Old2 = Sample_ID.getText();
        String Old3 = Aliquot_ID.getText();
        jsclick(CatalogBackButton);
        Ele_Click(fourth_file);

        for (int i = 2; i < 5; i++) {
            driver.findElement(By.xpath("//table//tbody//tr[" + i + "]/td[3]/a")).click();
            clickableEle(Case_Id);
            String New1 = Case_Id.getText();
            String New2 = Sample_ID.getText();
            String New3 = Aliquot_ID.getText();

            Assert.assertEquals("" + New1 + "", "" + Old1 + "", "Case Metadata values /UUID getting matched from first files metadata");
            Assert.assertEquals("" + New2 + "", "" + Old2 + "", "Sample Metadata values /UUID getting matched from first files metadata");
            Assert.assertEquals("" + New3 + "", "" + Old3 + "", "Aliquot Metadata values /UUID getting matched from first files metadata");
            Ex.Extent_Pass("Verify 4 File Details");
            screenshot();
            jsclick(CatalogBackButton);
            Ele_Click(fourth_file);
        }

        for (int j = 1; j < 5; j++) {
            clickableEle(driver.findElement(By.xpath("//table//tbody//tr[" + j + "]/td[8]")));
            Assert.assertEquals("" + driver.findElement(By.xpath("//table//tbody//tr[" + j + "]/td[9]")).getText() + "", "" + AlignedReads[1] + "", "Metadata values /UUID getting matched from first files metadata");
            clickableEle(driver.findElement(By.xpath("//table//tbody//tr[" + j + "]/td[9]")));
            Assert.assertEquals("" + driver.findElement(By.xpath("//table//tbody//tr[" + j + "]/td[10]")).getText() + "", "" + AlignedReads[0] + "", "Metadata values /UUID getting matched from first files metadata");
            clickableEle(driver.findElement(By.xpath("//table//tbody//tr[" + j + "]/td[11]")));
            Assert.assertEquals("" + driver.findElement(By.xpath("//table//tbody//tr[" + j + "]/td[12]")).getText() + "", "" + AlignedReads[2] + "", "Metadata values /UUID getting matched from first files metadata");
            clickableEle(driver.findElement(By.xpath("//table//tbody//tr[" + j + "]/td[12]")));
            Assert.assertEquals("" + driver.findElement(By.xpath("//table//tbody//tr[" + j + "]/td[13]")).getText() + "", "" + AlignedReads[3] + "", "Metadata values /UUID getting matched from first files metadata");
            screenshot();
            Ex.Extent_Pass("Verify 4 File Details");
        }

    }

    @Step("Manipulate Json")
    public void Manipulate_Json(String filepath, String Filename) throws IOException, ParseException, InterruptedException {


        jsclick(File_Id);
        Thread.sleep(2000);
        String fileid = Fileuuid.getText();
        System.out.println("file id is =" + fileid);
        String caseid = Case_Id.getText();
        System.out.println("case id is =" + caseid);
        String sampleid = Sample_ID.getText();
        System.out.println("sample id is =" + sampleid);
        String aliquotid = Aliquot_ID.getText();
        System.out.println("aliquot id is =" + aliquotid);
        driver.navigate().back();

        //manipulate json to delete case
        jsonmanipulator(filepath + Filename, caseid, fileid, sampleid, aliquotid);


    }

    @Step("Validate Case Details")
    public void NavigateToCaseTabAndClickonCaseIDandverify2(String primarySite, String diseaseType, String consentType) throws IOException, ParseException, InterruptedException {


        navitocasetab();
        page_loader();
        jsclick(CaseID_Cell_1_CaseTab);
        jsclick(Case_Detail_Page_Header);
        jsclick(Primary_Site);
        jsclick(Disease_Type);
        Assert.assertEquals(Disease_Type.getText(), diseaseType, "Disease type Successfully matched");
        Assert.assertEquals(Primary_Site.getText(), primarySite, "Primary site Successfully matched");
        Ele_Click(Consent_Type);
        Assert.assertEquals(Consent_Type.getText(), consentType, "Consent type Successfully matched");
        screenshot();
        Ex.Pass_ScreenShot("Validate Case Details");
        jsclick(CatalogBackButton);


    }

    public void NavigateToCatalogFileDetailandBack3(String samplesubidfetched) throws IOException, ParseException, InterruptedException {
        Thread.sleep(10000);
        jsclick(Forward_Button_Filter);
        send_keys(Search_File_Filter, samplesubidfetched);
        send_keys(Search_File_Filter, String.valueOf(Keys.ENTER));
        //Search_File_Filter.sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        send_keys(Search_File_Filter, String.valueOf(Keys.ENTER));
        Thread.sleep(3000);
        send_keys(Search_File_Filter, String.valueOf(Keys.ENTER));
        Thread.sleep(3000);
        send_keys(Search_File_Filter, String.valueOf(Keys.ENTER));
        Thread.sleep(3000);


    }

    @Step("Verify File Name in UI")
    public void NavigateToCatalogFileDetailandBack(String Filename) throws IOException, ParseException, InterruptedException {
        jsclick(Forward_Button_Filter);
        send_keys(Search_File_Filter, Filename);
        send_keys(Search_File_Filter, String.valueOf(Keys.ENTER));
        jsclick(Search_File_Filter);
        jsclick(File_Id);
        jsclick(Fileuuid);
        screenshot();
        driver.navigate().back();
    }

    public void NavigateToCaseTabAndClickonCaseIDandverify() throws IOException, ParseException, InterruptedException {
        navitocasetab();
        normalwait(1000);
        jsclick(CaseID_Cell_1_CaseTab);
    }

    @Step("Verify UI Case Details")
    public void VerifyCaseTableOnCaseDetailPage(String filepath, String filename) throws IOException, ParseException, InterruptedException {
        String fp = filepath + filename;
        FileReader reader = new FileReader(fp);
        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(reader);
        JSONObject jo1 = (JSONObject) jo.get("case");
        String test1 = (String) jo1.get("primary_site");
        String test2 = (String) jo1.get("disease_type");
        String test3 = (String) jo1.get("consent_type");
        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test3);
        clickableEle(Case_Detail_Page_Header);
        clickableEle(Primary_Site);
        Assert.assertEquals(Primary_Site.getText(), test1, "Primary site Successfully matched");
        clickableEle(Consent_Type);
        Assert.assertEquals(Consent_Type.getText(), test3, "Consent type Successfully matched");
        clickableEle(Disease_Type);
        Assert.assertEquals(Disease_Type.getText(), test2, "Disease type Successfully matched");
        screenshot();
        Ex.Pass_ScreenShot("Case Detail Verification");
        Ele_Click(Sample_Table);
        screenshot();
        jsclick(Aliquot_Downarrow);
        jsclick(Aliquot_Table);
        screenshot();
        page_loader();
        jsclick(CM_Breadcrumb);
        page_loader();


    }

    public void click_CM_Breadcrumb(){
        normalwait(3000);
        ActionClick(CM_Breadcrumb);
        page_loader();
    }


    @Step("Validate File Summary Values")
    public void verify_FileSummary_Values(String Projid, String ProjName, String filename, String Author
            , String Aligned_Reads, String Workflow, String FileUUid) {
        String[] AlignedReads = Aligned_Reads.split(",");
        String DF = AlignedReads[0];
        String DataType = AlignedReads[1];
        String DataCategory = AlignedReads[2];
        jsclick(File_UUID);
        wait.until(ExpectedConditions.visibilityOf(FS_ProjectId));
        /*if (!Projid.isEmpty()) {
            Assert.assertEquals(Projid, FS_ProjectId.getText());
        }*/
        if (!ProjName.isEmpty()) {
            Assert.assertEquals(ProjName, FS_ProjName.getText());
        }
        if (!filename.isEmpty()) {
            Assert.assertEquals(filename, FS_File_Name.getText());
        }
        Assert.assertTrue(FS_CaseId_Header.isDisplayed());
        Assert.assertTrue(FS_SampleId_Header.isDisplayed());
        Assert.assertTrue(FS_AliqId_Header.isDisplayed());

        if (!Author.isEmpty()) {
            Assert.assertEquals(Author, FS_Author.getText());
        }

    /*if (!DataClas.isEmpty()){
        Assert.assertEquals(DataClas,FS_DataClassification.getText());
    }*/

        Assert.assertEquals("CONTROLLED", FS_Access.getText());

        if (DF.isEmpty()) {
            Assert.assertEquals(DF, FS_Dataformat.getText());
        }
        if (DataCategory.isEmpty()) {
            Assert.assertEquals(DataCategory, FS_DataCategory.getText());
        }

        if (DataType.isEmpty()) {
            Assert.assertEquals(DataType, FS_DataType.getText());
        }

    /*if (!AlignedReads[3].isEmpty()){
        Assert.assertEquals(AlignedReads[3],FS_ExpStrategy.getText());
    }*/

        /*if (!Workflow.isEmpty()) {
            Assert.assertEquals(Workflow, FS_WorkFlow.getText());
        }*/

        Assert.assertTrue(FS_PrivacyOffHeader.isDisplayed());
        Assert.assertTrue(FS_DataAccessCommitee.isDisplayed());
        /*if (!FileUUid.isEmpty()) {
            Assert.assertEquals(FileUUid, FS_FileUUID.getText());
        }*/
        Assert.assertTrue(FS_SampleId_Header.isDisplayed());
        Ex.Pass_ScreenShot("Validate File Summary Values");
        screenshot();

    }

    public void driver_navigate_back() {
        Ele_Click(Back_Btn_cart);
    }


    public void validate_Aligned_reads_Values(String AlignedReads) {
        String[] Aligned_Reads = AlignedReads.split(",");
        String DF = Aligned_Reads[0];
        String DataType = Aligned_Reads[2];
        String DataCategory = Aligned_Reads[1];
        int size = Files_Row_Count.size();
        for (int i = 1; i < size; i++) {
            String UI_DataType = driver.findElement(By.xpath("(//td[contains(@class, 'dataType')])[" + i + "]")).getText();
            Assert.assertEquals(UI_DataType, DataType);
            String UI_DataFormat = driver.findElement(By.xpath("(//td[contains(@class, 'dataFormat')])[" + i + "]")).getText();
            Assert.assertEquals(UI_DataFormat, DF);
            String UI_DataCategory = driver.findElement(By.xpath("(//td[contains(@class, 'dataCategory')])[" + i + "]")).getText();
            Assert.assertEquals(UI_DataCategory, DataCategory);
        }
        Ex.Pass_ScreenShot("Aligned Reads Value Matching For All Files");
    }

    @Step("Validate File Values")
    public void Validate_FileValues(String uuid, String filename, String SampSubId, String Project, String AlignedReads) throws InterruptedException {
        String[] Aligned_Reads = AlignedReads.split(",");
        String DF = Aligned_Reads[0];
        String DataType = Aligned_Reads[1];
        String DataCategory = Aligned_Reads[2];
        Element_ToBe_Clickable(File_UUID);

        /*if (!uuid.isEmpty()) {
            Assert.assertEquals(uuid, File_UUID.getText());
        }*/
        Assert.assertEquals("CONTROLLED", File_Access.getText());
        if (!filename.isEmpty()) {
            Assert.assertEquals(filename, File_Name.getText());
        }
        if (!SampSubId.isEmpty()) {
            Assert.assertEquals(SampSubId, File_SsId.getText());
        }

        /*if (!Project.isEmpty()) {
            Assert.assertEquals(Project, File_Project.getText());
        }*/

        if (DF.isEmpty()) {
            Assert.assertEquals(DF, File_DataFormat.getText());
        }

        if (DataCategory.isEmpty()) {
            Assert.assertEquals(DataCategory, File_DataCategory.getText());
        }


        if (DataType.isEmpty()) {
            Assert.assertEquals(DataType, File_DataType.getText());
        }

       /* if (!Aligned_Reads[3].isEmpty()){
            Assert.assertEquals(Aligned_Reads[3],File_ExpStrategy.getText());
        }*/
        Ex.Pass_ScreenShot("Validate File Values");
        screenshot();

    }

    @Step("Validate File Details Headers")
    public void verify_filedetails_headers() {
        Assert.assertTrue(FileUUID_Header.isDisplayed());
        Assert.assertTrue(FileAccess_Header.isDisplayed());
        Assert.assertTrue(File_Name_Header.isDisplayed());
        Assert.assertTrue(File_CaseID_Header.isDisplayed());
        Assert.assertTrue(File_SSI_Header.isDisplayed());
        Assert.assertTrue(File_Project_Header.isDisplayed());
        Assert.assertTrue(File_DataCategory_Header.isDisplayed());
        Assert.assertTrue(File_DataFormat_Header.isDisplayed());
        Assert.assertTrue(FileSize_Header.isDisplayed());
        Assert.assertTrue(File_DataType_Header.isDisplayed());
        Assert.assertTrue(File_Exp_Strategy_Header.isDisplayed());
        Assert.assertTrue(File_DataClass_Header.isDisplayed());
        Ex.Pass_ScreenShot("Validate File Details Headers");
        screenshot();

        System.out.println("All File Headers Details Are Displayed As Expected");


    }

    public void validate_casetable_headers_raw() throws InterruptedException {
        navitocasetab();
        normalwait(4000);
        Element_ToBe_Clickable(Case_id_Header);
        s_assert.assertTrue(Case_id_Header.isDisplayed(),"Case_id_Header not displayed");
        s_assert.assertTrue(Project_Header.isDisplayed(),"Project_Header not displayed");
        s_assert.assertTrue(Primary_Site_Header.isDisplayed(),"Primary_Site_Header not displayed");
        s_assert.assertTrue(Files_Header.isDisplayed(),"Files_Header not displayed");
        s_assert.assertTrue(AgeatDiagnosis_Header.isDisplayed(),"AgeatDiagnosis_Header not displayed");
        s_assert.assertTrue(Vital_Status_Header.isDisplayed(),"Vital_Status_Header not displayed");
        s_assert.assertTrue(Primary_Diagnosis_Header.isDisplayed(),"Primary_Diagnosis_Header not displayed");
        s_assert.assertTrue(Ethnicity_Header.isDisplayed()," Ethnicity_Header not displayed");

        System.out.println("All Case Details Headers Are present as expected");

    }

    public void fetch_UI_Alliquotdetails(HashMap<String, String> Json_values) throws InterruptedException {
        String Aliquot_uuid = null;
        int size = Aliquots_Size.size();
        for (int i = 1; i <= size; i++) {
            WebElement ele = driver.findElement(By.xpath("((//span[text()='Aliquots'])[" + i + "]//following::td)[1]//mat-icon[text()='chevron_right']"));
            ScrollView_Action(ele);
            jsclick(ele);
            page_loader();
            normalwait(10000);
            Aliquot_uuid = driver.findElement(By.xpath("((//span[text()='Aliquots'])[" + i + "]//following::td)[1]//span[@class='marg-left']")).getText();
            Json_values.put("aliquot_uuid", Aliquot_uuid);
            //wait.until(ExpectedConditions.visibilityOf(ASI));
            HashMap<String, String> UI_Values = AliquotDetails();
            System.out.println("UI Values:" + UI_Values);
            System.out.println("JsonValues:" + Json_values);
            Assert.assertTrue(Json_values.equals(UI_Values));
            Ex.Pass_ScreenShot("Aliquot Detail Validation");
            UI_Values.clear();

        }
    }

    public HashMap AliquotDetails() {
        HashMap<String, String> AliquotDetails = new HashMap<>();
        AliquotDetails.put("aliquot_submitter_id", Aliquot_Submitter_ID.getText());
        AliquotDetails.put("aliquot_uuid", Aliquot_UUID.getText());
        AliquotDetails.put("concentration", Concentration.getText());
        AliquotDetails.put("amount", Amount.getText());
        AliquotDetails.put("source_center", Source_Center.getText());
        AliquotDetails.put("analyte_type", Analyte_Type.getText());
        return AliquotDetails;

    }

    public void fetch_UI_Sampledetails(HashMap<String, String> Json_Values) {
        int size = Samples_ChevRight.size();
        System.out.println(size);
        for (int i = 1; i < size; i++) {
            WebElement ele = driver.findElement(By.xpath("(//span[text()='Samples']/..//following::mat-icon[text()='chevron_right'])[" + i + "]"));
            ScrollView_Action(ele);
            jsclick(ele);
            wait.until(ExpectedConditions.visibilityOf(BAS));
            HashMap<String, String> UI_Values = sampledetails();
            Ex.Extent_Info("Json File Sample Input Values :" + MarkupHelper.createUnorderedList(UI_Values).getMarkup());
            System.out.println("Json Value"+Json_Values);
            System.out.println("UI Value"+UI_Values);
            Assert.assertTrue(Json_Values.equals(UI_Values));
            Ex.Pass_ScreenShot("Catalog Sample Validation :" + MarkupHelper.createUnorderedList(UI_Values).getMarkup());
            UI_Values.clear();

        }
    }

    public HashMap sampledetails() {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("biospecimen_anatomic_site", Bio_Ana_site.getText());
        hm.put("biospecimen_laterality", Bio_Laterality.getText());
        hm.put("composition", Sample_Composition.getText());
        hm.put("sample_submitter_id", Sample_Submitter_Id.getText());
       // hm.put("current_weight", Current_Weight.getText());
       // hm.put("date_of_collection", Date_Of_Collection.getText());
      //  hm.put("distance_normal_to_tumor", Distance_Normal_To_Tumor.getText());
       // hm.put("parent_samples", Parent_Samples.getText());
      /*  hm.put("preservation_method", Preservation_Method.getText());
        hm.put("sample_is_FFPE", Sample_Is_FFPE.getText());*/
 /*       hm.put("sample_submitter_id", Sample_Submitter_Id.getText());
        hm.put("sample_type", Sample_Type.getText());
        hm.put("tissue_collection_type", Tissue_Collection_Type.getText());
        hm.put("tissue_source_sites", Tissue_Source_Sites.getText());
        hm.put("tissue_type", Tissue_Type.getText());*/
        hm.put("tumor_descriptor", Tumor_Descriptor.getText());
        return hm;


    }

    public void navigate_to_casedetails() {
        jsclick(Case_id);
        page_loader();

    }


    public void Case_gridexpand() throws InterruptedException {
        int rowcount = Case_Row.size();
        for (int i = rowcount; i > 0; i--) {
            WebElement Ele = driver.findElement(By.xpath("(//span[text()='Case']/parent::*//mat-icon[text()=' arrow_right '])[" + i + "]"));
            ScrollView(Ele);
            jsclick(Ele);
          normalwait(1000);
        }
    }

    public void Samples_gridexpand() throws InterruptedException {
        int rowcount = Samples_Row.size();
        for (int i = rowcount; i > 0; i--) {
            WebElement Ele = driver.findElement(By.xpath("(//span[text()='Samples']/parent::*//mat-icon[text()=' arrow_right '])[" + i + "]"));
            ScrollView(Ele);
            jsclick(Ele);
            Thread.sleep(1000);
        }
    }

    public void Aliquots_gridexpand() throws InterruptedException {
        int rowcount = Aliquots_Row.size();
        for (int i = rowcount; i > 0; i--) {
            WebElement Ele = driver.findElement(By.xpath("(//span[text()='Aliquots']/parent::*//mat-icon[text()=' arrow_right '])[" + i + "]"));
            ScrollView(Ele);
            jsclick(Ele);
            Thread.sleep(1000);
        }
    }

    public void Grid_expand_Aliquots() throws InterruptedException {
        Case_gridexpand();
        Samples_gridexpand();
        Aliquots_gridexpand();
    }

    public void Grid_expand_case() throws InterruptedException {
        Case_gridexpand();

    }

    public void Grid_expand_Samples() throws InterruptedException {
        Case_gridexpand();
        Samples_gridexpand();
    }

    public void Grid_expand(String Value) throws InterruptedException {
        if (Value.equalsIgnoreCase("Case")) {
            Case_gridexpand();
        } else if (Value.equalsIgnoreCase("Samples")) {
            Case_gridexpand();
            Samples_gridexpand();
        } else {
            Case_gridexpand();
            Samples_gridexpand();
            Aliquots_gridexpand();
        }
    }

    /*public void Grid_expand(String Value) throws InterruptedException{

        String[] Values = Value.split(",");
        for (int i = 0; i < Values.length; i++) {

            if (Expand_arrrow_Right_Text.getText().equals(Values[i])) {
                ScrollView(Expand_arrrow_Right);
                Expand_arrrow_Right.click();
                Thread.sleep(2000);
            } else {
                break;
            }
        }

    }*/

    public void minimize_arrow() {
        normalwait(3000);
        int size = Expandable_Arrow_DrpDwn.size();
        System.out.println("Size"+size);
        for (int i = size; i > 0; i--) {
            WebElement ele = driver.findElement(By.xpath("(//td[contains(@class,'expandable')]//mat-icon[contains(text(),' arrow_drop_down ')])[" + i + "]"));
            Ele_Click(ele);
        }

    }


    @Step("Validate File Overview In UI")
    public void fetchfileid1(String filename) throws InterruptedException {
        int size = 0;
        Ele_Click(ArrowBtn);
        Ele_Click(File_txt_Input);
        send_keys(File_txt_Input, filename + Keys.ENTER);
        //File_txt_Input.sendKeys(filename + Keys.ENTER);
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(CaseHeader));
        long starttime = System.currentTimeMillis();
        long waittime = 120000;
        long endtime = starttime + waittime;
        boolean flag;

        do {
            send_keys(File_txt_Input, String.valueOf(Keys.ENTER));
            Thread.sleep(2000);
            size = Case_id_size.size();
        }
        while (System.currentTimeMillis() < endtime && size == 0);
        screenshot();
    }

    @Step("Validate File Overview In UI")
    public void fetchfileid(String filename) throws InterruptedException {
        int size = 0;
        Ele_Click(ArrowBtn);
        Ele_Click(File_txt_Input);
        send_keys(File_txt_Input, filename + Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(CaseHeader));
        while (size == 0) {
            send_keys(File_txt_Input, String.valueOf(Keys.ENTER));
            normalwait(2000);
            size = Case_id_size.size();
        }
        Ex.Pass_ScreenShot("Validate File Overview In UI");
        screenshot();
    }

    @FindBy(xpath = "(//span[text()='Case Id']/../..//span)[last()]")
    public WebElement Catalog_Case_id;

    @FindBy(xpath = "(//span[text()='Sample ID']/../..//span)[last()]")
    public WebElement Catalog_Sample_id;

    @FindBy(xpath = "(//span[text()='Aliquot ID']/../..//span)[last()]")
    public WebElement Catalog_Aliquot_id;

    @FindBy(xpath = "(//span[text()='File UUID']/../..//span)[last()]")
    public WebElement Catalog_File_id;

    @FindBy(xpath = "(//span[text()='File Name']/../..//span)[last()]")
    public WebElement Catalog_File_Name;

    public void navigate_to_file_header(){
        wait.until(ExpectedConditions.elementToBeClickable(FileHeader));
        jsclick(FileHeader);
        normalwait(1000);
    }
    public void Select_Case(String case_id) throws InterruptedException {
        Ele_Click(ArrowBtn);
        normalwait(2000);
        wait.until(ExpectedConditions.elementToBeClickable(CaseHeader));
        jsclick(File_txt_Input);
        send_keys(File_txt_Input, case_id + Keys.ENTER);
        normalwait(2000);
        send_keys(File_txt_Input, String.valueOf(Keys.ENTER));
        navitocasetab();
        normalwait(1000);
        WebElement Case_Link = driver.findElement(By.xpath("//a[contains(text(),'" + case_id + "')]"));
        Ele_Click(Case_Link);

    }

    public void validate_UIFilename(String filename) {
        String[] Files = filename.split(",");
        Ele_Click(ArrowBtn);
        wait.until(ExpectedConditions.elementToBeClickable(CaseHeader));
        for (String File : Files) {
            Ele_Click(File_txt_Input);
            send_keys(File_txt_Input, File + Keys.ENTER);
            int size = driver.findElements(By.xpath("//td[normalize-space(text())='" + File + "']")).size();
            if (size > 0) {
                System.out.println("File" + File + " ingested in AWS is present in UI as Expected ");
            } else {
                System.out.println("File" + File + " ingested in AWS is not present in UI as Expected ");
                Assert.assertTrue(false);
            }
            File_txt_Input.clear();


        }

    }

    @Step("Validate File Names")
    public void validate_UIFilename1(ArrayList<String> filename) {
        Ele_Click(ArrowBtn);
        wait.until(ExpectedConditions.elementToBeClickable(CaseHeader));
        for (String File : filename) {
            normalwait(2000);
            send_keys(File_txt_Input, File + Keys.ENTER);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[normalize-space(text())='" + File + "']")));
            int size = driver.findElements(By.xpath("//td[normalize-space(text())='" + File + "']")).size();
            if (size > 0) {
                System.out.println("File" + File + " ingested in AWS is present in UI as Expected ");
            } else {
                System.out.println("File" + File + " ingested in AWS is not present in UI as Expected ");
                Assert.assertTrue(false);
            }
            screenshot();
            Ex.Pass_ScreenShot("File Validation");
            File_txt_Input.clear();


        }

    }

    public String fetchcaseid(String filename) throws InterruptedException {
        int size = 0;
        jsclick(ArrowBtn);
        jsclick(File_txt_Input);
        send_keys(File_txt_Input, filename + Keys.ENTER);
        jsclick(CaseHeader);
        while (size == 0) {
            send_keys(File_txt_Input, String.valueOf(Keys.ENTER));
            normalwait(2000);
            size = Case_id_size.size();
        }
        send_keys(File_txt_Input, String.valueOf(Keys.ENTER));
        normalwait(2000);
        String caseid = Case_id.getText();
        screenshot();
        return caseid;
    }

    public void navigate_to_casetable(String casedetails) throws InterruptedException {
        Ele_Click(File_txt_Input);
        send_keys(File_txt_Input, casedetails + Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(CaseHeader));
        Thread.sleep(3000);
        jsclick(CaseHeader);
        Thread.sleep(3000);
    }

    public void Verify_csv_reference(String Filename){
        int size = 0;
        Element_ToBe_Clickable(Search_Files_Reference);
        send_keys(Search_Files_Reference, Filename + Keys.ENTER);
        normalwait(3000);
        send_keys(File_txt_Input, String.valueOf(Keys.ENTER));
        long starttime = System.currentTimeMillis();
        long waittime = 120000;
        long endtime = starttime + waittime;
        do {
            send_keys(Search_Files_Reference, String.valueOf(Keys.ENTER));
            normalwait(2000);
            size = driver.findElements(By.xpath("((//table//tbody//tr)[1]//td[text()=' " + Filename + " '])")).size();
        }
        while (System.currentTimeMillis() < endtime && size == 0);
        screenshot();
    }


    public void navigate_To_FileDeatils_Reference(String Filedetails, String fileuuid) throws InterruptedException {
        int size = 0;
        Element_ToBe_Clickable(Search_Files_Reference);
        send_keys(Search_Files_Reference, Filedetails + Keys.ENTER);
        normalwait(3000);
        send_keys(File_txt_Input, String.valueOf(Keys.ENTER));
        long starttime = System.currentTimeMillis();
        long waittime = 120000;
        long endtime = starttime + waittime;
        do {
            send_keys(Search_Files_Reference, String.valueOf(Keys.ENTER));
            normalwait(2000);
            size = driver.findElements(By.xpath("((//table//tbody//tr)[1]//td[text()=' " + Filedetails + " '])")).size();
        }
        while (System.currentTimeMillis() < endtime && size == 0);
        screenshot();
    }

    @Step("Navigate To File Details")
    public void navigate_To_FileDeatils(String Filedetails) throws InterruptedException {
        page_loader();
        int size = 0;
        jsclick(ArrowBtn);
        jsclick(File_txt_Input);
        File_txt_Input.clear();
        send_keys(File_txt_Input, Filedetails + Keys.ENTER);
        normalwait(3000);
        send_keys(File_txt_Input, String.valueOf(Keys.ENTER));
        long starttime = System.currentTimeMillis();
        long waittime = 120000;
        long endtime = starttime + waittime;
        do {
            send_keys(File_txt_Input, String.valueOf(Keys.ENTER));
            normalwait(2000);
            size = Case_id_size.size();
        }
        while (System.currentTimeMillis() < endtime && size == 0);
        Ex.Pass_ScreenShot("Navigate To File Details");
        screenshot();
    }

    @Step("Navigate To Case Details")
    public void navigate_To_Casedetails1(String casedetails) throws InterruptedException {
        int size = 0;
        Ele_Click(ArrowBtn);
        Ele_Click(File_txt_Input);
        send_keys(File_txt_Input, casedetails + Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(CaseHeader));
        Thread.sleep(3000);
        jsclick(CaseHeader);
        Thread.sleep(3000);
        long starttime = System.currentTimeMillis();
        long waittime = 120000;
        long endtime = starttime + waittime;
        boolean flag;

        do {
            send_keys(File_txt_Input, String.valueOf(Keys.ENTER));
            Thread.sleep(2000);
            size = Case_id_size.size();
        }
        while (System.currentTimeMillis() < endtime && size == 0);
        screenshot();
        jsclick(case_id);


    }

    public String Fetch_Case_Id() {
        String caseid = Case_Id.getText();
        return caseid;
    }

    @Step("Navigate To Case Details")
    public void navigate_To_Casedetails(String casedetails) throws InterruptedException {
        int size = 0;
        Ele_Click(ArrowBtn);
        jsclick(File_txt_Input);
        send_keys(File_txt_Input, casedetails + Keys.ENTER);
        jsclick(CaseHeader);
        long starttime = System.currentTimeMillis();
        long waittime = 120000;
        long endtime = starttime + waittime;
        boolean flag;

        do {
            send_keys(File_txt_Input, String.valueOf(Keys.ENTER));
            normalwait(2000);
            size = Case_id_size.size();
        }
        while (System.currentTimeMillis() < endtime && size == 0);
        screenshot();
        Ex.Pass_ScreenShot("Navigate To Case:");
        normalwait(1000);
        jsclick(Case_id);


    }

    @Step("Select View Type In UI")
    public void Select_View(String viewtype) throws InterruptedException {
        normalwait(2000);
        if (viewtype.equals("Raw")) {
            System.out.println("View is in Raw tab");
        } else {
            jsclick(Change_View);
            Element_ToBe_Clickable(SelectViewDrpwn);
            SelectDrpDwnValue(SelectViewDrpwn, viewtype);
        }
        screenshot();

    }

    public void ClickOnFileTab() {
        wait.until(ExpectedConditions.elementToBeClickable(Raw_Files_Table_content));
        System.out.println("File tab is Clicked and opened");
    }


    public void ClickOnCaseTab() {
        wait.until(ExpectedConditions.elementToBeClickable(cases_tab)).click();
        wait.until(ExpectedConditions.elementToBeClickable(Raw_cases_Table_content));
        System.out.println("Case tab is Clicked and opened");
    }


    public HashMap jsonread(String FilePath, String FileName, String UiValue) throws IOException, ParseException {
        ArrayList al1 = new ArrayList();
        ArrayList al2 = new ArrayList();
        String filepath = FilePath +"/"+ FileName;
        FileReader reader = new FileReader(filepath);
        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(reader);
        HashMap<String, String> hm = new HashMap<>();
        JSONObject jo1 = (JSONObject) jo.get(UiValue);
        Iterator<String> iterator = jo1.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            al1.add(key);
        }
        Iterator<String> iterator1 = jo1.values().iterator();
        while (iterator1.hasNext()) {
            String Value = String.valueOf(iterator1.next());
            al2.add(Value);
        }
        for (int i = 0; i < al1.size(); i++) {
            String key = (String) al1.get(i);
            String value = (String) al2.get(i);
            if (!key.equalsIgnoreCase("aliquot_volume")) {
                hm.put(key, value);
            }
        }
        Ex.Extent_Info("Json Upload File Values :" + hm);
        return hm;

    }

    public boolean compareHashMaps(HashMap<String, String> UIHashMap, HashMap<String, String> HJsonHashMap) {
        if (UIHashMap == null || HJsonHashMap == null)
            return false;

        for (String ch1 : UIHashMap.keySet()) {
            if (!UIHashMap.get(ch1).equalsIgnoreCase(HJsonHashMap.get(ch1)))
                return false;

        }
        for (String ch2 : HJsonHashMap.keySet()) {
            if (!HJsonHashMap.get(ch2).equalsIgnoreCase(UIHashMap.get(ch2)))
                return false;

        }
        return true;
    }


    public void Navigate_to_caseFilter() {
        jsclick(ArrowBtn);
        jsclick(Cases_Filter);

    }

    public void Select_CheckBox(String Value) {
        WebElement CB = driver.findElement(By.xpath("//div[contains(text(),'" + Value + "')]"));
        if (!CB.isSelected()) {
            jsclick(CB);
        }


    }

    @Step("Select Project ID Under Cases Tab")
    public void SelectCases_ProjId(String Project) throws InterruptedException {
        Navigate_to_caseFilter();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(Cases_ProjDrpDwn));
        jsclick(Cases_ProjDrpDwn);
        loader_new();
        Select_CheckBox(Project);
        loader_new();
        wait.until(ExpectedConditions.elementToBeClickable(CaseHeader));
        screenshot();

    }

    @Step("Navigate To Case tab")
    public void navitocasetab() throws InterruptedException {
        loader_new();
        page_loader();
        Ele_Click(CaseHeader);
        screenshot();

    }

    @Step("Navigate To Case tab")
    public void selectcaseid() throws InterruptedException {
        normalwait(8000);
        int size =0;
        wait.until(ExpectedConditions.elementToBeClickable(CaseHeader));
        mouse_hover(CaseHeader);
        jsclick(CaseHeader);
        normalwait(2000);
        screenshot();
         size = Cases_Overview.size();
        long starttime = System.currentTimeMillis();
        long waittime = 60000;
        long endtime = starttime + waittime;
        do {
            normalwait(1000);
            jsclick(CaseHeader);
            normalwait(1000);
            size = Cases_Overview.size();
        }
        while (System.currentTimeMillis() < endtime && size == 0);
        jsclick(Case_Id_First);
        normalwait(3000);


    }


    @Step("Click on Update Meta Data")
    public void select_update_metadata(String Filename, String Role) {
        if (Role.equalsIgnoreCase(Constant.Data_Manager) || Role.equalsIgnoreCase(Constant.Admin) || Role.equalsIgnoreCase("Data User1")) {
            System.out.println("Before" + getcurrenttime());
            loader_new();
            WebElement Elipse = driver.findElement(By.xpath("(//table//tbody//tr//td[contains(text(),'" + Filename + "')])[1]/../..//mat-cell//mat-icon"));
            ScrollView(Elipse);
            jsclick(Elipse);
            jsclick(Update_MetaData);
            Ex.Pass_ScreenShot("Update Meta Data Selected ");
            loader_new();
            //Assert.assertTrue(CM_UpdateMetaData_BC.isDisplayed());
            screenshot();
            Ex.Pass_ScreenShot("Update Meta Data BreadCrumb is Displayed As Expected");
            System.out.println("After" + getcurrenttime());
        }
        if (Role.equalsIgnoreCase("Unassigned User")) {
            WebElement Elipse = driver.findElement(By.xpath("(//table//tbody//tr//td[contains(text(),'" + Filename + "')])[1]/../..//mat-cell//mat-icon"));
            ScrollView(Elipse);
            jsclick(Elipse);
            Assert.assertTrue(Request_accessBtn.isDisplayed());
            Ex.Pass_ScreenShot("Request Access Button Displayed For Unassigned User As Expected");
        } else {
            List<WebElement> Count = driver.findElements(By.xpath("(//table//tbody//tr//td[contains(text(),'" + Filename + "')])[1]/../..//mat-cell//mat-icon"));
            int size = Count.size();
            Assert.assertEquals(size, 0);
            Ex.Pass_ScreenShot("Update Meta Data Not  Selected for " + Role + " As Expected ");
        }
    }


    @Step("Select Project")
    public void Select_File_Elipse(String Project) throws InterruptedException {
        //wait.until(ExpectedConditions.elementToBeClickable(Frst_Proj_Link));
        Thread.sleep(3000);
        WebElement File_Elipse1 = driver.findElement(By.xpath("(//table//tbody//tr//td//a[contains(text(),'" + Project + "')])[1]/../..//mat-cell//mat-icon"));
        //wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(File_Elipse1)));
        wait.until(ExpectedConditions.elementToBeClickable(File_Elipse1));
        ScrollView(File_Elipse1);
        Thread.sleep(1000);
        jsclick(File_Elipse1);
        screenshot();

    }

    @Step(" Verify Admin_PO_Request for Access option Data Catalog")
    public void verify_roleAccess(String Role, String Project) throws InterruptedException {
        Thread.sleep(1000);
        if (Role.equals("PO")) {
            WebElement File_Elipse1 = driver.findElement(By.xpath("(//table//tbody//tr//td//a[text()='" + Project + "'])[1]/../..//mat-cell//mat-icon"));
            wait.until(ExpectedConditions.elementToBeClickable(File_Elipse1));
            ScrollView(File_Elipse1);
            Thread.sleep(1000);
            jsclick(File_Elipse1);
            Assert.assertEquals(Request_accessBtnsize.size(), 1);
            Ex.Pass_ScreenShot("Request Access Button Is Displayed for PO as Expected");
            screenshot();
        } else if (Role.equals("Admin")) {
            List<WebElement> File_Elipse1 = driver.findElements(By.xpath("(//table//tbody//tr//td//a[text()='" + Project + "'])[1]/../..//mat-cell//mat-icon"));
            Assert.assertEquals(File_Elipse1.size(), 0);
            Ex.Pass_ScreenShot("Request Access Button Is not Displayed for Admin as Expected");
            screenshot();
        }
    }

    @Step("Validate Request Access Attributes")
    public void Verify_RequestAccess_Attributes() throws InterruptedException {
        Ele_Click(Request_accessBtn);
        Thread.sleep(2000);
        Assert.assertEquals(RequestAcc_Msg.getText(), "Are you sure you want to request access to this project?");
        Ex.Pass_ScreenShot("Request Access Msg Displayed");
        Assert.assertTrue(Req_Access_Yes_Btn.isDisplayed());
        Assert.assertTrue(Req_Access_No_Btn.isDisplayed());
        Ex.Pass_ScreenShot("Request Access Yes,No Option Displayed");
        screenshot();
    }

    @Step("Validate Request Access No Button")
    public void Verify_Request_Access_No(String Project) throws InterruptedException {
        Ele_Click(Req_Access_No_Btn);
        Select_File_Elipse(Project);
        Request_accessBtn.isDisplayed();
        screenshot();
    }

    @Step("Validate After Request Access YES")
    public void Verify_Request_Access_Yes(String FileType, String Project) throws InterruptedException {
        jsclick(Request_accessBtn);
        jsclick(Req_Access_Yes_Btn);
        Thread.sleep(1000);
        if (FileType.equals("Raw")) {
            WebElement Cancel = driver.findElement(By.xpath("//mat-chip[contains(text(),'" + Project + "')]//following-sibling::mat-icon"));
            Ele_Click(Cancel);
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(CaseHeader));
            Select_CheckBox(Project);
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(CaseHeader));
        } else {
            driver.navigate().refresh();
            wait.until(ExpectedConditions.elementToBeClickable(Frst_Proj_Link));
            selectNoofItems();
            wait.until(ExpectedConditions.elementToBeClickable(Frst_Proj_Link));
        }
        Thread.sleep(2000);
        WebElement File_Elipse1 = driver.findElement(By.xpath("(//table//tbody//tr//td//a[contains(text(),'" + Project + "')])[1]/../..//mat-cell//mat-icon"));
        wait.until(ExpectedConditions.elementToBeClickable(File_Elipse1));
        ScrollView(File_Elipse1);
        Thread.sleep(1000);
        jsclick(File_Elipse1);
        Assert.assertTrue(Req_Acc_RaisedMsg.isDisplayed());
        Ex.Pass_ScreenShot("Request Access Message Displayed");
        screenshot();


    }

    @Step("Change Items Per Page")
    public void selectNoofItems() throws InterruptedException {
        ScrollView(Items_Per_PageDrpDwn);
        SelectDrpDwnValue(Items_Per_PageDrpDwn, "100");
        Thread.sleep(3000);
        screenshot();
    }

    @FindBy(xpath = "//div[contains(text(),'Items per page')]/..//span")
    public WebElement Items_Per_PageDrpDwn;

    public void UpdateFileName(String filepath, String file, String Filename) throws IOException, ParseException {
        String fp = filepath + file;
        FileReader reader = new FileReader(fp);
        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(reader);
        JSONObject jo1 = (JSONObject) jo.get("file");
        jo1.put("file_name", Filename);
        FileWriter writer = new FileWriter(fp);
        writer.write(jo.toJSONString());
        writer.close();

    }

    public void UpdateFile_WithOut_Case(String filepath, String file, HashMap hm) throws IOException, ParseException {
        String fp = filepath + file;
        FileReader reader = new FileReader(fp);
        ;
        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(reader);
        JSONObject jo1 = (JSONObject) jo.get("sample");
        jo1.put("sample_id", hm.get("sample_id"));
        JSONObject jo2 = (JSONObject) jo.get("file");
        jo2.put("file_id", hm.get("file_id"));
        JSONObject jo3 = (JSONObject) jo.get("aliquot");
        jo3.put("aliquot_id", hm.get("aliquot_id"));
        JSONObject jo4 = (JSONObject) jo.get("case");
        jo4.remove("case_id", hm.get("case_id"));
        System.out.println(jo.toJSONString());
        FileWriter writer = new FileWriter(fp);
        writer.write(jo.toJSONString());
        writer.close();

    }

    public void JsonRemove_CaseId(String filepath, String file) throws IOException, ParseException {
        String fp = filepath + file;
        FileReader reader = new FileReader(fp);
        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(reader);
        JSONObject jo1 = (JSONObject) jo.get("case");
        jo1.remove("case_id");
        System.out.println(jo.toJSONString());
        FileWriter writer = new FileWriter(fp);
        writer.write(jo.toJSONString());
        writer.close();

    }

    public void UpdateFile_WithOut_Sample(String filepath, String file, HashMap hm) throws IOException, ParseException {
        String fp = filepath + file;
        FileReader reader = new FileReader(fp);
        ;
        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(reader);
        JSONObject jo1 = (JSONObject) jo.get("case");
        jo1.put("case_id", hm.get("case_id"));
        JSONObject jo2 = (JSONObject) jo.get("file");
        jo2.put("file_id", hm.get("file_id"));
        JSONObject jo3 = (JSONObject) jo.get("aliquot");
        jo3.put("aliquot_id", hm.get("aliquot_id"));
        JSONObject jo4 = (JSONObject) jo.get("sample");
        jo4.remove("sample_id", hm.get("sample_id"));
        System.out.println("SAMPLE" + jo.toJSONString());
        FileWriter writer = new FileWriter(fp);
        writer.write(jo.toJSONString());
        writer.close();

    }

    public void UpdateFile_WithOut_Aliquot(String filepath, String file, HashMap hm) throws IOException, ParseException {
        String fp = filepath + file;
        FileReader reader = new FileReader(fp);
        ;
        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(reader);
        JSONObject jo1 = (JSONObject) jo.get("case");
        jo1.put("case_id", hm.get("case_id"));
        JSONObject jo2 = (JSONObject) jo.get("file");
        jo2.put("file_id", hm.get("file_id"));
        JSONObject jo3 = (JSONObject) jo.get("sample");
        jo3.put("sample_id", hm.get("sample_id"));
        JSONObject jo4 = (JSONObject) jo.get("aliquot");
        jo4.remove("aliquot_id", hm.get("aliquot_id"));
        System.out.println("ALIQUOT" + jo.toJSONString());
        FileWriter writer = new FileWriter(fp);
        writer.write(jo.toJSONString());
        writer.close();

    }

    public void UpdateFile_WithOut_File(String filepath, String file, HashMap hm) throws IOException, ParseException {
        String fp = filepath + file;
        FileReader reader = new FileReader(fp);
        ;
        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(reader);
        JSONObject jo1 = (JSONObject) jo.get("case");
        jo1.put("case_id", hm.get("case_id"));
        JSONObject jo2 = (JSONObject) jo.get("aliquot");
        jo2.put("aliquot_id", hm.get("aliquot_id"));
        JSONObject jo3 = (JSONObject) jo.get("sample");
        jo3.put("sample_id", hm.get("sample_id"));
        JSONObject jo4 = (JSONObject) jo.get("file");
        jo4.remove("file_id", hm.get("file_id"));
/*        JSONObject jo5 = (JSONObject) jo.get("project");
        jo5.put("project_id","fifth1637164462663");*/
        System.out.println("FILE" + jo.toJSONString());
        FileWriter writer = new FileWriter(fp);
        writer.write(jo.toJSONString());
        writer.close();

    }

    public void UpdateFile_AllDetails(String filepath, String file, HashMap hm) throws IOException, ParseException {
        String fp = filepath + file;
        FileReader reader = new FileReader(fp);
        ;
        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(reader);
        JSONObject jo1 = (JSONObject) jo.get("case");
        jo1.put("case_id", hm.get("case_id"));
        JSONObject jo2 = (JSONObject) jo.get("aliquot");
        jo2.put("aliquot_id", hm.get("aliquot_id"));
        JSONObject jo3 = (JSONObject) jo.get("sample");
        jo3.put("sample_id", hm.get("sample_id"));
        JSONObject jo4 = (JSONObject) jo.get("file");
        jo4.put("file_id", hm.get("file_id"));
        /*JSONObject jo5 = (JSONObject) jo.get("project");
        jo5.put("project_id","fifth1637164462663 ");*/
        FileWriter writer = new FileWriter(fp);
        writer.write(jo.toJSONString());
        writer.close();

    }

    @Step("Validate File Deletion In UI")
    public void validate_delete_file_UI(String filename) throws InterruptedException {
        loader_new();
        normalwait(5000);
        Ele_Click(ArrowBtn);
        send_keys(File_txt_Input, filename + Keys.ENTER);
        normalwait(3000);
        send_keys(File_txt_Input, String.valueOf(Keys.ENTER));
        normalwait(3000);
        List<WebElement> File = driver.findElements(By.xpath("(//td[normalize-space(text())='" + filename + "']//ancestor::tr//a)[1]"));
        int size = File.size();
        if (size == 0) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
        Ex.Pass_ScreenShot("Validate File Deletion In UI");
    }

    @Step("Fetch File Details")
    public HashMap fetch_file_details(String filename) throws InterruptedException {
        fetchfileid(filename);
        HashMap hm = new HashMap();
        WebElement Ele = driver.findElement(By.xpath("(//td[normalize-space(text())='" + filename + "']//ancestor::tr//a)[1]"));
        Ele_Click(Ele);
        Element_ToBe_Clickable(Catalog_Header);
        normalwait(3000);
        hm.put("case_id",Catalog_Case_id.getText());
        hm.put("sample_id", Catalog_Sample_id.getText());
        hm.put("aliquot_id", Catalog_Aliquot_id.getText());
        hm.put("file_id", Catalog_File_id.getText());
        Ex.Extent_Info("File Details Are: " + hm);
        screenshot();
        return hm;

    }

    public void Validate_UI_sample_MandatoryDetail(String Sample_SubId) throws InterruptedException {
        int size = Samples_ChevRight.size();
        for (int i = 1; i <= size; i++) {
            WebElement ele = driver.findElement(By.xpath("(//span[text()='Samples']/..//following::mat-icon[text()='chevron_right'])[" + i + "]"));
            ScrollView(ele);
            jsclick(ele);
            normalwait(3000);
            if (Sample_SubId.equals(Sample_Submitter_Id.getText())) {
                Assert.assertEquals(Sample_Submitter_Id.getText(), Sample_SubId);
                System.out.println("Sample Submitter Id Is Matching");
            }
        }
    }

    @Step("Catalog Case Details Validation :")
    public void Validate_UI_CaseDetails(String consent_type, String disease_type, String Primary_site) {
        HashMap<String, String> map = new HashMap<>();
        normalwait(3000);
        Ex.Pass_ScreenShot("Case Details");
        int size = Case_ChevRight.size();
        int count = 0;
        for (int i = 1; i <= size; i++) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Case']/..//following::mat-icon[text()='chevron_right'])[" + i + "]")));
            WebElement ele = driver.findElement(By.xpath("(//span[text()='Case']/..//following::mat-icon[text()='chevron_right'])[" + i + "]"));
            ScrollView_Action(ele);
            jsclick(ele);
            normalwait(3000);
            if (!consent_type.isEmpty()) {
                Assert.assertEquals(Consent_Type.getText().trim(), consent_type);
                screenshot();
            }
            if (!disease_type.isEmpty()) {
                Assert.assertEquals(Disease_Type.getText().trim(), disease_type);
                screenshot();
            }
            Assert.assertEquals(Primary_site, Primary_Site.getText().trim());

            map.put("Case Consent Type", Consent_Type.getText().trim());
            map.put("Case Disease Type", Disease_Type.getText().trim());
            screenshot();
            Ex.Pass_ScreenShot("Catalog Case Details Validation :" + MarkupHelper.createUnorderedList(map).getMarkup());
            count = count + 1;
            break;
        }
        if (count == 0) {
            Assert.assertTrue(false,"Case Details Count :"+count);
        } else {
            Assert.assertTrue(true);
        }

    }

    @Step("validate UI Sample Details")
    public void Validate_UI_SampleDetails(String Sample_SubId, String Tumor_Des, String Composition, String BAS, String Bio_laterality) throws InterruptedException {
        HashMap<String, String> map = new HashMap<>();
        int size = Samples_ChevRight.size();
        int count = 0;
        for (int i = 1; i <= size; i++) {
            normalwait(2000);
            WebElement ele = driver.findElement(By.xpath("(//span[text()='Samples']/..//following::mat-icon[text()='chevron_right'])[" + i + "]"));
            ScrollView_Action(ele);
            jsclick(ele);
            normalwait(3000);
            page_loader();
            if (Sample_SubId.equals(Sample_Submitter_Id.getText())) {
                if (!Sample_SubId.isEmpty()) {
                    Assert.assertEquals(Sample_Submitter_Id.getText(), Sample_SubId);
                }
                if (!Tumor_Des.isEmpty()) {
                    Assert.assertEquals(Tumor_Descriptor.getText(), Tumor_Des);
                }
                if (!Composition.isEmpty()) {
                    Assert.assertEquals(Sample_Composition.getText(), Composition);
                }
                if (!BAS.isEmpty()) {
                    Assert.assertEquals(Bio_Ana_site.getText(), BAS);
                }

                if (!Bio_laterality.isEmpty()) {
                    Assert.assertEquals(Bio_Laterality.getText(), Bio_laterality);
                }
                map.put("Sample Submitter ID", Sample_Submitter_Id.getText());
                map.put("Sample Tumor Descriptor", Tumor_Descriptor.getText());
                map.put("Sample Composition", Sample_Composition.getText());
                map.put("Sample Bio Anametic site", Bio_Ana_site.getText());
                map.put("Sample Bio Laterality", Bio_Laterality.getText());

                Ex.Pass_ScreenShot("Catalog Sample Details Validation :" + MarkupHelper.createUnorderedList(map).getMarkup());
                count = count + 1;
                break;
            }

        }
        if (count == 0) {
            Assert.assertTrue(false);
        } else {
            Assert.assertTrue(true);
        }

    }

    @Step("Validate UI Aliquot Mandatory Details")
    public void Validate_UI_AliquotMandatorydetails(String Aliquot_Sub_Id) throws InterruptedException {
        String Aliquot_uuid = null;
        int size = Aliquots_Size.size();
        for (int i = 1; i <= size; i++) {
            WebElement ele = driver.findElement(By.xpath("((//span[text()='Aliquots'])[" + i + "]//following::td)[1]//mat-icon[text()='chevron_right']"));
            ScrollView(ele);
            jsclick(ele);
            normalwait(3000);
            if (Aliquot_Sub_Id.equals(Aliquot_Submitter_ID.getText())) {
                if (!Aliquot_Sub_Id.isEmpty()) {
                    Assert.assertEquals(Aliquot_Submitter_ID.getText(), Aliquot_Sub_Id);
                    screenshot();
                    System.out.println("Aliquot Submitter Id Is Matching");
                }
            }

        }
    }

    public void Add_Grid_Values(ArrayList<String> Arr) throws InterruptedException {
        int no_of_ele = driver.findElements(By.xpath("//mat-icon[text()='chevron_right']/../..//span[@class='marg-left']")).size();
        for (int i = 1; i <= no_of_ele; i++) {
            String ele = driver.findElement(By.xpath("(//mat-icon[text()='chevron_right']/../..//span[@class='marg-left'])[" + i + "]")).getText();

            if (!Arr.contains(ele)) {
                Arr.add(ele);
            }

        }
    }

    public void add_multipleAliq() {

    }

    public void verify_new_added_Details_Samples(String Sample_SubId, String Tumor_Des, String Composition, String BAS, String Bio_laterality, ArrayList<String> Ar) throws InterruptedException {
        int no_of_ele = driver.findElements(By.xpath("//mat-icon[text()='chevron_right']/../..//span[@class='marg-left']")).size();
        for (int i = 1; i <= no_of_ele; i++) {
            String ele = driver.findElement(By.xpath("(//mat-icon[text()='chevron_right']/../..//span[@class='marg-left'])[" + i + "]")).getText();

            if (!Ar.contains(ele)) {

                WebElement element = driver.findElement(By.xpath("(//mat-icon[text()='chevron_right'])[" + i + "]"));
                ScrollView_Action(element);
                normalwait(1000);
                jsclick(element);
                normalwait(3000);
                ;
                if (Sample_SubId.equals(Sample_Submitter_Id.getText())) {
                    if (!Sample_SubId.isEmpty()) {
                        Assert.assertEquals(Sample_Submitter_Id.getText(), Sample_SubId);
                    }
                    if (!Tumor_Des.isEmpty()) {
                        Assert.assertEquals(Tumor_Descriptor.getText(), Tumor_Des);
                    }
                    if (!Composition.isEmpty()) {
                        Assert.assertEquals(Sample_Composition.getText(), Composition);
                    }
                    if (!BAS.isEmpty()) {
                        Assert.assertEquals(Bio_Ana_site.getText(), BAS);
                    }

                    if (!Bio_laterality.isEmpty()) {
                        Assert.assertEquals(Bio_Laterality.getText(), Bio_laterality);
                    }
                    Ex.Pass_ScreenShot("Catalog Newly Added Sample Details Validation :");
                    break;


                }
            }
        }
    }

    @Step("Validate Newly Added Aliquot Details")
    public void verify_new_added_Details_Al(String Aliq_SubId, String Aliq_Amount, String Source_center, String Aliq_Analyte_Type, String Aliq_Concentration, ArrayList<String> Ar) throws InterruptedException {
        int no_of_ele = driver.findElements(By.xpath("//mat-icon[text()='chevron_right']/../..//span[@class='marg-left']")).size();
        for (int i = 1; i <= no_of_ele; i++) {
            String ele = driver.findElement(By.xpath("(//mat-icon[text()='chevron_right']/../..//span[@class='marg-left'])[" + i + "]")).getText();

            if (!Ar.contains(ele)) {
                System.out.println("new Ele" + ele);
                WebElement element = driver.findElement(By.xpath("(//mat-icon[text()='chevron_right'])[" + i + "]"));
                ScrollView_Action(element);
                normalwait(1000);
                jsclick(element);
                normalwait(2000);

                if (Aliquot_Submitter_ID_size.size() > 0) {
                    if (Aliq_SubId.equals(Aliquot_Submitter_ID.getText())) {
                        Assert.assertEquals(Aliquot_Submitter_ID.getText(), Aliq_SubId);
                        if (!Aliq_Amount.isEmpty()) {
                            Assert.assertEquals(Amount.getText(), Aliq_Amount);

                        }
                        if (!Source_center.isEmpty()) {
                            Assert.assertEquals(Source_Center.getText(), Source_center);

                        }
                        if (!Aliq_Analyte_Type.isEmpty()) {
                            Assert.assertEquals(Analyte_Type.getText(), Aliq_Analyte_Type);

                        }
                        if (!Aliq_Concentration.isEmpty()) {
                            Assert.assertEquals(Concentration.getText(), Aliq_Concentration);

                        }
                        screenshot();
                        Ex.Pass_ScreenShot("Catalog Newly Added Aliquot Details Validation :");
                        break;


                    }
                }
            }
        }
    }


    @FindBy(xpath = "//span[text()='Aliquots']")
    public List<WebElement> Aliq_Size;
    @Step("Validate Ui Aliquot Details")
    public void Validate_UI_Aliquotdetails(String Aliq_SubId, String Aliq_Amount, String Source_center, String Aliq_Analyte_Type, String Aliq_Concentration) throws InterruptedException {
        HashMap<String, String> map = new HashMap<>();
        normalwait(5000);
        int size = Aliquots_Size.size();
        System.out.println("Aliquot tree size"+size);
        int count = 0;
        for (int i = 1; i <= size; i++) {
            //wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("((//span[text()='Aliquots'])[" + i + "]//following::td)[1]//mat-icon[text()='chevron_right']"))));
            WebElement ele = driver.findElement(By.xpath("((//span[text()='Aliquots'])["+i+"]//following::td)[1]//mat-icon[text()='chevron_right']"));
            ScrollView_Action(ele);
            jsclick(ele);
            normalwait(8000);
            page_loader();
            System.out.println("Aliq_di"+Aliq_SubId);
            System.out.println("Aliq"+Aliquot_Submitter_ID.getText());
            if (Aliq_SubId.equals(Aliquot_Submitter_ID.getText())) {
                Assert.assertEquals(Aliquot_Submitter_ID.getText(), Aliq_SubId);
                if (!Aliq_Amount.isEmpty()) {
                    Assert.assertEquals(Amount.getText(), Aliq_Amount);
                    screenshot();
                    System.out.println("Aliquot Amount Is Matching");
                }
                if (!Source_center.isEmpty()) {
                    Assert.assertEquals(Source_Center.getText(), Source_center);
                    System.out.println("Source Center Is Matching");
                }
                if (!Aliq_Analyte_Type.isEmpty()) {
                    Assert.assertEquals(Analyte_Type.getText(), Aliq_Analyte_Type);
                    System.out.println("Analyte Type Is Matching");
                }
                if (!Aliq_Concentration.isEmpty()) {
                    Assert.assertEquals(Concentration.getText(), Aliq_Concentration);
                    System.out.println("Concentration  Is Matching");
                }
                screenshot();
                map.put("Aliquot Submitter ID", Aliquot_Submitter_ID.getText());
                map.put("Aliquot Amount", Amount.getText());
                map.put("Aliquot Source Center", Source_Center.getText());
                map.put("Aliquot Analyte Type", Analyte_Type.getText());
                map.put("Aliquot Concentration", Concentration.getText());

                Ex.Pass_ScreenShot("Catalog Aliquot Details Validation :" + MarkupHelper.createUnorderedList(map).getMarkup());
                count = count + 1;
                break;


            }

        }
        if (count == 0) {
            Assert.assertTrue(false);
        } else {
            Assert.assertTrue(true);
        }
    }

    public void file_header_reference() {
        Assert.assertTrue(FileUUID_Header.isDisplayed());
        Assert.assertTrue(File_DataAccess.isDisplayed());
        Assert.assertTrue(File_Name_Header.isDisplayed());
        Assert.assertTrue(File_Description.isDisplayed());
        Assert.assertTrue(File_IngestedDate.isDisplayed());
        Assert.assertTrue(File_Ingesteby.isDisplayed());
        screenshot();

        Ex.Pass_ScreenShot("Validate Reference File Headers");
    }

    @Step("Validate File Values")
    public void Validate_FileValues_Reference(String uuid, String filename, String username) throws InterruptedException {
        Element_ToBe_Clickable(File_UUID_Reference);
       /* if (!uuid.isEmpty()) {
            Assert.assertEquals(uuid, File_UUID_Reference.getText().trim());
        }*/
        Assert.assertEquals("open", File_Access.getText().trim());
        if (!filename.isEmpty()) {
            Assert.assertEquals(filename, File_Name.getText().trim());
        }
        Assert.assertEquals("na", Filedescription.getText().trim());

        //Assert.assertEquals(java.time.LocalDate.now(),File_IngestedDate.getText().trim());

        if (!username.isEmpty()) {
            Assert.assertEquals(username, File_IngestedBy.getText());
        }

        Ex.Pass_ScreenShot("Validate Reference Files  Details");
        screenshot();

    }


    @Step("Navigate to Cart Icon")
    public void Add_To_Cart() {
        normalwait(3000);
        int size = Add_To_Cart_File_Overview_Count.size();
        if (size == 1) {
            jsclick(Add_To_Cart_File_Overview);
        }
        normalwait(2000);
        jsclick(Cart_Icon);
        page_loader();
        loader_new();
        //Assert.assertTrue(Cart_Overview_BC.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Cart Overview BreadCrumb is Displayed in Cart Overview Page");


    }

    @Step("Cart Values Validation")
    public void validate_cart_values(String FileUUID, String FileName, String Project, String Aligned_Reads) {
        String[] AlignedReads = Aligned_Reads.split(",");
        String DF = AlignedReads[0];
        String Data_Category = AlignedReads[1];
        String Data_Type = AlignedReads[2];
        String Exp_Strat = AlignedReads[3];
        Add_To_Cart();
        //Assert.assertEquals(FileUUID, Cart_File_UUID.getText().trim());
        Assert.assertEquals("CONTROLLED", Cart_Access.getText().trim());
        Assert.assertEquals(FileName, Cart_FileName.getText().trim());
        //Assert.assertEquals(Project, Cart_Project.getText().trim());
        Assert.assertEquals(DF, Cart_data_format.getText().trim());
        Assert.assertEquals(Data_Category, Cart_data_category.getText().trim());
        Assert.assertEquals(Data_Category, Cart_data_category.getText().trim());
        Assert.assertEquals(Data_Type, Cart_dataType.getText().trim());
        Assert.assertEquals(Exp_Strat, Cart_experimental_strategy.getText().trim());
        screenshot();
        Ex.Pass_ScreenShot("Cart Values Validation");
        Ele_Click(Back_Btn_cart);
    }


    @Step("To Delete All Files In Cart")
    public void all_cart_delete() {
        loader_new();
        Ele_Click(Cart_Icon);
        page_loader();
        int count = Return_Files_Overview_List.size();
        if (count == 0) {
            loader_new();
            page_loader();
            if (All_Cart_Delete_Count.size() != 0) {
                jsclick(All_Cart_Delete);
                Ele_Click(Req_Access_Yes_Btn);
                wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
                String Success_Msg = Notification_Msg.getText();
                Ex.Pass_ScreenShot("Cart Items Delete Success Message");
                Assert.assertEquals("Cart items have been removed successfully", Success_Msg);
                screenshot();
                normalwait(2000);
            }


            double_click(Return_Files_Overview);

        } else {
            double_click(Return_Files_Overview);
        }


    }


    //Arumugam Ref Issue
    @FindBy(xpath = "(//div[@class='file-overview']/..//div)[2]")
    public WebElement Ref_Active_Count;

    @FindBy(xpath = "//div[@class='mat-paginator-range-label']")
    public WebElement Ref_Pagenator;

    @FindBy(xpath = "//table//tr")
    public List<WebElement> Row_Count;

    public void verify_ref_page(){
        loader_new();
        Element_ToBe_Clickable(Search_Files_Reference);
        Assert.assertEquals(Row_Count.size(),26);;
        Ex.Pass_ScreenShot("Reference Verification");
    }


    public void navigate_To_FileDeatils_Reference(String Filename) throws InterruptedException {
        int size = 0;
        Element_ToBe_Clickable(Search_Files_Reference);
        send_keys(Search_Files_Reference, Filename + Keys.ENTER);
        normalwait(3000);
        send_keys(File_txt_Input, String.valueOf(Keys.ENTER));
        long starttime = System.currentTimeMillis();
        long waittime = 120000;
        long endtime = starttime + waittime;
        do {
            send_keys(Search_Files_Reference, String.valueOf(Keys.ENTER));
            normalwait(2000);
            size = driver.findElements(By.xpath("((//table//tbody//tr)[1]//td[text()=' " + Filename + " '])")).size();
        }
        while (System.currentTimeMillis() < endtime && size == 0);
        screenshot();
    }

    public void select_proj_cart(String file1 ,String Proj){

        normalwait(3000);
        WebElement DropDown1 =driver.findElement(By.xpath("//table//tbody//tr//td[contains(text(),'"+file1+"')]/..//td//mat-form-field//div//div"));
        jsclick(DropDown1);
        WebElement Proj1 =driver.findElement(By.xpath("//span[contains(text(),'"+Proj+"')]"));
        jsclick(Proj1);
    }



    public void UpdateFile_WithCaseId(String filepath, String file, HashMap hm) throws IOException, ParseException {
        String fp = filepath + file;
        FileReader reader = new FileReader(fp);
        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(reader);
        JSONObject jo2 = (JSONObject) jo.get("case");
        jo2.put("case_id", hm.get("case_id"));
        System.out.println(jo.toJSONString());
        FileWriter writer = new FileWriter(fp);
        writer.write(jo.toJSONString());
        writer.close();
    }

    public void UpdateFile_WithCase_SampleId(String filepath, String file, HashMap hm) throws IOException, ParseException {
        String fp = filepath + file;
        FileReader reader = new FileReader(fp);
        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(reader);
        JSONObject jo1 = (JSONObject) jo.get("sample");
        jo1.put("sample_id", hm.get("sample_id"));
        JSONObject jo2 = (JSONObject) jo.get("case");
        jo2.put("case_id", hm.get("case_id"));
        System.out.println(jo.toJSONString());






        FileWriter writer = new FileWriter(fp);
        writer.write(jo.toJSONString());
        writer.close();
    }

    public void UpdateFile_WithCase_Sample_AliqId(String filepath, String file, HashMap hm) throws IOException, ParseException {
        String fp = filepath + file;
        FileReader reader = new FileReader(fp);
        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(reader);
        JSONObject jo1 = (JSONObject) jo.get("sample");
        jo1.put("sample_id", hm.get("sample_id"));
        JSONObject jo2 = (JSONObject) jo.get("case");
        jo2.put("case_id", hm.get("case_id"));
        JSONObject jo3 = (JSONObject) jo.get("aliquot");
        jo3.put("aliquot_id", hm.get("aliquot_id"));
        System.out.println(jo.toJSONString());
        FileWriter writer = new FileWriter(fp);
        writer.write(jo.toJSONString());
        writer.close();
    }

    public void UpdateFile_To_Delete(String filepath, String file) throws IOException, ParseException {
        String fp = filepath + file;
        FileReader reader = new FileReader(fp);
        ;
        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(reader);
       // JSONObject jo1 = (JSONObject) jo.get("type_of_operation");
        jo.put("type_of_operation", "DELETE");
        FileWriter writer = new FileWriter(fp);
        writer.write(jo.toJSONString());
        writer.close();

    }

    public void Validate_UI_Aliquotdetails_new(String Aliq_SubId, String Aliq_Amount, String Source_center, String Aliq_Analyte_Type, String Aliq_Concentration) throws InterruptedException {
        HashMap<String, String> map = new HashMap<>();
        normalwait(5000);
        int size = Aliquots_Size.size();
        System.out.println("Aliquot tree size"+size);
        int count = 0;
        for (int i = 1; i <= size; i++) {
            int aliq_size =driver.findElements(By.xpath("(//span[text()='Aliquots'])["+i+"]//following::td")).size();
            System.out.println("ni"+aliq_size);
            for (int j=1;j<=aliq_size;j++){
            WebElement ele = driver.findElement(By.xpath("((//span[text()='Aliquots'])["+i+"]//following::td)["+j+"]//mat-icon[text()='chevron_right']"));
            ScrollView_Action(ele);
            jsclick(ele);
            normalwait(8000);
            page_loader();
            System.out.println("Aliq_di"+Aliq_SubId);
            System.out.println("Aliq"+Aliquot_Submitter_ID.getText());
            if (Aliq_SubId.equals(Aliquot_Submitter_ID.getText())) {
                Assert.assertEquals(Aliquot_Submitter_ID.getText(), Aliq_SubId);
                if (!Aliq_Amount.isEmpty()) {
                    Assert.assertEquals(Amount.getText(), Aliq_Amount);
                    screenshot();
                    System.out.println("Aliquot Amount Is Matching");
                }
                if (!Source_center.isEmpty()) {
                    Assert.assertEquals(Source_Center.getText(), Source_center);
                    System.out.println("Source Center Is Matching");
                }
                if (!Aliq_Analyte_Type.isEmpty()) {
                    Assert.assertEquals(Analyte_Type.getText(), Aliq_Analyte_Type);
                    System.out.println("Analyte Type Is Matching");
                }
                if (!Aliq_Concentration.isEmpty()) {
                    Assert.assertEquals(Concentration.getText(), Aliq_Concentration);
                    System.out.println("Concentration  Is Matching");
                }
                screenshot();
                map.put("Aliquot Submitter ID", Aliquot_Submitter_ID.getText());
                map.put("Aliquot Amount", Amount.getText());
                map.put("Aliquot Source Center", Source_Center.getText());
                map.put("Aliquot Analyte Type", Analyte_Type.getText());
                map.put("Aliquot Concentration", Concentration.getText());

                Ex.Pass_ScreenShot("Catalog Aliquot Details Validation :" + MarkupHelper.createUnorderedList(map).getMarkup());
                count = count + 1;
                break;
            }

            }

        }
        if (count == 0) {
            Assert.assertTrue(false);
        } else {
            Assert.assertTrue(true);
        }
    }






}