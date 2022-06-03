package mx.consultoria.juridica.especializada.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import mx.consultoria.juridica.especializada.entities.HistoricoCasos;
import mx.consultoria.juridica.especializada.entities.UploadFile;
import mx.consultoria.juridica.especializada.entities.User;
import mx.consultoria.juridica.especializada.repository.FileUploadDAO;
import mx.consultoria.juridica.especializada.repository.FileUploadDAOImpl;
import mx.consultoria.juridica.especializada.repository.UserDAO;
import mx.consultoria.juridica.especializada.repository.UserDAOImpl;


@Configuration
@ComponentScan("mx.consultoria.juridica.especializada")
@EnableTransactionManagement
public class ApplicationContextConfig {
	
	  private Properties getHibernateProperties() {
	    	Properties properties = new Properties();
	    	properties.put("hibernate.show_sql", "true");
//	    	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	    	properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
	    	return properties;
	    }
	  
	  
	    @Autowired
	    @Bean(name = "sessionFactory")
	    public SessionFactory getSessionFactory(DataSource dataSource) {
	    	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	    	sessionBuilder.addProperties(getHibernateProperties());
	    	sessionBuilder.addAnnotatedClasses(User.class, UploadFile.class, HistoricoCasos.class); // Declarar all clases Entitys para poder hacer las transacciones por Hibernate.
	    	return sessionBuilder.buildSessionFactory();
	    }
	    
	    
	    @Autowired
		@Bean(name = "transactionManager")
		public HibernateTransactionManager getTransactionManager(
				SessionFactory sessionFactory) {
			HibernateTransactionManager transactionManager = new HibernateTransactionManager(
					sessionFactory);

			return transactionManager;
		}
	    
//	    Este bean se hace uso es importante para poder subir archivos MULPART binarios a DB
	    @Bean(name = "multipartResolver")
	    public CommonsMultipartResolver getCommonsMultipartResolver() {
	    	CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    	multipartResolver.setMaxUploadSize(20971520); // 20MB
	    	multipartResolver.setMaxInMemorySize(1048576);	// 1MB
	    	return multipartResolver;
	    }
	    
	    
	    
// --- INICIA ----------------------  ESTOS DOS BEAN FUERON DE EJEMPLO PARA CONSULTA CON HIBERNATE    ------------------------------
	    @Autowired
	    @Bean(name = "userDao")
	    public UserDAO getUserDao(SessionFactory sessionFactory) {
	    	return new UserDAOImpl(sessionFactory);
	    }
	    
	    @Autowired
	    @Bean(name = "fileUploadDao")
	    public FileUploadDAO getFileDao(SessionFactory sessionFactory) {
	    	return new FileUploadDAOImpl(sessionFactory);
	    }

// --- TERMINA ----------------------  ESTOS DOS BEAN FUERON DE EJEMPLO PARA CONSULTA CON HIBERNATE    ------------------------------
	    
	    
//	    NO SE USA
//	    @Autowired
//	    @Bean(name = "histrocioDocumentosDao")
//	    public RepositorioDaoHistoricoDocumentos getDaoHistoricoDocumentos(SessionFactory sessionFactory) {
//	    	return new RepositorioImplHistoricoDocumentos(sessionFactory);
//	    }
	    
	    
	   
}
