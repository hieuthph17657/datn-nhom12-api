package datnnhom12api.paginationrequest;

import datnnhom12api.core.Filter;
import datnnhom12api.core.PaginationRequest;
import datnnhom12api.core.QueryOperator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPaginationRequest extends PaginationRequest {
    private String searchProductKey;

    private String searchStatus;

    private String searchPrice;

    private String searchImei;

    public List<Filter> getFilters() {
        List<Filter> list = new ArrayList<>();
        if (searchStatus != null && !searchStatus.isEmpty()) {
            list.add(new Filter("status", QueryOperator.EQUALS, searchStatus, null));
        }
        if (searchImei != null && !searchImei.isEmpty()) {
            list.add(new Filter("imei", QueryOperator.EQUALS, searchImei, null));
        }
        return list;
    }
}
