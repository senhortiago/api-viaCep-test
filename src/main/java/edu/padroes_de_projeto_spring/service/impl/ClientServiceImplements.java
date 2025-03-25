package edu.padroes_de_projeto_spring.service.impl;

import edu.padroes_de_projeto_spring.model.Address;
import edu.padroes_de_projeto_spring.model.AddressRepository;
import edu.padroes_de_projeto_spring.model.Client;
import edu.padroes_de_projeto_spring.model.ClientRepository;
import edu.padroes_de_projeto_spring.service.ClientService;
import edu.padroes_de_projeto_spring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImplements implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (!client.isEmpty()){
            return client.get();
        }
        return null;

    }

    @Override
    public void insertClient(Client client) {
        saveClientCep(client);
    }


    @Override
    public void refresh(Long id, Client client) {
        //busca cliente pelo id
        Optional<Client> clientDb = clientRepository.findById(id);
        if (!clientDb.isEmpty()){
            //se ele existir no sistema ele atualiza
            saveClientCep(client);
        }
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    private void saveClientCep(Client client) {
        //verifica se o endereço do cliente já existe pelo cep
        String cep = client.getAddress().getCep();
        Address address =  addressRepository.findById(Long.valueOf(cep)).orElseGet(()->{
            //caso naõ exista, integrar com o viacep e persistir o retorno.
            Address newAddress = viaCepService.consultCep(cep);
            addressRepository.save(newAddress);
            return newAddress;});
        //inserir client, vinculando o endereço(novo ou existente).
        client.setAddress(address);
        clientRepository.save(client);
    }
}
