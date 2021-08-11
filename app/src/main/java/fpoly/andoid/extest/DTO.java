package fpoly.andoid.extest;

public class DTO {

    public static final String TB_NAME = "tb_note";
    public static final String COL_ID = "id";
    public static final String COL_TIME = "time";
    public static final String COL_CONTENT = "content";
    public static final String COL_TITLE = "title";


    private String content, title;
    private int id;
    private String time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
