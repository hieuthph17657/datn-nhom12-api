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
public class OrderDetailPaginationRequest extends PaginationRequest {
    private String searchMoney ;
    public List<Filter> getFilters(){
        List<Filter> list = new ArrayList<>();
        if (searchMoney !=null && !searchMoney.isEmpty()){
            list.add(new Filter("Money", QueryOperator.LIKE, searchMoney, null));
        }
        return list;
    }
}
