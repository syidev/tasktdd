package main;

public class Main {
    static final double EPS = 1e-8;
    private final double coefficientA = 2.7;
    private final double coefficientB = -0.3;
    private final double coefficientC = 4;
    public static void main( String[] args ) {}

    double calculateFunction(double variableX) {
        double answer = 0.0;

        if (variableX < 1.4) {
            answer = (coefficientA * Math.pow(variableX, 2)) + (coefficientB * variableX) + coefficientC;
        } else if (variableX == 1.4) {
            answer = (coefficientA / variableX) + Math.sqrt(Math.pow(variableX, 2) + 1);
        } else if (variableX > 1.4) {
            answer = (coefficientA + coefficientB * variableX) / Math.sqrt(Math.pow(variableX, 2) + 1);
        }

        return answer;
    }

    int getStepsNumber(double startNumber, double endNumber, double step) {
        double steps = ((endNumber - startNumber) / step) + 1;
        steps += EPS;

        return (int) steps;
    }

    double[] getArguments(double startNumber, double endNumber, double step) {
        double[] array = new double[getStepsNumber(startNumber, endNumber, step)];
        int counter = 0;

        for(double i = startNumber; i <= endNumber + EPS; i += step) {
            array[counter++] = i;
        }

        return array;
    }

    double[] getFunctionValues(double[] arguments) {
        double[] array = new double[arguments.length];
        int counter = 0;

        for(double argument : arguments) {
            array[counter++] = calculateFunction(argument);
        }

        return array;
    }

    int getMinFunctionValueIndex(double[] functionValues) {
        int minValueIndex = 0;

        for (int i = 1; i < functionValues.length; i++) {
            if (functionValues[i] < functionValues[minValueIndex]) {
                minValueIndex = i;
            }
        }

        return minValueIndex;
    }

    int getMaxFunctionValueIndex(double[] functionValues) {
        int maxValueIndex = 0;

        for (int i = 1; i < functionValues.length; i++) {
            if (functionValues[i] > functionValues[maxValueIndex]) {
                maxValueIndex = i;
            }
        }

        return maxValueIndex;
    }

    double getAverageArraysElement(double[] functionValues) {
        double sumOfValues = 0.0;
        int numberOfValues = 0;

        for (double element : functionValues) {
            sumOfValues += element;
            numberOfValues++;
        }

        return sumOfValues / numberOfValues;
    }

    double getSumArraysElement(double[] functionValues) {
        double sumOfValues = 0.0;

        for (double element : functionValues) {
            sumOfValues += element;
        }

        return sumOfValues;
    }

    private void getMinFunctionValue(double startNumber, double endNumber, double step) {
        double[] arguments = getArguments(startNumber, endNumber, step);
        double[] functionValues = getFunctionValues(arguments);
        int index = getMinFunctionValueIndex(functionValues);

        System.out.println("Min: " + functionValues[index] + ", index: " + index + ", argument: " + arguments[index]);
    }
    private void getMaxFunctionValue(double startNumber, double endNumber, double step) {
        double[] arguments = getArguments(startNumber, endNumber, step);
        double[] functionValues = getFunctionValues(arguments);
        int index = getMaxFunctionValueIndex(functionValues);

        System.out.println("Max: " + functionValues[index] + ", index: " + index + ", argument: " + arguments[index]);
    }
}
