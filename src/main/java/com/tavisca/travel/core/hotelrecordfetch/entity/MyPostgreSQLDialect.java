package com.tavisca.travel.core.hotelrecordfetch.entity;

import java.sql.Types;

import org.hibernate.spatial.dialect.postgis.PostgisPG94Dialect;

public class MyPostgreSQLDialect extends PostgisPG94Dialect {

	private static final long serialVersionUID = 1L;

	public MyPostgreSQLDialect() {
		super();
		this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
	}

}


