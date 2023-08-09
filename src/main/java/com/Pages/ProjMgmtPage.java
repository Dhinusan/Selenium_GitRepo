package com.redcapPage;

import Resources.Common_Functions;
import Resources.Constant;
import Resources.ExtentManager;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.qameta.allure.Step;
import org.apache.logging.log4j.core.util.ArrayUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;


import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;


public class ProjMgmtPage extends Common_Functions {
    WebDriverWait wait = new WebDriverWait(driver, 20);
    ExtentManager Ex = new ExtentManager();
    SoftAssert s_assert = new SoftAssert();

    public ProjMgmtPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //Project_Management_Page

    @FindBy(xpath = "//*[text()='No data found']")
    public WebElement No_Data_Found;


    @FindBy(xpath = "//mat-table[1]/mat-row")
    public List<WebElement> Proj_Overview_Row;

    @FindBy(xpath = "//a[text()='Project Access']")
    public WebElement Project_Access;

    @FindBy(xpath = "//div[contains(text(),'Active Users')]")
    public WebElement Active_Users;

    @FindBy(xpath = "//mat-dialog-container/app-projectaccess/div/mat-table/mat-row")
    public List<WebElement> Active_Users_Row;

    @FindBy(xpath = "//span[normalize-space()='Associate users']")
    public WebElement Associate_Users;

    @FindBy(xpath = "//span[contains(@class,'mat-select')]")
    public WebElement Associate_Users_DrpDwn;

    @FindBy(xpath = "//span[normalize-space()='Select']")
    public WebElement AssocUsers_SelBtn;

    @FindBy(xpath = "//span[contains(@class,'mat-select-value')]//span")
    public WebElement AssocUsers_Name;

    @FindBy(xpath = "//span[contains(@class,'mat-select-placeholder')]")
    public WebElement Role_DrpDwn;

    @FindBy(xpath = "//span[contains(text(),'Save')]")
    public WebElement Save_btn;

    @FindBy(xpath = "//simple-snack-bar[contains(@class,'mat-simple-snackbar')]//span")
    public WebElement Notification_Msg;

    @FindBy(xpath = "((//mat-dialog-container//mat-row)[last()]//mat-cell)[1]")
    public WebElement New_Added_User;


    @FindBy(xpath = "//div[@class='create-user']//span[@class='mat-button-wrapper'][normalize-space()='Delete']")
    public WebElement Delete_Btn;

    @FindBy(xpath = "//span[normalize-space()='Yes']")
    public WebElement Yes_Btn;

    @FindBy(xpath = "//span[normalize-space()='No']")
    public WebElement No_Btn;


    @FindBy(xpath = "//h1[contains(text(),'Confirm Action')]/../div/p")
    public WebElement Cnfrm_Action;

    @FindBy(xpath = "//a[contains(text(),'Technical Settings')]")
    public WebElement Technical_Settings;

    @FindBy(xpath = "//a[contains(text(),'Technical Settings')]")
    public List<WebElement> Technical_Settings_Count;

    @FindBy(xpath = "//a[contains(text(),'Data Ingestion Management')]")
    public WebElement Data_Ingestion_Mgmt;

    @FindBy(xpath = "//a[contains(text(),'Data Ingestion Management')]")
    public List<WebElement> Data_Ingestion_Mgmt_Count;

    @FindBy(xpath = "//a[contains(text(),'Infra Settings')]")
    public WebElement Infra_Settings;

    @FindBy(xpath = "//button[text()='Create Manifest']")
    public WebElement Create_Manifest;

    @FindBy(xpath = "//button[text()='Create Metadata']")
    public WebElement Create_Metadata;

    @FindBy(xpath = "//div[contains(text(),'Raw')]")
    public WebElement Raw_RadioBtn;

    @FindBy(xpath = "//div[contains(text(),'Yes')]")
    public WebElement Yes_RadioBtn;

    @FindBy(xpath = "//div[contains(text(),'No')]")
    public WebElement No_RadioBtn;

    @FindBy(xpath = "//span[text()='Proceed']")
    public WebElement Proceed_Btn;

    @FindBy(xpath = "//mat-label[text()=' File Name/URL ']/../../..//input")
    public WebElement File_Url_Input;

    @FindBy(xpath = "//h3[text()='File']/../../../..//div[@class='err-msg ng-star-inserted']")
    public WebElement File_Error_Msg;

    @FindBy(xpath = "(//h3[text()='Read group']/..//div[@class='err-msg ng-star-inserted'])[1]")
    public WebElement Read_Group_Error_Msg;

    @FindBy(xpath = "//div[text()=' File Name/URL is required ']/../input")
    public WebElement File_txt;

    @FindBy(xpath = "(//mat-label[contains(text(),'File id')]/../../../input)[1]")
    public WebElement File_id;

    @FindBy(xpath = "(//mat-label[contains(text(),'File size')]/../../../input)[1]")
    public WebElement File_size;

    @FindBy(xpath = "//span[text()=' Case ']")
    public WebElement MC_Case;

    @FindBy(xpath = "//a[contains(text(),'Case')]")
    public WebElement MC_Case1;

    @FindBy(xpath = "//a[contains(text(),'Sample')]")
    public WebElement MC_Sample;

    @FindBy(xpath = "//a[text()=' Project']")
    public WebElement MC_Project;

    @FindBy(xpath = "//a[text()=' Read group']")
    public WebElement MC_ReadGroup;

    @FindBy(xpath = "//a[text()=' Clinical supplement']")
    public WebElement MC_ClinicalSupplement;

    @FindBy(xpath = "//a[text()=' Molecular test']")
    public WebElement MC_Moleculartest;

    @FindBy(xpath = "//a[contains(text(),'Aliquot')]")
    public WebElement MC_Aliquot;

    @FindBy(xpath = "//a[text()=' Slide']")
    public WebElement MC_Slide;

    @FindBy(xpath = "//a[text()=' File']")
    public WebElement MC_File;

    @FindBy(xpath = "//mat-label[contains(text(),' Case submitter id ')]/../../../input")
    public WebElement Case_Submitter_ID;

    @FindBy(xpath = "//h3[text()='Case']/..//following-sibling::mat-label[text()=' Primary site ']//../../..//mat-select")
    public WebElement Case_PrimsiteDD;

    @FindBy(xpath = "//h3[text()='Case']/..//following-sibling::mat-label[text()=' Disease type ']//../../..//mat-select")
    public WebElement Case_DiseaseTypeDD;

    @FindBy(xpath = "//h3[text()='Case']/..//following-sibling::mat-label[text()=' Consent type ']//../../..//mat-select")
    public WebElement Case_ConsentTypeDD;

    @FindBy(xpath = "//mat-label[contains(text(),'Sample submitter id ')]/../../../input")
    public WebElement Sample_Submitter_ID;

    @FindBy(xpath = "//mat-label[text()=' Platform ']/../..")
    public WebElement PlatFormDrpDwn;

    @FindBy(xpath = "//mat-label[contains(text(),'Project id')]/../../../input")
    public WebElement Projectid_Input;

    @FindBy(xpath = "//mat-label[contains(text(),'Primary site')]/../../../input")
    public WebElement Primarysite_Input;

    @FindBy(xpath = "//mat-label[contains(text(),'Current data users')]/../../../input")
    public WebElement Curr_Datausers;

    @FindBy(xpath = "//mat-label[contains(text(),'Data access commitee')]/../../../input")
    public WebElement Data_Acc_Commite;

    @FindBy(xpath = "//h3[text()='Project']/..//mat-label[contains(text(),'Name')]/../../../input")
    public WebElement Project_Name;

    @FindBy(xpath = "(//mat-label[contains(text(),'Description')]/../../../input)[2]")
    public WebElement Project_Description;

    @FindBy(xpath = "//h3[text()='Project']/..//following-sibling::mat-label[text()=' Data classification ']/../../..//mat-select")
    public WebElement Data_ClassificationDD;

    @FindBy(xpath = "//mat-label[contains(text(),'Data officer name')]/../../../input")
    public WebElement Data_offName;

    @FindBy(xpath = "//mat-label[text()=' Read group name ']/../../../input")
    public WebElement Read_group_name;

    @FindBy(xpath = "//label[contains(text(),'Is paired end')]/..//div[contains(text(),'Yes')]")
    public WebElement Read_group_RadioBtn_Yes;

    @FindBy(xpath = "//mat-label[text()=' Library strategy ']/../..")
    public WebElement Library_strategyDrpDwn;

    @FindBy(xpath = "//mat-label[contains(text(),'Aliquot submitter id ')]/../../../input")
    public WebElement Aliquot_Submitter_ID;

    @FindBy(xpath = "//mat-label[contains(text(),'Aliquot volume')]/../../../input")
    public WebElement Aliquot_Volume;

    @FindBy(xpath = "//mat-label[contains(text(),'Percent lymphocyte')]/../../../input")
    public WebElement Slide_Percent_Lymph;

    @FindBy(xpath = "//a[text()=' Aligned reads']")
    public WebElement MC_Aligned_reads;

    @FindBy(xpath = "//a[text()=' Aligned reads']")
    public List<WebElement> MC_Aligned_reads_Count;


    @FindBy(xpath = "//a[text()=' Slide image']")
    public WebElement MC_Slide_Image;

    @FindBy(xpath = "//a[text()=' Aligned reads index']")
    public WebElement MC_Aligned_reads_index;

    @FindBy(xpath = "//h3[text()='Aligned reads']/..//following-sibling::mat-label[text()=' Data format ']/../..")
    public WebElement AR_DataFormatDrpDwn;

    @FindBy(xpath = "//h3[text()='Aligned reads']/..//following-sibling::mat-label[text()=' Data category ']/../..")
    public WebElement AR_DataCategoryDrpDwn;

    @FindBy(xpath = "//h3[text()='Aligned reads']/..//following-sibling::mat-label[text()=' Data type ']/../..")
    public WebElement AR_DataTypeDrpDwn;

    @FindBy(xpath = "//h3[text()='Aligned reads']/..//following-sibling::mat-label[text()=' Experimental strategy ']/../..")
    public WebElement AR_ExStrategyDrpDwn;

    @FindBy(xpath = "//h3[text()='Aligned reads index']/..//following-sibling::mat-label[text()=' Data format ']/../..")
    public WebElement ARI_DataFormatDrpDwn;

    @FindBy(xpath = "//h3[text()='Aligned reads index']/..//following-sibling::mat-label[text()=' Data category ']/../..")
    public WebElement ARI_DataCategoryDrpDwn;

    @FindBy(xpath = "//h3[text()='Aligned reads index']/..//following-sibling::mat-label[text()=' Data type ']/../..")
    public WebElement ARI_DataTypeDrpDwn;

    @FindBy(xpath = "//mat-table[1]/mat-row[1]/mat-cell[2]/span")
    public WebElement FileName_Verify;

    @FindBy(xpath = "//mat-table[1]/mat-row[1]/mat-cell[1]")
    public WebElement Manifest_id;

    @FindBy(xpath = "//a[text()='Edit']")
    public WebElement Edit_Btn;

    @FindBy(xpath = "//a[text()='Download']")
    public WebElement Download_Btn;

    @FindBy(xpath = "//span[contains(text(),'Download')]")
    public WebElement Download_Btn_Popup;

    @FindBy(xpath = "//span[text()='Cancel']")
    public WebElement Cancel_btn;

    @FindBy(xpath = "//mat-table[1]/mat-row[1]/mat-cell[6]//li[1]/mat-icon[1]")
    public WebElement Frst_Row_Icon;

    @FindBy(xpath = "//h3[text()='Aligned reads']/..//following-sibling::button//span[text()='Clear fields']")
    public WebElement AR_Clearfields;

    @FindBy(xpath = "//h3[text()='Slide image']/..//following-sibling::button//span[text()='Clear fields']")
    public WebElement SI_Clearfields;

    @FindBy(xpath = "//h3[text()='Immune profiling workflow']/..//following-sibling::button//span[text()='Clear fields']")
    public WebElement IPWorkFlow_Clearfields;

    @FindBy(xpath = "//mat-icon[contains(text(),'apps')]")
    public WebElement Apps_Icon;

    @FindBy(xpath = "//a[text()='Project']")
    public WebElement Apps_Icon_Project;

    @FindBy(xpath = "//a[text()='Catalog']")
    public WebElement Apps_Icon_Catalog;

    @FindBy(xpath = "(//h1[text()='Associate Users']/..//span)[2]")
    public WebElement MultipleUsers;

    @FindBy(xpath = "//span[text()='Submit for ingestion']")
    public WebElement Submit_Fr_IngesBtn;

    @FindBy(xpath = "//span[text()='Submit for ingestion']/..")
    public WebElement Submit_Fr_IngesBtnP_status;

    @FindBy(xpath = "//span[text()='Add sample/aliquot']/..")
    public WebElement Add_sample_aliquotBtn;

    @FindBy(xpath = "//span[text()='Add File']/..")
    public WebElement Add_FileBtn;

    @FindBy(xpath = "//span[text()='Add Aliquot']/..")
    public WebElement Add_aliquotBtn;

    @FindBy(xpath = "//h3[text()='Sample']/..//div[@class='err-msg ng-star-inserted']")
    public WebElement Sample_Error_Msg;

    @FindBy(xpath = "//h3[text()='Case']/..//div[@class='err-msg ng-star-inserted']")
    public WebElement Case_Error_Msg;

    @FindBy(xpath = "//h3[text()='Aliquot']/..//div[@class='err-msg ng-star-inserted']")
    public WebElement Aliquot_Error_Msg;

    @FindBy(xpath = "(//h3[text()='Aligned reads']/..//div[@class='err-msg ng-star-inserted'])[1]")
    public WebElement Aligned_Read_Error_Msg;
    @FindBy(xpath = " //mat-progress-spinner[@role='progressbar']")
    public List<WebElement> Loader_Circle;
    //div[@class='loading-user-spinner ng-star-inserted']

    @FindBy(xpath = "(//mat-row//mat-cell)[2]//span")
    public WebElement ManifestTable_Data;

    @FindBy(xpath = "//a[contains(text(),'Immune profile')]")
    public WebElement MC_ImmuneProfile;

    @FindBy(xpath = "//div//p[text()='Do you want to cancel manifest creation?']")
    public WebElement Cancel_No_Msg;

    @FindBy(xpath = "//a[contains(text(),'Immune profiling workflow')]")
    public WebElement MC_ImmuneProfile_Workflow;

    @FindBy(xpath = "//a[contains(text(),'Alignment workflow')]")
    public WebElement MC_Alignment_Workflow;

    @FindBy(xpath = "//a[contains(text(),'Immune profiling workflow')]")
    public List<WebElement> MC_IP_Workflow_Count;


    @FindBy(xpath = "//h3[text()='Immune profile']/..//following-sibling::mat-label[text()=' Data format ']/../..")
    public WebElement IF_dataformatDrpDwn;

    @FindBy(xpath = "//h3[text()='Immune profile']/..//following-sibling::mat-label[text()=' Data type ']/../..")
    public WebElement IF_datatypeDrpDwn;

    @FindBy(xpath = "//h3[text()='Immune profile']/..//following-sibling::mat-label[text()=' Data category ']/../..")
    public WebElement IF_datacategoryrpDwn;

    @FindBy(xpath = "//h3[text()='Immune profiling workflow']/..//following-sibling::mat-label[text()=' Workflow type ']/../..")
    public WebElement IF_workflowDD;

    @FindBy(xpath = "//h3[text()='Alignment workflow']/..//following-sibling::mat-label[text()=' Workflow type ']/../..")
    public WebElement Alignment_workflowDD;

    @FindBy(xpath = "//label[text()='Total Associated Files']/..//a")
    public List<WebElement> File_Count_size;

    @FindBy(xpath = "//label[text()='Total Associated Files']/..//a")
    public WebElement File_Count;

    @FindBy(xpath = "//label[text()='Total Associated Files']/..//span")
    public WebElement File_Count_No_Link;

    @FindBy(xpath = "//span[text()='Manifest ']")
    public WebElement Manifest_DrpDwn;

    @FindBy(xpath = "//h3[text()='Slide image']/..//following-sibling::mat-label[text()=' Data format ']/../..")
    public WebElement SI_DataFormat;

    @FindBy(xpath = "//h3[text()='Slide image']/..//following-sibling::mat-label[text()=' Data category ']/../..")
    public WebElement SI_DataCategory;

    @FindBy(xpath = "//h3[text()='Slide image']/..//following-sibling::mat-label[text()=' Data type ']/../..")
    public WebElement SI_DataType;

    @FindBy(xpath = "//h3[text()='Slide image']/..//following-sibling::mat-label[text()=' Experimental strategy ']/../..")
    public WebElement SI_ExpStrategy;

    @FindBy(xpath = "//mat-header-cell[contains(text(),'Manifest ID')]")
    public WebElement Manifest_ID;

    @FindBy(xpath = "//mat-header-cell[contains(text(),'File Name/URL')]")
    public WebElement File_Url;

    @FindBy(xpath = "//mat-header-cell[contains(text(),'Status')]")
    public WebElement MO_Status;

    @FindBy(xpath = "//mat-header-cell[contains(text(),'Created By')]")
    public WebElement MO_Created_By;

    @FindBy(xpath = "//mat-header-cell[contains(text(),'Date created/modified')]")
    public WebElement MO_Date_created_modified;

    @FindBy(xpath = "((//mat-table//mat-row)[1]//mat-cell)[2]")
    public WebElement MO_FileName;

    @FindBy(xpath = "//a[text()='Edit']")
    public WebElement MO_Editbtn;

    @FindBy(xpath = "(//div[contains(@class,'overlay-backdrop-showing')])[2]")
    public WebElement Back_Drop;

    //Robin Xpath
    @FindBy(xpath = "//input[@placeholder=\"Search here\"]")
    public WebElement Search_box;

    @FindBy(xpath = "//h3[text()='Case']/..//following-sibling::mat-label[text()=' Primary site ']/../..")
    public WebElement CASE_Primary_site;

    @FindBy(xpath = "//h3[text()='Case']/..//following-sibling::mat-label[text()=' Disease type ']/../..")
    public WebElement CASE_Disease_type;

    @FindBy(xpath = "//h3[text()='Case']/..//following-sibling::mat-label[text()=' Consent type ']/../..")
    public WebElement CASE_Consent_type;


    @FindBy(xpath = "//h5[contains(text(),'Add another file')]/..//button")
    public WebElement Add_another_file_button;

    @FindBy(xpath = "//h5[contains(text(),'Add another file')]/..//button")
    public List<WebElement> Add_file_button_after4files;

    @FindBy(xpath = "//h3[text()='Immune profiling workflow']/..//mat-select-trigger")
    public WebElement IP_WorkFlow_Value;

    @FindBy(xpath = "//h3[text()='Alignment workflow']/..//mat-select-trigger")
    public WebElement Alignment_WorkFlow_Value;

    @FindBy(xpath = "//h3[text()='Immune profile']/..//following-sibling::mat-label[text()=' Data format ']/../../..//mat-select-trigger")
    public WebElement IP_DataFromat_Value;

    @FindBy(xpath = "//h3[text()='Immune profile']/..//following-sibling::mat-label[text()=' Data type ']/../../..//mat-select-trigger")
    public WebElement IP_DataType_Value;

    @FindBy(xpath = "//h3[text()='Slide image']/..//following-sibling::mat-label[text()=' Data format ']/../../..//mat-select-trigger")
    public WebElement SI_DataFromat_Value;

    @FindBy(xpath = "//h3[text()='Slide image']/..//following-sibling::mat-label[text()=' Data category ']/../../..//mat-select-trigger")
    public WebElement SI_DataCategory_Value;

    @FindBy(xpath = "//h3[text()='Slide image']/..//following-sibling::mat-label[text()=' Data type ']/../../..//mat-select-trigger")
    public WebElement SI_DataType_Value;

    @FindBy(xpath = "//h3[text()='Slide image']/..//following-sibling::mat-label[text()=' Experimental strategy ']/../../..//mat-select-trigger")
    public WebElement SI_ExpStrat_Value;

    @FindBy(xpath = "//h3[text()='Aligned reads']/..//following-sibling::mat-label[text()=' Data category ']/../../..//mat-select-trigger")
    public WebElement AR_DataCategory_Value;

    @FindBy(xpath = "//h3[text()='Aligned reads']/..//following-sibling::mat-label[text()=' Data format ']/../../..//mat-select-trigger")
    public WebElement AR_DataFromat_Value;

    @FindBy(xpath = "//h3[text()='Aligned reads']/..//following-sibling::mat-label[text()=' Data type ']/../../..//mat-select-trigger")
    public WebElement AR_DataType_Value;

    @FindBy(xpath = "//h3[text()='Immune profile']/..//following-sibling::mat-label[text()=' Data category ']/../../..//mat-select-trigger")
    public WebElement IP_DataCategory_Value;

    @FindBy(xpath = "//h3[text()='Aligned reads']/..//following-sibling::mat-label[text()=' Experimental strategy ']/../../..//mat-select-trigger")
    public WebElement AR_ExpStrat_Value;

    @FindBy(xpath = "//h3[text()='Read group']/..//following-sibling::mat-label[text()=' Platform ']/../../..//mat-select-trigger")
    public WebElement RG_Platform_Value;


    @FindBy(xpath = "//h3[text()='Read group']/..//following-sibling::mat-label[text()=' Library strategy ']/../../..//mat-select-trigger")
    public WebElement RG_Library_strategy_Value;

    @FindBy(xpath = "//span[text()='Add more Metadata']")
    public WebElement Add_More_MetaData;

    @FindBy(xpath = "(//span[contains(text(),'Biospecimen')])[2]")
    public WebElement SI_Biospecimen;

    @FindBy(xpath = "//label[text()='Case ID']/..//span")
    public WebElement Case_ID;

    @FindBy(xpath = "//a[contains(text(),'Slide image')]")
    public WebElement MC_SlideImage;

