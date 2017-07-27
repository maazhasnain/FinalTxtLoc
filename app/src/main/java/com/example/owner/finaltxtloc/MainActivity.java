package com.example.owner.finaltxtloc;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnGetLoc;

    Button button;
   public EditText editText;
   public EditText editText2;
   public TextView textView1,textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetLoc=(Button)findViewById(R.id.btnGetLoc);
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
        btnGetLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GpsTracker g = new GpsTracker(getApplicationContext());
                android.location.Location l = g.getLocation();
                if (l != null) {
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    //Toast.makeText(getApplicationContext(), "LAT: " + lat + "\n LON: " + lon, Toast.LENGTH_LONG).show();
                    textView1=(TextView) findViewById(R.id.textView1);
                    textView2=(TextView) findViewById(R.id.textView2);

                    String lons= "longitude:-  "+lon;
                    String lats= "lattitude:-  "+lat;

                    textView1.setText(lons);
                    textView2.setText(lats);


                }
            }

        });
    //txt msg code

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.SEND_SMS)) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.SEND_SMS}, 1);
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.SEND_SMS}, 1);
            }
        }
        else{
            //do nothing
        }

        button= (Button)findViewById(R.id.button);
        editText=(EditText)findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);



        button.setOnClickListener(new View.OnClickListener(){
                                      @Override
                                      public void onClick(View view){
                                          String number= editText.getText().toString();
                                          String sms=editText2.getText().toString();

                                          try {
                                              SmsManager smsManager = SmsManager.getDefault();
                                              smsManager.sendTextMessage(number, null, sms, null, null);
                                              Toast.makeText(MainActivity.this, "Sent!", Toast.LENGTH_SHORT).show();
                                          }
                                          catch (Exception e){
                                              Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                                          }
                                      }
                                  }
        );
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        switch (requestCode){
            case 1:{
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED ){

                    if(ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    }

                    else {

                        Toast.makeText(this, "Permission not Granted", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
            }
        }
    }
    }

