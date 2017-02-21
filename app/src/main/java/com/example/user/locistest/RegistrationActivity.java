package com.example.user.locistest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.locistest.Api.AuthorizationTask;
import com.example.user.locistest.Api.RegistrationTask;

public class RegistrationActivity extends AppCompatActivity {

    String response;
    TextView registrationTextView;
    EditText emailEditText;
    EditText nameEditText;
    EditText passEditText;
    Button registrationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        registrationTextView = (TextView) findViewById(R.id.tv_regist);
        emailEditText = (EditText) findViewById(R.id.editText);
        nameEditText = (EditText) findViewById(R.id.editText2);
        passEditText = (EditText) findViewById(R.id.editText4);
        registrationButton = (Button) findViewById(R.id.button);
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RegistrationTask api = new RegistrationTask(nameEditText.getText().toString(), emailEditText.getText().toString(), passEditText.getText().toString());
                api.execute(getWindow().getContext());
            }
        });
    }

    public void getToken(String token, int responseCode) {
        switch (responseCode) {
            case 200: {
                int length = token.length() - 1;
                response = token.substring(1, length);
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                intent.putExtra("token", response);
                startActivity(intent);

            }
            ;
            break;
            case 403:
                ;
                break;
            case 400:
                ;
                break;
            default:
                ;
                break;
        }
    }
}
