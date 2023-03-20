<template>
  <div class="grid">
    <div v-for="list in taskLists">
      list
    </div>
  </div>
</template>

<script lang="ts">
import {TaskList} from "../models/TaskList";
import apiService from "../services/ApiService";

export default {
  name: "TaskListGrid",
  data() {
    return {
      taskLists: [] as TaskList[],
    }
  },
  methods: {
    async addTaskList() {
      this.taskLists.push(
          await apiService.addTaskList()
      )
      console.log(this.taskLists)
    }
  },
  async beforeMount() {
    try {
      if (!apiService.isLoggedIn()) {
        this.$router.push('/login')
        return
      }
      this.taskLists = await apiService.getTaskLists()
    } catch (e) {
      console.log(e)
    }
  },
  async mounted() {
    try {
      this.taskLists = await apiService.getTaskLists()
    } catch (e) {
      console.log(e)
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