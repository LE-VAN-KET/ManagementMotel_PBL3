package service.implement;

import dao.IPostDAO;
import service.IPostService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

@ManagedBean
public class PostService implements IPostService {
    @Inject
    private IPostDAO postDAO;
}
