package cr.ac.ucr.paraiso.autumnmobile;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import cr.ac.ucr.paraiso.autumnmobile.data.ValuationData;
import cr.ac.ucr.paraiso.autumnmobile.data.VolleyApplication;
import cr.ac.ucr.paraiso.autumnmobile.models.Valuation;

public class StatsActivity extends AppCompatActivity {
    private List<Valuation> valuations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        setTitle("Estadísticas");
        valuations = new ArrayList<>();
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
        if (id == R.id.itemValuations) {
            intent = new Intent(getApplicationContext(), ValuationsActivity.class);
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

    public void onClickCognitiveImpairment(View v) {
        Intent intent = new Intent(this, StatsCognitiveImpairmentActivity.class);
        intent.putExtra("valuations", (Serializable) valuations);
        startActivity(intent);
    }

    public void onClickDepressiveDisorder(View v) {
        Intent intent = new Intent(this, StatsDepressiveDisorderActivity.class);
        startActivity(intent);
    }

    public void onClickEmotionalDisorder(View v) {
        Intent intent = new Intent(this, StatsEmotionalDisorderActivity.class);
        startActivity(intent);
    }

    public void onClickMentalDisorder(View v) {
        Intent intent = new Intent(this, StatsMentalDisorderActivity.class);
        startActivity(intent);
    }

    public void onClickGeneralStats(View v) {
        Intent intent = new Intent(this, StatsGeneralActivity.class);
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
                        try {
                            ValuationData valuationData = new ValuationData();
                            valuations = valuationData.getAll(jsonObject);
                            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                            progressBar.setVisibility(View.GONE);
                            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout);
                            linearLayout.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            Toast.makeText(StatsActivity.this, "Unable to parse data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } catch (ParseException e) {
                            Toast.makeText(StatsActivity.this, "Unable to parse dates: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(StatsActivity.this, "Unable to fetch data: " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
        VolleyApplication.getInstance().getRequestQueue().add(request);
    }
}
