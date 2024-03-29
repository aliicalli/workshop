package com.alicalli.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

  private java.util.List<OrderLineItemsDto> orderLineItemsDtoList;
  
}
