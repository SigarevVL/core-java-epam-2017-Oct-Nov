package com.epam.courses.jf.practice.nzenkova.first;

import com.epam.courses.jf.practice.common.first.ISolver;

import java.math.BigDecimal;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by natal on 01.11.2017.
 */
public class Solver implements ISolver {
    /**
     * Searching the smallest and the biggest row. Output the rows found and their length.
     * If the rows fully satisfying the conditions are more than one, output the last row
     */
    public void task1() {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.valueOf(scanner.nextLine());/* number of lines */
        int maxLength, minLength;
        String maxString = "", minString = "";
        String str = scanner.nextLine();//reading a row
        maxLength = str.length();
        minLength = str.length();
        maxString = str;
        minString = str;
        for (int i = 0; i < number - 1; ++i) {
            str = scanner.nextLine();//reading a next row
            if (str.length() >= maxLength) {
                maxLength = str.length();
                maxString = str;
            }
            if (str.length() <= minLength) {
                minLength = str.length();
                minString = str;
            }
        }
        System.out.printf("MIN (%d): \"%s\"%n", minLength, minString);
        System.out.printf("MAX (%d): \"%s\"%n", maxLength, maxString);
    }

    public void task2() {
        Scanner in = new Scanner(System.in);
        int number = Integer.valueOf(in.nextLine());
        String[] strings = new String[number];
        for (int i = 0; i < number; ++i) {
            strings[i] = in.nextLine();
        }
        Arrays.sort(strings, (o1, o2) -> o1.length() > o2.length() ? 1 : o1.length() < o2.length() ? -1 : o1.compareTo(o2));
        for (String str : strings) {
            System.out.printf("(%d): \"%s\"%n", str.length(), str);
        }
    }

    public void task3() {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.parseInt(scanner.nextLine());
        String[] strings = new String[number];
        int sumLength = 0;
        for (int i = 0; i < number; ++i) {
            strings[i] = scanner.nextLine();
            sumLength += strings[i].length();
        }
        int averageLength = sumLength / number;

        System.out.printf("AVERAGE (%d)%n", averageLength);
        for (String str : strings) {
            if (str.length() < averageLength) {
                System.out.printf("(%d): \"%s\"%n", str.length(), str);
            }
        }
    }

    public void task4() {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.valueOf(scanner.nextLine());
        String result = "";
        int minUniqueElements = 0;
        String word = scanner.next();
        int countUniqueElements = (int) word.chars().distinct().count();
        minUniqueElements = countUniqueElements;
        result = word;
        for (int i = 0; i < number - 1; ++i) {
            word = scanner.next();
            countUniqueElements = (int) word.chars().distinct().count();
            //System.out.println(word.chars().distinct().count());
            if (countUniqueElements < minUniqueElements) {
                minUniqueElements = countUniqueElements;
                result = word;
            }
        }
        System.out.printf("%s%n", result);
    }

    public void task5() {
        Scanner scanner = new Scanner(System.in);
        //  System.out.println("Enter numbers of lines: ");
        int number = Integer.valueOf(scanner.nextLine());
        //   System.out.println("Enter words: ");
        int counter = 0;
        for (int i = 0; i < number; ++i) {
            String word = scanner.next();
            String latinAlphabet = "^[a-zA-Z]+";
            String vowels = "[AEIOUYaeiouy]";
            if (word.matches(latinAlphabet)) {
                if (2 * Arrays.stream(word.split(vowels)).collect(Collectors.joining("")).length() == word.length()) {
                    ++counter;
                }
            }
        }
        System.out.printf("%d%n", counter);
    }

    public void task6() {
        Scanner scanner = new Scanner(System.in);
        //    System.out.println("Enter numbers of lines: ");
        int number = Integer.valueOf(scanner.nextLine());
        //   System.out.println("Enter words: ");
        for (int i = 0; i < number; ++i) {
            char[] word = scanner.next().toCharArray();
            for (int j = 0; j < word.length - 1; ++j) {
                if (word[j] >= word[j + 1]) break;
                if (word.length - 2 == j) {
                    System.out.printf("%s%n", new String(word));
                    return;
                }
            }
        }
        System.out.printf("%s%n", "NOT FOUND");
    }

