package datnnhom12api.service.impl;

import datnnhom12api.entity.CartEntity;
import datnnhom12api.entity.ProductEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.CartRepository;
import datnnhom12api.repository.ProductRepository;
import datnnhom12api.request.CartRequest;
import datnnhom12api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("cartService")
@Transactional(rollbackFor = Throwable.class)
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public CartEntity save(CartRequest cartRequest) throws CustomException {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setData(cartRequest);
        ProductEntity productEntity = productRepository.findById(cartRequest.getProductId()).get();
        cartEntity.setName(productEntity.getName());
        cartEntity.setPrice(productEntity.getPrice());
        cartEntity.setTotal(productEntity.getPrice() * cartRequest.getQuantity());
        cartEntity = cartRepository.save(cartEntity);
        return cartEntity;
    }

    @Override
    public CartEntity edit(Long id, CartRequest cartRequest) throws CustomException {
        Optional<CartEntity> cartEntityOptional = cartRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id giỏ hàng phải lớn hơn 0");
        }
        if (cartEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id giỏ hàng muốn sửa");
        }
        CartEntity cartEntity = cartEntityOptional.get();
        cartEntity.setData(cartRequest);
        ProductEntity productEntity = productRepository.findById(cartRequest.getProductId()).get();
        cartEntity.setName(productEntity.getName());
        cartEntity.setPrice(productEntity.getPrice());
        cartEntity.setTotal(productEntity.getPrice() * cartRequest.getQuantity());
        cartEntity = cartRepository.save(cartEntity);
        return cartEntity;
    }

    @Override
    public CartEntity delete(Long id) throws CustomException {
        Optional<CartEntity> cartEntityOptional = cartRepository.findById(id);
        if (cartEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy đối tượng");
        }
        CartEntity cartEntity = cartRepository.getById(id);
        cartRepository.delete(cartEntity);
        return cartEntity;
    }

    @Override
    public List<CartEntity> paginate() {
        List<CartEntity> cartEntityList = cartRepository.findAll();
        return cartEntityList;
    }
}
