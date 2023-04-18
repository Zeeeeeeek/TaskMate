<template>
    <div class="modal-wrapper" v-if="value" @click.self="closeModal">
        <div class="modal">
            <div class="header">
                <div class="title">
                    <h3>Create new task</h3>
                </div>

                <img src="../../assets/icons/x.svg" class="close" alt="close" @click="closeModal">
            </div>
            <div class="creation-form">
                <input type=text v-model="title" placeholder="Title">
                <input type=text v-model="description" placeholder="Description">
                <DatePicker v-if="showDatePicker" v-model:due-date="dueDate" v-model:time="time" @close-date-picker="showDatePicker = false"/>
                <div v-else class="switch">
                    <div class="switch-text clickable" @click="showDatePicker = true">
                        Has a due date?
                    </div>
                    <div class="calendar-button clickable" @click="showDatePicker = true">
                        <Calendar/>
                    </div>
                </div>
            </div>
            <div class="footer">
                <Button text="Submit" @click="submitModal"/>
            </div>
        </div>
    </div>
</template>
<script lang="ts">
import Button from "../buttons/Button.vue";
import DatePicker from "./DatePicker.vue";
import Calendar from "../icons/Calendar.vue";

export default {
    name: "Modal",
    props: ['value'],
    components: {Calendar, DatePicker, Button},
    methods: {
        closeModal() {
            this.$emit("closeModal");
            this.resetModal();
        },
        submitModal() {
            if (!this.verifyFields()) return;

            const task = (!this.dueDate || !this.time) ? {
                name: this.title,
                description: this.description,
                "@type": "simpleTask"
            } : {
                name: this.title,
                description: this.description,
                dueDate: this.dueDate + ' ' + this.time,
                "@type": "timeConstrainedTask"
            }
            this.$emit("submitModal", task);
            this.closeModal();
        },
        verifyFields() {
            return this.title !== '' && this.description !== '';
        },
        resetModal() {
            this.title = '';
            this.description = '';
            this.dueDate = null;
            this.time = null;
            this.showDatePicker = false;
        }
    },
    data() {
        return {
            title: '',
            description: '',
            dueDate: null,
            time: null,
            showDatePicker: false
        }
    }
}
</script>

<style scoped>
.modal-wrapper {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 999;
    display: flex;
    justify-content: center;
    align-items: center;
}

.modal {
    display: flex;
    flex-direction: column;
    background-color: #f3f2f2;
    padding: 1rem;
    border-radius: 1.125rem;
    width: 20rem;
    height: 22rem;
    box-shadow: 6px 4px 5px rgba(0, 0, 0, 0.2);
}

.footer {
    display: flex;
    flex-direction: row;
    margin-bottom: 0;
    margin-top: auto;
    width: 100%;
    height: 2rem;
    justify-content: center;
}

.header {
    display: flex;
    flex-direction: row;
    margin-top: 0;
    width: 100%;
    height: 2rem;
}

.title {
    margin-left: 0;
    margin-right: auto;
}

.close {
    width: 1rem;
    height: auto;
    margin-right: 0;
    cursor: pointer;
}

.close:hover {
    opacity: 0.8;
}

.creation-form {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 0.4rem;
    width: 100%;
    height: 100%;
}

.creation-form input {
    width: 80%;
    height: 2rem;
    margin-bottom: 1rem;
    border: none;
    border-bottom: 0.09rem solid #9f9f9f;
    background-color: transparent;
    outline: none;
}

.switch {
    display: flex;
    flex-direction: row;
    width: 80%;
}

.switch-text {
    margin-left: 0;
    margin-right: auto;
}

.calendar-button {
    margin-right: 0;
    margin-left: auto;
}

.clickable {
    cursor: pointer;
}
</style>