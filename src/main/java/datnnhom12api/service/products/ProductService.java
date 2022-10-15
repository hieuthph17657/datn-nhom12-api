package datnnhom12api.service.products;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.products.ProductEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.products.ProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ProductService {
    ProductEntity insert(ProductRequest post) throws CustomException;

    ProductEntity update(Long id, ProductRequest post) throws CustomException;

    ProductEntity delete(Long id) throws CustomException;

    Page<ProductEntity> paginate(int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);
}