import org.jsoup.HttpStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.MessageFormat;

public class HttpStatusImageDownloader {

    private static final String IMG_NAME_PATTERN = "image_{0}.jpg";

    void downloadStatusImage(int code) throws HttpStatusException {
        HttpStatusChecker httpStatusChecker = new HttpStatusChecker();
        String url = httpStatusChecker.getStatusImage(code);
        try {
            InputStream inputStream = new URL(url).openStream();
            Files.copy(inputStream, Path.of(MessageFormat.format(IMG_NAME_PATTERN, code)), StandardCopyOption.REPLACE_EXISTING);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
