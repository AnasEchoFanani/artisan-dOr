package com.app.artisandor.services.classes;

import com.app.artisandor.entity.Newsletter;
import com.app.artisandor.services.interfaces.NewsletterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class NewsletterServiceImp implements NewsletterService {
    @Override
    public Page<Newsletter> getNewsletter(Pageable pageable) {
        return null;
    }

    @Override
    public Newsletter getNewsletterById(Long id) {
        return null;
    }

    @Override
    public Newsletter saveNewsletter(Newsletter newsletter) {
        return null;
    }

    @Override
    public void deleteNewsletterById(Long id) {

    }

    @Override
    public boolean isNewsletterAlreadyExist(String email) {
        return false;
    }

    @Override
    public int getTotalNewsletterCount() {
        return 0;
    }

    @Override
    public String sendNewsletter(List<Newsletter> newsletter) {
        return "";
    }
}
