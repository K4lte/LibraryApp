package org.example.libraryapp.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorDto implements Serializable {
    int id;
    String name;
}