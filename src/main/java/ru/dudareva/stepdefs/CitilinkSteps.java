package ru.dudareva.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import ru.dudareva.pages.CitilinkCatalogPage;
import ru.dudareva.pages.CitilinkMainPage;
import ru.dudareva.pages.CitilinkSmartphones;

import java.util.List;

/**
 * Класс CitilinkSteps содержит реализацию шагов для тестирования сайта Citilink.
 * Используются аннотации Cucumber для привязки шагов в файле .feature к методам Java.
 * Шаги включают переход на сайт, навигацию по каталогу и фильтрацию товаров.
 *
 * @author Дударева Диана
 * @version 1.0
 */
public class CitilinkSteps extends BaseSteps {

    /**
     * Открывает главную страницу Citilink.
     */
    @Given("пользователь переходит на сайт Citilink")
    public void пользовательПереходитНаСайтCitilink() {
        CitilinkMainPage citilinkMainPage = new CitilinkMainPage();
        citilinkMainPage.openPage();
        testContext.setPage(citilinkMainPage);
    }

    /**
     * Переходит в каталог на сайте Citilink.
     */
    @When("пользователь переходит в каталог")
    public void пользовательПереходитВКаталог() {
        CitilinkMainPage citilinkMainPage = (CitilinkMainPage) testContext.getPage();
        citilinkMainPage.clickOnCatalog();
        CitilinkCatalogPage citilinkCatalogPage = new CitilinkCatalogPage();
        testContext.setPage(citilinkCatalogPage);
    }

    /**
     * Наводит курсор мыши и кликает на заданную категорию.
     *
     * @param category Название категории.
     */
    @And("наводит мышку и нажимает на категорию {string}")
    public void наводитМышкуИнажимаетНаКатегорию(String category) {
        CitilinkCatalogPage citilinkCatalogPage = (CitilinkCatalogPage) testContext.getPage();
        citilinkCatalogPage.moveToSection(category);
        citilinkCatalogPage.clickOnSubsection(category);
        CitilinkSmartphones citilinkSmartphones = new CitilinkSmartphones();
        testContext.setPage(citilinkSmartphones);
    }

    /**
     * Проверяет, что открылась страница заданной категории.
     *
     * @param category Название категории.
     */
    @Then("открывается страница категории {string}")
    public void откраваетсяСтраницаКатегории(String category) {
        CitilinkSmartphones smartphones = (CitilinkSmartphones) testContext.getPage();
        Assert.assertTrue(smartphones.checkForSection(category).contains(category));
    }

    /**
     * Проверяет, что открылась страница заданной категории.
     *
     * @param minPrice Минимальная цена.
     * @param maxPrice Максимальная цена.
     */
    @When("пользователь устанавливает минимальную и максимальную цену {string}, {string}")
    public void пользовательУстанавливаетМинимальнуюИМаксимальнуюЦену(String minPrice, String maxPrice) {
        CitilinkSmartphones smartphones = (CitilinkSmartphones) testContext.getPage();
        smartphones.setPriceRange(minPrice, maxPrice);
    }

    /**
     * Задает производителей для фильтрации.
     *
     * @param brand Название производителя.
     */
    @And("пользователь задает производителей {string}")
    public void пользовательЗадаетПроизводителей(String brand) {
        CitilinkSmartphones smartphones = (CitilinkSmartphones) testContext.getPage();
        smartphones.selectBrand(brand);
    }

    /**
     * Ожидает загрузки результатов после фильтрации.
     */
    @And("дожидается загрузки результатов")
    public void дожидаетсяЗагрузкиРезультатов() {
        CitilinkSmartphones smartphones = (CitilinkSmartphones) testContext.getPage();
        smartphones.waitForProductsToLoad();
    }

    /**
     * Проверяет, что в выборку попали только товары с заданным названием.
     *
     * @param phoneName Название телефона.
     */
    @Then("в выборку должны попасть только {string}")
    public void вВыборкуДолжныПопастьТолько(String phoneName) {
        CitilinkSmartphones smartphones = (CitilinkSmartphones) testContext.getPage();
        List<String> elements = smartphones.getResultsForAllPages();
        elements.forEach(elem -> Assert.assertTrue("Товар должен совпадать с фильтром " + phoneName,
                elem.toUpperCase().contains(phoneName.toUpperCase())));
    }

}
