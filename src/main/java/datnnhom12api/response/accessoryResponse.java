package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.accessoryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class accessoryResponse extends BaseResponse<accessoryDTO> {
    public accessoryResponse(Page<accessoryDTO> toPageDTO) {
        super(toPageDTO);
    }

    public accessoryResponse(List<accessoryDTO> toListDTO) {
        super(toListDTO);
    }

    public accessoryResponse(accessoryDTO toDTO) {
        super(toDTO);
    }

    public accessoryResponse() {
        super();
    }
}
