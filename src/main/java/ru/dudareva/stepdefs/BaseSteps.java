package ru.dudareva.stepdefs;

import ru.dudareva.stash.TestContext;

/**
 * Базовый класс шагов
 * @author Дударева Диана
 * @version 1.0
 */
public class BaseSteps {

    /**
     * Класс-хранилище тестовых данных
     */
    public TestContext testContext;

    /**
     * Конструктор базового класса шагов
     */
    public BaseSteps() {
        this.testContext = TestContext.getInstance();
    }

}
