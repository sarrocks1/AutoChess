package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class playPage {
	WebDriver ldriver;
	public playPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="//button[@class='ui_v5-button-component ui_v5-button-primary ui_v5-button-large ui_v5-button-full']")
	WebElement playBtn;
	
	public void clickPlay() {
		playBtn.click();
	}
	
}
