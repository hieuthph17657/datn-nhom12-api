package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.*;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.InformationRepository;
import datnnhom12api.repository.OrderDetailRepository;
import datnnhom12api.repository.OrderRepository;
import datnnhom12api.repository.UserRepository;
import datnnhom12api.request.CreateUserOnOrderRequest;
import datnnhom12api.request.OrderDetailRequest;
import datnnhom12api.request.OrderRequest;
import datnnhom12api.service.OrderService;
import datnnhom12api.specifications.OrderSpecifications;
import datnnhom12api.utils.support.CategoryStatus;
import datnnhom12api.utils.support.OrderDetailStatus;
import datnnhom12api.utils.support.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("orderService")
@Transactional(rollbackFor = Throwable.class)
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    InformationRepository informationRepository;

    @Override
    public OrderEntity save(OrderRequest orderRequest) throws CustomException {
        System.out.println("orderRequest" + orderRequest.getOrderDetails());
        orderRequest.getOrderDetails().forEach(a -> {
            System.out.println(a.getQuantity());
        });
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setData(orderRequest);

        orderEntity = orderRepository.save(orderEntity);

        List<OrderDetailRequest> list = orderRequest.getOrderDetails();
        for (OrderDetailRequest orderDetailRequest : list) {
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setData(orderDetailRequest);
            orderDetailEntity.setOrder(orderEntity);
            orderDetailRepository.save(orderDetailEntity);
        }
        return orderEntity;
    }

    @Override
    public OrderEntity edit(Long id, OrderRequest orderRequest) throws CustomException {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id đơn hàng phải lớn hơn 0");
        }
        if (orderEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id đơn hàng muốn sửa");
        }
        OrderEntity orderEntity = orderEntityOptional.get();
        orderEntity.setData(orderRequest);
        orderEntity = orderRepository.save(orderEntity);
        String status = String.valueOf(orderEntity.getStatus());
        List<OrderDetailEntity> list = this.orderDetailRepository.getOrderDetailEntityById(orderEntity.getId());
        Long orderId = orderEntity.getId();
        list.forEach(
                list1 -> {
                    list1.setId(list1.getId());
                    list1.setTotal(list1.getTotal());
                    list1.setProduct(list1.getProduct());
                    list1.setStatus(OrderDetailStatus.valueOf(status));
                    list1.setQuantity(list1.getQuantity());
                    OrderEntity order = orderRepository.getById(orderId);
                    list1.setOrder(order);
                });
        this.orderDetailRepository.saveAll(list);
        return orderEntity;
    }

    @Override
    public OrderEntity delete(Long id) throws CustomException {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
        if (orderEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy đơn hàng");
        }
        OrderEntity orderEntity = orderRepository.getById(id);
        orderRepository.delete(orderEntity);
        return orderEntity;
    }

    @Override
    public Page<OrderEntity> paginate(int page, int limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Specification<OrderEntity> specifications = OrderSpecifications.getInstance().getQueryResult(filters);

        return orderRepository.findAll(specifications, pageable);
    }

    @Override
    public UserEntity createUser(CreateUserOnOrderRequest createUserOnOrderRequest) throws CustomException {
        List<UserEntity> userEntityList = userRepository.findAll();
        for (UserEntity userEntity : userEntityList) {
            if (createUserOnOrderRequest.getUsername().equals(userEntity.getUsername())) {
                throw new CustomException(403, "Tài khoản đã tồn tại!");
            }
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(createUserOnOrderRequest.getUsername());
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        userEntity.setPassword(b.encode(createUserOnOrderRequest.getNewPassword()));
        userEntity.setStatus(1);
        List<RoleEntity> roleEntityList = new ArrayList<>();
        RoleEntity roleEntity = userRepository.findRoleCustomer();
        roleEntityList.add(roleEntity);
        userEntity.setRoles(roleEntityList);
        userEntity = userRepository.save(userEntity);
        InformationEntity informationEntity = new InformationEntity();
        informationEntity.setFullName(createUserOnOrderRequest.getFullName());
        informationEntity.setEmail(createUserOnOrderRequest.getEmail());
        informationEntity.setPhoneNumber(createUserOnOrderRequest.getPhoneNumber());
        informationEntity.setAddress(createUserOnOrderRequest.getAddress());
        informationEntity.setUser(userEntity);
        informationEntity.setActive(1);
        informationRepository.save(informationEntity);
        return userEntity;
    }

    @Override
    public List<OrderDetailEntity> findByOrder(Long id) {
        return orderDetailRepository.findByOrder(id);
    }

    @Override
    public OrderEntity cancelled(Long id) throws CustomException {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "ID hóa đơn phải lớn hơn 0");
        }
        if (orderEntityOptional.isEmpty()) {
            throw new CustomException(403, "Không tìm thấy id hóa đơn muốn hủy");
        }
        OrderEntity order = orderEntityOptional.get();
        order.setStatus(OrderStatus.DA_HUY);
        order = orderRepository.save(order);
        return order;
    }

    @Override
    public OrderEntity received(Long id) throws CustomException {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "ID hóa đơn phải lớn hơn 0");
        }
        if (orderEntityOptional.isEmpty()) {
            throw new CustomException(403, "Không tìm thấy id hóa đơn muốn hủy");
        }
        OrderEntity order = orderEntityOptional.get();
        order.setStatus(OrderStatus.DA_NHAN);
        order = orderRepository.save(order);
        return order;
    }
}
