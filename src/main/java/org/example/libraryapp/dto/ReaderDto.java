package org.example.libraryapp.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReaderDto implements Serializable {
    int id;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
}