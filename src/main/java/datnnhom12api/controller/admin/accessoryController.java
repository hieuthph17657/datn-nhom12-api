package datnnhom12api.controller.admin;

import datnnhom12api.entity.accessoryEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.accessoryMapper;
import datnnhom12api.paginationrequest.accessoryPaginationRequest;
import datnnhom12api.request.accessoryRequest;
import datnnhom12api.response.accessoryResponse;
import datnnhom12api.service.accessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class accessoryController {
    @Autowired
    accessoryService accessoryService;

    @GetMapping("/api/staff/accessory/getAll")
    public List<accessoryEntity> getAll() {
        return accessoryService.findAll();
    }

    @GetMapping("/api/staff/accessory")
    public accessoryResponse index(@Valid accessoryPaginationRequest request, BindingResult bindingResult) throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<accessoryEntity> page = accessoryService.paginate(request.getPage(), request.getLimit(), request.getFilters(), request.getOrders());
        return new accessoryResponse(accessoryMapper.toPageDTO(page));
    }

    @PostMapping("/api/admin/accessory")
    public accessoryResponse create(@Valid @RequestBody accessoryRequest post, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        accessoryEntity postEntity = accessoryService.create(post);
        return new accessoryResponse(accessoryMapper.getInstance().toDTO(postEntity));
    }
}
