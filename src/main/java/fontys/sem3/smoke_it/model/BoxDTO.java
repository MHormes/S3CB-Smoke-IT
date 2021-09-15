package fontys.sem3.smoke_it.model;

public class BoxDTO {

    private int ID;
    private String name;
    private double basePrice;
    private String content;
    private String description;
    //PHOTO??????????????????/

    public BoxDTO(int ID, String name, double basePrice, String content, String description){
        this.ID = ID;
        this.name = name;
        this.basePrice = basePrice;
        this.content = content;
        this.description = description;
    }

    public BoxDTO(){

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
