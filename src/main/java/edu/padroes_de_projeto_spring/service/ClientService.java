package edu.padroes_de_projeto_spring.service;

import edu.padroes_de_projeto_spring.model.Client;

public interface ClientService {

    Iterable<Client> findAll();
    Client findById(Long id);
    void insertClient(Client client);
    void refresh(Long id, Client client);
    void delete(Long id);

}
