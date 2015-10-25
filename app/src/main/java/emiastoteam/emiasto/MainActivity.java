package emiastoteam.emiasto;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public void s()
    {
        //   Toast.makeText(getApplicationContext(), "this is my Toast message!!! =)",
        //         Toast.LENGTH_LONG).show();
    }

 //   private static String klucz = "xtxePCeZ5Y7SXNQuy2EqOE5xJF8SEhFVGjXx1VBxgkFfCW9gMrI/pOv/X/7fQQNx2Dyvji4iv30eXJ6aeQ/rIvqPu50r6vzwHqFsye3/CDj4IHiIuld0onBO2x5IHerTi/2XdqRcLkdl1TlqARN+Zuf1kt2Z5SOhZTOXNz+CMmVTYWx0ZWRfX6r/HAHziyMSONHEL/hNbpnCdrlgV64EFw8QXzXHglIOJjIQw2N7X1OQYmq6tRpvROzVJetm0T00ZBkRkJj7+OF3SSV6r2dPXBPqfcbb68mNnVueNeMlN4L/KHlA/u88W9NNT9fY3TOSI46SDveYYNYhmvRm8qTROIfH080B7kLzpFQw8Jl6stEaAafuafuJmp0ZvbSMFO1b8kBSHoaOAMDi1+Qn2YnjJd30RCbhtUsHecgEtqrPCMIUtCDHEApQMEpC5pOe3wMCjAvh9H7SxmCH1QPb7gPsoMXuauRy5DPuorhcsjTz4JWcTOkk2qOeJaj3/ccMjN8GEvPQ5U52ALz6ZO8VfK369sCk1Am2QVO/zUI53e3XX9pkTnoh1x5nCkrDeNeEJ64pHLrY3XMj1MTlcBOUzzjFuLFgXl4S+jcJceGWDShyqWzZUY409SaTLVmI7kkn4CuzzOic+nN2tBmBnA9WaLT9F6OTXwYnl7hjtwWXKJy8BRr6Vp8R/pu5xC6F+bAxOUxz";
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
        Intent intencja = new Intent(this, wikitude.class);
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