    public void task7() {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.valueOf(scanner.nextLine());
        String result = "", word = "";
        int countUniqueElements = 0;
        LinkedHashSet<String> necessaryWords = new LinkedHashSet<String>();
        for (int i = 0; i < number; ++i) {
            word = scanner.next();
            countUniqueElements = (int) word.chars().distinct().count();
            if (countUniqueElements == word.length()) {
                necessaryWords.add(word);
            }
        }
        if(necessaryWords.isEmpty()) System.out.printf("%s%n", "NOT FOUND");
        else{
            for (String str : necessaryWords) {
                //System.out.printf("%s ", str);
                System.out.println(str);
            }
        }
    }

    public void task8() {
        Scanner scanner = new Scanner(System.in);
        //    System.out.println("Enter numbers of words: ");
        int number = Integer.valueOf(scanner.nextLine());
        String numbers = "[0-9]+";
        int counter = 0;
        String palindrome = "";
        //    System.out.println("Enter words: ");
        for (int i = 0; i < number; ++i) {
            String word = scanner.next();
            if (word.matches(numbers)) {
                if (word.equals(new StringBuilder(word).reverse().toString())) {
                    if (counter == 1) {
                        palindrome = word;
                        break;
                    } else {
                        ++counter;
                        palindrome = word;
                    }
                }
            }
        }
        if (counter != 0) {
            System.out.printf("%s%n", palindrome);
        } else System.out.printf("%s%n", "NOT FOUND");
    }

    public void task9(){
        Scanner scanner = new Scanner(System.in);
        int dimension = Integer.valueOf(scanner.nextLine());
        int counter = 0;
        int[][] matrix = new int[dimension][dimension];
        for(int i = 0; i < dimension; ++i){
            for(int j = 0; j < dimension; ++j){
                matrix[i][j] = ++counter;
            }
        }
        printMatrix(matrix);
    }

    public void task10(){
        Scanner scanner = new Scanner(System.in);
        double A = scanner.nextDouble();
        double B = scanner.nextDouble();
        double C = scanner.nextDouble();
        double D = Math.pow(B, 2) - 4 * A * C;
        if(D < 0) System.out.println("No solution");
        else if(D == 0){
            BigDecimal x = (new BigDecimal(-B/(2 * A))).setScale(2,BigDecimal.ROUND_HALF_UP);
            System.out.println("One solution: " + x);
        }
        else{
            BigDecimal x1 = (new BigDecimal((- B - Math.sqrt(D))/(2 * A))).setScale(2,BigDecimal.ROUND_HALF_UP);
            BigDecimal x2 = (new BigDecimal((- B + Math.sqrt(D))/(2 * A))).setScale(2,BigDecimal.ROUND_HALF_UP);
            System.out.println("Two solutions: " + x1 + ", " + x2);
        }
    }

    public void task11(){
        Scanner scanner = new Scanner(System.in);
        try{
            int numberMonth = Integer.valueOf(scanner.nextLine());
            switch (numberMonth){
                case 1: System.out.println(Month.of(numberMonth)); break;
                case 2: System.out.println(Month.of(numberMonth)); break;
                case 3: System.out.println(Month.of(numberMonth)); break;
                case 4: System.out.println(Month.of(numberMonth)); break;
                case 5: System.out.println(Month.of(numberMonth)); break;
                case 6: System.out.println(Month.of(numberMonth)); break;
                case 7: System.out.println(Month.of(numberMonth)); break;
                case 8: System.out.println(Month.of(numberMonth)); break;
                case 9: System.out.println(Month.of(numberMonth)); break;
                case 10: System.out.println(Month.of(numberMonth)); break;
                case 11: System.out.println(Month.of(numberMonth)); break;
                case 12: System.out.println(Month.of(numberMonth)); break;
                default: System.out.println("INCORRECT INPUT DATA"); break;
            }
        } catch (NumberFormatException ex) {
            System.out.println("INCORRECT INPUT DATA");
        }
    }

