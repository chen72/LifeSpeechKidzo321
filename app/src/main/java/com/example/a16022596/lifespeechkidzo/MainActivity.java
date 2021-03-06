package com.example.a16022596.lifespeechkidzo;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //sidebar start
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    //sidebar end
    TextView tvNouns,tvPreposition,tvConcepts,tvPronouns,tvVerb;
    CardView cvNouns;
    CardView cvPreposition;
    CardView cvConcepts;
    CardView cvPronouns;
    CardView cvVerb;
    int id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //sidebar start
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        tvNouns = (TextView)findViewById(R.id.textViewNouns);
        tvPronouns = (TextView)findViewById(R.id.textViewPronouns);
        tvPreposition = (TextView)findViewById(R.id.textViewPreposition);



        cvNouns = (CardView)findViewById(R.id.cardViewViewNouns);
        cvConcepts = (CardView)findViewById(R.id.cardViewConcepts);
        cvPreposition = (CardView)findViewById(R.id.cardViewPreposition);
        cvPronouns = (CardView)findViewById(R.id.cardViewViewPronouns);
        cvVerb = (CardView)findViewById(R.id.cardViewVerb);

        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemTextColor(null);
        navigationView.setItemTextAppearance(R.style.MenuTextStyle);

        retrieve();
        cvNouns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = 1;
                Intent i = new Intent(MainActivity.this,showSubsCat.class);
                i.putExtra("id", id);
                startActivity(i);
            }
        });
        cvConcepts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = 5;
                Intent i = new Intent(MainActivity.this,showSubsCat.class);
                i.putExtra("id", id);
                startActivity(i);
            }
        });
        cvPronouns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = 3;
                Intent i = new Intent(MainActivity.this,showSubsCat.class);
                i.putExtra("id", id);
                startActivity(i);
            }
        });
        cvPreposition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = 2;
                Intent i = new Intent(MainActivity.this,showSubsCat.class);
                i.putExtra("id", id);
                startActivity(i);
            }
        });
        cvVerb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = 4;
                Intent i = new Intent(MainActivity.this,showSubsCat.class);
                i.putExtra("id", id);
                startActivity(i);
            }
        });
    }

    //sidebar start
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            Intent mainActivityIntent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(mainActivityIntent);


        } else if (id == R.id.nav_Login) {

            Intent mainActivityIntent = new Intent(MainActivity.this, LoginPage.class);
            startActivity(mainActivityIntent);
        } else if (id == R.id.nav_CreateAccount) {
            Intent mainActivityIntent = new Intent(MainActivity.this, RegisterPage.class);
            startActivity(mainActivityIntent);


        } else if (id == R.id.nav_contact) {

            Intent mainActivityIntent = new Intent(MainActivity.this, ContactPage.class);
            startActivity(mainActivityIntent);


        } else if (id == R.id.nav_about) {

            Intent rpIntent = new Intent(Intent.ACTION_VIEW);
            // Set the URL to be used.
            rpIntent.setData(Uri.parse("https://lifespeech.org/about-us/"));
            startActivity(rpIntent);
        }
        return false;
    }
    private void retrieve() {

            String url = "https://fypdmsd.000webhostapp.com/retrieveCategoriesAndroid.php";
            AsyncHttpClient client = new AsyncHttpClient();
            client.get(url, new AsyncHttpResponseHandler() {

                @Override
                public void onFailure(Throwable arg0, String arg1) {
                    Toast.makeText(getApplicationContext(),
                            "Please connect to internet and try again",
                            Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFinish() {

                    super.onFinish();
                }

                @Override
                public void onStart() {

                    super.onStart();
                }

                @Override
                public void onSuccess(String response) {
                    try {
                        JSONArray categoryArray =new JSONArray(response);
                        Log.i("info", String.valueOf(categoryArray));
                        String strName = "";
                        for (int i = 0; i < categoryArray.length(); i++) {
                            JSONObject moduleObj = (JSONObject) categoryArray.get(i);
                            String id = (String) moduleObj.get("category_id");
                            String name = (String) moduleObj.get("category_name");
                            String iString = String.valueOf(i);
                            strName += name ;
                        }

                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                }
            });
        }

    }




