package jda.app.opasys.knowledgeasset;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import jda.modules.msacommon.connections.UserContextInterceptor;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.DefaultController;
import jda.modules.msacommon.controller.RedirectController;
import jda.modules.msacommon.controller.RedirectControllerRegistry;
import jda.modules.msacommon.controller.ServiceRegistry;


@SpringBootApplication
@RefreshScope
@EnableEurekaClient
public class KnowledgeAssetServiceApp {
	private static final Logger logger = LoggerFactory.getLogger(KnowledgeAssetServiceApp.class);
	
	public static void main(String[] args) {
		final ServiceRegistry serviceRegistry = ServiceRegistry.getInstance();
		final ControllerRegistry controllerRegistry = ControllerRegistry.getInstance();
		final RedirectControllerRegistry redirectControllerRegistry = RedirectControllerRegistry.getInstance();
		ApplicationContext ctx = SpringApplication.run(KnowledgeAssetServiceApp.class, args);
		ctx.getBeansOfType(PagingAndSortingRepository.class).forEach((k, v) -> {serviceRegistry.put(k, v);
		System.out.println("CHECK SERVICES: "+ k +"_"+v);
			});
		ctx.getBeansOfType(DefaultController.class).forEach((k, v) -> {controllerRegistry.put(k, v);
		System.out.println("CHECK Controller: "+ k +"_"+v);
			});
		ctx.getBeansOfType(RedirectController.class).forEach((k, v) -> {redirectControllerRegistry.put(k, v);
		System.out.println("CHECK RedirectController: "+ k +"_"+v);
			});
	}
		
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setBasenames("messages");
		return messageSource;
	}
	
	@SuppressWarnings("unchecked")
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate(){
		RestTemplate template = new RestTemplate();
        List interceptors = template.getInterceptors();
        if (interceptors==null){
            template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
        }
        else{
            interceptors.add(new UserContextInterceptor());
            template.setInterceptors(interceptors);
        }

        return template;
	}

}
