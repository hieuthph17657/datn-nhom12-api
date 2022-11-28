package datnnhom12api.controller.admin;

import datnnhom12api.entity.ScreenEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.ScreenMapper;
import datnnhom12api.paginationrequest.ScreenPaginationRequest;
import datnnhom12api.request.ScreenRequest;
import datnnhom12api.response.ScreenResponse;
import datnnhom12api.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ScreenController {

    @Autowired
    private ScreenService screenService;


    @GetMapping("auth/screens")
    public ScreenResponse index(@Valid ScreenPaginationRequest request, BindingResult bindingResult)
            throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<ScreenEntity> page = screenService.paginate(request.getPage(), request.getLimit(), request.getFilters(),
                request.getOrders());
        return new ScreenResponse(ScreenMapper.toPageDTO(page));
    }

    @PostMapping("/staff/screens")
    public ScreenResponse create(@Valid @RequestBody ScreenRequest post, BindingResult bindingResult)
            throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ScreenEntity postEntity = screenService.create(post);
        return new ScreenResponse(ScreenMapper.getInstance().toDTO(postEntity));
    }

    @PutMapping("/staff/screens/{id}")
    public ScreenResponse edit(@PathVariable("id") Long id, @Valid @RequestBody ScreenRequest post, BindingResult bindingResult)
            throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ScreenEntity postEntity = screenService.update(id, post);
        return new ScreenResponse(ScreenMapper.getInstance().toDTO(postEntity));
    }

    @DeleteMapping("/staff/screens/{id}")
    public ScreenResponse delete(@PathVariable("id") Long id) throws CustomException {
        screenService.delete(id);
        return new ScreenResponse();
    }
}
