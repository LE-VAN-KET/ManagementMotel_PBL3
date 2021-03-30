package service.implement;

import dao.IDistrictDAO;
import service.IDistrictService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

@ManagedBean
public class DistrictService implements IDistrictService {
    @Inject
    private IDistrictDAO districtDAO;
}
