package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.CartDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class CartResponse extends BaseResponse<CartDTO> {
    public CartResponse(Page<CartDTO> toPageDTO) {
        super(toPageDTO);
    }

    public CartResponse(List<CartDTO> toListDTO) {

        super(toListDTO);
    }

    public CartResponse(CartDTO toDTO) {
        super(toDTO);
    }

    public CartResponse() {
        super();
    }
}