    private int[][] readMatrix(Scanner scanner) {
        final int DIMENSION = scanner.nextInt();
        int[][] matrix = new int[DIMENSION][DIMENSION];
        for (int row = 0; row < DIMENSION; ++row) {
            for (int col = 0; col < DIMENSION; ++col) {
                matrix[row][col] = scanner.nextInt();
            }
        }
        return matrix;
    }

    private void swapRows(int[] row1, int[] row2){
        int dimension = row1.length;
        for(int i = 0; i < dimension; ++i){
            int temp;
            temp = row1[i];
            row1[i] = row2[i];
            row2[i] = temp;
        }
    }

    private void printMatrix(int[][] matrix){
        int numberRows = matrix.length;
        int numberColumns = matrix[0].length;
        for(int i = 0; i < numberRows; ++i){
            for(int j = 0; j < numberColumns; ++j){
                if(j == numberColumns - 1) System.out.printf("%d", matrix[i][j]);
                else System.out.printf("%d\t", matrix[i][j]);
            }
            System.out.printf("%n");
        }
    }

    public void task12(){
        Scanner scanner = new Scanner(System.in);
        int ncol = scanner.nextInt();
        int[][] matrix = readMatrix(scanner);
        Arrays.sort(matrix, (o1, o2) -> o1[ncol] > o2[ncol] ? 1 : o1[ncol] < o2[ncol] ? -1 : Integer.compare(o1[ncol], o2[ncol]));
        System.out.println(matrix.length);
        printMatrix(matrix);
    }

    public void task13(){
        Scanner scanner = new Scanner(System.in);
        int counter = scanner.nextInt();
        int[][] matrix = readMatrix(scanner);
        int dimension = matrix.length;
        if((counter != 0) && (Math.abs(counter) % dimension != 0)){
            if(counter > 0){
                for(int i = 0; i < counter; ++i){
                    for(int j = dimension - 1; j > 0; --j){
                        swapRows(matrix[j], matrix[j - 1]);
                    }
                }
            }
            else{
                for(int i = 0; i < -counter; ++i){
                    for(int j = 0; j < dimension - 1; ++j){
                        if(j == dimension - 1) swapRows(matrix[0], matrix[j]);
                        swapRows(matrix[j], matrix[j + 1]);
                    }
                }
            }
        }
        System.out.println(matrix.length);
        printMatrix(matrix);
    }

    public void task14(){
        Scanner scanner = new Scanner(System.in);
       // int number = Integer.parseInt(scanner.nextLine());
        int number = Integer.valueOf(scanner.nextLine());
        int numberMax = 0, counter = 1;
        int number1 = 0, number2 = 0;
        number1 = scanner.nextInt();
        for(int i = 0; i < number - 1; ++i){
            number2 = scanner.nextInt();
            if(number2 > number1){
                ++counter; number1 = number2;
            }
            else{
                if(numberMax < counter) numberMax = counter;
                counter = 1;
                number1 = number2;
            }
        }
        if(numberMax < counter) numberMax = counter;
        if(numberMax <= 1) numberMax = 0;
        System.out.println(numberMax);
    }

    public void task15(){
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        int dimension = matrix.length;
        int counter = 0, sum = 0;
        for(int i = 0; i < dimension; ++i){
            for(int j = 0; j < dimension; ++j){
                if(matrix[i][j] > 0) ++counter;
                if((counter == 1) && (matrix[i][j] <= 0)) sum += matrix[i][j];
                if(counter == 2) break;
            }
            counter = 0;
        }
        System.out.println(sum);
    }

    public void task16(){
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        int dimension = matrix.length;
        int[][] resultMatrix = new int[dimension][dimension];
        for(int i  = 0; i < dimension; ++i){
            for(int j = 0; j < dimension; ++j) {
                resultMatrix[i][j] = matrix[j][dimension - 1 - i];
            }
        }
        System.out.println(resultMatrix.length);
        printMatrix(resultMatrix);

    }

