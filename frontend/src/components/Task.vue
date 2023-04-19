<template>
    <div class="task" :class="{'task-due-date-expired':task.expired}">
        <div class="task-header">
            <div class="task-name">
                {{ task.name }}
            </div>
            <div class="task-delete-wrapper" title="Delete task">
                <TrashCan @click="this.$emit('deleteTask')" class="task-delete"/>
            </div>
            <div class="checkbox-wrapper">
                <input type="checkbox" name="checkbox" v-model="task.completed" class="checkbox-field"
                       @click="toggleCompleted">
            </div>
        </div>
        <div class="task-content">
            <div class="task-description">
                {{ task.description }}
            </div>
            <div class="task-due-date">
                {{ task.dueDate }}
            </div>
        </div>
        <div class="task-footer">
            <div class="splitter">
                <div class="splitter-line"></div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">


import ApiService from "../services/ApiService";
import TrashCan from "./icons/TrashCan.vue";

export default {
    name: "Task",
    components: {TrashCan},
    props: {
        task: {
            required: true
        }
    },
    methods: {
        async toggleCompleted() {
            const newState = !this.task.completed;
            await ApiService.setTaskIsCompleted(this.task.id, newState)
        }
    }
}
</script>

<style scoped>
.task {
    display: flex;
    flex: 1;
    flex-direction: column;
    width: 100%;
    padding: 0 0.2rem;
}

.task-header {
    margin-top: 0;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    width: 100%;
}

.task-content {
    display: flex;
    justify-content: space-between;
}

.task-footer {
    margin-bottom: 0;
    margin-top: auto;
}

.checkbox-wrapper {
    margin-right: 0;
}

.checkbox-field {
    width: 1rem;
    height: 1rem;
    cursor: pointer;
    accent-color: #31d231;
    opacity: 0.9;
    outline: none;
}

.checkbox-field:hover {
    opacity: 1;
    transform: translateY(-1px);
}

.splitter {
    display: flex;
    justify-content: center;
    margin-top: 0.5rem;
    margin-bottom: 0;
}

.splitter-line {
    width: 60%;
    border-bottom: 2px solid rgba(159, 159, 159, 0.4);
    border-radius: 1rem;
}

.task-name {
    line-height: 0;
}

.task-description {
    font-size: 0.8rem;
    color: hsl(213, 5%, 45%);
}

.task-due-date {
    border-bottom: 1px solid rgba(159, 159, 159, 0.5);
    margin-bottom: 0.1rem;
}

.task-due-date-expired {
    background: #edc5c5;
    border-radius: 4px;
}

.task-delete-wrapper {
    margin-left: auto;
    margin-right: 1rem;
    margin-bottom: 0.3rem;
    height: 1rem;
    width: 1rem;
    cursor: pointer;
}

.task-delete {
    opacity: 0.9;
}

.task-delete:hover {
    opacity: 1;
    transform: translateY(-1px);
}

</style>