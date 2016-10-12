package cutecats.loginvsharage;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://labor.vernicat.ru/api.php";
    private Map<String, String> params;

    public RegisterRequest(Map<String, String> iParams, Response.Listener<String> listener) {

        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = iParams;

    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }
}
