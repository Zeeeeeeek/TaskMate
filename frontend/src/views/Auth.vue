<template>
  <div>
    <div class="auth-div">
      <div v-if="!showRegister">
        <div class="header">Login</div>
        <div class="auth-form">
          <div class="field">
            <label for="username" class="prevent-select">Username</label>
            <div class="field-input" :class="{'field-error': invalidUsername}">
              <img src="../assets/user.svg" class="login-icon" alt="">
              <input type="text" placeholder="Username"
                     class="auth-form-input"
                     @blur="validateUsername"
                     v-model="this.username">
            </div>
          </div>
          <div class="field">
            <label for="password" class="prevent-select">Password</label>
            <div class="field-input" :class="{'field-error': invalidPassword}">
              <img src="../assets/lock.svg" class="login-icon" alt="">
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
        <div class="header">Register</div>
        <div class="auth-form">
          <div class="field">
            <label for="username" class="prevent-select">Username</label>
            <div class="field-input" :class="{'field-error' : invalidUsername}">
              <img src="../assets/user.svg" class="register-icon" alt="">
              <input type="text" placeholder="Username"
                     class="auth-form-input"
                     @blur="validateUsername"
                     v-model="this.username">
            </div>
          </div>
          <div class="field">
            <label for="email" class="prevent-select">Email</label>
            <div class="field-input" :class="{'field-error': invalidEmail}">
              <img src="../assets/email.svg" class="register-icon" alt="">
              <input type="email" placeholder="Email"
                     class="auth-form-input"
                     @blur="validateEmail"
                     v-model="this.email">
            </div>
          </div>
          <div class="field">
            <label for="password" class="prevent-select">Password</label>
            <div class="field-input" :class="{'field-error': invalidPassword}">
              <img src="../assets/lock.svg" class="register-icon" alt="">
              <input type="password" placeholder="Password"
                     class="auth-form-input"
                     @blur="validatePassword"
                     v-model="this.password">
            </div>
          </div>
          <div class="field">
            <label for="confirmPassword" class="prevent-select">Confirm password</label>
            <div class="field-input" :class="{'field-error': invalidConfirmPassword}">
              <img src="../assets/lock.svg" class="register-icon" alt="">
              <input type="password" placeholder="Confirm password"
                     class="auth-form-input"
                     @blur="validateConfirmPassword"
                     v-model="this.confirmPassword">
            </div>
          </div>
        </div>
        <div class="switch-field prevent-select">
          <div @click="invertShowRegister">Already a member? Login</div>
        </div>
        <div class="submit-field">
          <div class="submitButton prevent-select" @click="register">
            Register
          </div>
        </div>
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

.auth-div {
  background-color: #f3f2f2;
  display: flex;
  flex-direction: column;
  height: 40rem;
  width: 30rem;
  margin: 0 auto;
  position: relative;
  border-radius: 0.313rem; /*5px*/
  box-shadow: 0 0 0.625rem 0 rgba(0, 0, 0, 0.3);
}

.submit-field {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 4rem;
  width: 100%;
}

.submitButton {
  display: flex;
  justify-items: center;
  align-items: center;
  height: 2rem;
  width: 14rem;
  background: rgb(2, 0, 36);
  border-radius: 0.5rem;
  justify-content: center;
  color: #f3f2f2;
  box-shadow: 4px 2px 3px rgba(0, 0, 0, 0.2);
  cursor: pointer;
  transition: all 0.1s ease;
}

.submitButton:hover {
  /*invert gradient*/
  background: rgb(2, 0, 36);
  transform: translateY(-2px);
}

.submitButton:active {
  transform: scale(0.95);
}

.switch-field {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  margin: 1rem 0 1rem 0;
  width: 100%;
  height: 1rem;
}

.switch-field > * {
  /*Set a minimal font weight, underlined*/
  font-weight: 300;
  text-decoration: underline;
  cursor: pointer;
  margin: 0.5rem;
}

.auth-form {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 20rem;
}

.field {
  display: flex;
  margin: 0 auto;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: left;
  width: 100%;
  height: 6rem;
}

.field .field-input {
  display: flex;
  width: 18rem;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  border-bottom: 0.09rem solid #9f9f9f;
  gap: 0.5rem;
  padding-left: 0.5rem;
}

.auth-form label {
  width: 18rem;
}

.auth-form-input {
  text-align: left;
  flex: 1;
  height: 1.9rem;
  border: none;
  background-color: transparent;
  outline: none;
  margin-top: 0.2rem;
}

.field .field-error {
  border-bottom: 0.09rem solid #d90000;
}

.login-icon, .register-icon {
  height: 1.2rem;
  width: auto;
}

.header {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 4rem;
  width: 100%;
  font-weight: 400;
  font-size: 1.5rem;
  border-radius: inherit;
  margin-top: 1rem;
}

.prevent-select {
  -webkit-touch-callout: none; /* iOS Safari */
  -webkit-user-select: none; /* Safari */
  -moz-user-select: none; /* Firefox */
  -ms-user-select: none; /* Internet Explorer/Edge */
  user-select: none;
  /* Non-prefixed version, currently
                         supported by Chrome and Opera */
}

.error-message {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  height: 1.5rem;
  width: auto;
  padding-left: 1rem;
  padding-right: 1rem;
  background-color: #8c1717;
  color: #f3f2f2;
  border-radius: 0.5rem;
  font-size: 0.8rem;
  margin-top: 1rem;
}

.footer {
  display: flex;
  justify-content: center;
  height: 6rem;
  width: 100%;
  border-radius: inherit;
  margin-top: 1rem;
}
</style>