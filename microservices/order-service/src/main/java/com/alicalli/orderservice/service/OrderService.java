package com.alicalli.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.alicalli.orderservice.dto.InventoryResponse;
import com.alicalli.orderservice.dto.OrderLineItemsDto;
import com.alicalli.orderservice.dto.OrderRequest;
import com.alicalli.orderservice.model.Order;
import com.alicalli.orderservice.model.OrderLineItems;
import com.alicalli.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

  private final OrderRepository orderRepository;
  private final WebClient.Builder webClientBuilder;

  public String placeOrder(OrderRequest orderRequest) {
    Order order = new Order();
    order.setOrderNumber(UUID.randomUUID().toString());

    List<OrderLineItems> orderLineItems = orderRequest
      .getOrderLineItemsDtoList()
      .stream()
      .map(this::maptoDto)
      .toList();

    order.setOrderLineItemsList(orderLineItems);

    List<String> skuCodes = order
      .getOrderLineItemsList()
      .stream()
      .map(OrderLineItems::getSkuCode)
      .toList();

    InventoryResponse[] inventoryResponsArray = webClientBuilder.build()
      .get()
      .uri(
        "http://inventory-service/api/inventory",
        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build()
      )
      .retrieve()
      .bodyToMono(InventoryResponse[].class)
      .onErrorMap(WebClientResponseException.class, ex -> new Exception("Error calling Inventory Service", ex))
      .block();

    boolean allProductsInStock = Arrays
      .stream(inventoryResponsArray)
      .allMatch(InventoryResponse::isInStock);

    if (allProductsInStock) {
      orderRepository.save(order);
      return "Order Placed Successfully";
    } else {
      throw new IllegalArgumentException(
        "Product is not in stock, please try again later"
      );
    }
  }

  private OrderLineItems maptoDto(OrderLineItemsDto orderLineItemsDto) {
    OrderLineItems orderLineItems = new OrderLineItems();
    orderLineItems.setPrice(orderLineItemsDto.getPrice());
    orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
    orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
    return orderLineItems;
  }
}
