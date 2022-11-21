package datnnhom12api.service.impl;


import datnnhom12api.core.Filter;
import datnnhom12api.dto.ImageDTO;
import datnnhom12api.dto.ProductDTO;
import datnnhom12api.entity.*;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.*;
import datnnhom12api.request.ImageRequest;
import datnnhom12api.request.OrderDetailRequest;
import datnnhom12api.request.ProductRequest;
import datnnhom12api.service.ProductService;
import datnnhom12api.specifications.ProductSpecifications;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Autowired
    ManufactureRepository manufactureRepository;

    @Autowired
    ImageRepository imageRepository;

    @Override
    public ProductEntity insert(ProductRequest productRequest) throws CustomException {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setData(productRequest);
//        List<ProductPropertyEntity> productPropertyEntity = productRequest.getProductProperties();
        if(productEntity.getId()!=null) {
            imageRepository.deleteAllByProductId(productEntity.getId());
        }
        List<ImageRequest> list = productRequest.getImages();
        for (ImageRequest imageRequest:list){
            ImagesEntity imagesEntity=new ImagesEntity();
            imagesEntity.setData(imageRequest);
            imagesEntity.setProduct(productEntity);
            imageRepository.save(imagesEntity);
        }
        CategoryEntity category = this.categoryRepository.getById(productRequest.getCategoryId());
        productEntity.setCategoryId(productRequest.getCategoryId());
        ManufactureEntity manufacture = this.manufactureRepository.getById(productRequest.getManufactureId());
        productEntity.setManufactureId(productRequest.getManufactureId());
        ConfigurationEntity configuration =new ConfigurationEntity();
        configuration.setCapacity(productRequest.getConfiguration().getCapacity());
        configuration.setOptical(productRequest.getConfiguration().getOptical());
        configuration.setRam(productRequest.getConfiguration().getRam());
        configuration.setSlot(productRequest.getConfiguration().getSlot());
        configuration.setWin(productRequest.getConfiguration().getWin());
        configuration.setHard_drive(productRequest.getConfiguration().getHard_drive());
        configuration.setProcessor(productRequest.getConfiguration().getProcessor());
        configuration.setBattery(productRequest.getConfiguration().getBattery());
        configuration.setScreen(productRequest.getConfiguration().getScreen());
        configuration.setSecurity(productRequest.getConfiguration().getSecurity());
        configuration.setProduct(productEntity);
        productEntity = productRepository.save(productEntity);
        Long id = productEntity.getId();
//        productPropertyEntity.stream().forEach(productPropertyEntity1 -> {
//            ProductEntity product = productRepository.getById(id);
//            productPropertyEntity1.setProduct(product);
//        });
//        configuration.setProduct(id);
        this.configurationRepository.save(configuration);
//        this.productPropertyRepository.saveAll(productPropertyEntity);
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
        productEntity.setData(productRequest);
//        if(productEntity.getId()!=null) {
//            imageRepository.deleteAllByProductId(productEntity.getId());
//        }
        List<ImageRequest> list = productRequest.getImages();
        for (ImageRequest imageRequest:list){
            ImagesEntity imagesEntity=new ImagesEntity();
            imagesEntity.setData(imageRequest);
            imagesEntity.setProduct(productEntity);
            imageRepository.save(imagesEntity);
        }
        CategoryEntity category = this.categoryRepository.getById(productRequest.getCategoryId());
        productEntity.setCategoryId(productRequest.getCategoryId());
        ManufactureEntity manufacture = this.manufactureRepository.getById(productRequest.getManufactureId());
        productEntity.setManufactureId(productRequest.getManufactureId());
        ConfigurationEntity configuration =new ConfigurationEntity();
        System.out.println(productRequest.toString());
        configuration.setCapacity(productRequest.getConfiguration().getCapacity());
        configuration.setOptical(productRequest.getConfiguration().getOptical());
        configuration.setRam(productRequest.getConfiguration().getRam());
        configuration.setSlot(productRequest.getConfiguration().getSlot());
        configuration.setWin(productRequest.getConfiguration().getWin());
        configuration.setHard_drive(productRequest.getConfiguration().getHard_drive());
        configuration.setProcessor(productRequest.getConfiguration().getProcessor());
        configuration.setBattery(productRequest.getConfiguration().getBattery());
        configuration.setScreen(productRequest.getConfiguration().getScreen());
        configuration.setSecurity(productRequest.getConfiguration().getSecurity());
        configuration.setProduct(productEntity);
        productEntity = productRepository.save(productEntity);
        this.configurationRepository.save(configuration);
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

    @Override
    public ProductDTO findById(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        ProductEntity productEntity = this.productRepository.getById(id);
        this.enrichImage(productEntity);
        ProductDTO productDTO = modelMapper.map(productEntity, ProductDTO.class);
        return productDTO;
    }

    private void enrichImage(ProductEntity productEntity) {
        List<ImagesEntity> imagesEntities = this.imageRepository.findAllByProductId(productEntity.getId());
        ConfigurationEntity configuration = this.configurationRepository.findByProductId(productEntity.getId());
        productEntity.enrichConfiguration(configuration);
        productEntity.enrichListImage(imagesEntities);
    }
}
