package datnnhom12api.mapper;

import datnnhom12api.dto.OrderDetailDTO;
import datnnhom12api.entity.OrderDetailEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class OrderDetailMapper {
    private static OrderDetailMapper INSTANCE;
    public static OrderDetailMapper getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new OrderDetailMapper();
        }
        return INSTANCE;
    }

    public OrderDetailMapper(){
    }

    public static OrderDetailDTO toDTO(OrderDetailEntity orderDetailEntity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(orderDetailEntity, OrderDetailDTO.class);
    }

    public static OrderDetailEntity toEntity(OrderDetailMapper orderDetailDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(orderDetailDTO, OrderDetailEntity.class);
    }

    public static Page<OrderDetailDTO> toPageDTO(Page<OrderDetailEntity> page){
        return page.map(new Function<>() {
            @Override
            public OrderDetailDTO apply(OrderDetailEntity entity){
                return OrderDetailMapper.toDTO(entity);
            }
        });
    }

    public static List<OrderDetailDTO> toListDTO(List<OrderDetailEntity> entityList){
        List<OrderDetailDTO> list = new ArrayList<>();
        for (OrderDetailEntity e: entityList){
            list.add(OrderDetailMapper.toDTO(e));
        }
        return list;
    }
}
