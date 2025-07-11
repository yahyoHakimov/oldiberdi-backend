<template>
  <form @submit.prevent="handleLogin">
    <input v-model="phoneNumber" placeholder="Phone Number" />
    <input v-model="password" type="password" placeholder="Password" />
    <button type="submit">Login</button>
  </form>
</template>

<script setup>
import { ref } from 'vue';
import { useAuthStore } from '../store/auth';
import { useRouter } from 'vue-router';

const phoneNumber = ref('');
const password = ref('');
const auth = useAuthStore();
const router = useRouter();

const handleLogin = async () => {
  await auth.login(phoneNumber.value, password.value);
  router.push('/debts');
};
</script>
