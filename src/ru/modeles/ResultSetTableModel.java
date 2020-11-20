package ru.modeles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ResultSetTableModel implements TableModel  {
    private ArrayList<String> columns=new ArrayList<>();
    private ArrayList<ArrayList> data=new ArrayList<>();
    public ResultSetTableModel(ResultSet resultSet) {
        try {
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                columns.add(resultSet.getMetaData().getColumnName(i));
            }

            while(resultSet.next()){
                ArrayList<Object> row=new ArrayList<>(resultSet.getMetaData().getColumnCount());
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    row.add(resultSet.getObject(i));
                }
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getRowCount() {

        return data.size();
    }

    @Override
    public int getColumnCount() {

        return columns.size();
    }

    @Override
    public String getColumnName(int i) {
        return columns.get(i);
    }

    @Override
    public Class<?> getColumnClass(int i) {
        return Object.class;
    }

    @Override
    public boolean isCellEditable(int i, int i2) {
        return false;
    }

    @Override
    public Object getValueAt(int r, int c) {
        return data.get(r).get(c);
    }

    @Override
    public void setValueAt(Object object, int i, int i2) {
        // TODO Implement this method

    }

    @Override
    public void addTableModelListener(TableModelListener tableModelListener) {
        // TODO Implement this method
    }

    @Override
    public void removeTableModelListener(TableModelListener tableModelListener) {
        // TODO Implement this method
    }
}
