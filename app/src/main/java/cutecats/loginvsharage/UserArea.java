package cutecats.loginvsharage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by longpoll on 13.10.2016.
 */

public class UserArea extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail, etPassword;
    Button bLogin;
    TextView registerLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

    }

    @Override
    public void onClick(View v) {

    }
}
