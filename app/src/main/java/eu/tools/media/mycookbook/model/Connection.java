package eu.tools.media.mycookbook.model;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Connection {

    private String hostname;
    private String port;
    private String dbname;
    private String password;
    private String username;

    public Connection(String hostname, String port, String dbname) {
        this.hostname = hostname;
        this.port = port;
        this.dbname = dbname;
    }

    public Connection(String hostname, String password, String username, String dbname, String port) {
        this.hostname = hostname;
        this.password = password;
        this.username = username;
        this.dbname = dbname;
        this.port = port;
    }



//    public JSONObject httpRequest (String HTTPMethod, String Path) {
//        URL serverAddress = null;
//        String resultat;
//        HttpURLConnection connection = null;
//
//        try {
//
//            if (this.port == null)
//                serverAddress = new URL("http://"+this.hostname+"/"+Path);
//            else
//                serverAddress = new URL("http://"+this.hostname+":"+this.port+Path);
//
//            // 1 ) Obtain a new HttpURLConnection by calling URL.openConnection() and casting the result to HttpURLConnection.
//
//            connection = (HttpURLConnection) serverAddress.openConnection();
//
//            // 2) Prepare the request. The primary property of a request is its URI.
//            // Request headers may also include metadata such as credentials, preferred content types, and session cookies.
//            connection.setRequestMethod(HTTPMethod);
//            connection.setReadTimeout(10000);
//
//            // 3) Optionally upload a request body. Instances must be configured with setDoOutput(true) if they include a request body. Transmit data by writing to the stream returned by getOutputStream().
//
//            // 4) Read the response.
//            // Response headers typically include metadata such as the response body's content type and length, modified dates and session cookies.
//            // The response body may be read from the stream returned by getInputStream(). If the response has no body, that method returns an empty stream.
//
//            // 5) Disconnect. Once the response body has been read, the HttpURLConnection should be closed by calling disconnect(). Disconnecting releases the resources held by a connection so they may be closed or reused.
//            connection.disconnect();
//
//
//            System.out.println("Querying URL : " + connection.getURL());
//
//
//
//            System.out.println("getContentEncoding : "+connection.getContentEncoding());
//            System.out.println("getContentLength : "+connection.getContentLength());
//            System.out.println("getContent : "+connection.getContent().toString());
//            System.out.println("getContentType : "+connection.getContentType().toString());
//            //System.out.println("getOutputStream : "+connection.getOutputStream().toString());
//
//            System.out.println("getDoOutput : "+connection.getDoOutput());
//            System.out.println("getExpiration : "+connection.getExpiration());
//            System.out.println("getDoInput : "+connection.getDoInput());
//
//            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//            readStream(in);
//
//
//            System.out.println("getInputStream : "+in.toString());
//
//            System.out.println("read : "+in.read());
//
//
//            resultat = "fine";
//            return resultat;
//        }
//        catch (Exception err) {
//            System.out.print("Exception :"+err);
//            return "failed";
//        }
//        catch (Error err) {
//            System.out.print("Error :"+err);
//            return "failed";
//        }
//        finally {
//            connection.disconnect();
//        }
//
//
//    }

    public Object httpRequestSample () {
        Object content;
        try {
            URL url = new URL("http://www.google.com");
            content = url.getContent();


        }
        catch (MalformedURLException exep) {
            content = exep;
        }
        catch (IOException exep) {
            content = exep;
        }

        return content.toString();
    } // TODO : Database Package
}
