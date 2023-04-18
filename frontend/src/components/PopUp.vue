<template>
    <transition name="pop-up-animation">
        <div class="pop-up" v-show="active">
            <div class="pop-up-content">
                <transition name="pop-up-content-animation">
                    <div class="pop-up-content" v-show="active">
                        <slot>

                        </slot>
                        <button @click="close">Close</button>
                    </div>
                </transition>
            </div>
        </div>
    </transition>
</template>

<script>
export default {
    name: "PopUp",
    props: {
        active: {
            type: Boolean
        }
    },
    setup(props, {emit}) {
        const close = () => {
            emit("close")
        }
        return {close}
    }
}
</script>

<style scoped>
.pop-up {
    display: flex;
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    width: 10rem;
    height: 10rem;
    background-color: rgba(0, 0, 0, 0.5);
}
</style>