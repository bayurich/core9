package ru.netology;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;



import java.io.*;

public class Main {

    public static final String REMOTE_SERVICE_URI = "https://api.nasa.gov/planetary/apod";
    public static final String API_KEY = "TQes7DD7NAZ7CX3aFhxWsH4gh2b1RJxVdVKbihng";
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setUserAgent("Task1")
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();
        HttpGet request = new HttpGet(REMOTE_SERVICE_URI + "?api_key=" + API_KEY);
        CloseableHttpResponse response = httpClient.execute(request);
        Nasa nasa = mapper.readValue(response.getEntity().getContent(), Nasa.class);
        if (nasa == null){
            System.out.println("Response is null. Stop processing");
            return;
        }

        String uri = nasa.getUrl();
        uri = uri == null || "".equals(uri) ? nasa.getHdUrl() : uri;
        if (uri == null || "".equals(uri)){
            System.out.println("URI is empty. Stop processing");
            return;
        }
        String fileName = uri.split("/")[uri.split("/").length - 1];
        //System.out.println("uri: " + uri);
        //System.out.println("fileName: " + fileName);

        request = new HttpGet(uri);
        response = httpClient.execute(request);
        InputStream is = response.getEntity().getContent();

        try (FileOutputStream fos = new FileOutputStream(fileName)){
            int i;
            while ( (i = is.read()) != -1) {
                fos.write(i);
            }
            System.out.println("Successfully write to file " + fileName);
        }
        catch (Exception e){
            System.out.println("Error while write to file " + fileName + ": " + e.getMessage());
        }
    }
}
