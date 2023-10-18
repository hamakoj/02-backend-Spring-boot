package com.spring.ecommerceapp.configuration;

import com.spring.ecommerceapp.entity.Country;
import com.spring.ecommerceapp.entity.Product;
import com.spring.ecommerceapp.entity.ProductCategory;
import com.spring.ecommerceapp.entity.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private final EntityManager entityManager;
    @Autowired
    public MyDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);

        HttpMethod[] theUnsupportedAction= {HttpMethod.PUT,HttpMethod.POST,HttpMethod.DELETE};

        //disable Http methods for ProductCategory, country,state and Product: PUT, DELETE,POST
        disableHttpMethods(ProductCategory.class, config, theUnsupportedAction);
        disableHttpMethods(Product.class, config, theUnsupportedAction);
        disableHttpMethods(Country.class, config, theUnsupportedAction);
        disableHttpMethods(State.class, config, theUnsupportedAction);

        // call the Internal helper method
        exposeIds(config);

        //
        cors.addMapping("/**")
                .allowedHeaders("http://localhost:4200")
                .allowedMethods("*")
                .allowedOrigins("*");
    }

    private static void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedAction) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedAction)))
                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedAction)));
    }

    // to generate inter Id for product-category
    private void exposeIds(RepositoryRestConfiguration config) {
        // expose entity ids

        // get a list of all entity classes from entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // create an array of the entity types
        List<Class<?>> entityClasses = new ArrayList<>();

        // get the entity for the entities
        for (EntityType<?> tempEntityType:entities){
            entityClasses.add(tempEntityType.getJavaType());
        }
        // expose the entity ids for the array of entity/domainType
        Class<?>[] domainTypes = entityClasses.toArray(new Class<?>[0]);
        config.exposeIdsFor(domainTypes);
    }
}
