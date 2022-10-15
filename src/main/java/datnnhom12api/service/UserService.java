package datnnhom12api.service;

import datnnhom12api.dto.core.Filter;
import datnnhom12api.entity.UserEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.request.UserRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface UserService {

    UserEntity save(UserRequest post) throws CustomException;

    UserEntity edit(Long id, UserRequest post) throws CustomException;

    UserEntity delete(Long id) throws CustomException;

    Page<UserEntity> paginate(int page, int limit, List<Filter> whereParams, Map<String, String> sortBy);
}
