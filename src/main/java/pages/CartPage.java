package pages;

import com.microsoft.playwright.Page;

public class CartPage {

    private Page page;

    public CartPage(Page page) {
        this.page = page;
    }

    String nameProductCart = "//div[@data-test='inventory-item-name']";
    String priceProduct = "//div[@data-test='inventory-item-price']";

    public String nameOneProduct(String nProduct){
        String cartTitleP = page.locator(nameProductCart).textContent();
        return cartTitleP;
    }

    public String priceOneProduct(String priceOne){
        String price = page.locator(priceProduct).textContent();
        String priceSplit = price.replace("$", "");
        return priceSplit;
    }


}
