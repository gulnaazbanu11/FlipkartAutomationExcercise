package commons;

import org.openqa.selenium.By;

public class WebElementLocators {

	public interface FlipkartLogin
	{
		public static By login=By.xpath("//a[@class='_3Ep39l']");
		public static By userName=By.xpath("//input[@class='_2zrpKA _1dBPDZ']");
		public static By password=By.xpath("//input[@class='_2zrpKA _3v41xv _1dBPDZ']");
		public static By loginBtn=By.xpath("//button[@class='_2AkmmA _1LctnI _7UHT_c']");
		public static By userTab = By.xpath("//div[contains(text(),'Gulnaaz')]");
		public static By logout = By.xpath("//div[contains(text(),'Logout')]");
		
	}
	
	public interface FlipkartSearch
	{
		public static By searchBox=By.xpath("//input[@title='Search for products, brands and more']");
		public static By nameOfProducts=By.xpath("//div[@class='_3wU53n']");
		public static By priceOfProducts=By.xpath("//div[@class='_1vC4OE _2rQ-NK']");
		public static By nameInCheckoutScreen=By.xpath("//span[@class='_35KyD6']");
		public static By priceInCheckoutScreen=By.xpath("//div[@class='_1vC4OE _3qQ9m1']");
	}
}
