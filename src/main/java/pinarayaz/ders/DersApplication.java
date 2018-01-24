package pinarayaz.ders;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by PINAR on 24.1.2018.
 */
public class DersApplication {

    public static void main(String args[]) throws IOException {

        Document doc = Jsoup.connect("https://stars.bilkent.edu.tr/homepage/ajax/plainOfferings.php?" +
                "COURSE_CODE=CS&" +
                "SEMESTER=20172&" +
                "submit=List%20Selected%20Offerings&" +
                "rndval=" + System.currentTimeMillis())
                .get();

        System.out.println(doc);
    }
}
