package ru.dudareva.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.actions;

/**
 * Класс, представляющий страницу каталога сайта Citilink.
 * Содержит методы для взаимодействия с элементами страницы каталога.
 * @author Дударева Диана
 * @version 1.0
 */
public class CitilinkCatalogPage {
    /**
     * XPath для категории.
     */
    private static final String CATEGORY_XPATH = "//a[contains(@class, 'CatalogLayout__link_level-1') and contains(., '%s')]";

    /**
     * XPath для подкатегории.
     */
    private static final String SUBCATEGORY_XPATH = "//li[@class='CatalogLayout__children-item']//a[text()='%s']";

    /**
     * Метод для наведения курсора на категорию.
     * @param categoryName Название категории, на которую нужно навести курсор.
     */
    public void moveToSection(String categoryName) {
        SelenideElement category = $x(String.format(CATEGORY_XPATH, categoryName)).shouldBe(Condition.visible);
        actions().moveToElement(category).perform();
    }

    /**
     * Метод для клика на подкатегорию.
     * @param categoryName Название подкатегории, на которую нужно кликнуть.
     */
    public void clickOnSubsection(String categoryName) {
        $x(String.format(SUBCATEGORY_XPATH, categoryName)).shouldBe(Condition.visible).click();
    }

}
