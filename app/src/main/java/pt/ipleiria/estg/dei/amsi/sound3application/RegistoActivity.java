package pt.ipleiria.estg.dei.amsi.sound3application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pt.ipleiria.estg.dei.amsi.sound3application.R;

public class RegistoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo);
    }

    public void ligacaoLogin(View view) {
        Intent intent = new Intent (getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}
