package be.ugent.flash.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCAbstractDAO {
    private final Connection connection;

    public JDBCAbstractDAO(Connection connection) {
        this.connection = connection;
    }

    protected PreparedStatement prepare(String command) throws SQLException {
        return connection.prepareStatement(command);
    }

    protected PreparedStatement prepareAutoGenerated(String command) throws SQLException {
        return connection.prepareStatement(command, Statement.RETURN_GENERATED_KEYS);
    }
}