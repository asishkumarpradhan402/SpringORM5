package com.main.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.main.dao.StudentDao;
import com.main.model.Course;
import com.main.model.Student;

@Configuration
@ComponentScan(basePackages = { "com.main" })
@EnableTransactionManagement
public class AppConfiguration {

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/test");
		driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
		driverManagerDataSource.setUsername("postgres");
		driverManagerDataSource.setPassword("root");
		return driverManagerDataSource;
	}

	@Bean
	public LocalSessionFactoryBean getLocalSessionFactoryBean() {
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(getDataSource());
		Properties properties = new Properties();
		properties.put("dialect", "org.hibernate.dialect.PostgreSQLDialect");
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.format_sql", true);
		localSessionFactoryBean.setHibernateProperties(properties);
		localSessionFactoryBean.setAnnotatedClasses(getAnnotatedClass());
		return localSessionFactoryBean;
	}

	private Class<?>[] getAnnotatedClass() {
		List<Class<?>> listOfClasses = new ArrayList<>();
		listOfClasses.add(Student.class);
		listOfClasses.add(Course.class);
		Class<?>[] claases = new Class<?>[listOfClasses.size()];
		for (int i = 0; i < claases.length; i++) {
			claases[i] = listOfClasses.get(i);
		}
		return claases;
	}

	@Bean(name = { "hibernateTemplate" })
	public HibernateTemplate getHibernateTemplate() {
		SessionFactory sessionFactory = getLocalSessionFactoryBean().getObject();
		HibernateTemplate hibernateTemplate = new HibernateTemplate();
		hibernateTemplate.setSessionFactory(sessionFactory);
//		hibernateTemplate.setCheckWriteOperations(false);
		return hibernateTemplate;
	}

	@Bean(name = { "hibernateTransactionManager" })
	public HibernateTransactionManager getHibernateTransactionManager() {
		SessionFactory sessionFactory = getLocalSessionFactoryBean().getObject();
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(sessionFactory);
		return hibernateTransactionManager;
	}

	@Bean(name = { "studentDao" })
	public StudentDao getStudentDao() {
		StudentDao studentDao = new StudentDao();
		studentDao.setHibernateTemplate(getHibernateTemplate());
		return studentDao;
	}
}
