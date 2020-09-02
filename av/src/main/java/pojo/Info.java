package pojo;

public class Info {

    private String code;

    private String id;

    private String image;

    @Override
    public String toString() {
        return "Info{" +
                "code='" + code + '\'' +
                ", id='" + id + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public Info(String code, String id) {
        this.code = code;
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
