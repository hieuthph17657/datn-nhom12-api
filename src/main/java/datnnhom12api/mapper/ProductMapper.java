package datnnhom12api.mapper;

import datnnhom12api.dto.ProductDTO;
import datnnhom12api.entity.products.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class  ProductMapper {
    private static ProductMapper INSTANCE;

    public static ProductMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProductMapper();
        }

        return INSTANCE;
    }

    public ProductMapper() {
    }

    public static ProductDTO toDTO(ProductEntity product) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(product, ProductDTO.class);
    }

    public static ProductEntity toEntity(ProductMapper productDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(productDTO, ProductEntity.class);
    }

    public static Page<ProductDTO> toPageDTO(Page<ProductEntity> page) {
        return page.map(new Function<>() {
            @Override
            public ProductDTO apply(ProductEntity entity) {
                return ProductMapper.toDTO(entity);
            }
        });
    }

    public static List<ProductDTO> toListDTO(List<ProductEntity> entityList) {
        List<ProductDTO> list = new ArrayList<>();
        for (ProductEntity e : entityList) {
            list.add(ProductMapper.toDTO(e));
        }
        return list;
    }
}
