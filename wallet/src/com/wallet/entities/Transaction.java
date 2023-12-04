package com.wallet.entities;
import java.time.LocalDate;

public class Transaction {
    private int idTransaction;
    private String typeTransaction;
    private LocalDate dateTransaction;
    private double amount;
    private int count;
        public Transaction(int idTransaction, String typeTransaction, LocalDate dateTransaction, double amount, int count){
            this.idTransaction = idTransaction;
            this.typeTransaction = typeTransaction;
            this.dateTransaction = dateTransaction;
            this.amount = amount;
            this.count = count;
        }
    public int getIdTransaction(){
        return idTransaction;
    }
    public String getTypeTransaction(){
        return typeTransaction;
    }
    public LocalDate getDateTransaction(){
        return dateTransaction;
    }
    public double getAmount(){
        return amount;
    }
    public int getCountId(){
        return count;
    }

        public String setTypeTransaction(){
            return typeTransaction;
        }
        public LocalDate setDateTransaction(){
            return dateTransaction;
        }
        public double setAmount(){
            return amount;
        }
  
        @Override
        public String toString(){
            return "transaction:\n" +
                    "id: " + idTransaction + "\n" +
                    "typetransaction: " + typeTransaction + "\n" +
                    "date: " + dateTransaction + "\n";
        }
}
