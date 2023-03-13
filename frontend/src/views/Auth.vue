<template>
  <div>
    <div class="auth-div">
      <div v-if="!showRegister">
        <div class="header">Login</div>
        <div class="auth-form">
          <div class="field">
            <label for="username" class="prevent-select">Username</label>
            <img src="../assets/user.svg" class="login-username" alt=""><input type="text" placeholder="Username"
                                                                               :class="{'field-error': invalidUsername, 'auth-form-input': !invalidUsername}"
                                                                               @blur="validateUsername"
                                                                               v-model="this.username">
          </div>
          <div class="field">
            <label for="password" class="prevent-select">Password</label>
            <img src="../assets/lock.svg" class="login-lock" alt=""><input type="password" placeholder="Password"
                                                                           class="password"
                                                                           :class="{'field-error': invalidPassword, 'auth-form-input': !invalidPassword}"
                                                                           @blur="validatePassword"
                                                                           v-model="this.password">
          </div>
        </div>
        <div class="switch-field" @click="invertShowRegister">
          Not a member? Register
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
            <img src="../assets/user.svg" class="register-username" alt=""><input type="text" placeholder="Username"
                                                                                  :class="{'field-error': invalidUsername, 'auth-form-input': !invalidUsername}"
                                                                                  @blur="validateUsername"
                                                                                  v-model="this.username">
          </div>
          <div class="field">
            <label for="email" class="prevent-select">Email</label>
            <img src="../assets/email.svg" class="register-email" alt=""><input type="email" placeholder="Email"
                                                                                :class="{'field-error': invalidEmail, 'auth-form-input': !invalidEmail}"
                                                                                @blur="validateEmail"
                                                                                v-model="this.email">
          </div>
          <div class="field">
            <label for="password" class="prevent-select">Password</label>
            <img src="../assets/lock.svg" class="register-lock" alt=""><input type="password" placeholder="Password"
                                                                              class="password"
                                                                              :class="{'field-error': invalidPassword, 'auth-form-input': !invalidPassword}"
                                                                              @blur="validatePassword"
                                                                              v-model="this.password">
          </div>
          <div class="field">
            <label for="confirmPassword" class="prevent-select">Confirm password</label>
            <img src="../assets/lock.svg" class="register-lock-confirm" alt=""><input type="password"
                                                                                      placeholder="Confirm password"
                                                                                      class="password"
                                                                                      :class="{'field-error': invalidConfirmPassword, 'auth-form-input': !invalidConfirmPassword}"
                                                                                      @blur="validateConfirmPassword"
                                                                                      v-model="this.confirmPassword">
          </div>
        </div>
        <div class="switch-field" @click="invertShowRegister">
          Already a member? Login
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
      if(!this.confirmPassword) {
        this.setErrorMessage('Fill credentials')
        this.invalidConfirmPassword = true
        return
      }
      if (this.confirmPassword !== this.password) {
        this.setErrorMessage('Passwords do not match')
        this.invalidConfirmPassword = true
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
  left: 50%;
  right: 50%;
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
  justify-content: center;
  align-items: center;
  margin: 1rem 9.7rem 0 9.7rem;
  /*Adapt the width to its content*/
  width: fit-content;
  height: 1rem;
  /*Set a minimal font weight, underlined*/
  font-weight: 300;
  text-decoration: underline;
  cursor: pointer;
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

.auth-form label {
  text-align: left;
  margin-right: auto;
  margin-left: 6.95rem;
}

.auth-form-input {
  text-align: left;
  width: 16rem;
  height: 1.9rem;
  border: none;
  border-bottom: 0.09rem solid #9f9f9f;
  background-color: transparent;
  outline: none;
  padding-left: 2rem;
  margin-top: 0.2rem;
}

.field-error {
  text-align: left;
  width: 16rem;
  height: 1.9rem;
  border: none;
  border-bottom: 0.09rem solid #d90000;
  outline: #134074;
  background-color: transparent;
  padding-left: 2rem;
  margin-top: 0.2rem;
}

.login-username {
  position: absolute;
  left: -14.3rem;
  top: -12.6rem;
  scale: 2.6%;
}

.login-lock {
  position: absolute;
  left: -14.3rem;
  top: -6.6rem;
  scale: 2.6%;
}

.register-username {
  position: absolute;
  left: -14.3rem;
  top: -16.9rem;
  scale: 2.6%;
}

.register-email {
  position: absolute;
  left: -14.3rem;
  top: -8.52rem;
  scale: 2.6%;
}

.register-lock {
  position: absolute;
  left: -14.3rem;
  top: -6.9rem;
  scale: 2.6%;
}

.register-lock-confirm {
  position: absolute;
  left: -14.3rem;
  top: -1.9rem;
  scale: 2.6%;
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