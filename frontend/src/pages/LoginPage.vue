<template>
  <div class="container d-flex flex-column justify-content-center align-items-center vh-100">
    <div class="text-center mb-4">
      <h1 class="fw-bold">OldiBerdi</h1>
      <p class="text-muted">Peer-to-peer loan management made simple</p>
    </div>

    <div class="card shadow-sm w-100" style="max-width: 400px;">
      <div class="card-body">
        <h3 class="text-center fw-semibold mb-1">Welcome Back</h3>
        <p class="text-center text-muted mb-4">Sign in to your account</p>

        <form @submit.prevent="handleLogin">
          <div class="mb-3">
            <label class="form-label">Phone Number</label>
            <input
                v-model="phoneNumber"
                type="tel"
                class="form-control"
                placeholder="+998901234567"
                required
            />
          </div>

          <div class="mb-4">
            <label class="form-label">Password</label>
            <input
                v-model="password"
                type="password"
                class="form-control"
                placeholder="Password"
                required
            />
          </div>

          <button type="submit" class="btn btn-dark w-100">Sign In</button>
        </form>

        <div class="text-center mt-3">
          <small class="text-muted">Don't have an account?</small>
          <router-link to="/register" class="ms-1">Sign up</router-link>
        </div>
      </div>
    </div>
  </div>
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