    @FindBy(xpath = "//h3[text()='Slide image']/..//following-sibling::mat-label[text()=' Data format ']/../..")
    public WebElement SI_dataformatDrpDwn;

    @FindBy(xpath = "//h3[text()='Slide image']/..//following-sibling::mat-label[text()=' Species ']/../..")
    public WebElement SI_speciesDrpDwn;

    @FindBy(xpath = "//mat-label[text()=' Slide id ']/../../../input")
    public WebElement SI_SlideId;

    @FindBy(xpath = "//mat-label[contains(text(),'Block id')]/../../../input")
    public WebElement SI_BlockId;

    @FindBy(xpath = "//mat-label[contains(text(),'Timepoint code')]/../../../input")
    public WebElement SI_Timepointcode;

    @FindBy(xpath = "//mat-label[contains(text(),'Protocol number')]/../../../input")
    public WebElement SI_ProtocolNumber;

    @FindBy(xpath = "//mat-label[contains(text(),'Protocol name')]/../../../input")
    public WebElement SI_ProtocolName;

    @FindBy(xpath = "//mat-label[contains(text(),'Study id')]/../../../input")
    public WebElement SI_StudyId;

    @FindBy(xpath = "//mat-label[contains(text(),'Collection type')]/../../../input")
    public WebElement SI_Collectiontype;

    @FindBy(xpath = "//mat-label[contains(text(),'Notes 1')]/../../../input")
    public WebElement SI_Notes1;

    @FindBy(xpath = "//mat-label[contains(text(),'Notes 2')]/../../../input")
    public WebElement SI_Notes2;

    @FindBy(xpath = "//mat-label[contains(text(),'Notes 3')]/../../../input")
    public WebElement SI_Notes3;

    @FindBy(xpath = "//h3[text()='Slide image']")
    public WebElement SI_header;

    @FindBy(xpath = "//h3[text()='Molecular test']")
    public WebElement MT_header;

    @FindBy(xpath = "//h3[text()='Immune profile']")
    public WebElement IP_header;

    @FindBy(xpath = "//h3[text()='Aligned reads']")
    public WebElement AR_header;

    @FindBy(xpath = "//h3[text()='Immune profiling workflow']")
    public WebElement IPWF_header;


    @FindBy(xpath = "//h3[text()='Slide image']/..//following-sibling::mat-label[text()=' Marker ']/../..")
    public WebElement SI_MarkerDrpDwn;

    @FindBy(xpath = "//h3[text()='Slide image']/..//following-sibling::mat-label[text()=' Data type ']/../..")
    public WebElement SI_datatypeDrpDwn;

    @FindBy(xpath = "//h3[text()='Slide image']/..//following-sibling::mat-label[text()=' Data category ']/../..")
    public WebElement SI_datacategoryDrpDwn;

    @FindBy(xpath = "//h3[text()='Slide image']/..//following-sibling::mat-label[text()=' Experimental strategy ']/../..")
    public WebElement SI_ExpStrategyDrpDwn;

    @FindBy(xpath = "//h3[text()='Slide image']/..//following-sibling::mat-label[text()=' Tumor type ']/../..")
    public WebElement SI_TumortypeDrpDwn;

    @FindBy(xpath = "//h3[text()='Slide image']/..//following-sibling::mat-label[text()=' Tissue type ']/../..")
    public WebElement SI_TissuetypeDrpDwn;

    @FindBy(xpath = "//h3[text()='Slide image']/..//following-sibling::mat-label[text()=' Sample type ']/../..")
    public WebElement SI_SampletypeDrpDwn;

    @FindBy(xpath = "//h3[text()='Read group']/..//following-sibling::mat-label[text()=' Target capture kit ']/../..")
    public WebElement RG_TargetDrpDwn;

    @FindBy(xpath = "//h3[text()='Clinical supplement']/..//following-sibling::mat-label[text()=' Data format ']/../..")
    public WebElement CS_DataformatDrpDwn;

    @FindBy(xpath = "//h3[text()='Molecular test']/..//following-sibling::mat-label[text()=' Data category ']/../..")
    public WebElement MT_DatacategoryDrpDwn;

    @FindBy(xpath = "//span[text()='Create Project']")
    public WebElement Create_Project;

    @FindBy(xpath = "//span[text()='Create Project']")
    public List<WebElement> Create_Project_Count;

    @FindBy(xpath = "//input[@placeholder='Project name']")
    public WebElement Project_Name_Input;

    @FindBy(xpath = "//input[@formcontrolname='projectname']/../../../../..//li")
    public WebElement Invalid_ProjName_Msg;

    @FindBy(xpath = "//button[contains(text(),'Name')]")
    public WebElement PO_Name_Header;

    @FindBy(xpath = "//button[contains(text(),'Project ID')]")
    public WebElement PO_ProjID_Header;

    @FindBy(xpath = "//button[contains(text(),'Status')]")
    public WebElement PO_Status_Header;

    @FindBy(xpath = "//button[contains(text(),'Region')]")
    public WebElement PO_Region_Header;

    @FindBy(xpath = "//button[contains(text(),'Additional Services')]")
    public WebElement PO_AddServices_Header;

    @FindBy(xpath = "//button[contains(text(),'Created on')]")
    public WebElement PO_CreatedOn_Header;

    @FindBy(xpath = "//mat-table//mat-row")
    public List<WebElement> PO_RowCount;

    @FindBy(xpath = "//a[text()='Catalog case details']")
    public WebElement Catalog_Case_Details;

    @FindBy(xpath = "//input[@placeholder='WBS Code']")
    public WebElement WBSCode_Input;

    @FindBy(xpath = "//mat-select[@formcontrolname='region']")
    public WebElement Region_DrpDwn;

    @FindBy(xpath = "//mat-select[@formcontrolname='addtionalService']")
    public WebElement AddService_DrpDwn;

    @FindBy(xpath = "//span[text()='Create project']")
    public WebElement Create_Projectnew;

    @FindBy(xpath = "((//mat-table//mat-row)[1]//mat-cell)[5]")
    public WebElement Project_AditionalService_Value;

    @FindBy(xpath = "((//mat-table//mat-row)[1]//mat-cell)[1]")
    public WebElement Project_Name_Value;

    @FindBy(xpath = "((//mat-table//mat-row)[1]//mat-cell)[3]")
    public WebElement Project_Status_Value;

    @FindBy(xpath = "((//mat-table//mat-row)[1]//mat-cell)[4]")
    public WebElement Project_Name_Region;

    @FindBy(xpath = "//a[text()='Apps']/../..//li[text()='Project Management']")
    public WebElement Apps_ProjectMangmnt_BreadCrumb;

    @FindBy(xpath = "//mat-table//mat-row//td//mat-radio-button")
    public WebElement Pm_RadioBtn;

    @FindBy(xpath = "//span[text()='Delete']")
    public WebElement Pm_DeleteBtn;

    @FindBy(xpath = "//span[text()='Onboard Region']")
    public WebElement Onboard_Region;

    @FindBy(xpath = "//mat-label[text()=' HPC ']/../..//mat-slide-toggle")
    public WebElement HPC_Toggle_btn;

    @FindBy(xpath = "//button[text()='Upload Manifest (CSV)']")
    public WebElement Upload_Manifest_CSV;

    @FindBy(xpath = "//mat-icon[text()='more_vert']")
    public WebElement Manifest_Elipse;

    @FindBy(xpath = "//a[text()='Delete']")
    public WebElement Manifest_Delete;

    @FindBy(xpath = "//li//a[contains(text(),'Project Management')]")
    public WebElement PM_BreadCrumb;

    @FindBy(xpath = "//span[text()='Edit']")
    public WebElement PA_EditBtn;

    @FindBy(xpath = "//li//a[contains(text(),'Data Ingestion Management')]")
    public WebElement DIM_BreadCrumb;

    @FindBy(xpath = "//div[@class='cdk-overlay-backdrop cdk-overlay-transparent-backdrop cdk-overlay-backdrop-showing']")
    public WebElement Backdrop;

    @FindBy(xpath = "//div[text()='csv']")
    public WebElement Csv_RadioBtn;

    @FindBy(xpath = "(//label[text()='Total Associated Files']/..//a)[2]")
    public WebElement Aliquot_File_Count;

    @FindBy(xpath = "((//span[text()='Samples'])[1]//following::td)[1]//span[@class='marg-left']")
    public WebElement Catalog_Sample_ID;

    @FindBy(xpath = "//div[@dir='ltr']")
    public WebElement Project_Access_BG;

    @FindBy(xpath = "//input[@type='text']")
    public WebElement Upload_Input_Box;


    public String fetch_sample_catalog(){
        String Sample_Id=null;
        normalwait(4000);
        Sample_Id =Catalog_Sample_ID.getText();
        return Sample_Id;
    }

    public String fetch_caseid_catalog(){
        String Case_Id=null;
        normalwait(4000);
        Case_Id =Case_ID.getText();
        return Case_Id;
    }

