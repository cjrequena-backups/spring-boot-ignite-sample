package com.aurora.sample.db.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * <p>
 * Need to configure the following for each DataSource:
 * <p>
 * - jdbcUrl=jdbc:oracle:thin:@HOST:PORT/BDD driverClassName=oracle.jdbc.OracleDriver
 * - username=xxxxx
 * - password=xxxxx
 * - maximumPoolSize=5
 * <p>
 * Refer to {@linktourl https://github.com/brettwooldridge/HikariCP/wiki/Configuration}. for more information
 * <p>
 *
 * @author cjrequena
 * @since JDK1.8
 */
@Configuration
public class DatabaseConfiguration {

//    @Bean(name = "dataSource", destroyMethod = "")
//    @ConfigurationProperties(prefix = "datasources.mysql_local")
//    @Validated
//    @Primary
//    public DataSource dataSource() {
//        return new HikariDataSource();
//    }

	@Primary
	@Bean(name = "dataSource", destroyMethod = "")
	public DataSource dataSource() {
		//return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).addScript("sql/oracle_compatibility.sql").addScript("sql/create-db.sql").addScript("sql/insert-data.sql").build();
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("sql/create-db.sql").addScript("sql/insert-data.sql").build();
	}

}
