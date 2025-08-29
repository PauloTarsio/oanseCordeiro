package br.com.igrejabatistadocordeiro.oanse.core.database;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//@Configuration
public class DataBaseConfiguration {

//	@Value("${spring.datasource.url}")
	private String url;
	
//	@Value("${spring.datasource.username}")
	private String username;
	
//	@Value("${spring.datasource.password}")
	private  String password;
	
//	@Value("${spring.datasource.driver-class-name}")
	private String driver;
	
//	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(url);
		config.setUsername(username);
		config.setPassword(password);
		config.setDriverClassName(driver);
		
		config.setMaximumPoolSize(10);	//max de conexoes
		config.setMinimumIdle(1);		//min de conexoes
		config.setPoolName("oanse-db-pool");
		config.setMaxLifetime(600000); // 10 minutos);
		
		config.setConnectionTimeout(100000); // 100 segundos para conseguir nova conexao
		config.setConnectionTestQuery("select 1");
		
		return new HikariDataSource(config);
	}
	
}
