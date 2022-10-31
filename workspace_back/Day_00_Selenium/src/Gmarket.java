import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Gmarket {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = (WebDriver) new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		driver.get("https://signinssl.gmarket.co.kr/login/login?url=http://www.gmarket.co.kr/");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id")));
		WebElement inputId = driver.findElement(By.id("id"));
		WebElement inputPw = driver.findElement(By.id("pwd"));
		WebElement btnLogin = driver.findElement(By.className("button_login"));
		
		inputId.sendKeys(Account.id);
		inputPw.sendKeys(Account.pw);
		btnLogin.click();
		
		driver.get("http://promotion.gmarket.co.kr/Event/pluszone.asp");
		
		driver.switchTo().frame("AttendRulletFrame");
		
		WebElement btnStart = driver.findElement(By.className("button_start"));
		btnStart.click();
		
		Thread.sleep(5000);
		driver.close();
	}

}
