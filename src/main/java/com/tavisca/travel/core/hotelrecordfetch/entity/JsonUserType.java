package com.tavisca.travel.core.hotelrecordfetch.entity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tavisca.travel.core.hotelrecordfetch.model.HotelRecord;

public class JsonUserType implements UserType {

	@Override
	public int[] sqlTypes() {
		return new int[] { Types.JAVA_OBJECT };
	}

	@Override
	public Class<HotelRecord> returnedClass() {
		System.out.println("	");
		System.out.println(Thread.currentThread().getStackTrace());
		return HotelRecord.class;
	}

	@Override
	public boolean equals(final Object x, final Object y) throws HibernateException {
		if (x == null) {
			return y == null;
		}
		System.out.println("x is instance of " + x.getClass().getName());
		return x.equals(y);
	}

	@Override
	public int hashCode(final Object x) throws HibernateException {
		return x.hashCode();
	}

	@Override
	public Object nullSafeGet(final ResultSet rs, final String[] names, final SharedSessionContractImplementor session, final Object owner)
			throws HibernateException, SQLException {
		final String cellContent = rs.getString(names[0]);
		if (cellContent == null) {
			return null;
		}
		try {
			final ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(cellContent.getBytes("UTF-8"), returnedClass());
		} catch (final Exception ex) {
			throw new RuntimeException("Failed to convert String to Invoice: " + ex.getMessage(), ex);
		}
	}

	@Override
	public void nullSafeSet(final PreparedStatement st, final Object value, final int index, final SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, Types.OTHER);
			return;
		}
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final StringWriter w = new StringWriter();
			mapper.writeValue(w, value);
			w.flush();
			st.setObject(index, w.toString(), Types.OTHER);
		} catch (final Exception ex) {
			throw new RuntimeException("Failed to convert Invoice to String: " + ex.getMessage(), ex);
		}

	}

	@Override
	public Object deepCopy(final Object value) throws HibernateException {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(value);
			oos.flush();
			oos.close();
			bos.close();

			ByteArrayInputStream bais = new ByteArrayInputStream(bos.toByteArray());
			return new ObjectInputStream(bais).readObject();
		} catch (ClassNotFoundException | IOException ex) {
			throw new HibernateException(ex);
		}
	}

	@Override
	public boolean isMutable() {
		return true;
	}

	@Override
	public Serializable disassemble(final Object value) throws HibernateException {
		return (Serializable) this.deepCopy(value);
	}

	@Override
	public Object assemble(final Serializable cached, final Object owner) throws HibernateException {
		return this.deepCopy(cached);
	}

	@Override
	public Object replace(final Object original, final Object target, final Object owner) throws HibernateException {
		return this.deepCopy(original);
	}

}
