package cr.ac.ucr.paraiso.autumnmobile;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import cr.ac.ucr.paraiso.autumnmobile.common.CalendarUtils;
import cr.ac.ucr.paraiso.autumnmobile.data.VolleyApplication;
import cr.ac.ucr.paraiso.autumnmobile.models.UserPerson;
import cr.ac.ucr.paraiso.autumnmobile.models.Valuation;

public class ValuationDetailActivity extends AppCompatActivity {
    Valuation valuation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valuation_detail);
        setTitle("Detalles");

        //Objects retrieval
        Intent i = getIntent();
        valuation = (Valuation) i.getSerializableExtra("valuation");
        TextView textViewUP = (TextView) findViewById(R.id.textViewUserPerson);
        TextView textViewD = (TextView) findViewById(R.id.textViewDate);
        TextView textViewU = (TextView) findViewById(R.id.textViewUser);
        TextView textViewCI = (TextView) findViewById(R.id.textViewCognitiveImpairment);
        TextView textViewDD = (TextView) findViewById(R.id.textViewDepressiveDisorder);
        TextView textViewED = (TextView) findViewById(R.id.textViewEmotionalDisorder);
        TextView textViewMD = (TextView) findViewById(R.id.textViewMentalDisorder);
        TextView textViewFS = (TextView) findViewById(R.id.textViewFamilySituation);
        TextView textViewES = (TextView) findViewById(R.id.textViewEconomicSituation);
        TextView textViewCRA = (TextView) findViewById(R.id.textViewCurrentlyReceivingAttention);
        TextView textViewDis = (TextView) findViewById(R.id.textViewDischarged);
        textViewUP.setText(valuation.getUserPerson().getFirstName() + " " + valuation.getUserPerson().getLastName1() + " " + valuation.getUserPerson().getLastName2());
        textViewD.setText(CalendarUtils.toStringFormat(valuation.getUpdatedAt()));
        textViewU.setText(valuation.getUser().getName());
        textViewCI.setText(valuation.getCognitiveImpairment());
        textViewDD.setText(valuation.getDepressiveDisorder());
        if (valuation.isHasEmotionalDisorder()) {
            textViewED.setText(valuation.getEmotionalDisorderType());
        } else {
            textViewED.setText("No tiene");
        }
        if (valuation.isHasMentalDisorder()) {
            textViewMD.setText(valuation.getMentalDisorderType());
        } else {
            textViewMD.setText("No tiene");
        }
        if (!valuation.getFamilySituation().equals("null")) {
            textViewFS.setText(valuation.getFamilySituation());
        } else {
            textViewFS.setText("N/E");
        }
        textViewES.setText(valuation.getEconomicSituation());
        if (valuation.isCurrentlyReceivingAttention()) {
            textViewCRA.setText("Sí");
        } else {
            textViewCRA.setText("No");
        }
        if (valuation.isDischarged()) {
            textViewDis.setText("Sí");
        } else {
            textViewDis.setText("No");
        }
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
            finish();
        } else if (id == R.id.itemStats) {
            intent = new Intent(getApplicationContext(), StatsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void buttonDeleteOnClick(View v) {
        AlertDialog.Builder alert = new AlertDialog.Builder(ValuationDetailActivity.this);
        alert.setTitle("Eliminar valoración");
        alert.setMessage("¿Está seguro que desea eliminar la valoración seleccionada?");
        alert.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callDeleteRequest();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    private void callDeleteRequest() {
        String url = "https://autumnascate.herokuapp.com/api/psychology/delete/" + valuation.getId();
        StringRequest postRequest = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Toast.makeText(getApplicationContext(), "Se ha eliminado la valoración...", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ValuationsActivity.class);
                        startActivity(intent);
                        //finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // error
                        Toast.makeText(getApplicationContext(), "Unable to post data: " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }
        );
        VolleyApplication.getInstance().getRequestQueue().add(postRequest);
    }
}
