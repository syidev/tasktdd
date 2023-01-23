package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    Main main;
    @BeforeEach
    void setUp() {
        main = new Main();
    }

    @ParameterizedTest
    @CsvSource({"1.3, 8.173", "1.4, 3.64903648", "1.5, 1.24807544"})
    void testCalculateFunction(double variableX, double expected) {
        assertThat(main.calculateFunction(variableX)).isCloseTo(expected, within(Main.EPS));
    }

    @Test
    void testGetStepsNumber() {
        int expected = 1001;

        int actual = main.getStepsNumber(0.0, 2.0, 0.002);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"0.0, 0", "1.4, 700", "2.00, 1000"})
    void testGetArguments(double expected, int index) {
        double[] actual = main.getArguments(0.0, 2.0, 0.002);

        assertThat(actual[index]).isCloseTo(expected, within(Main.EPS));
    }

    @ParameterizedTest
    @CsvSource({"4.0, 0", "1.32522308, 700", "0.93914855, 1000"})
    void testGetFunctionValues(double expected, int index) {
        double [] arguments = main.getArguments(0.0, 2.0, 0.002);
        double [] actual = main.getFunctionValues(arguments);

        assertThat(actual[index]).isCloseTo(expected, within(Main.EPS));
    }

    @Test
    void testGetMinFunctionValueIndex() {
        int expected = 1000;

        double [] arguments = main.getArguments(0.0, 2.0, 0.002);
        double [] functionValues = main.getFunctionValues(arguments);
        int actual = main.getMinFunctionValueIndex(functionValues);

        assertEquals(expected, actual);
    }

    @Test
    void testGetMaxFunctionValueIndex() {
        int expected = 699;

        double [] arguments = main.getArguments(0.0, 2.0, 0.002);
        double [] functionValues = main.getFunctionValues(arguments);
        int actual = main.getMaxFunctionValueIndex(functionValues);

        assertEquals(expected, actual);
    }

    @Test
    void testGetAverageArraysElement() {
        double expected = 4.21757161;

        double [] arguments = main.getArguments(0.0, 2.0, 0.002);
        double [] functionValues = main.getFunctionValues(arguments);
        double actual = main.getAverageArraysElement(functionValues);

        assertThat(actual).isCloseTo(expected, within(Main.EPS));
    }

    @Test
    void testGetSumArraysElement() {
        double expected = 4221.78918475;

        double [] arguments = main.getArguments(0.0, 2.0, 0.002);
        double [] functionValues = main.getFunctionValues(arguments);
        double actual = main.getSumArraysElement(functionValues);

        assertThat(actual).isCloseTo(expected, within(Main.EPS));
    }
}
