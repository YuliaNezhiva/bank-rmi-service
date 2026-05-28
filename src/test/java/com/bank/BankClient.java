package com.bank;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BankClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            BankService bank = (BankService) registry.lookup("BankService");
            System.out.println("=== Підключено до RMI Сервісу ===\n");

            String myAcc = "UA16-KB21-2026";

            System.out.println("1. Створення рахунку...");
            bank.openAccount(myAcc, 1000.0);
            System.out.println("Поточний баланс: " + bank.getBalance(myAcc));

            System.out.println("\n2. Поповнення на 500...");
            bank.deposit(myAcc, 500.0);
            System.out.println("Поточний баланс: " + bank.getBalance(myAcc));

            System.out.println("\n3. Зняття 300...");
            bank.withdraw(myAcc, 300.0);
            System.out.println("Кінцевий баланс: " + bank.getBalance(myAcc));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}