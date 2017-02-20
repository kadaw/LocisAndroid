package com.example.user.locistest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.locistest.Api.AuthorizationTask;

public class LoginActivity extends AppCompatActivity {


    TextView logTextView;
    EditText loginEditText;
    Button enterButton;
    EditText passwordEditText;
    public static final int REQUEST_CODE = 1;
    public String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginEditText = (EditText) findViewById(R.id.login_email_et);
        enterButton = (Button) findViewById(R.id.enter_btn);
        passwordEditText = (EditText) findViewById(R.id.login_pass_et);
        logTextView = (TextView) findViewById(R.id.login_tv);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AuthorizationTask api = new AuthorizationTask(loginEditText.getText().toString(), passwordEditText.getText().toString());
                api.execute(getWindow().getContext());
                Intent intent = new Intent(LoginActivity.this, UserPage.class);
                intent.putExtra("token", response);
                startActivity(intent);
            }
        });
    }
    public void getToken(String token,int responseCode){
        switch (responseCode){
            case 200 :{int length = token.length() - 1;
                response = token.substring(1, length);
            };
            break;
            case 403: ;
                break;
            case 400: ;
                break;
            default:;
                break;
        }


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}

