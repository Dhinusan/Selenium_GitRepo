package Resources;

public class Constant {
    public static String AwsUrl ="https://euc1devredcap.awsapps.com/console";
    public static String S3_url ="https://s3.console.aws.amazon.com/s3/buckets?region=us-east-1";
    public static String Euc1_Url="https://euc1.redcapdev.platform.navify.com/";
    public static String usw2_url="https://usw2.redcapdev.platform.navify.com/";
    public static String Url="https://euc1.redcapdev.platform.navify.com/login";
    public static String Use1_Url ="https://use1.redcapdev.platform.navify.com/";
    public static String Author="Dhinusan";
    public static String DI_Management="Data Ingestion Management";
    public static String User_Management="User Management";
    public static String Cataloge_Management="Cataloge Management";
    public static String Project_Management="Project Management";
    public static String Region="us-east-1";

    //UI&AWSProjects
    public static String UI_Project1 ="may191684481557461";
    public static String UI_Project2 ="OregonProject";
    public static String UI_Project3 ="irdbenableproject";
    public static String UI_Project1_Name="Sep62022-Project";
    public static String Aws_Bucket ="deveuc1-may191684481557461-ingest-s3-euc1";


    //User Roles Data
    public static String Admin_Name ="robin.john@hcl.com";
    public static String Data_Manager_Name="robin_john_jbp@yahoo.com";
    public static String Data_User1_Name="robin_hellraiser@yahoo.com";
    public static String Data_User2_Name="annphilip13@gmail.com";
    public static String Privacy_Officer_Name="robin.john.jbp@gmail.com";
    public static String Unassigned_User_Name="gopika.v@hcl.com";

    public static String Admin="Admin";
    public static String Data_Manager="Data Manager";
    public static String Data_User1="Data User1";
    public static String Data_User2="Data User2";
    public static String Privacy_Officer="PO";
    public static String Unassigned_User="Unassigned User";
    public static String Password ="Welcome@123";
    public static String Aws_Admin_UserName ="robin.john";
    public static String Aws_DM_UserName ="robin_john_jbp";
    public static String Aws_DU1_UserName ="robin_hellraiser";
    public static String Aws_DU2_UserName ="annphilip13";
    public static String Aws_PO_UserName ="robin.john.jbp";

    //Messages
    public static String Add_User_SuccessMsg ="Project assignment is in progress. Changes will be reflected after sometime";
    public static String Delete_User_SuccessMsg ="User deleted successfully";
    public static String ReqTable_RawSeq_ErrorMsg ="Fill one of, aligned_reads, aligned_reads_index, annotated_somatic_mutation, genomic_profile, simple_germline_variation, simple_somatic_mutation, slide_image, clinical_supplement, unaligned_reads, immune_profile";
    public static String ReqTable_RawNonSeq_ErrorMsg ="Fill one of, aligned_reads, annotated_somatic_mutation, genomic_profile, simple_germline_variation, simple_somatic_mutation, slide_image, clinical_supplement, immune_profile";
    public static String Multiple_DF_ErrorMsg="Multiple data file attributes entered, Fill only one of Aligned reads, Aligned reads index, Annotated somatic mutation, Genomic profile, Simple germline variation, Simple somatic mutation, Slide image, Clinical supplement, Unaligned reads, Immune profile.";
    public static String Ingestion_Success_Msg="Manifest has been submitted for ingestion successfully";
    public static String Manifest_Save_Msg ="Manifest has been saved successfully";
    public static String User_Creation_Msg ="User created successfully";
    public static String Download_Msg ="Manifest has been downloaded successfully";
    public static String Proj_Creation_SuccessMsg ="Project created successfully";
    public static String Proj_Deletion_SuccessMsg ="Project deletion has started";
    public static String CsvUpload_SuccessMsg ="File uploaded successfully. Ingestion process shall begin shortly";
    public static String Thousand_Files_SuccessMsg ="CSV file download link will be sent to your registered email address shortly";
    public static String Sample_Template_Download_SuccessMsg ="Sample Schema template has been downloaded successfully";
    //Error notification
    public static String File_Error_Msg ="File Name/URL is required";
    public static String Case_SID_ErrorMsg="Case submitter id is required";
    public static String Sample_SID_ErrorMsg ="Sample submitter id is required";
    public static String Aliquot_SID_ErrorMsg="Aliquot submitter id is required";
    public static String ReadGroup_ErrorMsg="Platform is required";
    public static String AlignedRead_ErrorMsg="Data format is required";

