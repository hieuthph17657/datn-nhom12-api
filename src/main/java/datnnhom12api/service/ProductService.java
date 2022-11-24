package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.dto.ProductDTO;
import datnnhom12api.entity.ProductEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.ProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ProductService {

    ProductEntity insert(ProductRequest post) throws CustomException;

    ProductEntity update(Long id, ProductRequest post) throws CustomException;

    ProductEntity delete(Long id) throws CustomException;

    Page<ProductEntity> paginate(int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);

    ProductEntity create(ProductEntity productEntity);

    ProductDTO findById(Long id);

    ProductEntity active(Long id) throws CustomException;

    ProductEntity inActive(Long id) throws CustomException;

    List<ProductEntity> discount(Long id, List<Long> idProduct) throws CustomException;

}
