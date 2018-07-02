package cr.ac.ucr.paraiso.autumnmobile;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cr.ac.ucr.paraiso.autumnmobile.common.CalendarUtils;
import cr.ac.ucr.paraiso.autumnmobile.data.ValuationData;
import cr.ac.ucr.paraiso.autumnmobile.data.VolleyApplication;
import cr.ac.ucr.paraiso.autumnmobile.models.User;
import cr.ac.ucr.paraiso.autumnmobile.models.UserPerson;
import cr.ac.ucr.paraiso.autumnmobile.models.Valuation;

public class ValuationsActivity extends AppCompatActivity {
    List<Valuation> valuations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valuations);
        setTitle("Valoraciones");
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Valuation valuation = (Valuation) parent.getItemAtPosition(position);
                //Toast.makeText(getApplicationContext(), valuation.getId()+" "+valuation.getUser().getName(), Toast.LENGTH_SHORT).show();
                //Serialize entry an send it on a new intent
                Intent intent = new Intent(getApplicationContext(), ValuationDetailActivity.class);
                intent.putExtra("valuation", valuation);
                startActivity(intent);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(getApplicationContext(), ValuationAddActivity.class);
                startActivity(intent);
            }
        });
        fetch();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.itemStats) {
            intent = new Intent(getApplicationContext(), StatsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void fetch() {
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                "https://autumnascate.herokuapp.com/api/psychology",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        // Parse the JSON
                        try {
                            ValuationData valuationData = new ValuationData();
                            valuations = valuationData.getAll(jsonObject);
                            ArrayAdapter<Valuation> adapter = new ArrayAdapter<Valuation>(ValuationsActivity.this, android.R.layout.simple_list_item_1, valuations);
                            ListView listView = (ListView) findViewById(R.id.listView);
                            listView.setAdapter(adapter);
                            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                            progressBar.setVisibility(View.GONE);
                            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                            fab.setVisibility(View.VISIBLE);
                            //If there are no valuations
                            //display empty message
                        } catch (JSONException e) {
                            Toast.makeText(ValuationsActivity.this, "Unable to parse data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } catch (ParseException e) {
                            Toast.makeText(ValuationsActivity.this, "Unable to parse dates: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(ValuationsActivity.this, "Unable to fetch data: " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
        VolleyApplication.getInstance().getRequestQueue().add(request);
    }
}
