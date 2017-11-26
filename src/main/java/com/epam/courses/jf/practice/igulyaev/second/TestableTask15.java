package com.epam.courses.jf.practice.igulyaev.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask15;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class TestableTask15 implements ITestableTask15 {
    @Override
    public IFileWithLines analyze(Set<I2DPoint> points, File output) {
        List<I2DPoint> pointList = new ArrayList<>(points);
        final ListIterator<I2DPoint> iterator = pointList.listIterator();
        Set<ILine> lines = new HashSet<>();
        if(iterator.hasNext()){
            I2DPoint vectorStart = iterator.next();
            while (iterator.hasNext()) {
              I2DPoint vectorEnd = iterator.next();
                if(lines.stream().noneMatch(line -> line.getPoints().containsAll(Arrays.asList(vectorStart, vectorEnd)))){
                    continue;
                }
                Line line = new Line(vectorStart, vectorEnd);
                Vector vector = new Vector(vectorStart, vectorEnd);
                for (Iterator<I2DPoint> iterator2 = pointList.listIterator(iterator.nextIndex()); iterator2.hasNext();){
                    I2DPoint p = iterator2.next();
                    if(vector.isCollinear(new Vector(vectorStart, p))){
                        line.addPoint(p);
                    }
                }
                if(line.points.size() > 2){
                    lines.add(line);
                }
            }
        }
        try(FileOutputStream fileOutputStream = new FileOutputStream(output);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            lines.forEach(obj -> {
                try {
                    objectOutputStream.writeObject(obj);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FileWithLines(output);
    }
    static private class FileWithLines implements IFileWithLines {
        File file;
        Set<ILine> lines;
        /**
         * @return Файл с результатами анализа.
         */
        FileWithLines(File file){
            this.file = file;
        }
        public File getFile(){
            return file;
        }

        /**
         * Извлекает из файла информацию о хранящихся в нем линиях.
         * @return Множество линий, найденных в результате анализа.
         */
        public Set<ILine> getLines(){
            if(lines == null){
                synchronized (this){
                    if(lines == null){
                        lines = new HashSet<>();
                        try(FileInputStream fileInputStream = new FileInputStream(file);
                            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
                            ILine line;
                            while ((line = (ILine) objectInputStream.readObject()) != null){
                                lines.add(line);
                            }
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return lines;
        }
    }

    /**
     * Прямая, заданная точками, входящими в исходное множество.
     */
    private static class Line implements ILine {
        Set<I2DPoint> points;
        /** @return Точки, через которые проходит прямая */
        public Set<I2DPoint> getPoints(){
            return null;
        }
        boolean addPoint(I2DPoint p){
            return points.add(p);
        }
        public Line(){ }

        Line(I2DPoint a, I2DPoint b){
            points = new HashSet<>();
        }

        @Override
        public int hashCode() {
            return points.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof Line))
                return false;
            Line line = (Line) o;
            return line.getPoints().equals(points);
        }
    }
}