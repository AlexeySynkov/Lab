package ru.modeles;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.sql.Connection;
import java.sql.SQLException;

public class OkModel implements TableModel {

    private Connection conn;

    public OkModel(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return "Соединение установлено";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Object.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            return conn.getMetaData().getDatabaseProductVersion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
