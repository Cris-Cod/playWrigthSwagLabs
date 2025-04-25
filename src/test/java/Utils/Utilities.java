package Utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;


import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Utilities {

    private Page page = null;
    private int screensCounter = 1;
    public Utilities(Page page){
        this.page = page;
    }

    public void takeScreenShot(Page page, String testName, String actionName){
        try {
            page.waitForTimeout(500);
            String filePath = generarNombreImages(testName, actionName);
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filePath)));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void locatorTakeScreenShot(Locator locator, String testName, String actionName){
        try {
            page.waitForTimeout(500);
            String filePath = generarNombreImages(testName, actionName);
            locator.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(filePath)));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void adjuntarCapturasDeCarpeta(String nombrePrueba) {
        File carpeta = new File("images/" + nombrePrueba);
        File[] imagenes = carpeta.listFiles((dir, name) -> name.endsWith(".png"));

        if (imagenes != null) {
            List<File> listaImagenes = Arrays.asList(imagenes);
            listaImagenes.sort(Comparator.comparing(File::getName));
            for (File imagen : imagenes) {
                try (FileInputStream input = new FileInputStream(imagen)) {
                    Allure.addAttachment(imagen.getName(), "image/png", input, ".png");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No se encontraron imágenes en: " + carpeta.getAbsolutePath());
        }
    }

    private String generarNombreImages(String testName, String action){
        if (action == null || action.isEmpty()) {
            action = "default";
        }
        String numero = String.format("%02d", screensCounter);
        String filePath = "images/" + testName + "/" + numero + "_" + action + ".png";

        screensCounter++;

        return filePath;
    }


}
