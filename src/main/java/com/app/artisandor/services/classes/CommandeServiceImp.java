package com.app.artisandor.services.classes;

import com.app.artisandor.entity.Commande;
import com.app.artisandor.services.interfaces.CommandeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CommandeServiceImp implements CommandeService {
    @Override
    public Page<Commande> getCommande(Pageable pageable) {
        return null;
    }

    @Override
    public Commande getCommandeById(Long id) {
        return null;
    }

    @Override
    public Commande saveCommande(Commande commande) {
        return null;
    }

    @Override
    public void deleteCommandeById(Long id) {

    }
}
