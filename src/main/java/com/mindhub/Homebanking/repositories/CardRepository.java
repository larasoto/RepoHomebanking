package com.mindhub.Homebanking.repositories;

import com.mindhub.Homebanking.models.Card;
import com.mindhub.Homebanking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource //se encargará de publicar el repositorio cómo servicio REST y acceder a las operaciones fundamentales que todo repositorio implementa.
public interface CardRepository extends JpaRepository<Card,Long> {
    Card findByNumber(String Number);
}
