package pl.coderslab.app;

import java.util.Locale;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "pl.coderslab")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"pl.coderslab.repository"})
public class AppConfig extends WebMvcConfigurerAdapter {
	
	@Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver =
            new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(""
        		+ ".jsp");
        return viewResolver; } 
	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactory() {
	    LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
	    emfb.setPersistenceUnitName("clinicRegistrationPersistenceUnit");
	    return emfb; }
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
	    JpaTransactionManager tm = new JpaTransactionManager(emf);
	    return tm; }

//	@Override
//	public void addFormatters(FormatterRegistry registry) {
//	    registry.addConverter(getUserConverter());
//	    registry.addConverter(getTweetConverter());
//	}
//	@Bean
//	public UserConverter getUserConverter() {
//	    return new UserConverter();
//	}
//	
//	@Bean
//	public TweetConverter getTweetConverter() {
//	    return new TweetConverter();
//	}
	
//	@Bean
//	public Validator validator() {
//	    return new LocalValidatorFactoryBean();
//	}
	
	@Bean(name="localeResolver")
	public LocaleContextResolver getLocaleContextResolver() {
	    SessionLocaleResolver localeResolver = new SessionLocaleResolver();
	    localeResolver.setDefaultLocale(new Locale("pl","PL"));
	    return localeResolver; }
	
	//załączanie statycznych elementow na stronie
	@Override
    public void configureDefaultServletHandling(
    DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	}
	

