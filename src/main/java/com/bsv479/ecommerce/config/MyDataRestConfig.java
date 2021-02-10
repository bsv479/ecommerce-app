package com.bsv479.ecommerce.config;

import com.bsv479.ecommerce.entity.Product;
import com.bsv479.ecommerce.entity.ProductCategory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] unsupportedHttpMethods = {HttpMethod.DELETE, HttpMethod.POST, HttpMethod.PUT};
        Class[] classesToConfig = {ProductCategory.class, Product.class};

        // Disable HTTP methods for classesToConfig: PUT, POST, DELETE
        for (Class clazz : classesToConfig) {
            config.getExposureConfiguration()
                    .forDomainType(clazz)
                    .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(unsupportedHttpMethods)))
                    .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(unsupportedHttpMethods)));  // Disable HTTP methods for Category
        }
    }
}
