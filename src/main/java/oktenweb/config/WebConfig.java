package oktenweb.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.io.File;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource bundleMessageSource
                =new ReloadableResourceBundleMessageSource();
        bundleMessageSource.setBasename("classpath:messages");
        return bundleMessageSource;
    }
    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(){

        LocalValidatorFactoryBean validatorFactoryBean= new LocalValidatorFactoryBean();
        validatorFactoryBean.setValidationMessageSource(messageSource());
        return validatorFactoryBean;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {

        String pathToFolder = System.getProperty("user.home")+ File.separator + "images" +File.separator;
        String pathToFolder2 = System.getProperty("user.home")+ File.separator + "cssFiles" +File.separator;

        resourceHandlerRegistry.addResourceHandler("/ava/**").addResourceLocations("file:///"+pathToFolder);
        resourceHandlerRegistry.addResourceHandler("/css/**").addResourceLocations("file:///"+pathToFolder2);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {

        viewControllerRegistry.addViewController("/about").setViewName("about");
    }
    @Override
    public void configurePathMatch(PathMatchConfigurer pathMatchConfigurer) {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer contentNegotiationConfigurer) {

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer asyncSupportConfigurer) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer defaultServletHandlerConfigurer) {

    }

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {

    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {

    }



    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry) {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> list) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> list) {

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> list) {

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> list) {

    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }
}
