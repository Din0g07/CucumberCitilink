package ru.dudareva.stash;

/**
 * Класс управляет хранилищем данных, которые передаются между шагами теста
 * @author Дударева Диана
 * @version 1.0
 */
public class TestContext {
    /**
     * Объект, куда кладутся данные о текущей странице
     */
    private Object currentPage;

    /**
     * Инстранс класс
     */
    private static TestContext INSTANCE;

    /**
     * Приватный конструктор для реализации паттерна SingleTon
     */
    private TestContext() {}

    /**
     * Реализован паттерн SingleTon для содержания едиснтвенного объекта класса
     * Это необходимо для реализации единого хранилища данных
     */
    public static TestContext getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestContext();
        }

        return INSTANCE;
    }

    /**
     * Сеттер для единого хранилища, а именно для страницы
     * @param value страница, которая устанавливается в текущую
     */
    public void setPage(Object value) {
        currentPage = value;
    }

    /**
     * Геттер для единого хранилища, а именно для страницы
     * @return текущая страница
     */
    public Object getPage() {
        return currentPage;
    }

}