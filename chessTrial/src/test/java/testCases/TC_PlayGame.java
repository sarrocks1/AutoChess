package testCases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;

import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connectors.connector;
import converters.BsanToFenConverter;
import converters.WsanToFenConverter;
import pageObject.playPage;

public class TC_PlayGame extends BaseClass {
	
	@Test
	public void play() throws AWTException, InterruptedException, IOException {
		driver.get(url);
		playPage pg = new playPage(driver);
		pg.clickPlay();
		String[] letters = {"a","b","c","d","e","f","g","h"};
		String[] revletters = {"h","g","f","e","d","c","b","a"};
		ArrayList<String> larr = new ArrayList<String>();
		for(String i : letters){
			larr.add(i);
			
		}
		ArrayList<String> arr = new ArrayList<String>();
		for(String i : revletters) {
			arr.add(i);
		}
		String[][] mat1 = {
				{"-260,-240","-160,-240","-80,-240","0,-240","70,-240","140,-240","220,-240","300,-240"},
				{"-260,-160","-160,-160","-80,-160","0,-160","70,-160","140,-160","220,-160","300,-160"},
				{"-260,-80","-160,-80","-80,-80","0,-80","70,-80","140,-80","220,-80","300,-80"},
				{"-260,-20","-160,-20","-80,-20","0,-20","70,-20","140,-20","220,-20","300,-20"},
				{"-260,60","-160,60","-80,60","0,60","70,60","140,60","220,60","300,60"},
				{"-260,120","-160,120","-80,120","0,120","70,120","140,120","220,120","300,120"},
				{"-260,180","-160,180","-80,180","0,180","70,180","140,180","220,180","300,180"},
				{"-260,240","-160,240","-80,240","0,240","70,240","140,240","220,240","300,240"}
		};
	
		Thread.sleep(2000);
		
		WebElement guestBtn = driver.findElement(By.id("guest-button"));
		guestBtn.click();
		
		Thread.sleep(5000);
		
		try {
			WebElement board = driver.findElement(By.xpath("//*[@class='board']"));
			WebDriverWait wd = new WebDriverWait(driver,540);// We Play White
			BsanToFenConverter Bsan = new BsanToFenConverter();
			WsanToFenConverter Wsan = new WsanToFenConverter();
			Actions actions = new Actions(driver);
			connector con = new connector();
			int move_counter = 1;
			String xpathstr = "";
			boolean my_move = true;
			String cur_fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
			String move = "";
			xpathstr = "//*[@data-ply="+"'"+move_counter+"']";
			boolean started = con.startEngine();
			if(started){
				
				do {
					if(my_move) {
						con.sendCommand("isready");
						Thread.sleep(1000);
						con.sendCommand("position fen "+cur_fen+" w KQkq - 0 1");
						con.sendCommand("go movetime 5000");
						String out1 = con.getOutput(100);
						String[] sp = out1.split(" ");
						out1 = sp[1];
						System.out.println(out1);
						my_move = false;
						String from = mat1[8 - Integer.parseInt(Character.toString(out1.charAt(1)))][larr.indexOf(Character.toString(out1.charAt(0)))];
						String to = mat1[8 - Integer.parseInt(Character.toString(out1.charAt(3)))][larr.indexOf(Character.toString(out1.charAt(2)))];
						String[] fromsub = from.split(",");
						String[] tosub = to.split(",");
						int x1 = Integer.parseInt(fromsub[0]);
						int y1 = Integer.parseInt(fromsub[1]);
						int x2 = Integer.parseInt(tosub[0]);
						int y2 = Integer.parseInt(tosub[1]);
						actions.moveToElement(board, x1, y1).click().perform();   
						Thread.sleep(1000);
						actions.moveToElement(board, x2, y2).click().perform();
						Thread.sleep(1000);
						WebElement sanMove = driver.findElement(By.xpath(xpathstr));
						String san1 = sanMove.getText();							
						cur_fen = Wsan.sanFenConverter(cur_fen, san1);
						move_counter++;
						xpathstr = "//*[@data-ply="+"'"+move_counter+"']";
						
					}
					else {
						wd.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathstr)));
						Thread.sleep(1000);
						WebElement sanMove = driver.findElement(By.xpath(xpathstr));
						String san1 = sanMove.getText();
						cur_fen = Bsan.convert(cur_fen, san1);
						move_counter++;
						xpathstr = "//*[@data-ply="+"'"+move_counter+"']";
						my_move = true;
					}
					
					
					
					
					
				}while(true);
				
			}

		} catch (NoSuchElementException e1) {
			// TODO Auto-generated catch block
			WebElement board = driver.findElement(By.xpath("//*[@class='board flipped']")); 
			WebDriverWait wd = new WebDriverWait(driver,240);// We Play Black
			BsanToFenConverter Bsan = new BsanToFenConverter();
			WsanToFenConverter Wsan = new WsanToFenConverter();
			Actions actions = new Actions(driver);
			connector con = new connector();
			int move_counter = 1;
			String xpathstr = "";
			boolean my_move = false;
			String cur_fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
			String move = "";
			xpathstr = "//*[@data-ply="+"'"+move_counter+"']";
			boolean started = con.startEngine();
			if(started){
				while(true) {
					if(my_move) {
						con.sendCommand("isready");
						Thread.sleep(1000);
						con.sendCommand("position fen "+cur_fen+" b KQkq - 0 1");
						con.sendCommand("go movetime 5000");
						String out1 = con.getOutput(100);
						String[] sp = out1.split(" ");
						out1 = sp[1];
						System.out.println(out1);
						String from = mat1[Integer.parseInt(Character.toString(out1.charAt(1)))-1][arr.indexOf(Character.toString(out1.charAt(0)))];
						String to = mat1[Integer.parseInt(Character.toString(out1.charAt(3)))-1][arr.indexOf(Character.toString(out1.charAt(2)))];
						String[] fromsub = from.split(",");
						String[] tosub = to.split(",");
						int x1 = Integer.parseInt(fromsub[0]);
						int y1 = Integer.parseInt(fromsub[1]);
						int x2 = Integer.parseInt(tosub[0]);
						int y2 = Integer.parseInt(tosub[1]);
						actions.moveToElement(board, x1, y1).click().perform();   
						Thread.sleep(1000);
						actions.moveToElement(board, x2, y2).click().perform();
						Thread.sleep(1000);
						WebElement sanMove = driver.findElement(By.xpath(xpathstr));
						String san1 = sanMove.getText();							
						cur_fen = Bsan.convert(cur_fen, san1);
						move_counter++;
						xpathstr = "//*[@data-ply="+"'"+move_counter+"']";
						my_move = false;
						
					}
					else {
						wd.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathstr)));
						Thread.sleep(1000);
						WebElement sanMove = driver.findElement(By.xpath(xpathstr));
						String san1 = sanMove.getText();
						cur_fen = Wsan.sanFenConverter(cur_fen, san1);
						move_counter++;
						xpathstr = "//*[@data-ply="+"'"+move_counter+"']";
						my_move = true;
					}
				}
			
				
			
			}
		

		}
		 
			
		
		
		
	}
	
	
}
