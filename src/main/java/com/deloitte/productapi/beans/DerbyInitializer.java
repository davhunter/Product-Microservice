package com.deloitte.productapi.beans;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.InitializingBean;

/**
 * This <code>InitializingBean</code> initializes the Derby DB with Product
 * tables and data.
 * 
 * @author David Hunter (Deloitte)
 *
 */
public class DerbyInitializer implements InitializingBean {

	/**
	 * Called by the system when the Bean is ready to be used. Connects to the
	 * Derby DB, creates each table, and populates it with data. Errors are
	 * re-thrown, but not really handled in any way.
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		String dbURL = "jdbc:derby:memory:muleEmbeddedDB;create=true";

		Connection conn = null;
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			conn = DriverManager.getConnection(dbURL);
			DatabaseMetaData md = conn.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);

			int i = 0;
			while (rs.next()) {
				if (rs.getString(3).equalsIgnoreCase("product")) {
					i = 1;
				}
			}

			if (i == 1) {
				return;
			}

			Statement stmt = conn.createStatement();
			stmt.executeUpdate("CREATE TABLE PRODUCT(ID INTEGER," + "NAME VARCHAR(50)," + "DESCRIPTION VARCHAR(4000))");
			stmt.executeUpdate(
					"INSERT INTO PRODUCT(ID,NAME,DESCRIPTION) VALUES(1,'Acme Pole-Vaulting Kit','Amaze your friends! Catch roadrunners! Vault over tall objects, risk free!')");
			stmt.executeUpdate(
					"INSERT INTO PRODUCT(ID,NAME,DESCRIPTION) VALUES(2,'Beginner''s Guide to Anypoint Development','eBook covering Anypoint basics, along with some tips and tricks')");
			stmt.executeUpdate(
					"INSERT INTO PRODUCT(ID,NAME,DESCRIPTION) VALUES(3,'Beer Goggles','Feel like you''re drunk, even at work')");
			stmt.executeUpdate(
					"INSERT INTO PRODUCT(ID,NAME,DESCRIPTION) VALUES(4,'Acme Magic Paint','Handy for painting holes into solid surfaces')");

			stmt.executeUpdate("CREATE TABLE REGION(ID INTEGER," + "NAME_EN VARCHAR(50)," + "NAME_FR VARCHAR(50))");
			stmt.executeUpdate("INSERT INTO REGION(ID,NAME_EN,NAME_FR) VALUES(1,'GTA','GTA')");
			stmt.executeUpdate("INSERT INTO REGION(ID,NAME_EN,NAME_FR) VALUES(2,'Atlantic','Atlantic')");
			stmt.executeUpdate("INSERT INTO REGION(ID,NAME_EN,NAME_FR) VALUES(3,'Montreal','Montreal')");
			stmt.executeUpdate("INSERT INTO REGION(ID,NAME_EN,NAME_FR) VALUES(4,'BC','BC')");

			stmt.executeUpdate("CREATE TABLE PRICE(PRODUCT_ID INTEGER," + "REGION_ID INTEGER," + "PRICE DECIMAL(5,2))");
			stmt.executeUpdate("INSERT INTO PRICE(PRODUCT_ID,REGION_ID,PRICE) VALUES (1,1,100)");
			stmt.executeUpdate("INSERT INTO PRICE(PRODUCT_ID,REGION_ID,PRICE) VALUES (1,2,110)");
			stmt.executeUpdate("INSERT INTO PRICE(PRODUCT_ID,REGION_ID,PRICE) VALUES (1,3,109.5)");
			stmt.executeUpdate("INSERT INTO PRICE(PRODUCT_ID,REGION_ID,PRICE) VALUES (1,4,100)");
			stmt.executeUpdate("INSERT INTO PRICE(PRODUCT_ID,REGION_ID,PRICE) VALUES (2,1,20)");
			stmt.executeUpdate("INSERT INTO PRICE(PRODUCT_ID,REGION_ID,PRICE) VALUES (2,2,20)");
			stmt.executeUpdate("INSERT INTO PRICE(PRODUCT_ID,REGION_ID,PRICE) VALUES (2,3,20)");
			stmt.executeUpdate("INSERT INTO PRICE(PRODUCT_ID,REGION_ID,PRICE) VALUES (2,4,20)");
			stmt.executeUpdate("INSERT INTO PRICE(PRODUCT_ID,REGION_ID,PRICE) VALUES (3,1,1.25)");
			stmt.executeUpdate("INSERT INTO PRICE(PRODUCT_ID,REGION_ID,PRICE) VALUES (3,2,1.5)");
			stmt.executeUpdate("INSERT INTO PRICE(PRODUCT_ID,REGION_ID,PRICE) VALUES (3,3,2)");
			stmt.executeUpdate("INSERT INTO PRICE(PRODUCT_ID,REGION_ID,PRICE) VALUES (3,4,1)");
			stmt.executeUpdate("INSERT INTO PRICE(PRODUCT_ID,REGION_ID,PRICE) VALUES (4,1,50)");
			stmt.executeUpdate("INSERT INTO PRICE(PRODUCT_ID,REGION_ID,PRICE) VALUES (4,2,52)");
			stmt.executeUpdate("INSERT INTO PRICE(PRODUCT_ID,REGION_ID,PRICE) VALUES (4,3,49)");
			stmt.executeUpdate("INSERT INTO PRICE(PRODUCT_ID,REGION_ID,PRICE) VALUES (4,4,55)");

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

}
