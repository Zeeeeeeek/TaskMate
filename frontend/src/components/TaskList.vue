<template>
  <div class="task-list">
      <div class="header">
        <input placeholder="List name" ref="nameField" class="task-list-name subtitle" @blur="setName(this.$refs.nameField.value)"
               v-model="name">
        <TrashCanIcon class="button" @click="deleteTasklist()"/>
      </div>
      <TaskContainer :taskList="tasks" ref="container"/>
      <AddTaskButton @addTask="addTask"/>
  </div>


</template>

<script>
import AddTaskButton from "@/components/buttons/AddTaskButton.vue";
import TaskContainer from "@/components/TaskContainer.vue";
import TrashCanIcon from "@/components/icons/TrashCanIcon.vue";
import ApiService from "@/services/ApiService";

export default {
  name: "TaskList",
  components: {TaskContainer, AddTaskButton, TrashCanIcon},
  props: {
    id: {
      type:String,
      required: true
    },
    name: {
      type:String,
      required: true
    },
    tasks: {
      type:Array,
      required: true
    }
  },
  methods: {
    setName(name) {
      console.log('name: ' + name)
      ApiService.updateTasklistName(this.id, name)
    },
    addTask() {
      this.$refs.container.addTask()
    },
    deleteTasklist() {
      ApiService.deleteTasklist(this.id)
      this.$emit('delete-tasklist', this.id)
    }

  }
}
</script>

<style scoped>
.task-list {
  display: flex;
  flex-direction: column;
  background-color: #f3f2f2;
  padding: 1rem;
  border-radius: 1.125rem;
  width: 18rem;
  height: 25rem;
  box-shadow: 6px 4px 5px rgba(0, 0, 0, 0.2);
}
.task-list-name {
  border: none;
  border-bottom: 1px solid transparent;
  background-color: #f3f2f2;
  outline: none;
  width: calc(100% - 5rem);
  margin-right: auto;
}
.task-list-name:focus {
  border-bottom: 1px solid #134074;
}

.header {
  display: flex;
  align-items: center;
}

.button {
  cursor: pointer;
}
</style>