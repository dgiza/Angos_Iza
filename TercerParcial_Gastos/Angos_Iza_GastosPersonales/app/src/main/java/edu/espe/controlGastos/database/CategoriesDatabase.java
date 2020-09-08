package edu.espe.controlGastos.database;

import edu.espe.controlGastos.model.MyCategory;

import java.util.List;


public interface CategoriesDatabase {
    public List<MyCategory> getIncomes();
    public List<MyCategory> getExpenses();
    public MyCategory get(long id);
    public boolean add(MyCategory myCategory);
    public boolean edit(MyCategory myCategory);
    public boolean remove(String title);
}
