package com.wallet.entities;
public class Count {
    private int idCount;
    private String bankName;
    private String userName;
    private String countNumber;
    private String accountType;
    private Currency currency;
    private double balance;
        public Count(int idCount, String bankName, String userName, String countNumber, String accountType, int currency, double balance){
            this.idCount = idCount;
            this.bankName = bankName;
            this.userName = userName;
            this.countNumber = countNumber;
            this.accountType = accountType;
            this.currency = currency;
            this.balance = balance;
        }
    public int getIdCount(){
        return idCount;
    }
    public String getBankName(){
        return bankName;
    }
    public String getUserName(){
        return userName;
    }
    public String getCountNumber(){
        return countNumber;
    }
    public String getAccountType(){
        return accountType;
    }
    public int getCurrency(){
        return currency;
    }
    public double getBalance(){
        return balance;
    }
        public String setBankName(){
            return bankName;
        }
        public String setUserName(){
            return userName;
        }
        public String setCountNumber(){
            return countNumber;
        }
        public String setAccountType(){
            return accountType;
        }

        public void setCurrency(Currency currency){
            this.currency = currency;
        }
        public double setBalance(){
            return balance;
        }
    @Override
    public String toString(){
        return "Count: \n" +
                "id: " + idCount + "\n" +
                "bank name: " + bankName + "\n" +
                "username: " + userName + "\n" +
                "count number: " + countNumber +
                "acounttype: " + accountType + "\n" +
                "your currency: " + currency + "\n" +
                "balance: " + balance;
    }
}
