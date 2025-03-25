package edu.padroes_de_projeto_spring.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne//Anotação muitos para um muitos clientes podem ter o mesmo endereço
    private Address address;


}
