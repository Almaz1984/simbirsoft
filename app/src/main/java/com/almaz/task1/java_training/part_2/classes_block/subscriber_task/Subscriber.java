package com.almaz.task1.java_training.part_2.classes_block.subscriber_task;

/*
      V

      Класс Абонент: Идентификационный номер, Фамилия, Имя, Отчество, Адрес,
      Номер кредитной карточки, Дебет, Кредит, Время междугородных и городских переговоров;
      Конструктор; Методы: установка значений атрибутов, получение значений атрибутов,
      вывод информации. Создать массив объектов данного класса.
      Вывести сведения относительно абонентов, у которых время городских переговоров
      превышает заданное.  Сведения относительно абонентов, которые пользовались
      междугородной связью. Список абонентов в алфавитном порядке.
     */

import androidx.annotation.NonNull;

public class Subscriber {
    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String address;
    private int creditCardNumber;
    private double debit;
    private double credit;
    private int longDistanceTalksTime;
    private int localTalksTime;

    public Subscriber(int id,
                      String lastName,
                      String firstName,
                      String middleName,
                      String address,
                      int creditCardNumber,
                      double debit,
                      double credit,
                      int longDistanceTalksTime,
                      int localTalksTime) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.address = address;
        this.creditCardNumber = creditCardNumber;
        this.debit = debit;
        this.credit = credit;
        this.longDistanceTalksTime = longDistanceTalksTime;
        this.localTalksTime = localTalksTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(int creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public int getLongDistanceTalksTime() {
        return longDistanceTalksTime;
    }

    public void setLongDistanceTalksTime(int longDistanceTalksTime) {
        this.longDistanceTalksTime = longDistanceTalksTime;
    }

    public int getLocalTalksTime() {
        return localTalksTime;
    }

    public void setLocalTalksTime(int localTalksTime) {
        this.localTalksTime = localTalksTime;
    }

    @NonNull
    @Override
    public String toString() {
        return "id: " + getId() +
                ", Last name: " + getLastName() +
                ", First name: " + getFirstName() +
                ", Middle name: " + getMiddleName() +
                ", Address: " + getAddress() +
                ", Credit card number: " + getCreditCardNumber() +
                ", Debit: " + getDebit() +
                ", Credit: " + getCredit() +
                ", Long distance talks time: " + getLongDistanceTalksTime() +
                ", Local talks time: " + getLocalTalksTime();
    }
}
