package com.example.uvce;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.drawable.AnimatedStateListDrawableCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    TextView register_textView;
    EditText emaileditText, passwordeditText;
    Button loginButton;
    final String TAG = "Login Activity";
    ProgressBar loginProgressbar;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register_textView = findViewById(R.id.register_textView);
        emaileditText = findViewById(R.id.email_edittext);
        loginProgressbar = findViewById(R.id.login_progressbar);
        passwordeditText = findViewById(R.id.password_edittext);
        loginButton = findViewById(R.id.loginButton);
        loginProgressbar.setVisibility(View.GONE);
        mAuth = FirebaseAuth.getInstance();
        ImageView anim=findViewById(R.id.anim);
        animate(anim);
        Log.d("Login ACtivity", "Oncreate called");

        register_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        loginfirebase();
    }

    private void loginfirebase() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginProgressbar.setVisibility(View.VISIBLE);
                String email = emaileditText.getText().toString();
                String password = passwordeditText.getText().toString();
                if (email.isEmpty()) {
                    loginProgressbar.setVisibility(View.GONE);
                    emaileditText.setError("Please Enter Valid Email");
                }
                if (password.isEmpty()) {
                    loginProgressbar.setVisibility(View.GONE);
                    passwordeditText.setError("Please Enter Valid Email");
                } else {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        if (user.isEmailVerified()) {
                                            Toast.makeText(LoginActivity.this, "Sign In Successful", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(LoginActivity.this, Categories.class);
                                            startActivity(intent);
                                            loginProgressbar.setVisibility(View.GONE);

                                        } else {
                                            loginProgressbar.setVisibility(View.GONE);
                                            Toast.makeText(LoginActivity.this, "Login Unsuccessful, Please Verify Email", Toast.LENGTH_SHORT).show();
                                            mAuth.signOut();
                                        }


                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        loginProgressbar.setVisibility(View.VISIBLE);
                                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        loginProgressbar.setVisibility(View.GONE);
                                    }


                                    // ...
                                }

                            });
                }
            }
        });

    }


    public void animate(View view) {
        ImageView v = (ImageView) view;
        Drawable drawable = v.getDrawable();
        if (drawable instanceof AnimatedVectorDrawableCompat) {
            AnimatedVectorDrawableCompat avd = (AnimatedVectorDrawableCompat) drawable;
            avd.start();

        } else if (drawable instanceof AnimatedVectorDrawable) {
            AnimatedVectorDrawable avd = (AnimatedVectorDrawable) drawable;
            avd.start();
        }
    }
}
