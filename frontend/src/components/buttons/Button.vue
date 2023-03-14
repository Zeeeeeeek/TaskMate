<template>
  <div class="button-div">
    <div class="button">
      {{ text }}
    </div>
  </div>

</template>

<script>
export default {
  name: "Button",
  props: {
    text: {
      type: String,
      required: true
    }
  }
}
</script>

<style scoped>

.button-div {
  display:flex;
  justify-content: center;
  align-items: center;
  height: 4rem;
  width: 100%;
  -webkit-touch-callout: none;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}



.button {
  display: flex;
  justify-items: center;
  align-items: center;
  width: 14rem;
  height: 2rem;
  background-color: rgb(2, 0, 36);
  border-radius: 0.5rem;
  justify-content: center;
  color: #f3f2f2;
  box-shadow: 4px 2px 3px rgba(0, 0, 0, 0.2);
  cursor: pointer;
  transition: all 0.1s ease;
}

.button:hover {
  transform: translateY(-2px);
}

.button:active {
  transform: scale(0.95);
}
</style>