    private double[][] upperTriangularMatrix(int[][] temp){
        int dimension = temp.length;
        double[][] matrix = new double[dimension][dimension];

        for(int i = 0; i < dimension; ++i){
            for(int j = 0; j < dimension; ++j){
                matrix[i][j] = temp[i][j];
            }
        }


        for (int i = 0; i < dimension; ++i) {
            if(matrix[i][i] == 0) break;
            for (int j = i + 1; j < dimension; ++j) {
                double coef = matrix[j][i]/matrix[i][i];
                for (int k = 0; k < dimension; ++k) {
                    matrix[j][k] -= coef * matrix[i][k];
                }
            }
        }
        return matrix;
    }

    private int getDet(double[][] matrix){
        int dimension = matrix.length;
        double det = 1.0;
        for(int i = 0; i < dimension; ++i){
            if(matrix[i][i] == 0) return 0;
            else{
                det *= matrix[i][i];
            }
        }
        return (int)Math.round(det);
    }


    public void task17(){
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        int dimension = matrix.length;
        double[][] matr = upperTriangularMatrix(matrix);
        System.out.println(getDet(matr));
    }

    public  void task18(){
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        int dimension = matrix.length;
        int maxElement = 0;

        for(int i = 0; i < dimension; ++i){
            for(int j = 0; j < dimension; ++j){
                if(matrix[i][j] > maxElement) maxElement = matrix[i][j];
            }
        }

        Set<Integer> deleteRows = new HashSet<Integer>();
        Set<Integer> deleteColumns = new HashSet<Integer>();

        for(int i = 0; i < dimension; ++i){
            for(int j = 0; j < dimension; ++j){
                if(maxElement == matrix[i][j]){
                    deleteColumns.add(j);
                    deleteRows.add(i);
                }
            }
        }

        int resultColumns = dimension - deleteColumns.size();
        int resultRows = dimension - deleteRows.size();
        int[][] resultMatrix = new int[resultRows][resultColumns];

        int rowsResultMatrix = 0, columnsResultMatrix = 0;
        for(int i = 0; i < dimension; ++i){
            if(!deleteRows.contains(i)){
                for(int j = 0; j < dimension; ++j){
                    if(!deleteColumns.contains(j)){
                        resultMatrix[rowsResultMatrix][columnsResultMatrix] = matrix[i][j];
                        ++columnsResultMatrix;
                    }
                }
                ++rowsResultMatrix;
                columnsResultMatrix = 0;
            }
        }

        System.out.println(resultRows);
        System.out.println(resultColumns);

       /* for(int i = 0; i < resultColumns; ++i){
            for(int j = 0; j < resultColumns; ++j){
                System.out.print(resultMatrix[i][j]);
            }
            System.out.printf("%n");
        }*/

        printMatrix(resultMatrix);





    }

    public void task19(){
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        int dimension = matrix.length;

        Set<Integer> deleteRows = new HashSet<Integer>();
        Set<Integer> deleteColumns = new HashSet<Integer>();

        int counterNullInRows = 0;
        for(int i = 0; i < dimension; ++i){
            for(int j = 0; j  < dimension; ++j){
                if(matrix[i][j] == 0) ++counterNullInRows;
            }
            if(counterNullInRows == dimension) deleteRows.add(i);
            counterNullInRows = 0;
        }

        int counterNullInColumns = 0;
        for(int j = 0; j < dimension; ++j){
            for(int i = 0; i  < dimension; ++i){
                if(matrix[i][j] == 0) ++counterNullInColumns;
            }
            if(counterNullInColumns == dimension) deleteColumns.add(j);
            counterNullInColumns = 0;
        }

        int resultColumns = dimension - deleteColumns.size();
        int resultRows = dimension - deleteRows.size();
        int[][] resultMatrix = new int[resultRows][resultColumns];

        int rowsResultMatrix = 0, columnsResultMatrix = 0;
        for(int i = 0; i < dimension; ++i){
            if(!deleteRows.contains(i)){
                for(int j = 0; j < dimension; ++j){
                    if(!deleteColumns.contains(j)){
                        resultMatrix[rowsResultMatrix][columnsResultMatrix] = matrix[i][j];
                        ++columnsResultMatrix;
                    }
                }
                ++rowsResultMatrix;
                columnsResultMatrix = 0;
            }
        }

        System.out.println(resultRows);
        System.out.println(resultColumns);
        printMatrix(resultMatrix);
    }

