package com.example.user.locistest.Api;

import android.os.AsyncTask;

import com.example.user.locistest.InvitesPage;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by User on 12.04.2017.
 */

public class InvitationAnswerTask extends AsyncTask {
    InvitesPage activity;
    String token;
    int invitationId;
    String answer;

    public InvitationAnswerTask(String token, int invitationId, String answer){
        this.token = token;
        this.invitationId = invitationId;
        this.answer = answer;
    }
    @Override
    protected Object doInBackground(Object[] params) {
        activity = (InvitesPage) params[0];
        try {

            URL url = new URL("http://locis.lod-misis.ru/invitation/"+invitationId+"/response");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization","Basic "+ token);
            connection.connect();
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write('"' +answer+ '"');
            wr.flush();
            int responseCode = connection.getResponseCode();
            System.out.println(responseCode);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

    }

}
