<template>
  <div class="auth-div">
    <div class="header">Login</div>
    <div class="auth-form">
      <div class="field">
        <label for="username" class="prevent-select">Username</label>
        <div class="field-input" :class="{'field-error': invalidUsername}">
          <img src="../assets/icons/user.svg" class="login-icon" alt="">
          <input type="text" placeholder="Username"
                 class="auth-form-input"
                 @blur="validateUsername"
                 v-model="this.username">
        </div>
      </div>
      <div class="field">
        <label for="password" class="prevent-select">Password</label>
        <div class="field-input" :class="{'field-error': invalidPassword}">
          <img src="../assets/icons/lock.svg" class="login-icon" alt="">
          <input type="password" placeholder="Password"
                 class="auth-form-input"
                 @blur="validatePassword"
                 v-model="this.password">
        </div>
      </div>
    </div>
    <div class="switch-field prevent-select">
      <router-link to="/register">
        Not a member? Register
      </router-link>
    </div>
    <Button text="Login" @click="login"/>
    <div class="footer">
      <div class="error-message" v-if="errorMessage !== null">{{ errorMessage }}</div>
    </div>
  </div>
</template>

<script>
import apiService from "@/services/ApiService";
import Button from "@/components/buttons/Button.vue";

export default {
  name: "Login",
components: {Button},
  data() {
    return {
      invalidUsername: false,
      invalidPassword: false,
      password: '',
      username: '',
      errorMessage: null
    }
  },
  methods: {
    validateUsername() {
      this.invalidUsername = this.username === '';
      if (this.invalidUsername) this.setErrorMessage('Fill credentials')
      else this.setErrorMessage(null)
    },
    validatePassword() {
      this.invalidPassword = this.password === '';
      if (this.invalidPassword) this.setErrorMessage('Fill credentials')
      else this.setErrorMessage(null)
    },
    async login() {
      this.validatePassword()
      this.validateUsername()
      if (this.invalidPassword || this.invalidUsername) {
        this.setErrorMessage('Fill credentials')
        return
      }
      try {
        if (await apiService.login(this.username, this.password)) {
          this.$router.push('/')
        } else {
          this.setErrorMessage('Invalid credentials')
        }
      } catch (e) {
        this.setErrorMessage('Error')
      }
    },
    setErrorMessage(message) {
      this.errorMessage = message
    }
  }
}
</script>

<style scoped>
@import '@/assets/auth.css';
</style>