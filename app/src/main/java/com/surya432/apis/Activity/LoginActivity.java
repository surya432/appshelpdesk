package com.surya432.apis.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import com.surya432.apis.Helpers.NetworkManager;
import com.surya432.apis.Helpers.SessionManager;
import com.surya432.apis.MainActivity;
import com.surya432.apis.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    @BindView(R.id.input_password)
    EditText input_password;
    @BindView(R.id.input_username)
    EditText input_username;
    private Intent intent;
    private SessionManager sessionManagerLogin;

    @Optional
    @OnClick(R.id.btn_login)
    public void btnLogin() {
        String KeyJob = "Driver";
        String UserId = input_username.getText().toString().toLowerCase();
        Log.d(TAG, "btnLogin: " + UserId);
        if (UserId.equals("sales")) {
            KeyJob = "Sales";
        }
        sessionManagerLogin.setLogin(true, "access", input_username.getText().toString().trim(), KeyJob);
        intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        checkInet();

    }

    private void checkInet() {
        sessionManagerLogin = new SessionManager(getApplicationContext());
        Log.d(TAG, "checkInet: " + NetworkManager.isNetworkAvaliable(this));
        if (NetworkManager.isNetworkAvaliable(this)) {
            if (sessionManagerLogin.isLoggedIn()) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
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
                            checkInet();
                        }
                    });
            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("NO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to invoke NO event
                            //dialog.cancel();
                        }
                    });
            // Showing Alert Message
            alertDialog.show();
        }
    }

    @Override
    public void onBackPressed() {
        backButtonHandler();
        return;
    }

    private void backButtonHandler() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                this);
        // Setting Dialog Title
        alertDialog.setTitle("Leave application?");
        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want to leave the application?");
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
                        //finish();

                    }
                });
        // Showing Alert Message
        alertDialog.show();
    }
}
