<template>
  <div class="field">
    <label class="prevent-select">{{ label }}</label>
    <div class="field-input" :class="{'field-error': error}">
      <img :src="iconPath" class="icon" :alt="iconAlt">
      <input :type="type" :placeholder="placeholder"
             class="auth-form-input"
             @blur="validateAndUpdate"
             v-model="value">
    </div>
  </div>
</template>

<script lang="ts">

export default {
  name: "FormField",
  emits: ['update', 'error'],
  props: {
    type: {
      type: String,
      required: true
    },
    label: {
      type: String,
      required: true
    },
    placeholder: {
      type: String,
    },
    iconPath: {
      type: String,
      required: true
    },
    iconAlt: {
      type: String
    },
  },
  data() {
    return {
      value: '',
      error: false
    }
  },
  methods: {
    validateAndUpdate() {
      this.error = this.value === '';
      if (this.error) this.$emit('error')
      else this.$emit('update', this.value)
    }
  }
}
</script>

<style scoped>
.prevent-select {
  -webkit-touch-callout: none; /* iOS Safari */
  -webkit-user-select: none; /* Safari */
  -moz-user-select: none; /* Firefox */
  -ms-user-select: none; /* Internet Explorer/Edge */
  user-select: none;
  /* Non-prefixed version, currently
                         supported by Chrome and Opera */
}

.field {
  display: flex;
  margin: 0 auto;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: left;
  width: 100%;
  height: 6rem;
}

.field .field-input {
  display: flex;
  width: 18rem;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  border-bottom: 0.09rem solid #9f9f9f;
  gap: 0.5rem;
  padding-left: 0.5rem;
}

.field label {
  width: 18rem;
}

.field .field-error {
  border-bottom: 0.09rem solid #d90000;
}

.auth-form-input {
  text-align: left;
  flex: 1;
  height: 1.9rem;
  border: none;
  background-color: transparent;
  outline: none;
  margin-top: 0.2rem;
}

.icon {
  height: 1.2rem;
  width: auto;
}
</style>