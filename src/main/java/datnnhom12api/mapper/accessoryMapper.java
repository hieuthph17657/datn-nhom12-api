package datnnhom12api.mapper;

import datnnhom12api.dto.accessoryDTO;
import datnnhom12api.entity.accessoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class accessoryMapper {
    private static accessoryMapper INSTANCE;

    public static accessoryMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new accessoryMapper();
        }

        return INSTANCE;
    }

    public accessoryMapper() {
    }

    public static accessoryDTO toDTO(accessoryEntity accessory) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(accessory, accessoryDTO.class);
    }

    public static accessoryEntity toEntity(accessoryMapper accessoryMapper) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(accessoryMapper, accessoryEntity.class);
    }

    public static Page<accessoryDTO> toPageDTO(Page<accessoryEntity> page) {
        return page.map(new Function<>() {
            @Override
            public accessoryDTO apply(accessoryEntity entity) {
                return accessoryMapper.toDTO(entity);
            }
        });
    }

    public static List<accessoryDTO> toListDTO(List<accessoryEntity> entityList) {
        List<accessoryDTO> list = new ArrayList<>();
        for (accessoryEntity e : entityList) {
            list.add(accessoryMapper.toDTO(e));
        }
        return list;
    }
}
