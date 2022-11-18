package datnnhom12api.mapper;

import datnnhom12api.dto.OrderDTO;
import datnnhom12api.dto.OrderDetailDTO;
import datnnhom12api.entity.OrderDetailEntity;
import datnnhom12api.entity.OrderEntity;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ExchangeMapper {
    private static ExchangeMapper INSTANCE;

    public static ExchangeMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ExchangeMapper();
        }

        return INSTANCE;
    }

    public ExchangeMapper() {
    }

    public static OrderDetailDTO toDTO(OrderDetailEntity orderEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(orderEntity, OrderDetailDTO.class);
    }

    public static List<OrderDetailDTO> toListDTO(List<OrderDetailEntity> entityList) {
        List<OrderDetailDTO> list = new ArrayList<>();
        for (OrderDetailEntity e : entityList) {
            list.add(ExchangeMapper.toDTO(e));
        }
        return list;
    }
}
