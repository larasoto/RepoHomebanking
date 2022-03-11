package com.mindhub.Homebanking.services.implementaciones;

import com.mindhub.Homebanking.dtos.ClienDTO;
import com.mindhub.Homebanking.models.Client;
import com.mindhub.Homebanking.repositories.ClientRepository;
import com.mindhub.Homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<ClienDTO> getClients() {
        return clientRepository.findAll().stream().map(client -> new ClienDTO(client)).collect(Collectors.toList());
    }

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public ClienDTO getClient(Long id) {
        ClienDTO clienDTO = new ClienDTO(clientRepository.findById(id).orElse(null));
        return clienDTO;
        //return new ClienDTO(clientRepository.findById(id).orElse(null));
    }

    @Override
    public ClienDTO findByEmail(String email) {
        return new ClienDTO( clientRepository.findByEmail(email));
    }

    @Override
    public Client findByClientEmail(String email) {
        return clientRepository.findByEmail(email);
    }
}
