package cr.ac.ucr.paraiso.autumnmobile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class ValuationAddActivity extends AppCompatActivity {

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
}
