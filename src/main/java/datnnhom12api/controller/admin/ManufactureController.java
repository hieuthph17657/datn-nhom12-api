package datnnhom12api.controller.admin;

import datnnhom12api.dto.ManufactureDTO;
import datnnhom12api.entity.ManufactureEntity;
import datnnhom12api.entity.ProductEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.ManufactureMapper;
import datnnhom12api.mapper.ProductMapper;
import datnnhom12api.paginationrequest.ManufacturePaginationRequest;
import datnnhom12api.response.ManufactureResponse;
import datnnhom12api.response.ProductResponse;
import datnnhom12api.service.ManufactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/manufactures")
public class ManufactureController {
    @Autowired
    private ManufactureService manufactureService;

    @GetMapping
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

}
