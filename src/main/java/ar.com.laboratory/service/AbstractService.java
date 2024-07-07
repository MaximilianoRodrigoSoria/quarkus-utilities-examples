package ar.com.laboratory.service;

import java.util.List;

public interface AbstractService<RQ,RS,ID> {
    List<RS> readAll();
    RS created(RQ request);
    RQ read(ID id);
    RQ update(RS request);
    void delete(ID id);
}