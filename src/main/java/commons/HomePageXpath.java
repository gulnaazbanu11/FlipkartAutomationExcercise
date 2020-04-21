package commons;

import org.openqa.selenium.By;

public interface HomePageXpath {
    public static By searchBox=By.xpath("//input[@title='Search for products, brands and more']");
    public static By nameOfProducts=By.xpath("//div[@class='_3wU53n']");
    public static By priceOfProducts=By.xpath("//div[@class='_1vC4OE _2rQ-NK']");
    public static By nameInCheckoutScreen=By.xpath("//span[@class='_35KyD6']");
    public static By priceInCheckoutScreen=By.xpath("//div[@class='_1vC4OE _3qQ9m1']");
    public static By searchBtn = By.xpath("//button[@class='vh79eN']");
}
