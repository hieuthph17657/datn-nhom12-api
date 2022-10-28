package datnnhom12api.mapper;

import datnnhom12api.dto.CartDTO;
import datnnhom12api.entity.CartEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CartMapper {
    private static CartMapper INSTANCE;

    public static CartMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CartMapper();
        }

        return INSTANCE;
    }

    public CartMapper() {
    }

    public static CartDTO toDTO(CartEntity cartEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cartEntity, CartDTO.class);
    }

    public static CartEntity toEntity(CartEntity cartDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cartDTO, CartEntity.class);
    }

    public static Page<CartDTO> toPageDTO(Page<CartEntity> page) {
        return page.map(new Function<>() {
            @Override
            public CartDTO apply(CartEntity entity) {
                return CartMapper.toDTO(entity);
            }
        });
    }

    public static List<CartDTO> toListDTO(List<CartEntity> entityList) {
        List<CartDTO> list = new ArrayList<>();
        for (CartEntity e : entityList) {
            list.add(CartMapper.toDTO(e));
        }
        return list;
    }
}
