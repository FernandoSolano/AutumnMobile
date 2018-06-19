package cr.ac.ucr.paraiso.autumnmobile.models;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cr.ac.ucr.paraiso.autumnmobile.common.CalendarUtils;

public class Valuation {
    private int id;
    private String cognitiveImpairment, depressiveDisorder, emotionalDisorderType, mentalDisorderType, familySituation, economicSituation;
    private boolean hasEmotionalDisorder, hasMentalDisorder, currentlyReceivingAttention, discharged;
    private Date lastAttentionDate, createdAt, updatedAt;
    private User user;
    private UserPerson userPerson;
    private List<Observation> observations;

    public Valuation() {
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
