<template>
  <div>
    <h1>
      Home page
    </h1>
  </div>
</template>

<script>
import ApiService from "@/services/ApiService";

export default {
  name: "Home",
  data() {
    return {
      taskLists: []
    }
  },
  async beforeMount() {
    try {
      this.taskLists = await ApiService.getTaskLists()
    } catch (e) {
      alert(e)
    }
  }
}
</script>

<style scoped>

</style>