<template>

  <button class="add-task-button" @click="addTask">

    <AddIcon class="add-icon"/>
    <p class="add-task-text subtitle">AddTask</p>

  </button>

</template>

<script>
import AddIcon from "@/components/icons/AddIcon.vue";

export default {
  name: "AddTaskButton",
  components: {AddIcon},
  emits: {
    addTask : null
  },
  methods: {
    addTask: function() {
      this.$emit('addTask')
    }
  }
}
</script>

<style scoped>
.add-task-button {
  display: flex;
  background-color: rgba(255, 255, 255, 0.01);
  border-top: 0.141rem solid #134074;
  border-bottom-width: 0;
  border-right-width: 0;
  border-left-width: 0;
  width: 16rem;
  height: 1.25rem;
  margin-bottom: 0.125rem;
  margin-left: -0.13rem;
  cursor: pointer;
}

.add-icon {
  display: flex;
  margin-left: 0.2rem;
  margin-top: 0.2rem;
  margin-right: 2rem;
}

.add-task-text {
  display: flex;
  margin-top: 0.3rem;
}
</style>