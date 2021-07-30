<template>
  <div>
    <h1>함께챌린지 상세화면</h1>
    {{ currentPageId }}
  </div>
</template>

<script>
import { mapState } from "vuex"

export default {
  name: 'WithDetail',
  computed: {
    ...mapState([
      "currentPageId",
    ])
  },
}
</script>

<style>

</style>