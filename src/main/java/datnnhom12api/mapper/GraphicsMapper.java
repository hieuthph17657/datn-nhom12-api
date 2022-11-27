package datnnhom12api.mapper;

import datnnhom12api.dto.CardDTO;
import datnnhom12api.dto.GraphicsDTO;
import datnnhom12api.entity.CardEntity;
import datnnhom12api.entity.GraphicsEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class GraphicsMapper {
    private static GraphicsMapper INSTANCE;

    public static GraphicsMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GraphicsMapper();
        }

        return INSTANCE;
    }

    public GraphicsMapper() {
    }

    public static GraphicsDTO toDTO(GraphicsEntity gra) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(gra, GraphicsDTO.class);
    }

    public static GraphicsEntity toEntity(GraphicsDTO gra) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(gra, GraphicsEntity.class);
    }

    public static Page<GraphicsDTO> toPageDTO(Page<GraphicsEntity> page) {
        return page.map(new Function<>() {
            @Override
            public GraphicsDTO apply(GraphicsEntity entity) {
                return GraphicsMapper.toDTO(entity);
            }
        });
    }

    public static List<GraphicsDTO> toListDTO(List<GraphicsEntity> entityList) {
        List<GraphicsDTO> list = new ArrayList<>();
        for (GraphicsEntity e : entityList) {
            list.add(GraphicsMapper.toDTO(e));
        }
        return list;
    }
}
