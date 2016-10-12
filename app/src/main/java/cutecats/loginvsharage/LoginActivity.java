package cutecats.loginvsharage;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail, etPassword;
    Button bLogin;
    TextView registerLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button   bLogin       = (Button)   findViewById(R.id.bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegister);


        registerLink.setOnClickListener(this);
        bLogin.setOnClickListener(this);
    }
    public void onClick(View v) {
        // по id определеяем кнопку, вызвавшую этот обработчик
        switch (v.getId()) {
            case R.id.tvRegister:
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
                LoginActivity.this.finish();
                break;
            case R.id.bLogin:

                final EditText etEmail      = (EditText) findViewById(R.id.etEmail);
                final EditText etPassword   = (EditText) findViewById(R.id.etPassword);

                final String email           = etEmail.getText().toString();
                final String password        = etPassword.getText().toString();
                Log.d("LOG", "ВХОЖДЕНИЕ");
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("LOG", response.toString());
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            Integer userId = jsonResponse.getJSONObject("response").getInt("userId");
                            Toast.makeText(getApplicationContext(), "Ваш userId: " + userId, Toast.LENGTH_SHORT).show();

                            Intent registerIntent = new Intent(LoginActivity.this, UserAreaActivity.class);
                            LoginActivity.this.startActivity(registerIntent);
                            LoginActivity.this.finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            try {
                                String responseString = new JSONObject(response).getString("response");
                                Toast.makeText(LoginActivity.this, responseString, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                };

                Map<String, String> params;
                params = new HashMap<>();
                params.put("method", "user.login");

                params.put("email",             email);
                params.put("password",          password);

                RegisterRequest registerRequest = new RegisterRequest(params, responseListener);
                RequestQueue queue           = Volley.newRequestQueue(LoginActivity.this);
                queue.add(registerRequest);

                break;
        }
    }
}