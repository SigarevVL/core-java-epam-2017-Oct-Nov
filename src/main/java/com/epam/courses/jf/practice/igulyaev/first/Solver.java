package com.epam.courses.jf.practice.igulyaev.first;

import com.epam.courses.jf.practice.common.first.ISolver;
import com.epam.courses.jf.practice.common.first.Reader;

import java.util.*;
import java.util.stream.Collectors;

public class Solver implements ISolver {
    //private Reader reader;
    /*public Solver(){

    }*/
    @Override
    public void task1() {
        final Reader reader = new Reader(System.in);
        int n = Integer.parseInt(reader.readLine());
        String maxString = reader.readLine();
        String minString = maxString;
        for(int i = 1; i < n; ++i){
            String str = reader.readLine();
            if(str.length() >= maxString.length()){
                maxString = str;
            }
            if(str.length() <= minString.length()){
                minString = str;
            }
        }
        System.out.printf("MIN (%d): \"%s\"%n", minString.length(), minString);
        System.out.printf("MAX (%d): \"%s\"%n", maxString.length(), maxString);
    }

    @Override
    public void task2(){
        final Reader reader = new Reader(System.in);
        Set<String> stringSet = new TreeSet<>((s1,s2) ->
                s1.length() < s2.length() ? -1 : (s1.length() > s2.length() ? 1 : s1.compareTo(s2))
        );

        int n = Integer.parseInt(reader.readLine());

        for(int i = 0; i < n; ++i){
            stringSet.add(reader.readLine());
        }

        stringSet.forEach(s -> System.out.printf("(%d): \"%s\"%n", s.length(), s));
    }

    @Override
    public void task3(){
        final Reader reader = new Reader(System.in);
        List<String> stringList = new LinkedList<>();
        int n = Integer.parseInt(reader.readLine());
        long sizeSum = 0;
        for(int i = 0; i < n; ++i){
            String str = reader.readLine();
            sizeSum += str.length();
            stringList.add(str);
        }
        final long averageLength = sizeSum / n;
        System.out.printf("AVERAGE (%d)%n", averageLength);
        stringList.stream()
                .filter(s -> s.length() < averageLength)
                .forEach(s -> System.out.printf("(%d): \"%s\"%n", s.length(), s));
    }

    @Override
    public void task4(){
        final Reader reader = new Reader(System.in);
        int n = Integer.parseInt(reader.readLine());
        Arrays.stream(reader.readLine().split(" ", n))
                .filter(s -> s.matches("^\\p{Alpha}+$"))
                .reduce((first, second) ->
                        first.chars().distinct().count() <= second.chars().distinct().count() ? first : second)
                .ifPresent(System.out::println);
    }

    @Override
    public void task5(){
        final Reader reader = new Reader(System.in);
        int n = Integer.parseInt(reader.readLine());
        System.out.println(
                Arrays.stream(reader.readLine().split(" ", n))
                        .filter(s -> s.matches("^\\p{Alpha}+$"))
                        .filter(s ->
                                ((s.split("[aeiouy]", -1).length - 1) * 2) == s.length()
                        ).count()
        );
    }

    @Override
    public void task6(){
        final Reader reader = new Reader(System.in);
        int n = Integer.parseInt(reader.readLine());
        System.out.println(
                Arrays.stream(reader.readLine().split(" ", n))
                        .filter(s -> s.length() > 1)
                        .filter(s -> {
                            char buffer = 0;
                            for (char c : s.toCharArray() ) {
                                if(c <= buffer){
                                    return false;
                                }
                                buffer = c;
                            }
                            return true;
                        }).findFirst().orElse("NOT FOUND")
        );
    }

    @Override
    public void task7(){
        final Reader reader = new Reader(System.in);
        int n = Integer.parseInt(reader.readLine());
        List<String> stringList = Arrays.stream(reader.readLine().split(" ", n))
                .distinct()
                .filter(s -> s.chars().distinct().count() == s.length())
                .collect(Collectors.toList());
        if(stringList.isEmpty()){
            System.out.println("NOT FOUND");
        } else {
            stringList.forEach(s -> System.out.printf("%s ", s));
        }
    }

    @Override
    public void task8(){
        final Reader reader = new Reader(System.in);
        int n = Integer.parseInt(reader.readLine());
        System.out.println(Arrays.stream(reader.readLine().split(" ", n))
                .filter(s -> s.matches("^\\d+$"))
                .filter(s ->{
                    char[] chars = s.toCharArray();
                    Stack<Character> stack = new Stack<>();
                    for(int i = 0; i < chars.length / 2; ++i){
                        stack.push(chars[i]);
                    }
                    for(int i = chars.length / 2 + chars.length % 2; i < chars.length; ++i){
                        char с = stack.pop();
                        if(с != chars[i]){
                            stack.push(с);
                        }
                    }
                    return stack.empty();
                }).limit(2).reduce((first, second) -> second).orElse("NOT FOUND")
        );
    }

    @Override
    public void task9(){
        final Reader reader = new Reader(System.in);
        int n = Integer.parseInt(reader.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 1; i <= n*n; ++i){
            stringBuilder.append(i);
            stringBuilder.append(i % n == 0 ? "\n" :"\t");
        }
        System.out.print(stringBuilder);
    }
}
