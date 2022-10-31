import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Exam_02 {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = (WebDriver) new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));//페이지 로딩시간 얼마나 기다릴지 정하는거
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		driver.get("https://nid.naver.com/nidlogin.login?mode=form&url=https%3A%2F%2Fwww.naver.com");
		
		WebElement inputId = driver.findElement(By.id("id"));
		WebElement inputPw = driver.findElement(By.id("pw"));
		WebElement btnLogin = driver.findElement(By.id("log.login"));
//		inputId.sendKeys(Account.id);
//		inputPw.sendKeys(Account.pw);
//		네이버는 sendkey를 통한 입력을 타이핑 속도 측정으로 로봇을 검출할 가능성이 있음
		
	
		
		js.executeScript("document.getElementById('id').value=arguments[0]", Account.id);
		js.executeScript("document.getElementById('pw').value=arguments[0]", Account.pw);
		
		btnLogin.click();
		
		driver.get("https://mail.naver.com/");

		//class이름을 list로 확인
//		List<WebElement> btnWriteToSelf = driver.findElement(By.className("btn_import"));
//		System.out.println(btnWriteToSelf.size());
		WebElement btnWriteToSelf = driver.findElement(By.className("btn_import"));
		btnWriteToSelf.click();
		
//		Thread.sleep(3000); 임시로 시간 딜레이를 시키는 것
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("subject"))); //창 뜨고 입력 해주는 시간 조절 문구
		
		
		WebElement inputSubject = driver.findElement(By.id("subject"));
		inputSubject.sendKeys("메일의 제목입니다.");
		

		
		driver.switchTo().frame("se2_iframe");
		
//		메일 내용을 입력하는 공간의 클래스 이름 개수를 찾는 곳.
//		List<WebElement> inputContent = driver.findElements(By.className("se2_inputarea"));
//		System.out.println(inputContent.size());
		
		WebElement inputContent = driver.findElement(By.className("se2_inputarea"));
		inputContent.sendKeys("ㄴㅁㅇㅁㄴㅇㄴ입력함 메일 올 ㅋㅋㅋ");
		
		driver.switchTo().defaultContent();
		
		WebElement btnSend = driver.findElement(By.id("sendBtn"));
		btnSend.click();
		
		
		Thread.sleep(10000);
		driver.close();
		
	}

}
