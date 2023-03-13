<template>
  <div>
    <div class="auth-div">
      <div v-if="!showRegister">
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
          <div @click="invertShowRegister">
            Not a member? Register
          </div>
        </div>
        <div class="submit-field">
          <div class="submitButton prevent-select" @click="login">
            Login
          </div>
        </div>
      </div>
      <div v-else>

      </div>
      <div class="footer">
        <div class="error-message" v-if="errorMessage !== null">{{ errorMessage }}</div>
      </div>

    </div>
  </div>

</template>

<script lang="ts">


import apiService from "../services/ApiService";

export default {
  name: "Auth",
  data() {
    return {
      invalidUsername: false,
      invalidPassword: false,
      invalidConfirmPassword: false,
      invalidEmail: false,
      showRegister: false,
      password: '',
      username: '',
      email: '',
      confirmPassword: null,
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
    validateEmail() {
      this.invalidEmail = this.email === '';
      if (this.invalidEmail) this.setErrorMessage('Fill credentials')
      else this.setErrorMessage(null)
    },
    validateConfirmPassword() {
      if (!this.confirmPassword) {
        this.setErrorMessage('Fill credentials')
        this.invalidConfirmPassword = true
        return
      }
      if (this.confirmPassword !== this.password) {
        this.setErrorMessage('Passwords do not match')
        this.invalidConfirmPassword = true
        this.invalidPassword = true
        return
      }
      this.setErrorMessage(null)
      this.invalidConfirmPassword = false
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
    async register() {
      this.validatePassword()
      this.validateUsername()
      this.validateEmail()
      this.validateConfirmPassword()
      if (this.invalidPassword || this.invalidUsername || this.invalidEmail || this.invalidConfirmPassword) {
        this.setErrorMessage('Invalid credentials')
        return
      }
      try {
        if (await apiService.register(this.username, this.password, this.email)) {
          this.$router.push('/')
        } else {
          this.setErrorMessage('Invalid credentials')
        }
      } catch (e) {
        this.setErrorMessage('Error')
      }
    },
    setErrorMessage(message: string) {
      this.errorMessage = message;
    },
    invertShowRegister() {
      this.showRegister = !this.showRegister;
      this.reset()
    },
    reset() {
      this.invalidUsername = false;
      this.invalidPassword = false;
      this.invalidConfirmPassword = false;
      this.invalidEmail = false;
      this.password = '';
      this.username = '';
      this.email = '';
      this.confirmPassword = null;
      this.errorMessage = null;
    }
  }
}
</script>

<style scoped>
@import '@/assets/auth.css';
</style>