package pinarayaz.ders;

/**
 * Created by PINAR on 24.1.2018.
 */
public class Section {
    private String courseId;
    private int quota;

    public Section(String courseId, int quota) {

        this.courseId = courseId;
        this.quota = quota;
    }

    public int getQuota() {
        return quota;
    }

    public String getCourseId() {

        return courseId;
    }
}
