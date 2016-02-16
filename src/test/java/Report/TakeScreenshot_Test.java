package Report;

import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TakeScreenshot_Test {

	static long endTime = System.currentTimeMillis() / 1000L;

	static String filePath = "C:/Users/admin/.jenkins/jobs/CXP_DemoLoadTest/workspace/";
	static String aggrgateCSVPath = "C:/Users/admin/.jenkins/jobs/CXP_DemoLoadTest/builds/";
	static WebDriver driver;
	static String graph =".nav-tabs-content>div:nth-child(1)>div:nth-child(1)>.clearfix>ul>li:nth-child(2)>.chart>div:nth-child(2)>svg";
	
	@Test
	public static void loadTestReport() throws IOException {

		DownloadStatusAfterTest.statusPagesafterTest();
		Delta.sspDelta();
		
		//driver = new FirefoxDriver();
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		TakeScreenshot_Test ts = new TakeScreenshot_Test();
		//driver.get("https://cengagecloud.tv.appneta.com/app/CXP_Stage_App/app_server#fs="+Report.startTime + "&fe=" + endTime + "&filter=cxp");
		
		//ts.login();
		//ts.takeScreenshot("AppServer");
		//ts.hosts();
		//ts.takeScreenshot("Hosts");
		//driver.close();
		Report.createHTML();
		
		
	}
	
	public void login(){
		driver.findElement(By.id("username")).sendKeys("sireesha.vasamsetti@contractor.cengage.com");
		driver.findElement(By.id("password")).sendKeys("Appneta1");
		driver.findElement(By.id("submit")).click();
		
	}
	
	public void hosts(){
		//driver.findElement(By.cssSelector(".app-nav>a:nth-child(8)>.text")).click();
		driver.get("https://cengagecloud.tv.appneta.com/app/CXP_Stage_App/hosts#fs="+Report.startTime + "&fe=" + endTime + "&filter=cxp");
		while(!driver.findElement(By.cssSelector(graph)).isDisplayed()){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

	public void takeScreenshot(String title) {
		File file = new File(filePath);
		boolean exists = file.exists();
		if (!exists) {
			new File(filePath).mkdir();
		}

		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			String saveImgFile = filePath + title + "_screenshot.png";
			System.out.println("[INFO]: Save Image File Path : " + saveImgFile);
			FileUtils.copyFile(scrFile, new File(saveImgFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
