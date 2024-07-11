package com.app.artisandor.controllers;

import com.app.artisandor.entity.Newsletter;
import com.app.artisandor.services.interfaces.NewsletterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/newsletters")
public class NewsletterController {

    private final NewsletterService newsletterService;

    @Autowired
    public NewsletterController(NewsletterService newsletterService) {
        this.newsletterService = newsletterService;
    }

    @GetMapping
    public ResponseEntity<Page<Newsletter>> getNewsletters(Pageable pageable) {
        Page<Newsletter> newsletters = newsletterService.getNewsletter(pageable);
        return ResponseEntity.ok(newsletters);
    }

    @PostMapping
    public ResponseEntity<Newsletter> createNewsletter(@Valid @RequestBody Newsletter newsletter) {
        Newsletter savedNewsletter = newsletterService.saveNewsletter(newsletter);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNewsletter);
    }
}
