package by.a1qa.klimov.utils;

import lombok.extern.log4j.Log4j;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Log4j
public class APIUtils {
    public static String doRequest(URL url, String method, String contentType, String accept) {
        String answer;
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(method);
            con.setRequestProperty("Content-Type", contentType);
            con.setRequestProperty("Accept", accept);
            con.setDoOutput(true);

            StringBuilder response;
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }
            answer = response.toString();
        } catch (MalformedURLException e) {
            log.error("URL is not created", e);
            return null;
        } catch (IOException e) {
            log.error("HttpURLConnection error", e);
            return null;
        } catch (JSONException e) {
            log.error("JSON body is not created", e);
            return null;
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return answer;
    }
}
