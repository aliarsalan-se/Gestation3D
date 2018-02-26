package com.example.humayunt.templateui;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.cuteBaby.Model1.UnityPlayerActivity;
import com.example.humayunt.templateui.DataModel.UserDetail;
import com.example.humayunt.templateui.HelpGuide.About;
import com.example.humayunt.templateui.HelpGuide.Faq;
import com.example.humayunt.templateui.HelpGuide.Features;
import com.example.humayunt.templateui.LocateHospital.hospital_MapsActivity;
import com.example.humayunt.templateui.MainPanel.LocateDoctorMap;
import com.example.humayunt.templateui.MainPanel.diets;
import com.example.humayunt.templateui.MainPanel.excercises;
import com.example.humayunt.templateui.SidePanel.change_password;
import com.example.humayunt.templateui.SidePanel.edit_profile;
import com.example.humayunt.templateui.Signinup.SigninActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;import android.app.Dialog;


public class UserProfile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    public TextView email, name;

    private FirebaseDatabase firebaseDatabase;
     private String UserId;
    private DatabaseReference databaseUserRef ;
    private String TAG;
    private CardView guidecard, modelcard,vrviewcard,arguidecard,dietcard,excercisecard,locatehospitalcard,quizcard;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String UserIDOld = getIntent().getStringExtra("UserID");
        myDialog = new Dialog(this);

        firebaseDatabase = FirebaseDatabase.getInstance();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        UserId = user.getUid();
        //Log.d(TAG,UserId );

        if(firebaseAuth.getCurrentUser()==null) {
            finish();
            startActivity(new Intent(this, SigninActivity.class));

        }
         databaseUserRef = firebaseDatabase.getReference();
        //mNavigationView=(NavigationView)findViewById(R.id.nav_view);
        guidecard = (CardView) findViewById(R.id.dguide);
        modelcard = (CardView) findViewById(R.id.dmodels);
     //   vrviewcard = (CardView) findViewById(R.id.vrview);
        arguidecard =(CardView) findViewById(R.id.arguide);
        dietcard = (CardView) findViewById(R.id.diets);
        excercisecard = (CardView) findViewById(R.id.excercise);
        locatehospitalcard = (CardView) findViewById(R.id.locatehospital);
       // quizcard= (CardView) findViewById(R.id.quiz);

        guidecard.setOnClickListener(this);
        modelcard.setOnClickListener(this);
        //  vrviewcard.setOnClickListener(this);
         arguidecard.setOnClickListener(this);
         dietcard.setOnClickListener(this);
         excercisecard.setOnClickListener(this);
         locatehospitalcard.setOnClickListener(this);
        // quizcard.setOnClickListener(this);
        View header = ((NavigationView)findViewById(R.id.nav_view)).getHeaderView(0);
        //mNavigationView.setNavigationItemSelectedListener(this);
        //View myview=mNavigationView.getHeaderView(0);
         email = ((TextView) header.findViewById(R.id.textView_email));
         name = ((TextView) header.findViewById(R.id.User_name));
        Toast.makeText(this,UserId, Toast.LENGTH_LONG).show();
       //;
        //email.setText(user.getEmail());
      //  useremail = (TextView) findViewById(R.id.textView_email);
     //   useremail.setText("kamran");
       // Toast.makeText(this, user.getEmail(), Toast.LENGTH_SHORT).show();

       // useremail.setText("Welcome" + user.getEmail());
      //  logout = (Button) findViewById(R.id.button2);
      //  logout.setOnClickListener(this);

       // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       // fab.setOnClickListener(new View.OnClickListener() {
           // @Override
          //  public void onClick(View view) {
              //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                       // .setAction("Action", null).show();
           // }
       // });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //changed to add drawer from set drawer
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        databaseUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              showData(dataSnapshot);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    public void ShowPopup() {
        TextView txtclose ;
        Button about,faq,features,watchvideo;
        myDialog.setContentView(R.layout.custompopup);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        faq = (Button)myDialog.findViewById(R.id.faq);
        features = (Button)myDialog.findViewById(R.id.features);
        watchvideo = (Button)myDialog.findViewById(R.id.watchVideo);
        txtclose.setText("X");

        about = (Button) myDialog.findViewById(R.id.About);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this,About.class));


            }
        });
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this,Faq.class));


            }
        });
        features.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this, Features.class));


            }
        });
        watchvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfile.this,WatchVideo.class));


            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }



    private void showData(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()){
            Log.d(TAG, ds.toString());
            //DoctorDetail dd = new DoctorDetail();
        UserDetail UserDetail = new UserDetail();
      UserDetail.setEmail(ds.child(UserId).getValue(UserDetail.class).getEmail());
            UserDetail.setName(ds.child(UserId).getValue(UserDetail.class).getName());

        //display all information
            Toast.makeText(this,UserDetail.getName(), Toast.LENGTH_LONG).show();
        Log.d(TAG, "ShowData :email " + UserDetail.getEmail());
        Log.d(TAG, "ShowData :name " + UserDetail.getName());
       email.setText(UserDetail.getEmail());
       name.setText(UserDetail.getName());
    }

    }


    public void onClick(View view) {

            //finish();
            //startActivity(new Intent(this, SigninActivity.class));
        if(view == guidecard){
           // firebaseAuth.signOut();
            startActivity(new Intent(UserProfile.this, guidetry.class));
        }
        if(view == modelcard){
            // firebaseAuth.signOut();
          //  startActivity(new Intent(UserProfile.this, UnityPlayerActivity.class));
            //startActivity(new Intent(UserProfile.this,UnityPlayerActivity.class));


        }
        if(view == arguidecard){
            // firebaseAuth.signOut();
           // startActivity(new Intent(UserProfile.this, Features.class));




        }
        if(view == dietcard){
            // firebaseAuth.signOut();
            startActivity(new Intent(UserProfile.this, diets.class));


        }
        if(view == excercisecard){
            // firebaseAuth.signOut();
            startActivity(new Intent(UserProfile.this, excercises.class));


        }
        if(view == locatehospitalcard){
            // firebaseAuth.signOut();

            startActivity(new Intent(UserProfile.this, hospital_MapsActivity.class));


        }
       /* if(view == quizcard){
            startActivity(new Intent(UserProfile.this, LocateDoctorMap.class));




        }*/
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            firebaseAuth.signOut();
            startActivity(new Intent(UserProfile.this, SigninActivity.class));
            finish();

           // return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager manager = getSupportFragmentManager();

       /* if (id == R.id.nav_camera) {


            Toast.makeText(getApplicationContext(),"inside",Toast.LENGTH_LONG).show();

            // Handle the camera action


            manager.beginTransaction().add(R.id.main_replace,new edit_profile()).addToBackStack("frag").commit();
          //  startActivity(new Intent(UserProfile.this, SigninActivity.class));
        }*/
        if (id == R.id.nav_changePassword) {
           // FragmentManager manager = getSupportFragmentManager();
            Log.d("tag2","inside");
            manager.beginTransaction().add(R.id.main_replace,new change_password()).addToBackStack("frag").commit();

        } /*else if (id == R.id.nav_slideshow) {

        }*/ else if (id == R.id.setting) {
            ShowPopup();

        }

        else if (id == R.id.nav_signout){
            firebaseAuth.signOut();
            startActivity(new Intent(UserProfile.this, SigninActivity.class));
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
