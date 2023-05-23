package automation_project;

import java.io.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import jxl.*;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class QAProject {
	public WebDriver driver;

	@BeforeTest
	public void launcher() throws Exception {
		// Taking data from an Excel sheet
		FileInputStream f = new FileInputStream("C:\\Users\\AKR23\\OneDrive\\Desktop\\projectinput.xls");
		Workbook wb = Workbook.getWorkbook(f);
		Sheet s = wb.getSheet("Sheet1");
		System.setProperty(s.getCell(0, 0).getContents(), s.getCell(0, 1).getContents());
		driver = new ChromeDriver();
		driver.get("https://test-nf.com/english.html");
		//driver.get(s.getCell(0, 2).getContents());
		driver.manage().window().maximize();

	}

	@Test
	// To check the availability of all the tabs
	public void scenario1() {
		PageObjectClass P = PageFactory.initElements(driver, PageObjectClass.class);
		SoftAssert A = new SoftAssert();
		boolean Homeavailability = P.home.isDisplayed();
		A.assertEquals(Homeavailability, true);
		System.out.println("Home tab is available");

		boolean FootballAvailability = P.football.isDisplayed();
		A.assertEquals(FootballAvailability, true);
		System.out.println("Football tab is available");

		boolean BasketballAvailability = P.basketball.isDisplayed();
		A.assertEquals(BasketballAvailability, true);
		System.out.println("Basketball tab is available");

		boolean CricketAvailability = P.cricket.isDisplayed();
		A.assertEquals(CricketAvailability, true);
		System.out.println("Cricket tab is available");

		boolean Cybersport = P.cybersport.isDisplayed();
		A.assertEquals(Cybersport, true);
		System.out.println("Cybersports tab is available");
		System.out.println();

	}

	@Test
	// To check whether URL of each tab contains the tab name and store every url
	// into excel sheet
	public void scenario2() throws Exception {
		PageObjectClass P = PageFactory.initElements(driver, PageObjectClass.class);
		SoftAssert A1 = new SoftAssert();
		// Home page
		String home = driver.getCurrentUrl();
		boolean Url1 = home.contains("Home");
		A1.assertEquals(Url1, true);
		if (Url1)
			System.out.println("Home URL CONTAINS TAB NAME");
		// Selecting Football Tab
		P.football.click();

		String football = driver.getCurrentUrl();
		boolean Url2 = football.contains("football");
		A1.assertEquals(Url2, true);
		if (Url2)
			System.out.println("FOOTBAL URL CONTAINS TAB NAME");
		// Selecting Basketball Tab
		P.basketball.click();
		String basketball = driver.getCurrentUrl();
		boolean Url3 = basketball.contains("busketball");
		A1.assertEquals(Url3, true);
		if (Url3) {
			System.out.println("BASKETBALL URL CONTAINS TAB NAME");
		}
		// Selecting Kriket Tab
		P.cricket.click();
		String cricket = driver.getCurrentUrl();
		boolean Url4 = cricket.contains("kriket");
		A1.assertEquals(Url4, true);
		if (Url4)
			System.out.println("CRICKET URL CONTAINS TAB NAME");
		// Selecting Cybersport Tab
		P.cybersport.click();
		String cybersport = driver.getCurrentUrl();
		boolean Url5 = cybersport.contains("cibersport");
		A1.assertEquals(Url5, true);
		if (Url5)
			System.out.println("CYBERSPORT URL CONTAINS TAB NAME");

		// Storing the URL IN EXCEL SHEET
		FileOutputStream fo = new FileOutputStream("C:\\\\Users\\\\AKR23\\\\OneDrive\\\\Desktop\\\\TestResult.xls");
		WritableWorkbook wb = Workbook.createWorkbook(fo);
		WritableSheet ws = wb.createSheet("Result", 1);
		Label l = new Label(0, 0, home);
		Label l1 = new Label(0, 1, football);
		Label l2 = new Label(0, 2, basketball);
		Label l3 = new Label(0, 3, cricket);
		Label l4 = new Label(0, 4, cybersport);

		// Adding Cells into the Sheet
		System.out.println("Adding url to cells");
		ws.addCell(l);
		ws.addCell(l1);
		ws.addCell(l2);
		ws.addCell(l3);
		ws.addCell(l4);
		System.out.println("Writing Data Into Excel Sheet....");
		wb.write();
		// Closing the Workbook
		wb.close();
		A1.assertAll();

	}

	@AfterTest
	// To close the Browser After Testing
	public void close() {
		driver.quit();
	}
}