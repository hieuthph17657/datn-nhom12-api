package datnnhom12api.service.impl;


import datnnhom12api.core.Filter;
import datnnhom12api.dto.ProductDTO;
import datnnhom12api.dto.SumProductDTO;
import datnnhom12api.entity.*;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.*;
import datnnhom12api.request.ImageRequest;
import datnnhom12api.request.ProductRequest;
import datnnhom12api.service.ProductService;
import datnnhom12api.specifications.ProductSpecifications;
import datnnhom12api.utils.support.ProductStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.*;

@Service("productService")
@Transactional(rollbackFor = Throwable.class)
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    DiscountRepository discountRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ManufactureRepository manufactureRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    AccessoryProductRepository accessoryProductRepository;

    @Autowired
    ProductColorRepository productColorRepository;

    @Autowired
    ColorRepository colorRepository;

    @Autowired
    AccessoryRepository accessoryRepository;

    @Autowired
    OriginRepository originRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BatteryChargerRepository batteryChargerRepository;

    @Autowired
    ProcessorRepository processorRepository;

    @Autowired
    RamRepository ramRepository;

    @Autowired
    ScreenRepository screenRepository;

    @Autowired
    StorageRepository storageRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    YearRepository yearRepository;

    @Override
    public ProductEntity insert(ProductRequest productRequest) throws CustomException {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setData(productRequest);
        productEntity.setOrigin(this.originRepository.getById(productRequest.getOriginId()));
        productEntity.setScreen(this.screenRepository.getById(productRequest.getScreenId()));
        productEntity.setStorage(this.storageRepository.getById(productRequest.getStorageId()));
        productEntity.setRam(this.ramRepository.getById(productRequest.getRamId()));
        productEntity.setProcessor(this.processorRepository.getById(productRequest.getProcessorId()));
        productEntity.setCard(this.cardRepository.getById(productRequest.getCardId()));
        productEntity.setCardOnboard(this.cardRepository.getById(productRequest.getCardOnboard()));
        productEntity.setBattery(this.batteryChargerRepository.getById(productRequest.getBatteryId()));
        List<ImageRequest> list = productRequest.getImages();
        for (ImageRequest imageRequest : list) {
            ImagesEntity imagesEntity = new ImagesEntity();
            imagesEntity.setData(imageRequest);
            imagesEntity.setProduct(productEntity);
            imageRepository.save(imagesEntity);
        }
        ManufactureEntity manufacture = this.manufactureRepository.getById(productRequest.getManufactureId());
        productEntity.setManufacture(manufacture);
        productEntity = productRepository.save(productEntity);
        Long id = productEntity.getId();
        productRequest.getAccessoryId().forEach(access -> {
            AccessoryProductEntity accessoryProductEntity = new AccessoryProductEntity();
            accessoryProductEntity.setProduct(productRepository.getById(id));
            accessoryProductEntity.setAccessory(accessoryRepository.getById(access));
            this.accessoryProductRepository.save(accessoryProductEntity);
        });
        productRequest.getColorId().forEach(color -> {
            ProductColorEntity pColorEntity = new ProductColorEntity();
            pColorEntity.setColor(colorRepository.getById(color));
            pColorEntity.setProduct(productRepository.getById(id));
            this.productColorRepository.save(pColorEntity);
        });

        productRequest.getCategoryId().forEach(cate -> {
            ProductCategoryEntity productCategory = new ProductCategoryEntity();
            productCategory.setProduct(productRepository.getById(id));
            productCategory.setCategory(categoryRepository.getById(cate));
            this.productCategoryRepository.save(productCategory);
        });
        YearEntity year = new YearEntity();
        int yearCurrent = Year.now().getValue();
        year.setProduct(this.productRepository.getById(id));
        year.setPrice(productRequest.getPrice());
        year.setYear(String.valueOf(yearCurrent));
        this.yearRepository.save(year);
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
        if (productEntity.getId() != null) {
            imageRepository.deleteAllByProductId(productEntity.getId());
        }
        List<ImageRequest> list = productRequest.getImages();
        for (ImageRequest imageRequest : list) {
            ImagesEntity imagesEntity = new ImagesEntity();
            imagesEntity.setData(imageRequest);
            imagesEntity.setProduct(productEntity);
            imageRepository.save(imagesEntity);
        }

        ManufactureEntity manufacture = this.manufactureRepository.getById(productRequest.getManufactureId());
        productEntity.setManufacture(manufacture);
        productEntity = productRepository.save(productEntity);
        productColorRepository.deleteAllProductColorByProductId(id);
        List<Long> longList = productRequest.getColorId();
        for (Long color : longList) {
            ProductColorEntity pColorEntity = new ProductColorEntity();
            pColorEntity.setColor(colorRepository.getById(color));
            pColorEntity.setProduct(productRepository.getById(id));
            this.productColorRepository.save(pColorEntity);
        }
        accessoryProductRepository.deleteAllAccessoryProductByProductId(id);
        List<Long> longs = productRequest.getAccessoryId();
        for (Long access : longs) {
            AccessoryProductEntity accessoryProductEntity = new AccessoryProductEntity();
            accessoryProductEntity.setAccessory(accessoryRepository.getById(access));
            accessoryProductEntity.setProduct(productRepository.getById(id));
            this.accessoryProductRepository.save(accessoryProductEntity);
        }
        return productEntity;
    }

    @Override
    public ProductEntity delete(Long id) throws CustomException {
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

    @Override
    public List<ProductEntity> discount(Long id, List<Long> idProduct) throws CustomException {
        Optional<DiscountEntity> discountEntityOptional = discountRepository.findById(id);
        DiscountEntity discountEntity = discountEntityOptional.get();
        List<ProductEntity> list = productRepository.findAll();
        ProductEntity productEntity = null;
        List<ProductEntity> listdiscountProduct = new ArrayList<>();
        for (ProductEntity product : list) {
            for (Long iP : idProduct) {
                if (product.getId() == iP) {
                    Optional<ProductEntity> productEntityOptional = productRepository.findById(iP);
                    if (id <= 0) {
                        throw new CustomException(403, "Mã sản phẩm phải lớn hơn 0");
                    }
                    if (productEntityOptional.isEmpty()) {
                        throw new CustomException(403, "Không tìm thấy mã sản phẩm muốn sửa");
                    }
                    productEntity = productEntityOptional.get();
                    productEntity.setDiscount(discountEntity);
//                    productEntity.setPrice(productEntity.getPrice() - (productEntity.getPrice() * discountEntity.getRatio() / 100));
                    productEntity = productRepository.save(productEntity);
                    listdiscountProduct.add(productEntity);
                }
            }

        }
        return listdiscountProduct;
    }

//    @Override
//    public List<ProductEntity> noDiscount(Long id, List<Long> idProduct) throws CustomException {
//        Optional<DiscountEntity> discountEntityOptional = discountRepository.findById(id);
//        DiscountEntity discountEntity = discountEntityOptional.get();
//        List<ProductEntity> list = productRepository.findAll();
//        ProductEntity productEntity = null ;
//        List<ProductEntity> listdiscountProduct = new ArrayList<>();
//        for (ProductEntity product:list){
//            for (Long iP: idProduct){
//                if (product.getId() == iP){
//                    Optional<ProductEntity> productEntityOptional = productRepository.findById(iP);
//                    if (id <= 0) {
//                        throw new CustomException(403, "Mã sản phẩm phải lớn hơn 0");
//                    }
//                    if (productEntityOptional.isEmpty()) {
//                        throw new CustomException(403, "Không tìm thấy mã sản phẩm muốn sửa");
//                    }
//                    productEntity = productEntityOptional.get();
//                    productEntity.setDiscount(null);
//                    productEntity.setPrice(productEntity.getPrice() + (productEntity.getPrice() * discountEntity.getRatio() / 100));
//                    productEntity = productRepository.save(productEntity);
//                    listdiscountProduct.add(productEntity);
//                }
//            }
//
//        }
//        return listdiscountProduct;
//    }

    private void enrichImage(ProductEntity productEntity) {
        List<ImagesEntity> imagesEntities = this.imageRepository.findAllByProductId(productEntity.getId());
        productEntity.enrichListImage(imagesEntities);
    }

    @Override
    public ProductEntity active(Long id) throws CustomException {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id sản phẩm phải lớn hơn 0");
        }
        if (productEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id sản phẩm muốn active");
        }
        ProductEntity productEntity = productEntityOptional.get();
        productEntity.setStatus(ProductStatus.ACTIVE);
        productEntity = productRepository.save(productEntity);
        return productEntity;
    }

    @Override
    public ProductEntity inActive(Long id) throws CustomException {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id sản phẩm phải lớn hơn 0");
        }
        if (productEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id sản phẩm muốn active");
        }
        ProductEntity productEntity = productEntityOptional.get();
        productEntity.setStatus(ProductStatus.INACTIVE);
        productEntity = productRepository.save(productEntity);
        return productEntity;
    }

    @Override
    public SumProductDTO sumProduct() {
        SumProductDTO productDTO = this.productRepository.sumProduct();
        return productDTO;
    }
}
