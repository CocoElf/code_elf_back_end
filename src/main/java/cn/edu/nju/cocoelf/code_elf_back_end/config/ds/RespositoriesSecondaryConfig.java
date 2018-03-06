//package cn.edu.nju.cocoelf.code_elf_back_end.config.ds;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.boot.orm.jpa.hibernate.SpringNamingStrategy;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.Database;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EntityScan("com.edu.nju.cocoelf.code_elf_back_end.repository.domain")
//@EnableJpaRepositories(basePackages = "cn.edu.nju.cocoelf.code_elf_back_end.repository",entityManagerFactoryRef = "pgEntityManagerFactory",
//        transactionManagerRef = "pgTransactionManager")
//public class RespositoriesSecondaryConfig {
//
//    @Autowired
////    @Qualifier("pgDataSource")
//    public DataSource pgDataSource;
//
//    @Bean
//    PlatformTransactionManager pgTransactionManager() {
//        return new JpaTransactionManager(pgEntityManagerFactory().getObject());
//    }
//
//    @Bean
//    LocalContainerEntityManagerFactoryBean pgEntityManagerFactory() {
//
//        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
//        jpaVendorAdapter.setGenerateDdl(true);
//        jpaVendorAdapter.setDatabase(Database.POSTGRESQL);
//        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
//
//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//        factoryBean.setDataSource(pgDataSource);
//        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
//        factoryBean.setJpaPropertyMap(jpaProperties());
//
//        return factoryBean;
//    }
//
//    private Map<String, Object> jpaProperties() {
//        Map<String, Object> props = new HashMap<>();
//        props.put("hibernate.ejb.naming_strategy",new SpringNamingStrategy());
//        return props;
//    }
//
//
//}