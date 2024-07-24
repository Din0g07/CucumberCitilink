package ru.dudareva.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.dudareva.config.Config;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Класс, представляющий главную страницу сайта Citilink.
 * Содержит методы для взаимодействия с элементами главной страницы.
 * @author Дударева Диана
 * @version 1.0
 */
public class CitilinkMainPage {

    /**
     * CSS-селектор для кнопки каталога.
     */
    private static final String CATALOG_BUTTON_CSS = "a[data-meta-name='DesktopHeaderFixed__catalog-menu']";

    /**
     * Элемент кнопки каталога.
     */
    private final SelenideElement catalogButton = $(By.cssSelector(CATALOG_BUTTON_CSS));

    /**
     * Открытие сайта citilink.
     * Метод открывает сайт по прямой ссылке и возвращает главную страницу сайта.
     */
    public void openPage() {
        open(Config.getProperty("citilink.url"));
    }

    /**
     * Переход в каталог товаров.
     * Метод кликает на кнопку каталога и возвращает страницу каталога.
     */
    public void clickOnCatalog() {
        catalogButton.shouldBe(Condition.visible).click();
        open("https://www.citilink.ru/catalog/");
    }

}
