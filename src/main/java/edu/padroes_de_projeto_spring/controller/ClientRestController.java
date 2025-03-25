package edu.padroes_de_projeto_spring.controller;

import edu.padroes_de_projeto_spring.model.Client;
import edu.padroes_de_projeto_spring.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clients")
public class ClientRestController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<Iterable<Client>> findAll(){
        return ResponseEntity.ok(clientService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id){
        return ResponseEntity.ok(clientService.findById(id));
    }
    @PostMapping
    public ResponseEntity<Client> insertClient(@RequestBody Client client){
        clientService.insertClient(client);
        return ResponseEntity.ok(client);
    }
    @PutMapping
    public ResponseEntity<Client> refresh(@PathVariable Long id, @RequestBody Client client){
        clientService.refresh(id,client);
        return ResponseEntity.ok(client);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Client> delete(@PathVariable Long id){
        clientService.delete(id);
        return ResponseEntity.ok().build();
    }




}
