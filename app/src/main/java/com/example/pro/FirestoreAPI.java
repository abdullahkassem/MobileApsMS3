package com.example.pro;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class FirestoreAPI {

    private static FirestoreAPI ourInstance = new FirestoreAPI();

    public String my_UID;
    DocumentReference myUserDoc;
    PersonalSettings mysettings;

    private FirestoreAPI()
    {
        my_UID = "N/A";
    }

    public static FirestoreAPI getInstance()
    {
        return ourInstance;
    }

    void LoadData(String s){    //Load user Data from Database
        this.my_UID = s;
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query query = db.collection("users").whereEqualTo("uid",my_UID);
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.i(TAG,"user document id: "+ document.getId() + " data: " + document.getData());
                                InitializePersonalSettings(document.getData());
                                myUserDoc = document.getReference();
                                ReadAccounts();
                            }
                        } else {
                            Log.i(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


    }

    public void createUser(String userame, String id, double usable_percentage, boolean notification, double budget, String currency) {


    }

    void addAccount(){}

    void addTransaction(){}

    void InitializePersonalSettings(Map<String,Object> userInformationMap)
    {
        String name = (String) userInformationMap.get("username");
        boolean notifications = (boolean) userInformationMap.get("notification");
        int percent = ( (Long) userInformationMap.get("usable percentage") ).intValue();
        String email = (String) userInformationMap.get("email address");
        String curr = (String) userInformationMap.get("currency");
        double budget =( (Long) userInformationMap.get("budget") ).doubleValue();
        //Log.i(TAG, "InitializePersonalSettings: budget read is " + budget);


        mysettings = PersonalSettings.getInstance();
        mysettings.setPersonalSettings(name,email,notifications,budget,percent,curr);

    }

    FinancialAccount createFinAcc(Map<String,Object> accountMap)
    {
        String Accname = (String) accountMap.get("account name");
        double OutstandingBalance =( (Long) accountMap.get("OutstandingBalance") ).doubleValue();
        double Expense =( (Long) accountMap.get("Expense") ).doubleValue();
        double Income =( (Long) accountMap.get("Income") ).doubleValue();

        Log.i(TAG, "createFinAcc: outBal read is " + OutstandingBalance);

        FinancialAccount f = new FinancialAccount(Accname,OutstandingBalance);

        return f;
    }

    void ReadAccounts(){    //Reads all the user's accounts


        myUserDoc.collection("accounts").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.i(TAG, "document in collection with id " + document.getId() + " => " + document.getData());
                                //for every account
                                FinancialAccount f= createFinAcc(document.getData());
                                readTransactions(f,document.getReference().collection("transactions"));
                                mysettings.addAccount(f);

                            }
                        } else {
                            Log.i(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

    void readTransactions(FinancialAccount f, CollectionReference c){   //reads all transactions in account

        c.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.i(TAG, "transaction in collection with id " + document.getId() + " => " + document.getData());
                                //for every transaction
                                Transactions t = createTransObj(document.getData());

//                                if(f==null)
//                                    Log.e(TAG, "onComplete: f is null " );
//                                else
//                                    Log.i(TAG, "onComplete: f is not null " );
//
//                                if(t==null)
//                                    Log.e(TAG, "onComplete: t is null " );
//                                else
//                                    Log.i(TAG, "onComplete: t is not null " );

                                f.addTransaction(t);


                            }
                        } else {
                            Log.i(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    Transactions createTransObj(Map<String,Object> TransMap)
    {
        String Transname = (String) TransMap.get("transaction name");
        double amount =( (Long) TransMap.get("amount") ).doubleValue();
        String category =(String) TransMap.get("category");
        Timestamp date_ts = (Timestamp) TransMap.get ("date");



        String date = date_ts.toString();

        Log.i(TAG, "createFinAcc: date read is " + date);

        Transactions t = new Transactions(Transname,amount,category,date_ts);

        return t;
    }




    ArrayList<Transactions> getTransactionByAccount(){return null;}

    ArrayList<Transactions> getTransactionByUser(){return null;}

}
