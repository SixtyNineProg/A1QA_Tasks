package by.a1qa.klimov.api.utils;

import aquality.selenium.core.logging.Logger;
import by.a1qa.klimov.exception.RequestException;
import by.a1qa.klimov.models.RequestResult;
import by.a1qa.klimov.models.User;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class APIUtils {
    public static RequestResult doRequest(String url,
                                          String method,
                                          String contentType,
                                          String accept,
                                          String body) {
        RequestResult requestResult = new RequestResult();
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) createUrl(url).openConnection();
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

    public static RequestResult doGetRequest(String url) {
        RequestResult requestResult = new RequestResult();
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) createUrl(url).openConnection();
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

    public static RequestResult doPostRequest(String url, String body) {
        RequestResult requestResult = new RequestResult();
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) createUrl(url).openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
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


    private static URL createUrl(String url) throws MalformedURLException {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            Logger.getInstance().fatal("Url doesn't create.", e);
            throw new MalformedURLException();
        }
    }

    public static User getUserById(List<User> users, int userId) {
        return users.stream().filter(p -> p.getId() == userId).findAny().orElse(null);
    }
}
