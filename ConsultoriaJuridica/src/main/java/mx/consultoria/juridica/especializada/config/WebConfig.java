package mx.consultoria.juridica.especializada.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import mx.consultoria.juridica.especializada.entities.HistoricoCasos;
import mx.consultoria.juridica.especializada.repository.RepositorioDaoHistoricoDocumentos;

@Configuration
@EnableWebMvc
@ComponentScan("mx.consultoria.juridica.especializada")
@EnableTransactionManagement
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver resol = new InternalResourceViewResolver();
		resol.setPrefix("/WEB-INF/views/");
		resol.setSuffix(".jsp");
		return resol;
	}
	
	 //Ruta de recursos como; css, js, imagenes, iconos, bootstrap, jquery
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/resources/**")
          .addResourceLocations("classpath:/resources/");	  
    }
    
    
    
    
//    @Autowired
//    @Bean(name = "sessionFactory")
//    public SessionFactory getSessionFactory(DataSource dataSource) {
//     
//        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
//     
//        sessionBuilder.addAnnotatedClasses(HistoricoCasos.class);
//     
//        return sessionBuilder.buildSessionFactory();
//    }
    
    
//    private Properties getHibernateProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
//        return properties;
//    }
//    
//    @Autowired
//    @Bean(name = "transactionManager")
//    public HibernateTransactionManager getTransactionManager(
//            SessionFactory sessionFactory) {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager(
//                sessionFactory);
//     
//        return transactionManager;
//    }
//    
    
   
    
    
    
//    <bean id="sessionFactory" class="org.springframework.orm.hibernate4.LocalSessionFactoryBean">
//    <property name="packagesToScan">
//    	<list>
//    		<value>mx.gob.conacyt.problemas.persistencia.entities</value>
//    		<value>mx.gob.conacyt.mujeres.persistencia.entities</value>
//    		<value>mx.gob.conacyt.becas.persistencia.entities</value>
//    		<value>mx.gob.conacyt.becas.vocaciones.persistencia.entities</value>
//    	</list>
//    </property>
//    <property name="hibernateProperties">
//       <props>
//          <prop key="hibernate.dialect">org.hibernate.dialect.Oracle10gDialect</prop>
//          <prop key="hibernate.show_sql">true</prop>
//       </props>
//    </property>
//    <property name="dataSource" ref="dataSource" />
// </bean>
    
    
}