    public void task20(){
        Scanner scanner = new Scanner(System.in);
        int numberRow = scanner.nextInt();
        int numberColumn = scanner.nextInt();
        int[][] matrix = readMatrix(scanner);
        int dimension = matrix.length;
        int rowMin = 0, columnMin = 0;
        int min = 0;
        for(int i = 0; i < dimension; ++i){
            for(int j = 0; j < dimension; ++j){
                if(matrix[i][j] < min){
                    min = matrix[i][j];
                    rowMin = i;
                    columnMin = j;
                }
            }
        }
        int tmp = 0;
        for(int i = 0; i < dimension; ++i){
            tmp = matrix[numberRow][i];
            matrix[numberRow][i] = matrix[rowMin][i];
            matrix[rowMin][i] = tmp;
        }
        for(int i = 0; i < dimension; ++i){
            tmp = matrix[i][numberColumn];
            matrix[i][numberColumn] = matrix[i][columnMin];
            matrix[i][columnMin] = tmp;
        }
        System.out.println(dimension);
        printMatrix(matrix);
    }

    public void  task21(){
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        int dimension = matrix.length;
        for(int i = 0; i < dimension; ++i){
            int flag = 0, j = 0;
            while(j < dimension - flag){
                if(matrix[i][j] == 0){
                    for(int  k = j  + 1; k < dimension; ++k){
                        int tmp = matrix[i][k];
                        matrix[i][k] = matrix[i][k - 1];
                        matrix[i][k - 1] = tmp;
                    }
                    ++flag;
                    --j;
                }
                ++j;
            }
        }
        System.out.println(dimension);
        printMatrix(matrix);
    }

    private Double[][] readDoubleMatrix(Scanner scanner) {
        final int DIMENSION = scanner.nextInt();
        Double[][] matrix = new Double[DIMENSION][DIMENSION];
        for (int row = 0; row < DIMENSION; ++row) {
            for (int col = 0; col < DIMENSION; ++col) {
                matrix[row][col] = scanner.nextDouble();
            }
        }
        return matrix;
    }

    public void task22(){
        Scanner scanner = new Scanner(System.in);
        Double[][] matrix = readDoubleMatrix(scanner);
        int dimension = matrix.length;
        int[][] resultMatrix = new int[dimension][dimension];
        for(int i = 0; i < dimension; ++i){
            for(int j = 0; j < dimension; ++j){
                resultMatrix[i][j] = (int)(Math.round(matrix[i][j]));
            }
        }
        System.out.println(dimension);
        printMatrix(resultMatrix);
    }

    public void task23(){
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        int dimension = matrix.length;
        int result = 0;
        //Set<Integer> inRows = new HashSet<Integer>();
        //Set<Integer> inColumns = new HashSet<Integer>();

        for(int[] aMatrix : matrix){
            int min = aMatrix[0];
            int jmin = 0;
            for(int j = 0; j < dimension; ++j){
                if(aMatrix[j] < min){
                    min = aMatrix[j];
                    jmin = j;
                }
            }
            int max = matrix[0][jmin];
            for(int[] aMatrix1 : matrix){
                if(aMatrix1[jmin] > max){
                    max = aMatrix1[jmin];
                }

            }
            if(min == max) ++result;
        }
        System.out.println(result);
    }

