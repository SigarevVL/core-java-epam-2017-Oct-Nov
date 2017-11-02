package com.epam.courses.jf.practice;

import com.epam.courses.jf.practice.common.first.ISolver;
import com.epam.courses.jf.practice.nbikbaev.first.Solver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class Task5Test {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ISolver solver = new Solver();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void case1() {
        String data1 = "5" + "\n" +
                "Язык программирования Java is widespread" + "\n";
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        solver.task5();
        assertTrue("Test5 Failed!", outContent.toString().equals("2\r\n"));
    }

    @Test
    public void case2() {
        String data1 = "5" + "\n" +
                "Язык программиro2вания Java is widespread" + "\n";
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        solver.task5();
        assertTrue("Test5 Failed!", outContent.toString().equals("2\r\n"));
    }

    @Test
    public void case3() {
        String data1 = "5" + "\n" +
                "Компилятор javac" + "\n";
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        solver.task5();
        assertTrue("Test5 Failed!", outContent.toString().equals("0\r\n"));
    }


    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setIn(null);
    }
}
