package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

public class CartPage {

    private Page page;

    public CartPage(Page page) {
        this.page = page;
    }

    String nameProductCart = "//div[@data-test='inventory-item-name']";
    String priceProduct = "//div[@data-test='inventory-item-price']";
    String bntCheckout = "#checkout";

    public String nameOneProduct(String nProduct){
        String cartTitleP = page.locator(nameProductCart).textContent();
        return cartTitleP;
    }

    public String priceOneProduct(String priceOne){
        String price = page.locator(priceProduct).textContent();
        String priceSplit = price.replace("$", "");
        return priceSplit;
    }

    public List<String> validateNameProducts(List<String> nameProducts){
        Locator names = page.locator(nameProductCart);
        List<String> allNames = names.allTextContents();

        List<String> namesCart = new ArrayList<>();
        for (String allName : allNames) {
            if(nameProducts.contains(allName)){
                namesCart.add(allName);
            }
        }
        return namesCart;
    }

    public void clickBtnCheckout(){
        page.locator(bntCheckout).click();
    }
}
