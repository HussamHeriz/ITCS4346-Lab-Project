package hussamheriz.aug.todolistproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hussamheriz.aug.todolistproject.Models.User;

public class SignUpActivity extends AppCompatActivity {

    EditText name, password, email;
    Button register;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        name        = findViewById(R.id.name);
        password    = findViewById(R.id.password);
        email       = findViewById(R.id.email);
        register    = findViewById(R.id.register);
        login       = findViewById(R.id.login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameStr = name.getText().toString();
                String passwordStr = password.getText().toString();
                String emailStr = email.getText().toString();

                User user = new User(nameStr,emailStr, passwordStr);
                Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                Toast.makeText(SignUpActivity.this, user.toString(), Toast.LENGTH_LONG).show();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}