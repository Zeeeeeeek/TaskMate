<template>
  <div class="task-container scroll">
    <p v-for="a in 20">
      task
    </p>
  </div>
</template>

<script>
export default {
  name: "TaskContainer"
}
</script>

<style scoped>
.task-container {
  flex: 1;
  width: 16rem;
  left: -0.15rem;
}

.scroll {
  overflow-y: scroll;
}

.scroll::-webkit-scrollbar {
  width: 0.313rem;
  color: #000;
}

.scroll::-webkit-scrollbar-track {
  background: #d2d2d2;
  border-radius: 0.5rem;
}

.scroll::-webkit-scrollbar-thumb {
  background: #9f9f9f;
  border-radius: 0.5rem;
}
</style>