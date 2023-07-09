import org.jsoup.HttpStatusException;

import java.util.Scanner;

public class HttpImageStatusCli {

    void askStatus() {
        System.out.println("Enter HTTP status code:");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (!input.matches("\\d+")) {
                System.out.println("Please enter valid number:");
                continue;
            }
            int code = Integer.parseInt(input);
            try {
                HttpStatusImageDownloader httpStatusImageDownloader = new HttpStatusImageDownloader();
                httpStatusImageDownloader.downloadStatusImage(code);
                System.out.println("Image has been downloaded :)");
                break;
            } catch (HttpStatusException e) {
                System.out.println("There is no image for HTTP status " + code);
                System.out.println("Enter another HTTP status code:");
            }
        }
        scanner.close();
    }
}
