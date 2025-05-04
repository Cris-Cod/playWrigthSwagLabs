package pages;

import com.microsoft.playwright.Page;

public class OverviewPage {

    private Page page;

    public OverviewPage(Page page) {
        this.page = page;
    }

    //Total: $36.69
    //Free Pony Express Delivery!
    //Checkout: Overview

    String textTitle = "//span[@data-test= 'title']";
    String shippingInformation = "//div[@data-test='shipping-info-value']";
    String total = "//div[@data-test='total-label']";
    String btnFinish = "#finish";

    public String setTextTile(){
        String title = page.locator(textTitle).textContent();
        return title;
    }

    public String setShipping(){
        String textShipping = page.locator(shippingInformation).textContent();
        return textShipping;
    }

    public String setTotal(){
        String textTotal = page.locator(total).textContent();
        return textTotal;
    }

    public void setBtnFinish(){
        page.locator(btnFinish).click();
    }

}
