package com.app.artisandor.services.classes;

import com.app.artisandor.entity.Commande;
import com.app.artisandor.exception.CommandeNotFoundException;
import com.app.artisandor.exception.CommandeValidationException;
import com.app.artisandor.repository.CommandeRepository;
import com.app.artisandor.services.interfaces.CommandeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommandeServiceImp implements CommandeService {

    private static final Logger logger = LoggerFactory.getLogger(CommandeServiceImp.class);

    private final CommandeRepository commandeRepository;

    @Override
    public Page<Commande> getCommande(Pageable pageable) {
        logger.info("Fetching commandes with pageable: {}", pageable);
        return commandeRepository.findAll(pageable);
    }

    @Override
    public Commande getCommandeById(Long id) {
        logger.info("Fetching commande with id: {}", id);
        Optional<Commande> commande = commandeRepository.findById(id);
        if (commande.isPresent()) {
            return commande.get();
        } else {
            logger.warn("Commande with id {} not found", id);
            throw new CommandeNotFoundException("Commande not found for id: " + id);
        }
    }

    @Override
    @Transactional
    public Commande saveCommande(@Valid Commande commande) {
        logger.info("Saving commande: {}", commande);

        validateCommande(commande);

        commande.setCreatedAt(LocalDateTime.now());

        try {
            Commande savedCommande = commandeRepository.save(commande);
            logger.info("Commande saved successfully: {}", savedCommande);
            return savedCommande;
        } catch (Exception e) {
            logger.error("Error saving commande: {}", commande, e);
            throw new RuntimeException("Failed to save commande", e);
        }
    }

    private void validateCommande(Commande commande) {
        if (commande.getUser() == null) {
            logger.warn("Commande user is null");
            throw new CommandeValidationException("Commande user cannot be null");
        }

        if (commande.getProducts() == null || commande.getProducts().isEmpty()) {
            logger.warn("Commande products are null or empty");
            throw new CommandeValidationException("Commande must include at least one product");
        }

        if (commande.getQte() <= 0) {
            logger.warn("Commande quantity is invalid: {}", commande.getQte());
            throw new CommandeValidationException("Commande quantity must be greater than zero");
        }
    }

    @Override
    @Transactional
    public void deleteCommandeById(Long id) {
        logger.info("Deleting commande with id: {}", id);
        try {
            commandeRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting commande with id: {}", id, e);
            throw new RuntimeException("Failed to delete commande", e);
        }
    }
}
