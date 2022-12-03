package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.dto.ReturnDetailDTO;
import datnnhom12api.dto.UpdateReturnDetailDTO;
import datnnhom12api.entity.ProductEntity;
import datnnhom12api.entity.ReturnDetailEntity;
import datnnhom12api.entity.ReturnEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.ProductRequest;
import datnnhom12api.request.ReturnDetailRequest;
import datnnhom12api.request.ReturnRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ReturnService {

    ReturnEntity insert(ReturnRequest post) throws CustomException;

    ReturnEntity update(Long id, ReturnRequest post) throws CustomException;

    Page<ReturnEntity> paginate(int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);

    void delete(Long id);

    List<ReturnDetailDTO> findById(Long id);

    UpdateReturnDetailDTO updateByReturnDetail(Long id, ReturnDetailRequest request);

    ReturnEntity getById(Long id);
}