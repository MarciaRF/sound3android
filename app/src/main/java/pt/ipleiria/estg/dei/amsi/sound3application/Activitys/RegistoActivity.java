package pt.ipleiria.estg.dei.amsi.sound3application.Activitys;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.LoginSignUpListener;

public class RegistoActivity extends AppCompatActivity implements LoginSignUpListener{
    LoginSignUpListener loginSignUpListener;

    private EditText mPasswordView;
    private EditText mRepeatPasswordView;
    private EditText mUsernameView;
    private EditText mEmailView;
    private Button mButtonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo);
        mPasswordView = findViewById(R.id.editText_password);
        mEmailView = findViewById(R.id.editText_email);
        mUsernameView = findViewById(R.id.editText_nome);
        mRepeatPasswordView = findViewById(R.id.editText_repetirPassword);
        mButtonSignUp = findViewById(R.id.button_SignUp);

        getSupportActionBar().hide();

        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegisto();
            }
        });
    }

    private void attemptRegisto() {
        boolean res;
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (password.trim().isEmpty()|| !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            focusView.requestFocus();
            return;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            focusView.requestFocus();
            return;
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    public void verificarRegisto(View view) {
        System.out.println("-------->verificar");
        if(!(mPasswordView.getText().toString()).equals(mRepeatPasswordView.getText().toString())){
            Toast.makeText(this, "Passwords não coincidem!", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent (getApplicationContext(), LoginSignUpActivity.class);
            startActivity(intent);
        }
    }

    public void requestRegisto(final Context context, boolean isConnected,final String username, final String password, final String email){
            RequestQueue queue = Volley.newRequestQueue(context);
            String url ="http://10.200.9.160/sound3application/frontend/web/api/user/verificarlogin";

            StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            if(loginSignUpListener !=null) {
                                loginSignUpListener.onConnectSignUp(response);
                                System.out.println("-------->LISTENER != NULL");
                            }

                            // response
                            System.out.println("-------->resposta de login: "+response);
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO Auto-generated method stub
                            System.out.println("-------->erro de resposta de registo: "+ error.toString());
                            Log.d("ERROR","error => "+error.toString());
                        }
                    }
            ) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String>  params = new HashMap<String, String>();
                    params.put("username", username);
                    params.put("password", password);
                    params.put("email", email);

                    return params;
                }
            };

            queue.add(getRequest);
        }

    @Override
    public void onConnectLogin(String response) {

    }

    @Override
    public void onConnectSignUp(String response) {
        if(response.equals("true")){

        }else if(response.equals("false")){

        }
    }
}
