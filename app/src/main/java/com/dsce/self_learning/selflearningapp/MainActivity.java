package com.dsce.self_learning.selflearningapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if(auth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(), Dash.class));
            finish();


    }


}

    public void crateNew(View view) {
        EditText mail = findViewById(R.id.umail);
        EditText pass = findViewById(R.id.upass);
        String email = mail.getText().toString();
        String passwd = pass.getText().toString();

        auth.createUserWithEmailAndPassword(email, passwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), Dash.class));
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Signup Failed",Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void logpage(View view) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
}
