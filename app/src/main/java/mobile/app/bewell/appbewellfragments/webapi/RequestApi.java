package mobile.app.bewell.appbewellfragments.webapi;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import mobile.app.bewell.appbewellfragments.models.api.Connexion;
import mobile.app.bewell.appbewellfragments.models.api.EventInfo;

public class RequestApi extends AsyncTask<Void, Void, EventInfo> {

    //region callback

    public interface IRequestEvent {
        void onRequestResult(EventInfo Temps);
    }

    private IRequestEvent callback;

    public void setCallback(IRequestEvent callback) {
        this.callback = callback;
    }

    //endregion

    @Override
    protected EventInfo doInBackground(Void... voids) {

        final String URL_BASE = "http://192.168.102.61:8000/api/event";
        final String URL = "http://{ip}:{port}/api/{filename}";



        //region connection API
        Connexion conec = new Connexion();
        String requestURL = URL_BASE;
        String requestResult = conec.connection(requestURL);

        EventInfo response = null;

        if (requestResult != null) {

            JSONObject json = null;
            try {
                json = new JSONObject(requestResult);
                response = new EventInfo(json); //(json)

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        //endregion

        return response;

    }

    @Override
    protected void onPostExecute(EventInfo temps) {
        if (callback != null) {
            callback.onRequestResult(temps);
        }
    }

}
