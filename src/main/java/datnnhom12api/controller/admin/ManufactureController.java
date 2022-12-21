package datnnhom12api.controller.admin;

import datnnhom12api.dto.ManufactureDTO;
import datnnhom12api.entity.ManufactureEntity;
import datnnhom12api.entity.ProcessorEntity;
import datnnhom12api.entity.ProductEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.ManufactureMapper;
import datnnhom12api.mapper.ProcessorMapper;
import datnnhom12api.mapper.ProductMapper;
import datnnhom12api.paginationrequest.ManufacturePaginationRequest;
import datnnhom12api.request.ManufactureRequest;
import datnnhom12api.request.ProcessorRequest;
import datnnhom12api.response.ManufactureResponse;
import datnnhom12api.response.ProcessorResponse;
import datnnhom12api.response.ProductResponse;
import datnnhom12api.service.ManufactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ManufactureController {
    @Autowired
    private ManufactureService manufactureService;


    @GetMapping("/auth/manufactures")
    public ManufactureResponse index(@Valid ManufacturePaginationRequest request, BindingResult bindingResult)
            throws CustomValidationException {
        if(bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<ManufactureEntity> page = manufactureService.paginate(
                request.getPage(), request.getLimit(), request.getFilters(), request.getOrders()
        );
        return new ManufactureResponse(ManufactureMapper.toPageDTO(page));
    }


    @PostMapping("/staff/manufacture")
    public ManufactureResponse create(@Valid @RequestBody ManufactureRequest post, BindingResult bindingResult)
            throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ManufactureEntity postEntity = manufactureService.create(post);
        return new ManufactureResponse(ManufactureMapper.getInstance().toDTO(postEntity));
    }

    @PutMapping("/staff/manufacture/{id}")
    public ManufactureResponse edit(@PathVariable("id") Long id, @Valid @RequestBody ManufactureRequest post,
                                    BindingResult bindingResult) throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ManufactureEntity postEntity = manufactureService.update(id, post);
        return new ManufactureResponse(ManufactureMapper.getInstance().toDTO(postEntity));
    }

    @DeleteMapping("/staff/manufacture/{id}")
    public ManufactureResponse delete(@PathVariable("id") Long id) throws CustomException {
        manufactureService.delete(id);
        return new ManufactureResponse();
    }

}
