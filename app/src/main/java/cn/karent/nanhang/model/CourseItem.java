package cn.karent.nanhang.model;

/**
 * Created by wan on 2016/12/29.
 * 每一节课的信息
 */
public class CourseItem {
    /**
     * 名称
     */
    private String name;
    /**
     * 教室
     */
    private String classroom;
    /**
     * 教师
     */
    private String teacher;
    /**
     * 时间
     */
    private String time;
    /**
     * 周数
     */
    private String week;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

}
