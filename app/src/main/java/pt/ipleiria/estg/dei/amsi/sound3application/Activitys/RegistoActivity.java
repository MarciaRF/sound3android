package pt.ipleiria.estg.dei.amsi.sound3application.Activitys;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pt.ipleiria.estg.dei.amsi.sound3application.R;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.LoginSignUpListener;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.GestorSharedPref;

public class RegistoActivity extends AppCompatActivity implements LoginSignUpListener{
    LoginSignUpListener loginSignUpListener;

    private EditText mPasswordView;
    private EditText mRepeatPasswordView;
    private EditText mUsernameView;
    private EditText mEmailView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo);

        mPasswordView = findViewById(R.id.editText_password);
        mEmailView = findViewById(R.id.editText_email);
        mUsernameView = findViewById(R.id.editText_nome);
        mRepeatPasswordView = findViewById(R.id.editText_repetirPassword);
        Button mButtonSignUp = findViewById(R.id.button_SignUp);

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
        mUsernameView.setError(null);
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String username = mUsernameView.getText().toString();

        View focusView = null;

        if (TextUtils.isEmpty(username)){
            mUsernameView.setError(getString(R.string.erro_username_vazio));
            focusView = mUsernameView;
            focusView.requestFocus();
        }

        if (TextUtils.isEmpty(email)){
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            focusView.requestFocus();
        }

        // Check for a valid password, if the user entered one.
        if (password.trim().isEmpty()|| !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            focusView.requestFocus();
            return;
        }

        if(!(mPasswordView.getText().toString()).equals(mRepeatPasswordView.getText().toString())) {
            Toast.makeText(this, "Passwords não coincidem!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email) || !isValidEmail(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            focusView.requestFocus();
            return;
        }

        setLoginSignUpListener(this);
        requestRegisto(getApplicationContext(),ConteudoJsonParser.isConnectionInternet(getApplicationContext()),username,password,email);
    }

    public boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 5;
    }

    public void requestRegisto(final Context context, boolean isConnected,final String username, final String password, final String email){
            RequestQueue queue = Volley.newRequestQueue(context);
            String url ="http://10.200.2.63/sound3application/frontend/web/api/user/create";

            StringRequest getRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            if(loginSignUpListener != null) {
                                loginSignUpListener.onConnectSignUp(response);
                                try {
                                    //converting response to json object

                                    if (response.contains("user")) {
                                        JSONObject obj = new JSONObject(response);
                                        JSONObject userJson = obj.getJSONObject("user");
                                        //adicionar o user logado nas shared preferences
                                        ArrayList<String> tempList = new ArrayList<String>();
                                        tempList.add("" + userJson.getLong("id"));
                                        tempList.add(userJson.getString("username"));
                                        tempList.add(userJson.getString("email"));

                                        //storing the user in shared preferences
                                        GestorSharedPref.getInstance(getApplicationContext()).userLogin(tempList);

                                        //starting the profile activity
                                        finish();
                                        Intent intent = new Intent(getApplicationContext(), LoginSignUpActivity.class);
                                        startActivity(intent);
                                    } else{
                                        if(response.equals("1")){
                                            View focusView = null;
                                            mUsernameView.setError(getString(R.string.erro_username));
                                            focusView = mUsernameView;
                                            focusView.requestFocus();
                                        }
                                        if(response.equals("2")){
                                            View focusViewemail = null;
                                            mEmailView.setError(getString(R.string.erro_email_usado));
                                            focusViewemail = mEmailView;
                                            focusViewemail.requestFocus();
                                        }
                                        Toast.makeText(context, "Ocorreu um erro durante o Registo. Tente novamente.", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();

                                }
                            }else{
                                    Toast.makeText(context, "Preencha os dados corretamente!", Toast.LENGTH_SHORT).show();
                            }
                            // response
                            System.out.println("-------->resposta de signup: "+response);
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
                public Map<String, String> getParams() throws AuthFailureError {
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

    }

    public void setLoginSignUpListener(LoginSignUpListener loginSignUpListener){
        this.loginSignUpListener = loginSignUpListener;
    }
}
