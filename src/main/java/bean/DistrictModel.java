package bean;

import java.util.List;

public class DistrictModel extends AbstractModel {
    private Long districtId;
    private String districtName;
    private List<VillageModel> listVillage;

    public List<VillageModel> getListVillage() {
        return listVillage;
    }

    public void setListVillage(List<VillageModel> listVillage) {
        this.listVillage = listVillage;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
