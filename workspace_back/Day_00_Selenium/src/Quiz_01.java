import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Quiz_01 {

	public static void main(String[] args) throws InterruptedException {
		
		
		
		WebDriver driver = (WebDriver) new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));//페이지 로딩시간 얼마나 기다릴지 정하는거
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		driver.get("https://www.interpark.com/member/login.do?_method=initial&GNBLogin=Y&&wid1=wgnb&wid2=wel_login&wid3=login&imfsUserPath=http%3A%2F%2Fwww.interpark.com%2Fmalls%2Findex.html%3F%26smid1%3Dgnb%26smid2%3Dlogo%26smid3%3Dwelcome&historyGoCnt=-1");
		
		driver.switchTo().frame("loginIframe");
		
		WebElement inputId = driver.findElement(By.id("userId"));
		WebElement inputPw = driver.findElement(By.id("userPwd"));
		WebElement btnLogin = driver.findElement(By.id("btn_login"));
		
	
		inputId.sendKeys(Account.id);
		inputPw.sendKeys(Account.pw);
		btnLogin.click();
		
		Thread.sleep(10000);
		driver.close();
	}

}
