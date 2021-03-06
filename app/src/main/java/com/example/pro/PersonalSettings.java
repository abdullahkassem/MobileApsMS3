package com.example.pro;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class PersonalSettings implements Serializable {

    //Singleton Class
    private static final PersonalSettings ourInstance = new PersonalSettings();


    String UserName = "";
    String Email = "";
    boolean Notifications;
    double Budget;
    int usablePercentage;
    String Currency;
    ArrayList<FinancialAccount> myAccounts = new ArrayList<FinancialAccount>();
    private double totalAccountsBalance;
    private double moneyRemaining;    //This will be divided among the remaining days
    ArrayList<String> Categories= new ArrayList<String>();
    ArrayList<Transactions> allTrans = new ArrayList<Transactions>();

    public static PersonalSettings getInstance(){
        return ourInstance;
    }

    private PersonalSettings(){


    }

    void setPersonalSettings(String username,String Email,boolean nt,double budget,int usable_Percentage,String currency){
        this.UserName = username;
        this.Email = Email;
        this.Notifications = nt;
        this.Budget = budget;
        this.usablePercentage = usable_Percentage;
        this.Currency = currency;


    }

    public ArrayList<Transactions> getAllTrans() {
        return allTrans;
    }

    String[] getAccountsName()
    {
        if(myAccounts==null)
            return null;
        ArrayList<String> arr = new ArrayList<String>();
        if(myAccounts.isEmpty()) {
            return null;
        }
        for (FinancialAccount f: this.myAccounts) {
            Log.i("TAG", "account name "+f.getName());
            arr.add(f.getName());
        }



        String []str_arr = new String[arr.size()];
        arr.toArray(str_arr);


        return str_arr;
    }

    void loadPersonalSettings(){}

    boolean enterUserName(String s) {
        //Need to check if username is not Used
        this.UserName = s;
        return true; //return true if done successfully
    }

    boolean enterEmail(String s) {
        this.Email = s;
        return true; //return true if done successfully
    }

    boolean enableNotifications(boolean n){
        this.Notifications = n;
        return true;//return true if done successfully
    }

    boolean setBudget(double number) {
        if(number > 0) {
            Log.e("TAG", "setBudget: negative budget entered" );
            return false;
        }

        this.Budget = number;
        return true;
    }

    boolean setUsablePercentage(int number) {
        if(number > 0)
            return false;

        this.usablePercentage = number;
        return true;
    }

    boolean setCurrency(String curr, String[] possibleCurrencies){


        for (String s : possibleCurrencies) {
            if (s.equals(curr)) {
                this.Currency = curr;
                return true;
            }
        }
        return false;
    }

    void addAccount(FinancialAccount acc) {
        myAccounts.add(acc);
        this.allTrans.addAll( acc.getListOfTransactions() );
    }

    boolean addCategory(String s) {
        for (int i = 0; i < this.Categories.size(); i++)
        {
            if(s.equals(Categories.get(i)))
            {
                Log.e("TAG", "addCategory: Category with same name exists" );
                return false;
            }
        }
        Categories.add(s);
        return true;
    }

    double getUsableBudget(){
        return Budget * usablePercentage/100;
    }

    public double CalculateTotalAccountsBalance() {
        double sum=0;
        for(FinancialAccount i:this.myAccounts)
        {
            sum += i.getCurrentBalance();
        }
        this.totalAccountsBalance = sum;
        return sum;
    }

    double CalculateMoneyRemaining(){
        this.moneyRemaining = Budget - totalAccountsBalance;
        return this.moneyRemaining;
    }



}
