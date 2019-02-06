package com.tavisca.travel.core.hotelrecordfetch.entity;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL94Dialect;

public class MyPostgreSQLDialect extends PostgreSQL94Dialect {

	public MyPostgreSQLDialect() {
		super();
		this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
	}

}
