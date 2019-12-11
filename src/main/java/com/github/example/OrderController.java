package com.github.example;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.example.model.Order;
import com.github.example.model.Product;
import com.github.example.repository.OrderRepository;
import com.github.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.datatables.DataTablesInput;
import org.springframework.data.mongodb.datatables.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void seed() {
        if(orderRepository.count() == 0) {

            List<Product> products = new ArrayList<>();

            for (int i = 1; i <= 100; i++) {
                Product p = new Product();
                p.setId(i);
                p.setLabel("Product " + i);
                p.setEnabled(i % 2 == 0);
                p.setCreatedAt(LocalDateTime.now());
                products.add(p);
            }

            productRepository.saveAll(products);

            List<Order> orders = new ArrayList<>();

            for (int i = 1; i <= 100; i++) {
                Order o = new Order();
                o.setId(i);
                o.setLabel("Order " + i);
                o.setEnabled(i % 2 == 1);
                o.setCreatedAt(LocalDateTime.now());
                o.setProduct(products.get(i - 1));
                orders.add(o);
            }

            orderRepository.saveAll(orders);
        }
    }

    @GetMapping(value = "/datatable")
    @ResponseBody
    @JsonView(DataTablesOutput.View.class)
    public DataTablesOutput<Order> getOrders(@Valid DataTablesInput input) {

        input.getColumns().stream().forEach(column -> {
            if (column.getData().equals("product")) {
                column.setReference(true);
                column.setReferenceOrderColumn("label");
                column.setReferenceCollection("product");

                List<String> productRefColumns = new ArrayList<>();
                productRefColumns.add("label");
                productRefColumns.add("isEnabled");
                productRefColumns.add("createdAt");
                column.setReferenceColumns(productRefColumns);
            }
        });

        return orderRepository.findAll(input);
    }
}
