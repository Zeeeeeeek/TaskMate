<template>
  <div class="grid">
    <div v-for="list in taskLists">
      <TaskList :taskList="list" @reload-lists="loadTaskLists"/>
    </div>
  </div>
</template>

<script lang="ts">
import {TaskListModel} from "../models/TaskListModel";
import apiService from "../services/ApiService";
import TaskList from "../components/TaskList.vue";

export default {
  name: "TaskListGrid",
    components: {TaskList},
  data() {
    return {
      taskLists: [] as TaskListModel[],
    }
  },
  methods: {
    async addTaskList() {
      this.taskLists.push(
          await apiService.addTaskList()
      )
    },
      async loadTaskLists() {
          try {
              this.taskLists = await apiService.getTaskLists()
          } catch (e) {
              console.log(e)
          }
      }
  },
  async beforeMount() {
      if (!apiService.isLoggedIn()) {
        this.$router.push('/login')
        return
      }
      await this.loadTaskLists()
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