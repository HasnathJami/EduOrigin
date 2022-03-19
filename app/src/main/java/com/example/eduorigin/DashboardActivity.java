package com.example.eduorigin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.eduorigin.fragments.BookLibraryFragment;
import com.example.eduorigin.fragments.OnlineQuizFragment;
import com.example.eduorigin.registration.SignInActivity;
import com.google.android.material.navigation.NavigationView;

public class DashboardActivity extends AppCompatActivity {


    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toogle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        userExistenceCheck();


//        signoutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences sp=getSharedPreferences("credential",MODE_PRIVATE);
//                SharedPreferences.Editor editor=sp.edit();
//                editor.remove("email");
//                editor.remove("password");
//                editor.commit();
//                editor.apply();
//
//                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
//                finish();
//            }
//        });


        toolbar=findViewById(R.id.toolBarId);
        setSupportActionBar(toolbar);

        navigationView=findViewById(R.id.navigationViewId);
        drawerLayout=findViewById(R.id.drawerId);

        toogle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutForFragmentContainerId,new BookLibraryFragment()).commit();
        getSupportActionBar().setTitle("Book Library");
        navigationView.setCheckedItem(R.id.menu_bookLibrary);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            Fragment store;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch(item.getItemId())
                {
                    case R.id.menu_bookLibrary:

                        store=new BookLibraryFragment();
                        getSupportActionBar().setTitle("Book Library");
                        break;

                    case R.id.menu_quiz:

                        store=new OnlineQuizFragment();
                        getSupportActionBar().setTitle("Online Quiz");
                        break;

                    case R.id.menu_logout:

                        SharedPreferences sp=getSharedPreferences("credential",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sp.edit();
                        editor.remove("email");
                        editor.remove("password");
                        editor.commit();
                        editor.apply();
                        startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                        finish();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutForFragmentContainerId,store).commit();
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;


            }
        });


    }

     private void  userExistenceCheck()
     {
         SharedPreferences sp=getSharedPreferences("credential",MODE_PRIVATE);
         if(sp.contains("email"))
         {
             //works like navigation drawer username appears when user is login
             //responseWarningDashboard.setText(sp.getString("email",""));
         }
         //else{
         //    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
        // }
     }
}