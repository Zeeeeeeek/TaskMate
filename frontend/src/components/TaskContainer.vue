<template>
  <div class="task-container scroll">
    <Task v-for="task in taskList" :ref="task.id">
      <div>{{ task }}</div>
    </Task>
  </div>
</template>

<script>
import Task from "@/components/Task.vue";

export default {
  name: "TaskContainer",
  components: {Task},
  props: {
    taskList: {
      type: Array,
      required: true
    }
  }
}
</script>

<style scoped>
.task-container {
  display: flex;
  flex-direction: column;
  flex: 1;
  width: 100%;
  left: -0.15rem;
}

.scroll {
  overflow-y: scroll;
}

.scroll::-webkit-scrollbar {
  width: 0.313rem;
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