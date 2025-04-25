package pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private Page page = null;
    public LoginPage(Page page){
        this.page = page;
    }

    String username = "id=user-name";
    String password = "id=password";
    String btnLogin = "#login-button";
    String titlePage = "//div[@class='login_logo']";

    String errorLockedUser = "//h3[@data-test='error']";


    public void loginUser(String usr, String pass){
        page.locator(username).fill(usr);
        page.locator(password).fill(pass);
        page.locator(btnLogin).click();

    }

    public String userError(String usr, String pass) {
        page.locator(username).fill(usr);
        page.locator(password).fill(pass);
        page.locator(btnLogin).click();
        String message = page.locator(errorLockedUser).textContent();
        return message;
    }

    public boolean titleLogin(){
        return page.locator(titlePage).isVisible();
    }
}
