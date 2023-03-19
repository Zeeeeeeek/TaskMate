<template>
<div class="auth-div">
  <div class="header">{{this.title}}</div>
</div>
</template>

<script>
export default {
  name: "AuthBox",
  props: {
    title: {
      type: String,
      required: true
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
</style>