package bean;

public class VillageModel extends AbstractModel {
    private Long villageId;
    private String villageName;
    private Long districtId;

    public Long getVillageId() {
        return villageId;
    }

    public void setVillageId(Long villageId) {
        this.villageId = villageId;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    @Override
    public String toString() {
        return "VillageModel{" +
                "villageId=" + villageId +
                ", villageName='" + villageName + '\'' +
                ", districtId=" + districtId +
                '}';
    }
}
