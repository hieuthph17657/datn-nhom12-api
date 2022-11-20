package datnnhom12api.controller.admin;


import datnnhom12api.dto.UpdateReturnDetailDTO;
import datnnhom12api.entity.ReturnDetailEntity;
import datnnhom12api.entity.ReturnEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.ReturnMapper;
import datnnhom12api.paginationrequest.ReturnPaginationRequest;
import datnnhom12api.request.ReturnDetailRequest;
import datnnhom12api.request.ReturnRequest;
import datnnhom12api.response.ReturnResponse;
import datnnhom12api.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ReturnController {

    @Autowired
    private ReturnService returnService;

    @GetMapping("/returns")
    public ReturnResponse index(@Valid ReturnPaginationRequest request, BindingResult bindingResult)
            throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<ReturnEntity> page = returnService.paginate(request.getPage(), request.getLimit(),
                request.getFilters(), request.getOrders());
        return new ReturnResponse(ReturnMapper.toPageDTO(page));
    }

    @PostMapping("/returns")
    public ReturnResponse create(@Valid @RequestBody ReturnRequest post,
                                 BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ReturnEntity postEntity = returnService.insert(post);
        return new ReturnResponse(ReturnMapper.getInstance().toDTO(postEntity));
    }

    @PutMapping("/returns/{id}")
    public ReturnResponse edit(@PathVariable("id") Long id, @Valid @RequestBody ReturnRequest post,
                               BindingResult bindingResult) throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ReturnEntity postEntity = this.returnService.update(id, post);
        return new ReturnResponse(ReturnMapper.getInstance().toDTO(postEntity));
    }

    @DeleteMapping("/returns/{id}")
    public ReturnResponse delete(@PathVariable("id") Long id) throws CustomException {
        returnService.delete(id);
        return new ReturnResponse();
    }

    @GetMapping("/returns/{id}")
    public List<ReturnDetailEntity> findReturnById (@PathVariable("id") Long id)  throws CustomException {
        List<ReturnDetailEntity>  returnDetailEntities = returnService.findById(id);
        return returnDetailEntities;
    }

    @PutMapping("/{id}/updateReturnDetails")
    public UpdateReturnDetailDTO updateByReturnDetail(@PathVariable("id")Long id,@RequestBody  ReturnDetailRequest returnDetailRequest){
        UpdateReturnDetailDTO returnDetailDTO = this.returnService.updateByReturnDetail(id,returnDetailRequest);
        return  returnDetailDTO;
    }

}