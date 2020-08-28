package vn.javisco.agency.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.javisco.agency.entity.OrderStatusFlag;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequest {

    private String name;

    private OrderStatusFlag status;

    private int userId;
}

