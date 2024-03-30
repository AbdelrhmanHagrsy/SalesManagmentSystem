package com.example.Sales.Management.System.configuration;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditingImplemen implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        /* if you use spring security
         return Optional.ofNullable(SecurityContextHolder.getContext())
            .map(SecurityContext::getAuthentication)
            .filter(Authentication::isAuthenticated)
            .map(Authentication::getPrincipal)
            .map(User.class::cast);
         */
        return Optional.of("userName");
    }
}
