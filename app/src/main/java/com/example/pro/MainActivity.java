package com.example.pro;//package tabian.com.listadapter;

//import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started.");
        ListView mListView = (ListView) findViewById(R.id.listView1);

        //Create the Transactions objects
        /*
        Transactions john = new Transactions("John","12-20-1998","Male",
                getResources().getIdentifier("@drawable/cartman_cop", null,this.getPackageName()));
                */

//        Transactions john = new Transactions("mobile phone","12-20-1998","10000",
//                "category", "drawable://" + R.drawable.plus);
//        Transactions steve = new Transactions("quick","08-03-1987","50",
//                "category", "drawable://" + R.drawable.minus_button);
//        Transactions stacy = new Transactions("mcdonalds","11-15-2000","200",
//                "category", "drawable://" + R.drawable.plus);
//        Transactions ashley = new Transactions("exmp5","07-02-1999","400",
//                "category", "drawable://" + R.drawable.minus_button);
//        Transactions matt = new Transactions("exmp4","03-29-2001","100",
//                "category", "drawable://" + R.drawable.minus_button);
//        Transactions matt2 = new Transactions("exmp3","03-29-2001","1000",
//                "category", "drawable://" + R.drawable.minus_button);
//        Transactions matt3 = new Transactions("exmp2","03-29-2001","10",
//                "category", "drawable://" + R.drawable.minus_button);
//        Transactions matt4 = new Transactions("exmp1","03-29-2001","60",
//                "category", "drawable://" + R.drawable.minus_button);
//
//
//
//        //Add the Transactions objects to an ArrayList

        PersonalSettings mysett = PersonalSettings.getInstance();
        Log.i(TAG, "onCreate: user name " + mysett.UserName);
        mysett.getAllTrans();
        ArrayList<Transactions> transactionsList = mysett.getAllTrans();
//        transactionsList.add(john);
//        transactionsList.add(steve);
//        transactionsList.add(stacy);
//        transactionsList.add(ashley);
//        transactionsList.add(matt);
//        transactionsList.add(matt2);
//        transactionsList.add(matt3);
//        transactionsList.add(matt4);
//        transactionsList.add(john);
//        transactionsList.add(steve);
//        transactionsList.add(stacy);
//        transactionsList.add(ashley);
//        transactionsList.add(matt);
//        transactionsList.add(matt2);
//        transactionsList.add(matt3);
//        transactionsList.add(matt4);
//        transactionsList.add(john);
//        transactionsList.add(steve);
//        transactionsList.add(stacy);
//        transactionsList.add(ashley);
//        transactionsList.add(matt);
//        transactionsList.add(matt2);
//        transactionsList.add(matt3);
//        transactionsList.add(matt4);
//        transactionsList.add(john);
//        transactionsList.add(steve);
//        transactionsList.add(stacy);
//        transactionsList.add(ashley);
//        transactionsList.add(matt);
//        transactionsList.add(matt2);
//        transactionsList.add(matt3);
//        transactionsList.add(matt4);
//        transactionsList.add(john);
//        transactionsList.add(steve);
//        transactionsList.add(stacy);
//        transactionsList.add(ashley);
//        transactionsList.add(matt);
//        transactionsList.add(matt2);
//        transactionsList.add(matt3);
//        transactionsList.add(matt4);
//        transactionsList.add(john);
//        transactionsList.add(steve);
//        transactionsList.add(stacy);
//        transactionsList.add(ashley);
//        transactionsList.add(matt);
//        transactionsList.add(matt2);
//        transactionsList.add(matt3);
//        transactionsList.add(matt4);
//        transactionsList.add(john);
//        transactionsList.add(steve);
//        transactionsList.add(stacy);
//        transactionsList.add(ashley);
//        transactionsList.add(matt);
//        transactionsList.add(matt2);
//        transactionsList.add(matt3);
//        transactionsList.add(matt4);
//        transactionsList.add(john);
//        transactionsList.add(steve);
//        transactionsList.add(stacy);
//        transactionsList.add(ashley);
//        transactionsList.add(matt);
//        transactionsList.add(matt2);
//        transactionsList.add(matt3);
//        transactionsList.add(matt4);
//        transactionsList.add(john);
//        transactionsList.add(steve);
//        transactionsList.add(stacy);
//        transactionsList.add(ashley);
//        transactionsList.add(matt);
//        transactionsList.add(matt2);
//        transactionsList.add(matt3);
//        transactionsList.add(matt4);
//        transactionsList.add(john);
//        transactionsList.add(steve);
//        transactionsList.add(stacy);
//        transactionsList.add(ashley);
//        transactionsList.add(matt);
//        transactionsList.add(matt2);
//        transactionsList.add(matt3);
//        transactionsList.add(matt4);
//        transactionsList.add(john);
//        transactionsList.add(steve);
//        transactionsList.add(stacy);
//        transactionsList.add(ashley);
//        transactionsList.add(matt);
//        transactionsList.add(matt2);
//        transactionsList.add(matt3);
//        transactionsList.add(matt4);
//        transactionsList.add(john);
//        transactionsList.add(steve);
//        transactionsList.add(stacy);
//        transactionsList.add(ashley);
//        transactionsList.add(matt);
//        transactionsList.add(matt2);
//        transactionsList.add(matt3);
//        transactionsList.add(matt4);

        //DatabaseReference mDatabase;

//        mDatabase = FirebaseDatabase.getInstance().getReference();

        //mDatabase.child("Accounts").child("useraccounts").child("AccountName").setValue("name");



        TransactionsListAdapter adapter = new TransactionsListAdapter(this, R.layout.activity_list, transactionsList);
        mListView.setAdapter(adapter);
    }
}