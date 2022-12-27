package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.dto.*;
import datnnhom12api.entity.ImagesEntity;
import datnnhom12api.entity.OrderDetailEntity;
import datnnhom12api.entity.OrderEntity;
import datnnhom12api.entity.UserEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface OrderService {

    OrderEntity save(OrderRequest order) throws CustomException;

    OrderEntity saveOfUser(OrderRequest order) throws CustomException;

    OrderEntity edit(Long id, OrderRequest order) throws CustomException;

    OrderEntity delete(Long id) throws CustomException;

    Page<OrderEntity> paginate(String searchStartDate, String searchEndDate, int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);

    UserEntity createUser(CreateUserOnOrderRequest createUserOnOrderRequest) throws CustomException;

    List<OrderDetailDTO> findByOrder(Long id);

    OrderByIdDTO findById(Long id);

    OrderEntity cancelled(Long id) throws CustomException;
    OrderEntity received(Long id) throws CustomException;

    OrderDetailDTO update(Long id, OrderDetailRequest orderDetailRequest);


    UpdateOrderDetailDTO findByOrderDetailDTO(Long id, UpdateOrderDetailRequest orderDetailRequest);

    List<OrderEntity> findUserName(String username);

    List<OrderEntity> findByDate(String createdAt);

    List<OrderDetailEntity> createOrderDetail(List<ExchangeRequest> exchangeRequest);

    OrderDetailDTO updateOrderDetail(Long id, OrderDetailRequest orderDetailRequest);

    UpdateOrderDetailDTO updateWithReturn(Long orderId, Long orderDetailId, UpdateOrderDetailRequest orderDetailRequest);

    List<OrderConfirmDTO> findByIdOrderId(List<OrderConfirmDTO> id);

    List<OrderExchangeDTO> updateWhenExchange(List<OrderExchangeDTO> request, Long orderId);

    List<StatisticalMonthDTO> statisticalByYear(Integer year);

    List<StatisticalOrderDTO> statisticalByOrder(Integer month, Integer year);

    List<StatisticalProductDTO> statisticalByProduct();

    SumProductDTO countOrder();

    ImageDTO addImageOrder(ImageOrderRequest request);

    OrderEntity updateNoteRequest(Long id, UpdateNoteRequest request);
}
