package ru.modeles;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.sql.ResultSet;

public class ExpressionSetTableModel implements TableModel  {
    private int rows=0;
    public ExpressionSetTableModel(int rows) {
        this.rows=rows;
    }

    @Override
    public int getRowCount() {
        // TODO Implement this method
        return 1;
    }

    @Override
    public int getColumnCount() {
        // TODO Implement this method
        return 1;
    }

    @Override
    public String getColumnName(int i) {
        // TODO Implement this method
        return new String[]{"Выполнено!!!"}[i];
    }

    @Override
    public Class<?> getColumnClass(int i) {
        // TODO Implement this method
        return Object.class;
    }

    @Override
    public boolean isCellEditable(int i, int i2) {
        // TODO Implement this method
        return false;
    }

    @Override
    public Object getValueAt(int i, int i2) {
        // TODO Implement this method
        return "Выполнено, обработано :"+this.rows+" строк!";
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
