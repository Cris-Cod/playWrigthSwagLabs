package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class ProductsPage {

    private Page page = null;
    ProductDetailPage productDetailPage;


    public ProductsPage(Page page){
        this.page = page;
    }

    String titleProducts = "//span[@data-test='title']";
    String btnBurger = "#react-burger-menu-btn";
    String btnLogout = "#logout_sidebar_link";
    String descriptionProducts = "//div[@class='inventory_item_label']";
    String listProducts = "//div[@class='inventory_item_label']/a";
    String btnCart = "//a[@data-test='shopping-cart-link']";


    //item_4_title_link

    public boolean isTitleVisible(){
        return page.waitForSelector(titleProducts).isVisible();
    }

    public void clicKMenuHarguger(){
        page.locator(btnBurger).click();
    }

    public void clickBtnLogout(){
        page.locator(btnLogout).click();
    }

    public void selectProduct(String nameProduct){
        Locator allTitles = page.locator(listProducts);
        List<String> list_title = allTitles.allTextContents();

        for (int i = 0; i < list_title.size(); i++) {
            if(list_title.get(i).equalsIgnoreCase(nameProduct)){
                allTitles.nth(i).click();
                break;
            }
        }
    }

    public void clickBtnCart(){
        page.locator(btnCart).click();
    }

    public void selectSeveralProducts(List<String> products){
        productDetailPage = new ProductDetailPage(page);
        Locator allTitlesProducts = page.locator(listProducts);
        List<String> titles = allTitlesProducts.allTextContents();

        for (int i = 0; i < titles.size(); i++) {
            String title = titles.get(i);
            if(title == null) continue;

            for (String product : products) {
                if(title.contains(product)){
                    allTitlesProducts.nth(i).click();
                    productDetailPage.selectBtnAddToCart();
                    productDetailPage.selectBtnBackProducts();
                    break;
                }
            }
        }
    }
}


