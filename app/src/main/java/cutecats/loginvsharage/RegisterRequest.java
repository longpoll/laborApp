package cutecats.loginvsharage;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://labor.vernicat.ru/api.php";
    private Map<String, String> params;

    public RegisterRequest(String email, String password, String confirmpassword,
      String secondname, String firstname,String surname, Response.Listener<String> listener) {

        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("method", "user.registration");

        params.put("email",             email);
        params.put("password",          password);
        params.put("passwordConfirm",   password); /* TODO: ЗАМЕНИТЬ НА CONFIRM PASSWORD */
        params.put("secondName",        secondname);
        params.put("firstName",         firstname);
        params.put("surName",           surname);
        params.put("email",             email);
    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }
}
