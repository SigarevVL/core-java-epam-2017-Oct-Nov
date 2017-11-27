package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask16;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * On the checkered paper a circle is drawn.
 * Outputs at file a description of all the cells
 * that lie entirely inside the circle.
 * Outputs in order of increasing distance from
 * the cell to the center of the circle.
 * Uses SortedMap.
 */
public class Task16 implements ITestableTask16 {

    /**
     * Performs analysis of the transmitted points,
     * finds among them points lying inside the circle.
     *
     * @param center The point at which the center of the circle is located.
     * @param radius The radius of the circle.
     * @param output File for  results of output.
     * @return File with the results of the analysis.
     */
    @Override
    public IFileWithPoints analyze(I2DPoint center, int radius, File output) {
        double centerY = center.getY();
        double centerX = center.getX();

        Queue<I2DPoint> queue = new PriorityQueue<>();
        SortedMap<I2DPoint, Double> sortedMap = new TreeMap<>(
                new Comparator<I2DPoint>() {
                    @Override
                    public int compare(I2DPoint o1, I2DPoint o2) {
                        double o1Radius = Math.sqrt(Math.pow(o1.getX() - centerX, 2)
                                + Math.pow(o1.getY() - centerY, 2));
                        double o2Radius = Math.sqrt(Math.pow(o2.getX() - centerX, 2)
                                + Math.pow(o2.getY() - centerY, 2));
                        return o1Radius > o2Radius ? 1 : -1;
                    }
                });

        for (int i = (int) Math.floor(radius + centerY); i
                >= (int) Math.floor(-(radius + centerY)); i--) {

            for (int j = (int) Math.floor(radius + centerX); j
                    >= (int) Math.floor(-(radius + centerX)); j--) {

                double radiusToPoint = Math.sqrt(Math.pow(i - centerX, 2)
                        + Math.pow(j - centerY, 2));

                if (radiusToPoint < radius) {
                    sortedMap.put(new I2DPointImpl(i, j), radiusToPoint);
                }
            }
        }

        try (PrintWriter writer = new PrintWriter(output)) {
            for (Map.Entry<I2DPoint, Double> entry : sortedMap.entrySet()) {
                writer.write(entry.getKey().toString()
                        + entry.getValue() + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new IFileWithPointsImpl(output, sortedMap);
    }

    /**
     * Represents a file that contains information
     * about the found points.
     */
    class IFileWithPointsImpl implements IFileWithPoints {

        private File file;
        private SortedMap<I2DPoint, Double> sortedMap;

        public IFileWithPointsImpl(File file,
                                   SortedMap<I2DPoint, Double> sortedMap) {
            this.file = file;
            this.sortedMap = new TreeMap<>(sortedMap);
        }

        /**
         * @return File with the results of the analysis.
         */
        @Override
        public File getFile() {
            return file;
        }

        /**
         * Extracts from the file information about the stored points.
         *
         * @return Set of pairs: point + distance to the center.
         */
        @Override
        public SortedMap<I2DPoint, Double> getPoints() {
            return sortedMap;
        }
    }


}