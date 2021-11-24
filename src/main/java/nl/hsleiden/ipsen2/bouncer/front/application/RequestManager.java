package nl.hsleiden.ipsen2.bouncer.front.application;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hsleiden.ipsen2.bouncer.front.exceptions.NotAuthorizedException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RequestManager {
    private static String host;
    private static String JWT = "";

    public static void setHost(String url) {
        host = url;
    }

    public static void setJWT(String JWT) {
        RequestManager.JWT = JWT;
    }

    public static HttpResponse makePostRequest (String uri, Map<String, String> paramMap, boolean auth) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(getUrl(uri));

        List<NameValuePair> params = new ArrayList<>();

        for (Map.Entry<String, String> entry: paramMap.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        try {
            return httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static HttpResponse makeGetRequest (String uri) throws NotAuthorizedException {
        if (JWT.isEmpty()) {
            throw new NotAuthorizedException();
        }

        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(getUrl(uri));

        httpGet.addHeader("Authorization", "Bearer " + JWT);

        try {
            return httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getModel (HttpEntity httpEntity, Class<?> resultClass) {
        if (httpEntity != null) {
            try (InputStream inputStream = httpEntity.getContent()) {
                ObjectMapper objectMapper = new ObjectMapper();

                return objectMapper.readValue(inputStream, resultClass);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static List<?> getList (HttpEntity httpEntity, Class<?> listItem) {
        try (InputStream inputStream = httpEntity.getContent()) {
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, listItem));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getUrl (String uri) {
        return host + uri;
    }
}
