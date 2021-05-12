package by.a1qa.klimov.utils;

import by.a1qa.klimov.models.Post;
import by.a1qa.klimov.models.RequestResult;
import lombok.extern.log4j.Log4j;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Log4j
public class APIUtils {
    public static RequestResult doRequest(URL url, String method, String contentType, String accept, String body) {
        RequestResult requestResult = new RequestResult();
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(method);
            if (contentType != null)
                con.setRequestProperty("Content-Type", contentType);
            if (accept != null)
                con.setRequestProperty("Accept", accept);
            con.setDoOutput(true);

            if (body != null) {
                try (OutputStream os = con.getOutputStream()) {
                    byte[] input = body.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
            }

            requestResult.setCode(con.getResponseCode());

            StringBuilder response;
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }
            requestResult.setAnswer(response.toString());
        } catch (MalformedURLException e) {
            log.error("URL is not created");
            return requestResult;
        } catch (IOException e) {
            log.error("HttpURLConnection error");
            return requestResult;
        } catch (JSONException e) {
            log.error("JSON body is not created");
            return requestResult;
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return requestResult;
    }

    public static URL createUrl(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            log.error("Url doesn't create.", e);
            throw new NullPointerException("Url doesn't create.");
        }
    }
}
