<template>
  <div>
    <h2>User Registration</h2>
    <form @submit.prevent="handleRegister">
      <input v-model="name" placeholder="Full Name" required />
      <input v-model="phoneNumber" placeholder="Phone Number (+998...)" required />
      <input v-model="password" type="password" placeholder="Password" required />
      <button type="submit">Register</button>
    </form>
    <p v-if="error" style="color: red">{{ error }}</p>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import api from '../api/axios';

const name = ref('');
const phoneNumber = ref('');
const password = ref('');
const error = ref('');
const router = useRouter();

const handleRegister = async () => {
  try {
    await api.post('/auth/register', {
      fullName: name.value,
      phoneNumber: phoneNumber.value,
      password: password.value,
    });
    router.push('/login');
  } catch (err) {
    error.value = err.response?.data?.message || 'Registration failed';
  }
};
</script>
