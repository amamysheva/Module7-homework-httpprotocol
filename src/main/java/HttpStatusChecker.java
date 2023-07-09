import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.text.MessageFormat;

public class HttpStatusChecker {
    private static final String URL_PATTERN = "https://http.cat/{0}.jpg";
    private static final String EXCEPTION_MESSAGE = "Invalid status code";

    public String getStatusImage(int code) throws HttpStatusException {
        String formattedLink = MessageFormat.format(URL_PATTERN, code);
        try {
            Connection.Response response = Jsoup.connect(formattedLink).ignoreContentType(true).execute();
        } catch (IOException e) {
            if (e instanceof HttpStatusException) {
                throw new HttpStatusException(EXCEPTION_MESSAGE, ((HttpStatusException) e).getStatusCode(), formattedLink);
            }
            e.printStackTrace();
        }
        return formattedLink;
    }
}
