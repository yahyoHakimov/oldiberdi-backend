<template>
  <div class="d-flex flex-column min-vh-100">
    <!-- Top Navbar -->
    <nav
        v-if="showTopNav"
        class="navbar navbar-light bg-white border-bottom px-3"
    >
      <span class="navbar-brand mb-0 h1">OldiBerdi</span>
      <i class="bi bi-person-circle fs-4"></i>
    </nav>

    <!-- Main Content -->
    <main class="flex-grow-1 container py-3">
      <router-view />
    </main>

    <!-- Bottom Nav -->
    <nav
        v-if="showBottomNav"
        class="navbar navbar-light bg-white border-top fixed-bottom justify-content-around px-0"
    >
      <router-link to="/home" class="nav-link text-center" :class="{ 'text-primary': isActive('/') }">
        <i class="bi bi-house-door-fill fs-4 d-block"></i>
        <small>Home</small>
      </router-link>
      <router-link to="/debts" class="nav-link text-center" :class="{ 'text-primary': isActive('/debts') }">
        <i class="bi bi-credit-card-2-front-fill fs-4 d-block"></i>
        <small>Debts</small>
      </router-link>
      <router-link to="/debts/create" class="nav-link text-center" :class="{ 'text-primary': isActive('/debts/create') }">
        <i class="bi bi-plus-circle-fill fs-4 d-block"></i>
        <small>Add</small>
      </router-link>
      <router-link to="/groups" class="nav-link text-center" :class="{ 'text-primary': isActive('/groups') }">
        <i class="bi bi-people-fill fs-4 d-block"></i>
        <small>Groups</small>
      </router-link>
    </nav>
  </div>
</template>

<script setup>
import { useAuthStore } from '@/store/auth.js';
import { useRouter, useRoute } from 'vue-router';
import { computed } from 'vue';

const auth = useAuthStore();
const router = useRouter();
const route = useRoute();

const hiddenRoutes = ['/login', '/signup'];

const showBottomNav = computed(() => !hiddenRoutes.includes(route.path));
const showTopNav = computed(() => !hiddenRoutes.includes(route.path));

const handleLogout = () => {
  auth.logout();
  router.push('/login');
};

const isActive = (path) => route.path === path;
</script>

<style>
body {
  background-color: #f8f9fa;
}
.navbar-brand {
  font-weight: bold;
}
</style>
