package datnnhom12api.controller.web;

import datnnhom12api.entity.CartEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.CartMapper;
import datnnhom12api.repository.ProductRepository;
import datnnhom12api.request.CartRequest;
import datnnhom12api.response.CartResponse;
import datnnhom12api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping
    public CartResponse index() {
        List<CartEntity> cartEntityList = cartService.paginate();
        return new CartResponse(CartMapper.toListDTO(cartEntityList));
    }

    @PostMapping()
    public CartResponse create(@Valid @RequestBody CartRequest cart, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        CartEntity cartEntity = cartService.save(cart);
        return new CartResponse(CartMapper.getInstance().toDTO(cartEntity));
    }

    @PutMapping("/{id}")
    public CartResponse edit(@PathVariable("id") Long id, @Valid @RequestBody CartRequest cart, BindingResult bindingResult) throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        CartEntity cartEntity = cartService.edit(id, cart);
        return new CartResponse(CartMapper.getInstance().toDTO(cartEntity));
    }

    @DeleteMapping("/{id}")
    public CartResponse delete(@PathVariable("id") Long id) throws CustomException {
        cartService.delete(id);
        return new CartResponse();
    }
}
