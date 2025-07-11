<template>
  <div>
    <h2>My Debts</h2>
    <ul>
      <li v-for="debt in debts" :key="debt.id">
        {{ debt.amount }} - {{ debt.status }} - due {{ debt.dueDate }}
      </li>
    </ul>
    <p v-if="error" style="color: red">{{ error }}</p>
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
