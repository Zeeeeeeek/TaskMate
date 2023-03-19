<template>
<div class="grid">
  <div v-for="list in taskList">
    list
  </div>
</div>
</template>

<script>
import taskList from "@/components/TaskList.vue";

export default {
  name: "TaskListGrid",
  data() {
    return {
      taskList: []
    }
  },
  methods: {
    addTaskList() {
      this.taskList.push(taskList)
    }
  }
}
</script>

<style scoped>
.grid {
  display: grid;
  flex-direction: column;
  grid-template-columns: repeat(6, 1fr);
  grid-gap: 1rem;
  grid-auto-rows: minmax(10rem, auto);
}

</style>