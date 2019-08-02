package com.surya432.skripsi;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.surya432.skripsi.Activity.LoginActivity;
import com.surya432.skripsi.Fragment.AdminFragment;
import com.surya432.skripsi.Fragment.AkunFragment;
import com.surya432.skripsi.Fragment.InboxFragment;
import com.surya432.skripsi.Fragment.MyPlanFragment;
import com.surya432.skripsi.Fragment.UserFragment;
import com.surya432.skripsi.Helpers.NetworkManager;
import com.surya432.skripsi.Helpers.SessionManager;
import com.surya432.skripsi.Service.FirebaseFCM;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.menubar)
    ImageView menubar;
    @BindView(R.id.tvVersion)
    TextView tvVersion;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.TopBar)
    ConstraintLayout TopBar;
    @BindView(R.id.frame_container)
    FrameLayout frameContainer;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private String versionName = BuildConfig.VERSION_NAME;
    private SessionManager sessionManager;
    private UserFragment userFragment;
    private MyPlanFragment nav_myPlan;
    private InboxFragment nav_inbox;
    private AkunFragment nav_akun;
    private AdminFragment adminFragment;

    private void firstTime() {
        checkInetSession();
        tvVersion.setText("Version" + BuildConfig.VERSION_NAME);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

       /* final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        menubar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });*/
        firstTime();
    }

    private void checkInetSession() {
        Log.d(TAG, "checkInet: " + NetworkManager.isNetworkAvaliable(this));
        if (NetworkManager.isNetworkAvaliable(this)) {
            sessionManager = new SessionManager(getApplicationContext());
            if (!sessionManager.isLoggedIn()) {
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            } else {
                Log.d(TAG, "Refreshed token: " + FirebaseFCM.getToken(MainActivity.this));
                adminFragment = new AdminFragment();
                userFragment = new UserFragment();
                nav_myPlan = new MyPlanFragment();
                nav_inbox = new InboxFragment();
                nav_akun = new AkunFragment();
                getRoles();
            }
        } else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                    this);
            // Setting Dialog Title
            alertDialog.setTitle("Koneksi Internet Tidak Tersedia.");
            // Setting Dialog Message
            alertDialog.setMessage("Silakan Aktifkan Paket Data Internet. Coba lagi?");
            // Setting Icon to Dialog
            alertDialog.setIcon(R.drawable.ic_sinyaloffs);
            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("YES",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            checkInetSession();
                        }
                    });
            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("NO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to invoke NO event
                            //dialog.cancel();
                            finish();
                        }
                    });
            // Showing Alert Message
            alertDialog.show();
        }
    }

    private void getRoles() {
        Log.d(TAG, "checkInetSession: " + sessionManager.getRole());
        if (sessionManager.getRole().equals("User")) {
            replaceFragment(userFragment);
        } else {
            replaceFragment(adminFragment);
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            backButtonHandler();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            getRoles();

        } else if (id == R.id.nav_inbox) {
            replaceFragment(nav_inbox);

        } else if (id == R.id.nav_my_plan) {
            replaceFragment(nav_myPlan);


        } else if (id == R.id.nav_akun) {
            replaceFragment(nav_akun);


        } else if (id == R.id.nav_logout) {
            sessionManager.destroySession();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void backButtonHandler() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        // Setting Dialog Title
        alertDialog.setTitle("Keluar Dari Aplikasi.");
        // Setting Dialog Message
        alertDialog.setMessage("Apa Anda Yakin Ingin Keluar Aplikasi?");
        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.ic_ic_exit);
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        dialog.cancel();
                    }
                });
        // Showing Alert Message
        alertDialog.show();
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }


}
