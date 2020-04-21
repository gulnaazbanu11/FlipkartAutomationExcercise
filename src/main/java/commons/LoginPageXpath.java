package commons;

import org.openqa.selenium.By;

public interface LoginPageXpath {
    public static By password = By.xpath("//span[text()='Enter Password']/../preceding-sibling::input");
    public static By userName = By.xpath("//span[contains(text(),'Enter Email')]/../preceding-sibling::input");
    public static By loginBtn=By.xpath("//span[contains(text(),'Login')]/parent::button");
    public static String userTab = "//div[contains(text(),'%s')]";
    public static By logout = By.xpath("//div[contains(text(),'Logout')]");
}
