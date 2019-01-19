package pt.ipleiria.estg.dei.amsi.sound3application.Listeners;

import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public interface LoginSignUpListener {
    void onConnectLogin(String response);
    void onConnectSignUp(String response);
}
