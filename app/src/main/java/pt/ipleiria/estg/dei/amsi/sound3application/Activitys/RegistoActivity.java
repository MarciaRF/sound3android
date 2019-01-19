package pt.ipleiria.estg.dei.amsi.sound3application.Activitys;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo);
        mPasswordView = findViewById(R.id.editText_password);
        mEmailView = findViewById(R.id.editText_email);
        mUsernameView = findViewById(R.id.editText_nome);
        mRepeatPasswordView = findViewById(R.id.editText_repetirPassword);

        getSupportActionBar().hide();
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
