<script>
import TaskList from "@/components/TaskList.vue";
import AuthForm from "@/components/AuthForm.vue";
import Button from "@/components/buttons/Button.vue";
import ApiService from "@/services/ApiService";
import Task from "@/components/Task.vue";

export default {
  components: {Task, Button, AuthForm, TaskList},
  data() {
    return {
      taskLists: []
    }
  },
  methods: {
    setListName: function (name) {

    },
    addTaskList() {

    },
    async register(username, password, email, firstName, lastName) {
      this.hasValidToken = await ApiService.register(username, password, email, firstName, lastName)
      await this.updateTasklists()
    },
    async login(username, password) {
      this.hasValidToken = await ApiService.login(username, password)
      await this.updateTasklists()
    },
    logToken() {
      console.log(localStorage.getItem('token'))
      console.log(this.hasValidToken)
    },
    deleteTaskList(id) {
      const index = this.taskLists.findIndex(taskList => taskList.id === id)
      this.taskLists.splice(index, 1)
    },
    async updateTasklists() {
      this.taskLists = await ApiService.getTaskLists()
    }
  }
}
</script>

<template>
  <RouterView/>
</template>

<style scoped>
header {
  line-height: 1.5;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
}
</style>
