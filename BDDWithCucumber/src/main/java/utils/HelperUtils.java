package utils;


import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperUtils {

    public static WebElement getWebElementByTextFromList(List<WebElement> elements, String text) {
        WebElement correctCategory = null;
        if (elements != null && text != null) {
            for (WebElement category : elements) {
                if (category.getText().equals(text)) {
                    correctCategory = category;
                    break;
                }
            }
        }
        return correctCategory;
    }

    public static int getIndexOfElementByText(List<WebElement> elements, String text) {
        WebElement correctCategory =getWebElementByTextFromList(elements,text);
        return elements.indexOf(correctCategory);
    }

    public static int parseToInt(String number) {
        String trimmed = number.trim();
        String result = trimmed.replaceAll("\\D", "");
        return Integer.parseInt(result);
    }

    public static int getNumberOfManufacturerProducts(List<WebElement> manufacturers, List<WebElement> quantities, String manufacturer) {
        int manufacturerIndex = getIndexOfElementByText(manufacturers, manufacturer);
        String quantity = quantities.get(manufacturerIndex).getText();
        return parseToInt(quantity);
    }


}
