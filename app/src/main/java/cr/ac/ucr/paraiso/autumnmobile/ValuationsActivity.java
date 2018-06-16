package cr.ac.ucr.paraiso.autumnmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

public class ValuationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valuations);
        setTitle("Valoraciones");
        //si no existen valoraciones, se debe desplegar un mensaje
        //GridView grid = (GridView) findViewById(R.id.grid_valutions_dynamic);
        //grid.setAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        Intent intent;
        if(id==R.id.itemReports){
            intent = new Intent(getApplicationContext(), ReportsActivity.class);
            startActivity(intent);
        }else if(id==R.id.itemStats){
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
}
