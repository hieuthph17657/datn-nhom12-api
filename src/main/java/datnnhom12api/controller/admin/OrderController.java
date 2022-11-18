package datnnhom12api.controller.admin;

import datnnhom12api.dto.OrderDetailDTO;
import datnnhom12api.dto.UpdateOrderDetailDTO;
import datnnhom12api.entity.OrderDetailEntity;
import datnnhom12api.entity.OrderEntity;
import datnnhom12api.entity.UserEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.ExchangeMapper;
import datnnhom12api.mapper.OrderMapper;
import datnnhom12api.mapper.UserMapper;
import datnnhom12api.paginationrequest.OrderPaginationRequest;
import datnnhom12api.request.*;
import datnnhom12api.response.ExchangeResponse;
import datnnhom12api.response.OrderResponse;
import datnnhom12api.response.UserResponse;
import datnnhom12api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public OrderResponse index(@Valid OrderPaginationRequest request, BindingResult bindingResult)
            throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<OrderEntity> page = orderService.paginate(request.getPage(), request.getLimit(),
                request.getFilters(), request.getOrders());
        return new OrderResponse(OrderMapper.toPageDTO(page));
    }

    @PostMapping()
    public OrderResponse create(@Valid @RequestBody OrderRequest orderRequest, OrderDetailRequest orderDetailRequest,
                                BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        OrderEntity orderEntity = orderService.save(orderRequest);
        return new OrderResponse(OrderMapper.getInstance().toDTO(orderEntity));
    }

    @PostMapping("exchanges")
    public ExchangeResponse createOrderDetail(@Valid @RequestBody List<ExchangeRequest> exchangeRequest,
                                              BindingResult bindingResult) throws CustomException, CustomValidationException {
        if(bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        List<OrderDetailEntity> orderDetailEntities = orderService.createOrderDetail(exchangeRequest);
        return new ExchangeResponse(ExchangeMapper.toListDTO(orderDetailEntities));
    }

    @PutMapping("/{id}")
    public OrderResponse edit(@PathVariable("id") Long id, @Valid @RequestBody OrderRequest orderRequest,
                              BindingResult bindingResult) throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        OrderEntity orderEntity = orderService.edit(id, orderRequest);
        return new OrderResponse(OrderMapper.getInstance().toDTO(orderEntity));
    }

    @PutMapping("/{id}/orderDetails")
    public OrderDetailDTO editOrderDetail(@PathVariable("id") Long id, @RequestBody OrderDetailRequest orderDetailRequest) {
        OrderDetailDTO orderDetailDTO = orderService.update(id, orderDetailRequest);
        return orderDetailDTO;
    }

    @DeleteMapping("/{id}")
    public OrderResponse delete(@PathVariable("id") Long id) throws CustomException {
        orderService.delete(id);
        return new OrderResponse();
    }

    @PostMapping("/createUser")
    public UserResponse createUser(@Valid @RequestBody CreateUserOnOrderRequest createUserOnOrderRequest,
                                   BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        UserEntity userEntity = orderService.createUser(createUserOnOrderRequest);
        return new UserResponse(UserMapper.getInstance().toDTO(userEntity));
    }

    @GetMapping("/{id}")
    public List<OrderDetailEntity> getOrderDetail(@PathVariable("id") Long id) throws CustomException {
        List<OrderDetailEntity> list = orderService.findByOrder(id);
        return list;
    }

    @PutMapping("/cancelled/{id}")
    public OrderResponse cancelled(@PathVariable("id") Long id) throws CustomException {
        OrderEntity order = orderService.cancelled(id);
        return new OrderResponse(OrderMapper.getInstance().toDTO(order));
    }

    @PutMapping("/received/{id}")
    public OrderResponse received(@PathVariable("id") Long id) throws CustomException {
        OrderEntity order = orderService.received(id);
        return new OrderResponse(OrderMapper.getInstance().toDTO(order));
    }

    @GetMapping("/get/{id}")
    public OrderResponse getOrder(@PathVariable("id") Long id) throws CustomException {
        OrderEntity order = orderService.findById(id);
        return new OrderResponse(OrderMapper.getInstance().toDTO(order));
    }

    @PutMapping("/{id}/updateReturn")
    public UpdateOrderDetailDTO updateOrderDetail(@PathVariable("id") Long id,
                                                  @RequestBody UpdateOrderDetailRequest orderDetailRequest) {
        UpdateOrderDetailDTO od = this.orderService.findByOrderDetailDTO(id, orderDetailRequest);
        return od;
    }

    @GetMapping("list/{username}")
    public List<OrderEntity> getByName(@PathVariable("username") String username) throws CustomException {
        List<OrderEntity> list = orderService.findUserName(username);
        return list;
    }

    @GetMapping("list/date/{createdAt}")
    public List<OrderEntity> findByDate(@PathVariable("createdAt") String createdAt) throws CustomException {
        List<OrderEntity> list = orderService.findByDate(createdAt);
        return list;
    }
}

