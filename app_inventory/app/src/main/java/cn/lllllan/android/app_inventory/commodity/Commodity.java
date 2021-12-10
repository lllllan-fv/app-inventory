package cn.lllllan.android.app_inventory.commodity;

public class Commodity {
    private int imageId;
    private String name;
    private String number;

    public Commodity(int imageId, String name, String number) {
        this.imageId = imageId;
        this.name = name;
        this.number = number;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
