<template>
  <div class="task-list">
      <div class="header">
        <input v-model="name" placeholder="List name" class="task-list-name subtitle" @blur="setName">
        <TrashCanIcon class="trash-can"/>
      </div>
      <TaskContainer/>
      <AddTaskButton class="button"/>
  </div>


</template>

<script>
import AddTaskButton from "@/components/buttons/AddTaskButton.vue";
import TaskContainer from "@/components/TaskContainer.vue";
import TrashCanIcon from "@/components/icons/TrashCanIcon.vue";

export default {
  name: "TaskList",
  components: {TaskContainer, AddTaskButton, TrashCanIcon},
  data() {
    return {
      tasks : [],
      name : "",
      id : ""
    }
  },
  methods: {
    setName: function(e) {
      this.name = e.target.value
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

.trash-can {
  cursor: pointer;
}
</style>