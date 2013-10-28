package ru.kpfu.quantum.service.integration;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.*;
import java.util.Arrays;

/**
 * @author sala
 */
public class IntegrationService {
    private final static Log log = LogFactory.getLog(IntegrationService.class);

    private Gson gson;

    public IntegrationService() {
        this.gson = new Gson();
    }

    private String host;
    private int port;

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String codeToCircuit(String source) throws IOException {
        final String sourcesJson = gson.toJson(Arrays.asList(source));
        final StringBuilder fileStringBuilder = new StringBuilder("/str_to_circuit?");
        try {
            fileStringBuilder.append("source_codes=").append(URLEncoder.encode(sourcesJson, "UTF-8"));
            final URL url = new URL("http", host, port,  fileStringBuilder.toString());
            URLConnection urlConnection = url.openConnection();
            StringBuilder result = new StringBuilder();
            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                String working;
                while ((working = bufferedReader.readLine()) != null) {
                    result.append(working).append("\n");
                }
            }
            return result.toString();
        } catch (UnsupportedEncodingException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] codeToFile(String source) throws IOException {
        final StringBuilder fileStringBuilder = new StringBuilder("/str_to_file?");
        try {
            fileStringBuilder.append("source_code=").append(URLEncoder.encode(source, "UTF-8"))
                             .append("&extension=png");
            final URL url = new URL("http", host, port, fileStringBuilder.toString());
            final HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            final int contentLength = urlConnection.getContentLength();
            final byte[] result = new byte[contentLength];
            try(BufferedInputStream inputStream = new BufferedInputStream(urlConnection.getInputStream())) {
                inputStream.read(result);
            }
            return result;
        } catch (UnsupportedEncodingException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public String circuitToCode(String circuit) throws IOException {
        final StringBuilder fileStringBuilder = new StringBuilder("/circuit_to_str?");
        try {
            fileStringBuilder.append("circuit=").append(URLEncoder.encode(circuit, "UTF-8"));
            final URL url = new URL("http", host, port, fileStringBuilder.toString());
            final HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            final StringBuilder result = new StringBuilder();
            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                String working;
                while ((working = bufferedReader.readLine()) != null) {
                    result.append(working);
                }
            }
            return result.toString();
        } catch (UnsupportedEncodingException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] circuitToFile(String circuit) throws IOException {
        final StringBuilder fileStringBuilder = new StringBuilder("/circuit_to_file?");
        try {
            fileStringBuilder.append("circuit=").append(URLEncoder.encode(circuit, "UTF-8"))
                    .append("&extension=png");
            final URL url = new URL("http", host, port, fileStringBuilder.toString());
            final HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            final int contentLength = urlConnection.getContentLength();
            final byte[] result = new byte[contentLength];
            try(BufferedInputStream inputStream = new BufferedInputStream(urlConnection.getInputStream())) {
                inputStream.read(result);
            }
            return result;
        } catch (UnsupportedEncodingException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
