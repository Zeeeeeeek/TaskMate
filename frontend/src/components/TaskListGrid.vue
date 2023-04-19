<template>
    <div class="grid">
        <div v-if="taskLists.length" v-for="list in taskLists" :key="list.id">
            <TaskList :taskList="list"  @deleteTaskList="deleteTaskList"/>
        </div>
        <div v-else>
            Aggiungi una nuova lista cliccando il pulsante!
        </div>
    </div>
</template>

<script lang="ts">
import {TaskListModel} from "../models/TaskListModel";
import apiService from "../services/ApiService";
import TaskList from "../components/TaskList.vue";

export default {
    name: "TaskListGrid",
    components: {TaskList},
    data() {
        return {
            taskLists: [] as TaskListModel[],
        }
    },
    methods: {
        async addTaskList() {
            this.taskLists.push(
                await apiService.addTaskList()
            )
        },
        async loadTaskLists() {
            try {
                this.taskLists = await apiService.getTaskLists()
            } catch (e) {
                console.log(e)
            }
        },
        async deleteTaskList(id: string) {
            await apiService.deleteTaskList(id)
            this.taskLists = this.taskLists.filter(list => list.id !== id)
        }
    },
    async beforeMount() {
        if (!apiService.isLoggedIn()) {
            this.$router.push('/login')
            return
        }
        await this.loadTaskLists()
    }
}
</script>

<style scoped>
.grid {
    display: flex;
    flex-wrap: wrap;
    gap: 1rem;
}

</style>