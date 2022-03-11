package com.mindhub.Homebanking.services;

import com.mindhub.Homebanking.dtos.ClienDTO;
import com.mindhub.Homebanking.models.Client;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ClientService {
    public List<ClienDTO> getClients();

    public Client saveClient(Client client);

    public ClienDTO getClient(Long id);

    public ClienDTO findByEmail(String email);

    public Client findByClientEmail(String email);
}