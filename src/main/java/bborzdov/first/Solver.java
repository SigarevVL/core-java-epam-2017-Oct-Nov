package bborzdov.first;

import com.epam.courses.jf.practice.common.first.ISolver;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by bogdan on 18.10.17.
 */
public class Solver implements ISolver {

    private Scanner scanner;

    public Solver(){
        scanner = new Scanner(System.in);
    }

    /**
     * Ввести N строк (каждая с новой строки). Найти среди них самую короткую и самую длинную.
     * Вывести найденные строки и их длину. Если строк, удовлетворяющих условию,
     * более одной - вывести последнюю из них.
     */
    @Override
    public void task1() {
        String[] strings = getInputStrings();
        String maxString=strings[0];
        String minString=strings[0];
        for (int i = 1; i < strings.length; i++) {
            if(maxString.length() <= strings[i].length()){
                maxString = strings[i];
            }
            if(minString.length() >= strings[i].length()){
                minString = strings[i];
            }
        }
        System.out.printf("MIN (%d): \"%s\"%n", minString.length(), minString);
        System.out.printf("MAX (%d): \"%s\"%n", maxString.length(), maxString);
    }

    /**
     * Ввести N строк (каждая с новой строки). Упорядочить и вывести строки в
     * порядке возрастания значений их длины. В случае, если длины строк
     * совпадают - упорядочить их в лексикографическом порядке.
     */
    @Override
    public void task2() {
        String[] strings = getInputStrings();
        Arrays.sort(strings,Comparator.comparingInt(String::length));
        for (int i = 1; i < strings.length; i++) {
            if(strings[i].length() == strings[i-1].length()){
                String lexicSortedArray[] = {strings[i], strings[i-1]};
                Arrays.asList(lexicSortedArray).sort(Comparator.naturalOrder());
                strings[i-1] = lexicSortedArray[0];
                strings[i] = lexicSortedArray[1];
            }
        }
        for (String string : strings) {
            System.out.printf("(%d): \"%s\"%n", string.length(), string);
        }
    }

    /**
     * Ввести N строк. Вывести те строки, длина которых меньше средней.
     * Под 'средней' подразумевается среднеарифметическая величина длины
     * всех строк, округленная до целого в меньшую сторону.
     */
    @Override
    public void task3() {
        String[] strings = getInputStrings();
        int average = 0;
        for (String string : strings) {
            average += string.length();
        }
        average /= strings.length;
        System.out.printf("AVERAGE (%d)%n", average);
        for (String string : strings) {
            if(average > string.length()){
                System.out.printf("(%d): \"%s\"%n", string.length(), string);
            }
        }
    }

    /**
     * Ввести N слов, состоящих из символов английского алфавита. Найти слово,
     * в котором число различных символов минимально. Символы верхнего и нижнего
     * регистра считать различными. Если таких слов несколько, найти первое из них.
     */
    @Override
    public void task4() {
        String[] words = getInputString();
        String[] minString=words[0].split("");
        Set<String> minStringSet = new HashSet<>();
        Collections.addAll(minStringSet,minString);
        for (String word : words) {
            Set<String> stringSet = new HashSet<>();
            String[] letters = word.split("");
            Collections.addAll(stringSet,letters);
            if(minStringSet.size() > stringSet.size()){
                minString = letters;
                minStringSet.clear();
                Collections.addAll(minStringSet,minString);
            }
        }
        for (String s : minString) {
            System.out.print(s);
        }
    }

