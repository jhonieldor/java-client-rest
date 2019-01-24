package io.spring.microservices.javarestclient.services;

import com.google.gson.Gson;
import io.spring.microservices.javarestclient.model.RestRequestMethod;
import io.spring.microservices.javarestclient.model.StatusResponse;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Service
public abstract class AbstractRestService {

    protected Boolean httpsCertified;

    protected Gson gson;

    protected final String getResponse(String queryString, String url) {
        try {

            URL obj = new URL(url + "?" + queryString);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json; charset=utf-8");

            int responseCode = con.getResponseCode();

            BufferedReader in;
            if (responseCode >= 200 && responseCode < 300) {
                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
            } else {
                in = new BufferedReader(
                        new InputStreamReader(con.getErrorStream(), Charset.forName("UTF-8")));
            }

            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();


        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public final StatusResponse getStatusResponse(String queryString, String url, Map<String, String> headers) {
        try {

            URL obj = new URL(url + "?" + queryString);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json; charset=utf-8");

            for (Map.Entry<String, String> entry : headers.entrySet()) {
                con.setRequestProperty(entry.getKey(), entry.getValue());
            }

            int responseCode = con.getResponseCode();
            String responseMessage = con.getResponseMessage();

            BufferedReader in;
            if (responseCode >= 200 && responseCode < 300) {
                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
            } else {
                in = new BufferedReader(
                        new InputStreamReader(con.getErrorStream(), Charset.forName("UTF-8")));
            }

            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String body = response.toString();

            return new StatusResponse(responseCode, body, responseMessage);
        } catch (Exception ex) {
            ex.printStackTrace();

            return null;
        }
    }


    protected final StatusResponse requestApi(String url, Map<String, String> headerParams, RestRequestMethod restRequestMethod, Boolean inHost) {
        StringBuffer response = null;
        String messageResponse = null;
        Integer responseCode = null;
        try {
//            URL obj = new URL(url.replace("http", "https"));
//            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            HttpURLConnection con;

            if (httpsCertified == null) {
                httpsCertified = true;
            }

            if (httpsCertified && inHost) {
                URL obj = new URL(url.replace("http", "https"));
                con = (HttpsURLConnection) obj.openConnection();
            } else {
                URL obj = new URL(url);
                con = (HttpURLConnection) obj.openConnection();
            }


            //add reuqest header
            con.setRequestMethod(restRequestMethod.toString());
            con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            con.setRequestProperty("Accept", "application/json;charset=UTF-8");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            if (headerParams != null) {
                headerParams.entrySet().forEach(param -> {
                    con.setRequestProperty(param.getKey(), param.getValue());
                });
            }

            // Send post request
            con.setDoOutput(true);

            responseCode = con.getResponseCode();
            messageResponse = con.getResponseMessage();

            BufferedReader in;
            if (responseCode >= 200 && responseCode < 300) {
                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
            } else {
                in = new BufferedReader(
                        new InputStreamReader(con.getErrorStream(), Charset.forName("UTF-8")));
            }

            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            //commonsIntegrationService.enviarEmailNotificacao(ecommerce, "Erro de comunicação com o servidor: " + commonsIntegrationService.getMessageLog().getStackTrace(ex));
        }
        return new StatusResponse(responseCode, response.toString(), messageResponse);
    }

    protected final String requestGETMethod(String apiURL, Integer unit, String key) {
        String url = apiURL;

        Map<String, String> mapToConvert = new HashMap<>();

        mapToConvert.put("unit", unit.toString());
        mapToConvert.put("key", key);

        String queryString = "";

        for (Map.Entry<String, String> entry : mapToConvert.entrySet()) {
            queryString += entry.getKey() + "=" + entry.getValue() + "&";
        }

        return getResponse(queryString, url);
    }

    protected final StatusResponse requestGETStatusResponse(String apiURL, Integer unit, String key){
        String url = apiURL;

        Map<String, String> mapToConvert = new HashMap<>();

        mapToConvert.put("unit", unit.toString());
        mapToConvert.put("key", key);

        String queryString = "";

        for (Map.Entry<String, String> entry : mapToConvert.entrySet()) {
            queryString += entry.getKey() + "=" + entry.getValue() + "&";
        }

//        return getStatusResponse(queryString, url);
        return null;
    }


    public Boolean getStatusOK(Integer responseCode) {
        return responseCode >= 200 && responseCode < 300;
    }

}
