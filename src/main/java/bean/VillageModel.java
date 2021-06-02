package bean;

public class VillageModel extends AbstractModel {
    private Long villageId;
    private String villageName;
    private DistrictModel districtModel;

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

    public DistrictModel getDistrictModel() {
        return districtModel;
    }

    public void setDistrictModel(DistrictModel districtModel) {
        this.districtModel = districtModel;
    }

    @Override
    public String toString() {
        return "VillageModel{" +
                "villageId=" + villageId +
                ", villageName='" + villageName + '\'' +
                ", districtModel=" + districtModel +
                '}';
    }
}
