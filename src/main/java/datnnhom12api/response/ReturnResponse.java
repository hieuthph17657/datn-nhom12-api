package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.CartDTO;
import datnnhom12api.dto.ReturnDTO;
import org.springframework.data.domain.Page;

public class ReturnResponse extends BaseResponse<ReturnDTO> {
    public ReturnResponse(int status, String message, ReturnDTO data) {
        super(status, message, data);
    }

    public ReturnResponse() {
    }

    public ReturnResponse(ReturnDTO data) {
        super(data);
    }

    public ReturnResponse(Page<ReturnDTO> toPageDTO) {
        super(toPageDTO);
    }
}