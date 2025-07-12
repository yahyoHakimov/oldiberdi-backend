<template>
  <div class="container py-4">
    <div class="text-center mb-4">
      <h2 class="fw-bold">Welcome back, {{ userName }}!</h2>
      <p class="text-muted">Manage your loans and groups efficiently</p>
    </div>

    <div class="d-grid gap-3">
      <router-link to="/debts" class="card shadow-sm text-decoration-none text-dark">
        <div class="card-body d-flex justify-content-between align-items-center">
          <div>
            <h5 class="card-title fw-semibold">My Debts</h5>
            <p class="card-text text-muted mb-0">View and manage your debts</p>
          </div>
          <i class="bi bi-credit-card fs-4 text-secondary"></i>
        </div>
      </router-link>

      <router-link to="/debts/create" class="card shadow-sm text-decoration-none text-dark">
        <div class="card-body d-flex justify-content-between align-items-center">
          <div>
            <h5 class="card-title fw-semibold">Create Debt</h5>
            <p class="card-text text-muted mb-0">Record a new debt</p>
          </div>
          <i class="bi bi-plus-circle fs-4 text-secondary"></i>
        </div>
      </router-link>

      <router-link to="/groups" class="card shadow-sm text-decoration-none text-dark">
        <div class="card-body d-flex justify-content-between align-items-center">
          <div>
            <h5 class="card-title fw-semibold">My Groups</h5>
            <p class="card-text text-muted mb-0">Manage lending groups</p>
          </div>
          <i class="bi bi-people fs-4 text-secondary"></i>
        </div>
      </router-link>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

const userName = ref('Demo'); // Can be set from store/localStorage in the future

onMounted(() => {
  const name = localStorage.getItem('userName');
  if (name) userName.value = name;
});
</script>
