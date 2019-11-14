package com.ndevaki.stockbrokerage.client;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.misc.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class HttpClient {
    static String result=null;

    public static String getrequest(String api) throws IOException {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet(api);
            CloseableHttpResponse response = httpClient.execute(request);
            try {
               // response.getEntity().getContent()
                if(response.getStatusLine().getStatusCode()==200) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        // return it as a String
                        InputStream stream=entity.getContent();
                        result= EntityUtils.toString(entity);
                       // result=new String(IOUtils. (stream),"UTF-8");

                    }
                }
            } finally {
                response.close();
                httpClient.close();
                return result;
            }
    }
}
