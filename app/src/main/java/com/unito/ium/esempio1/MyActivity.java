package com.unito.ium.esempio1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/* importante:
se volte estendere Activity, dovete usare anche getAtionBar()
e usare uno stile nuovo in res/styles.xml al posto di AppCompat
 */
public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) {
            Log.d("MIAPP", "onCreated called");
        }
        setContentView(R.layout.activity_my);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        checkWhoStarted();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        // Handle presses on the action bar items
        Log.d("MIAPP", "onOptionsItemSelected");
        switch (item.getItemId()) {
            case R.id.action_search:
                openSearch();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public final static String EXTRA_MESSAGE = "com.unito.ium.esempio1.MESSAGE";

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    private void checkWhoStarted(){
        // metodo che viene invocato per vedere se siamo stati invocati
        // da un'altra applicazione (nel nostro corso sara' l'esempio "NetApp"
        Intent intent = getIntent();
        Log.d("onCreate","intent action: "+intent.getAction());
        String typ = intent.getType();

        if (typ != null && typ.equals("text/plain")) {
            String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
            if (sharedText != null) {
                // Update UI to reflect text being shared
                Log.d("onCreate", "ricevuto: " + sharedText);
                ((TextView) findViewById(R.id.daltra)).setText(sharedText);
                // al termine dell' App sara' spedito al mittente
                setResult(444);
                //    se dovessimo restituire invece qualcosa di piu' complesso:
                //    Intent result = new Intent("com.example.RESULT_ACTION", Uri.parse("content://result_uri");
                //     setResult(Activity.RESULT_OK, result);
                //     finish();
            }
        }
    }

    void openSearch(){
        if (BuildConfig.DEBUG) {
            Log.d("MIAPP", "openSearch called");
        }
    }

    void openSettings(){
        if (BuildConfig.DEBUG) {
            Log.d("MIAPP", "openSettings called");
        }
    }

}
