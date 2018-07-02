package cr.ac.ucr.paraiso.autumnmobile.data;

import android.app.Application;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import cr.ac.ucr.paraiso.autumnmobile.ValuationAddActivity;
import cr.ac.ucr.paraiso.autumnmobile.models.UserPerson;

public class UserPeopleData {

    public UserPeopleData() {
    }

    public List<UserPerson> getAll(JSONObject json) throws JSONException, ParseException {
        List<UserPerson> userPeople = new ArrayList<>();
        JSONArray jsonList = json.getJSONArray("userPersons");
        for (int i = 0; i < jsonList.length(); i++) {
            JSONObject jsonObject = jsonList.getJSONObject(i);
            userPeople.add(new UserPerson(jsonObject));
        }
        return userPeople;
    }
}
