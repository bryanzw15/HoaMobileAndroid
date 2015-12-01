package com.seng4100.hoamobile.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.seng4100.hoamobile.API.EndpointInterface;
import com.seng4100.hoamobile.API.ServiceGenerator;
import com.seng4100.hoamobile.LoginActivity;
import com.seng4100.hoamobile.Model.User;
import com.seng4100.hoamobile.R;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class RegisterUserViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        final EditText firstName = (EditText) findViewById(R.id.ru_first_name);
        final EditText lastName = (EditText) findViewById(R.id.ru_last_name);
        final EditText email = (EditText) findViewById(R.id.ru_email);
        final EditText pass = (EditText) findViewById(R.id.ru_pass);
        final EditText passConfirm = (EditText) findViewById(R.id.ru_pass_confirm);
        final Button submitButton = (Button) findViewById(R.id.ru_b_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitButton.setEnabled(false);

                User user = new User();

                user.setName(firstName.getText().toString() + " " + lastName.getText().toString());
                user.setEmail(email.getText().toString());
                user.setPassword(pass.getText().toString());

                createUser(user);

            }
        });
    }

    private void createUser(User user){
        EndpointInterface endpoint = ServiceGenerator.createService(EndpointInterface.class);
        Call<User> call = endpoint.createUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Intent i = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
