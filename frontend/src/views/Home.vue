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
    const lists = await ApiService.getTaskLists()

    console.log(`hasValidToken: ${this.hasValidToken}`)
  }
}
</script>

<style scoped>

</style>