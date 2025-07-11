<template>
  <div>
    <h2>Create New Debt</h2>
    <form @submit.prevent="createDebt">
      <input v-model="amount" type="number" placeholder="Amount" required />
      <input v-model="borrowerPhone" placeholder="Borrower Phone (+998...)" required />
      <input v-model="dueDate" type="date" required />
      <button type="submit">Submit</button>
    </form>
    <p v-if="success" style="color: green">{{ success }}</p>
    <p v-if="error" style="color: red">{{ error }}</p>
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
