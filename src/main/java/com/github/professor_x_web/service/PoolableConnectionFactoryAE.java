package com.github.professor_x_web.service;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.pool.KeyedObjectPoolFactory;
import org.apache.commons.pool.ObjectPool;

/**
 *
 * @author xin.cao@100credit.com
 */
public class PoolableConnectionFactoryAE extends PoolableConnectionFactory {

    private final int validationTimeout;

    public PoolableConnectionFactoryAE(ConnectionFactory connFactory, ObjectPool pool, KeyedObjectPoolFactory stmtPoolFactory, int validationTimeout, boolean defaultReadOnly, boolean defaultAutoCommit) {
        super(connFactory, pool, stmtPoolFactory, null, defaultReadOnly, defaultAutoCommit);
        this.validationTimeout = validationTimeout;
    }

    @Override
    public void validateConnection(Connection conn) throws SQLException {
        if (conn.isClosed()) {
            throw new SQLException("validateConnection: connection closed");
        }
        if (validationTimeout >= 0 && !conn.isValid(validationTimeout)) {
            throw new SQLException("validateConnection: connection invalid");
        }
    }
}