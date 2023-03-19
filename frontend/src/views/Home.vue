<template>
  <div class="home">
    <TaskListGrid v-model="taskLists"/>
  </div>
  <div class="footer">
    <div class="add-taskList-button prevent-select">+</div>
  </div>
</template>

<script>
import apiService from "@/services/ApiService";
import TaskListGrid from "@/components/TaskListGrid.vue";

export default {
  name: "Home",
  components: {TaskListGrid},
  data() {
    return {
      taskLists: []
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
  position: fixed;
  left: 0;
  bottom: 0;
  width: 100%;
}

.add-taskList-button {
  flex: 1;
  margin-left: auto;
  margin-top: auto;
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

.add-taskList-button:hover {
  transform: translateY(-2px);
}

.add-taskList-button:active {
  transform: scale(0.95);
}
</style>