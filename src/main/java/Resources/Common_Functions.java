package Resources;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

public class Common_Functions extends base {
    public WebDriver getWebdriver() {

        return driver;
    }

    public void ScrollView(WebElement Element) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView;", Element);
    }

    public void ScrollView_Action(WebElement Ele){
        Actions a = new Actions(driver);
        a.moveToElement(Ele).build().perform();
    }

    public void SelectDrpDwnValue(WebElement DrpDwn, String Value) throws InterruptedException {
        jsclick(DrpDwn);
        normalwait(1000);
        WebElement ele = driver.findElement(By.xpath("//span[contains(text(),'" + Value + "')]"));
        jsclick(ele);
        normalwait(1000);
    }

    public void jsclick(WebElement Element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(Element));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0]. click();", Element);
    }

    public String hexcolor(WebElement Element) {
        String color = Element.getCssValue("color");
        String HexValue = Color.fromString(color).asHex();
        return HexValue;
    }

    public void clickableEle(WebElement Element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(Element));
        //Element.click();
    }

    public void Element_ToBe_Clickable(WebElement Element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(Element));
    }

    public void Ele_Click(WebElement Element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(Element));
        Element.click();
    }

    //Wait For Give Time
    public static void normalwait(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static String getcurrent_date_time(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
        String Date_Time =dtf.format(now);
        String Date_Time1=Date_Time.replace(" ","_").replace("-","_").replace(":","_");
        return Date_Time1;

    }
    public String getcurrentdate() {
        LocalDateTime instance = LocalDateTime.now();

        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");

        String formattedString = formatter.format(instance);
        System.out.println(formattedString);
        String[] time = formattedString.split(" ");
        String Time = time[1];

        return Time;
    }

    public void send_keys(WebElement Element, String Value) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(Element));
        Element.sendKeys(Value);

    }

    public static void page_loader() {

        JavascriptExecutor j = (JavascriptExecutor) driver;
        if (j.executeScript("return document.readyState").toString().equals("complete")) {
        }
        for (int i = 0; i < 50; i++) {
            normalwait(1000);
            //again check page state
            if (j.executeScript("return document.readyState").toString().equals("complete")) {
                break;
            }
        }
    }

    public void js_sendkeys(WebElement element,String  text){

        ((JavascriptExecutor)driver).executeScript("arguments[0].value = arguments[1];", element,
                text);
    }

    public void double_click(WebElement Element){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(Element));
        Actions act = new Actions(driver);
        act.doubleClick(Element).build().perform();

    }

    public void ActionClick(WebElement Element){
        Actions act = new Actions(driver);
        act.click(Element).build().perform();
    }

    public static void navigate_back(){
        driver.navigate().back();
        normalwait(2000);

    }

    public static void mouse_hover(WebElement Element){
        Actions act = new Actions(driver);
        act.moveToElement(Element).perform();

    }

    public void rename_files(String filePath,String file,String rename){
        File f= new File(filePath+file);
        File name =new File(filePath+rename);
        f.renameTo(name);
    }

    public void list_files(String filePath){
        File file= new File(filePath);
        String contents[] =file.list();
        for (int i=0;i<contents.length;i++){
            System.out.println(contents[i]);
        }
    }

    public void close_file(String filename,String filePath) throws IOException {
        try{
            File f = new File(filePath+filename);
            FileOutputStream fo = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            FileReader f = new FileReader(filePath);
            BufferedReader br = new BufferedReader(f);
            br.close();

        }
    }
    public void deleteAllexisitingFiles(String filePath) throws IOException {
            File file = new File(filePath);
            System.out.println(file);
            FileUtils.cleanDirectory(file);


    }

    public static String get_filename(String filePath){
        File file = new File(filePath);
        File[] files =file.listFiles();
        String Filename =files[0].getName();
        System.out.println(Filename);
        return Filename;
    }

    public String getcurrenttime() {
        LocalDateTime instance = LocalDateTime.now();

        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");

        String formattedString = formatter.format(instance);
        String[] time = formattedString.split(" ");
        String Time = time[1];

        return Time;
    }


}
