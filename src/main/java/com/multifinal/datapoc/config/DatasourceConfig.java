package com.multifinal.datapoc.config;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.stereotype.Component;

import com.multifinal.datapoc.repository.EmployeeRepository;
import com.multifinal.datapoc.repository.RetailerRepository;
import com.multifinal.datapoc.repository.StudentRepository;



@Configuration
@Component
public class DatasourceConfig {

	// Primary DataSource
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.primary") 
	public JndiPropertyHolder jndiPrimary() {
		return new JndiPropertyHolder();
	}
	
	public DataSource datasourcePrimary() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		return dataSourceLookup.getDataSource(jndiPrimary().getJndiName());
	}
	
	public SqlSessionFactory sessionFactoryPrimary() throws Exception {
		DataSource dataSource=datasourcePrimary();
		return getSessionFactory(dataSource);
	}
	
	public SqlSessionTemplate sessionTemplatePrimary() throws Exception {
		SqlSessionFactory sqlSessionFactory=sessionFactoryPrimary();
		return new SqlSessionTemplate(sqlSessionFactory);		
	}
	
	// Secondary DataSource
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.secondary")
	public JndiPropertyHolder jndiSecondary() {
		return new JndiPropertyHolder();
	}
  
	public DataSource datasourceSecondary() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		return dataSourceLookup.getDataSource(jndiSecondary().getJndiName());
	}
  
	public SqlSessionFactory sessionFactorySecondary()throws Exception {
		DataSource dataSource=datasourceSecondary();
		return getSessionFactory(dataSource);
	}
  
	public SqlSessionTemplate sessionTemplateSecondary() throws Exception {
		SqlSessionFactory sqlSessionFactory=sessionFactorySecondary();
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	// Tertiary DataSource
	
		@Bean
		@ConfigurationProperties(prefix = "spring.datasource.tertiary")
		public JndiPropertyHolder jndiTertiary() {
			return new JndiPropertyHolder();
		}
	  
		public DataSource datasourceTertiary() {
			JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
			return dataSourceLookup.getDataSource(jndiTertiary().getJndiName());
		}
	  
		public SqlSessionFactory sessionFactoryTertiary()throws Exception {
			DataSource dataSource=datasourceTertiary();
			return getSessionFactory(dataSource);
		}
	  
		public SqlSessionTemplate sessionTemplateTertiary() throws Exception {
			SqlSessionFactory sqlSessionFactory=sessionFactoryTertiary();
			return new SqlSessionTemplate(sqlSessionFactory);
		}
 	
	
	private SqlSessionFactory getSessionFactory(DataSource dataSource) {
		SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		SqlSessionFactory sqlSessionFactory;
		try {
			sqlSessionFactory=bean.getObject();
			sqlSessionFactory.getConfiguration().addMapper(EmployeeRepository.class);
			sqlSessionFactory.getConfiguration().addMapper(StudentRepository.class);
			sqlSessionFactory.getConfiguration().addMapper(RetailerRepository.class);
			
			return sqlSessionFactory;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static class JndiPropertyHolder {
		private String jndiName;

		public String getJndiName() {
			return jndiName;
		}

		public void setJndiName(String jndiName) {
			this.jndiName = jndiName;
		}		
	}
}