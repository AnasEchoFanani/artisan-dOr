package com.app.artisandor.repositories;

import com.app.artisandor.entity.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsletterRepository extends JpaRepository<Newsletter, Long> {
}