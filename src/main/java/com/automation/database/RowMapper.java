
package com.automation.database;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface RowMapper<T> {

    T mapear(ResultSet resultSet) throws SQLException;
}