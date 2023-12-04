package com.wallet.entities;
import java.time.LocalDate;

public class Transaction {
    private int idTransaction;
    private String typeTransaction;
    private LocalDate dateTransaction;
    private double amount;
    private Count count;
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
        return count.getIdCount();
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
                    "id: " + idTransaction + "\n" +
                    "typetransaction: " + typeTransaction + "\n" +
                    "date: " + dateTransaction + "\n" +
                    "About your count: id= " + count.getIdCount() + ", count number: " + count.getCountNumber() + ", bank name: " + count.getBankName();
        }
}
