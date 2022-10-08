package datnnhom12api.controller.admin;

import datnnhom12api.entity.OrderEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.OrderMapper;
import datnnhom12api.paginationrequest.OrderPaginationRequest;
import datnnhom12api.request.OrderRequest;
import datnnhom12api.response.OrderResponse;
import datnnhom12api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public OrderResponse index(@Valid OrderPaginationRequest request, BindingResult bindingResult) throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<OrderEntity> page = orderService.paginate(request.getPage(), request.getLimit(), request.getFilters(), request.getOrders());
        return new OrderResponse(OrderMapper.toPageDTO(page));
    }

    @PostMapping()
    public OrderResponse create(@Valid @RequestBody OrderRequest orderRequest, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        OrderEntity orderEntity = orderService.save(orderRequest);
        return new OrderResponse(OrderMapper.getInstance().toDTO(orderEntity));
    }

    @PutMapping("/{id}")
    public OrderResponse edit(@PathVariable("id") Long id, @Valid @RequestBody OrderRequest orderRequest, BindingResult bindingResult) throws CustomValidationException, CustomException {
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
}
