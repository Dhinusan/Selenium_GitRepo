package Resources;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3.transfer.Copy;
import com.amazonaws.services.s3.transfer.Transfer;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class base {

    public static WebDriver driver;
    public Properties prop;

    ExtentManager Ex = new ExtentManager();
    public static Logger logger = Logger.getLogger(base.class);

    public static WebDriver getDriver() {
        return driver;
    }

    public String fileseperator = System.getProperty("file.separator");

    //public String reportDownloadpath= System.getProperty("user.dir") + fileseperator + Constant.
    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/Resources/data.properties");
        prop.load(fis);
        String browsername = prop.getProperty("browser");
        System.out.println("Browser is =" + browsername);
        String RunMode = prop.getProperty("RunMode");
        if (browsername.equals("chrome")) {
            if (RunMode.equalsIgnoreCase("Headless")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("download.default_directory", Paths.get("Downloads").toAbsolutePath().toString());
                options.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver(options);
            } else {
                //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +prop.getProperty("ChromePath"));
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("download.default_directory", Paths.get("Downloads").toAbsolutePath().toString());
                options.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver(options);
            }
        } else if (browsername.equals("edge")) {
            System.setProperty("webdriver.edge.driver", prop.getProperty("EdgePath"));
            driver = new EdgeDriver();
        }
        String envName = prop.getProperty("env");
        System.out.println("Environment is =" + envName);

        if (envName.equals("EUC1")) {
            driver.get(Constant.Euc1_Url);
        } else if (envName.equals("USE1")) {
            driver.get(Constant.Use1_Url);
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    //@BeforeTest
   /* public WebDriver launchBrowser1() throws IOException {
        driver =initializeDriver();
        DriverFactory.getInstance().setDriver(driver);
        return driver = DriverFactory.getInstance().getDriver();

    }
*/
    @BeforeMethod
    public void Before_Method() throws IOException {
        driver = initializeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        DOMConfigurator.configure(System.getProperty("user.dir") + "/src/main/java/Resources/Log4j.xml");
        logger.info("Test Case Started :" + getClass().getSimpleName());
        System.out.println("Test Case Started :" + getClass().getSimpleName());
    }

    @BeforeSuite
    public void Before_Suite() {
        Ex.Initialize_Extent_Report();
    }


    @AfterMethod
    public void After_Method(ITestResult result) {
        /*driver.manage().deleteAllCookies();
        driver.close();*/
        if (result.getStatus() == ITestResult.SUCCESS) {
            DOMConfigurator.configure(System.getProperty("user.dir") + "/src/main/java/Resources/Log4j.xml");
            logger.info("Test Case Completed SuccessFully:" + getClass().getSimpleName());
            System.out.println("Test Case Completed SuccessFully:" + getClass().getSimpleName());

        } else if (result.getStatus() == ITestResult.FAILURE) {
            DOMConfigurator.configure(System.getProperty("user.dir") + "/src/main/java/Resources/Log4j.xml");
            logger.error("Test Case Failed:" + getClass().getSimpleName(), result.getThrowable());
            System.out.println("Test Case Failed:" + getClass().getSimpleName());
        }

    }

    /*  @AfterClass
      public void suite() {
          Ex.extent.flush();
      }*/
    @AfterSuite
    public void suite() {
        Ex.extent.flush();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] screenshot() {

        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public void getScreenshotPath(String testcaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String c = testcaseName;
        DateFormat df = new SimpleDateFormat("yyyy_MMM_ddhh_mm_ss");
        Date d = new Date();
        String time = df.format(d);
        String destination = System.getProperty("user.dir") + "\\reports\\" + testcaseName + "screenshot" + time + ".png";
        FileUtils.copyFile(source, new File(destination));


    }


    public void create_aws_bucket(String bucket) {
        S3Client S3Client1 = S3Client.create();
        software.amazon.awssdk.services.s3.model.CreateBucketRequest cr = software.amazon.awssdk.services.s3.model.CreateBucketRequest.builder().bucket(bucket).build();

        try {
            S3Client1.createBucket(cr);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Step("Upload AWS Meta Data File From Backend")
    public void awsupload_meta_datafile(String bucketname, String filename, String filepath) {
        String fp = filepath + filename;
        String key = "metadata/" + filename;
        S3Client client = S3Client.builder().region(Region.EU_CENTRAL_1).build();
        PutObjectRequest request1 = PutObjectRequest.builder().bucket(bucketname).key(key).build();
        client.putObject(request1, RequestBody.fromFile(new File(fp)));


    }


    public void jsonfileupdate(String case_id, String fp, String filename) throws IOException, ParseException {
        String filepath = fp + filename;
        FileReader reader = new FileReader(filepath);
        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(reader);

        JSONObject jo1 = (JSONObject) jo.get("case");
        jo1.put("case_id", case_id);

        FileWriter writer = new FileWriter(filepath);
        writer.write(jo.toJSONString());
        writer.close();
    }


    public static int randomNumber() {
        int number = 0;
        Random rand = new Random();
        number = rand.nextInt(10000000);
        return number;

    }

    public void Change_File_Name(String FilePath, String File1, String File2) {

        Path source = Paths.get(FilePath + File1);
        Path target = Paths.get(FilePath + File2);

        try {

            Files.move(source, target);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(File1 + " is changed to " + File2);
        //Log.INFO(File1+" is changed to "+File2);
    }

    public void jsonmanipulator(String filepath, Object caseid, Object fileid, Object sampleid, Object aliquotid) throws IOException, ParseException {

        FileReader reader = new FileReader(filepath);
        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(reader);
        JSONObject jo1 = (JSONObject) jo.get("case");
        System.out.println("case is" + jo1);
        jo1.put("case_id", caseid);
        JSONObject jo2 = (JSONObject) jo.get("file");
        System.out.println("file is" + jo2);
        jo2.put("file_id", fileid);
        JSONObject jo3 = (JSONObject) jo.get("sample");
        System.out.println("sample is" + jo3);
        jo3.put("sample_id", sampleid);
        JSONObject jo4 = (JSONObject) jo.get("aliquot");
        System.out.println("aliquot is" + jo4);
        jo4.put("aliquot_id", aliquotid);
        FileWriter writer = new FileWriter(filepath);
        writer.write(jo.toJSONString());
        writer.close();
    }


    public String getbase64() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

    public static final String UploadPath = System.getProperty("user.dir") + "/UploadFiles/";
    public static final String DownloadPath = System.getProperty("user.dir") + "/Downloads//";

    public static final String UploadPath1 = Paths.get("UploadFiles//").toAbsolutePath().toString();
    public static final String DownloadPath1 = Paths.get("Downloads" + "//").toAbsolutePath().toString();

    public static LinkedHashMap<String, Map<String, String>> ExcelData = new LinkedHashMap<String, Map<String, String>>();

    @BeforeTest
    public LinkedHashMap<String, Map<String, String>> readTestData() throws IOException {

        File file = new File(System.getProperty("user.dir") + "/Test_Data/TestData.xlsx");
        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        wb.close();
        int lastRowNum = sheet.getPhysicalNumberOfRows();
        int lastCellNum = sheet.getRow(0).getPhysicalNumberOfCells();
        ArrayList<String> al = new ArrayList<String>();

        for (int i = 1; i < lastRowNum; i++) {
            al.add(sheet.getRow(i).getCell(0).toString());
            System.out.println("al" + al);

        }

        for (int i = 0; i < lastRowNum - 1; i++) {
            Map<String, String> datamap = new HashMap<>();

            for (int j = 1; j < lastCellNum; j++) {
                datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
                System.out.println("datamap" + datamap);

            }

            ExcelData.put(al.get(i), datamap);


        }
        return ExcelData;
    }

    public void aws_files() {
        S3Client client = S3Client.builder().region(Region.US_WEST_2).build();
        software.amazon.awssdk.services.s3.model.ListObjectsRequest request = software.amazon.awssdk.services.s3.model.ListObjectsRequest.builder().
                bucket("devusw2-boost1679902533539-ingest-s3-usw2").prefix("data/").build();
        ListObjectsResponse response = client.listObjects(request);
        List<S3Object> objects = response.contents();
        ListIterator<S3Object> listIterator = objects.listIterator();

        while (listIterator.hasNext()) {
            S3Object object = listIterator.next();
            System.out.println(object.key());
        }
    }





    public List<String> getObjectslistFromFolder( String folderKey) {
        AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
                .withBucketName("devuse1-pttpc1664267276776-ingest-s3-use1")
                .withPrefix(folderKey + "/");
        System.out.println(listObjectsRequest);

        List<String> keys = new ArrayList<>();

        ObjectListing objects = s3.listObjects(listObjectsRequest);
        System.out.println(objects);

        for (;;) {
            List<S3ObjectSummary> summaries = objects.getObjectSummaries();
            System.out.println(summaries.size());
            if (summaries.size() < 1) {
                break;
            }

            summaries.forEach(s -> keys.add(s.getKey()));
            objects = s3.listNextBatchOfObjects(objects);
        }
        System.out.println(keys);
        return keys;
    }

    /*public void copybucket() throws InterruptedException {
        String from_key = "data/";
        String from_bucket = "deveuc1-autom1651117170020-ingest-s3-euc1";
            String to_key = "data/";
        String to_bucket = "deveuc1-hpcen1652098515668-ingest-s3-euc1";
        AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.EU_CENTRAL_1).build();
        ObjectListing objectList = s3.listObjects(new ListObjectsRequest()
                .withBucketName(from_bucket).withPrefix("data/"));
        List<String> keys = new ArrayList<>();
        TransferManager xfer_mgr = TransferManagerBuilder.standard().withS3Client(s3).build();
        for (S3ObjectSummary objectSummary : objectList .getObjectSummaries()) {
            PutObjectRequest request1 = PutObjectRequest.builder().bucket(from_bucket).key(from_key).build();
            PutObjectRequest request2 = PutObjectRequest.builder().bucket(to_bucket).key(to_key).build();
            //client.putObject(request2, RequestBody.fromFile(new File(fp)));

            s3.copyObject(from_bucket, objectSummary.getKey(), to_bucket, to_key);
        }
    }*/


    @Step("Upload AWS Data File From Backend")
    public void awsupload_datafile(String bucketname, String filename, String filepath) {
        String[] File =filename.split(",");
        for(String Fi : File) {
            String fp = filepath + Fi;
            String key = "data/" + Fi;
            S3Client client = S3Client.builder().region(Region.EU_CENTRAL_1).build();
            PutObjectRequest request1 = PutObjectRequest.builder().bucket(bucketname).key(key).build();
            client.putObject(request1, RequestBody.fromFile(new File(fp)));
            Common_Functions.normalwait(5000);
            Ex.Extent_Info("Aws Upload File Name: " + filename);
        }
    }

    @Step("Upload AWS Data File From Backend")
    public void awsupload_metadatafile(String bucketname, String filename, String filepath) {
        String[] File =filename.split(",");
        for(String Fi : File) {
            String fp = filepath + Fi;
            String key = "metadata/" + Fi;
            S3Client client = S3Client.builder().region(Region.EU_CENTRAL_1).build();
            PutObjectRequest request1 = PutObjectRequest.builder().bucket(bucketname).key(key).build();
            client.putObject(request1, RequestBody.fromFile(new File(fp)));
            Common_Functions.normalwait(5000);
            Ex.Extent_Info("Aws Upload File Name: " + filename);
        }
    }
    public void multiple_data_file_Upload(){
        TransferManager Transfer_client=null;
        AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.EU_CENTRAL_1).build();
        Transfer_client = TransferManagerBuilder.standard().withS3Client(s3).build();
        File directory = new File("C:\\REDCAP\\src\\test\\Upload");
        List<File> fileList = Arrays.asList(directory.listFiles());
       Transfer_client.uploadFileList("deveuc1-autom1651117170020-ingest-s3-euc1","data/",directory,fileList);

    }


    public void aws_file_upload(String bucketname,String filename,String Path){
        String fp = Path + filename;
        String key = "data/" + filename;
        Region reg =Region.US_EAST_1;
        AmazonS3 S3 =AmazonS3ClientBuilder.standard().withCredentials(new InstanceProfileCredentialsProvider(false)).withRegion(Regions.US_EAST_1).build();
        S3.putObject(new com.amazonaws.services.s3.model.PutObjectRequest(bucketname,key,new File(fp)).withCannedAcl(CannedAccessControlList.PublicRead));
        /*// S3Client cli =S3.builder().region(reg).build();
        PutObjectRequest req =PutObjectRequest.builder().bucket(bucketname).key(filename).build();
        cli.putObject(req, RequestBody.fromFile(new File(fp)));*/
    }

    AWSS3Helper awsS3Helper = new AWSS3Helper();
    public void aws_file_upload2(String bucketname,String AWSfile, String filename, File Path,String Region){

        awsS3Helper.uploadToS3(bucketname,AWSfile, filename, Path,
                awsS3Helper.getS3Client(Region));
    }




}

