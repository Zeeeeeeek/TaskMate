<link rel="stylesheet" href="../assets/auth.css">
<template>
  <div class="auth-div">
    <div class="header">Register</div>
    <div class="auth-form">
      <div class="field">
        <label for="username" class="prevent-select">Username</label>
        <div class="field-input" :class="{'field-error' : invalidUsername}">
          <img src="../assets/icons/user.svg" class="register-icon" alt="">
          <input type="text" placeholder="Username"
                 class="auth-form-input"
                 @blur="validateUsername"
                 v-model="this.username">
        </div>
      </div>
      <div class="field">
        <label for="email" class="prevent-select">Email</label>
        <div class="field-input" :class="{'field-error': invalidEmail}">
          <img src="../assets/icons/email.svg" class="register-icon" alt="">
          <input type="email" placeholder="Email"
                 class="auth-form-input"
                 @blur="validateEmail"
                 v-model="this.email">
        </div>
      </div>
      <div class="field">
        <label for="password" class="prevent-select">Password</label>
        <div class="field-input" :class="{'field-error': invalidPassword}">
          <img src="../assets/icons/lock.svg" class="register-icon" alt="">
          <input type="password" placeholder="Password"
                 class="auth-form-input"
                 @blur="validatePassword"
                 v-model="this.password">
        </div>
      </div>
      <div class="field">
        <label for="confirmPassword" class="prevent-select">Confirm password</label>
        <div class="field-input" :class="{'field-error': invalidConfirmPassword}">
          <img src="../assets/icons/lock.svg" class="register-icon" alt="">
          <input type="password" placeholder="Confirm password"
                 class="auth-form-input"
                 @blur="validateConfirmPassword"
                 v-model="this.confirmPassword">
        </div>
      </div>
    </div>
    <div class="switch-field prevent-select">
      <router-link to="/login">Already a member? Login</router-link>
    </div>
    <Button text="Register" @click="register"/>
    <div class="footer">
      <div class="error-message" v-if="errorMessage !== null">{{ errorMessage }}</div>
    </div>
  </div>
</template>

<script>
import apiService from "@/services/ApiService";
import Button from "@/components/buttons/Button.vue";

export default {
  name: "Register",
  components: {Button},
  data() {
    return {
      invalidUsername: false,
      invalidPassword: false,
      invalidConfirmPassword: false,
      invalidEmail: false,
      password: '',
      username: '',
      email: '',
      confirmPassword: null,
      errorMessage: null,
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
    setErrorMessage(message) {
      this.errorMessage = message
    }
  }
}
</script>

<style scoped>
@import '@/assets/auth.css';
</style>