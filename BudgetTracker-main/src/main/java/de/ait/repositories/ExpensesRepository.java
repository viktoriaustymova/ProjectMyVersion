package de.ait.repositories;

import de.ait.models.Expense;

import java.util.ArrayList;
import java.util.List;

public interface ExpensesRepository {
    List<Expense> expenses= new ArrayList<>();

    List<Expense> getAllExpenses();
    void save(Expense expense);


    Expense getExpenseById(String expenseId);

    void updateExpense(Expense expense);

    void removeExpense(String expenseToRemove);






}
