package cr.ac.ucr.paraiso.autumnmobile.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cr.ac.ucr.paraiso.autumnmobile.common.CalendarUtils;

public class Valuation implements Serializable {
    private int id;
    private String cognitiveImpairment, depressiveDisorder, emotionalDisorderType, mentalDisorderType, familySituation, economicSituation;
    private boolean hasEmotionalDisorder, hasMentalDisorder, currentlyReceivingAttention, discharged;
    private Date lastAttentionDate, createdAt, updatedAt;
    private User user;
    private UserPerson userPerson;
    private List<Observation> observations;

    public Valuation() {
    }

    public Valuation(JSONObject jsonObject) throws JSONException, ParseException {
        int id = jsonObject.getInt("id");
        String cognitiveImpairment = jsonObject.getString("deterioro_cognitivo");
        String depressiveDisorder = jsonObject.getString("trastorno_depresivo");
        String emotionalDisorderType = jsonObject.getString("tipo_trastorno_emocional");
        String mentalDisorderType = jsonObject.getString("tipo_trastorno_mental");
        String familySituation = jsonObject.getString("situacion_familiar");
        String economicSituation = jsonObject.getString("situacion_economica");
        int intHasEmotionalDisorder = jsonObject.getInt("tiene_trastorno_emocional");
        int intHasMentalDisorder = jsonObject.getInt("tiene_trastorno_mental");
        int intCurrentlyReceivingAttention = jsonObject.getInt("actualmente_recibiendo_atencion");
        int intDischarged = jsonObject.getInt("de_alta");
        boolean hasEmotionalDisorder = (intHasEmotionalDisorder > 0) ? true : false;
        boolean hasMentalDisorder = (intHasMentalDisorder > 0) ? true : false;
        boolean currentlyReceivingAttention = (intCurrentlyReceivingAttention > 0) ? true : false;
        boolean discharged = (intDischarged > 0) ? true : false;
        Date lastAttentionDate = CalendarUtils.toDateFormat(jsonObject.getString("fecha_ultima_atencion"));
        Date createdAt = CalendarUtils.toDateFormat(jsonObject.getString("created_at"));
        Date updatedAt = CalendarUtils.toDateFormat(jsonObject.getString("updated_at"));
        JSONObject jsonUser = jsonObject.getJSONObject("collaborator");
        int userId = jsonUser.getInt("id");
        String userName = jsonUser.getString("nombre");
        JSONObject jsonUserPerson = jsonObject.getJSONObject("user_person");
        int userPersonId = jsonUserPerson.getInt("id");
        String firstName = jsonUserPerson.getString("nombre");
        String lastName1 = jsonUserPerson.getString("primer_apellido");
        String lastName2 = jsonUserPerson.getString("segundo_apellido");
        String ind = jsonUserPerson.getString("cedula");
        String birthday = jsonUserPerson.getString("fecha_nacimiento");
        String gender = jsonUserPerson.getString("genero");
        //ToDo... Retrieve list of observations
        //Objects creation
        User user = new User();
        user.setId(userId);
        user.setName(userName);
        UserPerson userPerson = new UserPerson();
        userPerson.setId(userPersonId);
        userPerson.setFirstName(firstName);
        userPerson.setLastName1(lastName1);
        userPerson.setLastName2(lastName2);
        userPerson.setInd(ind);
        userPerson.setBirthday(birthday);
        userPerson.setGender(gender);
        //ToDo... List of observations
        this.setId(id);
        this.setCognitiveImpairment(cognitiveImpairment);
        this.setDepressiveDisorder(depressiveDisorder);
        this.setEmotionalDisorderType(emotionalDisorderType);
        this.setMentalDisorderType(mentalDisorderType);
        this.setFamilySituation(familySituation);
        this.setEconomicSituation(economicSituation);
        this.setHasEmotionalDisorder(hasEmotionalDisorder);
        this.setHasMentalDisorder(hasMentalDisorder);
        this.setCurrentlyReceivingAttention(currentlyReceivingAttention);
        this.setDischarged(discharged);
        this.setLastAttentionDate(lastAttentionDate);
        this.setCreatedAt(createdAt);
        this.setUpdatedAt(updatedAt);
        this.setUser(user);
        this.setUserPerson(userPerson);
        //ToDo... List of observations
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCognitiveImpairment() {
        return cognitiveImpairment;
    }

    public void setCognitiveImpairment(String cognitiveImpairment) {
        this.cognitiveImpairment = cognitiveImpairment;
    }

    public String getDepressiveDisorder() {
        return depressiveDisorder;
    }

    public void setDepressiveDisorder(String depressiveDisorder) {
        this.depressiveDisorder = depressiveDisorder;
    }

    public String getEmotionalDisorderType() {
        return emotionalDisorderType;
    }

    public void setEmotionalDisorderType(String emotionalDisorderType) {
        this.emotionalDisorderType = emotionalDisorderType;
    }

    public String getMentalDisorderType() {
        return mentalDisorderType;
    }

    public void setMentalDisorderType(String mentalDisorderType) {
        this.mentalDisorderType = mentalDisorderType;
    }

    public String getFamilySituation() {
        return familySituation;
    }

    public void setFamilySituation(String familySituation) {
        this.familySituation = familySituation;
    }

    public String getEconomicSituation() {
        return economicSituation;
    }

    public void setEconomicSituation(String economicSituation) {
        this.economicSituation = economicSituation;
    }

    public boolean isHasEmotionalDisorder() {
        return hasEmotionalDisorder;
    }

    public void setHasEmotionalDisorder(boolean hasEmotionalDisorder) {
        this.hasEmotionalDisorder = hasEmotionalDisorder;
    }

    public boolean isHasMentalDisorder() {
        return hasMentalDisorder;
    }

    public void setHasMentalDisorder(boolean hasMentalDisorder) {
        this.hasMentalDisorder = hasMentalDisorder;
    }

    public boolean isCurrentlyReceivingAttention() {
        return currentlyReceivingAttention;
    }

    public void setCurrentlyReceivingAttention(boolean currentlyReceivingAttention) {
        this.currentlyReceivingAttention = currentlyReceivingAttention;
    }

    public boolean isDischarged() {
        return discharged;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    public Date getLastAttentionDate() {
        return lastAttentionDate;
    }

    public void setLastAttentionDate(Date lastAttentionDate) {
        this.lastAttentionDate = lastAttentionDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserPerson getUserPerson() {
        return userPerson;
    }

    public void setUserPerson(UserPerson userPerson) {
        this.userPerson = userPerson;
    }

    public List<Observation> getObservations() {
        return observations;
    }

    public void setObservations(List<Observation> observations) {
        this.observations = observations;
    }

    @Override
    public String toString() {
        return CalendarUtils.toStringFormat(this.getUpdatedAt()) + " - " + this.getUserPerson().getFirstName() + " " + this.getUserPerson().getLastName1() + " " + this.getUserPerson().getLastName2();
    }
}
