package kz.baqshamninonimi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity {

    private String phoneStored = "", passwordStored = "", usertype="", full_name="";
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToogle;
    private MenuItem mMenuItem, mMenuItem2;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();


        mDrawerLayout = findViewById(R.id.drawer);
        mToogle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToogle);
        mToogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView = findViewById(R.id.nav);
        Menu mn = navigationView.getMenu();
        mMenuItem= mn.findItem(R.id.purchase);
        mMenuItem2= mn.findItem(R.id.addprod);
        SharedPreferences pref = getSharedPreferences("loginData", MODE_PRIVATE);
        phoneStored = pref.getString("phone", "+7(747)5652503");
        passwordStored = pref.getString("password", null);
        usertype = pref.getString("usertype", "клиент");
        full_name = pref.getString("full_name", "User");

        if (!phoneStored.isEmpty()  && !full_name.isEmpty() ) {
//            TextView name = findViewById(R.id.fullName);
//            name.setText(full_name);
//            TextView phoneNumber = findViewById(R.id.phoneNumber);
//            phoneNumber.setText(phoneStored);

            if  (usertype.equals("фермер")) {
                mMenuItem.setTitle("Продажи");

            } else mMenuItem2.setTitle("Корзина");

        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.catalog:
                        startActivity(new Intent(HomeActivity.this, CatalogActivity.class));
                        return false;
                    case R.id.account:
                        startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                        return false;
                    case R.id.purchase:
                        if(mMenuItem.getTitle().toString().contentEquals("Продажи")){
                            startActivity(new Intent(HomeActivity.this, CatalogActivity.class));
                            return false;
                        }
                        else {
                            startActivity(new Intent(HomeActivity.this, AddItem.class));
                            return false;
                        }
                    case R.id.addprod:
                        if(mMenuItem2.getTitle().toString().contentEquals("Корзина")){
                            startActivity(new Intent(HomeActivity.this, CatalogActivity.class));
                            return false;
                        }
                        else {
                            startActivity(new Intent(HomeActivity.this, AddItem.class));
                            return false;
                        }
                    case R.id.aboutus:
                        startActivity(new Intent(HomeActivity.this, AboutUs.class));
                        return false;
                    case R.id.logout:
                        SharedPreferences pref = getSharedPreferences("loginData", MODE_PRIVATE);
                        pref.edit().remove("phone").commit();
                        pref.edit().remove("password").commit();
                        pref.edit().remove("usertype").commit();
                        pref.edit().remove("full_name").commit();
                        startActivity(new Intent(HomeActivity.this, MainActivity.class));
                        return false;

                }
                return false;
            }
        });


//        Element versionElement = new Element();
//        versionElement.setTitle("Version 6.2");
//
//        Element adsElement = new Element();
//        adsElement.setTitle("Advertise with us");
//6
//        View aboutPage = new AboutPage(this)
//                .isRTL(false)
//                .setImage(R.drawable.bg_veg)
//                .addItem(versionElement)
//                .addItem(adsElement)
//                .addGroup("Connect with us")
//                .addEmail("elmehdi.sakout@gmail.com")
//                .addWebsite("http://medyo.github.io/")
//                .addFacebook("the.medy")
//                .addTwitter("medyo80")
//                .addYoutube("UCdPQtdWIsg7_pi4mrRu46vA")
//                .addPlayStore("com.ideashower.readitlater.pro")
//                .addInstagram("medyo80")
//                .addGitHub("medyo")
//                .create();
//
//        setContentView(aboutPage);

    }
}
