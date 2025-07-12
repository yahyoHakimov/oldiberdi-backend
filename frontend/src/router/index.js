import { createRouter, createWebHistory } from 'vue-router';
import LoginPage from '../pages/LoginPage.vue';
import CreateGroupPage from "@/pages/CreateGroupPage.vue";
import MyGroupsPage from "@/pages/MyGroupsPage.vue";
import DashboardPage from "@/pages/DashboardPage.vue";
import RegisterPage from "@/pages/RegisterPage.vue";
import MyDebtsPage from "@/pages/MyDebtsPage.vue";
import CreateDebtPage from "@/pages/CreateDebtPage.vue";
// import RegisterPage from '../pages/RegisterPage.vue';
// import MyDebtsPage from '../pages/MyDebtsPage.vue';
// import CreateDebtPage from '../pages/CreateDebtPage.vue';
// import CreateGroupPage from '../pages/CreateGroupPage.vue';
// import MyGroupsPage from '../pages/MyGroupsPage.vue';

const routes = [
  {
    path: '/',
    redirect: '/login',
  },
  {
    path: '/login',
    name: 'Login',
    component: LoginPage,
  },
  {
    path: '/groups/create',
    name: 'CreateGroup',
    component: CreateGroupPage,
  },
  {
    path: '/register',
    name: 'Register',
    component: RegisterPage,
  },
  {
    path: '/debts',
    name: 'MyDebts',
    component: MyDebtsPage,
  },
  {
    path: '/debts/create',
    name: 'CreateDebt',
    component: CreateDebtPage,
  },
  {
    path: '/groups',
    name: 'MyGroups',
    component: MyGroupsPage,
  },{
    path: '/home',
    name: 'DashboardPage',
    component: DashboardPage,
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

router.beforeEach((to, from, next) => {
  const publicPages = ['/login', '/register'];
  const authRequired = !publicPages.includes(to.path);
  const token = localStorage.getItem('token');

  if (authRequired && !token) {
    return next('/login');
  }

  next();
});

export default router;
