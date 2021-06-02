package by.a1qa.klimov.api.utils;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.exception.RequestException;
import by.a1qa.klimov.models.RequestResult;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.ByteArrayBody;
import org.apache.hc.client5.http.entity.mime.HttpMultipartMode;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

public class APIUtils {
    public static RequestResult doGetRequest(String url, Map<String, String> params) {
        RequestResult requestResult = new RequestResult();
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) createUrl(url, params).openConnection();
            con.setRequestMethod("GET");

            requestResult.setCode(con.getResponseCode());

            int requestStatusCode = requestResult.getCode();
            if (requestStatusCode >= 200 && requestStatusCode < 300) {
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
            }
            return requestResult;
        } catch (MalformedURLException e) {
            Logger.getInstance().fatal("URL is not created", e);
            throw new RequestException("URL is not created");
        } catch (IOException e) {
            Logger.getInstance().fatal("HttpURLConnection error", e);
            throw new RequestException("HttpURLConnection error");
        } catch (JSONException e) {
            Logger.getInstance().fatal("JSON body is not created", e);
            throw new RequestException("JSON body is not created");
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }

    public static RequestResult doPostRequest(String url,
                                              String body,
                                              String contentType,
                                              String accept,
                                              Map<String, String> params) {
        RequestResult requestResult = new RequestResult();
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) createUrl(url, params).openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", contentType);
            con.setRequestProperty("Accept", accept);
            con.setDoOutput(true);

            if (body != null) {
                try (OutputStream os = con.getOutputStream()) {
                    byte[] input = body.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
            }

            requestResult.setCode(con.getResponseCode());

            int requestStatusCode = requestResult.getCode();
            if (requestStatusCode >= 200 && requestStatusCode < 300) {
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
            }
            return requestResult;
        } catch (MalformedURLException e) {
            Logger.getInstance().fatal("URL is not created", e);
            throw new RequestException("URL is not created");
        } catch (IOException e) {
            Logger.getInstance().fatal("HttpURLConnection error", e);
            throw new RequestException("HttpURLConnection error");
        } catch (JSONException e) {
            Logger.getInstance().fatal("JSON body is not created", e);
            throw new RequestException("JSON body is not created");
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }


    private static URL createUrl(String url, Map<String, String> parameters) throws MalformedURLException {
        try {
            url = addParameters(url, parameters);
            return new URL(url);
        } catch (MalformedURLException e) {
            Logger.getInstance().fatal("Url doesn't create.", e);
            throw new MalformedURLException();
        }
    }

    private static String addParameters(String url, Map<String, String> parameters) {
        Set<String> keys = parameters.keySet();
        if (keys.size() != 0) {
            url += "?";
            StringBuilder urlBuilder = new StringBuilder(url);
            for (String key : keys) {
                urlBuilder.append(key).append("=").append(parameters.get(key)).append("&");
            }
            return urlBuilder.toString();
        }
        Logger.getInstance().info("Parameters is empty.");
        return url;
    }
}
