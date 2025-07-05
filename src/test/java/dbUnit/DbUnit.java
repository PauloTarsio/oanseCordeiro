package dbUnit;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.stereotype.Component;
import org.springframework.test.context.event.annotation.AfterTestExecution;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import io.micrometer.common.util.StringUtils;

@Component
public class DbUnit {

	private static final String DRIVER = "org.postgresql.Driver";
	private static final String JDBC = "jdbc:postgresql://localhost:5432/oanse_test";
	private static final String USER = "postgres";
	private static final String PASSWORD = "123";

	private IDatabaseTester iDatabaseTester;
	private String tableName;
	private String datasetPath;

	@BeforeTestExecution
	public void setUp() throws Exception {
		iDatabaseTester = new JdbcDatabaseTester(DRIVER, JDBC, USER, PASSWORD);
		
		 // Adiciona a configuração do tipo de dado específico do PostgreSQL
	    iDatabaseTester.getConnection().getConfig()
	        .setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new PostgresqlDataTypeFactory());

		iDatabaseTester.onTearDown();
		resetSequences();

		// Carrega o dataset XML inicial
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream(getDatasetPath()));
		iDatabaseTester.setDataSet(dataSet);
		iDatabaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		iDatabaseTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);

		// Configura o banco com os dados iniciais
		iDatabaseTester.onSetup();
	}

	@AfterTestExecution
	public void tearDown() throws Exception {
		iDatabaseTester.onTearDown();
		resetSequences();
	}

	private void resetSequences() throws Exception {
	    try (Connection connection = DriverManager.getConnection(JDBC, USER, PASSWORD);
	         Statement statement = connection.createStatement()) {
	        
	        String sequenceName = StringUtils.isNotBlank(tableName) ? tableName.toLowerCase() + "_id_seq" : null;
	        if (StringUtils.isNotBlank(sequenceName)) {
	            // Verifica se a sequência existe
	            String checkSeqSql = "SELECT 1 FROM pg_class WHERE relkind = 'S' AND relname = '" + sequenceName + "'";
	            try (ResultSet rs = statement.executeQuery(checkSeqSql)) {
	                if (rs.next()) {
	                    // Sequência existe, reseta
	                    statement.executeUpdate("ALTER SEQUENCE " + sequenceName + " RESTART WITH 1");
	                } else {
	                    System.out.println("Sequência " + sequenceName + " não existe. Ignorando reset.");
	                }
	            }
	        }
	    }
	}


	public String getDatasetPath() {
		return datasetPath;
	}

	public void setDatasetPath(String datasetPath) {
		this.datasetPath = datasetPath;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
