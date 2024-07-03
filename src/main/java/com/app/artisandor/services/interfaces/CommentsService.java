package com.app.artisandor.services.interfaces;

import com.app.artisandor.entity.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This interface defines the contract for the CommentsService.
 * It provides methods for managing comments in the application.
 */
public interface CommentsService {

    /**
     * Retrieves a paginated list of comments.
     *
     * @param pageable The pagination information.
     * @return A Page object containing the comments.
     */
    Page<Comments> getComments(Pageable pageable);

    /**
     * Retrieves a comment by its unique identifier.
     *
     * @param id The unique identifier of the comment.
     * @return The comment with the specified id, or null if not found.
     */
    Comments getCommentsById(Long id);

    /**
     * Saves a new comment or updates an existing one.
     *
     * @param comments The comment to be saved or updated.
     * @return The saved comment.
     */
    Comments saveComments(Comments comments);

    /**
     * Deletes a comment by its unique identifier.
     *
     * @param id The unique identifier of the comment to be deleted.
     */
    void deleteCommentsById(Long id);
}
