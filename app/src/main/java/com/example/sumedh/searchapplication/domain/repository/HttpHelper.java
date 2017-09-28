package com.example.sumedh.searchapplication.domain.repository;

import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Sumedh on 9/28/2017.
 */

public abstract class HttpHelper {

    @MainThread
    public abstract void onSuccess(String responce);
    @MainThread
    public abstract void onFailure(Exception e);

    int CONNECTION_TIMEOUT = 3000;
    int READ_TIMEOUT = 3000;
    String REQUEST_METHOD;

    public void execute(final String url, String requestMethod){

        REQUEST_METHOD = requestMethod;
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        Future<String> future=executorService.submit(new Callable<String>() {
            @Override
            public String call(){
                try {
                   return executeUrl(new URL(url));
                }catch (IOException io){
                    throw new NetworkException(io);
                }catch (Exception e){
                    throw new NetworkException(e);
                }

            }
        });

        try {
            onSuccess(future.get());
        }catch (NetworkException e){
            onFailure(e);
        }catch (Exception e){
            onFailure(e);
        }
    }

    public class NetworkException extends IllegalStateException{

        public NetworkException(Throwable cause) {
            super(cause);
        }
    }

    @WorkerThread
    private String executeUrl(URL url) throws IOException {
        InputStream stream = null;
        HttpsURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setDoInput(true);
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
            // Retrieve the response body as an InputStream.
            stream = connection.getInputStream();
            if (stream != null) {
                result =  readStream(stream);
            }
        } finally {
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }

    /**
     * Converts the contents of an InputStream to a String.
     */
    public String readStream(InputStream stream)
            throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(stream));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line).append('\n');
        }
        return total.toString();
    }
}
