package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.ProductEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.ProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<ProductEntity> findAll();
    ProductEntity save(ProductRequest post) throws CustomException;

    ProductEntity edit(Long id, ProductRequest post) throws CustomException;

    ProductEntity delete(Long id) throws CustomException;

    Page<ProductEntity> paginate(int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);
}