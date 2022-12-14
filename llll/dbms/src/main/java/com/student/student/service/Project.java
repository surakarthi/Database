package com.student.student.service;


import java.sql.*;

public class Project {

	public static void main(String[] args) throws Exception{
		String mysqlUrl = "jdbc:mysql://localhost:3307/FinalProject";
		Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
		System.out.println("Connection established......");
		//Setting auto-commit false
		con.setAutoCommit(false);
		System.out.println("Auto commit value is: "+con.getAutoCommit());

		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rs = null;

			// atomicity.
			stmt.executeUpdate("create table Product(" + "prodid char(5), pname char(20), price double precision)");

			// altering table Product by adding prodid as primary key
			stmt.executeUpdate("alter table Product p ADD CONSTRAINT pk_product PRIMARY KEY(p.prodid)");

			// inserting values into prodid, pname and price
			stmt.executeUpdate(
					"insert into Product p(p.prodid,p.pname,p.price) values('p1','tape',2.5), ('p2','tv',250), ('p3','vcr',80)");

			// creating table Depot with dep, addr and volume
			stmt.executeUpdate("create table Depot(" + "dep char(5), addr char(20), volume double precision)");

			// altering table Depot by adding dep as the primary key
			stmt.executeUpdate("alter table Depot ADD CONSTRAINT pk_depot PRIMARY KEY(dep)");

			// inserting values into dep, addr and volume
			stmt.executeUpdate(
					"insert into Depot d (d.dep,d.addr,d.volume) values('d1','New York',9000), ('d2','Syracuse',6000), ('d4','New York',2000)");

			// creating table Stock with prod, dep and quantity
			stmt.executeUpdate("create table Stock(" + "prod char(5), dep char(5), quantity double precision)");

			// altering table Stock by adding dep as a foreign key
			stmt.executeUpdate(
					"alter table Stock s ADD CONSTRAINT pk_stock FOREIGN KEY(s.dep) REFERENCES Depot d (d.dep)");

			// altering table Stock by adding prod as a foreign key
			stmt.executeUpdate(
					"alter table Stock ADD CONSTRAINT pk_stockprod FOREIGN KEY(prod) REFERENCES Product (prodid)");

			// inserting values into prod, dep and quantity
			stmt.executeUpdate(
					"insert into Stock s (s.prod,s.dep,s.quantity) values ('p1','d1',1000), ('p1','d2',-100), ('p1','d4',1200), ('p3','d1',3000), ('p3','d4',2000), ('p2','d4',1500),('p2','d1',-400),('p2','d2',2000)");

			// deleting p1 from prodid from Product and Stock table
			stmt.executeUpdate(
					"delete Product, Stock FROM	p AS Product JOIN s AS Stock ON p.prodid = s.prod WHERE prodid= 'p1'");
			stmt.executeUpdate("DELETE FROM  Stock Where prod='p1'");
			stmt.executeUpdate("DELETE FROM  Product Where prodid='p1'");

			// moving to the next row
			rs.next();

			// setAutoCommit set to false - automicity
			con.setAutoCommit(false);

		} catch (SQLException e) {
			System.out.println("Execution has completed");
			// For atomicity
			con.rollback();
			stmt.close();
			con.close();
			return;
		}
		con.commit();
		stmt.close();
		con.close();
	}



	}



