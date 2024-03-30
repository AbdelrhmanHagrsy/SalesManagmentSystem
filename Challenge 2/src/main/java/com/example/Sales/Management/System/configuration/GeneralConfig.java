package com.example.Sales.Management.System.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class GeneralConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditingImplemen();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(
                "classpath:/messages/message",
                "classpath:/messages/message_ar"
        );
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