    public void task24(){
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        int dimension = matrix.length;
        Arrays.sort(matrix, new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){
                int sum1 = 0, sum2 = 0;
                for(int i = 0; i < dimension; ++i){
                    sum1 += o1[i];
                    sum2 += o2[i];
                }
                return (sum1 > sum2 ? 1 : sum1 < sum2 ? -1 : 0);
            }
        });

        System.out.println(dimension);
        printMatrix(matrix);
    }

    private boolean testLocalMin(int[][] matrix, int i, int j){
        if(i == 0){
            if(j == 0){
                if((matrix[i][j] < matrix[i][j + 1]) && (matrix[i][j] < matrix[i + 1][j])
                        && (matrix[i][j] < matrix[i + 1][j + 1]))
                    return true;
                else return false;
            }
            else if(j == matrix.length - 1){
                if((matrix[i][j] < matrix[i][j - 1]) && (matrix[i][j] < matrix[i + 1][j])
                        && (matrix[i][j] < matrix[i + 1][j - 1]))
                    return true;
                else return false;
            }
            else if((matrix[i][j] < matrix[i][j - 1]) && (matrix[i][j] < matrix[i + 1][j])
                    && (matrix[i][j] < matrix[i + 1][j - 1]) && (matrix[i][j] < matrix[i][j + 1])
                    && (matrix[i][j] < matrix[i + 1][j + 1]))
                return true;
            else return false;
        }
        else if(i == matrix.length - 1){
            if(j == 0){
                if((matrix[i][j] < matrix[i - 1][j]) && (matrix[i][j] < matrix[i - 1][j  + 1])
                        && (matrix[i][j] < matrix[i][j + 1]))
                    return true;
                else return false;
            }
            else if(j == matrix.length - 1){
                if((matrix[i][j] < matrix[i - 1][j]) && (matrix[i][j] < matrix[i - 1][j  - 1])
                        && (matrix[i][j] < matrix[i][j - 1]))
                    return true;
                else return false;
            }
            else if((matrix[i][j] < matrix[i][j - 1]) && (matrix[i][j] < matrix[i - 1][j])
                    && (matrix[i][j] < matrix[i - 1][j - 1]) && (matrix[i][j] < matrix[i][j + 1])
                    && (matrix[i][j] < matrix[i - 1][j + 1]))
                return true;
            else return false;
        }
        else if(j == 0){
            if((matrix[i][j] < matrix[i - 1][j]) && (matrix[i][j] < matrix[i - 1][j + 1])
                    && (matrix[i][j] < matrix[i][j + 1]) && (matrix[i][j] < matrix[i + 1][j + 1])
                    && (matrix[i][j] < matrix[i + 1][j]))
                return true;
            else return false;
        }
        else if(j == matrix.length){
            if((matrix[i][j] < matrix[i - 1][j]) && (matrix[i][j] < matrix[i - 1][j - 1])
                    && (matrix[i][j] < matrix[i][j - 1]) && (matrix[i][j] < matrix[i + 1][j - 1])
                    && (matrix[i][j] < matrix[i + 1][j]))
                return true;
            else return false;
        }
        else if((matrix[i][j] < matrix[i][j - 1]) && (matrix[i][j] < matrix[i - 1][j - 1])
                && (matrix[i][j] < matrix[i - 1][j]) && (matrix[i][j] < matrix[i - 1][j + 1])
                && (matrix[i][j] < matrix[i][j + 1]) && (matrix[i][j] < matrix[i + 1][j + 1])
                && (matrix[i][j] < matrix[i + 1][j]) && (matrix[i][j] < matrix[i + 1][j - 1]))
            return true;
        else return false;
    }

    public void task25(){
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        int dimension = matrix.length;
        int result = 0;

        if(dimension <= 1) result = 1;
        else{
            for(int  i = 0; i < dimension; ++i){
                for(int j = 0; j < dimension; ++j){
                    if(testLocalMin(matrix, i , j))
                        ++result;
                }
            }
        }
        System.out.println(result);
    }

    private boolean testLocalMax(int[][] matrix, int i , int j){
        if(i == 0){
            if(j == 0){
                if((matrix[i][j] > matrix[i][j + 1]) && (matrix[i][j] > matrix[i + 1][j])
                        && (matrix[i][j] > matrix[i + 1][j + 1]))
                    return true;
                else return false;
            }
            else if(j == matrix.length - 1){
                if((matrix[i][j] > matrix[i][j - 1]) && (matrix[i][j] > matrix[i + 1][j])
                        && (matrix[i][j] > matrix[i + 1][j - 1]))
                    return true;
                else return false;
            }
            else if((matrix[i][j] > matrix[i][j - 1]) && (matrix[i][j] > matrix[i + 1][j])
                    && (matrix[i][j] > matrix[i + 1][j - 1]) && (matrix[i][j] > matrix[i][j + 1])
                    && (matrix[i][j] > matrix[i + 1][j + 1]))
                return true;
            else return false;
        }
        else if(i == matrix.length - 1){
            if(j == 0){
                if((matrix[i][j] > matrix[i - 1][j]) && (matrix[i][j] > matrix[i - 1][j  + 1])
                        && (matrix[i][j] > matrix[i][j + 1]))
                    return true;
                else return false;
            }
            else if(j == matrix.length - 1){
                if((matrix[i][j] > matrix[i - 1][j]) && (matrix[i][j] > matrix[i - 1][j  - 1])
                        && (matrix[i][j] > matrix[i][j - 1]))
                    return true;
                else return false;
            }
            else if((matrix[i][j] > matrix[i][j - 1]) && (matrix[i][j] > matrix[i - 1][j])
                    && (matrix[i][j] > matrix[i - 1][j - 1]) && (matrix[i][j] > matrix[i][j + 1])
                    && (matrix[i][j] > matrix[i - 1][j + 1]))
                return true;
            else return false;
        }
        else if(j == 0){
            if((matrix[i][j] > matrix[i - 1][j]) && (matrix[i][j] > matrix[i - 1][j + 1])
                    && (matrix[i][j] > matrix[i][j + 1]) && (matrix[i][j] > matrix[i + 1][j + 1])
                    && (matrix[i][j] > matrix[i + 1][j]))
                return true;
            else return false;
        }
        else if(j == matrix.length){
            if((matrix[i][j] > matrix[i - 1][j]) && (matrix[i][j] > matrix[i - 1][j - 1])
                    && (matrix[i][j] > matrix[i][j - 1]) && (matrix[i][j] > matrix[i + 1][j - 1])
                    && (matrix[i][j] > matrix[i + 1][j]))
                return true;
            else return false;
        }
        else if((matrix[i][j] > matrix[i][j - 1]) && (matrix[i][j] > matrix[i - 1][j - 1])
                && (matrix[i][j] > matrix[i - 1][j]) && (matrix[i][j] > matrix[i - 1][j + 1])
                && (matrix[i][j] > matrix[i][j + 1]) && (matrix[i][j] > matrix[i + 1][j + 1])
                && (matrix[i][j] > matrix[i + 1][j]) && (matrix[i][j] > matrix[i + 1][j - 1]))
            return true;
        else return false;
    }
    public void task26(){
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        int dimension = matrix.length;
        Set<Integer> localMax = new HashSet<>();

        if(dimension <= 1) System.out.println(matrix[0][0]);
        else {
            for (int i = 0; i < dimension; ++i) {
                for (int j = 0; j < dimension; ++j) {
                    if (testLocalMax(matrix, i, j)) {
                      //  if (localMax.contains(matrix[i][j]))
                          //  localMax.remove(matrix[i][j]);
                       // else localMax.add(matrix[i][j]);
                        localMax.add(matrix[i][j]);
                    }


                }
            }

            if (localMax.isEmpty() || (Collections.max(localMax) == 0)) System.out.println("NOT FOUND");
            else System.out.println(Collections.max(localMax));
        }
    }

    private int[][] transposedMatrix(int[][] matrix){
        int dimension = matrix.length;
        int[][] transposedMatrix = new int[dimension][dimension];
        for(int i = 0; i < dimension; ++i){
            for(int j = 0; j < dimension; ++j){
                transposedMatrix[i][j] = matrix[j][i];
            }
        }
        return transposedMatrix;
    }

    public void task27(){
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        int dimension = matrix.length;
        int[][] tempMatrix = transposedMatrix(matrix);

        Arrays.sort(tempMatrix, new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){
                int sum1 = 0, sum2 = 0;
                for(int i = 0; i < dimension; ++i){
                    sum1 += Math.abs(o1[i]);
                    sum2 += Math.abs(o2[i]);
                }
                return (sum1 > sum2 ? -1 : sum1 < sum2 ? 1 : 0);
            }
        });

        matrix = transposedMatrix(tempMatrix);

        System.out.println(dimension);
        printMatrix(matrix);
    }
}