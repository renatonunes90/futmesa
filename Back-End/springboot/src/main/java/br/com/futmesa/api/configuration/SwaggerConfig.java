package br.com.futmesa.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig
{

   private String title = "Futmesa API";

   private String description = "API de acesso a base da dados da aplicação de Futmesa.";

   @Value ( "${info.api.version:}" )
   private String version = "0.1";

   @Value ( "${info.api.termsOfServiceUrl:}" )
   private String termsOfServiceUrl = "";

   private String name = "Renato Nunes";

   @Value ( "${info.api.contact.url:}" )
   private String url = "";

   @Value ( "${info.api.contact.email:}" )
   private String email = "";

   @Value ( "${info.api.contact.licence:}" )
   private String licence = "";

   @Value ( "${info.api.contact.licenceUrl:}" )
   private String licenceUrl = "";

   @Bean
   public Docket api()
   {
      return new Docket( DocumentationType.SWAGGER_2 ).apiInfo(
               new ApiInfo( title, description, version, termsOfServiceUrl, new Contact( name, url, email ), licence, licenceUrl ) )
               .select().apis( RequestHandlerSelectors.any() ).paths( PathSelectors.any() ).build();
   }
}
