package cr.ac.ucr.paraiso.autumnmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Autumn Mobile - Psicología");
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
        } else {
            intent = new Intent(getApplicationContext(), StatsActivity.class);
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    public void onClickValuations(View v) {
        Intent intent = new Intent(MainActivity.this, ValuationsActivity.class);
        startActivity(intent);
    }

    public void onClickStats(View v) {
        Intent intent = new Intent(MainActivity.this, StatsActivity.class);
        startActivity(intent);
    }
}
