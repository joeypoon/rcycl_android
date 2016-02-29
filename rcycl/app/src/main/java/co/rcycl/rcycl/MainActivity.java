package co.rcycl.rcycl;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Pair;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private BaseFragment mActiveFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        swapFragment(new RequestPickupFragment());
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_request_pickup:
                swapFragment(new RequestPickupFragment());
                break;

            case R.id.nav_go_pickup:
                break;

            case R.id.nav_leaderboard:
                break;

            case R.id.nav_my_account:
                break;

            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void postToAPI(APIObject obj) {
        postToAPI(obj, null);
    }

    public void postToAPI(APIObject obj, Method callback) {
        new HttpAsyncTask(APIManager.HTTP_POST, callback).execute(obj);
    }

    // Accepts a Fragment and swaps that into the activity_main's content wrapper.
    public void swapFragment(BaseFragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
        mActiveFragment = fragment;
    }

    // Handles API calls in the background. To use, create a new instance
    // with the desired HttpUrlConnection (see APIManager). If using a POST,
    // PUT or DELETE, one or more APIObjects(s) must be passed in on execute().
    private class HttpAsyncTask extends AsyncTask<APIObject, Void, Pair<APIObject,String>> {
        private String mHttpMethod;
        private Method mCallback;

        public HttpAsyncTask(String httpMethod) {
            this(httpMethod, null);
        }

        public HttpAsyncTask(String httpMethod, Method callback) {
            mHttpMethod = httpMethod;
            mCallback = callback;
        }

        @Override
        protected Pair<APIObject, String> doInBackground(APIObject... params) {
            String result = "Testing";

            // While an array of APIObject is required to be passed in,
            // we only ever use one so pull params[0] for simplicity.
            APIObject param = params[0];

//            try {
//                if (param == null) {
//                    throw new Exception("Request parameter cannot be null.");
//                }
//
//                HttpURLConnection connection;
//                connection = APIManager.initRequest(mHttpMethod, param.getEndpointName(), param.getID());
//
//                if (connection == null) {
//                    throw new Exception("Http connection failed to initialize.");
//                }
//
//                // Possibly send data, and request a response.
//                switch (mHttpMethod) {
//                    case (APIManager.HTTP_GET):
//                        connection.getResponseMessage();
//                        break;
//
//                    case (APIManager.HTTP_POST):
//                        JSONObject json = param.toJSONObject();
//                        if (json != null) {
//                            connection.setDoOutput(true);
//                            OutputStream output = connection.getOutputStream();
//                            output.write(json.toString().getBytes());
//                            output.flush();
//                            output.close();
//                        }
//                        break;
//
//                    default:
//                        throw new Exception("HTTP method " + mHttpMethod + " is invalid.");
//                }
//
//                // Read the response from the server.
//                InputStream input = new BufferedInputStream(connection.getInputStream());
//                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//
//                String response = "Got response from server: ";
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    response += line;
//                }
//
//                input.close();
//
//                // Update param with information from the server.
//                JSONObject json = new JSONObject(response);
//                boolean success = param.fromJSONObject(json);
//                response += " received and converted with result " + success;
//
//                result = response;
//
//            } catch (Exception e) {
//                result = "Exception: " + e.getLocalizedMessage();
//            }

            return new Pair<>(param, result);
        }

        @Override
        // The result is packaged with the updated APIObject and the string response from the server.
        protected void onPostExecute(Pair<APIObject,String> result) {
            super.onPostExecute(result);

            // At this time, if a callback was set, invoke it on the active fragment.
            if (mCallback != null) {
                try {
                    mCallback.invoke(mActiveFragment,result.first);
                } catch (Exception e) {
                    Utils.log(MainActivity.this, e.getLocalizedMessage());
                }

            } else {
                Utils.cheers(MainActivity.this, "null callback");
            }

            //DEBUGGING
            Utils.log(this, result.second);
            Utils.cheers(MainActivity.this, result.second);
        }
    }
}
