package com.app.artisandor.services.interfaces;

import com.app.artisandor.entity.Newsletter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsletterService {
    Page<Newsletter> getNewsletter(Pageable pageable);
    Newsletter getNewsletterById(Long id);
    Newsletter saveNewsletter(Newsletter newsletter);
    void deleteNewsletterById(Long id);
    boolean isNewsletterAlreadyExist(String email);
    long getTotalNewsletterCount();
    String sendNewsletter(List<Newsletter> newsletter);
}
