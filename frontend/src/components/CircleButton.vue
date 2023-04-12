<template>
    <div class="button prevent-select">
        <img :src="src" :title="title" :alt="alt" class="icon"/>
    </div>
</template>
<script lang="ts">
export default {
    name: 'CircleButton',
    props: {
        src: {
            type: String,
            required: true
        },
        title: {
            type: String,
        },
        alt: {
            type: String,
        }
    }
}
</script>
<style scoped>

.button {
    cursor: pointer;
    width: 50px;
    height: 50px;
    border-radius: 50%;
    background-color: #f3f2f2;
    box-shadow: 0 0 0.625rem 0 rgba(0, 0, 0, 0.3);
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 2rem;
    color: #000000;
}

.button:hover {
    transform: translateY(-2px);
}

.button:active {
    transform: scale(0.95);
}

.icon {
    height: 1.2rem;
    width: auto;
}
</style>