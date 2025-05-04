package pages;

import com.microsoft.playwright.Page;

public class CheckoutPage {

    private Page page;

    public CheckoutPage(Page page) {
        this.page = page;
    }

    //Checkout: Your Information

    String textTitle = "//span[@data-test= 'title']";
    String firstName = "#first-name";
    String lastName = "#last-name";
    String zipCode = "#postal-code";
    String btnContinue = "#continue";


    public String setTextTitle(){
        String title = page.locator(textTitle).textContent();
        return title;
    }

    public void setForm(String name, String last, String zip){
        page.locator(firstName).fill(name);
        page.locator(lastName).fill(last);
        page.locator(zipCode).fill(zip);
        page.locator(btnContinue).click();
    }
}
