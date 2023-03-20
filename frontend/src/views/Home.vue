<template>
  <div class="home">
    <TaskListGrid ref="grid"/>
  </div>
  <div class="footer">
    <div class="button add-task-list prevent-select" @click="addTaskList">
      <img src="../assets/icons/add.svg" alt="add list" class="icon"/>
    </div>
    <div class="button logout prevent-select" @click="logout">
      <img src="../assets/icons/logout.svg" alt="logout" class="icon"/>
    </div>
  </div>
</template>

<script lang="ts">
import TaskListGrid from "../components/TaskListGrid.vue";
import apiService from "../services/ApiService";

export default {
  name: "Home",
  components: {TaskListGrid},
  methods: {
    async addTaskList() {
      await this.$refs.grid.addTaskList()
    },
    async logout() {
      await apiService.logout()
      window.location.reload()
    }
  }
}
</script>

<style scoped>
.home {
  position: relative;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  height: 100%;
  flex-direction: column;
}

.footer {
  display: flex;
  position: fixed;
  left: 0;
  bottom: 0;
  width: 100%;
}

.button {
  cursor: pointer;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background-color: #f3f2f2;
  box-shadow: 0 0 0.625rem 0 rgba(0, 0, 0, 0.3);
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 2rem;
  color: #000000;
}

.button:hover {
  transform: translateY(-2px);
}

.button:active {
  transform: scale(0.95);
}

.icon {
  height: 1.2rem;
  width: auto;
}

.add-task-list {
  margin: 0 auto 1rem 1rem;
}

.logout {
  margin: 0 1rem 1rem auto;
}
</style>