import api from "@/api/axios.js";
import {defineStore} from "pinia";

export const useAuthStore = defineStore('auth', {
    state: () => ({
        token: localStorage.getItem('token') || '',
    }),
    getters: {
        isLoggedIn: (state) => !!state.token,
    },
    actions: {
        async login(phoneNumber, password) {
            const res = await api.post('/auth/login', { phoneNumber, password });
            this.token = res.data.token;
            localStorage.setItem('token', this.token);
        },
        logout() {
            this.token = '';
            localStorage.removeItem('token');
        },
    },
});
