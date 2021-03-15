package testCases;

import java.awt.AWTException;


import org.openqa.selenium.By;

import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import pageObject.playPage;

public class TC_PlayGame extends BaseClass {
	
	@Test
	public void play() throws AWTException, InterruptedException {
		driver.get(url);
		playPage pg = new playPage(driver);
		pg.clickPlay();
		
		Thread.sleep(2000);
		
		WebElement guestBtn = driver.findElement(By.id("guest-button"));
		guestBtn.click();
		
		Thread.sleep(5000);
		
		
			try {
				WebElement board = driver.findElement(By.xpath("//*[@class='board']"));                       // We Play White
				Actions actions = new Actions(driver);
				actions.moveToElement(board, 70, 180).click().perform();   
				Thread.sleep(1000);
				actions.moveToElement(board, 70, 60).click().perform();                                               
				WebDriverWait wd = new WebDriverWait(driver,240);
				wd.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@data-ply='2']")));        
				Thread.sleep(1000);
				actions.moveToElement(board, 220, 240).click().perform();
				Thread.sleep(1000);
				actions.moveToElement(board, 140, 120).click().perform();
				wd.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@data-ply='4']")));
				Thread.sleep(1000);
				actions.moveToElement(board, 140, 240).click().perform();
				Thread.sleep(1000);
				actions.moveToElement(board, -160, -20).click().perform();
			} catch (NoSuchElementException e1) {
				// TODO Auto-generated catch block
				WebElement board = driver.findElement(By.xpath("//*[@class='board flipped']"));                // We Play Black
				Actions actions = new Actions(driver);
				WebDriverWait wd = new WebDriverWait(driver,240);
				wd.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@data-ply='1']")));
				Thread.sleep(1000);
				actions.moveToElement(board, -170, 240).click().perform();
				Thread.sleep(1000);
				actions.moveToElement(board, -90, 120).click().perform();
				wd.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@data-ply='3']")));
				Thread.sleep(1000);
				actions.moveToElement(board, -170, 180).click().perform();
				Thread.sleep(1000);
				actions.moveToElement(board, -170, 120).click().perform();
				wd.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@data-ply='5']")));
				Thread.sleep(1000);
				actions.moveToElement(board, -80, 240).click().perform();
				Thread.sleep(1000);
				actions.moveToElement(board, -170, 180).click().perform();
				wd.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@data-ply='7']")));
				Thread.sleep(1000);
				actions.moveToElement(board, 70, 180).click().perform();
				Thread.sleep(1000);
				actions.moveToElement(board, 70, 120).click().perform();
			}
			
		
		
		
	}
	
	
}
