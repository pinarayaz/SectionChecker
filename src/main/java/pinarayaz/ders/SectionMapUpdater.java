package pinarayaz.ders;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Map;

/**
 * Created by PINAR on 24.1.2018.
 */
public class SectionMapUpdater {
    private final String departmentId;

    public SectionMapUpdater(String departmentId) {
        this.departmentId = departmentId;
    }

    public void update(Map<String, Section> sectionMap) throws IOException {
        Document doc = Jsoup.connect("https://stars.bilkent.edu.tr/homepage/ajax/plainOfferings.php?" +
                "COURSE_CODE=" + departmentId + "&" +
                "SEMESTER=20172&" +
                "submit=List%20Selected%20Offerings&" +
                "rndval=" + System.currentTimeMillis())
                .get();

        for (Element element : doc.select("tbody").select("tr")) {
            Elements divisionElements = element.select("td");
            if (divisionElements.get(12).html().equalsIgnoreCase("unlimited")) {
                continue;
            }
            String courseId = divisionElements.get(0).html();
            if (sectionMap.containsKey(courseId)) {
                sectionMap.get(courseId).setQuota(Integer.valueOf(divisionElements.get(13).html()));
            } else {
                sectionMap.put(courseId, new Section(courseId, Integer.valueOf(divisionElements.get(13).html())));
            }
        }
    }
}
