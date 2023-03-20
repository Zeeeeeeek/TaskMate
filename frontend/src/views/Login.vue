<template>
  <div class="auth-div">
    <div class="header">Login</div>
    <div class="auth-form">
      <FormField type="text" icon-path="src/assets/icons/user.svg" label="Username" placeholder="Username" icon-alt=""
                 v-model="username" v-bind:error="invalidUsername" @keyup.enter="login"/>
      <FormField icon-path="src/assets/icons/lock.svg" label="Password" type="password" placeholder="Password"
                 icon-alt=""
                 v-model="password" v-bind:error="invalidPassword" @keyup.enter="login"/>
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
import FormField from "@/components/auth/FormField.vue";

export default {
  name: "Login",
  components: {FormField, Button},
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
    validateForm() {
      this.invalidPassword = this.password === '';
      this.invalidUsername = this.username === '';
      this.updateErrorMessage()
    },
    async login() {
      this.validateForm()
      if (this.invalidPassword || this.invalidUsername) return
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
    },
    updateErrorMessage() {
      if (this.invalidPassword || this.invalidUsername) {
        this.setErrorMessage('Fill credentials')
      } else {
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