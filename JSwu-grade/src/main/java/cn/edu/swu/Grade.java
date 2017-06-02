package cn.edu.swu;

/**
 * Created by 西南大学开源协会 陈思定 on 2017/6/2.
 * <p>
 * Email : chensiding@qq.com
 */
public class Grade {

    private String name;
    private String grade;
    // 绩点,满绩点
    private String point;
    private String fullpoint;

    private String teacher;
    // 类别，必修还是选修
    private String category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getFullpoint() {
        return fullpoint;
    }

    public void setFullpoint(String fullpoint) {
        this.fullpoint = fullpoint;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grade)) return false;

        Grade grade = (Grade) o;

        if (!name.equals(grade.name)) return false;
        if (!teacher.equals(grade.teacher)) return false;
        return category.equals(grade.category);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + teacher.hashCode();
        result = 31 * result + category.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                ", point='" + point + '\'' +
                ", fullpoint='" + fullpoint + '\'' +
                ", teacher='" + teacher + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
