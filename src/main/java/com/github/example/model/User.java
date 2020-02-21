package com.github.example.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.datatables.DataTablesOutput;

@Document
@Data
public class User {

    @JsonView(DataTablesOutput.View.class)
    @Id
    private long id;

    @JsonView(DataTablesOutput.View.class)
    private String firstName;

    @JsonView(DataTablesOutput.View.class)
    private String lastName;
}

