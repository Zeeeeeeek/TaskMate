<script>
import TaskList from "@/components/TaskList.vue";
import AuthForm from "@/components/AuthForm.vue";
import axios from "axios";
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
      const data = {
        "name": "",
        "tasksDTO": {
          "tasks": []
        }
      }
      const token = localStorage.getItem('token')
      const config = {
        headers: {Authorization: `Bearer ${token}`}
      };

      axios
          .post("http://localhost:8080/api/taskLists/",
              data,
              config
          )
          .then(response => {
            this.taskLists.push(response.data)
          })
          .catch(error => {
            console.log(error);
          });
    },
    async register(username, password, email, firstName, lastName) {
      this.hasValidToken = await ApiService.register(username, password, email, firstName, lastName)
      this.updateTasklists()
    },
    login(username, password) {
      this.hasValidToken = ApiService.login(username, password)
      this.updateTasklists()
    },
    logToken() {
      console.log(localStorage.getItem('token'))
      console.log(this.hasValidToken)
    },
    deleteTaskList(id) {
      const index = this.taskLists.findIndex(taskList => taskList.id === id)
      this.taskLists.splice(index, 1)
    },
    updateTasklists() {
      ApiService.getTaskLists()
          .then(response => {
            this.taskLists = response.data
            this.hasValidToken = true
          })
          .catch(error => {
            this.hasValidToken = false;
          });
    }
  },
  beforeMount() {
    this.updateTasklists()
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
