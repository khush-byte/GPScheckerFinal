package com.example.gpschecker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class SplashActivity extends AppCompatActivity {
    private final int REQUEST_LOCATION_PERMISSION = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //requestLocationPermission();
        checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, REQUEST_LOCATION_PERMISSION);
    }

    private void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(SplashActivity.this, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(SplashActivity.this, new String[] { permission }, requestCode);
        }
        else {
            //Toast.makeText(SplashActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(SplashActivity.this, "Location Permission Granted", Toast.LENGTH_SHORT) .show();
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
        else {
            Toast.makeText(SplashActivity.this, "Location Permission Denied", Toast.LENGTH_SHORT) .show();
            finish();
        }
    }
//
//    @AfterPermissionGranted(REQUEST_LOCATION_PERMISSION)
//    public void requestLocationPermission() {
//        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
//        if(EasyPermissions.hasPermissions(this, perms)) {
//            //Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(SplashActivity.this, MainActivity.class));
//            finish();
//        }
//        else {
//            EasyPermissions.requestPermissions(this, "Please grant the location permission", REQUEST_LOCATION_PERMISSION, perms);
////
//        }
//    }
}