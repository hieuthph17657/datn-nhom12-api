package datnnhom12api.mapper;

import datnnhom12api.dto.ReturnDTO;
import datnnhom12api.entity.ReturnEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ReturnMapper {

    private static ReturnMapper INSTANCE;

    public static ReturnMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReturnMapper();
        }
        return INSTANCE;
    }

    public ReturnMapper() {
    }

    public static ReturnDTO toDTO(ReturnEntity returnEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(returnEntity, ReturnDTO.class);
    }

    public static ReturnEntity toEntity(ReturnMapper cartDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cartDTO, ReturnEntity.class);
    }

    public static Page<ReturnDTO> toPageDTO(Page<ReturnEntity> page) {
        return page.map(new Function<>() {
            @Override
            public ReturnDTO apply(ReturnEntity entity) {
                return ReturnMapper.toDTO(entity);
            }
        });
    }

    public static List<ReturnDTO> toListDTO(List<ReturnEntity> entityList) {
        List<ReturnDTO> list = new ArrayList<>();
        for (ReturnEntity e : entityList) {
            list.add(ReturnMapper.toDTO(e));
        }
        return list;
    }
}