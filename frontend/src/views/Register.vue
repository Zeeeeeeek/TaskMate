<link rel="stylesheet" href="../assets/auth.css">
<template>
  <div class="auth-div">
    <div class="header">Register</div>
    <div class="auth-form">
      <FormField icon-path="src/assets/icons/user.svg" label="Username" type="text" placeholder="Username" icon-alt=""
                 v-model="username" v-bind:error="invalidUsername"/>
      <FormField icon-path="src/assets/icons/email.svg" label="Email" type="text" placeholder="Email" icon-alt=""
                 v-model="email" v-bind:error="invalidEmail"/>
      <FormField icon-path="src/assets/icons/lock.svg" label="Password" type="password" placeholder="Password" icon-alt=""
                 v-model="password" v-bind:error="invalidPassword"/>
      <FormField icon-path="src/assets/icons/lock.svg" label="Confirm password" type="password" placeholder="Confirm password" icon-alt=""
                 v-model="confirmPassword" v-bind:error="invalidConfirmPassword"/>
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
      confirmPassword: '',
      errorMessage: null,
    }
  },
  methods: {
    validateForm() {
      this.invalidPassword = this.password === '';
      this.invalidUsername = this.username === '';
      this.invalidEmail = this.email === '';
      this.invalidConfirmPassword = this.confirmPassword === '' || this.confirmPassword !== this.password;
      this.updateErrorMessage()
    },
    async register() {
      this.validateForm()
      if (this.invalidPassword || this.invalidUsername || this.invalidEmail || this.invalidConfirmPassword)
        return
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
    },
    updateErrorMessage() {
      if (this.invalidPassword || this.invalidUsername || this.invalidEmail) {
        this.setErrorMessage('Invalid credentials')
      } else {
        if(this.invalidConfirmPassword) {
          this.setErrorMessage('Passwords do not match')
          return
        }
        this.setErrorMessage(null)
      }
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