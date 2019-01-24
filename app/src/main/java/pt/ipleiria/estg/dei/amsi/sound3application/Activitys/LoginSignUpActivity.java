package pt.ipleiria.estg.dei.amsi.sound3application.Activitys;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
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
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.ConteudoJsonParser;
import pt.ipleiria.estg.dei.amsi.sound3application.Utils.GestorSharedPref;
import pt.ipleiria.estg.dei.amsi.sound3application.Listeners.LoginSignUpListener;


/**
 * A login screen that offers login via email/password.
 */
public class LoginSignUpActivity extends AppCompatActivity implements LoginSignUpListener {
    private LoginSignUpListener loginSignUpListener = null;
    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        getSupportActionBar().hide();
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
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

        // Show a progress spinner, and kick off a background task to
        // perform the user login attempt.
        System.out.println("-------->Executar Login");
        showProgress(true);
        setLoginSignUpListener(this);
        verificarLogin(getApplicationContext(),ConteudoJsonParser.isConnectionInternet(getApplicationContext()),email,password);

        return;



    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public void ligacaoRegisto(View view) {
        Intent intent = new Intent (getApplicationContext(), RegistoActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConnectLogin(String response) {
        if(response.contains("user")){
            System.out.println("-------->Login válido, siga main");
            JSONObject obj = null;
            try {
                obj = new JSONObject(response);
                JSONObject userJson = obj.getJSONObject("user");
                //adicionar o user logado nas shared preferences
                ArrayList<String> tempList = new ArrayList<String>();
                tempList.add(""+userJson.getLong("id"));
                tempList.add(userJson.getString("username"));
                tempList.add(userJson.getString("email"));

                GestorSharedPref.getInstance(getApplicationContext()).userLogin(tempList);

                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else{
            if(response.equals("erro")){
                Toast.makeText(this, "Verifique a sua ligação!", Toast.LENGTH_SHORT).show();
                return;
            }
            View focusView = null;
            showProgress(false);
            mPasswordView.setError(getString(R.string.pass_incorreta));
            focusView = mPasswordView;
            focusView.requestFocus();
            System.out.println("-------->connectLogin FALSE");
        }

    }

    @Override
    public void onConnectSignUp(String response) {

    }

    public void setLoginSignUpListener(LoginSignUpListener loginSignUpListener){
        this.loginSignUpListener = loginSignUpListener;
    }

    public void verificarLogin(final Context context, boolean isConnected, final String username, final String password){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="http://10.200.9.224/sound3application/frontend/web/api/user/verificarlogin";

        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        if(loginSignUpListener !=null) {
                            loginSignUpListener.onConnectLogin(response);
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
                        System.out.println("-------->erro de resposta de login: "+ error.toString());
                        Log.d("ERROR","error => "+error.toString());
                        loginSignUpListener.onConnectLogin("erro");
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);

                return params;
            }
        };

        queue.add(getRequest);
    }

    public static class RegistoActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registo);
        }

        public void ligacaoLogin(View view) {
            Intent intent = new Intent (getApplicationContext(), LoginSignUpActivity.class);
            startActivity(intent);
        }
    }
}

