package datnnhom12api.controller.admin;

import datnnhom12api.entity.BatteryChargerEntity;
import datnnhom12api.entity.DiscountEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.BatteryChargerMapper;
import datnnhom12api.mapper.DiscountMapper;
import datnnhom12api.paginationrequest.BatteryChargerPaginationRequest;
import datnnhom12api.request.BatteryChargerRequest;
import datnnhom12api.response.BatteryChargerResponse;
import datnnhom12api.response.DiscountResponse;
import datnnhom12api.service.BatteryChargerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
public class BatteryChargerController {
    @Autowired
    private BatteryChargerService batteryChargerService;

    @GetMapping("/api/staff/batteryCharger")
    public BatteryChargerResponse index(@Valid BatteryChargerPaginationRequest request, BindingResult bindingResult) throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<BatteryChargerEntity> page = batteryChargerService.paginate(request.getPage(), request.getLimit(), request.getFilters(), request.getOrders());
        return new BatteryChargerResponse(BatteryChargerMapper.toPageDTO(page));
    }

    @PostMapping("/api/admin/batteryCharger")
    public BatteryChargerResponse create(@Valid @RequestBody BatteryChargerRequest post, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        BatteryChargerEntity postEntity = batteryChargerService.create(post);
        return new BatteryChargerResponse(BatteryChargerMapper.getInstance().toDTO(postEntity));
    }

    @GetMapping("/api/admin/batteryCharger/{id}")
    public BatteryChargerResponse getId(@PathVariable("id") Long id) throws CustomException{
        BatteryChargerEntity entity =batteryChargerService.getByIdBatteryCharger(id);
        return new BatteryChargerResponse(BatteryChargerMapper.getInstance().toDTO(entity));
    }

    @PutMapping("/api/admin/batteryCharger/{id}")
    public BatteryChargerResponse edit(@PathVariable("id") Long id, @Valid @RequestBody BatteryChargerRequest post, BindingResult bindingResult) throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        BatteryChargerEntity postEntity = batteryChargerService.update(id, post);
        return new BatteryChargerResponse(BatteryChargerMapper.getInstance().toDTO(postEntity));
    }

    @DeleteMapping("/api/admin/batteryCharger/{id}")
    public BatteryChargerResponse delete(@PathVariable("id") Long id) throws CustomException {
        batteryChargerService.delete(id);
        return new BatteryChargerResponse();
    }
}