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

}
