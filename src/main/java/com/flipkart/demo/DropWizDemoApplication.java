package com.flipkart.demo;

import com.flipkart.bean.Auth;
import com.flipkart.dao.AuthDAO;
import com.flipkart.health.TemplateHealthCheck;
import com.flipkart.resources.PersonResource;
import com.flipkart.resources.HelloWorld;
import com.flipkart.services.PersonService;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

public class DropWizDemoApplication extends Application<DropWizDemoConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropWizDemoApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropWizDemo";
    }

    private final HibernateBundle<DropWizDemoConfiguration> hibernate = new HibernateBundle<DropWizDemoConfiguration>(Auth.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(DropWizDemoConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<DropWizDemoConfiguration> bootstrap) {
        // Add Asset Bundle
        bootstrap.addBundle(hibernate);
    }


    @Override
    public void run(final DropWizDemoConfiguration configuration, final Environment environment) {
        final HelloWorld resource = new HelloWorld(configuration.getTemplate(), configuration.getDefaultName());
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());

        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "SQL");

        final AuthDAO authDAO = jdbi.onDemand(AuthDAO.class);
        final PersonService personService = new PersonService(authDAO);

        final PersonResource personResource = new PersonResource(personService);


        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
        environment.jersey().register(personResource);
    }

}