    /**
     * Ввести N слов. Найти количество слов, содержащих только символы латинского
     * алфавита, а среди них – количество слов с равным числом гласных и согласных букв.
     */
    @Override
    public void task5() {
        String[] words = getInputString();
        int counter = 0;
        int numOfVowels = 0;
        int numOfConsonants = 0;
        String vowelString = "aeiou";
        ArrayList<String> vowels = new ArrayList<>(5);
        Collections.addAll(vowels, vowelString.split(""));
        for (String word : words) {
            String[] noLatinLetters = word.split("[a-zA-Z]");
            if(noLatinLetters.length == 0 && word.length() % 2 == 0){
                String[] letters = word.split("");
                for (String letter : letters) {
                    if(vowels.contains(letter)){
                        numOfVowels++;
                    }else{
                        numOfConsonants++;
                    }
                }
                if(numOfVowels == numOfConsonants){
                    counter++;
                }
                numOfVowels = 0;
                numOfConsonants = 0;
            }
        }
        System.out.println(counter);
    }

    /**
     * Ввести N слов. Найти слово, символы в котором идут в строгом
     * порядке возрастания их кодов. Если таких слов несколько, найти первое из них.
     */
    @Override
    public void task6() {
        String[] words = getInputString();
        for (String word : words) {
            if (word.length()==1){
                continue;
            }
            char[] letters = word.toCharArray();
            for (int i = 1; i < letters.length; i++) {
                if(letters[i-1] >= letters[i]){
                    break;
                }else if(i == letters.length-1){
                    System.out.println(word);
                    return;
                }
            }
        }
        System.out.println("NOT FOUND");
    }

    /**
     * Ввести N слов. Найти слова, состоящие только из различных символов.
     * В случае, если слово встречается более одного раза - вывести его единожды.
     */
    @Override
    public void task7() {
        String[] words = getInputString();
        Set<String> stringSet = new HashSet<>();
        AbstractList<String> targetWords = new ArrayList<>();
        for (String word : words) {
            Collections.addAll(stringSet,word.split(""));
            if(word.length() == stringSet.size() && !targetWords.contains(word)){
                targetWords.add(word);
                System.out.print(word+" ");
            }
            stringSet.clear();
        }
        if(targetWords.size()==0){
            System.out.print("NOT FOUND");
        }
    }

    /**
     * Ввести N слов. Помимо обычных слов, во входной последовательности могут
     * встречаться целые числа. Среди них необходимо найти число-палиндром
     * (одинаково читающееся в обоих направлениях). Если таких чисел больше одного,
     * найти второе из них. Ограничения на размер числа нет. Одна цифра является
     * палиндромом. Числа могут быть большими.
     */
    @Override
    public void task8() {
        String words[] = getInputString();
        String palindrome;
        int numPalindrome=0;
        for (String word : words) {
            String wordSymbols[] = word.split("");
            if(wordSymbols[0].matches("[0-9]")) {
                List<String> listWordSymbols = new ArrayList<>();
                Collections.addAll(listWordSymbols, wordSymbols);
                boolean flag=true;
                for (int i = 0; i < 10; i++) {
                    if(i!=Integer.valueOf(wordSymbols[0])) {
                        if (listWordSymbols.contains(String.valueOf(i))) {
                            flag = false;
                            break;
                        }
                    }
                }
                if(flag){
                    numPalindrome++;
                    palindrome = word;
                    if(numPalindrome==2){
                        System.out.println(palindrome);
                        return;
                    }
                }

            }

        }
        if(numPalindrome==0) {
            System.out.println("NOT FOUND");
        }
    }

    /**
     * Написать программу, которая выводит числа от 1 до N^2 в
     * виде матрицы NxN слева направо и сверху вниз.
     */
    @Override
    public void task9() {
        int sizeOfMatrix = scanner.nextInt();
        for (int i = 0; i < sizeOfMatrix; i++) {
            for (int j = 1; j < sizeOfMatrix+1; j++) {
                if(j<sizeOfMatrix){
                    System.out.print(i*sizeOfMatrix+j+"\t");
                }else{
                    System.out.print(i*sizeOfMatrix+j);
                }

            }
            System.out.println();
        }
    }

