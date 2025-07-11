<template>
  <div>
    <nav>
      <router-link v-if="!auth.isLoggedIn" to="/login">Login</router-link> |
      <router-link v-if="!auth.isLoggedIn" to="/register">Register</router-link>

      <template v-if="auth.isLoggedIn">
        <router-link to="/debts">My Debts</router-link> |
        <router-link to="/debts/create">Create Debt</router-link> |
        <router-link to="/groups">My Groups</router-link> |
        <router-link to="/groups/create">Create Group</router-link> |
        <button @click="handleLogout">Logout</button>
      </template>
    </nav>

    <router-view />
  </div>
</template>

<script setup>
import { useAuthStore } from '@/store/auth.js';
import { useRouter } from 'vue-router';

const auth = useAuthStore();
const router = useRouter();

const handleLogout = () => {
  auth.logout();
  router.push('/login');
};
</script>
