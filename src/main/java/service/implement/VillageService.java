package service.implement;

import dao.IVillageDAO;
import service.IVillageService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

@ManagedBean
public class VillageService implements IVillageService {
    @Inject
    private IVillageDAO villageDAO;
}
