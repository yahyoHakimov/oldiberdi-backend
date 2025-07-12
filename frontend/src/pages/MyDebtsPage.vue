<template>
  <div class="container py-4">
    <h3 class="fw-bold mb-4">My Debts</h3>

    <div v-if="debts.length">
      <div v-for="debt in debts" :key="debt.id" class="card mb-3 shadow-sm">
        <div class="card-body">
          <h5 class="card-title mb-2 text-primary">Amount: {{ debt.amount }} UZS</h5>
          <p class="mb-1"><strong>Status:</strong> {{ debt.status }}</p>
          <p class="mb-0"><strong>Due Date:</strong> {{ debt.dueDate }}</p>
        </div>
      </div>
    </div>

    <div v-else class="alert alert-info text-center">No debts found.</div>
    <div v-if="error" class="alert alert-danger text-center">{{ error }}</div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import api from '../api/axios';

const debts = ref([]);
const error = ref('');

onMounted(async () => {
  try {
    const res = await api.get('/debts/my');
    debts.value = res.data;
  } catch (err) {
    error.value = err.response?.data?.message || 'Could not load debts';
  }
});
</script>
