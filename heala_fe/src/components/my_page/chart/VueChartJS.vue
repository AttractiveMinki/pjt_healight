<template>
  <section class="container">
    <div class="columns">
      <div class="column">
        <h3>최근 10주 체중/BMI 확인</h3>
        <reactive :chart-data="weightdatacollection"></reactive>
        <bar-chart :chart-data="bmidatacollection"></bar-chart>
      </div>
    </div>
  </section>
</template>

<script>
  import BarChart from './BarChart'
  import Reactive from './Reactive'
  import axios from 'axios';
  import SERVER from "@/api/drf.js"

  export default {
    name: 'VueChartJS',
    components: {
      BarChart,
      Reactive,
    },
    data () {
      return {
        bmidatacollection: null,
        weightdatacollection: null,
        recordDates: [],
        bmiDates: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        weightDates: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        getWeek: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        type: "",
      }
    },
    created () {
      this.type = "week"
      this.getWeightInformation()
    },
    methods: {
      async getWeightInformation() {
        try {
          const res = await axios.get(`${SERVER.URL}${SERVER.ROUTES.getWeightInformation}?type=${this.type}&userId=${localStorage.getItem('userId')}`)
          this.recordDates = res.data
          if (this.recordDates) {
            // 최근 10개 BMI 계산
            for (let i = 0; i < this.recordDates.bodyRecord.length; i++) {
              this.bmiDates[9 - i] = this.recordDates.bodyRecord[i].bmi
            }

            // 최근 10개 체중 계산
            for (let i = 0; i < this.recordDates.bodyRecord.length; i++) {
              this.weightDates[9 - i] = this.recordDates.bodyRecord[i].weight
            }

            // 최근 10개 주차 계산
            for (let i = 0; i < 10; i++ ) {
              if (this.recordDates.todayNumber - i > 0) {
                this.getWeek[9 - i] = this.recordDates.todayNumber - i
              }
              else {
                this.getWeek[9 - i] = this.recordDates.todayNumber - i + 53
              }
            }
          }

          this.fillData()
        } catch(err) {
          console.log(err)
        }
      },
      fillData () {
        this.bmidatacollection = {
          labels: this.getWeek,
          // labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October'],
          datasets: [
            {
              label: 'BMI',
              backgroundColor: '#f87979',
              // Data for the x-axis of the chart
              data: this.bmiDates,
              // data: [this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt()]
            }
          ]
        },
        this.weightdatacollection = {
          labels: this.getWeek,
          // labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October'],
          datasets: [
            {
              label: 'weight',
              backgroundColor: '#f87979',
              // Data for the x-axis of the chart
              data: this.weightDates,
              // data: [this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt()]
            }
          ]
        }
      },
      getRandomInt () {
        return Math.floor(Math.random() * (50 - 5 + 1)) + 5
      }
    }
  }
</script>
