package com.danielsela.myapplication.Activities;

import static androidx.core.app.FrameMetricsAggregator.DELAY_DURATION;
import static com.danielsela.myapplication.Utiles.Const.USER_EMAIL;
import static com.danielsela.myapplication.Utiles.Const.USER_NAME;
import static com.danielsela.myapplication.Utiles.Const.USER_NODE;
import static com.danielsela.myapplication.Utiles.Const.USER_PICTURE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;

import com.danielsela.myapplication.Adapters.PersonAdapter;
import com.danielsela.myapplication.Interfaces.PersonCallback;
import com.danielsela.myapplication.Models.Chat;
import com.danielsela.myapplication.Models.Dog;
import com.danielsela.myapplication.Models.Person;
import com.danielsela.myapplication.Models.User;
import com.danielsela.myapplication.R;
import com.danielsela.myapplication.Utiles.DataManager;
import com.danielsela.myapplication.Utiles.ImageLoader;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.danielsela.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private FirebaseUser fb_user = null;
    private MaterialTextView mail, username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        username = navigationView.getHeaderView(0).findViewById(R.id.menu_username);
        mail = navigationView.getHeaderView(0).findViewById(R.id.menu_email);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_profile, R.id.nav_dogsitter, R.id.nav_playdate, R.id.nav_chat, R.id.nav_one_chat)
                .setOpenableLayout(drawer)
                .build();
        FirebaseAuth.getInstance();
        this.fb_user = FirebaseAuth.getInstance().getCurrentUser();

        ImageLoader.initImageLoader(this);

        NavController navController = Navigation.findNavController(this, R.id.nav_content);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_profile) {
                navController.navigate(R.id.nav_profile);
            } else if (itemId == R.id.nav_dogsitter) {
                navController.navigate(R.id.nav_dogsitter);
            } else if (itemId == R.id.nav_playdate) {
                navController.navigate(R.id.nav_playdate);
            } else if (itemId == R.id.nav_chat) {
                navController.navigate(R.id.nav_chat);
            }else if (itemId == R.id.nav_logout) {
                navigateToLoginFragment(true);
            }
            drawer.closeDrawer(GravityCompat.START);
            return true;
        });


        if (this.fb_user == null) {
            navController.navigate(R.id.nav_login);
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        }


        // setDatabase();
        setUserDatabase();
        setUserValues();
    }
    private void navigateToLoginFragment(boolean signOut) {
        if (signOut) {
            FirebaseAuth.getInstance().signOut();
            fb_user = null;
        }
        NavController navController = Navigation.findNavController(this, R.id.nav_content);
        navController.navigate(R.id.nav_login);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
    }


    private void setDatabase() {
        ArrayList<Person> persons = DataManager.getPerson();
        DataManager.uploadPersonsToFirebase(persons);
        ArrayList<Dog> dogs = DataManager.getDogs();
        DataManager.uploadDogsToFirebase(dogs);
        ArrayList<Chat> chatList = DataManager.getChats();
        DataManager.uploadChatsToFirebase(chatList);
    }

    private void setUserDatabase() {
        User user = DataManager.getUser();
        DataManager.uploadUserToFirebase(user);
    }

    private void setUserValues() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(USER_NODE).child(USER_NODE).child(USER_NAME);
        databaseReference.get().addOnSuccessListener(dataSnapshot -> username.setText(dataSnapshot.getValue(String.class)));
        databaseReference = FirebaseDatabase.getInstance().getReference(USER_NODE).child(USER_NODE).child(USER_EMAIL);
        databaseReference.get().addOnSuccessListener(dataSnapshot -> mail.setText(dataSnapshot.getValue(String.class)));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_content);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}