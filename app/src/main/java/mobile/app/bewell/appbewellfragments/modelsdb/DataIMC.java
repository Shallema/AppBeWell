package mobile.app.bewell.appbewellfragments.modelsdb;

import android.widget.ScrollView;

public class DataIMC {

    private float imcUser;
    private String interpretationUser;
    private long timestampUser;

    public float getImcUser() {
        return imcUser;
    }

    public void setImcUser(float imcUser) {
        this.imcUser = imcUser;
    }

    public String getInterpretationUser() {
        return interpretationUser;
    }

    public void setInterpretationUser(String interpretationUser) {
        this.interpretationUser = interpretationUser;
    }

    public long getTimestampUser() {
        return timestampUser;
    }

    public void setTimestampUser(long timestampUser) {
        this.timestampUser = timestampUser;
    }

    public DataIMC() {
    }

    public DataIMC(float imcUser, String interpretationUser, long timestampUser) {
        this.imcUser = imcUser;
        this.interpretationUser = interpretationUser;
        this.timestampUser = timestampUser;
    }

    @Override
    public String toString() {
        return getImcUser() + " - " + getTimestampUser();
    }

}
