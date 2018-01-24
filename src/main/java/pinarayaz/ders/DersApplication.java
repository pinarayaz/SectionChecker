package pinarayaz.ders;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Application to check section availability from Offerings
 * Created by PINAR on 24.1.2018.
 */
public class DersApplication {
    private static final List<String> VALID_COURSES = Arrays.asList("CS 202", "CS 224");

    public static void main(String args[]) throws IOException {

        Document doc = Jsoup.connect("https://stars.bilkent.edu.tr/homepage/ajax/plainOfferings.php?" +
                "COURSE_CODE=CS&" +
                "SEMESTER=20172&" +
                "submit=List%20Selected%20Offerings&" +
                "rndval=" + System.currentTimeMillis())
                .get();
        List<Section> sectionList = new ArrayList<Section>();
        for (Element element : doc.select("tbody").select("tr")) {
            Elements divisionElements = element.select("td");
            if (divisionElements.get(12).html().equalsIgnoreCase("unlimited")) {
                continue;
            }
            sectionList.add(new Section(divisionElements.get(0).html(), Integer.valueOf(divisionElements.get(13).html())));
        }

        for (Section s : sectionList) {
            for (String course : VALID_COURSES) {
                if (s.getCourseId().startsWith(course)) {
                    System.out.println(s.toString());
//                    if(s.getQuota() )
                }

            }

        }
    }
}
