<template>
    <div class="task-list">
        <div class="header">
            <input class="task-list-name subtitle" v-model="name" placeholder="List name" v-on:change="updateTitle">
            <TrashCan class="trash-can" @click="deleteTaskList"/>
        </div>
        <TaskContainer :tasks="this.tasks" />
        <Modal  :value="active" @closeModal="toggle" @submitModal="createNewTask"/>
        <div class="footer" @click="addTask">
            <AddTaskButton/>
        </div>
    </div>
</template>

<script lang="ts">
import AddTaskButton from "../components/buttons/AddTaskButton.vue";
import ApiService from "../services/ApiService";
import {TaskListModel} from "../models/TaskListModel";
import TaskContainer from "./TaskContainer.vue";
import {ref} from "vue";
import Modal from './task creation/Modal.vue'
import Button from "./buttons/Button.vue";
import TrashCan from "./icons/TrashCan.vue";


export default {
    name: "TaskList",
    components: {TrashCan, Button,  TaskContainer, AddTaskButton, Modal},
    props: {
        taskList: {
            type: TaskListModel,
            required: true
        }
    },
    methods: {
        addTask() {
            this.toggle()
        },
        async updateTitle() {
            await ApiService.updateTasklistName(this.id, this.name)
        },
        async createNewTask(task) {
            this.tasks.push(await ApiService.addTask(this.taskList.id, task))
        },
        deleteTaskList() {
            this.$emit('deleteTaskList', this.taskList.id)
        }
    },
    data() {
        return {
            id: this.taskList.id,
            name: this.taskList.name,
            tasks: this.taskList.tasks,
            taskCreationTitle: "Create new task",
        }
    },
    setup() {
        const active = ref(false)
        const toggle = () => {
            active.value = !active.value
        }
        return {active, toggle}
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
    height: 28rem;
    box-shadow: 6px 4px 5px rgba(0, 0, 0, 0.2);
}

.footer {
    display: flex;
    margin-bottom: 0;
    margin-top: auto;
    width: 100%;
    height: 2rem;
}

.header {
    display: flex;
    margin-top: 0;
    width: 100%;
    height: 2rem;
}

.task-list-name {
    border: none;
    border-bottom: 1px solid #9f9f9f;
    background-color: #f3f2f2;
    outline: none;
    width: 100%;
}

.task-list-name:focus {
    border-bottom: 1px solid #134074;
}

.trash-can {
    padding-left: 1rem;
    margin-top: 0.1rem;
    cursor: pointer;
    height: 1.6rem;
}
</style>