package tr.zka;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

public class LinkService implements Callable<String> {
    private String url;
    private final int BUFFER_SIZE = 10000;
    private final int MAX_ATTEMPT = 2;

    public LinkService(String url){
        this.url = url;
    }

    @Override
    public String call() throws Exception {
        String content = sendGet();
        return content;
    }

    private String sendGet() throws IOException {
        int count = 0;
        while (true) {
            try {
                HttpClient client = HttpClientBuilder.create().build();
                HttpGet request = new HttpGet(url);

                HttpResponse response = client.execute(request);
                InputStream is = response.getEntity().getContent();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();

                int nRead;
                byte[] data = new byte[BUFFER_SIZE];
                while ((nRead = is.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }
                buffer.flush();
                buffer.toByteArray();

                return buffer.toString();
            } catch (IOException e) {
                if (++count == MAX_ATTEMPT)
                    throw e;
            }
        }
    }
}
