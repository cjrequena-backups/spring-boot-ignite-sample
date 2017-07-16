package com.aurora.sample.db.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

/**
 * <p>
 * <p>
 * <p>
 * <p>
 * @author cjrequena
 * @version 1.0
 * @since JDK1.8
 * @see
 *
 */
@MappedTypes(LocalDateTime.class)
public class LocalDateTimeTypeHandler implements TypeHandler<LocalDateTime> {
	@Override
	public void setParameter(final PreparedStatement ps, final int index, final LocalDateTime parameter, final JdbcType jdbcType)
			throws SQLException {
		final LocalDateTime datetime = parameter;
		if (datetime != null) {
			ps.setTimestamp(index, Timestamp.valueOf(parameter));
		} else {
			ps.setTimestamp(index, null);
		}
	}

	@Override
	public LocalDateTime getResult(final ResultSet rs, final String columnName) throws SQLException {
		final Timestamp timestamp = rs.getTimestamp(columnName);
		if (timestamp != null) {
			return timestamp.toLocalDateTime();
		} else {
			return null;
		}
	}

	@Override
	public LocalDateTime getResult(final ResultSet rs, final int columnIndex) throws SQLException {
		final Timestamp timestamp = rs.getTimestamp(columnIndex);
		if (timestamp != null) {
			return timestamp.toLocalDateTime();
		} else {
			return null;
		}
	}

	@Override
	public LocalDateTime getResult(final CallableStatement cs, final int columnIndex) throws SQLException {
		final Timestamp timestamp = cs.getTimestamp(columnIndex);
		if (timestamp != null) {
			return timestamp.toLocalDateTime();
		} else {
			return null;
		}
	}
}
