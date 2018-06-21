package cr.ac.ucr.paraiso.autumnmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import cr.ac.ucr.paraiso.autumnmobile.models.Valuation;

public class StatsCognitiveImpairmentActivity extends AppCompatActivity {
    private List<Valuation> valuations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_cognitive_impairment);
        setTitle("Deterioro cognitivo");

        Intent i = getIntent();
        valuations = (List<Valuation>) i.getSerializableExtra("valuations");
        int qtyX0 = 0;
        int qtyX1 = 0;
        int qtyX2 = 0;
        int qtyX3 = 0;
        int qtyX4 = 0;
        int qtyX5 = 0;
        int qtyX6 = 0;
        for (Valuation valuation : valuations) {
            if (valuation.getCognitiveImpairment().equals("No tiene")) {
                qtyX0++;
            } else if (valuation.getCognitiveImpairment().equals("Alteración cognitiva leve")) {
                qtyX1++;
            } else if (valuation.getCognitiveImpairment().equals("Alteración cognitiva moderada")) {
                qtyX2++;
            } else if (valuation.getCognitiveImpairment().equals("Alteración cognitiva severa")) {
                qtyX3++;
            } else if (valuation.getCognitiveImpairment().equals("Demencia leve")) {
                qtyX4++;
            } else if (valuation.getCognitiveImpairment().equals("Demencia moderada")) {
                qtyX5++;
            } else if (valuation.getCognitiveImpairment().equals("Demencia severa")) {
                qtyX6++;
            }
        }
        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        ArrayList<String> xVals = new ArrayList<String>();
        if(qtyX0!=0) {
            xVals.add("No tiene");
            yvalues.add(new Entry(qtyX0, 0));
        }
        if(qtyX1!=0) {
            xVals.add("Alteración cognitiva leve");
            yvalues.add(new Entry(qtyX1, 1));
        }
        if(qtyX2!=0) {
            xVals.add("Alteración cognitiva moderada");
            yvalues.add(new Entry(qtyX2, 2));
        }
        if(qtyX3!=0) {
            xVals.add("Alteración cognitiva severa");
            yvalues.add(new Entry(qtyX3, 3));
        }
        if(qtyX4!=0) {
            xVals.add("Demencia leve");
            yvalues.add(new Entry(qtyX4, 4));
        }
        if(qtyX5!=0) {
            xVals.add("Demencia moderada");
            yvalues.add(new Entry(qtyX5, 5));
        }
        if(qtyX6!=0) {
            xVals.add("Demencia severa");
            yvalues.add(new Entry(qtyX6, 6));
        }
        PieDataSet dataSet = new PieDataSet(yvalues, "");
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);
        PieData data = new PieData(xVals, dataSet);
        // In percentage Term
        data.setValueFormatter(new PercentFormatter());
        // Default value
        //data.setValueFormatter(new DefaultValueFormatter(0));
        data.setValueTextSize(13f);
        pieChart.setData(data);
        pieChart.setDescription("Personas usuarias que padecen de deterioro cognitivo en los últimos 6 meses");
        pieChart.animateXY(1400, 1400);
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
        } else if (id == R.id.itemStats) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

        /*
        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(8f, 0));
        yvalues.add(new Entry(15f, 1));
        yvalues.add(new Entry(12f, 2));
        yvalues.add(new Entry(25f, 3));
        yvalues.add(new Entry(23f, 4));
        yvalues.add(new Entry(17f, 5));
        PieDataSet dataSet = new PieDataSet(yvalues, "Election Results");
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);
        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("January");
        xVals.add("February");
        xVals.add("March");
        xVals.add("April");
        xVals.add("May");
        xVals.add("June");
        PieData data = new PieData(xVals, dataSet);
        // In percentage Term
        data.setValueFormatter(new PercentFormatter());
        // Default value
        //data.setValueFormatter(new DefaultValueFormatter(0));
        pieChart.setData(data);
        pieChart.setDescription("This is Pie Chart");
        */
}
