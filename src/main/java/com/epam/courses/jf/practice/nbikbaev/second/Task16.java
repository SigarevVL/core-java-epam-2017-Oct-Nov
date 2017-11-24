package com.epam.courses.jf.practice.nbikbaev.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask16;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Task16 implements ITestableTask16 {
    @Override
    public IFileWithPoints analyze(I2DPoint center, int radius, File output) {

        SortedMap<I2DPoint, Double> map = new TreeMap<>((o1, o2) ->
                (distance(o1, center) < distance(o2, center)) ? -1 : 1);
        findPoints(center, radius, map);
        writeToFile(output, map);
        return new FileWithPoints(output, map);
    }

    private void findPoints(I2DPoint center, int radius, SortedMap<I2DPoint, Double> map) {
        for (int i = (int) (center.getY() - radius); i <= center.getY() + radius; i++) {
            for (int j = (int) (center.getX() - radius); j <= center.getX() + radius; j++) {
                I2DPoint point = new Point2DImpl(j, i);
                double testRadius = distance(center, point);
                if (testRadius < radius) {
                    map.put(point, testRadius);
                }
            }
        }
    }

    private void writeToFile(File output, SortedMap<I2DPoint, Double> map) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            for (I2DPoint point : map.keySet()) {
                writer.write(point.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double distance(I2DPoint point1, I2DPoint point2) {
        double x = Math.pow(point1.getX() - point2.getX(), 2);
        double y = Math.pow(point1.getY() - point2.getY(), 2);
        return Math.sqrt(x + y);
    }

    private class FileWithPoints implements IFileWithPoints {

        private File file;
        private SortedMap<I2DPoint, Double> points;

        public FileWithPoints(File file, SortedMap<I2DPoint, Double> points) {
            this.file = file;
            this.points = points;
        }

        @Override
        public File getFile() {
            return file;
        }

        @Override
        public SortedMap<I2DPoint, Double> getPoints() {
            return points;
        }
    }

}