package pages;

import com.microsoft.playwright.Page;

public class CompletePage {

    private Page page;

    public CompletePage(Page page) {
        this.page = page;
    }


    //Thank you for your order!

    //Checkout: Complete!

    String textTitle = "//span[@data-test= 'title']";
    String textThankYou = "//h2[@data-test='complete-header']";
    String btnBackHome = "#back-to-products";

    public String setTitlePage(){
        String text = page.locator(textTitle).textContent();
        return text;
    }

    public String setTextThankYou(){
        String text = page.locator(textThankYou).textContent();
        return text;
    }

    public void setBtnBackHome(){
        page.locator(btnBackHome).click();
    }
}
