package com.almaz.task1.classes_block;

    /*
      I

      Создать класс с двумя переменными. Добавить функцию вывода на экран
      и функцию изменения этих переменных. Добавить функцию, которая находит
      сумму значений этих переменных, и функцию, которая находит наибольшее
      значение из этих двух переменных.
     */
public class MyClass {
    private int value1;
    private int value2;

    public void setValue1(int value1) {
        this.value1 = value1;
    }

    public void setValue2(int value2) {
        this.value2 = value2;
    }

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }

    public void printValue1() {
        System.out.println("Value1 = " + getValue1());
    }

    public void printValue2() {
        System.out.println("Value2 = " + getValue2());
    }

    public int getSumValues() {
        return value1 + value2;
    }

    public int getMaxValue() {
        return Math.max(value1, value2);
    }

}