    //User Error Notification
    public static String Empty_UserName="Username is required";
    public static String Empty_FirstName="First name is required";
    public static String Empty_LastName="Last name is required";
    public static String Empty_Organisation="Organization is required";
    public static String Empty_AWS_UserName="AWS Username is required";
    public static String Incorrect_UserName="Provide valid email address";
    public static String Incorrect_FirstName="Max 16 characters allowed";
    public static String Incorrect_LastName="Max 16 characters allowed";
    public static String Incorrect_Organisation="Max 16 characters allowed";
    public static String Incorrect_AWS_UserName="Max 20 characters allowed";
    public static String Incorrect_AWS_UserName_SpecialChar="AWS username can contain a-z, 0-9, special characters dot, hyphen, underscore.";
    public static String Already_Exist_AwsUser_Msg="AWS Username already exists. Please change";
    public static String Empty_Password ="Provide password";
    public static String Empty_Confirm_Password ="Provide confirm password";
    public static String Different_Password ="passwords didn't match";
    public static String AWS_Pw_Reset ="Your AWS account password has been reset successfully";
    public static String No_Alphabet_Pw ="Must contain atleast one alphabet (upper or lower case)";
    public static String No_Number_Pw ="Must contain at least 1 number";
    public static String No_SpecialCharacter_Pw ="Must contain at least 1 Special Character";
    public static String Pw_Min_Msg ="Must be at least 8 characters";
    public static String Pw_Max_Msg ="Maximum 16 characters";
    public static String ProjName_LengthMsg="Minimum 5 characters required";
    public static String ProjName_AlphabetErrorMsg="Must start with an alphabet";
    public static String ProjName_AlphaNumericErrorMsg="Characters 2 to 5 must be alphanumeric";
    public static String ProjName_SpecialCharErrorMsg="+ / = . - _ : @ special characters are allowed";

    //Aws
    public static String Aws_UploadMsg ="Upload succeeded";

    //EC2_Intance_Success_Msg
    public static String EC2_Instance_SuccessMsg="EC2 instance creation is in-Progress";
    public static String AWS_Container_URL="297036008099.dkr.ecr.us-west-2.amazonaws.com/usw2_boost1679902533539-ecr-repo";
    public static String AWS_Source_URL="https://git-codecommit.us-west-2.amazonaws.com/v1/repos/usw2_boost1679902533539-cc-repo";
    public static String AWS_Link="https://usw2devredcap.awsapps.com/console";
    public static String Infra_Settings_SuccessMsg="Infra Settings have been saved successfully";
    public static String Rstudio_Create_SuccessMsg ="User profile is being created. Try launching RStudio after sometime";




    //Checkout Messages

    public static String P1Disabled_P2Enabled_Msg="Files transfer has started for HPC enabled projects. Files cannot be copied to HPC disabled projects ["+UI_Project1+"]";
    public static String P1Disabled_CopyToBoth_Msg="Files transfer has started for Data Analytics. Files cannot be copied to HPC disabled projects ["+UI_Project1+"]";
    public static String P1Disabled_Copy_To_HPC_Msg="Files cannot be copied to HPC disabled projects ["+UI_Project1+"]";
    public static String P1Disabled_P2Enabled_CopyToBoth_Msg="Files transfer has started for Data Analytics and HPC enabled projects. Files cannot be copied to HPC disabled projects ["+UI_Project1+"]";
    public static String P2Enabled_CopyToBoth_Msg="Files transfer has started for Data Analytics and HPC enabled projects.";



}
