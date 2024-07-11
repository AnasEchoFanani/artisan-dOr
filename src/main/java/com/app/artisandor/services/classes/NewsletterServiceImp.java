package com.app.artisandor.services.classes;

import com.app.artisandor.entity.Newsletter;
import com.app.artisandor.repository.NewsletterRepository;
import com.app.artisandor.services.interfaces.NewsletterService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsletterServiceImp implements NewsletterService {

    private static final Logger logger = LoggerFactory.getLogger(NewsletterServiceImp.class);

    private final NewsletterRepository newsletterRepository;
    private final MailService mailService;

    @Override
    public Page<Newsletter> getNewsletter(Pageable pageable) {
        logger.info("Fetching newsletters with pageable: {}", pageable);
        return newsletterRepository.findAll(pageable);
    }

    @Override
    public Newsletter getNewsletterById(Long id) {
        logger.info("Fetching newsletter with id: {}", id);
        return newsletterRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Newsletter saveNewsletter(Newsletter newsletter) {
        logger.info("Saving newsletter: {}", newsletter);
        return newsletterRepository.save(newsletter);
    }

    @Override
    @Transactional
    public void deleteNewsletterById(Long id) {
        logger.info("Deleting newsletter with id: {}", id);
        newsletterRepository.deleteById(id);
    }

    @Override
    public boolean isNewsletterAlreadyExist(String email) {
        logger.info("Checking if newsletter already exists for email: {}", email);
        return newsletterRepository.existsByEmail(email);
    }

    @Override
    public long getTotalNewsletterCount() {
        logger.info("Getting total count of newsletters");
        return newsletterRepository.count();
    }

    @Override
    public String sendNewsletter(List<Newsletter> newsletters) {
        logger.info("Sending newsletters to {} recipients", newsletters.size());
        for (Newsletter newsletter : newsletters) {
            try {
                StringBuilder htmlContent = new StringBuilder();
                htmlContent.append("<html>")
                        .append("<body>")
                        .append("<h1>Newsletter</h1>")
                        .append("<p>This is the content of the newsletter.</p>")
                        .append("</body>")
                        .append("</html>");

                mailService.sendEmail(newsletter.getEmail(), "Newsletter", htmlContent.toString());
            } catch (MessagingException e) {
                logger.error("Error sending newsletter to {}", newsletter.getEmail(), e);
                return "Error sending newsletters";
            }
        }
        return "Newsletters sent successfully!";
    }
}
