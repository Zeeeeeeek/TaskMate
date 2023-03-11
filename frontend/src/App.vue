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
      console.log(`hasValidToken after register: ${this.hasValidToken}`)
    },
    async login(username, password) {
      this.hasValidToken = await ApiService.login(username, password)
      console.log(`hasValidToken after login: ${this.hasValidToken}`)
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
  },
  async beforeMount() {
    await this.updateTasklists()
  }
}
</script>

<template>
  <main>
    <div v-cloak>
      <AuthForm v-if="hasValidToken === false" @register="register" @login="login" v-cloak/>
      <div v-else class="task-grid">
        <TaskList v-for="taskList in taskLists" :key="taskList.id" :id="taskList.id" :name="taskList.name"
                  :tasks="taskList.tasks" @delete-tasklist="deleteTaskList(id)" v-cloak/>
      </div>
      <Button text="log token" @click="logToken" v-cloak/>
      <Button text="Create new list" @click="addTaskList" v-cloak/>
    </div>
  </main>
</template>

<style scoped>
header {
  line-height: 1.5;
}

[v-cloak] {
  display: none !important;
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
