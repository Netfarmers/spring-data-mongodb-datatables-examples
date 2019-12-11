package com.github.example.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.datatables.DataTablesOutput;

import java.time.LocalDateTime;

@Document
@Data
public class Order {
    @JsonView(DataTablesOutput.View.class)
    @Id
    private long id;

    @JsonView(DataTablesOutput.View.class)
    private boolean isEnabled;

    @JsonView(DataTablesOutput.View.class)
    private String label;

    @JsonView(DataTablesOutput.View.class)
    private LocalDateTime createdAt;

    @JsonView(DataTablesOutput.View.class)
    @DBRef
    private Product product;
}
