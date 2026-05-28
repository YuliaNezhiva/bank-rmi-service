package com.bank;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankServer extends UnicastRemoteObject implements BankService {
    private static final long serialVersionUID = 1L;

    private final Map<String, Account> accounts = new ConcurrentHashMap<>();

    protected BankServer() throws RemoteException {
        super();
    }

    @Override
    public boolean openAccount(String accountNumber, double initialBalance) throws RemoteException {
        if (accounts.containsKey(accountNumber)) {
            System.out.println("[Сервер] Рахунок вже існує: " + accountNumber);
            return false;
        }
        accounts.put(accountNumber, new Account(accountNumber, initialBalance));
        System.out.println("[Сервер] Створено рахунок: " + accountNumber + " (Баланс: " + initialBalance + ")");
        return true;
    }

    @Override
    public boolean deposit(String accountNumber, double amount) throws RemoteException {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
            System.out.println("[Сервер] Рахунок " + accountNumber + " поповнено на " + amount);
            return true;
        }
        return false;
    }

    @Override
    public boolean withdraw(String accountNumber, double amount) throws RemoteException {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            boolean success = account.withdraw(amount);
            System.out.println("[Сервер] Зняття з рахунку " + accountNumber + " суми " + amount + ": " + (success ? "Успішно" : "Відхилено"));
            return success;
        }
        return false;
    }

    @Override
    public double getBalance(String accountNumber) throws RemoteException {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            return account.getBalance();
        }
        throw new RemoteException("Рахунок не знайдено");
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            BankServer server = new BankServer();
            registry.rebind("BankService", server);
            System.out.println("=== RMI Банк-Сервер запущено ===");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}