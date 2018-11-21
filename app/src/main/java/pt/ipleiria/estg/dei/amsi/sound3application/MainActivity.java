package pt.ipleiria.estg.dei.amsi.sound3application;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 *  Link para ver o Bottom Navigation
 *  https://www.youtube.com/watch?v=tPV8xA7m-iw
 */

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //define a home como default ao abrir a app
        if (savedInstanceState == null) {
            bottomNav.setSelectedItemId(R.id.nav_home);
        }

        /* adicionar logo antes do nome da app

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);*/
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem MenuItem) {
                    Fragment selectedFragment = null;
                    switch(MenuItem.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_search:
                            selectedFragment = new UtilizadorFragment();
                            break;
                        case R.id.nav_favoritos:
                            selectedFragment = new FavoritosFragment();
                            break;
                        case R.id.nav_carrinho:
                            selectedFragment = new CarrinhoFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                }
            };

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pesquisa, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
