package com.example.uvce;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class RegisterActivity extends AppCompatActivity {
    EditText registeremaileditText, registerpasswordeditText, fullnameeditText;
    Button registerbutton;
    ProgressBar progressBarRegister;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference users = db.collection("users");
    CollectionReference user_account_settings = db.collection("user_accounts_settings");
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registeremaileditText = findViewById(R.id.registeremail_editText);
        registerpasswordeditText = findViewById(R.id.registerpassword_editText);
        registerbutton = findViewById(R.id.register_button);
        fullnameeditText = findViewById(R.id.fullname_editText);
        mAuth = FirebaseAuth.getInstance();
        progressBarRegister = findViewById(R.id.progressbar_register);
        progressBarRegister.setVisibility(View.GONE);
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarRegister.setVisibility(View.VISIBLE);
                final String TAG = "Register Activity";
                final String name = fullnameeditText.getText().toString();
                final String email = registeremaileditText.getText().toString();
                final String password = registerpasswordeditText.getText().toString();
                if (name.isEmpty()) {
                    progressBarRegister.setVisibility(View.GONE);
                    fullnameeditText.setError("Please Enter a Username");
                }
                if (email.isEmpty() || !email.contains("@")) {
                    progressBarRegister.setVisibility(View.GONE);

                    registeremaileditText.setError("Please enter a valid email");
                }
                if (password.isEmpty() || password.length() < 8) {
                    progressBarRegister.setVisibility(View.GONE);

                    registerpasswordeditText.setError("Please Enter a password at least 8 characters long");
                } else {

                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        String userid = mAuth.getCurrentUser().getUid();
                                        Log.d(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();

                                        //adding the userAccountSetting objects to the collection
                                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                        firebaseUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(RegisterActivity.this, "Verification link sent", Toast.LENGTH_SHORT).show();
                                                mAuth.signOut();
                                                finish();

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.d(TAG, e.toString());


                                            }
                                        });

                                    } else {
                                        progressBarRegister.setVisibility(View.GONE);
                                        // If sign in fails, display a message to the user.
                                        Log.d(TAG, task.getException().toString());
                                        Toast.makeText(RegisterActivity.this, "Registration failed,Email Already in Use",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    firebaseUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "Email verification sent");
                            mAuth.signOut();
                            finish();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, e.toString());


                        }
                    });

                }
            }

        });
    }
}






