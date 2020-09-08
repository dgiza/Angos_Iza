package edu.espe.controlGastos.database;

import edu.espe.controlGastos.model.MyBudget;

import java.util.List;

public interface BudgetDatabase {
    public List<MyBudget> get();
    public MyBudget get(long id);
    public boolean add(MyBudget budget);
    public boolean edit(MyBudget budget);

    List<MyBudget> getGlobalInMonth(int month);
}
