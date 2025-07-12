<template>
  <div class="container py-4">
    <h3 class="fw-bold mb-4 text-center">Create New Debt</h3>

    <form @submit.prevent="createDebt" class="mx-auto" style="max-width: 420px">
      <div class="mb-3">
        <label class="form-label">Amount</label>
        <input
            v-model="amount"
            type="number"
            class="form-control"
            placeholder="Enter amount"
            required
        />
      </div>

      <div class="mb-3">
        <label class="form-label">Borrower Phone</label>
        <input
            v-model="borrowerPhone"
            class="form-control"
            placeholder="+998..."
            required
        />
      </div>

      <div class="mb-4">
        <label class="form-label">Due Date</label>
        <input
            v-model="dueDate"
            type="date"
            class="form-control"
            required
        />
      </div>

      <button type="submit" class="btn btn-primary w-100">
        Submit
      </button>
    </form>

    <p v-if="success" class="alert alert-success mt-3 text-center">
      {{ success }}
    </p>
    <p v-if="error" class="alert alert-danger mt-3 text-center">
      {{ error }}
    </p>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import api from '../api/axios';

const amount = ref('');
const borrowerPhone = ref('');
const dueDate = ref('');
const success = ref('');
const error = ref('');

const createDebt = async () => {
  try {
    await api.post('/debts', {
      amount: parseFloat(amount.value),
      borrowerPhone: borrowerPhone.value,
      dueDate: dueDate.value,
    });
    success.value = 'Debt successfully created';
    error.value = '';
    amount.value = '';
    borrowerPhone.value = '';
    dueDate.value = '';
  } catch (err) {
    error.value = err.response?.data?.message || 'Error creating debt';
    success.value = '';
  }
};
</script>
