package com.example.assignment1;

public class Payment {
    private double hours;
    private double rate;
    private double pay;
    private double overtimePay;
    private double totalPay;
    private double tax;

    public Payment(double hours, double rate, double pay, double overtimePay, double totalPay, double tax) {
        this.hours = hours;
        this.rate = rate;
        this.pay = pay;
        this.overtimePay = overtimePay;
        this.totalPay = totalPay;
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "Hours: " + hours + "\n" +
                "Rate: $" + rate + "/hr\n" +
                "Total Pay: $" + String.format("%.2f", totalPay) + "\n" +
                "Tax: $" + String.format("%.2f", tax) + "\n" +
                "Final Pay: $" + String.format("%.2f", totalPay-tax);

    }
}