package datnnhom12api.service;

import datnnhom12api.entity.CartEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.CartRequest;

import java.util.List;

public interface CartService {

    CartEntity save(CartRequest cart) throws CustomException;

    CartEntity edit(Long id, CartRequest cart) throws CustomException;

    CartEntity delete(Long id) throws CustomException;

    List<CartEntity> paginate();
}
