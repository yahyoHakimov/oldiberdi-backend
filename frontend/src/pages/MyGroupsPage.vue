<template>
  <div class="container py-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <h3 class="fw-bold">My Groups</h3>
      <router-link to="/groups/create" class="btn btn-success btn-sm">+ New Group</router-link>
    </div>

    <div v-if="groups.length">
      <div v-for="group in groups" :key="group.id" class="card mb-3 shadow-sm">
        <div class="card-body">
          <h5 class="card-title mb-3">{{ group.name || 'Unnamed Group' }}</h5>
          <ul class="list-group list-group-flush small">
            <li
                v-for="member in group.members"
                :key="member.id"
                class="list-group-item d-flex justify-content-between"
            >
              <span>{{ member.fullName }}</span>
              <span class="text-muted">{{ member.phoneNumber }}</span>
            </li>
          </ul>
        </div>
      </div>
    </div>

    <div v-else class="alert alert-info text-center">No groups found.</div>
    <div v-if="error" class="alert alert-danger text-center">{{ error }}</div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import api from '../api/axios';

const groups = ref([]);
const error = ref('');

onMounted(async () => {
  try {
    const response = await api.get('/groups/my');
    groups.value = response.data;
  } catch (err) {
    error.value = err.response?.data?.message || 'Failed to load groups';
  }
});
</script>
