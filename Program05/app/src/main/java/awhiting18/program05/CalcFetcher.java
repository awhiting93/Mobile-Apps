package awhiting18.program05;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by awhiting18 on 12/10/2015.
 */
public class CalcFetcher {
    public byte[] getUrlBytes(String location) throws IOException
    {
        URL url = new URL(location);
        HttpURLConnection connect = (HttpURLConnection)url.openConnection();

        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connect.getInputStream();

            if(connect.getResponseCode() != HttpURLConnection.HTTP_OK)
            {
                throw new IOException(connect.getResponseMessage() + "with "
                        + location);

            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while((bytesRead = in.read(buffer)) > 0){
                out.write(buffer, 0 , bytesRead);
            }
            out.close();
            return out.toByteArray();

        }finally {
            connect.disconnect();
        }
    }

    public String getUrlString(String location) throws IOException
    {
        return new String(getUrlBytes(location));
    }
}
