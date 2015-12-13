package emiastoteam.emiasto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static int STATIC_INTEGER_VALUE = 0;
    protected String json = "";
    GPSTracker gps;

    GPSHelper gpsh;

    double latA = 51.421935 ;
    double lonA = 21.960356 ;
    double latT = 51.143642 ;
    double lonT = 23.484617 ;



    public void s()
    {
        //   Toast.makeText(getApplicationContext(), "this is my Toast message!!! =)",
        //         Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

       /* Button btn = (Button) findViewById(R.id.btnStartW);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  startW(v);
            }
        });*/


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            }

    public void startW(View view)
    {
        Intent intencja = new Intent(this, LoadFileActivity.class);
        startActivityForResult(intencja, STATIC_INTEGER_VALUE);
    }

    public void GetGPS(View view)
    {
        gps = new GPSTracker(MainActivity.this);
        // check if GPS enabled
        if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            double altitude = gps.getAltitude(latitude, longitude);


            // \n is for new line
            Toast.makeText(getApplicationContext(), "Twoja pozycja GPS to:  \nLat: " + latitude + "\nLong: " + longitude +"\nWys: " + altitude,  Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
                    }

//        gpsh = new GPSHelper();
//        double odleglosc = gpsh.CalculatDistance(latA, lonA, latT, lonT);
//        String result = String.format("%.2f", odleglosc);
//        Toast.makeText(getApplicationContext(), "Twoja odległość: " + result + "km", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case 0: {
                if (resultCode == Activity.RESULT_OK) {
                    String file = data.getStringExtra("FileName");
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        // TODO Update your TextView.
                        json = br.readLine();
                        if (!json.equals("")) {
                            Intent i = new Intent(this, wikitude.class);
                            i.putExtra("poiData", json);
                            startActivity(i);
                        }
                    } catch (FileNotFoundException ex){
                        Log.d("onActivityResult",ex.toString());
                    }
                    catch (IOException ioex) {
                        Log.d("onActivityResult",ioex.toString());
                    }

                }
                break;
            }
        }
    }
    public void ShowGPS(View view)
    {
        double odleglosc = gpsh.CalculatDistance(latA, lonA, latT, lonT);
        String result = String.format("%.2f", odleglosc);
        Toast.makeText(getApplicationContext(), "Twoja odległość: " + result + "km", Toast.LENGTH_SHORT).show();
    }


    public void showAbout(View view)
    {
        Intent intencja = new Intent(this, about.class);
        startActivity(intencja);
    }

    public void closeApp(View view)
    {
        finish();
        System.exit(0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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




}
