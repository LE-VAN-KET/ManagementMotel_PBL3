package criteria;

public class Criteria {
    private Double price_from;
    private Double price_to;
    private Long square_from;
    private Long square_to;
    private Long villageId;
    private String districtName;

    public Double getPrice_from() {
        return price_from;
    }

    public void setPrice_from(Double price_from) {
        this.price_from = price_from;
    }

    public Double getPrice_to() {
        return price_to;
    }

    public void setPrice_to(Double price_to) {
        this.price_to = price_to;
    }

    public Long getSquare_from() {
        return square_from;
    }

    public void setSquare_from(Long square_from) {
        this.square_from = square_from;
    }

    public Long getSquare_to() {
        return square_to;
    }

    public void setSquare_to(Long square_to) {
        this.square_to = square_to;
    }

    public Long getVillageId() {
        return villageId;
    }

    public void setVillageId(Long villageId) {
        this.villageId = villageId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
