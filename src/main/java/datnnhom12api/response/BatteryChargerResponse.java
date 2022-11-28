package datnnhom12api.response;

import datnnhom12api.core.BaseResponse;
import datnnhom12api.dto.BatteryChargerDTO;
import org.springframework.data.domain.Page;

public class BatteryChargerResponse extends BaseResponse<BatteryChargerDTO> {
    public BatteryChargerResponse(int status, String message, BatteryChargerDTO data) {
        super(status, message, data);
    }

    public BatteryChargerResponse() {
    }

    public BatteryChargerResponse(BatteryChargerDTO data) {
        super(data);
    }

    public BatteryChargerResponse(Page<BatteryChargerDTO> toPageDTO) {
        super(toPageDTO);
    }
}
