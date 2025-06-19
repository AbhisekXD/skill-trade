package org.skilltrade.service;

import org.springframework.stereotype.Service;

@Service
public class EmailDomainService {
    public boolean isEdu(String email) {
        return email != null && email.endsWith(".edu");
    }
}
