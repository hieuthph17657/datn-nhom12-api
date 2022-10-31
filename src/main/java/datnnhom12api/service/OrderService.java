package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.entity.OrderDetailEntity;
import datnnhom12api.entity.OrderEntity;
import datnnhom12api.entity.UserEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.CategoryRequest;
import datnnhom12api.request.CreateUserOnOrderRequest;
import datnnhom12api.request.OrderDetailRequest;
import datnnhom12api.request.OrderRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface OrderService {

    OrderEntity save(OrderRequest port) throws CustomException;

    OrderEntity edit(Long id, OrderRequest post) throws CustomException;

    OrderEntity delete(Long id) throws CustomException;

    Page<OrderEntity> paginate(int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);

    UserEntity createUser(CreateUserOnOrderRequest createUserOnOrderRequest) throws CustomException;

    List<OrderDetailEntity> findByOrder(Long id);
}
