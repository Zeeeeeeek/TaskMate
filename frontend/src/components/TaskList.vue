<template>
  <div class="task-list">
      <div class="header">
        <input placeholder="List name" class="task-list-name subtitle" @blur="setName">
        <TrashCanIcon class="button"/>
        <div>
          <Save @click="saveTasks" class="button"/>
        </div>
      </div>
      <TaskContainer :taskList="tasks"/>
      <AddTaskButton @addTask="addTask"/>
  </div>


</template>

<script>
import AddTaskButton from "@/components/buttons/AddTaskButton.vue";
import TaskContainer from "@/components/TaskContainer.vue";
import TrashCanIcon from "@/components/icons/TrashCanIcon.vue";
import Save from "@/components/icons/Save.vue";
import addTaskButton from "@/components/buttons/AddTaskButton.vue";

export default {
  name: "TaskList",
  computed: {
    addTaskButton() {
      return addTaskButton
    }
  },
  components: {Save, TaskContainer, AddTaskButton, TrashCanIcon},
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
    setName: function(e) {
      this.$emit('set-name', e.target.value)
    },
    saveTasks() {
      this.$emit('save-tasks', this.id, this.tasks)
    },
    addTask() {
      this.tasks.push({
        id: this.tasks.length,
        name: '',
        description: '',
        dueDate: null,
        completed: false
      })
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