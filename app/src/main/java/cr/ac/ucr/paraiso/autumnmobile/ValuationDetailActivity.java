package cr.ac.ucr.paraiso.autumnmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cr.ac.ucr.paraiso.autumnmobile.common.CalendarUtils;
import cr.ac.ucr.paraiso.autumnmobile.models.Valuation;

public class ValuationDetailActivity extends AppCompatActivity {
    Valuation valuation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valuation_detail);

        //Objects retrieval
        Intent i = getIntent();
        valuation = (Valuation) i.getSerializableExtra("valuation");
        TextView textViewUP = (TextView)findViewById(R.id.textViewUserPerson);
        TextView textViewD = (TextView)findViewById(R.id.textViewDate);
        TextView textViewU = (TextView)findViewById(R.id.textViewUser);
        TextView textViewCI = (TextView)findViewById(R.id.textViewCognitiveImpairment);
        TextView textViewDD = (TextView)findViewById(R.id.textViewDepressiveDisorder);
        TextView textViewED = (TextView)findViewById(R.id.textViewEmotionalDisorder);
        TextView textViewMD = (TextView)findViewById(R.id.textViewMentalDisorder);
        TextView textViewFS = (TextView)findViewById(R.id.textViewFamilySituation);
        TextView textViewES = (TextView)findViewById(R.id.textViewEconomicSituation);
        TextView textViewCRA = (TextView)findViewById(R.id.textViewCurrentlyReceivingAttention);
        TextView textViewDis = (TextView)findViewById(R.id.textViewDischarged);
        textViewUP.setText(valuation.getUserPerson().getFirstName()+" "+valuation.getUserPerson().getLastName1()+" "+valuation.getUserPerson().getLastName2());
        textViewD.setText(CalendarUtils.toStringFormat(valuation.getUpdatedAt()));
        textViewU.setText(valuation.getUser().getName());
        textViewCI.setText(valuation.getCognitiveImpairment());
        textViewDD.setText(valuation.getDepressiveDisorder());
        if (valuation.isHasEmotionalDisorder()) {
            textViewED.setText(valuation.getEmotionalDisorderType());
        }else{
            textViewED.setText("No tiene");
        }
        if (valuation.isHasMentalDisorder()) {
            textViewMD.setText(valuation.getMentalDisorderType());
        }else{
            textViewMD.setText("No tiene");
        }
        textViewFS.setText(valuation.getFamilySituation());
        textViewES.setText(valuation.getEconomicSituation());
        if (valuation.isCurrentlyReceivingAttention()) {
            textViewCRA.setText("Sí");
        }else{
            textViewCRA.setText("No");
        }
        if (valuation.isDischarged()) {
            textViewDis.setText("Sí");
        }else{
            textViewDis.setText("No");
        }
    }
}
