package datnnhom12api.controller.admin;

import datnnhom12api.dto.ProductDTO;
import datnnhom12api.dto.ProductDTOById;
import datnnhom12api.entity.DiscountEntity;
import datnnhom12api.entity.ProductEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.DiscountMapper;
import datnnhom12api.mapper.ProductMapper;
import datnnhom12api.paginationrequest.ProductPaginationRequest;
import datnnhom12api.repository.ProductRepository;
import datnnhom12api.request.DiscountRequest;
import datnnhom12api.request.ProductRequest;
import datnnhom12api.response.DiscountResponse;
import datnnhom12api.response.ProductDiscountResponse;
import datnnhom12api.response.ProductFilterResponse;
import datnnhom12api.response.ProductResponse;
import datnnhom12api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public ProductResponse index(@Valid ProductPaginationRequest request, BindingResult bindingResult)
            throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<ProductEntity> page = productService.paginate(
                request.getPage(), request.getLimit(), request.getFilters(), request.getSearchProductKey(), request.getSearchImei(), request.getSearchStatus(), request.getSearchPrice(), request.getOrders());
        return new ProductResponse(ProductMapper.toPageDTO(page));
    }

    @GetMapping("/all")
    public ProductResponse getAll(){
        List<ProductEntity> product = this.productRepository.findAll();
        return new ProductResponse(ProductMapper.toListDTO(product));
    }

    @GetMapping("/getAllProAccess")
    public ProductFilterResponse getAllProAccess(@Valid ProductPaginationRequest request, BindingResult bindingResult)
            throws CustomValidationException {
            if (bindingResult.hasErrors()) {
                throw new CustomValidationException(bindingResult.getAllErrors());
            }
            Page<ProductEntity> page = productService.paginate(
                    request.getPage(), request.getLimit(), request.getFilters(), request.getSearchProductKey(), request.getSearchImei(), request.getSearchStatus(), request.getSearchPrice(), request.getOrders());
            return new ProductFilterResponse(ProductMapper.toPageDTOFilter(page));
        }

    @GetMapping("/allProDiscount")
    public ProductDiscountResponse getAllProDiscount(){
        List<ProductEntity> product = this.productRepository.findAll();
        return new ProductDiscountResponse(ProductMapper.getInstance().toListProDiscountDTO(product));
    }
    @GetMapping("/allProWithDiscount")
    public ProductResponse getAllProWithDiscount(){
        List<ProductEntity> product = this.productRepository.getProductWithDiscount();
        return new ProductResponse(ProductMapper.getInstance().toListDTO(product));
    }

    @GetMapping("/{id}")
    public ProductDTOById getById(@PathVariable Long id) {
        ProductDTOById product = this.productService.findById(id);
        return product;
    }

    @PostMapping()
    public ProductResponse create(
            @Valid @RequestBody ProductRequest productRequest,
            BindingResult bindingResult)
            throws CustomException,
            CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ProductEntity productEntity = productService.insert(productRequest);
        return new ProductResponse(ProductMapper.getInstance().toDTO(productEntity));
    }

    @PutMapping("/discountProduct/{id}")
    public ProductResponse discount(
            @PathVariable("id") Long id,
            @Valid @RequestBody List<Long> idProduct,
            BindingResult bindingResult
    ) throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        List<ProductEntity> productEntity = productService.discount(id, idProduct);
        return new ProductResponse(ProductMapper.getInstance().toListDTO(productEntity));
    }

    @PutMapping("/noDiscountProduct/{id}/{idPro}")
    public  ProductResponse nodiscount(
            @PathVariable("id") Long id,
            @PathVariable("idPro") Long idPro
    ) throws CustomValidationException, CustomException {
        ProductEntity productEntity = productService.noDiscount(id,idPro );
        return new ProductResponse(ProductMapper.getInstance().toDTO(productEntity));
    }


    @PutMapping("/{id}")
    public ProductResponse edit(
            @PathVariable("id") Long id,
            @Valid @RequestBody ProductRequest productRequest,
            BindingResult bindingResult) throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ProductEntity productEntity = productService.update(id, productRequest);
        return new ProductResponse(ProductMapper.getInstance().toDTO(productEntity));
    }

    @PutMapping("/active/{id}")
    public ProductResponse active(@PathVariable("id") Long id) throws CustomValidationException, CustomException {
        ProductEntity postEntity = productService.active(id);
        return new ProductResponse(ProductMapper.getInstance().toDTO(postEntity));
    }

    @PutMapping("/inactive/{id}")
    public ProductResponse inActive(@PathVariable("id") Long id) throws CustomValidationException, CustomException {
        ProductEntity postEntity = productService.inActive(id);
        return new ProductResponse(ProductMapper.getInstance().toDTO(postEntity));
    }

    @PostMapping("/copy/{productId}")
    public ProductResponse copyProduct(
            @Valid @RequestBody ProductRequest productRequest,
            BindingResult bindingResult, @PathVariable("productId") Long productId)
            throws CustomException,
            CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ProductEntity productEntity = productService.copyProduct(productRequest, productId);
        return new ProductResponse(ProductMapper.getInstance().toDTO(productEntity));
    }
}