    /**
     * Написать программу, позволяющую корректно находить корни
     * квадратного уравнения. Параметры уравнения должны
     * задаваться из стандартного входа.
     */
    @Override
    public void task10() {
        int [] coeffs = new int[3];
        double x1;
        double x2;
        double discriminant;
        for (int i = 0; i < coeffs.length; i++) {
            coeffs[i] = scanner.nextInt();
        }
        discriminant = Math.pow(coeffs[1],2) - 4 * coeffs[0] * coeffs[2];
        if(discriminant > 0){
            x1 = (- coeffs[1] + Math.sqrt(discriminant)) / (2 * coeffs[0]);
            x2 = (- coeffs[1] - Math.sqrt(discriminant)) / (2 * coeffs[0]);
            BigDecimal bigDecimalX1 = new BigDecimal(x1);
            BigDecimal bigDecimalX2 = new BigDecimal(x2);
            bigDecimalX1 = bigDecimalX1.setScale(2, BigDecimal.ROUND_UP);
            bigDecimalX2 = bigDecimalX2.setScale(2, BigDecimal.ROUND_UP);
            System.out.println("Two solutions: "+bigDecimalX1+", "+bigDecimalX2);
        } else if(discriminant == 0){
            x1 = (- coeffs[1] + Math.sqrt(discriminant)) / (2 * coeffs[0]);
            BigDecimal bigDecimalX1 = new BigDecimal(x1);
            bigDecimalX1 = bigDecimalX1.setScale(2, BigDecimal.ROUND_UP);
            System.out.println("One solution: "+bigDecimalX1);
        }else{
            System.out.println("No solution");
        }
    }

    /**
     * Ввести число от 1 до 12. Вывести на консоль название месяца,
     * соответствующего данному числу. При реализации использовать
     * оператор switch. Осуществить проверку корректности ввода числа.
     */
    @Override
    public void task11() {
        int numOfMonth = scanner.nextInt();
        switch (numOfMonth){
            case 1:{
                System.out.println("January");
                break;
            }
            case 2:{
                System.out.println("February");
                break;
            }
            case 3:{
                System.out.println("March");
                break;
            }
            case 4:{
                System.out.println("April");
                break;
            }
            case 5:{
                System.out.println("May");
                break;
            }
            case 6:{
                System.out.println("June");
                break;
            }
            case 7:{
                System.out.println("July");
                break;
            }
            case 8:{
                System.out.println("August");
                break;
            }
            case 9:{
                System.out.println("September");
                break;
            }
            case 10:{
                System.out.println("October");
                break;
            }
            case 11:{
                System.out.println("November");
                break;
            }
            case 12:{
                System.out.println("December");
                break;
            }
            default:{
                System.out.println("INCORRECT INPUT DATA");
            }
        }
    }

    /**
     * Упорядочить строки матрицы размерности N в порядке возрастания значений элементов k-го столбца.
     */
    @Override
    public void task12() {
        int numOfColumn = scanner.nextInt();
        scanner.nextLine();
        int[][] matrix = getIntMatrix();
        TreeMap<Integer, int[]> matrixMap = new TreeMap<>();
        for (int[] elems : matrix) {
            matrixMap.put(elems[numOfColumn],elems);
        }
        Collection<int[]> sortedMatrix = matrixMap.values();
        System.out.println(matrix.length);
        for (int[] rows : sortedMatrix) {
            for (int i = 0; i < sortedMatrix.size(); i++) {
                if(i < sortedMatrix.size()-1) {
                    System.out.print(rows[i] + "\t");
                }else {
                    System.out.print(rows[i]);
                }
            }
            System.out.println();
        }
    }

    /**
     * Выполнить циклический сдвиг матрицы размерности N на k позиций вниз.
     */
    @Override
    public void task13() {
        int shift = scanner.nextInt();
        scanner.nextLine();
        int[][] matrix = getIntMatrix();
        if(shift < 0){
            shift = - Math.abs(shift) % matrix.length;
        }else{
            shift %= matrix.length;
        }
        int i = (matrix.length - shift) % matrix.length;
        System.out.println(matrix.length);
        do {
            for (int j = 0; j < matrix.length; j++) {
                if(j < matrix.length-1) {
                    System.out.print(matrix[i][j] + "\t");
                }else {
                    System.out.print(matrix[i][j]);
                }
            }
            System.out.println();
            i = (i + 1) % matrix.length;
        }while (i != (matrix.length - shift) % matrix.length);

    }

