package datnnhom12api.controller.admin;

import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.entity.OrderDetailEntity;
import datnnhom12api.entity.OrderEntity;
import datnnhom12api.entity.UserEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.CategoryMapper;
import datnnhom12api.mapper.OrderMapper;
import datnnhom12api.mapper.UserMapper;
import datnnhom12api.paginationrequest.OrderPaginationRequest;
import datnnhom12api.request.*;
import datnnhom12api.response.CategoryResponse;
import datnnhom12api.response.OrderResponse;
import datnnhom12api.response.UserResponse;
import datnnhom12api.service.OrderService;
import datnnhom12api.service.UserService;
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

    @PutMapping("/{id}")
    public OrderResponse edit(@PathVariable("id") Long id, @Valid @RequestBody OrderRequest orderRequest,
                              BindingResult bindingResult) throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        OrderEntity orderEntity = orderService.edit(id, orderRequest);
        return new OrderResponse(OrderMapper.getInstance().toDTO(orderEntity));
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
}
