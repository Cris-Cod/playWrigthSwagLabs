package pages;

import com.microsoft.playwright.Page;

public class ProductDetailPage {

    private Page page;

    public ProductDetailPage(Page page) {
        this.page = page;
    }

    String titleProductDatail = "//div[@data-test='inventory-item-name']";
    String btnAddCart = "//button[@data-test='add-to-cart']";
    String btnBackProducts = "#back-to-products";

    public String nameProductDetail(String name){
        String productName = page.locator(titleProductDatail).textContent();
        return productName;
    }

    public void selectBtnAddToCart(){
        page.locator(btnAddCart).click();
    }

    public void selectBtnBackProducts(){
        page.locator(btnBackProducts).click();
    }


}
