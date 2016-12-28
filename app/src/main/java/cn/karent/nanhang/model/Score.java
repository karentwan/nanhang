package cn.karent.nanhang.model;

/**
 * Created by wan on 2016/12/26.
 * 成绩的model
 */
public class Score {
    /**
     * 课程名称
     */
    private String name;
    /**
     * 课时
     */
    private String courseTime;
    /**
     * 选修还是必修
     */
    private String property;
    /**
     * 课程性质
     */
    private String courseProperty;
    /**
     * 考试方式
     */
    private String way;
    /**
     * 分数
     */
    private float score;
    /**
     * 学分
     */
    private float credit;

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseProperty() {
        return courseProperty;
    }

    public void setCourseProperty(String courseProperty) {
        this.courseProperty = courseProperty;
    }
}
