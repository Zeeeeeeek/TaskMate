<template>
  <div class="task">
    <div class="header">
      <input type="text" class="task-input" ref="name" placeholder="Task name" @blur="setName(this.$refs.name.value)">
      <div @click="invertCompleted" class="button">
        <TaskUncompleted v-if="!this.completed" class="task-complete-button"/>
        <TaskCompleted v-else class="task-complete-button"/>
      </div>
    </div>
    <div class="header">
      <input type="text" class="task-input" placeholder="Description">
      <div class="button">
        <Calendar class="calendar-button" @click="setOpenedDatePicker(true)"/>
        <div v-if="openDatePicker" class="date-picker">
          <input type="datetime-local" ref="dateInput">
          <div @click="setDueDate(this.$refs.dateInput.value); setOpenedDatePicker(false)">Imposta data</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import TaskUncompleted from "@/components/icons/TaskUncompleted.vue";
import TaskCompleted from "@/components/icons/TaskCompleted.vue";
import Calendar from "@/components/icons/Calendar.vue";

export default {
  name: "Task",
  components: {Calendar, TaskCompleted, TaskUncompleted},
  props: {
    id: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      openDatePicker: false,
      id: Number,
      name : String,
      description: String,
      dueDate: null,
      completed: false
    }
  },
  methods: {
    setName(name) {
      this.name = name
      console.log('name: ' + this.name)
      console.log('id: ' + this.id)
      this.$emit('setName', this.name, this.id)
    },
    setDescription(description) {
      this.description = description
    },
    setDueDate(dueDate) {
      if (!dueDate.match(/^(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2})$/))
        throw new Error("Invalid date format: " + dueDate)
      this.dueDate = dueDate
      console.log(this.dueDate)
    },
    invertCompleted() {
      this.completed = !this.completed
    },
    setOpenedDatePicker(opened) {
      this.openDatePicker = opened
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

.task-input {
  flex: 1;
  border: none;
  border-bottom: 1px solid rgba(73, 78, 83, 0.19);
  background: transparent;
  font-size: 16px;
  margin-top: 0.5rem;
  margin-bottom: 0.5rem;
  margin-right: auto;
}

.task-input:focus {
  outline: none;
  border-bottom: 1px solid #134074;
}

.task-complete-button {
  margin-left: auto;
  margin-bottom: 0.5rem;
}

.calendar-button {
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

.date-picker {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: #ffffff;
  z-index: 1;
}
</style>