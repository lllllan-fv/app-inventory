package cn.lllllan.android.app_inventory.record;

public class Record {

    private String type;
    private String company;
    private String date;
    private String commodity;
    private String price;
    private String quantity;
    private String amount;

    public Record(String type, String company, String date, String commodity, String price, String quantity, String amount) {
        this.type = type;
        this.company = company;
        this.date = date;
        this.commodity = commodity;
        this.price = price;
        this.quantity = quantity;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
