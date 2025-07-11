<template>
  <div>
    <h2>Create Group</h2>
    <form @submit.prevent="createGroup">
      <input v-model="groupName" placeholder="Group Name (optional)" />

      <h4>Member Phone Numbers:</h4>
      <div v-for="(phone, index) in memberPhones" :key="index">
        <input v-model="memberPhones[index]" placeholder="+998901234567" />
        <button type="button" @click="removePhone(index)">❌</button>
      </div>

      <button type="button" @click="addPhone">➕ Add Member</button>

      <br /><br />
      <button type="submit">Create Group</button>
    </form>

    <p v-if="success" style="color: green">{{ success }}</p>
    <p v-if="error" style="color: red">{{ error }}</p>
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
    const response = await api.post('/groups', {
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
