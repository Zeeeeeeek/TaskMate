<template>
  <div class="task">
    <div class="header">
      <p class="task-field">{{name}}</p>
      <div @click="invertCompleted" class="button">
        <TaskUncompleted v-if="!this.completed" class="task-complete-button"/>
        <TaskCompleted v-else class="task-complete-button"/>
      </div>
    </div>
    <div class="header">
      <p class="task-field">{{description}}</p>
    </div>
  </div>
</template>

<script>
import TaskUncompleted from "@/components/icons/TaskUncompleted.vue";
import TaskCompleted from "@/components/icons/TaskCompleted.vue";
import ApiService from "@/services/ApiService";

export default {
  name: "Task",
  components: {TaskCompleted, TaskUncompleted},
  props: {
    id: {
      type: String,
      required: true
    },
    name: {
      type: String,
      required: true
    },
    description: {
      type: String,
      required: true
    },
    dueDate: {
      type: String,
    },
    isCompleted: {
      type: Boolean,
      required: true
    }
  },
data() {
    return {
      completed: this.isCompleted
    }
},
  methods: {
    invertCompleted() {
      this.completed = !this.completed
      console.log(this.completed)
      ApiService.setTaskIsCompleted(this.id, !this.completed)
    }
  }

}
</script>

<style scoped>
.task {
  position: relative;
  display: flex;
  flex-direction: column;
  height: 5rem;
  background-color: transparent;
  border-bottom: 2px solid rgba(110, 110, 110, 0.97);
  width: calc(100% - 0.3rem);
}
.task-field {
  flex: 1;
  border: none;
  border-bottom: 1px solid rgba(73, 78, 83, 0.19);
  background: transparent;
  font-size: 16px;
  margin-top: 0.5rem;
  margin-bottom: 0.5rem;
  margin-right: auto;
}
.task-complete-button {
  margin-left: auto;
  margin-bottom: 0.5rem;
}
.header {
  display: flex;
}
.button {
  cursor: pointer;
  border: none;
  outline: none;
  margin-top: 0.5rem;
  margin-left: 0.5rem;
  margin-right: 0.5rem;
}
</style>