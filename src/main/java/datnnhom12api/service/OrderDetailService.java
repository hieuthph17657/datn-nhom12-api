package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.OrderDetailEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.OrderDetailRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public interface OrderDetailService {
    OrderDetailEntity save(OrderDetailRequest post) throws CustomException;
    OrderDetailEntity edit(Long id, OrderDetailRequest post) throws CustomException;
    OrderDetailEntity delete(Long id) throws CustomException;
    Page<OrderDetailEntity> paginate(int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);
}
