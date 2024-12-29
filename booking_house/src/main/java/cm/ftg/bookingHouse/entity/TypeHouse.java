package cm.ftg.bookingHouse.entity;
import lombok.Getter;
import lombok.Setter;
public enum TypeHouse {
BEDROOM (1, "bedroom" ),
STUDIO (2, "studios"),
APARTMENT (3, "apartment"),
VILLA(4, "villa");
private Integer key;
private String description;

TypeHouse(Integer key, String description){
    this.key=key;
    this.description=description;}

    
    public Integer getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
