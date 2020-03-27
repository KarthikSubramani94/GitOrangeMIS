package Annexure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class browserSelection implements matrixExcel {

	public Properties prop;
	public static WebDriver driver;
	private String dir=System.getProperty("user.dir");
	//private String browser = System.getProperty("browser");

	public WebDriver browserCombo() throws IOException
	{   prop=new Properties();
		FileInputStream fis=new FileInputStream(dir+"\\src\\main\\resources\\resource\\annexure.properties");
		prop.load(fis);
		String browser = prop.getProperty("browser");
		String baseUrl = prop.getProperty("baseurl");
		if(browser.contains("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", dir+"\\src\\main\\resources\\resource\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			if(browser.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver =new ChromeDriver(options);
			driver.manage().deleteAllCookies();
			driver.get(baseUrl);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}else if(browser.equals("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver",dir+"\\src\\main\\resources\\resource\\geckodriver.exe");
			driver=new FirefoxDriver();
			driver.manage().deleteAllCookies();
			driver.get(baseUrl);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}else
		{
			System.out.print("Please provide the type of browser to execute test");
			driver = null;
		}
		return driver;
	}

	
	public File getExcelFile(String Filename) throws IOException
	{
		prop=new Properties();
		FileInputStream fisp=new FileInputStream(dir+"\\src\\main\\resources\\resource\\annexure.properties");
		prop.load(fisp);
		String filepath=prop.getProperty("login");
		fisp.close();
		File file = new File(dir+filepath+ "\\" + Filename);
		return file;
	}

	public Workbook getWorkbook(String Filename) throws IOException 
	{
		File file=getExcelFile(Filename);
		FileInputStream fis = new FileInputStream(file);
		Workbook wb = null;
		String fileExt = Filename.substring(Filename.indexOf("."));
		if (fileExt.equals(".xlsx")) {
			wb = new XSSFWorkbook(fis);
		} else if (fileExt.equals(fis)) {
			wb = new HSSFWorkbook(fis);
		}
		return wb;
	}
	
	public Sheet getSheet(String sheetname, String Filename) throws IOException 
	{
		Workbook wb=getWorkbook(Filename);
		Sheet sheet = wb.getSheet(sheetname);
		return sheet;
	}

	public int getRowCount(String sheetname, String Filename)
			throws IOException {
		Sheet sheet = getSheet(sheetname, Filename);
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		return rowCount;
	}
	
	public String readData(String sheetname, String Filename,int row, int colno)
			throws IOException {
		Sheet sheet = getSheet(sheetname, Filename);
		String value=sheet.getRow(row).getCell(colno).getStringCellValue();
		return value;
	}
	
	public void writeData(String sheetname, String Filename,int row, int colno, String value)
			throws IOException {
		File file=getExcelFile(Filename);
		Workbook ws=getWorkbook(Filename);
		Sheet sheet = ws.getSheet(sheetname);
		Cell cell = sheet.getRow(row).createCell(colno);
		cell.setCellValue(value);
		FileOutputStream fos = new FileOutputStream(file);
		ws.write(fos);	
	}
	
	public void getScreenshot(String result) throws IOException
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(dir+"\\Screenshots\\"+result+"screenshot.png"));
		
	}
	
}
