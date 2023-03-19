<template>
  <div class="auth-div">
    <div class="header">Login</div>
    <div class="auth-form">
      <FormField type="text" icon-path="src/assets/icons/user.svg" label="Username" placeholder="Username" icon-alt="" @update="setUsername" @error="setErrorMessage('Fill credentials')"/>
      <FormField @keyup.enter="login" icon-path="src/assets/icons/lock.svg" label="Password" type="password" placeholder="Password" icon-alt="" @update="setPassword" @error="setErrorMessage('Fill credentials')"/>
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
    setUsername(username) {
      this.username = username
    },
    setPassword(password) {
      this.password = password
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
  },
  beforeMount() {
    if(apiService.isLoggedIn()) {
      this.$router.push('/')
    }
  }
}
</script>

<style scoped>
@import '@/assets/auth.css';
</style>