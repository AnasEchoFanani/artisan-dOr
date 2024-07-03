package com.app.artisandor.services.classes;

import com.app.artisandor.entity.Newsletter;
import com.app.artisandor.repository.NewsletterRepository;
import com.app.artisandor.services.interfaces.NewsletterService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsletterServiceImp implements NewsletterService {

    private final NewsletterRepository newsletterRepository;
    private final MailService mailService;

    @Autowired
    public NewsletterServiceImp(NewsletterRepository newsletterRepository, MailService mailService) {
        this.newsletterRepository = newsletterRepository;
        this.mailService = mailService;
    }

    @Override
    public Page<Newsletter> getNewsletter(Pageable pageable) {
        return newsletterRepository.findAll(pageable);
    }

    @Override
    public Newsletter getNewsletterById(Long id) {
        return newsletterRepository.findById(id).orElse(null);
    }

    @Override
    public Newsletter saveNewsletter(Newsletter newsletter) {
        return newsletterRepository.save(newsletter);
    }

    @Override
    public void deleteNewsletterById(Long id) {
        newsletterRepository.deleteById(id);
    }

    @Override
    public boolean isNewsletterAlreadyExist(String email) {
        return newsletterRepository.existsByEmail(email);
    }

    @Override
    public long getTotalNewsletterCount() {
        return newsletterRepository.count();
    }

    @Override
    public String sendNewsletter(List<Newsletter> newsletters) {
        for (Newsletter newsletter : newsletters) {
            try {
                String htmlContent = "<html><body><h1>Newsletter</h1><p>This is the content of the newsletter.</p></body></html>";
                mailService.sendEmail(newsletter.getEmail(), "Newsletter", htmlContent);
            } catch (MessagingException e) {
                e.printStackTrace();
                return "Error sending newsletters";
            }
        }
        return "Newsletters sent successfully!";
    }
}
