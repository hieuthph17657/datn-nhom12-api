package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.CardDTO;
import datnnhom12api.dto.CategoryDTO;
import datnnhom12api.dto.GraphicsDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class GraphicResponse extends BaseResponse<GraphicsDTO> {
    public GraphicResponse(Page<GraphicsDTO> toPageDTO) {
        super(toPageDTO);
    }

    public GraphicResponse(List<GraphicsDTO> toListDTO) {
        super(toListDTO);
    }

    public GraphicResponse(GraphicsDTO toDTO) {
        super(toDTO);
    }

    public GraphicResponse() {
        super();
    }
}
