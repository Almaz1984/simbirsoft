package com.almaz.task1;

public class PracticeBlock {
    /*
      Написать простое лямбда-выражение в переменной myClosure, лямбда-выражение должно выводить
      в консоль фразу "I love Java". Вызвать это лямбда-выражение. Далее написать функцию, которая
      будет запускать заданное лямбда-выражение заданное количество раз. Объявить функцию так:
      public void repeatTask (int times, Runnable task). Функция должна запускать times раз
      лямбда-выражение task . Используйте эту функцию для печати "I love Java" 10 раз.
     */

    public static class Lambda {

        public static void main() {
            Runnable myClosure = () -> System.out.println("I love Java");
            repeatTask(10, myClosure);
        }

        public static void repeatTask(int times, Runnable task) {
            for (int i = 0; i < times; i++) {
                task.print();
            }
        }

    }

    interface Runnable {
        void print();
    }

    /*
      Условия: есть начальная позиция на двумерной плоскости, можно осуществлять последовательность
      шагов по четырем направлениям up, down, left, right. Размерность каждого шага равна 1.
      Задание:
      Создать enum Directions с возможными направлениями движения
      Создать метод, принимающий координаты и одно из направлений и возвращающий координаты
      после перехода по направлению.
      Создать метод, осуществляющий несколько переходов от первоначальных координат и выводящий
      координаты после каждого перехода. Для этого внутри метода следует определить переменную
      location с начальными координатами (0,0) и массив направлений, содержащий элементы
      [up, up, left, down, left, down, down, right, right, down, right], и програмно вычислить
      какие будут координаты у переменной location после выполнения этой последовательности шагов.
      Для вычисленеия результата каждого перемещения следует использовать созданный ранее метод
      перемещения на один шаг.
     */


    public static class DirectionsTask {
        public static void moveByDirectionList(Directions[] directionArray) {
            Point currentPoint = new Point(0, 0);
            for (Directions direction : directionArray) {
                currentPoint = changeCoordinate(currentPoint, direction);
                System.out.println("x:" + currentPoint.getX() + " y:" + currentPoint.getY());
            }
        }

        public static Point changeCoordinate(Point currentPoint, Directions direction) {
            Point newPoint = new Point(currentPoint.getX(), currentPoint.getY());

            switch (direction) {
                case UP:
                    newPoint.setY(currentPoint.getY() + 1);
                    break;
                case DOWN:
                    newPoint.setY(currentPoint.getY() - 1);
                    break;
                case LEFT:
                    newPoint.setX(currentPoint.getX() - 1);
                    break;
                case RIGHT:
                    newPoint.setX(currentPoint.getX() + 1);
                    break;
            }

            return  newPoint;
        }

        public static class Point {
            private double x;
            private double y;

            public Point(double x, double y) {
                this.x = x;
                this.y = y;
            }

            public double getX() {
                return x;
            }

            public void setX(double x) {
                this.x = x;
            }

            public double getY() {
                return y;
            }

            public void setY(double y) {
                this.y = y;
            }
        }
    }

    public enum Directions {
        UP,
        DOWN,
        LEFT,
        RIGHT

    }

    /*
      Создать интерфейс Shape с двумя методами perimeter и area, выводящими периметр и площадь
      фигуры соответственно, после чего реализовать и использовать для вывода периметра и площади
      следующие классы, реализующие интерфейс Shape:
      Rectangle - прямоугольник с двумя свойствами: ширина и длина
      Square - квадрат с одним свойством: длина стороны
      Circle - круг с одним свойством: диаметр круга
     */
    public interface Shape {
        double perimeter();

        double area();
    }

    public static class Circle implements Shape {
        private double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        public double getRadius() {
            return radius;
        }

        public void setRadius(double radius) {
            this.radius = radius;
        }

        @Override
        public double perimeter() {
            return 2 * Math.PI * radius;
        }

        @Override
        public double area() {
            return 2 * Math.PI * Math.pow(radius, 2);
        }
    }

    public static class Rectangle implements Shape {
        private double height;
        private double width;

        public Rectangle(double height, double width) {
            this.height = height;
            this.width = width;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        @Override
        public double perimeter() {
            return 2 * (width + height);
        }

        @Override
        public double area() {
            return width * height;
        }
    }

    public static class Square implements Shape {
        double side;

        public double getSide() {
            return side;
        }

        public void setSide(double side) {
            this.side = side;
        }

        @Override
        public double perimeter() {
            return 4 * side;
        }

        @Override
        public double area() {
            return Math.pow(side, 2);
        }
    }
}
