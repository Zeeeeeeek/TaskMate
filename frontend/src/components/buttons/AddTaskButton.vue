<template>

    <div class="add-task-button" title="Add task">

        <AddIcon class="add-icon"/>
        <p class="add-task-text subtitle">Add task</p>

    </div>

</template>

<script>
import AddIcon from "@/components/icons/AddIcon.vue";

export default {
    name: "AddTaskButton",
    components: {AddIcon},
}
</script>

<style scoped>
.add-task-button {
    display: flex;
    flex-direction: row;
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
    margin-left: 1rem;
    margin-top: 0.5rem;
    margin-right: 2rem;
}

.add-task-text {
    font-weight: 400;
    margin-top: 0.4rem;
    margin-left: 0.8rem;
}
</style>