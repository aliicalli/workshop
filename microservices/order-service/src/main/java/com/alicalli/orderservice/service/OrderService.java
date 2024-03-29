package com.alicalli.orderservice.service;

import com.alicalli.orderservice.dto.OrderLineItemsDto;
import com.alicalli.orderservice.dto.OrderRequest;
import com.alicalli.orderservice.model.Order;
import com.alicalli.orderservice.model.OrderLineItems;
import com.alicalli.orderservice.repository.OrderRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

  private final OrderRepository orderRepository;

  public void placeOrder(OrderRequest orderRequest) {
    Order order = new Order();
    order.setOrderNumber(UUID.randomUUID().toString());

    List<OrderLineItems> orderLineItems = orderRequest
      .getOrderLineItemsDtoList()
      .stream()
      .map(this::maptoDto)
      .toList();

    order.setOrderLineItemsList(orderLineItems);

    orderRepository.save(order);
  }

  private OrderLineItems maptoDto(OrderLineItemsDto orderLineItemsDto) {
    OrderLineItems orderLineItems = new OrderLineItems();
    orderLineItems.setPrice(orderLineItemsDto.getPrice());
    orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
    orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
    return orderLineItems;
  }
}
