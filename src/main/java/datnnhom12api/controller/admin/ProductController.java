package datnnhom12api.controller.admin;

import datnnhom12api.entity.OrderEntity;
import datnnhom12api.entity.ProductEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.OrderMapper;
import datnnhom12api.mapper.ProductMapper;
import datnnhom12api.paginationrequest.OrderPaginationRequest;
import datnnhom12api.paginationrequest.ProductPaginationRequest;
import datnnhom12api.request.OrderRequest;
import datnnhom12api.request.ProductRequest;
import datnnhom12api.response.OrderResponse;
import datnnhom12api.response.ProductResponse;
import datnnhom12api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public ProductResponse index(@Valid ProductPaginationRequest request, BindingResult bindingResult) throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<ProductEntity> page = productService.paginate(request.getPage(), request.getLimit(), request.getFilters(), request.getOrders());
        return new ProductResponse(ProductMapper.toPageDTO(page));
    }

    @PostMapping()
    public ProductResponse create(@Valid @RequestBody ProductRequest productRequest, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ProductEntity productEntity = productService.save(productRequest);
        return new ProductResponse(ProductMapper.getInstance().toDTO(productEntity));
    }

    @PutMapping("/{id}")
    public ProductResponse edit(@PathVariable("id") Long id, @Valid @RequestBody ProductRequest productRequest, BindingResult bindingResult) throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ProductEntity productEntity = productService.edit(id, productRequest);
        return new ProductResponse(ProductMapper.getInstance().toDTO(productEntity));
    }

    @DeleteMapping("/{id}")
    public ProductResponse delete(@PathVariable("id") Long id) throws CustomException {
        productService.delete(id);
        return new ProductResponse();
    }
}
