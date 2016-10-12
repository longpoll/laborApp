package cutecats.loginvsharage;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static cutecats.loginvsharage.R.id.bRegister;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etEmail           = (EditText) findViewById(R.id.etEmail);
        final EditText etPassword        = (EditText) findViewById(R.id.etPassword);
        final EditText etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        final EditText etSecondName      = (EditText) findViewById(R.id.etSecondName);
        final EditText etFirstName       = (EditText) findViewById(R.id.etFirstName);
        final EditText etSurName         = (EditText) findViewById(R.id.etSurName);
        final Button   bRegister         = (Button)   findViewById(R.id.bRegister);


        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email           = etEmail.getText().toString();
                final String password        = etPassword.getText().toString();
                final String confirmpassword = etConfirmPassword.getText().toString();
                final String secondname      = etSecondName.getText().toString();
                final String firstname       = etFirstName.getText().toString();
                final String surname         = etSurName.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("LOG", response.toString());
                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                            Integer userId = jsonResponse.getJSONObject("response").getInt("userId");
                            String successRegistartion = jsonResponse.getJSONObject("response").getString("successRegistration");
                            Toast.makeText(getApplicationContext(), "Регистрация успешна: " + successRegistartion + "\n Ваш userId: " + userId, Toast.LENGTH_SHORT).show();

                            Log.d("LOG", userId.toString());
                           if (userId > 0) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.finish();
                                RegisterActivity.this.startActivity(intent);
                           } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Ошибка регистрации!")
                                        .setNegativeButton("Попробуйте заново!", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            try {
                                String responseString = new JSONObject(response).getString("response");
                                Toast.makeText(RegisterActivity.this, responseString, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(email, password, confirmpassword, secondname, firstname, surname, responseListener);
                RequestQueue    queue           = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        // super.onBackPressed();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        RegisterActivity.this.finish();
        RegisterActivity.this.startActivity(intent);
    }
}