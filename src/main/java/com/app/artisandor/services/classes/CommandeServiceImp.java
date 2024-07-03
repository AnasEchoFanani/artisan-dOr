package com.app.artisandor.services.classes;

import com.app.artisandor.entity.Commande;
import com.app.artisandor.repository.CommandeRepository;
import com.app.artisandor.services.interfaces.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommandeServiceImp implements CommandeService {

    private final CommandeRepository commandeRepository;

    @Autowired
    public CommandeServiceImp(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    @Override
    public Page<Commande> getCommande(Pageable pageable) {
        return commandeRepository.findAll(pageable);
    }

    @Override
    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id).orElse(null);
    }

    @Override
    public Commande saveCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    @Override
    public void deleteCommandeById(Long id) {
        commandeRepository.deleteById(id);
    }
}
