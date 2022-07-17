package com.example.pro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Mainmenu_Activity extends AppCompatActivity implements View.OnClickListener {

    PersonalSettings mysettings;
    double ProgressValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        mysettings = PersonalSettings.getInstance();

        ImageButton preferences_button = (ImageButton) findViewById(R.id.Prefrences_button);
        ImageButton transactions_button = (ImageButton) findViewById(R.id.transactionsButton);
        ImageButton accounts_button = (ImageButton) findViewById(R.id.accounts_button);
        ImageButton suggestions_button = (ImageButton) findViewById(R.id.price_suggestions);



        preferences_button.setOnClickListener(this);
        transactions_button.setOnClickListener(this);
        accounts_button.setOnClickListener(this);
        suggestions_button.setOnClickListener(this);

        ProgressBar progressBar= (ProgressBar) findViewById(R.id.progressBar);
        TextView percentagetxt = (TextView) findViewById(R.id.Progress_percentage_view);

        ProgressValue = mysettings.CalculateMoneyRemaining() / mysettings.Budget;
        if(Double.isNaN(ProgressValue)) {
            ProgressValue=99;
        }

        progressBar.setProgress((int)ProgressValue);

        Handler progressBarHandler = new Handler();
        progressBarHandler.post(new Runnable() {
            public void run() {
                progressBar.setProgress((int)ProgressValue);
            }
        });


        Log.i("TAG", "onCreate: Progress Value is "+ progressBar.getProgress());
        percentagetxt.setText(String.format("%s %%", progressBar.getProgress()));
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.Prefrences_button) {
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
        } else if(id == R.id.transactionsButton)
        {

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            // Create a new user with a first and last name
            Map<String, Object> user = new HashMap<>();
            user.put("first", "Ada");
            user.put("last", "Lovelace");
            user.put("born", 1815);

            // Add a new document with a generated ID
            db.collection("Accounts")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        private static final String TAG = "name";

                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        private static final String TAG = "name";

                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });

            DatabaseReference mDatabase;

            mDatabase = FirebaseDatabase.getInstance().getReference();

            mDatabase.child("Accounts").child("useraccounts").child("AccountName").setValue("name");

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        else if(id == R.id.accounts_button)
        {

//            FinancialAccount f = null;
//            f.updateFinancialAccount("Credit", 0, 0, 0, 0, uid);



            Intent i = new Intent(this, AccountsDisplayActivity.class);
            startActivity(i);
        }else if(id == R.id.price_suggestions)
        {
            Toast toast2 = Toast.makeText(getApplicationContext(), "Sorry, This feature is not Implemented\n Yet!!", Toast.LENGTH_SHORT);
            toast2.show();
        }
    }
}