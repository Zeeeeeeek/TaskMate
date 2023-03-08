<template>
  <div class="auth">
    <div v-if="showRegister">
      <h1 class="title">Register</h1>
      <form>
        <div class="field">
          <div class="control">
            <input class="input" type="text" placeholder="Username" v-model="username">
          </div>
        </div>
        <div class="field">
          <div class="control">
            <input class="input" type="password" placeholder="Password" v-model="password">
          </div>
        </div>
        <div class="field">
          <div class="control">
            <input class="input" type="email" placeholder="Email" v-model="email">
          </div>
        </div>
        <div class="field">
          <div class="control">
            <input class="input" type="text" placeholder="First Name" v-model="firstName">
          </div>
        </div>
        <div class="field">
          <div class="control">
            <input class="input" type="text" placeholder="Last Name" v-model="lastName">
          </div>
        </div>
        <div class="field">
          <div class="control">
            <Button text="Register" @click="register"/>
          </div>
        </div>
        <div class="field">
          <div class="control">
            <Button text="Switch to login" @click="showRegister = !showRegister"/>
          </div>
        </div>
      </form>
    </div>
    <div v-else>
      <h1 class="title">Login</h1>
      <form>
        <div class="field">
          <div class="control">
            <input class="input" type="text" placeholder="Username" v-model="username">
          </div>
        </div>
        <div class="field">
          <div class="control">
            <input class="input" type="password" placeholder="Password" v-model="password">
          </div>
        </div>
        <div class="field">
          <div class="control">
            <Button text="Login" @click="login"/>
          </div>
        </div>
        <div class="field">
          <div class="control">
            <Button text="Switch to register" @click="showRegister = !showRegister"/>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import Button from "@/components/buttons/Button.vue";

export default {
  name: "AuthForm",
  components: {Button},
  data() {
    return {
      showRegister: false,
      username: '',
      password: '',
      email: '',
      firstName: '',
      lastName: ''
    }
  },
  methods: {
    register() {
      if (this.username === '' || this.password === '' || this.email === '' || this.firstName === '' || this.lastName === '') {
        alert('Please fill all the fields');
        return;
      }
      this.$emit('register', this.username, this.password, this.email, this.firstName, this.lastName);
    },
    login() {
      if (this.username === '' || this.password === '') {
        alert('Please fill all the fields');
        return;
      }
      this.$emit('login', this.username, this.password);
    }
  }
}
</script>

<style scoped>
.auth {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 30rem;
  width: 30rem;
  background-color: #f3f2f2;
  border-radius: 1.125rem;
  box-shadow: 6px 4px 5px rgba(0, 0, 0, 0.2);
}

.title {
  font-size: 2rem;
  font-weight: 400;
  margin-bottom: 1rem;
}

.field {
  margin-bottom: 1rem;
}

.control {
  display: flex;
  flex-direction: column;
  width: 100%;
}

.input {
  border: none;
  border-bottom: 2px solid rgba(110, 110, 110, 0.97);
  outline: none;
  background-color: transparent;
}


</style>