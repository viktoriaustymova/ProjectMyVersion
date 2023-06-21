package de.ait.services;

import de.ait.models.Category;
import de.ait.models.Expense;
import de.ait.repositories.ExpensesRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpensesServicesImpl implements ExpensesServices{
    private ExpensesRepository expensesRepository;

    public ExpensesServicesImpl(ExpensesRepository expensesRepository) {
        this.expensesRepository = expensesRepository;
    }

    public List<Expense> getAll() {

        return expensesRepository.getAllExpenses();
    }

    @Override
    public void addNewExpense(String title, Category category, double sumExpenses, Date date) {
        Expense newExpense = new Expense(title, category, sumExpenses, date);

    }


    public void changeExpenseAmount(String expenseId, double newAmount) {
        Expense expense = expensesRepository.getExpenseById(expenseId);
        if (expense != null) {
            expense.setAmount(newAmount);
            expensesRepository.updateExpense(expense);
            System.out.println("Сумма расхода успешно изменена");
        } else {
            System.out.println("Расход с указанным идентификатором не найден");
        }
    }




}