    @Step("Verify UI File Count")
    public void verify_Aliquotfile_count(int Filecount) throws InterruptedException {
        normalwait(5000);
        WebElement Ele = driver.findElement(By.xpath("((//span[text()='Aliquots'])[1]//following::td)[1]//mat-icon[text()='chevron_right']"));
        Ele.click();
        normalwait(3000);
        String FileCount = Aliquot_File_Count.getText();
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
    public int get_aliquot_filecount(){
        WebElement Ele = driver.findElement(By.xpath("((//span[text()='Aliquots'])[1]//following::td)[1]//mat-icon[text()='chevron_right']"));
        Ele.click();
        normalwait(5000);
        String FileCount = Aliquot_File_Count.getText();
        int count = Integer.parseInt(FileCount);
        System.out.println(count);
        return count;
    }

    @Step("Validate Ingestion In  Manifest Overview Page")
    public void Verify_Ingestion_MO(String FileName,String ManifestId) throws InterruptedException {
        int count=0;
        loader();
        do {
            driver.navigate().refresh();
            normalwait(3000);
            count=driver.findElements(By.xpath("//mat-cell[text()='"+ManifestId+"']/..//span[text()='"+FileName+"']")).size();
        }
        while (count != 0);

       screenshot();
       Ex.Pass_ScreenShot("Csv File "+FileName+" Has Been Removed From Manifest Overview Page");

    }

    @Step("Verify Cancelled Update Meta Data File In Manifest Overview Page")
    public void verify_saved_manifest_forupdatemetadata(String filename){
       int count = driver.findElements(By.xpath("//span[text()='"+filename+"']")).size();
       Assert.assertEquals(count,0);
       screenshot();
       Ex.Pass_ScreenShot("Cancelled Update Meta Data File "+filename+" not displayed in MO as Expected");
    }


    /*public void verify_csv_Values_new(String filename) throws IOException {
        String filepath = DownloadPath + filename;
        String Line = "";

        BufferedReader br = new BufferedReader(new FileReader(filepath));
        while ((Line = br.readLine()) != null) {
            while ((Line = br.readLine()) != null) {
                List<String> lis = new ArrayList<>();
                String[] values = Line.split(",");
                Assert.assertEquals(values[0],);
            }
        }
    }*/



    public void Download_Csv(String File){
        jsclick(DIM_BreadCrumb);
        Ele_Click(Frst_Row_Icon);
        Ele_Click(Download_Btn);
        normalwait(1000);
        jsclick(Csv_RadioBtn);
        jsclick(Download_Btn_Popup);
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg, Constant.Download_Msg);
        Ex.Pass_ScreenShot("Manifest Download Success Message");
        screenshot();
        normalwait(5000);

    }
    public void verify_projectaccess_breadcrumb() {
        jsclick(Project_Access);
        normalwait(3000);
        Assert.assertTrue(Apps_ProjectMangmnt_BreadCrumb.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Project Management BreadCrumb Displayed in Project Access Page");
    }

    @Step(" BreadCrumb Displayed in Project Access Page")
    public void verify_Pm_Breadcrumbs_projectaccess(String Project, String UserName) {
        jsclick(Associate_Users);
        Assert.assertTrue(Apps_ProjectMangmnt_BreadCrumb.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Project Management BreadCrumb Displayed in Associate Users BackGround Page");
        jsclick(Cancel_btn);
        jsclick(PA_EditBtn);
        Assert.assertTrue(Apps_ProjectMangmnt_BreadCrumb.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Project Management BreadCrumb Displayed in PA Edit BackGround Page");
        jsclick(Cancel_btn);
        normalwait(3000);
        WebElement Ele = driver.findElement(By.xpath("//mat-cell[contains(text(),'" + UserName + "')]/..//div[@class='mat-radio-outer-circle']"));
        ScrollView(Ele);
        jsclick(Ele);
        Ele_Click(Delete_Btn);
        Assert.assertTrue(Apps_ProjectMangmnt_BreadCrumb.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Project Management BreadCrumb Displayed in PA Delete Users BackGround Page");
        jsclick(No_Btn);
        normalwait(2000);
        jsclick(Back_Drop);
        normalwait(2000);
        //navigate_back();

    }


    @Step("Manifest Creation BreadCrumb Displayed in Create MetaData Page")
    public void verify_Pm_Breadcrumbs_createmetadata(String Project) throws InterruptedException {
        Navigate_To_CreateMetaData();
        WebElement MC_BreadCrumb = driver.findElement(By.xpath("//a[text()='Apps']/../..//li//a[contains(text(),'Project Management (" + Project + ")')]/../..//li//a[text()='Data Ingestion Management']/../..//li[contains(text(),'Metadata Creation')]"));
        Assert.assertTrue(MC_BreadCrumb.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Manifest Creation BreadCrumb Displayed in Create MetaData Page");
        jsclick(PM_BreadCrumb);

    }

    @Step("Required Info BreadCrumb Displayed in Create Manifest Page")
    public void verify_Pm_Breadcrumbs_createmanifest(String Project) throws InterruptedException {
        jsclick(Manifest_DrpDwn);
        jsclick(Create_Manifest);
        WebElement BreadCrumb = driver.findElement(By.xpath("//a[text()='Apps']/../..//li//a[contains(text(),'Project Management (" + Project + ")')]/../..//li//a[text()='Data Ingestion Management']/../../li[text()='Required Information']"));
        Assert.assertTrue(BreadCrumb.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Required Info BreadCrumb Displayed in Create Manifest Page");
        Navigate_To_RawNonSequence();
        WebElement MC_BreadCrumb = driver.findElement(By.xpath("//a[text()='Apps']/../..//li//a[contains(text(),'Project Management (" + Project + ")')]/../..//li//a[text()='Data Ingestion Management']/../..//li//a[text()='Required Information']/../..//li[contains(text(),'Manifest Creation')]"));
        Assert.assertTrue(MC_BreadCrumb.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Manifest Creation BreadCrumb Displayed in Create Manifest Page");
        jsclick(DIM_BreadCrumb);
        loader();
    }

    @Step("Validation Of Infra Settings BreadCrumb Page")
    public void validate_Pm_Breadcrumbs_DataIngestion_Manage1(String UIProject) {
        jsclick(Data_Ingestion_Mgmt);
        validate_DataInges_Breadcrumbs(UIProject);
        screenshot();
        Ex.Pass_ScreenShot("Data Ingestion Manage Bred Crumb Displayed in Data Ingestion Page");
        page_loader();
        jsclick(Manifest_DrpDwn);
        page_loader();
        jsclick(Upload_Manifest_CSV);
        validate_DataInges_Breadcrumbs(UIProject);
        screenshot();
        Ex.Pass_ScreenShot("Data Ingestion Manage Bred Crumb Displayed in Manifest Upload Background Page");
        jsclick(Cancel_btn);
        loader();
        ScrollView(Manifest_Elipse);
        jsclick(Manifest_Elipse);
        jsclick(Download_Btn);
        screenshot();
        Ex.Pass_ScreenShot("Data Ingestion Manage Bred Crumb Displayed in Manifest Download Background Page");
        jsclick(Cancel_btn);
        loader();
        jsclick(Manifest_Delete);
        screenshot();
        Ex.Pass_ScreenShot("Data Ingestion Manage Bred Crumb Displayed in Manifest Delete Background Page");
        jsclick(No_Btn);

    }


    public void validate_DataInges_Breadcrumbs(String UIProject) {
        WebElement Infra_Breadcrumb = driver.findElement(By.xpath("//a[text()='Apps']/../..//li//a[contains(text(),'Project Management (" + UIProject + ")')]/../..//li[text()='Data Ingestion Management']"));
        Assert.assertTrue(Infra_Breadcrumb.isDisplayed());
    }

    public void validate_Infra_Breadcrumbs(String UIProject) {
        WebElement Infra_Breadcrumb = driver.findElement(By.xpath("//a[text()='Apps']/../..//li//a[contains(text(),'Project Management (" + UIProject + ")')]/../..//li[text()='Infra Settings']"));
        Assert.assertTrue(Infra_Breadcrumb.isDisplayed());
    }

    @Step("Validation Of Infra Settings BreadCrumb Page")
    public void validate_Pm_Breadcrumbs_Infra_Settings(String UIProject) {
        jsclick(Infra_Settings);
        validate_Infra_Breadcrumbs(UIProject);
        screenshot();
        Ex.Pass_ScreenShot("Infra Settings Bred Crumb Displayed in Infra Settings Page");
        jsclick(HPC_Toggle_btn);
        validate_Infra_Breadcrumbs(UIProject);
        screenshot();
        Ex.Pass_ScreenShot("Infra Settings Bred Crumb Displayed in HPC Toggle Background Page");
        WebElement ProjectBM_Link = driver.findElement(By.xpath("//a[text()='Apps']/../..//li//a[contains(text(),'Project Management (" + UIProject + ")')]"));
        jsclick(ProjectBM_Link);

    }

    @Step("Validate Apps > Project Management Bredcrumbs For Delete & Onboard Project")
    public void validate_Pm_BreadCrumbs_DeleteProject_Onboard() {
       
        jsclick(Pm_RadioBtn);
        Ele_Click(Pm_DeleteBtn);
        Assert.assertTrue(Apps_ProjectMangmnt_BreadCrumb.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Apps_ProjectMangmnt_BreadCrumb Is Displayed In Background for Delete Project");
        jsclick(No_Btn);
        jsclick(Onboard_Region);
        Assert.assertTrue(Apps_ProjectMangmnt_BreadCrumb.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Apps_ProjectMangmnt_BreadCrumb Is Displayed In Background for Onboard Region");
        jsclick(Cancel_btn);
    }

    public void delete_project(String Project_Name) {
        String Status = Project_Status_Value.getText().trim();
        if (Status.equalsIgnoreCase("Created")) {
            Assert.assertTrue(true, "Project creation completed in given time");
            screenshot();
            Ex.Pass_ScreenShot("Project " + Project_Name + "is created");
        } else {
            screenshot();
            Ex.Pass_ScreenShot("Project " + Project_Name + "is not  created");
            Assert.assertTrue(false);
        }

        WebElement RadioBtn = driver.findElement(By.xpath("(//mat-cell[contains(text()," + Project_Name + ")]/..//td//mat-radio-button//div)[1]"));
        jsclick(RadioBtn);
        jsclick(Pm_DeleteBtn);
        Ele_Click(Yes_Btn);
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg, Constant.Proj_Deletion_SuccessMsg);

    }

    @Step("Project Creation Values Validation")
    public void verify_Project_table(String Name, String Region, String Addtional_Service) {
        String Status = null;
        Assert.assertEquals(Project_Name_Value.getText().trim(), Name);
        Assert.assertEquals(Project_Name_Region.getText().trim(), Region);
        Assert.assertEquals(Project_AditionalService_Value.getText().trim(), Addtional_Service);
        /*do {
            driver.get(driver.getCurrentUrl());
            normalwait(2000);
            Status = Project_Status_Value.getText().trim();
        }
        while (Status != "failed" || Status != "Created");

        if (Status.equalsIgnoreCase("failed")) {
            Assert.assertTrue(false, "Project Creation Got Failed");
            screenshot();
            Ex.Pass_ScreenShot("Project Creation Got Failed");
        } else {
            screenshot();
            Ex.Pass_ScreenShot("Project Got Created");
        }*/
    }

    public void verify_ProjectCreate_SuccessMsg() {
        jsclick(Save_btn);
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg, Constant.Proj_Creation_SuccessMsg);
    }

    public void Create_Porject(String ProjName, String Region, String WBSCode, String AddService) throws InterruptedException {
        jsclick(Create_Project);
        send_keys(Project_Name_Input, ProjName);
        jsclick(Create_Projectnew);
        normalwait(3000);
        SelectDrpDwnValue(Region_DrpDwn, Region);
        send_keys(WBSCode_Input, WBSCode);
        SelectDrpDwnValue(AddService_DrpDwn, AddService);

    }

    public void navigate_cat_case_details() {
        normalwait(3000);
        jsclick(Catalog_Case_Details);
        normalwait(3000);
    }

    @Step("Verify Sorting As Per Project Names And Unavailabilty of  Create Project(Unless Admin)")
    public void verify_PO_SortFunction() {
        ArrayList<String> Names = new ArrayList<>();
        ArrayList<String> SortedNames = new ArrayList<>();
        jsclick(PO_Name_Header);
        int count = PO_RowCount.size();
        for (int i = 1; i < count; i++) {
            WebElement project = driver.findElement(By.xpath("((//mat-table//mat-row)[" + i + "]//mat-cell)[1]"));
            Names.add(project.getText().trim());
        }
        for (String value : Names) {
            SortedNames.add(value);
        }
        //Names.addAll(SortedNames);
        SortedNames.sort(Comparator.naturalOrder());
        boolean value = Names.equals(SortedNames);
        Assert.assertTrue(value);
        Assert.assertEquals(Create_Project_Count.size(), 0);
        screenshot();
        Ex.Pass_ScreenShot("Verify Sorting As Per Project Names And Unavailabilty of  Create Project(Unless Admin)");
    }

    @Step("Verify User Able to View His Project Acces Role")
    public void verify_projectAccess() {
        jsclick(Project_Access);
        page_loader();
        WebElement User_Role = driver.findElement(By.xpath("(//mat-cell[contains(text(),'pavithra.ravi.pr1@contractors.roche.com')]/..//mat-cell)[4]"));
        screenshot();
        Ex.Pass_ScreenShot("Logged in User Role : " + User_Role);
        jsclick(Project_Access);
    }

    @Step("Validate Project Elipse Access")
    public void verify_projectelipse_access(String Role) {
        if (Role.equalsIgnoreCase(Constant.Data_Manager) || Role.equalsIgnoreCase(Constant.Data_User1)) {
            Assert.assertTrue(Project_Access.isDisplayed());
            Assert.assertTrue(Technical_Settings.isDisplayed());
            Assert.assertTrue(Data_Ingestion_Mgmt.isDisplayed());
        } else if (Role.equalsIgnoreCase(Constant.Data_User2)) {
            Assert.assertTrue(Project_Access.isDisplayed());
            Assert.assertTrue(Technical_Settings.isDisplayed());
        } else if (Role.equalsIgnoreCase(Constant.Privacy_Officer)) {
            Assert.assertTrue(Project_Access.isDisplayed());
        }
        screenshot();
        Ex.Pass_ScreenShot("Validate Project Elipse Access");

    }

    @Step("Validate Project Overiew Headers")
    public void Verify_ProjOverview_Headers() {
        Assert.assertTrue(PO_Name_Header.isDisplayed());
        Assert.assertTrue(PO_ProjID_Header.isDisplayed());
        Assert.assertTrue(PO_Status_Header.isDisplayed());
        Assert.assertTrue(PO_Region_Header.isDisplayed());
        Assert.assertTrue(PO_AddServices_Header.isDisplayed());
        Assert.assertTrue(PO_CreatedOn_Header.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Validate Project Overiew Headers");

    }

    @Step("Invalid Project Name Validation")
    public void verify_invalidProjectname(String Pw0, String Pw1, String Pw2, String Pw3, String Pw4) {
        jsclick(Create_Project);
        send_keys(Project_Name_Input, Pw0);
        validate_max_values(Project_Name_Input, Pw0);
        screenshot();
        Ex.Pass_ScreenShot("Project Name Max Count Validation");
        Project_Name_Input.clear();
        send_keys(Project_Name_Input, Pw1);
        Assert.assertEquals(Invalid_ProjName_Msg.getText().trim(), Constant.ProjName_LengthMsg);
        screenshot();
        Ex.Pass_ScreenShot("Project Name Length Error Message");
        Project_Name_Input.clear();
        send_keys(Project_Name_Input, Pw2);
        Assert.assertEquals(Invalid_ProjName_Msg.getText().trim(), Constant.ProjName_AlphabetErrorMsg);
        screenshot();
        Ex.Pass_ScreenShot("Project Name Alphabet Error Message");
        Project_Name_Input.clear();
        send_keys(Project_Name_Input, Pw3);
        Assert.assertEquals(Invalid_ProjName_Msg.getText().trim(), Constant.ProjName_AlphaNumericErrorMsg);
        screenshot();
        Ex.Pass_ScreenShot("Project Name Alpha Numeric Error Message");
        Project_Name_Input.clear();
        send_keys(Project_Name_Input, Pw4);
        Assert.assertEquals(Invalid_ProjName_Msg.getText().trim(), Constant.ProjName_SpecialCharErrorMsg);
        screenshot();
        Ex.Pass_ScreenShot("Project Name Special Character Error Message");
    }

    //Robin Method
    @Step("Delete All File Cards And Verify")
    public void DeleteAllFileCardsAndVerify() throws InterruptedException {

        for (int i = 0; i <= 2; i++) {
            driver.findElement(By.xpath("(//mat-icon[contains(text(),'delete_outline')]/../../..//button)[1]")).click();
            screenshot();
        }

        Assert.assertTrue(Add_another_file_button.isDisplayed());
        Ex.Pass_ScreenShot("Delete File Validation:");
        screenshot();


    }

    @Step("Validate UI Ingestion With Mulitple Valid Files")
    public void Valid_Filename_onAll4Files(String File) throws InterruptedException {
        driver.navigate().refresh();
        String[] Files = File.split(",");
        for (int i = 1; i < 5; i++) {
            driver.findElement(By.xpath("(//mat-label[text()=' File Name/URL ']/../../../input)[" + i + "]")).sendKeys(Files[i]);
            screenshot();
            if (i <= 3) {
                Assert.assertTrue(Add_another_file_button.isDisplayed());
                jsclick(Add_another_file_button);

            } else {
                int noe = Add_file_button_after4files.size();
                if (noe == 0) {
                    System.out.println("maximum 4 files can be added");
                }
            }
            Ex.Pass_ScreenShot("Validate UI Ingestion With Mulitple Valid Files");

        }
        screenshot();
        //Save_btn.click();
        Thread.sleep(3000);
    }

    @Step("Validate UI Ingestion With Multiple Invalid Files")
    public void verifyInvalid_Filename_onAll4Files(String File) throws InterruptedException {

        driver.navigate().refresh();
        Ele_Click(File_Url_Input);
        String[] Files = File.split(",");
        for (int i = 1; i < 5; i++) {
            driver.findElement(By.xpath("(//mat-label[text()=' File Name/URL ']/../../../input)[" + i + "]")).sendKeys(Files[i]);
            Thread.sleep(2000);
            Assert.assertEquals(driver.findElement(By.xpath("(//div[contains(text(),'Please enter a valid https URL')])[" + i + "]")).getText(), "Please enter a valid https URL");


            if (i <= 3) {
                Assert.assertTrue(Add_another_file_button.isDisplayed());
                jsclick(Add_another_file_button);
                screenshot();
            } else {
                int noe = Add_file_button_after4files.size();
                if (noe == 0) {
                    System.out.println("maximum 4 files can be added");
                }
            }

        }
        screenshot();
        Ele_Click(Save_btn);
        Thread.sleep(3000);
    }

    public void DF_Case_Table(String Primarysite, String Diseasetype, String Consenttype) throws InterruptedException {

        ScrollView(MC_Case);
        normalwait(2000);
        jsclick(MC_Case1);
        SelectDrpDwnValue(CASE_Primary_site, Primarysite);
        SelectDrpDwnValue(CASE_Disease_type, Diseasetype);
        SelectDrpDwnValue(CASE_Consent_type, Consenttype);
        send_keys(Case_Submitter_ID, "CaseSubmittedIDtest");

    }

    public int select_Proj_Overview(String projectid) {

        //new line
        Ele_Click(Search_box);
        send_keys(Search_box, projectid);
        int rowCount = Proj_Overview_Row.size();
        if (rowCount < 2) {
            driver.findElement(By.xpath("//mat-table[1]/mat-row[1]/mat-cell[6]/li[1]/mat-icon[1]")).click();
        } else {
            driver.findElement(By.xpath("//mat-table[1]/mat-row[2]/mat-cell[6]/li[1]/mat-icon[1]")).click();
        }

        return rowCount;
    }


    @Step("Validate PO Technical Settings Access")
    public void verify_PO_TechnicalSettings() {
        int size = Technical_Settings_Count.size();
        Assert.assertEquals(size, 0);
        screenshot();
        Ex.Pass_ScreenShot("Technical Settings Not Displayed For PO As Expected");
    }

    @Step("Verify Project Overview For UA User")
    public void validate_PO_UnassignedUser() {
        wait.until(ExpectedConditions.visibilityOf(No_Data_Found));
        Assert.assertTrue(No_Data_Found.isDisplayed());
        screenshot();
    }

    public void validate_MO_SavedStatus(String FileName) throws InterruptedException {
        Ele_Click(Cancel_btn);
        normalwait(1000);
        wait.until(ExpectedConditions.elementToBeClickable(Manifest_DrpDwn));
        String Name = MO_FileName.getText();
        while (!Name.equalsIgnoreCase(FileName)) {
            normalwait(2000);
            driver.navigate().refresh();
        }
        WebElement Status = driver.findElement(By.xpath("(//mat-cell//span[text()='" + FileName + "']/..//following-sibling::mat-cell)[1]"));
        Assert.assertEquals(Status.getText(), "Saved");
        Ex.Pass_ScreenShot("Validate MO saved Status");
    }

    public void verify_MO_Subforingestion(String FileName) throws InterruptedException {
        WebElement ele = driver.findElement(By.xpath("(//mat-cell//span[text()='" + FileName + "']/..//following-sibling::mat-cell)[4]//li"));
        jsclick(ele);
        jsclick(MO_Editbtn);
        ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
        Submit_Ingestion_SuccessMsg(Constant.Ingestion_Success_Msg);
        WebElement Status = driver.findElement(By.xpath("(//mat-cell//span[text()='" + FileName + "']/..//following-sibling::mat-cell)[1]"));
        Assert.assertEquals(Status.getText(), "Submitted for Ingestion");
        int size = driver.findElements(By.xpath("(//mat-cell//span[text()='" + FileName + "']/..//following-sibling::mat-cell)[1]")).size();
        long starttime = System.currentTimeMillis();
        long waittime = 20000;
        long endtime = starttime + waittime;
        do {
            normalwait(2000);
            driver.navigate().refresh();
            size = driver.findElements(By.xpath("(//mat-cell//span[text()='" + FileName + "']/..//following-sibling::mat-cell)[1]")).size();
        }
        while (System.currentTimeMillis() < endtime && size > 0);


    }


    public void verify_manifest_overview_fields() {

        wait.until(ExpectedConditions.visibilityOf(Manifest_ID));
        Assert.assertTrue(Manifest_ID.isDisplayed());
        Assert.assertTrue(File_Url.isDisplayed());
        Assert.assertTrue(MO_Status.isDisplayed());
        Assert.assertTrue(MO_Created_By.isDisplayed());
        Assert.assertTrue(MO_Date_created_modified.isDisplayed());
        Ex.Pass_ScreenShot("Verify Manifest Overview Fields");

    }

    @Step("Verify UI File Count")
    public int verify_file_count(int Filecount) throws InterruptedException {
        int size = 0;
        int count = 0;
        String No = null;
        long starttime = System.currentTimeMillis();
        System.out.println("Start" + starttime);
        long waittime = 60000;
        long endtime = starttime + waittime;
        System.out.println("End" + endtime);
        boolean flag;

        do {
            driver.get(driver.getCurrentUrl());
            normalwait(2000);
            if (File_Count_size.size() == 0) {
                No = File_Count_No_Link.getText();
            } else {
                No = File_Count.getText();
            }

            count = Integer.parseInt(No);
            System.out.println("Current" + System.currentTimeMillis());
        }
        while (System.currentTimeMillis() < endtime && count <= Filecount);


        if (count == Filecount + 1) {
            Assert.assertTrue(true);
            Ex.Pass_ScreenShot("File Count Increase Verification After UI Ingestion :");
        } else {
            System.out.println("File Count:" + count);
            Assert.assertTrue(false);
        }


        screenshot();
        return count;
    }


    /*public int verify_file_count(int Filecount) throws InterruptedException {
        int size=0;
        int Iteration_count=0;

        while (size==0){
            if (Iteration_count<10){
                driver.get(driver.getCurrentUrl());
                Thread.sleep(2000);
                size =File_Count_size.size();
                Iteration_count=Iteration_count+1;
            }
            else{
                break;
            }

        }
        String c =File_Count.getText();
        int count =Integer.parseInt(c);
        if (count==Filecount+1){
            Assert.assertTrue(true);
        }
        else{
            System.out.println("File Count:"+count);
            Assert.assertTrue(false);
        }
        return count;
    }*/
    public void Create_Manifest_Cancel_No() {
        Ele_Click(Cancel_btn);
        Assert.assertTrue(Cancel_No_Msg.isDisplayed());
        Ele_Click(No_Btn);
    }

    public boolean validate_DrpDwnDisabled(WebElement Ele) {
        boolean value;
        String status = Ele.getAttribute("aria-disabled");
        if (status.equalsIgnoreCase("true")) {
            value = false;
        } else {
            value = true;
        }
        return value;
    }

    @Step("Verify Non-Editable Case Fields")
    public void verify_non_editable_case_fields() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(MC_Case1));
        ScrollView(MC_Case1);
        jsclick(MC_Case1);
        Assert.assertFalse(Case_Submitter_ID.isEnabled());
        Assert.assertFalse(validate_DrpDwnDisabled(Case_PrimsiteDD));
        Assert.assertFalse(validate_DrpDwnDisabled(Case_DiseaseTypeDD));
        Assert.assertFalse(validate_DrpDwnDisabled(Case_ConsentTypeDD));
        screenshot();
        Ex.Pass_ScreenShot("Verify Non-Editable Case Fields");
    }

    @Step("Verify Non-Editable Sample Fields")
    public void verify_non_editable_sample_fields() throws InterruptedException {
        ScrollView(MC_Sample);
        jsclick(MC_Sample);
        Thread.sleep(2000);
        Assert.assertFalse(validate_DrpDwnDisabled(Tumor_DescriptorDrpDwn));
        Assert.assertFalse(validate_DrpDwnDisabled(Composition_DrpDwn));
        Assert.assertFalse(validate_DrpDwnDisabled(BAS_DrpDwn));
        Assert.assertFalse(validate_DrpDwnDisabled(Bio_laterality_DrpDwn));
        screenshot();
        Ex.Pass_ScreenShot("Verify Non-Editable Sample Fields");
    }

    @Step("Verify Non Editable Aliquot Fields")
    public void verify_non_editable_aliquot_fields() throws InterruptedException {
        ScrollView(MC_Aliquot);
        jsclick(MC_Aliquot);
        Thread.sleep(2000);
        Assert.assertFalse(Aliquot_Submitter_ID.isEnabled());
        Assert.assertFalse(Aliquot_Amount_Input.isEnabled());
        Assert.assertFalse(Source_center_Input.isEnabled());
        Assert.assertFalse(validate_DrpDwnDisabled(Aliq_Analytetype_DrpDwn));
        Assert.assertFalse(Aliq_Concentration_Input.isEnabled());
        screenshot();
        Ex.Pass_ScreenShot("Verify Non-Editable Aliquot Fields");
    }

    @Step("Verify Non-Editable Project Fields")
    public void verify_non_editable_project_fields() throws InterruptedException {
        ScrollView(MC_Project);
        jsclick(MC_Project);
        s_assert.assertFalse(Projectid_Input.isEnabled(),"Project ID Enabled Incorrectly");
        s_assert.assertFalse(Primarysite_Input.isEnabled(),"Primarysite_Input Enabled Incorrectly");
        s_assert.assertFalse(validate_DrpDwnDisabled(Data_ClassificationDD),"Data_ClassificationDD is Enabled Incorrectly");
        s_assert.assertFalse(Data_offName.isEnabled(),"Data_offName Enabled Incorrectly");
        s_assert.assertFalse(Project_Name.isEnabled(),"Project_Name Enabled Incorrectly");
        s_assert.assertFalse(Curr_Datausers.isEnabled(),"Curr_Datausers Enabled Incorrectly");
        s_assert.assertFalse(Data_Acc_Commite.isEnabled(),"Data_Acc_Commite Enabled Incorrectly");
        //s_assert.assertFalse(Project_Description.isEnabled(),"Project_Description Enabled Incorrectly");
        screenshot();
        Ex.Pass_ScreenShot("Verify Non-Editable Project Fields");


    }

    public void non_editable_fields_Aliquot() throws InterruptedException {

        verify_non_editable_case_fields();
        verify_non_editable_sample_fields();
        verify_non_editable_project_fields();

    }

    public void verify_can_save_ingestBtn() {
        Assert.assertTrue(Save_btn.isDisplayed());
        Assert.assertTrue(Cancel_btn.isDisplayed());
        Assert.assertTrue(Submit_Fr_IngesBtn.isDisplayed());
        System.out.println("Save,Cancel,Submit For Ingestion Btn Displayed As Expected");
    }

    @FindBy(xpath = "//span[contains(text(),'CSV')]")
    public WebElement IP_Csv;

    @FindBy(xpath = "//span[contains(text(),'FCS')]")
    public WebElement IP_Fcs;

    @FindBy(xpath = "//span[contains(text(),'TAB')]")
    public WebElement IP_Tab;

    @FindBy(xpath = "//span[contains(text(),'ImmunoPETE')]")
    public WebElement IP_ImmunoPETE;

    @FindBy(xpath = "//span[contains(text(),'RNA-seq')]")
    public WebElement IP_RNASeq;

    @FindBy(xpath = "//span[contains(text(),'single-cell RNA-seq')]")
    public WebElement IP_Singlecell;

    @FindBy(xpath = "(//span[contains(text(),'Immune profile')])[2]")
    public WebElement IP_ImmuneProfile;

    @FindBy(xpath = "//span[contains(text(),'10x')]")
    public WebElement IPWT_10x;

    @FindBy(xpath = "//span[contains(text(),'Daedalus')]")
    public WebElement IPWT_Daedalus;

    @FindBy(xpath = "//span[contains(text(),'Single cell')]")
    public WebElement IPWT_Singlecell;

    @FindBy(xpath = "//span[contains(text(),'Takara')]")
    public WebElement IPWT_Takara;


    @FindBy(xpath = "//mat-label[contains(text(),'Project id')]/../../..//input")
    public WebElement Project_id;

    @Step("Validate Immune Profile Values ")
    public void validate_MCImmuneprofile_Values() throws InterruptedException {
        Element_ToBe_Clickable(MC_ImmuneProfile);
        ScrollView(MC_ImmuneProfile);
        Thread.sleep(2000);
        jsclick(MC_ImmuneProfile);
        validate_IPDataFormatValues();
        validate_IPDataTypeValues();
        validate_IPDataCategoryValues();
    }

    @Step("Validate Immune Profile Workflow Values")
    public void validate_MC_IPWorkflowValues() throws InterruptedException {
        ScrollView(MC_ImmuneProfile_Workflow);
        Thread.sleep(2000);
        jsclick(MC_ImmuneProfile_Workflow);
        jsclick(IF_workflowDD);
        s_assert.assertTrue(IPWT_10x.isDisplayed());
        s_assert.assertTrue(IPWT_Daedalus.isDisplayed());
        s_assert.assertTrue(IPWT_Singlecell.isDisplayed());
        s_assert.assertTrue(IPWT_Takara.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Validate IP WorkFlow Values");
        double_click(IPWF_header);

    }

    public void validate_IPDataFormatValues() {
        jsclick(IF_dataformatDrpDwn);
        normalwait(1000);
        Assert.assertTrue(IP_Csv.isDisplayed());
        Assert.assertTrue(IP_Fcs.isDisplayed());
        Assert.assertTrue(IP_Tab.isDisplayed());
        screenshot();
        System.out.println("IP Data Format Values CSV,FCS,TAB Displayed AS Expected");
        double_click(IP_header);

    }

    public void validate_IPDataTypeValues() {
        jsclick(IF_datatypeDrpDwn);
        normalwait(1000);
        Assert.assertTrue(IP_ImmunoPETE.isDisplayed());
        Assert.assertTrue(IP_RNASeq.isDisplayed());
        Assert.assertTrue(IP_Singlecell.isDisplayed());
        screenshot();
        double_click(IP_header);

    }


    public void validate_IPDataCategoryValues() {
        jsclick(IF_datacategoryrpDwn);
        normalwait(1000);
        s_assert.assertTrue(IP_ImmuneProfile.isDisplayed());
        screenshot();
        System.out.println("IP Data Category Values Immune Profile Displayed AS Expected");
        double_click(IP_header);

    }

    @Step("Immune Profile Inputs")
    public void MC_Immuneprofile_Input(String Immune_ProfileValues) throws InterruptedException {
        HashMap<String, String> map = new HashMap<>();
        String[] Immune_Profile = Immune_ProfileValues.split(",");
        ScrollView(MC_ImmuneProfile);
        jsclick(MC_ImmuneProfile);
        SelectDrpDwnValue(IF_dataformatDrpDwn, Immune_Profile[0]);
        SelectDrpDwnValue(IF_datatypeDrpDwn, Immune_Profile[1]);
        jsclick(IF_datacategoryrpDwn);
        jsclick(IP_ImmuneProfile);
        screenshot();
        map.put("IF Data Format", Immune_Profile[0]);
        map.put("IP Data Type", Immune_Profile[1]);
        map.put("IP Data Category", Immune_Profile[2]);
        Ex.Info_ScreenShot("IP WorkFlow Input  Values: " + MarkupHelper.createUnorderedList(map).getMarkup());

    }

    public void Clear_IPWorkFlow() throws InterruptedException {
        ScrollView(MC_ImmuneProfile_Workflow);
        jsclick(MC_ImmuneProfile_Workflow);
        jsclick(IPWorkFlow_Clearfields);
        jsclick(IPWorkFlow_Clearfields);
        Ex.Extent_Info("IP WorkFlow  Is Cleared");
    }

    @Step("Immune Workflow Input")
    public void MC_Alignment_WFInput(String Workflow) throws InterruptedException {
        ScrollView(MC_Alignment_Workflow);
        jsclick(MC_Alignment_Workflow);
        SelectDrpDwnValue(Alignment_workflowDD, Workflow);
        screenshot();
        Ex.Extent_Info("Alignment WorkFlow Value: " + Workflow);
    }

    @Step("Immune Workflow Input")
    public void MC_Immune_WFInput(String Workflow) throws InterruptedException {
        ScrollView(MC_ImmuneProfile_Workflow);
        jsclick(MC_ImmuneProfile_Workflow);
        SelectDrpDwnValue(IF_workflowDD, Workflow);
        screenshot();
        Ex.Extent_Info("Immune Profile WorkFlow Value: " + Workflow);
    }
    @Step("Navigate To File")
    public void Navigate_MCFile() throws InterruptedException {
        ScrollView(MC_File);
        jsclick(MC_File);
    }
    @Step("File Input")
    public void MC_FileInput(String FileName) throws InterruptedException {
        ScrollView(MC_File);
        jsclick(MC_File);
        File_Url_Input.clear();
        send_keys(File_Url_Input, FileName);
        screenshot();
        Ex.Pass_ScreenShot("File Name Entered: " + FileName);
    }

    @FindBy(xpath = "(//tbody[@role='rowgroup']//td)[6]")
    public WebElement Case_ID_dm;


    public String getCaseId() {
        String value = Case_ID_dm.getText();
        return value;
    }
    public String getFileId() {
        String value = File_id.getAttribute("value");
        System.out.println("File Id"+value);
        return value;
    }

    public String getprojectId() throws InterruptedException {
        ScrollView(MC_Project);
        jsclick(MC_Project);
        String value = Project_id.getAttribute("value");
        return value;
    }

    @Step("Validate Manifest Data")
    public void Verify_Manifestdata(String FileName) throws InterruptedException {
        loader();
        jsclick(ManifestTable_Data);
        String File = ManifestTable_Data.getText();
        Assert.assertEquals(FileName, File);
        Ex.Pass_ScreenShot("Manifest Data Verification");
        screenshot();
        System.out.println("Manifest is saved successfully and can viewed in the manifest table ");

    }

    @Step("Validate Sample ALiquot Error Message")
    public void Val_Samp_Ali_ErrorMsg(String Sample_SubId, String Aliquot_SubId, String Role) throws InterruptedException {

        SampAliq_Error_Msg_WithoutData(Role);
        SampAliq_Error_Msg_Data(Sample_SubId, Aliquot_SubId);
    }


    @FindBy(xpath = "//a[text()='Apps']/../..//li//a[text()='Catalog Management (Raw Files)']/../..//li/a[text()='Catalog case details']/../..//li[contains(text(),'Add new aliquot')]")
    public WebElement CM_AddNewAliquot_BC;

    public void Sel_AddAliquot(String Role) throws InterruptedException {
        WebElement Ele = driver.findElement(By.xpath("((//span[text()='Samples'])[1]//following::td)[1]//mat-icon[text()='chevron_right']"));
        Ele.click();
        Thread.sleep(2000);
        if (Role.equalsIgnoreCase("Data Manager") || Role.equalsIgnoreCase("Admin") || Role.equalsIgnoreCase("Data User1")) {
            Ele_Click(Add_aliquotBtn);
            normalwait(3000);
            jsclick(Select_VersionDrpDWn);
            jsclick(Select_Version_1);
            screenshot();
            Ele_Click(Proceed_Btn);
            normalwait(3000);
            //Navigate_To_CreateMetaData();
            Assert.assertTrue(CM_AddNewAliquot_BC.isDisplayed());
            screenshot();
            Ex.Pass_ScreenShot("Add new Aliquot Breadcrumb is displayed for add aliq from sample page");
        } else {
            List<WebElement> Ele1 = driver.findElements(By.xpath("//span[text()='Add Aliquot']/.."));
            Assert.assertEquals(Ele1.size(), 0);
            System.out.println("Add aliquot Button Not Displayed For " + Role + " As Expected");
        }

    }

    @Step("Add File From Aliquot")
    public void AddFile_Aliquot(String Role) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("((//span[text()='Aliquots'])[1]//following::td)[1]//mat-icon[text()='chevron_right']")));
        WebElement Ele = driver.findElement(By.xpath("((//span[text()='Aliquots'])[1]//following::td)[1]//mat-icon[text()='chevron_right']"));
        Ele.click();
        Thread.sleep(2000);
        if (Role.equalsIgnoreCase("Data Manager") || Role.equalsIgnoreCase("Admin") || Role.equalsIgnoreCase("Data User1")) {
            clickableEle(Add_FileBtn);
            screenshot();
            Ex.Info_ScreenShot("Add File From Aliquot Button Clicked");
            Ele_Click(Add_FileBtn);

        } else {
            List<WebElement> Ele1 = driver.findElements(By.xpath("//span[text()='Add File']/.."));
            Assert.assertEquals(Ele1.size(), 0);
            screenshot();
            Ex.Pass_ScreenShot("Add aliquot Button Not Displayed For " + Role + " As Expected");
        }

    }

    @Step("Add File From Sample")
    public void AddFile_Sample(String Role,String Domain) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("((//span[text()='Samples'])[1]//following::td)[1]//mat-icon[text()='chevron_right']")));
        WebElement Ele = driver.findElement(By.xpath("((//span[text()='Samples'])[1]//following::td)[1]//mat-icon[text()='chevron_right']"));
        Ele.click();
       normalwait(2000);
        if (Role.equalsIgnoreCase("Data Manager") || Role.equalsIgnoreCase("Admin") || Role.equalsIgnoreCase("Data User1")) {
            clickableEle(Add_FileBtn);
            screenshot();
            Ele_Click(Add_FileBtn);
            normalwait(3000);
            if(Domain.equalsIgnoreCase("Sequence")){
                Navigate_To_RawSequence();
            }
            else if (Domain.equalsIgnoreCase("Non-Sequence")){
                Navigate_To_RawNonSequence();
            }
            Assert.assertTrue(CM_AddNewFile_BC.isDisplayed());
            screenshot();
            Ex.Pass_ScreenShot("Required info bredcrumb is dsiplayed under add new file from Sample/Aliquot Page  ");
        } else {
            List<WebElement> Ele1 = driver.findElements(By.xpath("//span[text()='Add File']/.."));
            Assert.assertEquals(Ele1.size(), 0);
            screenshot();
            System.out.println("Add aliquot Button Not Displayed For " + Role + " As Expected");
        }

    }


    @FindBy(xpath = "//a[text()='Apps']/../..//li//a[text()='Catalog Management (Raw Files)']/../..//li/a[text()='Catalog case details']/../..//li/a[text()='Required Information']/../..//li[contains(text(),'Add new file')]")
    public WebElement Add_NewFileBC;

    public void CM_Seq_Non_Seq(String Status) throws InterruptedException {
        page_loader();
        normalwait(1000);
        if (Status.equalsIgnoreCase("Sequence")) {
            Navigate_To_RawSequence();
        } else {
           Navigate_To_RawNonSequence();
        }
        normalwait(3000);
        Assert.assertTrue(Add_NewFileBC.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Add new File is displayed As Expected");

    }

    @FindBy(xpath = "//a[text()='Apps']/../..//li//a[text()='Catalog Management (Raw Files)']/../..//li/a[text()='Catalog case details']/../..//li/a[text()='Required Information']/../..//li[contains(text(),'Add new file')]")
    public WebElement CM_AddNewFile_BC;

    public void AddFile_Case(String Role,String Domain) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("((//span[text()='Case'])[1]//following::td)[1]//mat-icon[text()='chevron_right']")));
        WebElement Ele = driver.findElement(By.xpath("((//span[text()='Case'])[1]//following::td)[1]//mat-icon[text()='chevron_right']"));
        Ele_Click(Ele);
        normalwait(2000);
        if (Role.equalsIgnoreCase("Data Manager") || Role.equalsIgnoreCase("Admin") || Role.equalsIgnoreCase("Data User1")) {
            Ele_Click(Add_FileBtn);
            normalwait(3000);
            if(Domain.equalsIgnoreCase("Sequence")){
                Navigate_To_RawSequence();
            }
            else if (Domain.equalsIgnoreCase("Non-Sequence")){
                Navigate_To_RawNonSequence();
            }
            normalwait(3000);
            //Assert.assertTrue(CM_AddNewFile_BC.isDisplayed());
            screenshot();
            Ex.Pass_ScreenShot("Required info bredcrumb is dsiplayed under add new file from case page ");
        } else {
            List<WebElement> Ele1 = driver.findElements(By.xpath("//span[text()='Add File']/.."));
            Assert.assertEquals(Ele1.size(), 0);
            System.out.println("Add File Button Not Displayed For " + Role + " As Expected");
        }
    }

        @FindBy(xpath = "//a[text()='Apps']/../..//li//a[text()='Catalog Management (Raw Files)']/../..//li/a[text()='Catalog case details']/../..//li[text()='Add new sample']")
    public WebElement CM_AddNewSample_BC;

    @Step("Select Add Sample/Aliquot")
    public void Sel_AddSampAliquot(String Role) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("((//span[text()='Case'])[1]//following::td)[1]//mat-icon[text()='chevron_right']")));
        WebElement Ele = driver.findElement(By.xpath("((//span[text()='Case'])[1]//following::td)[1]//mat-icon[text()='chevron_right']"));
        ScrollView_Action(Ele);
        Ele_Click(Ele);
        normalwait(2000);
        if (Role.equalsIgnoreCase("Data Manager") || Role.equalsIgnoreCase("Admin") || Role.equalsIgnoreCase("Data User1")) {
            clickableEle(Add_sample_aliquotBtn);
            screenshot();
            Add_sample_aliquotBtn.click();
            normalwait(3000);
            ScrollView_Action(Select_VersionDrpDWn);
            jsclick(Select_VersionDrpDWn);
            jsclick(Select_Version_1);
            screenshot();
            jsclick(Proceed_Btn);
            normalwait(3000);
//            Assert.assertTrue(CM_AddNewSample_BC.isDisplayed());
            screenshot();
            Ex.Pass_ScreenShot("Add new sample bredcrumb is dsiplayed under add sample/aliq from case page ");
        } else {
            List<WebElement> Ele1 = driver.findElements(By.xpath("//span[text()='Add sample/aliquot']/.."));
            Assert.assertEquals(Ele1.size(), 0);
            screenshot();
            System.out.println("Add Sample/aliquot Button Not Displayed For " + Role + " As Expected");
        }
    }

    public void validate_Aliq_ErrorMsg() throws InterruptedException {
        Ele_Click(Save_btn);
        Thread.sleep(3000);
        clickableEle(MC_Aliquot);
        jsclick(MC_Aliquot);
        Assert.assertEquals(Aliquot_Error_Msg.getText().trim(), "Aliquot submitter id is required");
    }

    public void validate_Case_Addfile_Error_Msg(String Samp_SubID, String Aliq_ID, String File) throws InterruptedException {
        validate_Case_Addfile_Error_Msg_WithoutData();
        validate_Case_Addfile_Error_Msg_WithData(Samp_SubID, Aliq_ID, File);
    }

    public void validate_Case_Addfile_Error_Msg_WithoutData() throws InterruptedException {
        Ele_Click(Save_btn);
        File_Error_Msg();
        sample_Error_Msg();
        Aliquot_Error_Msg();
    }

    @Step("Verify Add File From Sample Error Msg With Out Data")
    public void validate_Sample_Addfile_Error_Msg_WithoutData() throws InterruptedException {
        Ele_Click(Save_btn);
        File_Error_Msg();
        Aliquot_Error_Msg();
        AlignedRead_Error_Msg();

    }

    @Step("Validate Add File From Aliquot Error Msg Without Data")
    public void validate_Aliquot_Addfile_Error_Msg_WithoutData() throws InterruptedException {
        Ele_Click(Save_btn);
        File_Error_Msg();
        AlignedRead_Error_Msg();

    }

    @Step("Verify Add File From Sample Error Msg With  Data")
    public void validate_Sample_Addfile_Error_Msg_WithData(String Aliq_ID, String File, String RawType, String AlignedRead, String ReadGroup) throws InterruptedException {
        if (RawType.equalsIgnoreCase("Sequence")) {
            MCAlliquot_Mandatory_Input(Aliq_ID);
            Ele_Click(Save_btn);
            File_Error_Msg();
            AlignedRead_Error_Msg();
            ReadGroup_Error_Msg();
            MC_FileInput(File);
            Ele_Click(Save_btn);
            AlignedRead_Error_Msg();
            ReadGroup_Error_Msg();
            remove_Aliquot_data();
            MC_ReadGroup_Inputs(ReadGroup);
            Ele_Click(Save_btn);
            Aliquot_Error_Msg();
            MCAlliquot_Mandatory_Input(Aliq_ID);
            Ele_Click(Save_btn);
            fill_mandatory_tables("192");
            Ele_Click(Save_btn);
            wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
            String Success_Msg = Notification_Msg.getText();
            s_assert.assertEquals(Success_Msg, "Fill one of Aligned reads, Aligned reads index, Annotated somatic mutation, Genomic profile, Simple germline variation, Simple somatic mutation, Slide image, Clinical supplement, Unaligned reads, Immune profile.","Message Not Matching As Expected");
            screenshot();
            System.out.println("Mandatory DF Notification Displayed As Expected When Manifest Saved with no DF");
        } else {
            MCAlliquot_Mandatory_Input(Aliq_ID);
            Ele_Click(Save_btn);
            File_Error_Msg();
            AlignedRead_Error_Msg();
            MC_FileInput(File);
            Ele_Click(Save_btn);
            AlignedRead_Error_Msg();
            remove_Aliquot_data();
            MC_ReadGroup_Inputs(AlignedRead);
            Ele_Click(Save_btn);
            Aliquot_Error_Msg();
            MCAlliquot_Mandatory_Input(Aliq_ID);
            Ele_Click(Save_btn);
            wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
            String Success_Msg = Notification_Msg.getText();
            Assert.assertEquals(Success_Msg, "Fill one of Aligned reads, Aligned reads index, Annotated somatic mutation, Genomic profile, Simple germline variation, Simple somatic mutation, Slide image, Clinical supplement, Unaligned reads, Immune profile.");
            screenshot();
            System.out.println("Mandatory DF Notification Displayed As Expected When Manifest Saved with no DF");
        }

    }


    @Step("Validate Add File From Aliquot Error Msg With Data")
    public void validate_Aliquot_Addfile_Error_Msg_WithData(String File, String RawType, String ReadGroup) throws InterruptedException {
        if (RawType.equalsIgnoreCase("Sequence")) {
            MC_FileInput(File);
            Ele_Click(Save_btn);
            ReadGroup_Error_Msg();
            AlignedRead_Error_Msg();
            MC_ReadGroup_Inputs(ReadGroup);
            remove_File_data();
            Ele_Click(Save_btn);
            File_Error_Msg();
            MC_FileInput(File);
            Ele_Click(Save_btn);
            wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
            String Success_Msg = Notification_Msg.getText();
            Assert.assertEquals(Success_Msg, Constant.ReqTable_RawSeq_ErrorMsg);
            screenshot();
            System.out.println("Mandatory DF Notification Displayed As Expected When Manifest Saved with no DF");
        } else {
            MC_FileInput(File);
            ManifestSave();
            fill_mandatory_tables("193");
            Ele_Click(Save_btn);
            driver.navigate().refresh();
            Ele_Click(Save_btn);
            wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
            String Success_Msg = Notification_Msg.getText();
            Assert.assertEquals(Success_Msg, Constant.ReqTable_RawNonSeq_ErrorMsg);
            screenshot();
            System.out.println("Mandatory DF Notification Displayed As Expected When Manifest Saved with no DF");
        }

    }


    public void validate_Case_Addfile_Error_Msg_WithData(String Samp_SubID, String Aliq_ID, String File) throws InterruptedException {
        MCSample_MandatoryInput(Samp_SubID);
        MCAlliquot_Mandatory_Input(Aliq_ID);
        Ele_Click(Save_btn);
        File_Error_Msg();
        remove_sample_data();
        MC_FileInput(File);
        Ele_Click(Save_btn);
        sample_Error_Msg();
        remove_Aliquot_data();
        MCSample_MandatoryInput(Samp_SubID);
        Ele_Click(Save_btn);
        Aliquot_Error_Msg();
    }


    public void remove_Aliquot_data() throws InterruptedException {
        ScrollView(MC_Aliquot);
        Thread.sleep(2000);
        clickableEle(MC_Aliquot);
        jsclick(MC_Aliquot);
        send_keys(Aliquot_Submitter_ID, Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        send_keys(Aliquot_Submitter_ID, String.valueOf(Keys.BACK_SPACE));
    }

    public void remove_File_data() throws InterruptedException {
        ScrollView(MC_File);
        Thread.sleep(2000);
        clickableEle(MC_File);
        jsclick(MC_File);
        send_keys(File_Url_Input, Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        send_keys(File_Url_Input, String.valueOf(Keys.BACK_SPACE));
    }

    public void remove_sample_data() throws InterruptedException {
        ScrollView(MC_Sample);
        Thread.sleep(2000);
        clickableEle(MC_Sample);
        jsclick(MC_Sample);
        send_keys(Sample_Submitter_ID, Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        send_keys(Sample_Submitter_ID, String.valueOf(Keys.BACK_SPACE));
    }

    @Step("File Error Message")
    public void File_Error_Msg() throws InterruptedException {
        ScrollView(MC_File);
        Thread.sleep(2000);
        clickableEle(MC_File);
        jsclick(MC_File);
        Assert.assertEquals(File_Error_Msg.getText().trim(), Constant.File_Error_Msg);
        Ex.Pass_ScreenShot("File Error Message");
        screenshot();
    }

    public void validate_mdupdate_error_msg() throws InterruptedException {
        page_loader();
        ScrollView(MC_Case1);
        jsclick(MC_Case1);
        send_keys(Case_Submitter_ID, Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        send_keys(Case_Submitter_ID, String.valueOf(Keys.BACK_SPACE));
        Ex.Pass_ScreenShot("Case Error Message");
        Assert.assertEquals(Case_Error_Msg.getText().trim(), Constant.Case_SID_ErrorMsg);
        screenshot();
        ScrollView(MC_Sample);
        jsclick(MC_Sample);
        send_keys(Sample_Submitter_ID, Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        send_keys(Sample_Submitter_ID, String.valueOf(Keys.BACK_SPACE));
        Ex.Pass_ScreenShot("Sample Error Message");
        Assert.assertEquals(Sample_Error_Msg.getText().trim(), Constant.Sample_SID_ErrorMsg);
        screenshot();
        ScrollView(MC_Aliquot);
        jsclick(MC_Aliquot);
        send_keys(Aliquot_Submitter_ID, Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        send_keys(Aliquot_Submitter_ID, String.valueOf(Keys.BACK_SPACE));
        Ex.Pass_ScreenShot("Aliquot Error Message");
        Assert.assertEquals(Aliquot_Error_Msg.getText().trim(), Constant.Aliquot_SID_ErrorMsg);
        screenshot();
        ScrollView(MC_Aligned_reads);
        jsclick(MC_Aligned_reads);
        ScrollView(AR_Clearfields);
        jsclick(AR_Clearfields);
        normalwait(3000);
    }


    @Step("Read Group Error Message")
    public void ReadGroup_Error_Msg() throws InterruptedException {
        ScrollView(MC_ReadGroup);
        Thread.sleep(2000);
        clickableEle(MC_ReadGroup);
        jsclick(MC_ReadGroup);
        Assert.assertEquals(Read_Group_Error_Msg.getText().trim(), Constant.ReadGroup_ErrorMsg);
        screenshot();

    }

    public void sample_Error_Msg() throws InterruptedException {
        ScrollView(MC_Sample);
        clickableEle(MC_Sample);
        jsclick(MC_Sample);
        Assert.assertEquals(Sample_Error_Msg.getText().trim(), Constant.Sample_SID_ErrorMsg);
        Ex.Pass_ScreenShot("Verify Sample Error Message");
    }

    @Step("Aliquot Error Message")
    public void Aliquot_Error_Msg() throws InterruptedException {
        ScrollView(MC_Aliquot);
        clickableEle(MC_Aliquot);
        jsclick(MC_Aliquot);
        Assert.assertEquals(Aliquot_Error_Msg.getText().trim(), Constant.Aliquot_SID_ErrorMsg);
        Ex.Pass_ScreenShot("Aliquot Error Message");
        screenshot();
    }

    @Step("Aligned Read Error Message")
    public void AlignedRead_Error_Msg() throws InterruptedException {
        ScrollView(MC_Aligned_reads);
        Thread.sleep(2000);
        clickableEle(MC_Aligned_reads);
        jsclick(MC_Aligned_reads);
        Assert.assertEquals(Aligned_Read_Error_Msg.getText().trim(), Constant.AlignedRead_ErrorMsg);
        screenshot();
        Ex.Pass_ScreenShot("Aligned Read Error Message");
    }

    @Step("Validate Sample Aliquot With Out Data Error Message")
    public void SampAliq_Error_Msg_WithoutData(String Role) throws InterruptedException {
        normalwait(2000);
        Sel_AddSampAliquot(Role);
        clickableEle(MC_Sample);
        ScrollView(MC_Sample);
        jsclick(MC_Sample);
        jsclick(Save_btn);
        Assert.assertEquals(Sample_Error_Msg.getText().trim(), Constant.Sample_SID_ErrorMsg);
        screenshot();
        ScrollView(MC_Aliquot);
        normalwait(2000);
        clickableEle(MC_Aliquot);
        jsclick(MC_Aliquot);
        Assert.assertEquals(Aliquot_Error_Msg.getText().trim(), Constant.Aliquot_SID_ErrorMsg);
        screenshot();
    }

    @Step("Validate Sample Aliquot With  Data Error Message")
    public void SampAliq_Error_Msg_Data(String Sample_SubId, String Aliquot_SubId) throws InterruptedException {
        ScrollView(MC_Aliquot);
        Thread.sleep(2000);
        jsclick(MC_Aliquot);
        send_keys(Aliquot_Submitter_ID, Aliquot_SubId);
        Ele_Click(Save_btn);
        Thread.sleep(3000);
        clickableEle(MC_Sample);
        ScrollView(MC_Sample);
        jsclick(MC_Sample);
        Assert.assertEquals(Sample_Error_Msg.getText().trim(), Constant.Sample_SID_ErrorMsg);
        screenshot();
        send_keys(Sample_Submitter_ID, Sample_SubId);
        ScrollView(MC_Aliquot);
        Thread.sleep(2000);
        clickableEle(MC_Aliquot);
        jsclick(MC_Aliquot);
        send_keys(Aliquot_Submitter_ID, Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        send_keys(Aliquot_Submitter_ID, String.valueOf(Keys.BACK_SPACE));
        Assert.assertEquals(Aliquot_Error_Msg.getText().trim(), Constant.Aliquot_SID_ErrorMsg);
        screenshot();
    }

    public void Navigate_To_Project_AppIcon() {
        page_loader();
        jsclick(Apps_Icon);
        jsclick(Apps_Icon_Project);
    }

    public void Navigate_To_AppsCatalog() throws InterruptedException {
        page_loader();
        jsclick(Apps_Icon);
        page_loader();
        jsclick(Apps_Icon_Catalog);

    }

    @Step("Raw Sequence Mandatory DF Error Message")
    public void verify_MandatoryRawSeqDF_Msg(String Msg) {
        Ele_Click(Save_btn);
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg, Msg);
        screenshot();
        Ex.Pass_ScreenShot("Raw Sequence Mandatory DF Error Message");
        System.out.println("Mandatory DF Notification Displayed As Expected When Manifest Saved with no DF");
    }

    @Step("Raw Non Sequence DF Error Message")
    public void verify_MandatoryRawNonSeqDF_Msg(String Msg) {
        Ele_Click(Save_btn);
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg, Msg);
        screenshot();
        Ex.Pass_ScreenShot("Raw Non Sequence DF Error Message");
        System.out.println("Mandatory DF Notification Displayed As Expected When Manifest Saved with no DF");
    }

    public void saveMC() {
        Save_btn.click();
    }

    @Step("Validate Raw Sequence Mandatory Fields")
    public void Validate_RawSequencing_Mandatory_Fields() throws InterruptedException {
        Ele_Click(Save_btn);
        String MC_FileHexColor = hexcolor(MC_File);
        String MC_caseHexColor = hexcolor(MC_Case1);
        String MC_AliquotHexColor = hexcolor(MC_Aliquot);
        String MC_SampleHexColor = hexcolor(MC_Sample);
        String MC_ReadGroupHexColor = hexcolor(MC_ReadGroup);
        Assert.assertEquals(MC_FileHexColor, "#ff0000");
        Assert.assertEquals(MC_caseHexColor, "#ff0000");
        Assert.assertEquals(MC_AliquotHexColor, "#ff0000");
        Assert.assertEquals(MC_SampleHexColor, "#ff0000");
        Assert.assertEquals(MC_ReadGroupHexColor, "#ff0000");
        screenshot();
        Ex.Pass_ScreenShot("Validate Raw Sequence Mandatory Fields");
        System.out.println("Mandatory Fields File,case,Aliquot,Sample,ReadGroup are highlighted as expected for raw sequence");
    }

    @Step("Validate Raw Non Seqeunce Mandatory Fields")
    public void Validate_RawNonSequencing_Mandatory_Fields() throws InterruptedException {
        Ele_Click(Save_btn);
        String MC_FileHexColor = hexcolor(MC_File);
        String MC_caseHexColor = hexcolor(MC_Case1);
        String MC_AliquotHexColor = hexcolor(MC_Aliquot);
        String MC_SampleHexColor = hexcolor(MC_Sample);
        String MC_ReadGroupHexColor = hexcolor(MC_ReadGroup);
        Assert.assertEquals(MC_FileHexColor, "#ff0000");
        Assert.assertEquals(MC_caseHexColor, "#ff0000");
        Assert.assertEquals(MC_AliquotHexColor, "#ff0000");
        Assert.assertEquals(MC_SampleHexColor, "#ff0000");
        Assert.assertEquals(MC_ReadGroupHexColor, "#333333");
        screenshot();
        System.out.println("Mandatory Fields File,case,Aliquot,Sample are highlighted as expected for raw non sequence");
    }

    public void Edit_Manifest() {
        Ele_Click(Frst_Row_Icon);
        Ele_Click(Edit_Btn);
        Ex.Extent_Info("Edit Button Clicked");
        wait.until(ExpectedConditions.visibilityOf(File_Url_Input));
    }

    @Step("Manifest Download Success Message")
    public void Download_Json_Manifest() {
        Ele_Click(Frst_Row_Icon);
        Ele_Click(Download_Btn);
        normalwait(1000);
        jsclick(Download_Btn_Popup);
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg, Constant.Download_Msg);
        Ex.Pass_ScreenShot("Manifest Download Success Message");
        screenshot();
        normalwait(5000);
    }

    @Step("Manifest Download Success Message")
    public void Download_csv_Manifest() {
        Ele_Click(Frst_Row_Icon);
        Ele_Click(Download_Btn);
        normalwait(1000);
        jsclick(Csv_RadioBtn);
        jsclick(Download_Btn_Popup);
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg, Constant.Download_Msg);
        Ex.Pass_ScreenShot("Manifest Download Success Message");
        screenshot();
        normalwait(5000);
    }


    public void Clear_AlignedReads() throws InterruptedException {

        ScrollView(MC_Aligned_reads);
        jsclick(MC_Aligned_reads);
        ScrollView(AR_Clearfields);
        jsclick(AR_Clearfields);
        normalwait(2000);
        jsclick(AR_Clearfields);
        double_click(AR_header);
        Ex.Extent_Info("Aligned Reads Is Cleared");
    }

    public void Clear_Aligned_Reads() throws InterruptedException {
        ScrollView(MC_Aligned_reads);
        jsclick(MC_Aligned_reads);
        ScrollView(AR_Clearfields);
        jsclick(AR_Clearfields);
        jsclick(AR_Clearfields);
        Ex.Extent_Info("Aligned Reads Is Cleared");
    }

    @Step("Validate File Name ")
    public String fetch_Manifest_ID() {
        //Navigate_To_Project_AppIcon();
        Ele_Click(Cancel_btn);
        wait.until(ExpectedConditions.visibilityOf(Manifest_ID));
        String Manifestid = Manifest_id.getText().trim();
        screenshot();
        return Manifestid;

    }


    @Step("Validate File Name ")
    public void validate_ManifestTable(String FileName) {
        //Navigate_To_Project_AppIcon();
        Ele_Click(Cancel_btn);
        wait.until(ExpectedConditions.visibilityOf(FileName_Verify));
        Assert.assertEquals(FileName_Verify.getText(), FileName);
        System.out.println("File Name in Manifest Overview Table Is Matching");
        screenshot();
        Ex.Pass_ScreenShot(" File Name In Manifest Over View Table Is Matching");

    }

    @Step("Multiple Data File Error Message")
    public void verify_MultipleDF_ErrorMsg(String Msg) {
        Ele_Click(Save_btn);
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg, Msg);
        screenshot();
        Ex.Pass_ScreenShot("Error Notification Displayed As Expected When Mulitple DataFiles are Saved");
    }

    @Step("Aligned Reads Index Input ")
    public void Aligned_reads_index(String AlignedReadIndex) throws InterruptedException {
        String[] Aligned_Read_Index = AlignedReadIndex.split(",");
        ScrollView(MC_Aligned_reads_index);
        jsclick(MC_Aligned_reads_index);
        SelectDrpDwnValue(ARI_DataFormatDrpDwn, Aligned_Read_Index[0]);
        SelectDrpDwnValue(ARI_DataTypeDrpDwn, Aligned_Read_Index[1]);
        SelectDrpDwnValue(ARI_DataCategoryDrpDwn, Aligned_Read_Index[2]);
        screenshot();
        Ex.Pass_ScreenShot("Aligned Reads Index Input :");
    }

    public void verify_Download_json(String AlignedReadIndex, HashMap<String, String> Json_Values) {
        HashMap<String, String> UI_Values = new HashMap<>();
        String[] Aligned_Read_Index = AlignedReadIndex.split(",");
        UI_Values.put("data_format", Aligned_Read_Index[0]);
        UI_Values.put("data_type", Aligned_Read_Index[1]);
        UI_Values.put("data_category", Aligned_Read_Index[2]);
        Assert.assertEquals(UI_Values, Json_Values);
        Ex.Extent_Info("UI Aligned Read Index Input  Values: " + MarkupHelper.createUnorderedList(UI_Values).getMarkup());
        Ex.Extent_Info("Json Aligned Read Index Input  Values: " + MarkupHelper.createUnorderedList(Json_Values).getMarkup());
        Ex.Extent_Pass("Edited Values Are Reflecting On Json");
    }

    @Step("Submit For Ingestion Success Message")
    public void Submit_Ingestion_SuccessMsg(String Msg) throws InterruptedException {
        normalwait(3000);
        page_loader();
        jsclick(Submit_Fr_IngesBtn);
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg, Msg);
        System.out.println("Manifest has been submitted for ingestion successfully");
        screenshot();
        Ex.Pass_ScreenShot("Ingestion Success Message");
        page_loader();
    }

    @Step("Validate Update MetaData Attributes")
    public void verify_update_metadata_attributes() {
        Assert.assertTrue(Save_btn.isEnabled());
        Assert.assertTrue(Cancel_btn.isEnabled());
        String status = Submit_Fr_IngesBtnP_status.getAttribute("disabled");
        Assert.assertEquals("true", status);
        screenshot();
        ScrollView(MC_File);
        jsclick(MC_File);
        Assert.assertFalse(File_Url_Input.isEnabled());
        Assert.assertFalse(File_id.isEnabled());
        Assert.assertFalse(File_size.isEnabled());
        screenshot();
        Assert.assertFalse(Projectid_Input.isEnabled());
        Assert.assertFalse(Project_Name.isEnabled());
        screenshot();
        Ex.Pass_ScreenShot("Update Meta Data Fields Validated SuccessFully");
    }

    @Step("Manifest Cancel Success Message")
    public void update_Md_CancelBtn_Verify() {
        Ele_Click(Cancel_btn);
        Assert.assertTrue(Cancel_No_Msg.isDisplayed());
        jsclick(No_Btn);
        MC_File.isDisplayed();
        Ele_Click(Cancel_btn);
        Ele_Click(Yes_Btn);
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Cancel_Msg = Notification_Msg.getText();
        screenshot();
        Ex.Pass_ScreenShot("Manifest Cancel Success Message");
        Assert.assertEquals(Cancel_Msg, "Manifest has been cancelled successfully", "Manifest has been cancelled successfully");

    }


    @Step("Manifest Save Success Message")
    public void ManifestSave() throws InterruptedException {

        jsclick(Save_btn);
        normalwait(3000);

    }

    @Step("Manifest Save Success Message")
    public void ManifestSave_SuccessMsg(String Msg) throws InterruptedException {
        normalwait(1000);
        jsclick(Save_btn);
        normalwait(1000);
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg, Msg);
        screenshot();
        Ex.Pass_ScreenShot("Manifest Save Success Message");
        clickableEle(Submit_Fr_IngesBtn);
        page_loader();

    }

    public void navigate_to_slideimage() {
        wait.until(ExpectedConditions.visibilityOf(MC_SlideImage));
        ScrollView(MC_SlideImage);
        jsclick(MC_SlideImage);
    }

    public void DF_Slide_Image(String SlideImage) throws InterruptedException {
        HashMap<String, String> map = new HashMap<>();
        String[] Slide_Image = SlideImage.split(",");
        String SI_DataFormat = Slide_Image[0];
        String SI_DataCategory = Slide_Image[1];
        String SI_DataType = Slide_Image[2];
        String SI_Exp_Strategy = Slide_Image[3];
        SelectDrpDwnValue(SI_dataformatDrpDwn, SI_DataFormat);
        jsclick(SI_datacategoryDrpDwn);
        jsclick(SI_Biospecimen);
        SelectDrpDwnValue(SI_datatypeDrpDwn, SI_DataType);
        SelectDrpDwnValue(SI_ExpStrategyDrpDwn, SI_Exp_Strategy);
        map.put("SI_DataFormat", Slide_Image[0]);
        map.put("SI_DataCategory", Slide_Image[1]);
        map.put("SI_DataType", Slide_Image[2]);
        map.put("SI_Exp_Strategy", Slide_Image[3]);
        Ex.Info_ScreenShot("Slide Image Input  Values: " + MarkupHelper.createUnorderedList(map).getMarkup());
        double_click(SI_header);

    }

    @Step("Aligned Reads Input")
    public void DF_Aligned_reads(String AlignedReads) throws InterruptedException {
        HashMap<String, String> map = new HashMap<>();
        String[] Aligned_Reads = AlignedReads.split(",");
        ScrollView(MC_Aligned_reads);
        jsclick(MC_Aligned_reads);
        SelectDrpDwnValue(AR_DataFormatDrpDwn, Aligned_Reads[0]);
        SelectDrpDwnValue(AR_DataCategoryDrpDwn, Aligned_Reads[1]);
        SelectDrpDwnValue(AR_DataTypeDrpDwn, Aligned_Reads[2]);
        SelectDrpDwnValue(AR_ExStrategyDrpDwn, Aligned_Reads[3]);
        screenshot();
        map.put("AR DataFormat", Aligned_Reads[0]);
        map.put("AR Data Category", Aligned_Reads[1]);
        map.put("AR Data Type", Aligned_Reads[2]);
        map.put("AR Exp Strategy", Aligned_Reads[3]);
        Ex.Info_ScreenShot("Aligned Reads Input  Values: " + MarkupHelper.createUnorderedList(map).getMarkup());
    }
    /*@Step("Aligned Reads Input")
    public void DF_Aligned_reads(String Dataformat,String DataCategory,String DataType
    ,String ExStrategy) throws InterruptedException {
        ScrollView(MC_Aligned_reads);
        Thread.sleep(1000);
        jsclick(MC_Aligned_reads);
        SelectDrpDwnValue(AR_DataFormatDrpDwn,Dataformat);
        SelectDrpDwnValue(AR_DataCategoryDrpDwn,DataCategory);
        SelectDrpDwnValue(AR_DataTypeDrpDwn,DataType);
        SelectDrpDwnValue(AR_ExStrategyDrpDwn,ExStrategy);
        screenshot();
    }*/


    public void DF_SlideImage(String Dataformat, String DataType
            , String ExStrategy) throws InterruptedException {
        ScrollView(MC_Slide_Image);
        Thread.sleep(1000);
        jsclick(MC_Slide_Image);
        SelectDrpDwnValue(SI_DataFormat, Dataformat);
        jsclick(SI_DataCategory);
        Thread.sleep(2000);
        WebElement ele = driver.findElement(By.xpath("(//span[contains(text(),'Biospecimen')])[2]"));
        jsclick(ele);
        Thread.sleep(1000);
        SelectDrpDwnValue(SI_DataType, DataType);
        SelectDrpDwnValue(SI_ExpStrategy, ExStrategy);
    }


    public int select_Proj_Overview() {
        int rowCount = Proj_Overview_Row.size();
        if (rowCount < 2) {
            driver.findElement(By.xpath("//mat-table[1]/mat-row[1]/mat-cell[6]/li[1]/mat-icon[1]")).click();
        } else {
            driver.findElement(By.xpath("//mat-table[1]/mat-row[1]/mat-cell[6]/li[1]/mat-icon[1]")).click();
        }

        return rowCount;
    }

    public void Navigate_To_ProjecAccess() {
        Ele_Click(Project_Access);
        Element_ToBe_Clickable(Active_Users);
    }

    @Step("Select Mulitple Users")
    public void verify_MultiUserSelectCB(String AssocUser) throws InterruptedException {
        String[] Users = AssocUser.split(",");
        jsclick(Associate_Users);
        jsclick(Associate_Users_DrpDwn);
        for (String User : Users) {
            WebElement Element = driver.findElement(By.xpath("//span[normalize-space()='" + User + "']"));
            Ele_Click(Element);
        }
        Ele_Click(AssocUsers_SelBtn);
        String Updated = MultipleUsers.getText().replace(" ", "");
        Assert.assertEquals(Updated, AssocUser);
        System.out.println("Multiple Users Check Box is Working As Expected");
        Ele_Click(Cancel_btn);
    }

    @Step("Select Associate Users")
    public void Select_Associate_Users(String AssocUser, String Role) throws InterruptedException {
        jsclick(Associate_Users);
        Ele_Click(Associate_Users_DrpDwn);
        WebElement User = driver.findElement(By.xpath("//span[normalize-space()='" + AssocUser + "']"));
        Ele_Click(User);
        Ele_Click(AssocUsers_SelBtn);
        String UserName = AssocUsers_Name.getText();
        Ele_Click(Role_DrpDwn);
        normalwait(2000);
        WebElement User_Role = driver.findElement(By.xpath("//span[contains(text(),'" + Role + "')]"));
        Ele_Click(User_Role);
        normalwait(2000);
        Ex.Pass_ScreenShot("Associate User");
        jsclick(Save_btn);

    }

    @Step("Associate Users Success Message")
    public void verify_AssocUsers_Add_SuccessMsg(String Msg) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Assert.assertEquals(Success_Msg, Msg);
        screenshot();
        Ex.Pass_ScreenShot("Users Add Toast Success Message");
        normalwait(3000);
    }

    public void verify_New_User(int OldRowCount, String UserName1) throws InterruptedException {
        Thread.sleep(2000);
        int Newrowcount = Active_Users_Row.size();
        if (Newrowcount > OldRowCount) {
            String User2 = New_Added_User.getText();
            Assert.assertEquals(User2, UserName1, "New User Added As Expected");
        } else {
            Assert.assertFalse(true, "New User Not Added As Expected");
        }
    }

    @Step("Verify New Added User")
    public void verify_new_user(String UserName) {
        WebElement ele = driver.findElement(By.xpath("//mat-cell[contains(text(),'" + UserName + "')]"));
        if (ele.isDisplayed()) {
            Assert.assertTrue(true);
            screenshot();
            System.out.println("New User " + UserName + " is added In Active users ");
        } else {
            System.out.println("New User " + UserName + " is not added In Active users ");
            Assert.assertTrue(false);
        }
    }

    public void Delete_New_User(String UserName) throws InterruptedException {
        normalwait(2000);
        WebElement Ele = driver.findElement(By.xpath("//mat-cell[contains(text(),'" + UserName + "')]/..//div[@class='mat-radio-outer-circle']"));
        ScrollView(Ele);
        jsclick(Ele);
        Ele_Click(Delete_Btn);
        Element_ToBe_Clickable(Cnfrm_Action);
        Assert.assertEquals(Cnfrm_Action.getText(), "Are you sure you want to delete this user from the project?", "confirmation text is also present on model");
        Assert.assertEquals(Yes_Btn.getText(), "Yes");
        Assert.assertEquals(No_Btn.getText(), "No");
        Ele_Click(Yes_Btn);
    }

    @Step("Delete Success Message")
    public void verify_AssocUsers_Delete_SuccessMsg(String Msg) throws IOException {
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Delete_Msg = Notification_Msg.getText();
        Assert.assertEquals(Delete_Msg, Msg);
        screenshot();
        Ex.Pass_ScreenShot("Users Delete Toast Message");
        jsclick(Project_Access_BG);
    }

    public void verify_PMAccess_TechnicalSettings(String MailId) throws InterruptedException {
        if (MailId.equals("hareesitpo@gmail.com")) {
            int row_count = Proj_Overview_Row.size();
            if (row_count < 2) {
                driver.findElement(By.xpath("//mat-table[1]/mat-row[1]/mat-cell[6]/li[1]/mat-icon[1]")).click();
                Thread.sleep(2000);
                String Verifytext = Technical_Settings.getText();
                System.out.println(Verifytext);
                Assert.assertFalse(Verifytext.contains("Technical settings"));
                System.out.println("Technical settings Option not present for Privacy officer--" + MailId + ".");
            } else {
                driver.findElement(By.xpath("//mat-table[1]/mat-row[2]/mat-cell[6]/li[1]/mat-icon[1]")).click();
                Thread.sleep(2000);
                Assert.assertFalse(Technical_Settings.isDisplayed());
                System.out.println("Technical settings Option not present for Privacy officer--" + MailId + ".");
            }


        } else if (MailId.equals("peniyathuroy@gmail.com")) {
            Assert.assertTrue(No_Data_Found.isDisplayed());
            System.out.println("Project not displayed for Unassigned user--" + MailId + ".");
        }
    }

    @Step("Verify Data Ingestion Management Option for Du1,PO,UA Users")
        public void verify_DataMgmt_PO_DU1_UA(String Role){
            int count =Data_Ingestion_Mgmt_Count.size();
            Assert.assertEquals(count,0);
            screenshot();
            Ex.Pass_ScreenShot("No Data Ingestion Managemnet option displayed for "+Role+" As Expected");
        }
    public void Navigate_To_DataMgmtIngest() {

        Ele_Click(Data_Ingestion_Mgmt);

        normalwait(2000);

    }

    @FindBy(xpath = "//h1[contains(text(),' Upload Manifest ')]")
    public WebElement UploadManifest_Header;

    @FindBy(xpath = "//span[contains(text(),'Upload')]")
    public WebElement Upload_Btn;

    @FindBy(xpath = "//mat-icon[text()='close']")
    public WebElement Close_Icon;

    @FindBy(xpath = "//mat-label[text()='File Name *']")
    public WebElement File_Name_Header;

    @FindBy(xpath = "//span[contains(text(),'Please choose csv file to upload')]")
    public WebElement Csv_EmptyFileErrorMsg;

    @FindBy(xpath = "//mat-icon[text()='drive_folder_upload']")
    public WebElement Csv_UploadIcon;

    @FindBy(xpath = "//span[text()=' Only .CSV file format allowed ']")
    public WebElement Csv_InvalidFileFormatErrorMsg;

    @FindBy(xpath = "//span[text()=' CSV file cannot be more than 10MB size ']")
    public WebElement teMb_Csv_ErrorMsg;

    @Step("Ivalid Size  Error Message Is Displayed (More than 10 mb)")
    public void verify_csv_sizefile(String filepath, String filename) throws AWTException {
        upload_csvfile_10Mb(filepath, filename);
        normalwait(25000);
        jsclick(Upload_Btn);
        Assert.assertTrue(teMb_Csv_ErrorMsg.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Ivalid Size  Error Message Is Displayed (More than 10 mb)");
        jsclick(Cancel_btn);
    }

    @Step("Validate Manifest OverView  Values After Csv Upload")
    public String verify_ManifestOverviewData(String FileName, String User_Name) {
        WebElement Status = driver.findElement(By.xpath("(//mat-cell//span[text()='" + FileName + "']/..//following-sibling::mat-cell)[1]"));
        Assert.assertEquals(Status.getText(), "Submitted for Ingestion");
        WebElement Username = driver.findElement(By.xpath("(//mat-cell//span[text()='" + FileName + "']/..//following-sibling::mat-cell)[2]//span"));
        Assert.assertEquals(Username.getText(), User_Name);
        WebElement Manifest_Id =driver.findElement(By.xpath("((//mat-cell//span[text()='" + FileName + "']/..//following-sibling::mat-cell)[1]/..//mat-cell)[1]"));
        String Manifest_ID =Manifest_Id.getText();
        screenshot();
        Ex.Pass_ScreenShot("Validate Manifest OverView  Values After Csv Upload");
        return Manifest_ID;
    }

    @Step("Ivalid File Format Error Message Is Displayed")
    public void verify_csvinvalidformatfile(String filepath, String filename) throws AWTException {
        upload_csvfile(filepath, filename);
        Assert.assertTrue(Csv_InvalidFileFormatErrorMsg.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Invalid File Format Error Message Is Displayed");
        jsclick(Cancel_btn);
    }

    @Step("Csv Upload Success Message")
    public void verify_csvupload_successmsg(){
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        screenshot();
        Ex.Pass_ScreenShot("Csv Upload Success Message");
        Assert.assertEquals(Success_Msg,Constant.CsvUpload_SuccessMsg);
        loader_new();

    }

    public void loader_new() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loading-user-spinner ng-star-inserted']")));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='loading-user-spinner ng-star-inserted']")));
    }


    public void upload_csvfile_10Mb(String filepath, String filename) throws AWTException {
        page_loader();
        jsclick(Manifest_DrpDwn);
        jsclick(Upload_Manifest_CSV);
        normalwait(5000);
        Ele_Click(Csv_UploadIcon);
        normalwait(3000);
        String fp = filepath + filename;
        StringSelection selection = new StringSelection(fp);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        normalwait(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        normalwait(1000);
    }

    public void upload_csvfile_1(String filepath, String filename) throws AWTException {
        page_loader();
        jsclick(Manifest_DrpDwn);
        jsclick(Upload_Manifest_CSV);
        normalwait(10000);
        //String fp = filepath + filename;
        System.out.println("C:\\Users\\dhinusan.sivalingam\\Redcap_Auto\\redcap_ui_automation\\Downloads\\sjzuvlgp7mxypgn1dnnewe.csv");
        send_keys(Upload_Input_Box,"C:\\Users\\dhinusan.sivalingam\\Redcap_Auto\\redcap_ui_automation\\Downloads\\sjzuvlgp7mxypgn1dnnewe.csv");
        normalwait(1000);
        jsclick(Upload_Btn);


    }

    public void upload_csvfile(String filepath, String filename) throws AWTException {
        page_loader();
        jsclick(Manifest_DrpDwn);
        jsclick(Upload_Manifest_CSV);
        normalwait(10000);
        ActionClick(Csv_UploadIcon);
        normalwait(3000);
        String fp = filepath + filename;
        StringSelection selection = new StringSelection(fp);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        normalwait(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        normalwait(1000);
        jsclick(Upload_Btn);


    }

    @Step("Verify Csv Empty File Upload Error Notification")
    public void verify_csvUpload_EmptyFile() {
        jsclick(Upload_Btn);
        Assert.assertTrue(Csv_EmptyFileErrorMsg.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Verify Csv Empty File Upload Error Notification");
        jsclick(Cancel_btn);
    }

    @Step("Verify Upload Manifest CSV Attributes")
    public void verify_uploadpopup_attributes() {
        Assert.assertTrue(UploadManifest_Header.isDisplayed());
        Assert.assertTrue(Cancel_btn.isDisplayed());
        Assert.assertTrue(Close_Icon.isDisplayed());
        Assert.assertTrue(File_Name_Header.isDisplayed());
        Assert.assertTrue(Upload_Btn.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("Verify Upload Manifest CSV Attributes");
    }

    @Step("Navigate to Upload Manifest(CSV)")
    public void Navigate_To_UploadManifest_CSV() throws InterruptedException {
        page_loader();
        jsclick(Manifest_DrpDwn);
        jsclick(Upload_Manifest_CSV);
        screenshot();
        Ex.Extent_Info("Navigated To Upload Manifest(CSV)");
    }

    @FindBy(xpath = "//div[normalize-space(text())='METADATA']")
    public WebElement MetaData_RadioBtn;

    @Step("Navigate To Create Manifest")
    public void Navigate_To_CreateManifest(){
        normalwait(2000);
        jsclick(Manifest_DrpDwn);
        normalwait(2000);
        jsclick(Create_Manifest);
    }
    @Step("Navigate to Create Meta Data")
    public void Navigate_To_CreateMetaData() throws InterruptedException {
        normalwait(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Manifest ']")));
        jsclick(Manifest_DrpDwn);
        normalwait(2000);
        ScrollView_Action(Create_Metadata);
        jsclick(Create_Metadata);
        normalwait(2000);
        jsclick(Select_VersionDrpDWn);
        normalwait(1000);
        jsclick(Select_Version_1);
        screenshot();
        Ele_Click(Proceed_Btn);
        normalwait(3000);
        screenshot();
        Ex.Extent_Info("Navigated To Create MetaData");
    }


    @FindBy(xpath = "//div[normalize-space(text())='REFERENCE']")
    public WebElement Reference_RadioBtn;

    @Step("Navigate to Manifest Reference")
    public void verify_Manifest_Reference() throws InterruptedException {
        normalwait(1000);
        ScrollView_Action(Reference_RadioBtn);
        jsclick(Reference_RadioBtn);
        normalwait(2000);
        jsclick(Select_VersionDrpDWn);
        jsclick(Select_Version_1);
        screenshot();
        Ele_Click(Proceed_Btn);
        normalwait(2000);
        wait.until(ExpectedConditions.visibilityOf(File_Url_Input));
        Assert.assertTrue(File_Url_Input.isDisplayed());
        screenshot();
        System.out.println("File Name/URL is present on table");
        Ex.Extent_Info("Navigated To Reference");
    }

    @FindBy(xpath = "//div[contains(text(),' RAW-SEQ ')]")
    public WebElement Raw_SeqRadioBtn;

    @FindBy(xpath = "//h5[text()='Select Version']/..//div//div//div")
    public WebElement Select_VersionDrpDWn;

    @FindBy(xpath = "//span [normalize-space(text())='1.0']")
    public WebElement Select_Version_1;
    @Step("Navigate To Raw Sequence")
    public void Navigate_To_RawSequence() throws InterruptedException {
        normalwait(1000);
        wait.until(ExpectedConditions.visibilityOf(Raw_SeqRadioBtn));
        ScrollView_Action(Raw_SeqRadioBtn);
        Ele_Click(Raw_SeqRadioBtn);
        normalwait(2000);
        ScrollView_Action(Select_VersionDrpDWn);
        jsclick(Select_VersionDrpDWn);
        jsclick(Select_Version_1);
        screenshot();
        Ele_Click(Proceed_Btn);
        normalwait(2000);
        wait.until(ExpectedConditions.visibilityOf(File_Url_Input));
        Assert.assertTrue(File_Url_Input.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("User Navigated To Raw Sequence");
    }

    @FindBy(xpath = "//div[normalize-space(text())='RAW-NONSEQ']")
    public WebElement Raw_NonSeqRadioBtn;

    @Step("Navigate To Manifest Raw Non Sequence")
    public void verify_ManifestPage_RawNonSequence() throws InterruptedException {
        page_loader();
        normalwait(1000);
        jsclick(Manifest_DrpDwn);
        normalwait(2000);
        jsclick(Create_Manifest);
        normalwait(2000);
        ScrollView_Action(Raw_NonSeqRadioBtn);
        Ele_Click(Raw_NonSeqRadioBtn);
        normalwait(3000);
        jsclick(Select_VersionDrpDWn);
        jsclick(Select_Version_1);
        screenshot();
        Ele_Click(Proceed_Btn);
        normalwait(2000);
        wait.until(ExpectedConditions.visibilityOf(File_Url_Input));
        screenshot();
        Ex.Pass_ScreenShot("User Navigated To Raw Non Sequence");
    }


    @Step("Navigate To Manifest Raw Non Sequence")
    public void Navigate_To_RawNonSequence() throws InterruptedException {
        ScrollView_Action(Raw_NonSeqRadioBtn);
        Ele_Click(Raw_NonSeqRadioBtn);
        normalwait(3000);
        jsclick(Select_VersionDrpDWn);
        jsclick(Select_Version_1);
        screenshot();
        Ele_Click(Proceed_Btn);
        wait.until(ExpectedConditions.visibilityOf(File_Url_Input));
        Assert.assertTrue(File_Url_Input.isDisplayed());
        screenshot();
        Ex.Pass_ScreenShot("User Navigated To Raw Non Sequence");
    }

    @Step("Validate Incorrect File Names")
    public void verify_IncorrectFileName(String FileName) throws InterruptedException {
        String[] Files = FileName.split(",");
        for (int i = 0; i < Files.length; i++) {
            String F = Files[i];
            send_keys(File_Url_Input, Files[i]);
            normalwait(1000);
            Assert.assertEquals(File_Error_Msg.getText(), "Please enter a valid https URL");
            Ex.Pass_ScreenShot("Validate Incorrect File Names");
            screenshot();
            File_Url_Input.clear();
        }
    }

    @Step("Validate Correct File Names")
    public void Save_Valid_File(String File) throws InterruptedException {
        send_keys(File_Url_Input, File);
        Ele_Click(Save_btn);
        screenshot();
        Ex.Pass_ScreenShot("Validate Correct File Names");
    }

    @Step("Case Input")
    public void MCCase_Input(String Case_SubId) throws InterruptedException {
        try {
            ScrollView(MC_Case1);
            Element_ToBe_Clickable(MC_Case1);
            jsclick(MC_Case1);
            Element_ToBe_Clickable(Case_Submitter_ID);
            Case_Submitter_ID.clear();
            send_keys(Case_Submitter_ID, Case_SubId);
            Ex.Info_ScreenShot("Case Submitter Id Entered :" + Case_SubId);
            screenshot();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void MCSample_AllInputs(String Sample_SubId) throws InterruptedException {
        ScrollView(MC_Sample);
        Thread.sleep(3000);
        if (!Sample_SubId.isEmpty()) {
            jsclick(MC_Sample);
            send_keys(Sample_Submitter_ID, Sample_SubId);
        }
    }

    @FindBy(xpath = "//mat-label[contains(text(),'Tumor descriptor')]/../../..//mat-select")
    public WebElement Tumor_DescriptorDrpDwn;


    @FindBy(xpath = "//mat-label[contains(text(),'Composition')]/../../..//mat-select")
    public WebElement Composition_DrpDwn;

    @FindBy(xpath = "//mat-label[contains(text(),'Biospecimen anatomic site')]/../../..//mat-select")
    public WebElement BAS_DrpDwn;

    @FindBy(xpath = "//mat-label[contains(text(),'Biospecimen laterality')]/../../..//mat-select")
    public WebElement Bio_laterality_DrpDwn;

    @FindBy(xpath = "(//mat-label[contains(text(),'Analyte type')]/../..)[1]/../../..//mat-select")
    public WebElement Aliq_Analytetype_DrpDwn;

    @Step("Case Input  Values:")
    public void MCCase_MultipleInputs(String Case_SubId, String Primarysite, String Diseasetype, String Consenttype) throws InterruptedException {
        HashMap<String, String> map = new HashMap<>();
        ScrollView(MC_Case);
        jsclick(MC_Case1);
        Case_Submitter_ID.clear();
        send_keys(Case_Submitter_ID, Case_SubId);
        SelectDrpDwnValue(CASE_Primary_site, Primarysite);
        SelectDrpDwnValue(CASE_Disease_type, Diseasetype);
        SelectDrpDwnValue(CASE_Consent_type, Consenttype);
        map.put("Case Submitter ID", Case_SubId);
        map.put("Case Primary Site", Primarysite);
        map.put("Case Disease Type", Diseasetype);
        map.put("Case Consent Type", Consenttype);
        screenshot();
        Ex.Info_ScreenShot("Case Input  Values: " + MarkupHelper.createUnorderedList(map).getMarkup());

    }

    @Step("Sample Multiple Inputs")
    public void MCSample_MultipleInputs(String Sample_SubId, String Tumor_Descriptor, String Composition, String BAS, String Bio_laterality) throws InterruptedException {
        HashMap<String, String> map = new HashMap<>();
        wait.until(ExpectedConditions.visibilityOf(MC_Sample));
        ScrollView(MC_Sample);
        jsclick(MC_Sample);
        if (!Sample_SubId.isEmpty()) {
            Sample_Submitter_ID.clear();
            send_keys(Sample_Submitter_ID, Sample_SubId);
        }
        if (!Tumor_Descriptor.isEmpty()) {
            SelectDrpDwnValue(Tumor_DescriptorDrpDwn, Tumor_Descriptor);
        }

        if (!Composition.isEmpty()) {
            SelectDrpDwnValue(Composition_DrpDwn, Composition);
        }
        if (!Bio_laterality.isEmpty()) {
            SelectDrpDwnValue(Bio_laterality_DrpDwn, Bio_laterality);
        }
        if (!BAS.isEmpty()) {
            SelectDrpDwnValue(BAS_DrpDwn, BAS);

        }
        screenshot();
        map.put("Sample Submitter ID", Sample_SubId);
        map.put("Sample Tumour Descriptor", Tumor_Descriptor);
        map.put("Sample Composition", Composition);
        map.put("Sample Bio Laterality", Bio_laterality);
        map.put("Sample Bio Anatomic site", BAS);
        Ex.Info_ScreenShot("Sample Input  Values: " + MarkupHelper.createUnorderedList(map).getMarkup());

    }

    @FindBy(xpath = "//mat-label[contains(text(),'Aliquot quantity')]/../../../input")
    public WebElement Aliquot_Quantity;

    @FindBy(xpath = "//mat-label[contains(text(),'Analytes')]/../../../input")
    public WebElement Aliq_Analytes;

    @FindBy(xpath = "(//mat-label[contains(text(),'Amount')]/../../../input)[1]")
    public WebElement Aliquot_Amount_Input;

    @FindBy(xpath = "//mat-label[contains(text(),'Source center')]/../../../input")
    public WebElement Source_center_Input;

    @FindBy(xpath = "(//mat-label[contains(text(),'Concentration')]/../../../input)[1]")
    public WebElement Aliq_Concentration_Input;

    @FindBy(xpath = "//h3[text()='Read group']/..//following-sibling::mat-label[text()=' Platform ']/../../..//mat-select")
    public WebElement RG_PlatformDD;

    @FindBy(xpath = "//mat-label[contains(text(),'Read group name')]/../../../input")
    public WebElement RG_Name;

    @FindBy(xpath = "//label[contains(text(),'Is paired end')]/..//div[contains(text(),'Yes')]")
    public WebElement RG_Yes_RadioBtn;

    @FindBy(xpath = "//mat-label[contains(text(),'Library strategy')]/../../..//mat-select")
    public WebElement RG_Library_Strategy;

    @FindBy(xpath = "//h3[text()='Read group']/..//following-sibling::mat-label[contains(text(),'Sequencing date')]/../../..//mat-datepicker-toggle")
    public WebElement RG_DatePicker;

    @FindBy(xpath = "//h3[text()='Sample']/..//following-sibling::mat-label[contains(text(),'Date of collection')]/../../..//mat-datepicker-toggle")
    public WebElement Sample_DatePicker;

    @FindBy(xpath = "//h3[text()='Slide image']/..//following-sibling::mat-label[contains(text(),'Imaging date')]/../../..//mat-datepicker-toggle")
    public WebElement SI_DatePicker;

    @FindBy(xpath = "//span[contains(text(),'PDF')]")
    public WebElement CS_PDF;

    @FindBy(xpath = "//span[contains(text(),'CT scan')]")
    public WebElement MT_CTScan;

    @FindBy(xpath = "//span[contains(text(),'Cytology')]")
    public WebElement MT_Cytology;

    @FindBy(xpath = "//span[contains(text(),'Other molecular tests')]")
    public WebElement MT_tests;

    @FindBy(xpath = "//span[contains(text(),'PCR')]")
    public WebElement MT_PCR;

    @FindBy(xpath = "//span[contains(text(),'Sequencing')]")
    public WebElement MT_Sequencing;

    @FindBy(xpath = "//h3[text()='Read group']/..//following-sibling::button//span[text()='Clear fields']")
    public WebElement RG_Clearfields;
    @FindBy(xpath = "//h3[text()='Clinical supplement']/..//following-sibling::button//span[text()='Clear fields']")
    public WebElement CS_Clearfields;
    @FindBy(xpath = "//h3[text()='Molecular test']/..//following-sibling::button//span[text()='Clear fields']")
    public WebElement MT_Clearfields;

    public void verify_CS_MT() {
        ScrollView(MC_ClinicalSupplement);
        jsclick(MC_ClinicalSupplement);
        ScrollView(CS_DataformatDrpDwn);
        jsclick(CS_DataformatDrpDwn);
        normalwait(1000);
        Assert.assertTrue(CS_PDF.isDisplayed());
        ScrollView(CS_Clearfields);
        double_click(CS_Clearfields);
        ScrollView(MC_Moleculartest);
        jsclick(MC_Moleculartest);
        ScrollView(MT_DatacategoryDrpDwn);
        jsclick(MT_DatacategoryDrpDwn);
        normalwait(1000);
        Ex.Pass_ScreenShot("Molecular test Data Category Values");
        Assert.assertTrue(MT_CTScan.isDisplayed());
        Assert.assertTrue(MT_Cytology.isDisplayed());
        Assert.assertTrue(MT_tests.isDisplayed());
        Assert.assertTrue(MT_PCR.isDisplayed());
        Assert.assertTrue(MT_Sequencing.isDisplayed());
        double_click(MT_header);

    }


    public void verify_date_picker() {
        ScrollView(MC_ReadGroup);
        jsclick(MC_ReadGroup);
        ScrollView_Action(RG_DatePicker);
        Ex.Pass_ScreenShot("Read Group Date Picker Displayed");
        Assert.assertTrue(RG_DatePicker.isDisplayed());
        ScrollView(MC_Sample);
        jsclick(MC_Sample);
        ScrollView_Action(Sample_DatePicker);
        Ex.Pass_ScreenShot("Sample  Date Picker Displayed");
        Assert.assertTrue(Sample_DatePicker.isDisplayed());
        ScrollView(MC_SlideImage);
        jsclick(MC_SlideImage);
        ScrollView_Action(SI_DatePicker);
        Ex.Pass_ScreenShot("Slide Image  Date Picker Displayed");
        Assert.assertTrue(SI_DatePicker.isDisplayed());

    }

    public void verify_RG_TargetcaptureKit(String CaptureKit) {
        ScrollView(MC_ReadGroup);
        jsclick(MC_ReadGroup);
        String[] RG_CaptureKit = CaptureKit.split(",");
        ScrollView(RG_TargetDrpDwn);
        jsclick(RG_TargetDrpDwn);
        for (String RG_Target : RG_CaptureKit) {
            dropdown_present(RG_Target);
        }
        Ex.Pass_ScreenShot("Read Group Capture Kit Values Displayed As Expected");
        double_click(RG_Clearfields);
    }

    public void loader() {
        int count = Loader_Circle.size();

        do {
            count = Loader_Circle.size();
            normalwait(1000);

        }
        while (count > 0);


    }

    @Step("Read Group Input")
    public void MC_ReadGroup_Inputs(String ReadGroup) throws InterruptedException {
        HashMap<String, String> map = new HashMap<>();
        String[] Read_Group = ReadGroup.split(",");
        ScrollView(MC_ReadGroup);
        jsclick(MC_ReadGroup);
        SelectDrpDwnValue(RG_PlatformDD, Read_Group[0]);
        RG_Name.clear();
        send_keys(RG_Name, Read_Group[1]);
        Ele_Click(RG_Yes_RadioBtn);
        SelectDrpDwnValue(RG_Library_Strategy, Read_Group[2]);
        map.put("RG PlatForm", Read_Group[0]);
        map.put("RG Name", Read_Group[1]);
        map.put("RG Library Strategy", Read_Group[2]);
        screenshot();
        Ex.Info_ScreenShot("Read Group  Input  Values: " + MarkupHelper.createUnorderedList(map).getMarkup());
    }

    @Step("Aliquot Multiple Inputs")
    public void MCAliquot_MultipleInputs(String Aliq_SubId, String Aliq_Amount, String Source_center, String Analyte_Type, String Concentration) throws InterruptedException {
        HashMap<String, String> map = new HashMap<>();
        ScrollView(MC_Aliquot);
        jsclick(MC_Aliquot);
        if (!Aliq_SubId.isEmpty()) {
            Aliquot_Submitter_ID.clear();
            send_keys(Aliquot_Submitter_ID, Aliq_SubId);
        }
        if (!Aliq_Amount.isEmpty()) {
            Aliquot_Amount_Input.clear();
            send_keys(Aliquot_Amount_Input, Aliq_Amount);
        }

        if (!Source_center.isEmpty()) {
            Source_center_Input.clear();
            send_keys(Source_center_Input, Source_center);
        }
        if (!Analyte_Type.isEmpty()) {

            SelectDrpDwnValue(Aliq_Analytetype_DrpDwn, Analyte_Type);
        }
        if (!Concentration.isEmpty()) {
            Aliq_Concentration_Input.clear();
            send_keys(Aliq_Concentration_Input, Concentration);
        }
        map.put("Aliquot Submitter ID ", Aliq_SubId);
        map.put("Aliquot Amount       ", Aliq_Amount);
        map.put("Aliquot Source Center", Source_center);
        map.put("Aliquot Analyte Type ", Analyte_Type);
        map.put("Aliquot Concentration", Concentration);
        screenshot();
        Ex.Info_ScreenShot("Aliquot Input  Values: " + MarkupHelper.createUnorderedList(map).getMarkup());
    }

    @Step("Sample Input")
    public void MCSample_MandatoryInput(String Sample_SubId) throws InterruptedException {
        ScrollView(MC_Sample);
        if (!Sample_SubId.isEmpty()) {
            jsclick(MC_Sample);
            Sample_Submitter_ID.clear();
            send_keys(Sample_Submitter_ID, Sample_SubId);
        }
        screenshot();
        Ex.Info_ScreenShot("Sample Submitter ID Entered: " + Sample_SubId);
    }

    @Step("Read Group Input")
    public void MCReadGroup_Input(String Platform, String ReadGroupName, String Library) throws InterruptedException {
        ScrollView(MC_ReadGroup);
        Thread.sleep(3000);
        jsclick(MC_ReadGroup);
        SelectDrpDwnValue(PlatFormDrpDwn, Platform);
        send_keys(Read_group_name, ReadGroupName);
        Ele_Click(Read_group_RadioBtn_Yes);
        SelectDrpDwnValue(Library_strategyDrpDwn, Library);
        screenshot();
    }

    public void requeiredtbale_Error_msg_NonSeq() {
        jsclick(Save_btn);
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        Ex.Pass_ScreenShot("Non Seq  Error Message");
        Assert.assertEquals(Success_Msg, "Fill one of, aligned_reads, annotated_somatic_mutation, genomic_profile, simple_germline_variation, simple_somatic_mutation, slide_image, clinical_supplement, immune_profile");
        screenshot();
    }

    public void requeiredtbale_Error_msg_Seq() {
        jsclick(Save_btn);
        wait.until(ExpectedConditions.visibilityOf(Notification_Msg));
        String Success_Msg = Notification_Msg.getText();
        //Assert.assertEquals(Success_Msg, "Fill one of Aligned reads, Aligned reads index, Annotated somatic mutation, Genomic profile, Simple germline variation, Simple somatic mutation, Slide image, Clinical supplement, Unaligned reads, Immune profile.");
        screenshot();
    }

    @Step("Alliquot Mandatory Input")
    public void MCAlliquot_Mandatory_Input(String Aliquot_SubId) throws InterruptedException {
        ScrollView(MC_Aliquot);
        jsclick(MC_Aliquot);
        Aliquot_Submitter_ID.clear();
        send_keys(Aliquot_Submitter_ID, Aliquot_SubId);
        screenshot();
        Ex.Extent_Info("Aliquot Submitter ID Entered: " + Aliquot_SubId);
    }


    @Step("Select Project Overview Elipse")
    public void select_Project_OverviewElipse(String Project) {

        WebElement elipse = driver.findElement(By.xpath("//mat-cell[contains(text(),'" + Project + "')]/..//mat-icon[text()='more_vert']"));
        Ele_Click(elipse);
        screenshot();
        Ex.Extent_Info("UI Project Selected: " + Project);

    }

    @FindBy(xpath = "(//table/tbody/tr//td)[9]")
    public WebElement Grid_DataCategory;

    @FindBy(xpath = "(//table/tbody/tr//td)[10]")
    public WebElement Grid_DataFormat;

    @FindBy(xpath = "(//table/tbody/tr//td)[12]")
    public WebElement Grid_DataType;

    @Step("Validate UI Immune Profile Values")
    public void validate_UI_ImmuneProfile(String Immune_ProfileValues) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(Grid_DataCategory));
        ScrollView_Action(Grid_DataCategory);
        String[] Immune_Profile = Immune_ProfileValues.split(",");
        if (!Immune_Profile[0].isEmpty()) {
            Assert.assertEquals(Immune_Profile[0], Grid_DataFormat.getText());
        }
        if (!Immune_Profile[1].isEmpty()) {
            ScrollView(Grid_DataType);
            Assert.assertEquals(Immune_Profile[1], Grid_DataType.getText());
        }
        if (!Immune_Profile[2].isEmpty()) {
            ScrollView(Grid_DataCategory);
            Assert.assertEquals(Immune_Profile[2], Grid_DataCategory.getText());
        }

        Ex.Pass_ScreenShot("Validate Immune Profile UI Values");
        screenshot();
    }

    public void validate_read_group(String readgroup) {
        String[] RG = readgroup.split(",");
        String Platform = RG[0];
        String Group_Name = RG[1];
        String Library_Strategy = RG[2];

        ScrollView(MC_ReadGroup);
        jsclick(MC_ReadGroup);
        Assert.assertEquals(Platform, RG_Platform_Value.getText().trim());
        Assert.assertEquals(Group_Name, RG_Name.getAttribute("value"));
        Assert.assertEquals(Library_Strategy, RG_Library_strategy_Value.getText().trim());
        Ex.Pass_ScreenShot("Validate ReadGroup In Update MetaData");

    }

    public void validate_optionaltable_remove() {
        s_assert.assertEquals(MC_Aligned_reads_Count.size(), 0);
        s_assert.assertEquals(MC_IP_Workflow_Count.size(), 0);
        Ex.Extent_Info("Optional Tables Aligned Reads,IP WorkFlow Removed As Expected");
    }

    @Step("Validate Slide Image In Update MetaData")
    public void validate_Slide_Image(String Slide_Image) {
        String[] Slide_Images = Slide_Image.split(",");
        String Dataformat = Slide_Images[0];
        String DataCategory = Slide_Images[1];
        String DataType = Slide_Images[2];
        String Experimnetal_Strat = Slide_Images[3];
        ScrollView(MC_SlideImage);
        jsclick(MC_SlideImage);
        Assert.assertEquals(Dataformat, SI_DataFromat_Value.getText().trim());
        Assert.assertEquals(DataCategory, SI_DataCategory_Value.getText().trim());
        Assert.assertEquals(DataType, SI_DataType_Value.getText().trim());
        Assert.assertEquals(Experimnetal_Strat, SI_ExpStrat_Value.getText().trim());
        Ex.Pass_ScreenShot("Validate Slide Image In Update MetaData");
        screenshot();

    }

    public void validate_Aligned_reads(String Aligned_reads) {
        String[] AR = Aligned_reads.split(",");
        String Dataformat = AR[0];
        String DataCategory = AR[1];
        String DataType = AR[2];
        String Experimnetal_Strat = AR[3];
        ScrollView(MC_Aligned_reads);
        jsclick(MC_Aligned_reads);
        //Assert.assertEquals(Dataformat, AR_DataFromat_Value.getText().trim());
        Assert.assertEquals(DataCategory, AR_DataCategory_Value.getText().trim());
        Assert.assertEquals(DataType, AR_DataType_Value.getText().trim());
        Assert.assertEquals(Experimnetal_Strat, AR_ExpStrat_Value.getText().trim());
        Ex.Pass_ScreenShot("Validate Aligned Reads Update Data Values ");
    }

    public void validate_AlignmentWorkFlow(String WorkFlowId) {
        ScrollView(MC_Alignment_Workflow);
        jsclick(MC_Alignment_Workflow);
        String UI_IPWF = Alignment_WorkFlow_Value.getText();
        if (UI_IPWF.contains(WorkFlowId)) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
    }

    public void validate_IPWorkFlow(String WorkFlowId) {
        ScrollView(MC_ImmuneProfile_Workflow);
        jsclick(MC_ImmuneProfile_Workflow);
        String UI_IPWF = IP_WorkFlow_Value.getText();
        if (UI_IPWF.contains(WorkFlowId)) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false);
        }
        Ex.Pass_ScreenShot("Validate Immune Profile Values ");
    }

    @Step("Validate Update Meta Data")
    public void validate_metadata_values(String case_id, String Sample_id, String Aliq_id, String IPWF) throws InterruptedException {
        ScrollView(MC_Case1);
        Thread.sleep(1000);
        jsclick(MC_Case1);
        Thread.sleep(2000);
        String UI_CaseId = Case_Submitter_ID.getAttribute("value");
        Assert.assertEquals(UI_CaseId, case_id);
        ScrollView(MC_Sample);
        Thread.sleep(1000);
        clickableEle(MC_Sample);
        jsclick(MC_Sample);
        String UI_SampleId = Sample_Submitter_ID.getAttribute("value");
        Assert.assertEquals(UI_SampleId, Sample_id);
        ScrollView(MC_Aliquot);
        normalwait(1000);
        jsclick(MC_Aliquot);
        String UI_AliquotId = Aliquot_Submitter_ID.getAttribute("value");
        Assert.assertEquals(UI_AliquotId, Aliq_id);
        Ex.Pass_ScreenShot("All UI Values are reflecting correctly on Update MetaData ");
        screenshot();
    }


    public void dropdown_present(String Value) {
        WebElement Element = driver.findElement(By.xpath("//span[contains(text(),'" + Value + "')]"));
        s_assert.assertTrue(Element.isDisplayed(),Value +"is not displayed in SI tumour dropdown");
    }

    public void verify_SampletypeDrpDwn(String Sampletype) {
        String[] SI_Sampletype = Sampletype.split(",");
        ScrollView(SI_SampletypeDrpDwn);
        normalwait(2000);
        jsclick(SI_SampletypeDrpDwn);
        for (String si_specy : SI_Sampletype) {
            dropdown_present(si_specy);
        }
        Ex.Pass_ScreenShot("Slide Image Sample Type  Validation");
        double_click(SI_header);
    }

    public void verify_markDrpDwn(String Marker) {
        String[] SI_Marker = Marker.split(",");
        ScrollView(SI_MarkerDrpDwn);
        jsclick(SI_MarkerDrpDwn);
        for (String si_specy : SI_Marker) {
            dropdown_present(si_specy);
        }
        Ex.Pass_ScreenShot("Slide Image Mark Values  Validation");
        double_click(SI_header);
    }


    public void verify_TissueytypeDrpDwn(String Tissuetype) {
        String[] SI_Tissuetype = Tissuetype.split(",");
        ScrollView(SI_TissuetypeDrpDwn);
        jsclick(SI_TissuetypeDrpDwn);
        normalwait(2000);
        for (String si_specy : SI_Tissuetype) {
            dropdown_present(si_specy);
        }
        Ex.Pass_ScreenShot("Slide Image Tissue Type  Validation");
        double_click(SI_header);
    }

    public void verify_TumourtypeDrpDwn(String Tumortype) {
        String[] SI_Marker = Tumortype.split(",");
        ScrollView(SI_TumortypeDrpDwn);
        jsclick(SI_TumortypeDrpDwn);
        for (String si_specy : SI_Marker) {
            dropdown_present(si_specy);
        }
        Ex.Pass_ScreenShot("Slide Image Tumour Type  Validation");
        double_click(SI_header);
    }

    public void verify_SpeciesDrpDwn(String Species) {
        ScrollView(MC_Slide_Image);
        jsclick(MC_Slide_Image);
        String[] SI_Species = Species.split(",");
        ScrollView(SI_speciesDrpDwn);
        jsclick(SI_speciesDrpDwn);
        for (String si_specy : SI_Species) {
            dropdown_present(si_specy);
        }
        Ex.Pass_ScreenShot("Slide Image Species DropDown Values  Validation");
        double_click(SI_header);
    }

    public void veriy_SI_DataFormat(String DataFormat) throws InterruptedException {
        ScrollView(MC_SlideImage);
        normalwait(2000);
        jsclick(MC_SlideImage);
        SelectDrpDwnValue(SI_dataformatDrpDwn, "BIF");
        SelectDrpDwnValue(SI_datacategoryDrpDwn, "Biospecimen");
        SelectDrpDwnValue(SI_datatypeDrpDwn, "Slide Image");
        SelectDrpDwnValue(SI_ExpStrategyDrpDwn, "Tissue Slide");
        System.out.println("BIF Value Present As Expected");
    }

    public void validate_max_values(WebElement Ele, String maxplus1) {
        ScrollView(Ele);
        String value = Ele.getAttribute("value");
        String max = maxplus1.substring(0, maxplus1.length() - 1);
        Assert.assertEquals(max, value);
    }

    public void validate_values_min(WebElement Ele, String value) {
        ScrollView(Ele);
        String value1 = Ele.getAttribute("value");
        String min = Character.toString(value.charAt(0));
        Assert.assertEquals(value1, min);
    }

    public void validate_values_max(WebElement Ele, String value) {
        ScrollView(Ele);
        String value1 = Ele.getAttribute("value");
        Assert.assertEquals(value1, value);
    }

    public void verify_SlideImage_min(String SlideId, String BlockId, String TimepointCode, String ProtoNum, String ProtoName
            , String Studyid, String CollectionType, String Notes, String SlideImage) throws InterruptedException {
        ScrollView(SI_SlideId);
        send_keys(SI_SlideId, Character.toString(SlideId.charAt(0)));
        ScrollView(SI_BlockId);
        send_keys(SI_BlockId, Character.toString(BlockId.charAt(0)));
        ScrollView(SI_Timepointcode);
        send_keys(SI_Timepointcode, Character.toString(TimepointCode.charAt(0)));
        ScrollView(SI_ProtocolNumber);
        send_keys(SI_ProtocolNumber, Character.toString(ProtoNum.charAt(0)));
        ScrollView(SI_ProtocolName);
        send_keys(SI_ProtocolName, Character.toString(ProtoName.charAt(0)));
        ScrollView(SI_StudyId);
        send_keys(SI_StudyId, Character.toString(Studyid.charAt(0)));
        ScrollView(SI_Collectiontype);
        send_keys(SI_Collectiontype, Character.toString(CollectionType.charAt(0)));
        send_keys(SI_Notes1, Character.toString(Notes.charAt(0)));
        send_keys(SI_Notes2, Character.toString(Notes.charAt(0)));
        send_keys(SI_Notes3, Character.toString(Notes.charAt(0)));
        validate_values_min(SI_SlideId, SlideId);
        validate_values_min(SI_BlockId, BlockId);
        validate_values_min(SI_Timepointcode, TimepointCode);
        validate_values_min(SI_ProtocolNumber, ProtoNum);
        validate_values_min(SI_ProtocolName, TimepointCode);
        validate_values_min(SI_StudyId, Studyid);
        validate_values_min(SI_Collectiontype, CollectionType);
        validate_values_min(SI_Notes1, Notes);
        validate_values_min(SI_Notes2, Notes);
        validate_values_min(SI_Notes3, Notes);
        Ex.Pass_ScreenShot("Validate Slide Image Min Values");
        ScrollView(SI_Clearfields);
        jsclick(SI_Clearfields);
        normalwait(5000);
        //DF_Slide_Image(SlideImage);

    }

    public void verify_SlideImage_max(String SlideId, String BlockId, String TimepointCode, String ProtoNum, String ProtoName
            , String Studyid, String CollectionType, String Notes) {
        send_keys(SI_SlideId, SlideId);
        send_keys(SI_BlockId, BlockId);
        send_keys(SI_Timepointcode, TimepointCode);
        send_keys(SI_ProtocolNumber, ProtoNum);
        send_keys(SI_ProtocolName, ProtoName);
        send_keys(SI_StudyId, Studyid);
        send_keys(SI_Collectiontype, CollectionType);
        send_keys(SI_Notes1, Notes);
        send_keys(SI_Notes2, Notes);
        send_keys(SI_Notes3, Notes);
        Save_btn.click();
        normalwait(5000);
        validate_values_max(SI_SlideId, SlideId);
        validate_values_max(SI_BlockId, BlockId);
        validate_values_max(SI_Timepointcode, TimepointCode);
        validate_values_max(SI_ProtocolNumber, ProtoNum);
        validate_values_max(SI_ProtocolName, TimepointCode);
        validate_values_max(SI_StudyId, Studyid);
        validate_values_max(SI_Collectiontype, CollectionType);
        validate_values_max(SI_Notes1, Notes);
        validate_values_max(SI_Notes2, Notes);
        validate_values_max(SI_Notes3, Notes);
        ScrollView(SI_Clearfields);
        jsclick(SI_Clearfields);

    }

    public void veriy_SlideImage_maxplus1(String SlideId, String BlockId, String TimepointCode, String ProtoNum, String ProtoName
            , String Studyid, String CollectionType, String Notes, String Slide_Image) throws InterruptedException {
        long num = Notes.chars().count();
        send_keys(SI_SlideId, SlideId);
        send_keys(SI_BlockId, BlockId);
        send_keys(SI_Timepointcode, TimepointCode);
        send_keys(SI_ProtocolNumber, ProtoNum);
        send_keys(SI_ProtocolName, TimepointCode);
        send_keys(SI_StudyId, Studyid);
        send_keys(SI_Collectiontype, CollectionType);
        js_sendkeys(SI_Notes1, Notes);
        js_sendkeys(SI_Notes2, Notes);
        js_sendkeys(SI_Notes3, Notes);
        //ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);
        validate_max_values(SI_SlideId, SlideId);
        validate_max_values(SI_BlockId, BlockId);
        validate_max_values(SI_Timepointcode, TimepointCode);
        validate_max_values(SI_ProtocolNumber, ProtoNum);
        validate_max_values(SI_ProtocolName, TimepointCode);
        validate_max_values(SI_StudyId, Studyid);
        validate_max_values(SI_Collectiontype, CollectionType);
        Ex.Info_ScreenShot("Slide Image Max Plus 1 Validation:");
        /*validate_max_values(SI_Notes1,Notes);
        validate_max_values(SI_Notes2,Notes);
        validate_max_values(SI_Notes3,Notes);*/
        ScrollView(SI_Clearfields);
        jsclick(SI_Clearfields);
        normalwait(5000);
        //DF_Slide_Image(Slide_Image);
        //ManifestSave_SuccessMsg(Constant.Manifest_Save_Msg);

    }

    public void character_maxcount(String value) {

    }


    @FindBy(xpath = "//li[contains(@class,'ng-star-inserted')]//a//span")
    public List<WebElement> Table_Count;

    @FindBy(xpath = "(//span[@class='mat-option-text'])[1]")
    public WebElement First_DrpDwn;

    @FindBy(xpath = "//div[contains(@class,'mat-calendar-body-today')]/..")
    public WebElement Today_Date;

    @FindBy(xpath = "//li[contains(@class,'invalid-manifest-form')]")
    public List<WebElement> Highlighted_Table;
    public void read_global_schema(String Filename) throws IOException, ParseException {

        ArrayList<String> csv_headers = new ArrayList<String>();
        HashMap<String, String> map = new HashMap<>();
        String filePath = DownloadPath + Filename;
        FileReader reader = new FileReader(filePath);
        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(reader);
        JSONObject jo1 = (JSONObject) jo.get("case");

    }
    //without try catch

    /*public void find_mandatory_tables1_Dhu() {
        String Mandatory_Table_Name;
        try {
            for (int i = 1; i <= Table_Count.size(); i++) {
                String Table_Name = driver.findElement(By.xpath("(//li[contains(@class,'ng-star-inserted')]//a//span)[" + i + "]")).getText().trim();
                if (Table_Name.equals("*")) {

                    WebElement Table = driver.findElement(By.xpath("(//li[contains(@class,'ng-star-inserted')]//a//span)[" + i + "]"));
                    jsclick(Table);
                    try {
                        List<WebElement> txtFiled = driver.findElements(By.xpath("//section[contains(@style,'display: block')]//label//span[text()='*']//preceding::input[not(contains(@class,'datepicker'))][1]"));
                        int txtCount = 0;
                        for (WebElement txt : txtFiled) {
                            //     if (txtCount != 0) {
                            if (txt.isEnabled()) {
                                txt.sendKeys("text" + txtCount);
                                //     }
                            }
                            txtCount++;
                        }
                    } catch (Exception e) {

                    }

                    try {
                        List<WebElement> txtDDFiled = driver.findElements(By.xpath("//section[contains(@style,'display: block')]//label//span[text()='*']//preceding::mat-select[1]"));
                        for (WebElement txtDD : txtDDFiled) {
                            txtDD.click();
                            jsclick(First_DrpDwn);
                        }
                    } catch (Exception e) {

                    }

                    try {
                        List<WebElement> txtDPFiled = driver.findElements(By.xpath("//section[contains(@style,'display: block')]//label//span[text()='*']//preceding::input[contains(@class,'datepicker')][1]//following::button[1]"));
                        int txtDPCount = 0;
                        for (WebElement txtDP : txtDPFiled) {
                            //if(txtDPCount!=0){
                            txtDP.click();
                            driver.findElement(By.xpath("//mat-month-view//tbody//tr[@role='row']//td//div[contains(@class,'today')]")).click();
                            //  }
                            txtDPCount++;
                        }
                    } catch (Exception e) {

                    }
                }
            }
        }
        catch (){

        }
    }*/
    public void find_mandatory_tables1(){
        String Mandatory_Table_Name;
        for (int i=2;i<=Table_Count.size();i++){
            String Table_Name =driver.findElement(By.xpath("(//li[contains(@class,'ng-star-inserted')]//a//span)["+i+"]")).getText().trim();
            if (Table_Name.equals("*")){

                WebElement Table =driver.findElement(By.xpath("(//li[contains(@class,'ng-star-inserted')]//a//span)["+i+"]")) ;
                jsclick(Table);
                Mandatory_Table_Name = driver.findElement(By.xpath("(//li[contains(@class,'ng-star-inserted')]//a)["+i+"]")).getText().trim();
                String name=Mandatory_Table_Name.replace("*","");
                System.out.println("Table_Name:"+name);
                List<WebElement> Row_Count =driver.findElements(By.xpath("//h3[text()='"+name+"']/..//label[not(contains(@class,'mat-radio-label'))]//span"));
                System.out.println("Row Count"+Row_Count.size());
                for (int j=1;j<=Row_Count.size();j++){
                    String row_name =  driver.findElement(By.xpath("(//h3[text()='"+name+"']/..//mat-label//span)["+j+"]")).getText().trim();
                    System.out.println(j);
                    System.out.println("Row Name"+row_name);
                    if(row_name.equals("*")){

                        System.out.println("Entered:"+row_name);
                        WebElement text =driver.findElement(By.xpath("(//h3[text()='"+name+"']/..//mat-label)["+j+"]"));
                        String value =text.getText().trim().replace("*","");

                        System.out.println("Values"+value);
                        //TextBox
                        List<WebElement> textbox =driver.findElements(By.xpath("//h3[text()='"+name+"']/..//mat-label[contains(text(),'"+value+"')]/../../../input[not(contains(@class,'datepicker'))]"));
                        //DropDwn
                        List<WebElement> DrpDwn =driver.findElements(By.xpath(" //h3[contains(text(),'"+name+"')]/..//following-sibling::mat-label[contains(text(),'"+value+"')]/../../..//div[contains(@class,'mat-select-arrow ng')]"));
                        //Calender
                        List<WebElement> Calender =driver.findElements(By.xpath("//h3[text()='"+name+"']/..//following-sibling::mat-label[contains(text(),'"+value+"')]/../../..//button"));
                        int textbox_size =textbox.size();
                        int DrpDwn_size =DrpDwn.size();
                        int Calender_size =Calender.size();
                        // boolean plan =wait.until()
                        System.out.println("Textsize"+textbox_size);
                        System.out.println("DrpDwn_size"+DrpDwn_size);
                        System.out.println("Calender_size"+Calender_size);
                        if (textbox_size==1){
                            System.out.println("Jump");
                            WebElement TextBox =driver.findElement(By.xpath("//h3[text()='"+name+"']/..//mat-label[contains(text(),'"+value+"')]/../../../input"));
                            TextBox.sendKeys("Dhinu");
                            System.out.println("Keys");
                            ScrollView(TextBox);
                            //jsclick(TextBox);
                            //send_keys(TextBox,"Dhinu");
                            normalwait(1000);
                            String a =TextBox.getAttribute("value");
                            if (a.isEmpty()){

                                send_keys(TextBox,"34");
                            }
                        }

                        else if (DrpDwn_size==1){
                            WebElement DropDown =driver.findElement(By.xpath(" //h3[contains(text(),'"+name+"')]/..//following-sibling::mat-label[contains(text(),'"+value+"')]/../.."));
                            jsclick(DropDown);
                            normalwait(1000);
                            jsclick(First_DrpDwn);
                        }
                        else if (Calender_size==1){
                            System.out.println("Entered calender");
                            WebElement Calender_Icon =driver.findElement(By.xpath("//h3[text()='"+name+"']/..//following-sibling::mat-label[contains(text(),'"+value+"')]/../../..//button"));
                            jsclick(Calender_Icon);
                            System.out.println("Clicked Calender");
                            normalwait(5000);
                            //jsclick(Today_Date);

                            List<WebElement> cal =driver.findElements(By.xpath("//td[@role='gridcell']//div"));
                            System.out.println("dATE"+cal.size());
                            for (int k=1;k<cal.size();k++){
                                if (k==1){
                                    System.out.println(cal.get(k).getText());
                                    jsclick(cal.get(k));

                                    //  WebElement date =   driver.findElement(By.xpath("//td[@role='gridcell']//div[text()="+ k +"]"));
                                 //jsclick(date);
                                }
                            }

                            //mouse_hover(driver.findElement(By.xpath("//td[@role='gridcell']//div[text()=' 1 ']")));
                            //jsclick(driver.findElement(By.xpath("//td[@role='gridcell']//div[text()=' 1 ']")));

                        }
                    }

                }
            }

        }
    }
    public void find_mandatory_tables2(){
        String Mandatory_Table_Name;
        for (int i=1;i<=Table_Count.size();i++){
            String Table_Name =driver.findElement(By.xpath("(//li[contains(@class,'ng-star-inserted')]//a//span)["+i+"]")).getText().trim();
            if (Table_Name.equals("*")){

                WebElement Table =driver.findElement(By.xpath("(//li[contains(@class,'ng-star-inserted')]//a//span)["+i+"]")) ;
                jsclick(Table);
                Mandatory_Table_Name = driver.findElement(By.xpath("(//li[contains(@class,'ng-star-inserted')]//a)["+i+"]")).getText().trim();
                String name=Mandatory_Table_Name.replace("*","");
                System.out.println("Table_Name:"+name);
                List<WebElement> Row_Count =driver.findElements(By.xpath("//h3[text()='"+name+"']/..//mat-label//span"));
                System.out.println("Row Count"+Row_Count.size());
                for (int j=1;j<=Row_Count.size();j++){
                    String row_name =  driver.findElement(By.xpath("(//h3[text()='"+name+"']/..//mat-label//span)["+j+"]")).getText().trim();
                    System.out.println(j);
                    System.out.println("Row Name"+row_name);
                    if(row_name.equals("*")){

                        System.out.println("Entered:"+row_name);
                        WebElement text =driver.findElement(By.xpath("(//h3[text()='"+name+"']/..//mat-label)["+j+"]"));
                        String value =text.getText().trim().replace("*","");

                        System.out.println("Values"+value);
                        //TextBox
                        List<WebElement> textbox =driver.findElements(By.xpath("//h3[text()='"+name+"']/..//mat-label[contains(text(),'"+value+"')]/../../../input"));
                        //DropDwn
                        List<WebElement> DrpDwn =driver.findElements(By.xpath(" //h3[contains(text(),'"+name+"')]/..//following-sibling::mat-label[contains(text(),'"+value+"')]/../.."));
                        //Calender
                        List<WebElement> Calender =driver.findElements(By.xpath("//h3[text()='"+name+"']/..//following-sibling::mat-label[contains(text(),'"+value+"')]/../../..//button"));
                        int textbox_size =textbox.size();
                        int DrpDwn_size =DrpDwn.size();
                        int Calender_size =Calender.size();
                        // boolean plan =wait.until()

                        if (textbox_size==1){

                            WebElement TextBox =driver.findElement(By.xpath("//h3[text()='"+name+"']/..//mat-label[contains(text(),'"+value+"')]/../../../input"));
                            ScrollView(TextBox);
                            jsclick(TextBox);
                            send_keys(TextBox,"Dhinu");
                            normalwait(1000);
                            String a =TextBox.getAttribute("value");
                            if (a.isEmpty()){

                                send_keys(TextBox,"34");
                            }
                        }

                        else if (DrpDwn_size==1){
                            WebElement DropDown =driver.findElement(By.xpath(" //h3[contains(text(),'"+name+"')]/..//following-sibling::mat-label[contains(text(),'"+value+"')]/../.."));
                            jsclick(DropDown);
                            normalwait(1000);
                            jsclick(First_DrpDwn);
                        }
                        else if (Calender_size==1){
                            WebElement Calender_Icon =driver.findElement(By.xpath("//h3[text()='"+name+"']/..//following-sibling::mat-label[contains(text(),'"+value+"')]/../../..//button"));
                            jsclick(Calender_Icon);
                            normalwait(2000);
                            jsclick(Today_Date);

                        }
                    }

                }
            }

        }
    }



//try catch code
    public void find_mandatory_tables(){
        String Mandatory_Table_Name;
        for (int i=2;i<=Table_Count.size();i++){
            String Table_Name =driver.findElement(By.xpath("(//li[contains(@class,'ng-star-inserted')]//a//span)["+i+"]")).getText().trim();
            if (Table_Name.equals("*")){

                    WebElement Table =driver.findElement(By.xpath("(//li[contains(@class,'ng-star-inserted')]//a//span)["+i+"]")) ;
                    jsclick(Table);
                    Mandatory_Table_Name = driver.findElement(By.xpath("(//li[contains(@class,'ng-star-inserted')]//a)["+i+"]")).getText().trim();
                    String name=Mandatory_Table_Name.replace("*","");
                    System.out.println("Table_Name:"+name);
                    List<WebElement> Row_Count =driver.findElements(By.xpath("//h3[text()='"+name+"']/..//mat-label//span"));
                    System.out.println("Row Count"+Row_Count.size());
                    for (int j=2;j<=Row_Count.size();j++){
                        String row_name =  driver.findElement(By.xpath("(//h3[text()='"+name+"']/..//mat-label//span)["+j+"]")).getText().trim();
                        System.out.println(j);
                        System.out.println("Row Name"+row_name);
                      if(row_name.equals("*")){

                            System.out.println("Entered:"+row_name);
                            WebElement text =driver.findElement(By.xpath("(//h3[text()='"+name+"']/..//mat-label)["+j+"]"));
                            String value =text.getText().trim().replace("*","");

                            System.out.println("Values"+value);
                            //TextBox
                          List<WebElement> textbox =driver.findElements(By.xpath("//h3[text()='"+name+"']/..//mat-label[contains(text(),'"+value+"')]/../../../input"));
                            //DropDwn
                          List<WebElement> DrpDwn =driver.findElements(By.xpath(" //h3[contains(text(),'"+name+"')]/..//following-sibling::mat-label[contains(text(),'"+value+"')]/../.."));
                            //Calender
                          List<WebElement> Calender =driver.findElements(By.xpath("//h3[text()='"+name+"']/..//following-sibling::mat-label[contains(text(),'"+value+"')]/../../..//button"));
                          int textbox_size =textbox.size();
                          int DrpDwn_size =DrpDwn.size();
                          int Calender_size =Calender.size();
                         // boolean plan =wait.until()
                          try {
                              if (driver.findElement(By.xpath("//h3[text()='"+name+"']/..//mat-label[contains(text(),'"+value+"')]/../../../input")).isDisplayed()){

                                  WebElement TextBox =driver.findElement(By.xpath("//h3[text()='"+name+"']/..//mat-label[contains(text(),'"+value+"')]/../../../input"));
                                  ScrollView(TextBox);
                                  jsclick(TextBox);
                                  if (value.equals("Case submitter id")){

                                  }
                                  send_keys(TextBox,"Dhinu");
                                  normalwait(1000);
                                  String a =TextBox.getAttribute("value");
                                  if (a.isEmpty()){

                                      send_keys(TextBox,"34");
                                  }
                              }
                          }
                          catch (NoSuchElementException e){
                              try{
                                  if (driver.findElement(By.xpath(" //h3[contains(text(),'"+name+"')]/..//following-sibling::mat-label[contains(text(),'"+value+"')]/../..")).isDisplayed()){
                                      WebElement DropDown =driver.findElement(By.xpath(" //h3[contains(text(),'"+name+"')]/..//following-sibling::mat-label[contains(text(),'"+value+"')]/../.."));
                                      jsclick(DropDown);
                                      normalwait(1000);
                                      jsclick(First_DrpDwn);
                                  }
                              }
                              catch (NoSuchElementException f){
                                  if (driver.findElement(By.xpath("//h3[text()='"+name+"']/..//following-sibling::mat-label[contains(text(),'"+value+"')]/../../..//button")).isDisplayed()){
                                      WebElement Calender_Icon =driver.findElement(By.xpath("//h3[text()='"+name+"']/..//following-sibling::mat-label[contains(text(),'"+value+"')]/../../..//button"));
                                      jsclick(Calender_Icon);
                                      normalwait(1000);
                                      jsclick(Today_Date);

                                  }
                              }

                          }
                          }

                        }
                    }

            }
        }

    public void fill_mandatory_fields(){
        String Mandatory_Table_Name;
        for (int i=1;i<=Table_Count.size();i++){
            String Table_Name =driver.findElement(By.xpath("(//li[contains(@class,'ng-star-inserted')]//a//span)["+i+"]")).getText().trim();
            if (Table_Name.equals("*")){

                WebElement Table =driver.findElement(By.xpath("(//li[contains(@class,'ng-star-inserted')]//a//span)["+i+"]")) ;
                jsclick(Table);
                Mandatory_Table_Name = driver.findElement(By.xpath("(//li[contains(@class,'ng-star-inserted')]//a)["+i+"]")).getText().trim();
                String name=Mandatory_Table_Name.replace("*","");
                System.out.println("Table_Name:"+name);
                List<WebElement> Row_Count =driver.findElements(By.xpath("//h3[text()='"+name+"']/..//label[not(contains(@class,'mat-radio-label'))]//span"));
                System.out.println("Row Count"+Row_Count.size());
                for (int j=1;j<=Row_Count.size();j++){
                    String row_name =  driver.findElement(By.xpath("(//h3[text()='"+name+"']/..//label[not(contains(@class,'mat-radio-label'))]//span)["+j+"]")).getText().trim();
                    System.out.println(j);
                    System.out.println("Row Name"+row_name);
                    if(row_name.equals("*")){

                        System.out.println("Entered:"+row_name);
                        WebElement text =driver.findElement(By.xpath("(//h3[text()='"+name+"']/..//label[not(contains(@class,'mat-radio-label'))])["+j+"]"));
                        String value =text.getText().trim().replace("*","");

                        System.out.println("Values"+value);
                        //TextBox
                        List<WebElement> textbox =driver.findElements(By.xpath("//h3[text()='"+name+"']/..//mat-label[contains(text(),'"+value+"')]/../../../input[not(contains(@class,'datepicker'))]"));
                        //DropDwn
                        List<WebElement> DrpDwn =driver.findElements(By.xpath(" //h3[contains(text(),'"+name+"')]/..//following-sibling::mat-label[contains(text(),'"+value+"')]/../../..//div[contains(@class,'mat-select-arrow ng')]"));
                        //Calender
                        List<WebElement> Calender =driver.findElements(By.xpath("//h3[text()='"+name+"']/..//following-sibling::mat-label[contains(text(),'"+value+"')]/../../..//button"));
                        //Radio Btn
                        List<WebElement> Radio_Btn = driver.findElements(By.xpath("//h3[text()='"+name+"']/..//label[contains(text(),'"+value+"')]/..//mat-radio-group"));


                        int textbox_size =textbox.size();
                        int DrpDwn_size =DrpDwn.size();
                        int Calender_size =Calender.size();
                        int Radio_Button_size =Radio_Btn.size();
                        // boolean plan =wait.until()
                        System.out.println("Textsize"+textbox_size);
                        System.out.println("DrpDwn_size"+DrpDwn_size);
                        System.out.println("Calender_size"+Calender_size);
                        System.out.println("Radio_Btn"+Radio_Button_size);
                        try{
                            driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
                            if (textbox_size==1){

                                WebElement TextBox =driver.findElement(By.xpath("//h3[text()='"+name+"']/..//mat-label[contains(text(),'"+value+"')]/../../../input"));
                                TextBox.sendKeys("Dhinu");
                                ScrollView(TextBox);
                                normalwait(1000);
                                String a =TextBox.getAttribute("value");
                                if (a.isEmpty()){

                                    send_keys(TextBox,"34");
                                }
                            }
                        }
                        catch (Exception e){

                        }




                        if (DrpDwn_size==1){
                            WebElement DropDown =driver.findElement(By.xpath(" //h3[contains(text(),'"+name+"')]/..//following-sibling::mat-label[contains(text(),'"+value+"')]/../.."));
                            jsclick(DropDown);
                            normalwait(1000);
                            jsclick(First_DrpDwn);
                        }
                        else if (Calender_size==1){
                            System.out.println("Entered calender");
                            WebElement Calender_Icon =driver.findElement(By.xpath("//h3[text()='"+name+"']/..//following-sibling::mat-label[contains(text(),'"+value+"')]/../../..//button"));
                            jsclick(Calender_Icon);
                            System.out.println("Clicked Calender");
                            normalwait(5000);
                            //jsclick(Today_Date);

                            List<WebElement> cal =driver.findElements(By.xpath("//td[@role='gridcell']//div"));
                            System.out.println("dATE"+cal.size());
                            for (int k=1;k<cal.size();k++){
                                if (k==1){
                                    System.out.println(cal.get(k).getText());
                                    jsclick(cal.get(k));

                                    //  WebElement date =   driver.findElement(By.xpath("//td[@role='gridcell']//div[text()="+ k +"]"));
                                    //jsclick(date);
                                }
                            }

                            //mouse_hover(driver.findElement(By.xpath("//td[@role='gridcell']//div[text()=' 1 ']")));
                            //jsclick(driver.findElement(By.xpath("//td[@role='gridcell']//div[text()=' 1 ']")));

                        }

                        else if (Radio_Button_size==1){
                            System.out.println("Entered Checkbox");
                           WebElement CheckBox = driver.findElement(By.xpath("//section[contains(@style,'display: block')]//label[contains(text(),'"+value+"')]/..//div[contains(text(),'Yes')]"));
                            jsclick(CheckBox);
                        }
                    }

                }
            }

        }
    }







    public void fill_mandatory_tables(String TC_Case) {
        String Mandatory_Table_Name;
        int Table_Size = Highlighted_Table.size();
        for (int i = 0; i < Table_Size; i++) {

            WebElement Table = driver.findElement(By.xpath("(//li[contains(@class,'invalid-manifest-form')]//a)[1]"));
            jsclick(Table);
            Mandatory_Table_Name = driver.findElement(By.xpath("(//li[contains(@class,'invalid-manifest-form')]//a)[1]")).getText().trim();
            String name = Mandatory_Table_Name.replace("*", "");
            List<WebElement> Row_Count = driver.findElements(By.xpath("//h3[text()='" + name + "']/..//label[not(contains(@class,'mat-radio-label'))]//span"));
            driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
            for (int j = 1; j <= Row_Count.size(); j++) {
                String row_name = driver.findElement(By.xpath("(//h3[text()='" + name + "']/..//label[not(contains(@class,'mat-radio-label'))]//span)[" + j + "]")).getText().trim();
                if (row_name.equals("*")) {
                    WebElement text = driver.findElement(By.xpath("(//h3[text()='" + name + "']/..//label[not(contains(@class,'mat-radio-label'))])[" + j + "]"));
                    String value = text.getText().trim().replace("*", "");

                    //TextBox
                    List<WebElement> textbox = driver.findElements(By.xpath("//h3[text()='" + name + "']/..//mat-label[contains(text(),'" + value + "')]/../../../input[not(contains(@class,'datepicker'))]"));
                    //DropDwn
                    List<WebElement> DrpDwn = driver.findElements(By.xpath(" //h3[contains(text(),'" + name + "')]/..//following-sibling::mat-label[contains(text(),'" + value + "')]/../../..//div[contains(@class,'mat-select-arrow ng')]"));
                    //Calender
                    List<WebElement> Calender = driver.findElements(By.xpath("//h3[text()='" + name + "']/..//following-sibling::mat-label[contains(text(),'" + value + "')]/../../..//button"));
                    //Radio Btn
                    List<WebElement> Radio_Btn = driver.findElements(By.xpath("//h3[text()='" + name + "']/..//label[contains(text(),'" + value + "')]/..//mat-radio-group"));


                    int textbox_size = textbox.size();
                    int DrpDwn_size = DrpDwn.size();
                    int Calender_size = Calender.size();
                    int Radio_Button_size = Radio_Btn.size();
                    driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
                    try {
                        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
                        if (textbox_size == 1) {
                            WebElement TextBox = driver.findElement(By.xpath("//h3[text()='" + name + "']/..//mat-label[contains(text(),'" + value + "')]/../../../input"));
                            String b = TextBox.getAttribute("value");
                            if (b.isEmpty()) {
                                TextBox.sendKeys(value + "_" + TC_Case);
                            }
                            ScrollView(TextBox);
                            normalwait(1000);
                            String a = TextBox.getAttribute("value");
                            if (a.isEmpty()) {

                                send_keys(TextBox, TC_Case);
                            }
                        }
                    } catch (Exception e) {

                    }
                    if (DrpDwn_size == 1) {
                        WebElement DropDown = driver.findElement(By.xpath(" //h3[contains(text(),'" + name + "')]/..//following-sibling::mat-label[contains(text(),'" + value + "')]/../.."));
                        jsclick(DropDown);
                        normalwait(1000);
                        wait.until(ExpectedConditions.elementToBeClickable(First_DrpDwn));
                        ActionClick(First_DrpDwn);
                    } else if (Calender_size == 1) {
                        WebElement Calender_Icon = driver.findElement(By.xpath("//h3[text()='" + name + "']/..//following-sibling::mat-label[contains(text(),'" + value + "')]/../../..//button"));
                        jsclick(Calender_Icon);
                        normalwait(2000);
                        List<WebElement> cal = driver.findElements(By.xpath("//td[@role='gridcell']//div"));
                        for (int k = 1; k < cal.size(); k++) {
                            if (k == 1) {
                                jsclick(driver.findElement(By.xpath("//td[@role='gridcell']//div[text()=' 1 ']")));
                            }
                        }
                    } else if (Radio_Button_size == 1) {
                        WebElement CheckBox = driver.findElement(By.xpath("//section[contains(@style,'display: block')]//label[contains(text(),'" + value + "')]/..//div[contains(text(),'Yes')]"));
                        jsclick(CheckBox);
                    }
                }

            }


        }
        /*ScrollView(MC_Aliquot);
        jsclick(MC_Aliquot);
        if (Aliquot_Volume.isEnabled()) {
            send_keys(Aliquot_Volume, "100");
        }
        else{
            ScrollView(MC_Slide);
            jsclick(MC_Slide);
            send_keys(Slide_Percent_Lymph, "100");
        }*/
    }
    }









