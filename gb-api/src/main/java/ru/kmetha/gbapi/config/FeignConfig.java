package ru.kmetha.gbapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.kmetha.gbapi.category.api.CategoryGateway;
import ru.kmetha.gbapi.manufacturer.api.ManufacturerGateway;
import ru.kmetha.gbapi.product.api.ProductGateway;

@Configuration
@EnableConfigurationProperties(GbApiProperties.class)
@RequiredArgsConstructor
@Import(value = {FeignClientFactory.class})
public class FeignConfig {

    private final GbApiProperties gbApiProperties;
    private final FeignClientFactory feignClientFactory;

    @Bean
    public ManufacturerGateway manufacturerGateway() {
        return feignClientFactory.newFeignGateway(ManufacturerGateway.class, gbApiProperties.getEndpoint().getManufacturerUrl());
    }

    @Bean
    public CategoryGateway categoryGateway() {
        return feignClientFactory.newFeignGateway(CategoryGateway.class, gbApiProperties.getEndpoint().getCategoryUrl());
    }

    @Bean
    public ProductGateway productGateway() {
        return feignClientFactory.newFeignGateway(ProductGateway.class, gbApiProperties.getEndpoint().getManufacturerUrl());
    }
}
