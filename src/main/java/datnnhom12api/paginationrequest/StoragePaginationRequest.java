package datnnhom12api.paginationrequest;

import datnnhom12api.core.PaginationRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoragePaginationRequest extends PaginationRequest {
    private String search;
}
