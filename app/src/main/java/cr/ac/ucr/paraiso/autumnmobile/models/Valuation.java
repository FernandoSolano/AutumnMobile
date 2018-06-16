package cr.ac.ucr.paraiso.autumnmobile.models;

import java.text.DateFormat;

public class Valuation {
    private int id;
    private String cognitiveImpairment, depressiveDisorder, emotionalDisorderType, mentalDisorderType, familySituation, economicSituation;
    private boolean hasEmotionalDisorder, hasMentalDisorder, currentlyReceivingAttention, discharged;
    private DateFormat lastAttentionDate, createdAt, updatedAt;

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

    public DateFormat getLastAttentionDate() {
        return lastAttentionDate;
    }

    public void setLastAttentionDate(DateFormat lastAttentionDate) {
        this.lastAttentionDate = lastAttentionDate;
    }

    public DateFormat getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateFormat createdAt) {
        this.createdAt = createdAt;
    }

    public DateFormat getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(DateFormat updatedAt) {
        this.updatedAt = updatedAt;
    }
}
