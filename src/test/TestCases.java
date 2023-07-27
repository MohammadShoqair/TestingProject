package test;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class TestCases extends Parameters {

	// Test case #1 : Checking whether the title is correct
	@Test(priority = 1)
	public void checkTitle() {
		String title = driver.getTitle();
		MySoftAssert.assertEquals(title, expectedTitle, "Title is incorrect");
		MySoftAssert.assertAll();
	}

	// Test case #2 : Checking whether pictures in the website have the same name
	// This will be done through taking the attribute that has the name of them
	@Test(priority = 2)
	public void checkPics() {
		List<WebElement> pics = driver.findElements(By.className("img-fluid"));
		boolean myPicCheck1 = pics.get(0).getAttribute("src") == pics.get(1).getAttribute("src");
		boolean myPicCheck2 = pics.get(1).getAttribute("src") == pics.get(2).getAttribute("src");
		boolean myPicCheck3 = pics.get(0).getAttribute("src") == pics.get(2).getAttribute("src");

		MySoftAssert.assertEquals(myPicCheck1, false, "Pic 0 with 1");
		MySoftAssert.assertEquals(myPicCheck2, false, "Pic 1 with 2");
		MySoftAssert.assertEquals(myPicCheck3, false, "Pic 0 with 2");

		MySoftAssert.assertAll();

	}
	
	//Test case #3 : Checking whether email field sends the correct form of emails
	@Test(priority = 3)
	public void checkEmails() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		String [] emails= {"a@gmail.com","ss@","Dh.com","shoqir192@gmail.com"};
		Random random=new Random();
		int index= random.nextInt(4);
		
		driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[2]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"recipient-email\"]")).sendKeys(emails[index]);
		
//		String myValue= driver.findElement(By.xpath("\"//*[@id=\\\"recipient-email\\\"]\"")).getAttribute("value").toString();
		
		String regex= "^[A-Za-z0-9+-._]+@(.+)+.(.+)";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher =pattern.matcher(emails[index]);
		
		System.out.println("Is the email: "+emails[index]+" valid? "+matcher.matches());
		
		boolean myCheckProcess= matcher.matches();
		
		MySoftAssert.assertEquals(myCheckProcess, true, "Invalid email");
		MySoftAssert.assertAll();
		
	}

}
