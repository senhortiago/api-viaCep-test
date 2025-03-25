package edu.padroes_de_projeto_spring.service;


import edu.padroes_de_projeto_spring.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {
    //busca o endereço baseado no cep e popula o endereço com as variáveis do get
    @GetMapping("/{cep}/json/")
    Address consultCep(@PathVariable("cep") String cep);
}
