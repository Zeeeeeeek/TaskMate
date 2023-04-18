<template>
    <input type=date value="dueDate" placeholder="dueDate" class="input"
           @input="$emit('update:dueDate', $event.target.value)">
    <input type="time" value="time" placeholder="time" class="input" @input="$emit('update:time', $event.target.value)">
    <img src="../assets/icons/chevron-up.svg" alt="Close date picker" class="close-arrow"
         @click="$emit('close-date-picker')" title="Close date picker">
</template>
<script>
export default {
    name: 'DatePicker',
    props: {
        dueDate: {},
        time: {}
    },
    emits: ['update:dueDate', 'update:time', 'close-date-picker']
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

.close-arrow {
    height: 1rem;
    cursor: pointer;
}

</style>