package com.team10.sigilium.service;

import com.team10.sigilium.model.Expense;
import com.team10.sigilium.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    // Retrieve all expenses
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // Retrieve an expense by id (returns null if not found)
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }

    // Create a new expense; the id will be auto-generated.
    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    // Delete an expense; returns true if deletion was successful
    public boolean deleteExpense(Long id) {
        if (expenseRepository.existsById(id)) {
            expenseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}