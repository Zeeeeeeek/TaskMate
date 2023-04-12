<template>
    <div class="home">
        <TaskListGrid ref="grid"/>
    </div>
    <div class="footer">
        <CircleButton src="src/assets/icons/add.svg" title="Add new tasklist" @click="addTaskList" class="add-task-list"/>
        <CircleButton src="src/assets/icons/logout.svg" title="Logout" @click="logout" class="logout"/>
    </div>
</template>

<script lang="ts">
import TaskListGrid from "../components/TaskListGrid.vue";
import apiService from "../services/ApiService";
import CircleButton from "../components/CircleButton.vue";

export default {
    name: "Home",
    components: {CircleButton, TaskListGrid},
    methods: {
        async addTaskList() {
            await this.$refs.grid.addTaskList()
        },
        async logout() {
            await apiService.logout()
            window.location.reload()
        }
    }
}
</script>

<style scoped>
.home {
    position: relative;
    display: flex;
    justify-content: flex-end;
    align-items: center;
    height: 100%;
    flex-direction: column;
}

.footer {
    display: flex;
    position: fixed;
    left: 0;
    bottom: 0;
    width: 100%;
}

.add-task-list {
    margin: 0 auto 1rem 1rem;
}

.logout {
    margin: 0 1rem 1rem auto;
}
</style>