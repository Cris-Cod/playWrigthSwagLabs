package Utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelperScreenShot {

    public static void takeStepScreenshot(Page page, String stepName) {
        try {
            // Crear carpeta si no existe
            Files.createDirectories(Paths.get("screenshots"));

            // Formato de fecha y hora
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = String.format("screenshots/%s_%s.png", stepName, timestamp);

            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(fileName)));
            System.out.println("📸 Screenshot tomado: " + fileName);
        } catch (IOException e) {
            System.err.println("❌ Error al guardar screenshot: " + e.getMessage());
        }
    }

    public static void takeElementScreenshot(Locator locator, String elementName) {
        try {
            Files.createDirectories(Paths.get("screenshots"));

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = String.format("screenshots/%s_%s.png", elementName, timestamp);

            locator.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(fileName)));
            System.out.println("📸 Screenshot del elemento: " + fileName);
        } catch (IOException e) {
            System.err.println("❌ Error al guardar screenshot del elemento: " + e.getMessage());
        }
    }

}
