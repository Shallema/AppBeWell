package mobile.app.bewell.appbewellfragments.models.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Connexion {

    private String requestResult = null;
    private HttpURLConnection connection = null;

    public String connection(String requestURL) {

        try {
            URL url = new URL(requestURL);

            //region connection a l'API
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }
            //endregion

            //region lecture de l'input de l'API et création du String a renvoyer
            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);

            StringBuilder data = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
            reader.close();
            streamReader.close();

            requestResult = data.toString();

            //endregion

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return requestResult;
    }
}
