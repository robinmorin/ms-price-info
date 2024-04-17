package org.test.capitole.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.test.capitole.domain.ports.in.SearchPriceInputPort;
import org.test.capitole.domain.ports.out.SearchPriceOutputPort;
import org.test.capitole.domain.usecase.SearchPriceUseCase;

/***
 * Configuration class for the beans of the application for classes that couldn't be
 * annotated with Spring annotations for layer domain protection
 */
@Configuration
public class BeanConfig {

    @Bean
    public SearchPriceInputPort searchPriceInputPort(SearchPriceOutputPort searchPriceOutputPort){
        return new SearchPriceUseCase(searchPriceOutputPort);
    }

}
