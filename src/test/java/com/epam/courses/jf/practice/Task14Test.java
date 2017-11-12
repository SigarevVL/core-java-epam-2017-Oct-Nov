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

public class Task14Test {

    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    protected final ISolver solver = new Solver();


    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setIn(null);
    }

    @Test
    public void case1() {
        String dataIn = "8\n" + "2 1 3 3 4 5 6 5";
        System.setIn(new ByteArrayInputStream(dataIn.getBytes()));
        solver.task14();
        String outData = outContent.toString();
        assertTrue("Test14 Failed!", outData.equals("4"));
    }
}