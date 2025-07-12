<template>
  <div class="container d-flex flex-column justify-content-center align-items-center vh-100">
    <div class="text-center mb-4">
      <h1 class="fw-bold">OldiBerdi</h1>
      <p class="text-muted">Peer-to-peer loan management made simple</p>
    </div>

    <div class="card shadow-sm w-100" style="max-width: 400px;">
      <div class="card-body">
        <h3 class="text-center fw-semibold mb-1">Create Account</h3>
        <p class="text-center text-muted mb-4">Sign up to get started</p>

        <form @submit.prevent="handleRegister">
          <div class="mb-3">
            <label class="form-label">Full Name</label>
            <input
                v-model="fullName"
                type="text"
                class="form-control"
                placeholder="Enter your name"
                required
            />
          </div>

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

          <button type="submit" class="btn btn-primary w-100">Sign Up</button>
        </form>

        <div class="text-center mt-3">
          <small class="text-muted">Already have an account?</small>
          <router-link to="/login" class="ms-1">Login</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import api from '../api/axios';

const fullName = ref('');
const phoneNumber = ref('');
const password = ref('');
const router = useRouter();

const handleRegister = async () => {
  await api.post('/auth/register', {
    fullName: fullName.value,
    phoneNumber: phoneNumber.value,
    password: password.value
  });
  router.push('/login');
};
</script>
