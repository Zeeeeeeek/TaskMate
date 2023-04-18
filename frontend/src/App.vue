
<template>
  <RouterView/>
</template>

<style scoped>
header {
  line-height: 1.5;
}

@media (min-width: 100px) {
  header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
}
</style>
