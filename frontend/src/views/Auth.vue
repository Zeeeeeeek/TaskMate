<template>
  <div>
    <div class="auth-div">
      <div class="header">Login</div>
      <div class="auth-form">
        <div class="field">
          <label for="username" class="prevent-select">Username</label>
          <img src="../assets/user.svg" class="username"  alt=""><input type="text" placeholder="Username" :class="{'field-error': hasError}">
        </div>
        <div class="field">
          <label for="password" class="prevent-select">Password</label>
          <img src="../assets/lock.svg" class="lock" alt=""><input type="password" placeholder="Password" class="password">
        </div>
      </div>
        <div class="switch-field">
          Not a member?<router-link to="/register"> Register</router-link>
        </div>
      <div class="submit-field">
        <div class="submitButton prevent-select">
          Login
        </div>
      </div>
    </div>
  </div>

</template>

<script>


export default {
  name: "Auth",
  data() {
    return {
      hasError: true
    }
  },
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
  justify-content: center;
  align-items: center;
  height: 2rem;
  width: 100%;
  background-color: #f3f2f2;
}

.submitButton {
  display:flex;
  justify-items: center;
  align-items: center;
  height: 2rem;
  width: 14rem;
  background: rgb(2,0,36);
  background: linear-gradient(36deg, rgba(2,0,36,1) 0%, rgba(19,64,116,1) 50%, rgba(0,212,255,1) 100%);
  border-radius: 0.5rem;
  justify-content: center;
  color: #f3f2f2;
  box-shadow: 4px 2px 3px rgba(0, 0, 0, 0.2);
  cursor: pointer;
  transition: all 0.1s ease;
}
.submitButton:hover {
  /*invert gradient*/
  background: rgb(2,0,36);
  background: linear-gradient(36deg, rgba(0,212,255,1) 0%, rgba(19,64,116,1) 50%, rgba(2,0,36,1) 100%);
  transform: translateY(-2px);
}
.submitButton:active {
  transform: scale(0.95);
}

.switch-field {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 1rem 0 1rem 0;
  width: 100%;
  height: 1rem;
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
  margin-right: 11.3rem;
}

.auth-form input {
  text-align: left;
  width: 16rem;
  height: 1.9rem;
  border-bottom: 0.09rem solid #9f9f9f;
  background-color: transparent;
  outline: none;
  padding-left: 2rem;
  margin-top: 0.2rem;
}

.field-error {
  border: 0.09rem solid #b20202;
  outline: #134074;
}

.username {
  position: absolute;
  left: -14.3rem;
  top: -12.6rem;
  scale: 2.6%;
}

.lock {
  position: absolute;
  left: -14.3rem;
  top: -6.6rem;
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
  user-select: none; /* Non-prefixed version, currently
                        supported by Chrome and Opera */
}
</style>