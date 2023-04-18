<template>
    <input type=date value="dueDate" placeholder="dueDate" class="input" @input="$emit('update:dueDate', $event.target.value)">
    <input type="time" value="time" placeholder="time" class="input" @input="$emit('update:time', $event.target.value)">
</template>
<script>
export default {
    name: 'DatePicker',
    props: {
        dueDate: {},
        time: {}
    }
}
</script>
<style scoped>

.input {
    width: 80%;
    height: 2rem;
    margin-bottom: 1rem;
    border: none;
    border-bottom: 0.09rem solid #9f9f9f;
    background-color: transparent;
    outline: none;
}

</style>