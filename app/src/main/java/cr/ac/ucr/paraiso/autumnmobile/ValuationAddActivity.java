package cr.ac.ucr.paraiso.autumnmobile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import cr.ac.ucr.paraiso.autumnmobile.data.UserPeopleData;
import cr.ac.ucr.paraiso.autumnmobile.data.ValuationData;
import cr.ac.ucr.paraiso.autumnmobile.data.VolleyApplication;
import cr.ac.ucr.paraiso.autumnmobile.models.UserPerson;
import cr.ac.ucr.paraiso.autumnmobile.models.Valuation;

public class ValuationAddActivity extends AppCompatActivity {
    List<UserPerson> userPeople;

    private Calendar calendar;
    private int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valuation_add);
        setTitle("Nueva valoración");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        calendar = Calendar.getInstance();
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        year = calendar.get(Calendar.YEAR);
        fetchDate(year, month, day);

        fetchUsers();
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

    public void buttonCancelOnClick(View v) {
        finish();
    }

    public void imageButtonObtenerFechaeOnClick(View v) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                fetchDate(year, month, day);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void fetchDate(int year, int month, int day) {
        EditText editTextDate = (EditText) findViewById(R.id.editTextDate);
        editTextDate.setText(day + "/" + (month + 1) + "/" + year);
    }

    private void fetchUsers() {
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                "https://autumnascate.herokuapp.com/api/users",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        // Parse the JSON
                        try {
                            UserPeopleData userPeopleData = new UserPeopleData();
                            userPeople = userPeopleData.getAll(jsonObject);
                            ArrayAdapter<UserPerson> adapter = new ArrayAdapter<UserPerson>(getApplicationContext(), android.R.layout.simple_list_item_1, userPeople);
                            Spinner listView = (Spinner) findViewById(R.id.spinnerUserPerson);
                            listView.setAdapter(adapter);
                            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                            progressBar.setVisibility(View.GONE);
                            findViewById(R.id.linearLayout).setVisibility(View.VISIBLE);
                            //If there are no userPeople
                            //display empty message
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "JSONException... Unable to parse data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } catch (ParseException e) {
                            Toast.makeText(getApplicationContext(), "Unable to parse dates: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getApplicationContext(), "Unable to fetch data: " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
        VolleyApplication.getInstance().getRequestQueue().add(request);
    }
}
