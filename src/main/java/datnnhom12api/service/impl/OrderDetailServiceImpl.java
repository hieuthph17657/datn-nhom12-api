package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.entity.OrderDetailEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.OrderDetailRepository;
import datnnhom12api.request.OrderDetailRequest;
import datnnhom12api.service.OrderDetailService;
import datnnhom12api.specifications.CategorySpecifications;
import datnnhom12api.specifications.OrderDetailSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Override
    public OrderDetailEntity save(OrderDetailRequest orderDetailRequest) throws CustomException {
        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
        orderDetailEntity.setData(orderDetailRequest);
        orderDetailEntity = orderDetailRepository.save(orderDetailEntity);
        return orderDetailEntity;
    }

    @Override
    public OrderDetailEntity edit(Long id, OrderDetailRequest orderDetailRequest) throws CustomException {
        Optional<OrderDetailEntity> orderDetailEntityOptional = orderDetailRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id đơn hàng chi tiết phải lớn hơn 0");
        }
        if (orderDetailEntityOptional.isEmpty()){
            throw new CustomException(403, "không tìm thấy đơn hàng chi tiết muốn sửa");
        }
        OrderDetailEntity orderDetailEntity = orderDetailEntityOptional.get();
        orderDetailEntity.setData(orderDetailRequest);
        orderDetailEntity = orderDetailRepository.save(orderDetailEntity);
        return orderDetailEntity;
    }

    @Override
    public OrderDetailEntity delete(Long id) throws CustomException {
        Optional<OrderDetailEntity> orderDetailEntityOptional = orderDetailRepository.findById(id);
        if (orderDetailEntityOptional.isEmpty()){
            throw new CustomException(403, "không tìm thấy đơn hàng chi tiết ");
        }
        OrderDetailEntity orderDetailEntity = orderDetailRepository.getById(id);
        orderDetailRepository.delete(orderDetailEntity);
        return orderDetailEntity;
    }

    @Override
    public Page<OrderDetailEntity> paginate(int page, int limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Specification<OrderDetailEntity> specifications = OrderDetailSpecifications.getInstance().getQueryResult(filters);

        return orderDetailRepository.findAll(specifications, pageable);
    }
}
