package com.example.firstwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private LoginAttempt correctLogin = new LoginAttempt("Alex", "Chang");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Retrieve the logout message if it exists
        String message = getIntent().getStringExtra("logoutMessage");

        if (message != null) {
            // Display a toast if the message is not null
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

            // Find the Remember Me checkbox by its ID

        }

        CheckBox rememberMeCheckbox = findViewById(R.id.rememberme_checkbox);

        // Method to toggle visibility of password entered
        ImageView hiddenIcon = findViewById(R.id.hidden_1);
        final EditText passwordEditText = findViewById(R.id.password_textbox);

        hiddenIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int inputType = passwordEditText.getInputType();
                if (inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    // Switch to password visibility off (textPassword)
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    // Switch to password visibility on (textVisiblePassword)
                    passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
            }
        });


        View signinBtn = (View) findViewById(R.id.signin_box);


        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ((EditText) findViewById(R.id.username_textbox)).getText().toString();
                String password = ((EditText) findViewById(R.id.password_textbox)).getText().toString();
                LoginAttempt attempt = new LoginAttempt(username, password);

                boolean loginSucceeded = login(attempt);
                if (loginSucceeded) {
                    AppState.getInstance().setCurrentUser(new CurrentUser(attempt.getUsername()));
                    Intent homeIntent = new Intent(MainActivity.this, NavBarActivity.class);
                    startActivity(homeIntent);
                } else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private boolean login(LoginAttempt attempt) {
        boolean a = attempt.getUsername().compareTo(correctLogin.getUsername()) == 0 && attempt.getPassword().compareTo(correctLogin.getPassword()) == 0;
//        Toast.makeText(MainActivity.this,Boolean.toString(a),Toast.LENGTH_SHORT).show();
        return a;
    }
}