package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.AccessoryEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.accessoryRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface AccessoryService {
    AccessoryEntity create(accessoryRequest post) throws CustomException;

    Page<AccessoryEntity> paginate(int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);

    List<AccessoryEntity> findAll();
}
