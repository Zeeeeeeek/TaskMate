<template>
<div class="button">
  {{text}}
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
.button{
  display: flex;
  width: 100%;
  height: 2rem;
  background-color: #134074;
  border-radius: 0.5rem;
  justify-content: center;
  align-items: center;
  color: #f3f2f2;
  box-shadow: 4px 2px 3px rgba(0, 0, 0, 0.2);
  cursor: pointer;
}
.button:hover{
  background-color: #f3f2f2;
  color: #134074;
  box-shadow: 6px 4px 5px rgba(0, 0, 0, 0.2);
  font-weight: 600;
}
.button:active {
  transform: scale(0.95);
}
</style>