<template>
    <div class="task">
        <div class="header">
            <div class="task-name ">
                {{ task.name }}
            </div>
            <div class="checkbox-wrapper">
                <input type="checkbox" name="checkbox" v-model="task.completed" class="checkbox-field"
                       @click="toggleCompleted">
            </div>
        </div>
        <div class="header">
            <div class="task-description">
                {{ task.description }}
            </div>
            <div class="task-due-date" :class="{'task-due-date-expired':task.expired}">
                {{ task.dueDate }}
            </div>
        </div>
        <div class="footer">
            <div class="splitter">
                <div class="splitter-line"></div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">


import ApiService from "../services/ApiService";

export default {
    name: "Task",
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
    },
    mounted() {
        console.log(this.task.id)
    }
}
</script>

<style scoped>
.task {
    display: flex;
    flex: 1;
    flex-direction: column;
    width: 100%;
    height: 2.5rem;
    margin-top: 1rem;
    margin-bottom: 1rem;
}

.header {
    margin-top: 0;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    height: 2rem;
}

.footer {
    margin-bottom: 0;
    margin-top: auto;
}

.checkbox-wrapper {
    margin-left: auto;
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
    border-bottom: 0.3rem solid rgba(159, 159, 159, 0.5);
    border-radius: 1rem;
}

.task-due-date {
    border-bottom: 1px solid rgba(159, 159, 159, 0.5);
    margin-bottom: 0.1rem;
}

.task-due-date-expired {
    border-bottom: 1px solid rgba(178, 63, 63, 0.5);
}

</style>