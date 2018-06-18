package cr.ac.ucr.paraiso.autumnmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cr.ac.ucr.paraiso.autumnmobile.models.Valuation;

public class ValuationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valuations);
        setTitle("Valoraciones");
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
        if (id == R.id.itemReports) {
            intent = new Intent(getApplicationContext(), ReportsActivity.class);
            startActivity(intent);
        } else if (id == R.id.itemStats) {
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

    //This method tries to fill the table, it returns true or false if it finds existing valuations
    private void fetch() {
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                "http://192.168.0.7:8000/api/psychology",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        // Parse the JSON
                        try {
                            List<Valuation> valuations = parse(jsonObject);
                            ArrayAdapter<Valuation> adapter = new ArrayAdapter<Valuation>(ValuationsActivity.this,android.R.layout.simple_list_item_1,valuations);
                            ListView listView = (ListView) findViewById(R.id.listView);
                            listView.setAdapter(adapter);
                            //If there are no valuations
                            //display empty message
                        } catch (JSONException e) {
                            Toast.makeText(ValuationsActivity.this, "Unable to parse data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(ValuationsActivity.this, "Unable to fetch data: " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        VolleyApplication.getInstance().getRequestQueue().add(request);
    }

    private List<Valuation> parse(JSONObject json) throws JSONException {
        ArrayList<Valuation> valuations = new ArrayList<>();
        JSONArray jsonList = json.getJSONArray("valuations");
        for (int i = 0; i < jsonList.length(); i++) {
            JSONObject jsonObject = jsonList.getJSONObject(i);
            //Getting jsonObject properties
            int id = jsonObject.getInt("id");
            String cognitiveImpairment = jsonObject.getString("deterioro_cognitivo");
            String depressiveDisorder = jsonObject.getString("trastorno_depresivo");
            String emotionalDisorderType = jsonObject.getString("tipo_trastorno_emocional");
            String mentalDisorderType = jsonObject.getString("tipo_trastorno_mental");
            String familySituation = jsonObject.getString("situacion_familiar");
            String economicSituation = jsonObject.getString("situacion_economica");
            int intHasEmotionalDisorder = jsonObject.getInt("tiene_trastorno_emocional");
            int intHasMentalDisorder = jsonObject.getInt("tiene_trastorno_mental");
            int intCurrentlyReceivingAttention = jsonObject.getInt("actualmente_recibiendo_atencion");
            int intDischarged = jsonObject.getInt("de_alta");
            boolean hasEmotionalDisorder = (intHasEmotionalDisorder > 0) ? true : false;
            boolean hasMentalDisorder = (intHasMentalDisorder > 0) ? true : false;
            boolean currentlyReceivingAttention = (intCurrentlyReceivingAttention > 0) ? true : false;
            boolean discharged = (intDischarged > 0) ? true : false;
            Date lastAttentionDate = dateFormatter(jsonObject.getString("fecha_ultima_atencion"));
            Date createdAt = dateFormatter(jsonObject.getString("created_at"));
            Date updatedAt = dateFormatter(jsonObject.getString("updated_at"));
            Valuation valuation = new Valuation();
            //Object creation
            valuation.setId(id);
            valuation.setCognitiveImpairment(cognitiveImpairment);
            valuation.setDepressiveDisorder(depressiveDisorder);
            valuation.setEmotionalDisorderType(emotionalDisorderType);
            valuation.setMentalDisorderType(mentalDisorderType);
            valuation.setFamilySituation(familySituation);
            valuation.setEconomicSituation(economicSituation);
            valuation.setHasEmotionalDisorder(hasEmotionalDisorder);
            valuation.setHasMentalDisorder(hasMentalDisorder);
            valuation.setCurrentlyReceivingAttention(currentlyReceivingAttention);
            valuation.setDischarged(discharged);
            valuation.setLastAttentionDate(lastAttentionDate);
            valuation.setCreatedAt(createdAt);
            valuation.setUpdatedAt(updatedAt);
            valuations.add(valuation);
            //Toast.makeText(ValuationsActivity.this, "valuation id: " + valuation.getId(), Toast.LENGTH_SHORT).show();
        }
        return valuations;
    }

    private Date dateFormatter(String stringDate){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(stringDate);
            return date;
        } catch (ParseException e) {
            Toast.makeText(ValuationsActivity.this, "Unable to format date: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return null;
    }
}
