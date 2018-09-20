package mobile.app.bewell.appbewellfragments.models.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class EventInfo {

    private String name, place, city, description, emailContact;
    private double price;
    private Boolean registration;
    private Date dateStart, dateEnd, dateLimitInscr;

    //region Getters & Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmailContact() {
        return emailContact;
    }

    public void setEmailContact(String emailContact) {
        this.emailContact = emailContact;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Boolean getRegistration() {
        return registration;
    }

    public void setRegistration(Boolean registration) {
        this.registration = registration;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Date getDateLimitInscr() {
        return dateLimitInscr;
    }

    public void setDateLimitInscr(Date dateLimitInscr) {
        this.dateLimitInscr = dateLimitInscr;
    }

    //endregion

    //region Méthodes

    public String getInfoTime() {

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy - HH:mm", Locale.FRANCE);
        dateFormatter.setTimeZone(TimeZone.getDefault());

        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        timeFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));

        return dateFormatter.format(dateStart) + " - " + timeFormatter.format(dateEnd);
    }

    //endregion

    //region Ctor

    public EventInfo(String name, String place, String city, String description, Date dateStart, Date dateEnd) {
        this.name = name;
        this.place = place;
        this.city = city;
        this.description = description;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public EventInfo(String name, String place, String city, String description, String emailContact, double price, Boolean registration, Date dateStart, Date dateEnd, Date dateLimitInscr) {
        this.name = name;
        this.place = place;
        this.city = city;
        this.description = description;
        this.emailContact = emailContact;
        this.price = price;
        this.registration = registration;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.dateLimitInscr = dateLimitInscr;
    }
    //endregion

    //region Transforme in methode to get list of event (static)
    public EventInfo(JSONObject json) throws JSONException {


        //assignation du nom de l'événement
        JSONArray events = json.getJSONArray("events");
        JSONObject one = events.getJSONObject(1);
        this.name = one.getString("name");

        //assignation de la descrption de l'événement
        this.description = one.getString("description");

        //assignation du lieu de l'événément
        JSONObject location = one.getJSONObject("location");
        this.place = location.getString("place");
        this.city = location.getString("city");

        //assignation de la date de l'événement
        this.dateStart = new Date(one.getLong("date_start") * 1000L);

        boolean isNotValidDate = one.getString("date_end").equals("null");
        this.dateEnd = (isNotValidDate) ? null : new Date(one.getLong("date_end") * 1000L);
    }

    //endregion

    public String getLieu() {
        return getPlace() + " " + getCity();
    }

    public String getDateComplete() {
        return getDateStart() + " - " + getDateEnd();
    }


}
