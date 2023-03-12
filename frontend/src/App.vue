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
      hasValidToken: false,
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

.task-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(18rem, 1fr));
  grid-template-rows: minmax(10rem, auto);
  grid-gap: 1rem;
  padding: 1rem;
  max-width: 80vw;
  margin: 1rem auto;
  text-align: justify-all;
}

.box {
  display:flex;
  background-color: #134074;
  height: 100vh;
  width: 100vw;
}


@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }
}
</style>
