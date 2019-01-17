package com.ceyizuygulamasi.kubra.ceyizprojesi.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ceyizuygulamasi.kubra.ceyizprojesi.Adapter.KategoriAdapter;
import com.ceyizuygulamasi.kubra.ceyizprojesi.Adapter.TabAdapter;
import com.ceyizuygulamasi.kubra.ceyizprojesi.Fragment.FirsatlarFragment;
import com.ceyizuygulamasi.kubra.ceyizprojesi.Fragment.HarcamalarFragment;
import com.ceyizuygulamasi.kubra.ceyizprojesi.Fragment.KategoriFragment;
import com.ceyizuygulamasi.kubra.ceyizprojesi.Fragment.PaylasimlarFragment;
import com.ceyizuygulamasi.kubra.ceyizprojesi.Fragment.UrunlerFragment;
import com.ceyizuygulamasi.kubra.ceyizprojesi.Model.Kategori;
import com.ceyizuygulamasi.kubra.ceyizprojesi.Model.Users;
import com.ceyizuygulamasi.kubra.ceyizprojesi.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthAnonymousUpgradeException;
import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText etEmail,etPwd;
    Button btnKayit,btnGiris;
    ArrayList<Users> users;
    private FirebaseDatabase mFireBaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private String mUsername;

    TabLayout tabLayout;
    TabAdapter adapter;


    public static final int RC_SIGN_IN=1;
    public void icerigiDegistir(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction ft=fragmentManager.beginTransaction();

        ft.replace(R.id.frameLayout,fragment);
        ft.commit();
    }
   /* public void TabUret(String[] tabs,List<Fragment> tabFragments){
        tabLayout.removeAllTabs();
        for(int i=0;i<tabs.length;i++){
            tabLayout.addTab(tabLayout.newTab().setText(tabs[i]));
        }
        adapter=new TabAdapter(getSupportFragmentManager(),tabFragments);
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       mUsername="";



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        users=new ArrayList<>();

        mFireBaseDatabase=FirebaseDatabase.getInstance();
        mFirebaseAuth=FirebaseAuth.getInstance();
        mDatabaseReference=mFireBaseDatabase.getReference().child("users");
        //tabLayout=findViewById(R.id.tabLayout);


        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null){
                    onSignedInInitialize(user.getDisplayName());

                }
                else{
                    onSignedOutCleanup();
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.GoogleBuilder().build(),
                                            new AuthUI.IdpConfig.EmailBuilder().build()
                                          ))
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };

        icerigiDegistir(new KategoriFragment());
        //tabLayout.removeAllTabs();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mAuthStateListener!=null){
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
        detachDatabaseReadListener();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            if(resultCode==RESULT_OK){
                Toast.makeText(this,"Signed in",Toast.LENGTH_LONG).show();
            }
            else if(resultCode==RESULT_CANCELED){

                Toast.makeText(this,"Signed out",Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_urunler) {
            icerigiDegistir(new UrunlerFragment());


            //TabUret(new String[]{"Alınan Ürünler","Alınmayan Ürünler"},Urunler);
        } else if (id == R.id.nav_firsatlar) {
            icerigiDegistir(new FirsatlarFragment());
           // TabUret(new String[]{"Tab 4","Tab5","Tab 6"});
        } else if (id == R.id.nav_paylasimListeleri) {
           icerigiDegistir(new PaylasimlarFragment());
            //TabUret(new String[]{"Tab 7","Tab8","Tab 9"});
        } else if (id == R.id.nav_harcamalar) {
            icerigiDegistir(new HarcamalarFragment());
            //TabUret(new String[]{"Tab 10","Tab12","Tab 13"});

        } else if (id == R.id.nav_kategoriler) {
           icerigiDegistir(new KategoriFragment());
            //TabUret(new String[]{"Tab 41","Tab25","Tab 43"});
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void onSignedInInitialize(String username){
      mUsername=username;
      attachDatabaseReadListener();
    }
    private void onSignedOutCleanup(){
         mUsername="";
        detachDatabaseReadListener();
    }
    private void detachDatabaseReadListener(){
        if(mChildEventListener!=null){
            mDatabaseReference.removeEventListener(mChildEventListener);
            mChildEventListener=null;
        }
    }
    private void attachDatabaseReadListener(){
        if(mChildEventListener==null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

            mDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }
}
