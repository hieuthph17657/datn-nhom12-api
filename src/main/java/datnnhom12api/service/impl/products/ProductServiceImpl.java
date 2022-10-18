package datnnhom12api.service.impl.products;

import datnnhom12api.dto.core.Filter;
import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.entity.products.ConfigurationEntity;
import datnnhom12api.entity.products.ProductEntity;
import datnnhom12api.entity.products.ProductPropertyEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.CategoryRepository;
import datnnhom12api.repository.ConfigurationRepository;
import datnnhom12api.repository.products.ProductPropertyRepository;
import datnnhom12api.repository.products.ProductRepository;
import datnnhom12api.request.products.ProductRequest;
import datnnhom12api.service.products.ProductService;
import datnnhom12api.specifications.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("productService")
@Transactional(rollbackFor = Throwable.class)
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductPropertyRepository productPropertyRepository;

    @Autowired
    ConfigurationRepository configurationRepository;

    @Override
    public ProductEntity insert(ProductRequest productRequest) throws CustomException {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setData(productRequest);
        List<ProductPropertyEntity> productPropertyEntity = productRequest.getProductProperties();
        CategoryEntity category = this.categoryRepository.getById(productRequest.getCategoryId());
        productEntity.setCategory(category);
        ConfigurationEntity configuration =new ConfigurationEntity();
        configuration.setCapacity(productRequest.getConfigurationEntity().getCapacity());
        configuration.setOptical(productRequest.getConfigurationEntity().getOptical());
        configuration.setRam(productRequest.getConfigurationEntity().getRam());
        configuration.setSlot(productRequest.getConfigurationEntity().getSlot());
        configuration.setWin(productRequest.getConfigurationEntity().getWin());
        configuration.setHard_drive(productRequest.getConfigurationEntity().getHard_drive());
        configuration.setProcessor(productRequest.getConfigurationEntity().getProcessor());
        configuration.setBattery(productRequest.getConfigurationEntity().getBattery());
        configuration.setScreen(productRequest.getConfigurationEntity().getScreen());
        configuration.setSecutity(productRequest.getConfigurationEntity().getSecutity());
        productEntity = productRepository.save(productEntity);
        Long id = productEntity.getId();
        productPropertyEntity.stream().forEach(productPropertyEntity1 -> {
            ProductEntity product = productRepository.getById(id);
            productPropertyEntity1.setProduct(product);
        });
        configuration.setProduct(id);
        this.configurationRepository.save(configuration);
        this.productPropertyRepository.saveAll(productPropertyEntity);
        return productEntity;
    }

    @Override
    public ProductEntity update(Long id, ProductRequest productRequest) throws CustomException {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "Mã sản phẩm phải lớn hơn 0");
        }
        if (productEntityOptional.isEmpty()) {
            throw new CustomException(403, "Không tìm thấy mã sản phẩm muốn sửa");
        }
        ProductEntity productEntity = productEntityOptional.get();
        List<ProductPropertyEntity>productPropertyEntityList = productRequest.getProductProperties();
        productEntity.setData(productRequest);
        productEntity = productRepository.save(productEntity);
        Long productId = productEntity.getId();
        productPropertyEntityList.forEach(productPropertyEntity -> {
            System.out.println(productPropertyEntity.getPropertyName());
        });
        productPropertyEntityList.stream().forEach(productPropertyEntity1 -> {
            ProductEntity product = productRepository.getById(productId);
            productPropertyEntity1.setProduct(product);
        });
        this.productPropertyRepository.saveAll(productPropertyEntityList);
        return productEntity;
    }

    @Override
    public ProductEntity delete(Long id) throws CustomException{
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        if (productEntityOptional.isEmpty()) {
            throw new CustomException(403, "Không tìm thấy sản phẩm");
        }
        ProductEntity productEntity = productRepository.getById(id);
        productRepository.delete(productEntity);
        return productEntity;
    }

    @Override
    public Page<ProductEntity> paginate(int page, int limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);
        Specification<ProductEntity> specifications = ProductSpecifications.getInstance().getQueryResult(filters);
        return productRepository.findAll(specifications, pageable);
    }

    @Override
    public ProductEntity create(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }
}
