<template>
  <div class="grid">
    <div v-for="list in taskLists">
      <TaskList :taskList="list"/>
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