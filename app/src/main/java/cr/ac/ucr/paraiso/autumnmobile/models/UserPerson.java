package cr.ac.ucr.paraiso.autumnmobile.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import cr.ac.ucr.paraiso.autumnmobile.common.CalendarUtils;

public class UserPerson implements Serializable{
    private int id;
    private String firstName, lastName1, lastName2, ind, birthday, gender;

    public UserPerson() {
    }

    public UserPerson(JSONObject jsonObject) throws JSONException, ParseException {
        int userPersonId = jsonObject.getInt("id");
        String firstName = jsonObject.getString("nombre");
        String lastName1 = jsonObject.getString("primer_apellido");
        String lastName2 = jsonObject.getString("segundo_apellido");
        String ind = jsonObject.getString("cedula");
        String birthday = jsonObject.getString("fecha_nacimiento");
        String gender = jsonObject.getString("genero");
        //Objects creation
        this.setId(userPersonId);
        this.setFirstName(firstName);
        this.setLastName1(lastName1);
        this.setLastName2(lastName2);
        this.setInd(ind);
        this.setBirthday(birthday);
        this.setGender(gender);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName1() {
        return lastName1;
    }

    public void setLastName1(String lastName1) {
        this.lastName1 = lastName1;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    public String getInd() {
        return ind;
    }

    public void setInd(String ind) {
        this.ind = ind;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName1() + " " + this.getLastName2();
    }
}
