package de.ait.repositories;

import de.ait.models.Category;
import de.ait.models.Expense;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpensesRepositoryText implements ExpensesRepository{
    private String fileName;

    public ExpensesRepositoryText(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Expense> getAllExpenses() {
        List<Expense> expenses = new ArrayList<>();
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line = bufferedReader.readLine();

            while (line != null) {
                Expense expenses1 = parseLine(line);
                expenses.add(expenses1);
                line = bufferedReader.readLine();
            }
        } catch (IOException | ParseException e) {
            System.err.println("Что-то пошло не так");
        }
        return expenses;
    }

    private static Expense parseLine(String line) throws ParseException {
        String[] parsed = line.split("\\|");
        String title = parsed[0];
        double sumExpenses = Double.parseDouble(parsed[1]);
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(parsed[2]);
        Category category = Category.valueOf(parsed[3]);

        return new Expense(title,category, sumExpenses, date);
    }


    @Override
    public void save(Expense expense) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(fileName, true))){

            String newExpense = expense.getTitle() + "|" +
                    expense.getCategory() + "|" +
                    expense.getSumExpenses() + "|" +
                    expense.getDate();
            bufferedWriter.write(newExpense);
            bufferedWriter.newLine();

        }catch(IOException e){
            System.out.println("Произошла ошибка");
        }

    }

    @Override
    public Expense getExpenseById(String expenseId) {
        return null;
    }

    @Override
    public void updateExpense(Expense expense) {
    }




    @Override
    public void removeExpense(String expenseToDelete) {
        List<Expense> expenses = new ArrayList<>();//создаем массив элементов расходов
        try {
            File expensesExistFile = new File("expenses.txt");// читаем исходный файл
            File expensesTempFile = new File("expensesTemp.txt");// создаем временный файл
            BufferedReader reader = new BufferedReader(new FileReader("expenses.txt"));//читаем исходный файл
            BufferedWriter writer = new BufferedWriter(new FileWriter(expensesTempFile));//записываем в новый файл

            expenseToDelete = reader.readLine();

            while ((expenseToDelete !=null)) {
                for (Expense expense:expenses){
                    if (!expenseToDelete.equals(expense.getTitle())) {
                        writer.write(expenseToDelete);
                    }
                }
            }
            System.out.println("Расход успешно удален");
            expensesExistFile.delete();
            expensesTempFile.renameTo(expensesExistFile);

        } catch (IOException e) {
            System.out.println("Произошла ошибка");
        }


    }


}
