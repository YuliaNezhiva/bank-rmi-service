package com.bank;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BankService extends Remote {
    boolean openAccount(String accountNumber, double initialBalance) throws RemoteException;
    boolean deposit(String accountNumber, double amount) throws RemoteException;
    boolean withdraw(String accountNumber, double amount) throws RemoteException;
    double getBalance(String accountNumber) throws RemoteException;
}