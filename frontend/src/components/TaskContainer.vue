<template>
    <div class="task-container scroll">
        <div v-for="task in tasks" :key="task.id">
            <Task :task="task" @deleteTask="this.$emit('deleteTask', task.id)"/>
        </div>
    </div>
</template>

<script lang="ts">
import Task from "./Task.vue";

export default {
    name: "TaskContainer",
    components: {Task},
    props: {
        tasks: {
            required: true
        }
    },

}
</script>

<style scoped>
.task-container {
    display: flex;
    flex-direction: column;
    flex: 1;
    width: 100%;
    left: -0.15rem;
}

.task-container > div {
    margin-top: 1rem;
}

.scroll {
    overflow-y: scroll;
}

.scroll::-webkit-scrollbar {
    width: 0.313rem;
}

.scroll::-webkit-scrollbar-track {
    background: transparent;
    border-radius: 0.5rem;
}

.scroll::-webkit-scrollbar-thumb {
    background: #9f9f9f;
    border-radius: 0.5rem;
}
</style>