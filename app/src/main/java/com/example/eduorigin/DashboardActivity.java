package com.example.eduorigin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.eduorigin.adapters.BookLibraryAdapter;
import com.example.eduorigin.featurefragments.BookLibraryFragment;
import com.example.eduorigin.featurefragments.OnlineQuizFragment;
import com.example.eduorigin.learningresourcefragments.GeeksForGeeksFragment;
import com.example.eduorigin.learningresourcefragments.JavaTPointFragment;
import com.example.eduorigin.learningresourcefragments.TutorialsPointFragment;
import com.example.eduorigin.learningresourcefragments.W3SchoolFragment;
import com.example.eduorigin.registration.SignInActivity;
import com.google.android.material.navigation.NavigationView;

public class DashboardActivity extends AppCompatActivity {


    private NavigationView navigationView,navigationView2;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toogle;
    private TextView navHeaderEmail;
    private String output;
    private View headerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        SharedPreferences sp=getSharedPreferences("credential",MODE_PRIVATE);
        String output=sp.getString("email","");

        navigationView2 = (NavigationView) findViewById(R.id.navigationViewId );
        headerView = navigationView2.getHeaderView(0);
        navHeaderEmail = (TextView) headerView.findViewById(R.id.navHeaderEmailId);
        navHeaderEmail.setText(output);

        //userExistenceCheck();




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

                    case R.id.menu_logout:

                        onLogOut();
                        break;


                    case R.id.menu_bookLibrary:

                        store=new BookLibraryFragment();
                        getSupportActionBar().setTitle(item.getTitle());
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutForFragmentContainerId,store).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);

                        break;

                    case R.id.menu_quiz:

                        store=new OnlineQuizFragment();
                        getSupportActionBar().setTitle(item.getTitle());
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutForFragmentContainerId,store).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);

                        break;



                    case R.id.menu_w3School:
                        store=new W3SchoolFragment();
                        getSupportActionBar().setTitle(item.getTitle());
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutForFragmentContainerId,store).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);

                        break;


                    case R.id.menu_geeksForGeeks:
                        store=new GeeksForGeeksFragment();
                        getSupportActionBar().setTitle(item.getTitle());
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutForFragmentContainerId,store).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);

                        break;


                    case R.id.menu_tutorialPoint:
                        store=new TutorialsPointFragment();
                        getSupportActionBar().setTitle(item.getTitle());
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutForFragmentContainerId,store).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);

                        break;

                    case R.id.menu_javaTPoint:
                        store=new JavaTPointFragment();
                        getSupportActionBar().setTitle(item.getTitle());
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutForFragmentContainerId,store).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);

                        break;


                }

                return true;


            }
        });


    }

    private void onLogOut()
    {
        SharedPreferences sp=getSharedPreferences("credential",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.remove("email");
        editor.remove("password");
        editor.remove("result");
        //editor.remove("jishanc@gmail.com");
        //editor.remove("hero@gmail.com");
        editor.commit();
        editor.apply();

        startActivity(new Intent(DashboardActivity.this, SignInActivity.class));
        finish();
    }

    // private void  userExistenceCheck()
   //  {
       //  SharedPreferences sp=getSharedPreferences("credential",MODE_PRIVATE);
       //  if(sp.contains("email"))
       //  {
             //works like navigation drawer username appears when user is login
             //responseWarningDashboard.setText(sp.getString("email",""));
             //navHeaderEmail.setText("out");
//             output=getIntent().getStringExtra("emailKey");
//             navigationView2 = (NavigationView) findViewById(R.id.navigationViewId );
//             headerView = navigationView2.getHeaderView(0);
//             navHeaderEmail = (TextView) headerView.findViewById(R.id.navHeaderEmailId);
              //navHeaderEmail.setText(output);

        // }
         //else{
         //    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
        // }



 //   @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//         getMenuInflater().inflate(R.menu.search_menu,menu);
//        MenuItem menuItem=menu.findItem(R.id.search_from_menuId);
//        SearchView searchView= (SearchView) menuItem.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//               // adapter.getFilter().filter(newText);
//               // BookLibraryFragment bf=new BookLibraryFragment();
//               // bf.returnString(newText);
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//   }
}