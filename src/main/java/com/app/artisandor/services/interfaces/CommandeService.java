package com.app.artisandor.services.interfaces;

import com.app.artisandor.entity.Commande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This interface defines the contract for the CommandeService.
 * It provides methods for managing Commande entities.
 */
public interface CommandeService {

    /**
     * Retrieves a paginated list of Commande entities.
     *
     * @param pageable The pagination information.
     * @return A Page object containing the list of Commande entities.
     */
    Page<Commande> getCommande(Pageable pageable);

    /**
     * Retrieves a Commande entity by its unique identifier.
     *
     * @param id The unique identifier of the Commande entity.
     * @return The Commande entity with the specified id.
     */
    Commande getCommandeById(Long id);

    /**
     * Saves a new or updates an existing Commande entity.
     *
     * @param commande The Commande entity to be saved or updated.
     * @return The saved Commande entity.
     */
    Commande saveCommande(Commande commande);

    /**
     * Deletes a Commande entity by its unique identifier.
     *
     * @param id The unique identifier of the Commande entity to be deleted.
     */
    void deleteCommandeById(Long id);
}
