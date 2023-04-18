<template>
  <div class="task-container scroll">
   <Task v-for="task in tasks" :id="task.id" :name="task.name" :description="task.description" :is-completed="task.completed" :due-date="task.dueDate">
      <div>{{ task }}</div>
    </Task>
  </div>
</template>

<script lang="ts">
import Task from "./Task.vue";

export default {
  name: "TaskContainer",
  components: {Task},
  props: {
    tasksArray: {
      type: Array,
      required: true
    }
  },
  data() {
    return {
      tasks: this.tasksArray,
      stagedTasks: [],
      stagedCounter: 0
    }
  },
  methods: {
    log() {
      console.log(this.stagedTasks)
    },
    updateName(name, id) {
      console.log(name, id)
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
  background: transparent;
  border-radius: 0.5rem;
}

.scroll::-webkit-scrollbar-thumb {
  background: #9f9f9f;
  border-radius: 0.5rem;
}
</style>