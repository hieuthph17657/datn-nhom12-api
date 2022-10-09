package datnnhom12api.response;
import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.OrderDetailDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class OrderDetailResponse extends BaseResponse<OrderDetailDTO>{
    public OrderDetailResponse(Page<OrderDetailDTO> toPageDTO) {
        super(toPageDTO);
    }

    public OrderDetailResponse(List<OrderDetailDTO> toListDTO) {
        super(toListDTO);
    }

    public OrderDetailResponse(OrderDetailDTO toDTO) {
        super(toDTO);
    }

    public OrderDetailResponse() {
        super();
    }
}
