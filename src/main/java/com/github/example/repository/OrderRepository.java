package com.github.example.repository;

import com.github.example.model.Order;
import org.springframework.data.mongodb.datatables.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends DataTablesRepository<Order, Long>  {

}
