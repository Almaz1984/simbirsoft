package com.almaz.task1.java_training.part_2.classes_block.triangle;

/*
      III

      Описать класс, представляющий треугольник. Предусмотреть методы для создания объектов,
      вычисления площади, периметра и точки пересечения медиан.
      Описать свойства для получения состояния объекта.
     */

import androidx.annotation.NonNull;

public class Triangle {
    Point pointA;
    Point pointB;
    Point pointC;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.pointA = new Point(x1, y1);
        this.pointB = new Point(x2, y2);
        this.pointC = new Point(x3, y3);
    }

    public double getPerimeter() {
        return getLengthAb() + getLengthBc() + getLengthCa();
    }

    public double getSideLength(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2) + Math.pow(point2.getY() - point1.getY(), 2));
    }

    public double getSquare() {
        double halfPerimeter = getPerimeter() / 2.0;
        return Math.sqrt(halfPerimeter * (halfPerimeter - getLengthAb()) *
                (halfPerimeter - getLengthBc()) * (halfPerimeter - getLengthCa()));
    }

    public double getLengthAb() {
        return getSideLength(pointA, pointB);
    }

    public double getLengthBc() {
        return getSideLength(pointB, pointC);
    }

    public double getLengthCa() {
        return getSideLength(pointC, pointA);
    }

    public Point getPointA() {
        return pointA;
    }

    public void setPointA(Point pointA) {
        this.pointA = pointA;
    }

    public Point getPointB() {
        return pointB;
    }

    public void setPointB(Point pointB) {
        this.pointB = pointB;
    }

    public Point getPointC() {
        return pointC;
    }

    public void setPointC(Point pointC) {
        this.pointC = pointC;
    }

    public Point getMedianPoint() {
        double x = (getPointA().getX() + getPointB().getX() + getPointC().getX()) / 3;
        double y = (getPointA().getY() + getPointB().getY() + getPointC().getY()) / 3;
        return new Point(x, y);
    }

    @NonNull
    @Override
    public String toString() {
        return "Perimeter: " + getPerimeter() +
                ", Square: " + getSquare() +
                ", Median x point: " + getMedianPoint().getX() +
                ", Median y point: " + getMedianPoint().getY();
    }
}

