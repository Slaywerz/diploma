package ru.netology.test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;


public class Tests {

    @BeforeEach
    void setUp() {
        Selenide.open("http://localhost:8080/");
    }

    @AfterAll
    static void showDown() {
        Selenide.closeWindow();
    }
}