    /**
     * Найти наибольшее число строго возрастающих элементов последовательности,
     * идущих подряд. Оператор отношения можно определить на множестве,
     * включающем более одного элемента.
     */
    @Override
    public void task14() {
        String[] numbers = getInputString();
        ArrayList<Integer> maxes = new ArrayList<>();
        int count = 1;
        for (int i = 1; i < numbers.length; i++) {
            if (Integer.valueOf(numbers[i - 1]) < Integer.valueOf(numbers[i])) {
                count++;
            }else if (count != 1){
                maxes.add(count);
                count = 1;
            }
        }
        if(count != 1){
            maxes.add(count);
        }
        if(!maxes.isEmpty()){
            System.out.println(Collections.max(maxes));
        }else {
            System.out.println(0);
        }
    }

    /**
     * Найти сумму элементов матрицы, расположенных между первым
     * и вторым положительными элементами каждой строки.
     */
    @Override
    public void task15() {
        int[][] matrix = getIntMatrix();
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            boolean isFirstPositive = false;
            for (int j = 0; j < matrix.length; j++) {
                if(matrix[i][j]>0){
                    if(!isFirstPositive) {
                        isFirstPositive = true;
                    }else{
                        break;
                    }
                }else if(isFirstPositive){
                    sum += matrix[i][j];
                }
            }
        }
        System.out.println(sum);
    }

    /**
     * Повернуть матрицу на 90 градусов против часовой стрелки.
     */
    @Override
    public void task16() {
        int[][] matrix = getIntMatrix();
        System.out.println(matrix.length);
        for (int i = matrix.length-1; i > -1; i--) {
            for (int j = 0; j < matrix.length; j++) {
                if(j < matrix.length-1) {
                    System.out.print(matrix[j][i] + "\t");
                }else {
                    System.out.print(matrix[j][i]);
                }
            }
            System.out.println();
        }
    }

    /**
     * Вычислить определитель матрицы.
     */
    @Override
    public void task17() {
        int[][] matrix = getIntMatrix();
        int principalDiagonal;
        int counterDiagonal;
        int sumPrincipalDiagonal = 0;
        int sumCounterDiagonal = 0;
        for (int i = 0; i < matrix.length; i++) {
            principalDiagonal = 1;
            counterDiagonal = 1;
            for (int j = 0, k = i, count = 0; count < matrix.length; j++,k++,count++) {
                principalDiagonal *= matrix[j][k % matrix.length];
                counterDiagonal *= matrix[matrix.length - 1 - j][k % matrix.length];
            }
            sumPrincipalDiagonal += principalDiagonal;
            sumCounterDiagonal += counterDiagonal;
        }
        System.out.println(sumPrincipalDiagonal - sumCounterDiagonal);
    }

    /**
     * Найти максимальный элемент(ы) в матрице и удалить из матрицы
     * все строки и столбцы, его содержащие.
     */
    @Override
    public void task18() {
        int[][] matrix = getIntMatrix();
        TreeSet<Integer> rows = new TreeSet<>();
        TreeSet<Integer> columns = new TreeSet<>();
        ArrayList<ArrayList<Integer>> newMatrix = new ArrayList<>();
        int max = matrix[0][0];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(max <= matrix[i][j]){
                    if(max < matrix[i][j]){
                        rows.clear();
                        columns.clear();
                        max = matrix[i][j];
                    }
                    rows.add(i);
                    columns.add(j);
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            if(rows.contains(i)){
                continue;
            }
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < matrix.length; j++) {
                if(columns.contains(j)){
                    continue;
                }
                row.add(matrix[i][j]);
            }
            newMatrix.add(row);
        }
        System.out.println(newMatrix.size());
        System.out.println(newMatrix.get(0).size());
        for (int i = 0; i < newMatrix.size(); i++) {
            for (int j = 0; j < newMatrix.get(0).size(); j++) {
                if(j < matrix.length-1){
                    System.out.print(newMatrix.get(i).get(j)+"\t");
                }else {
                    System.out.print(newMatrix.get(i).get(j));
                }
            }
            System.out.println();
        }
    }

    /**
     * Уплотнить матрицу, удаляя из нее строки и столбцы, заполненные нулями.
     */
    @Override
    public void task19() {
        int[][] matrix = getIntMatrix();
        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<Integer> columns = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            if(matrix[i][0]==0 && !rows.contains(i)){
                int j = 0;
                for (;j < matrix.length;j++){
                    if(matrix[i][j] != 0){
                        break;
                    }
                }
                if(j == matrix.length){
                    rows.add(i);
                }
            }
            for (int j = 0; j < matrix.length; j++) {
                if(matrix[i][j]==0 && !columns.contains(j)){
                    int k = i;
                    for (;k < matrix.length; k++){
                        if(matrix[k][j] != 0){
                            break;
                        }
                    }
                    if (k == matrix.length) {
                        columns.add(j);
                    }
                }
            }
        }
        System.out.println(matrix.length - rows.size()+"\n"+(matrix.length - columns.size()));
        for (int i = 0; i < matrix.length ; i++) {
            if(rows.contains(i)){
                continue;
            }
            for (int j = 0; j < matrix.length ; j++) {
                if(columns.contains(j)){
                    continue;
                }
                if(j < matrix.length-1){
                    System.out.print(matrix[i][j]+"\t");
                }else {
                    System.out.print(matrix[i][j]);
                }
            }
            System.out.println();
        }
    }

    /**
     * В матрице найти минимальный элемент и переместить его на место
     * заданного элемента путем перестановки строк и столбцов. Гарантируется,
     * что минимальный элемент в матрице встречается ровно один раз.
     */
    @Override
    public void task20() {
        int rowDest = scanner.nextInt();
        int columnDest = scanner.nextInt();
        int[][] matrix = getIntMatrix();
        int[] min = {matrix[0][0],0,0};
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(min[0] > matrix[i][j]){
                    min[0] = matrix[i][j];
                    min[1] = i;
                    min[2] = j;
                }
            }
        }
        System.out.println(matrix.length);
        int newMatrix[][] = new int[matrix.length][matrix.length];
        for (int i = 0; i < newMatrix.length; i++) {
            for (int j = 0; j < newMatrix.length; j++) {
                newMatrix[i][j] = matrix[i][j];
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            newMatrix[rowDest][i] = matrix[min[1]][i];
            newMatrix[min[1]][i] = matrix[rowDest][i];
        }
        int buffer;
        for (int i = 0; i < matrix.length; i++) {
            buffer = newMatrix[i][columnDest];
            newMatrix[i][columnDest] = newMatrix[i][min[2]];
            newMatrix[i][min[2]] = buffer;
        }

        outputMatrix(newMatrix);

    }

    /**
     * Преобразовать строки матрицы таким образом, чтобы элементы,
     * равные нулю, располагались после всех остальных.
     */
    @Override
    public void task21() {
        int[][] matrix = getIntMatrix();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(i!=matrix.length-1 && j!=matrix.length-1 && matrix[i][j] == 0){
                    if(matrix[i][j+1]!=0){
                        int tmp = matrix[i][j+1];
                        matrix[i][j+1] = matrix[i][j];
                        matrix[i][j] = tmp;
                    }
                }
            }
        }
        System.out.println(matrix.length);
        outputMatrix(matrix);
    }

    /**
     * Округлить все элементы матрицы до целого числа.
     * Использовать округление к ближайшему целому —
     * число округляется до целого с использованием Math.round();
     */
    @Override
    public void task22() {
        int sizeOfMatrix = scanner.nextInt();
        double[][] matrix = new double[sizeOfMatrix][sizeOfMatrix];
        for (int i = 0; i < sizeOfMatrix; i++) {
            scanner.nextLine();
            for (int j = 0; j < sizeOfMatrix; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        System.out.println(matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(j < matrix.length-1){
                    System.out.print(matrix[i][j]+"\t");
                }else {
                    System.out.print(matrix[i][j]);
                }
            }
            System.out.println();
        }
    }

    /**
     * Найти количество всех седловых точек матрицы. Матрица А имеет
     * седловую точку (i, j), если А[i, j] является минимальным
     * элементом в i-й строке и максимальным в j-м столбце.
     */
    @Override
    public void task23() {
        int[][] matrix = getIntMatrix();
        int strMin[] = new int[2];
        int saddlePoint = 0;
        for (int i = 0; i < matrix.length; i++) {
            strMin[0] = matrix[i][0];
            strMin[1] = 0;
            for (int j = 1; j < matrix.length; j++) {
                if(strMin[0] > matrix[i][j]){
                    strMin[0] = matrix[i][j];
                    strMin[1] = j;
                }
            }
            int j = 0;
            for (; j < matrix.length; j++) {
                if(strMin[0] < matrix[j][strMin[1]]){
                    break;
                }
            }
            if(j == matrix.length){
                saddlePoint++;
            }
        }
        System.out.println(saddlePoint);
    }

    /**
     * Перестроить матрицу, переставляя в ней строки так, чтобы сумма
     * элементов в строках полученной матрицы возрастала.
     */
    @Override
    public void task24() {
        int[][] matrix = getIntMatrix();
        System.out.println(matrix.length);
        TreeMap<Integer,Integer> sums = new TreeMap<>();
        for (int i = 0; i < matrix.length; i++) {
            int sum=0;
            for (int j = 0; j < matrix.length; j++) {
                sum += matrix[i][j];
            }
            sums.put(sum,i);
        }
        for (Integer i : sums.values()) {
            for (int j = 0; j < matrix.length; j++) {
                if(j < matrix.length-1){
                    System.out.print(matrix[i][j]+"\t");
                }else {
                    System.out.print(matrix[i][j]);
                }
            }
            System.out.println();
        }
    }

    /**
     * Найти число локальных минимумов. Соседями элемента матрицы назовем
     * элементы, имеющие с ним общую сторону или угол. Элемент матрицы
     * называется локальным минимумом, если он строго меньше всех своих соседей.
     */
    @Override
    public void task25() {
        int[][] matrix = getIntMatrix();
        int mins = 0;
        int step = matrix.length % 2 == 0 ? 2 : 1;
        boolean isSame;
        for (int i = 0; i < matrix.length-1; i+=step) {
            for (int j = 0; j < matrix.length-1; j+=step) {
                int min[]  ={matrix[i][j],i,j };
                for (int k = i; k < i+2; k++) {
                    for (int l = j; l < j+2; l++) {
                        if(min[0] > matrix[k][l]){
                            min[0] = matrix[k][l];
                            min[1] = k;
                            min[2] = l;
                        }
                    }

                }
                isSame = false;
                for (int k = i; k < i+2; k++) {
                    for (int l = j; l < j+2; l++) {
                        if((k != min[1] || l != min[2]) && min[0] == matrix[k][l]){
                            isSame = true;
                            break;
                        }
                    }
                    if(isSame) {
                        break;
                    }
                }
                if(!isSame){
                    mins++;
                }

            }
        }
        System.out.println(mins);
    }

    /**
     * Найти наибольший среди локальных максимумов. Соседями элемента матрицы
     * назовем элементы, имеющие с ним общую сторону или угол. Элемент матрицы
     * называется локальным максимумом, если он строго больше всех своих соседей.
     */
    @Override
    public void task26() {
        int[][] matrix = getIntMatrix();
        ArrayList<Integer> maxes = new ArrayList<>();
        int step = matrix.length % 2 == 0 ? 2 : 1;
        boolean isSame;
        for (int i = 0; i < matrix.length-1; i+=step) {
            for (int j = 0; j < matrix.length-1; j+=step) {
                int max[]  ={matrix[i][j],i,j };
                for (int k = i; k < i+2; k++) {
                    for (int l = j; l < j+2; l++) {
                        if(max[0] < matrix[k][l]){
                            max[0] = matrix[k][l];
                            max[1] = k;
                            max[2] = l;
                        }
                    }

                }
                isSame = false;
                for (int k = i; k < i+2; k++) {
                    for (int l = j; l < j+2; l++) {
                        if((k != max[1] || l != max[2]) && max[0] == matrix[k][l]){
                            isSame = true;
                            break;
                        }
                    }
                    if(isSame) {
                        break;
                    }
                }
                if(!isSame && !maxes.contains(max[0])){
                   maxes.add(max[0]);
                }

            }
        }
        if(maxes.size() == 0){
            System.out.println("NOT FOUND");
        }else {
            System.out.println(Collections.max(maxes));
        }
    }

    /**
     * Перестроить заданную матрицу, переставляя в ней столбцы так,
     * чтобы значения их характеристик убывали. Характеристикой столбца
     * прямоугольной матрицы называется сумма модулей его элементов.
     * Если значения характеристики совпадают - столбцы должны следовать
     * в том же порядке, что и в исходной матрице.
     */
    @Override
    public void task27() {
        int[][] matrix = getIntMatrix();
        System.out.println(matrix.length);
        TreeMap<Integer,Integer> sums = new TreeMap<>(Comparator.reverseOrder());
        for (int i = 0; i < matrix.length; i++) {
            int sum=0;
            for (int j = 0; j < matrix.length; j++) {
                if( matrix[j][i]<0 ){
                    sum += (-matrix[j][i]);
                }else{
                    sum += matrix[j][i];
                }
            }
            sums.put(sum,i);
        }
        Integer[] rowSum =(Integer[])sums.values().toArray();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < rowSum.length; j++) {
                if(j < matrix.length-1){
                    System.out.print(matrix[i][j]+"\t");
                }else {
                    System.out.print(matrix[i][j]);
                }
            }
            System.out.println();
        }
    }

    /**
     * ввод заданного числа строк
     * @return массив строк
     */
    private String[] getInputStrings(){
        int numOfStrings = scanner.nextInt();
        String[] strings = new String[numOfStrings];
        scanner.nextLine();
        for (int i = 0; i < strings.length; i++) {
            strings[i] = scanner.nextLine();
        }
        return strings;
    }

    /**
     * ввод одной строки
     * @return массив строки
     */
    private String[] getInputString(){
        int numOfWords = scanner.nextInt();
        scanner.nextLine();
        return Arrays.copyOfRange(scanner.nextLine().split(" "), 0,numOfWords);
    }

    /**
     * ввод квадратной матрицы заданной размерности
     * @return массив матрицы
     */
    private int[][] getIntMatrix(){
        int sizeOfMatrix = scanner.nextInt();
        int[][] matrix = new int[sizeOfMatrix][sizeOfMatrix];
        for (int i = 0; i < sizeOfMatrix; i++) {
            scanner.nextLine();
            for (int j = 0; j < sizeOfMatrix; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    /**
     * вывод матрицы в консоль
     * @param matrix массив матрицы для вывода в консоль
     */
    private void outputMatrix(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(j < matrix.length-1){
                    System.out.print(matrix[i][j]+"\t");
                }else {
                    System.out.print(matrix[i][j]);
                }
            }
            System.out.println();
        }
    }
}
