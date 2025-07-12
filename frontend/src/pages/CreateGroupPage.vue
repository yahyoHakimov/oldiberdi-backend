<template>
  <div class="container py-4">
    <h3 class="fw-bold mb-4 text-center">Create Group</h3>

    <form @submit.prevent="createGroup" class="card p-3 shadow-sm">
      <div class="mb-3">
        <label for="groupName" class="form-label">Group Name (optional)</label>
        <input
            v-model="groupName"
            id="groupName"
            class="form-control"
            placeholder="Enter group name"
        />
      </div>

      <div class="mb-3">
        <label class="form-label">Member Phone Numbers</label>
        <div v-for="(phone, index) in memberPhones" :key="index" class="input-group mb-2">
          <input
              v-model="memberPhones[index]"
              class="form-control"
              placeholder="+998..."
          />
          <button
              type="button"
              class="btn btn-outline-danger"
              @click="removePhone(index)"
              v-if="memberPhones.length > 1"
          >
            ❌
          </button>
        </div>
        <button
            type="button"
            class="btn btn-outline-secondary btn-sm"
            @click="addPhone"
        >
          ➕ Add Member
        </button>
      </div>

      <button type="submit" class="btn btn-primary w-100">Create Group</button>
    </form>

    <div v-if="success" class="alert alert-success mt-3 text-center">
      {{ success }}
    </div>
    <div v-if="error" class="alert alert-danger mt-3 text-center">
      {{ error }}
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import api from '../api/axios';

const groupName = ref('');
const memberPhones = ref(['']);
const success = ref('');
const error = ref('');

const addPhone = () => {
  memberPhones.value.push('');
};

const removePhone = (index) => {
  memberPhones.value.splice(index, 1);
};

const createGroup = async () => {
  try {
    await api.post('/groups', {
      name: groupName.value,
      memberPhones: memberPhones.value.filter(p => p.trim() !== ''),
    });

    success.value = 'Group created successfully';
    error.value = '';
    groupName.value = '';
    memberPhones.value = [''];
  } catch (err) {
    error.value = err.response?.data?.message || 'Failed to create group';
    success.value = '';
  }
};
</script>
