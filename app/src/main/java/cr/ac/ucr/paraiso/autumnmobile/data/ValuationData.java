package cr.ac.ucr.paraiso.autumnmobile.data;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import cr.ac.ucr.paraiso.autumnmobile.models.Valuation;

public class ValuationData {

    public ValuationData() {
    }

    public List<Valuation> getAll(JSONObject json) throws JSONException, ParseException {
        List<Valuation> valuations = new ArrayList<>();
        JSONArray jsonList = json.getJSONArray("valuations");
        for (int i = 0; i < jsonList.length(); i++) {
            JSONObject jsonObject = jsonList.getJSONObject(i);
            valuations.add(new Valuation(jsonObject));
        }
        return valuations;
    }
}
