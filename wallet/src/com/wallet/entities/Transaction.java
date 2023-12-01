package com.wallet.entities;
import java.time.LocalDate;

public class Transaction {
    private int id;
    private String typeTransaction;
    private LocalDate dateTransaction;
    private double amount;
    private Count count;
        public Transaction(int id, String typeTransaction, LocalDate dateTransaction, double amount, Count count){
            this.id = id;
            this.typeTransaction = typeTransaction;
            this.dateTransaction = dateTransaction;
            this.amount = amount;
            this.count = count;
        }
    public int getId(){
        return id;
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
        return count.getId();
    }
    public String getCountNumber(){
        return count.getCountNumber();
    }
    public String getBankName(){
        return count.getBankName();
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
        public String setCountNumber(){
            return count.setCountNumber();
        }
        public String setBankName(){
            return count.setBankName();
        }
  
        @Override
        public String toString(){
            return "transaction:\n" +
                    "id: " + id + "\n" +
                    "typetransaction: " + typeTransaction + "\n" +
                    "date: " + dateTransaction + "\n" +
                    "About your count: id= " + count.getId() + ", count number: " + count.getCountNumber() + ", bank name: " + count.getBankName();
        }
}
