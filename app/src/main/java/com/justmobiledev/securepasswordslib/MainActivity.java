package com.justmobiledev.securepasswordslib;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    // CONSTANTS
    private static final String SECURE_PREFS_FILE_NAME = "my_secure_prefs_file";
    private static final String SECURE_PREFS_STRING_KEY = "my_secret_string_key";

    // Members
    private SecurePasswordContainer secureContainer;

    // UI fields
    private EditText editTextPrefs;
    private TextView textViewRetrievedPrefs;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind UI controls
        editTextPrefs = findViewById(R.id.edittext_pref_value);
        textViewRetrievedPrefs = findViewById(R.id.textview_retrieved_pref_value);
        startButton = findViewById(R.id.button_start);

        // Setup secure preferences
        secureContainer = SecurePasswordContainer.create(MyApplication.getInstance());

        // Register listeners
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storePassword();
            }
        });

    }

    private void storePassword(){
        final String MY_PASSWORD_KEY = "my_password_key";

        // Get value from UI
        String password = editTextPrefs.getText().toString();

        SecurePasswordContainer.set(MY_PASSWORD_KEY, password);

        // Retrieve password
        String retrievedPassword = SecurePasswordContainer.get(MY_PASSWORD_KEY, "");

        textViewRetrievedPrefs.setText(retrievedPassword);
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
