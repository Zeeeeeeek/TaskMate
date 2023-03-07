<template>
  <div class="task">
    <input type="text" class="task-name" placeholder="Task name" v-model="name" @blur="setName('name, $event')">
    <p><input type="text" class="task-description" placeholder="Description" v-model="description"
              @blur="setDescription('description, $event')"></p>
    <p>{{ this.dueDate }}</p>
    <p>{{ this.completed }}</p>
    <button @click="completed = !completed">Complete</button>

  </div>
</template>

<script>
export default {
  name: "Task",
  props: {
    id: {
      type: String
    },
    name: {
      type: String
    },
    description: {
      type: String
    },
    dueDate: {
      type: String
    },
    completed: {
      type: Boolean
    }
  },
  data() {
    return {
      name: "",
      description: "",
      dueDate: null,
      completed: false
    }
  },
  methods: {
    setName(name) {
      this.name = name
    },
    setDescription(description) {
      this.description = description
    },
    setDueDate(dueDate) {
      if (!dueDate.match(/^(0[1-9]|[12]\d|3[01])\/(0[1-9]|1[012])\/(19|20)\d\d (0\d|1\d|2[0-3]):([0-5]\d)$/))
        throw new Error("Invalid date format")
      this.dueDate = dueDate
    }
  },
  computed: {
    getType() {
      return this.dueDate !== null ? "simpleTask" : "timeConstrainedTask"
    }
  }
}
</script>

<style scoped>
.task {
  display: flex;
  height: 0.625rem;
  padding: 0.625rem;
  border-bottom: 1px solid #808080;
}

.task input[type="text"] {
  border: none;
  border-bottom: 1px solid rgba(73, 78, 83, 0.19);
  background: transparent;
  padding: 5px;
  font-size: 16px;
}

.task input[type="text"]:focus {
  outline: none;
  border-bottom: 1px solid #134074;
}
</style>