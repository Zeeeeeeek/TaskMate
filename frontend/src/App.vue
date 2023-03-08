<script>
import TaskList from "@/components/TaskList.vue";
import AuthForm from "@/components/AuthForm.vue";
import axios from "axios";
import Button from "@/components/buttons/Button.vue";

export default {
  components: {Button, AuthForm, TaskList},
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
          "tasks": [

          ]
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
            console.log(this.taskLists)
          })
          .catch(error => {
            console.log(error);
          });
    },
    register(username, password, email, firstName, lastName) {

      const data = {
        username: username,
        password: password,
        email: email,
        firstName: firstName,
        lastName: lastName
      }
      axios
          .post('http://localhost:8080/api/auth/register', data, {
                headers: {
                  'Content-Type': 'application/json'
                }
              }
          )
          .then(response => {
            const token = response.data.jwtToken;
            console.log(token);
            if (token) {
              localStorage.setItem('token', token)
              this.hasValidToken = true;
            }
          })
          .catch(error => {
            console.log(error);
          });
    },
    login(username, password) {
      const data = {
        username: username,
        password: password
      }
      axios
          .post('http://localhost:8080/api/auth/login', data, {
                headers: {
                  'Content-Type': 'application/json'
                }
              }
          )
          .then(response => {
            const token = response.data.jwtToken;
            console.log(token);
            if (token) {
              localStorage.setItem('token', token)
              this.invertHasValidToken()
            }
          })
          .catch(error => {
            console.log(error);
          });
    },
    logToken() {
      console.log(localStorage.getItem('token'))
      console.log(this.hasValidToken)
    },
    invertHasValidToken() {
      this.hasValidToken = !this.hasValidToken
    },
    saveTasks(id, tasks) {

    }
//<TaskList @set-name="setListName" @add-task="addTask()" :tasks="[ ]"/>
  },
  beforeMount() {
    const token = localStorage.getItem('token')
    const config = {
      headers: {Authorization: `Bearer ${token}`}
    };

    axios
        .get('http://localhost:8080/api/taskLists/', config)
        .then(
            response => {
              this.taskLists = response.data
              this.hasValidToken = true;
            }
        )
        .catch(error => {
          this.hasValidToken = false;
        })
  }
}
</script>

<template>
  <main>
    <AuthForm v-if="hasValidToken === false" @register="register" @login="login"/>
    <div v-else>
      <TaskList v-for="taskList in taskLists" :key="taskList.id"  :id="taskList.id"  :name="taskList.name" :tasks="taskList.tasks" @save-tasks="saveTasks"/>
    </div>
    <Button text="log token" @click="logToken"/>
    <Button text="Create new list" @click="addTaskList"/>
  </main>
</template>

<style scoped>
header {
  line-height: 1.5;
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
