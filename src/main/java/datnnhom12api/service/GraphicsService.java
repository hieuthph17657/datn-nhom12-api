package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.CartEntity;
import datnnhom12api.entity.GraphicsEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.CartRequest;
import datnnhom12api.request.GraphicsRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface GraphicsService {
    GraphicsEntity create(GraphicsRequest request) throws CustomException;

    Page<GraphicsEntity> paginate(int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);

    GraphicsEntity update(Long id, GraphicsRequest post) throws CustomException;

    GraphicsEntity delete(Long id) throws CustomException;
}
