package pinarayaz.ders;

/**
 * Model class that holds a course section
 * Created by PINAR on 24.1.2018.
 */
class Section {
    private String courseId;
    private int quota;

    Section(String courseId, int quota) {

        this.courseId = courseId;
        this.quota = quota;
    }

    public int getQuota() {
        return quota;
    }

    public String getCourseId() {

        return courseId;
    }

    @Override
    public String toString() {
        return "Section{" +
                "courseId='" + courseId + '\'' +
                ", quota=" + quota +
                '}';
    }
}

