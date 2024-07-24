package ru.dudareva.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

/**
 * Класс, представляющий страницу раздела "Смартфоны" на сайте Citilink.
 * Содержит методы для взаимодействия с элементами страницы раздела.
 * @author Дударева Диана
 * @version 1.0
 */
public class CitilinkSmartphones {

    /**
     * XPath для наименования раздела.
     */
    private static final String SECTION_XPATH = "//div[@data-meta-name='SubcategoryPageTitle']//h1";

    /**
    * XPath для поля ввода минимальной цены.
    */
    private String MIN_PRICE_INPUT_XPATH = "//input[@data-meta-name='FilterRangeGroup__input-min']";

    /**
    * XPath для поля ввода максимальной цены.
    */
    private String MAX_PRICE_INPUT_XPATH = "//input[@data-meta-name='FilterRangeGroup__input-max']";

    /**
     * XPath для чекбокса с брендом.
     */
    private static final String BRAND_CHECKBOX_XPATH = "//div[@data-meta-name='FilterLabel' and @data-meta-value='%s']//input";

    /**
     * XPath для кнопки принятия файлов cookie.
     */
    private static final String ACCEPT_COOKIES_BUTTON_XPATH = "//button[span[text()='Я согласен']]";

    /**
     * XPath для элементов продуктов.
     */
    private static final String PRODUCT_ELEMENTS_XPATH = "//div[@data-meta-name='ProductVerticalSnippet']";

    /**
     * XPath для кнопки новой страницы.
     */
    private static final String NEXT_PAGE_XPATH = "//a[@data-meta-name='PageLink__page-page-next']";

    /**
     * Метод получения наименования раздела.
     * @param sectionName раздел для поиска
     * @return наименование раздела
     */
    public String checkForSection(String sectionName) {
        $x(SECTION_XPATH).shouldHave(Condition.text(sectionName));
        return $x(SECTION_XPATH).getText();
    }

    /**
     * Метод для установки диапазона цен.
     *
     * @param minPrice минимальная цена.
     * @param maxPrice максимальная цена.
     */
    public void setPriceRange(String minPrice, String maxPrice) {
        String currentUrl = WebDriverRunner.url();
        String newUrl;
        if (currentUrl.contains("?")) {
            newUrl = currentUrl + "&pf=discount.any%2Crating.any&f=discount.any%2Crating.any&r=price_filter_group_id%3A" + minPrice + "-" + maxPrice;
        } else {
            newUrl = currentUrl + "?pf=discount.any%2Crating.any&f=discount.any%2Crating.any&r=price_filter_group_id%3A" + minPrice + "-" + maxPrice;
        }
        open(newUrl);
        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(5));
        wait.until((ExpectedCondition<Boolean>) driver -> WebDriverRunner.url().equals(newUrl));
    }


    /**
     * Метод для фильтрации списка товаров по производителю.
     * @param brand Производитель для фильтрации.
     */
    public void selectBrand(String brand) {
        $x(ACCEPT_COOKIES_BUTTON_XPATH).shouldBe(Condition.enabled).click();
        $x(String.format(BRAND_CHECKBOX_XPATH, brand.toUpperCase())).shouldBe(Condition.enabled).click();
    }

    /**
     * Метод для ожидания загрузки списка товаров.
     */
    public void waitForProductsToLoad() {
        ElementsCollection products = $$x(PRODUCT_ELEMENTS_XPATH);
        for (int i = 0; i < products.size(); i++) {
            products.get(i).shouldBe(Condition.exist, Duration.ofSeconds(5));
        }
    }

    /**
     * Метод для получения результатов всех доступных страниц.
     * @return коллекция наименований результатов
     */
    public List<String> getResultsForAllPages() {
        List<String> elementsCombined = new ArrayList<>();
        while (true) {
            ElementsCollection products = $$x(PRODUCT_ELEMENTS_XPATH);
            for (int i = 0; i < products.size(); i++) {
                products.get(i).shouldBe(Condition.exist, Duration.ofSeconds(5));
                elementsCombined.add(products.get(i).getText());
            }
            SelenideElement nextPageButton = $x(NEXT_PAGE_XPATH);
            if (nextPageButton.exists()) {
                nextPageButton.click();
            } else {
                break;
            }
        }
        return elementsCombined;
    }
}
