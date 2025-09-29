package stepDef; // <-- PASTIKAN NAMA PAKET INI SAMA DENGAN DI GLUE

import helper.Utility;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class Hooks {

    @BeforeAll
    public static void setUp() {

    }

    @AfterAll
    public static void tearDown() {

    }

    private static final String WEB_TAG = "@web";

    @Before // Ini akan menjalankan Utility.startDriver() sebelum skenario
    public void setupDriver() {
        Utility.startDriver();
    }

    @After // Ini akan menjalankan Utility.quitDriver() setelah skenario
    public void tearDownDriver() {
        Utility.quitDriver();
    }
}


