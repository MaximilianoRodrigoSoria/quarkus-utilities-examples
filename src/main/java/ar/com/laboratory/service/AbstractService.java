package ar.com.laboratory.service;

import java.util.List;

public interface AbstractService<RQ,RS,ID> {
    List<RS> readAll();
    RS created(RQ request);
    RS read(ID id) throws Exception;
    RS update(ID id,RQ request);
    void delete(ID id) throws Exception;
}