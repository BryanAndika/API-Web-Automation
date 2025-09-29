package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static helper.Utility.driver;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WebPage {

    By input_username = By.id("user-name");
    By input_pwd = By.id("password");
    By btn_login = By.id("login-button");
    By icon_cart = By.xpath("//select[@class='product_sort_container']");
    By text_err_msg(String msg){
        return By.xpath("//*[contains(text (), '" + msg + "')]");
    }
    By icon_cart_item = By.xpath("//span[@class='shopping_cart_badge']");
    By addToCart = By.xpath("(//*[text()='Add to cart'])[1]");
    By removeToCart = By.xpath("(//*[text()='Remove'])[1]");


    public void openBrowser(){
        driver.get("https://www.saucedemo.com/");
    }

    public void setInput_username(String username){
        driver.findElement(input_username).sendKeys(username);
    }

    public void setInput_pwd(String pwd){
        driver.findElement(input_pwd).sendKeys(pwd);
    }

    public void clickBtnLogin(){
        driver.findElement(btn_login).click();
    }

    public void assertHomePage(){
        driver.findElement(icon_cart).isDisplayed();
    }

    public void assertErrMsg(String errmsg){
        driver.findElement(text_err_msg(errmsg)).isDisplayed();
    }

    public void clickAddToCart(){
        driver.findElement(addToCart).click();
    }

    public void removeAddToCart(){
        driver.findElement(removeToCart).click();
    }

    public void assertCartItem(String cartItem){
        String itemExpected = cartItem;
        String itemActual = driver.findElement(icon_cart_item).getText();
        assertThat(itemActual).isEqualTo(itemExpected);
    }

}
