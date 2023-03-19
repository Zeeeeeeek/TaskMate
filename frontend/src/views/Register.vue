<link rel="stylesheet" href="../assets/auth.css">
<template>
  <div class="auth-div">
    <div class="header">Register</div>
    <div class="auth-form">
      <FormField icon-path="src/assets/icons/user.svg" label="Username" type="text" placeholder="Username" icon-alt="" @update="setUsername" @error="setErrorMessage('Fill credentials')"/>
      <FormField icon-path="src/assets/icons/email.svg" label="Email" type="text" placeholder="Email" icon-alt="" @update="setEmail" @error="setErrorMessage('Fill credentials')"/>
      <FormField icon-path="src/assets/icons/lock.svg" label="Password" type="password" placeholder="Password" icon-alt="" @update="setPassword" @error="setErrorMessage('Fill credentials')"/>
      <FormField icon-path="src/assets/icons/lock.svg" label="Confirm password" type="password" placeholder="Confirm password" icon-alt="" @update="setConfirmPassword" @error="setErrorMessage('Fill credentials')"/>
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
import FormField from "@/components/auth/FormField.vue";

export default {
  name: "Register",
  components: {FormField, Button},
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
    setUsername(username) {
      this.username = username
    },
    setPassword(password) {
      this.password = password
    },
    setEmail(email) {
      this.email = email
    },
    setConfirmPassword(confirmPassword) {
      this.confirmPassword = confirmPassword
    },
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
  },
  beforeMount() {
    if (apiService.isLoggedIn()) {
      this.$router.push('/')
    }
  }
}
</script>

<style scoped>
@import '@/assets/auth.css';
</style>