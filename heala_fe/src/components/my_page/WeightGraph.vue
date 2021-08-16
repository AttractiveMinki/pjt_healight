<template>
  <div>
    {{ recordDates }}
  </div>
</template>

<script>
import axios from 'axios';
import SERVER from "@/api/drf.js"

export default {
  name: "WeightGraph",
  data () {
    return {
      recordDates: [],
      type: "",
    }
  },
  methods: {
    async getWeightInformation() {
      try {
        const res = await axios.get(`${SERVER.URL}${SERVER.ROUTES.getWeightInformation}?type=${this.type}&userId=${localStorage.getItem('userId')}`)
        this.recordDates = res.data
        console.log(this.recordDates)
      } catch(err) {
        console.log(err)
      }
    },
  },
  created () {
    this.type = "week"
    this.getWeightInformation()
  },
}
</script>

<style scoped>

</style>