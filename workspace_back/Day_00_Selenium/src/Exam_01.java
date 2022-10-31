import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Exam_01 {

	public static void main(String[] args) throws Exception {
		WebDriver driver = (WebDriver) new ChromeDriver();
		driver.get("http://www.naver.com");
		
		
		WebElement searchInput = driver.findElement(By.id("query"));
		searchInput.sendKeys("it's almost lunch time!");
		WebElement searchClick = driver.findElement(By.id("search_btn"));
		searchClick.click();
		
		
		Thread.sleep(10000);
		driver.close();
	}

}
