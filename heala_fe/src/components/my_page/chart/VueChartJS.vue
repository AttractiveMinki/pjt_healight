<template>
  <section class="container">
    <div class="columns">
      <button class="toggle-button" @click="toggleWeekMonth()">{{ toggleMessage }}</button>
      <div class="column">
        <button class="page-number-button" @click="pagedown()"> &lt; </button>
        <span class="body-title">{{ hotcalcDates[0][0] }} ~ {{ hotcalcDates[5][0] }}</span>
        <button class="page-number-button" @click="pageup()"> &gt; </button>
        <reactive id="chart" :chart-data="weightdatacollection"></reactive>
        <bar-chart id="chart" :chart-data="bmidatacollection"></bar-chart>
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
        bmidatacollection: {},
        weightdatacollection: {},
        recordDates: [],
        bmiDates: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        weightDates: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        getWeek: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        hotbmiDates: [0, 0, 0, 0, 0, 0],
        hotweightDates: [0, 0, 0, 0, 0, 0],
        hotgetWeek: [0, 0, 0, 0, 0, 0],
        pageNumber: 3,
        type: "",

        // 날짜 계산
        currentYear: new Date().getFullYear(),
        currentMonth: new Date().getMonth()+1,
        currentDay: new Date().getDate(),
        calcDates: [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], []],
        hotcalcDates: [[], [], [], [], [], []],
        toggleMessage: "월간 기록 보러 가기",
      }
    },
    created () {
      this.type = "week"
      this.$store.commit("GET_USERID")
      this.getWeightInformation()
    },
    methods: {
      // 주간 기록
      async getWeightInformation() {
        try {
          const res = await axios.get(`${SERVER.URL}${SERVER.ROUTES.getWeightInformation}?type=${this.type}&userId=${this.$store.state.userId}`)
          this.recordDates = res.data
          if (this.recordDates) {
            // 최근 24개 BMI 계산
            for (let i = 0; i < this.recordDates.bodyRecord.length; i++) {
              this.bmiDates[23 - i] = this.recordDates.bodyRecord[i].bmi
            }

            // 최근 24개 체중 계산
            for (let i = 0; i < this.recordDates.bodyRecord.length; i++) {
              this.weightDates[23 - i] = this.recordDates.bodyRecord[i].weight
            }

            // 최근 24개 주차 계산
            for (let i = 0; i < 24; i++ ) {
              if (this.recordDates.todayNumber - i > 0) {
                this.getWeek[23 - i] = this.recordDates.todayNumber - i
              }
              else {
                this.getWeek[23 - i] = this.recordDates.todayNumber - i + 53
              }
            }

            // 최근 24주차 날짜 계산
            for (let i = 0; i < 24; i++) {
              // 현재 일자 - 일주일 * n
              let helpnum = this.currentDay - (7 * i)
              // 날짜가 0보다 크면
              if (helpnum > 0) {
                this.calcDates[23 - i] = [this.currentMonth + '.' + helpnum]
              }
              else {
                let tempYear = this.currentYear
                let tempMonth = this.currentMonth
                while (helpnum <= 0) {
                  tempMonth -= 1
                  if (tempMonth == 0) {
                    tempYear -= 1
                    tempMonth = 12
                  }
                  let number = this.getEndOfDay(tempYear, tempMonth)
                  helpnum += number
                  
                }
                this.calcDates[23 - i] = [tempMonth + '.' + helpnum]
              }
            }
          }

          this.calculatehotDates()
        } catch(err) {
          alert('날짜 계산 중 오류가 발생했습니다.')
        }
      },

      // 월간 기록
      async getWeightInformationMonth() {
        try {
          const res = await axios.get(`${SERVER.URL}${SERVER.ROUTES.getWeightInformation}?type=${this.type}&userId=${this.$store.state.userId}`)
          this.recordDates = res.data
          if (this.recordDates) {
            // 최근 24개 BMI 계산
            for (let i = 0; i < this.recordDates.bodyRecord.length; i++) {
              this.bmiDates[23 - i] = this.recordDates.bodyRecord[i].bmi
            }

            // 최근 24개 체중 계산
            for (let i = 0; i < this.recordDates.bodyRecord.length; i++) {
              this.weightDates[23 - i] = this.recordDates.bodyRecord[i].weight
            }

            // 최근 24개 주차 계산
            for (let i = 0; i < 24; i++ ) {
              if (this.recordDates.todayNumber - i > 0) {
                this.getWeek[23 - i] = this.recordDates.todayNumber - i
              }
              else {
                this.getWeek[23 - i] = this.recordDates.todayNumber - i + 53
              }
            }

            // 최근 24주차 날짜 계산
            for (let i = 0; i < 24; i++) {
              // 현재 일자 - n
              let tempMonth = this.currentMonth - (i)
              let tempYear = this.currentYear
              while (tempMonth <= 0) {
                tempMonth += 12
                tempYear -= 1
              }
              this.calcDates[23 - i] = [tempYear + '.' + tempMonth]
            }
          }

          this.calculatehotDates()
        } catch(err) {
          alert('날짜 계산 중 오류가 발생했습니다.')
        }
      },
      calculatehotDates () {
        if (0 <= this.pageNumber && this.pageNumber <= 3) {
          for (let i = 0; i < 6; i++) {
            this.hotbmiDates[i] = this.bmiDates[i + (6 * this.pageNumber)]
            this.hotweightDates[i] = this.weightDates[i + (6 * this.pageNumber)]
            this.hotgetWeek[i] = this.getWeek[i + (6 * this.pageNumber)]
            this.hotcalcDates[i] = this.calcDates[i + (6 * this.pageNumber)]
          }
          this.fillData()
        }
      },
      fillData () {
        this.bmidatacollection = {
          labels: this.hotcalcDates,
          // labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October'],
          datasets: [
            {
              label: 'BMI',
              backgroundColor: '#f87979',
              // Data for the x-axis of the chart
              data: this.hotbmiDates,
              // data: [this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt()]
            }
          ]
        },
        this.weightdatacollection = {
          labels: this.hotcalcDates,
          // labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October'],
          datasets: [
            {
              label: '체중',
              backgroundColor: 'white',
              borderColor: '#1A8EFA',
              // Data for the x-axis of the chart
              data: this.hotweightDates,
              // data: [this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt(), this.getRandomInt()]
              lineTension: 0
            }
          ]
        }
      },
      pagedown() {
        if (this.pageNumber >= 1)
        {
          this.pageNumber -= 1
          this.calculatehotDates()
        }
        else
        {
          alert('최근 24주 이내 기록만 확인할 수 있습니다.');
        }
      },
      pageup() {
        if (this.pageNumber <= 2) {
          this.pageNumber += 1
          this.calculatehotDates()
        }
        else
        {
          alert('미래의 기록은 확인할 수 없습니다.');
        }
      },
      // getRandomInt () {
      //   return Math.floor(Math.random() * (50 - 5 + 1)) + 5
      // }

      // 날짜 관련
      getEndOfDay: function(year, month){
        switch(month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
              return 31;
              // break;
            case 4:
            case 6:
            case 9:
            case 11:
              return 30;
              // break;
            case 2:
              if( (year%4 == 0) && (year%100 != 0) || (year%400 == 0) ){
              return 29;   
              }
              else{
                  return 28;
              }
              // break;
            default:
              alert('유효하지 않은 달:' + month)
              return 0;
              // break;
        }
      },
      toggleWeekMonth() {
        if (this.type == "week") {
          this.type = "month"
          this.getWeightInformationMonth()
          this.toggleMessage="주간 기록 보러 가기"
        }
        else {
          this.type = "week"
          this.getWeightInformation()
          this.toggleMessage="월간 기록 보러 가기"
        }
      },
    }
  }
</script>

<style scoped>
  #chart {
    width: 95%;
    height: calc(49vh - 4vw - 100px);
  }
  .body-title {
    margin: auto 3vw;
    font-size: 18px;
    font-weight: bold;
  }
  .page-number-button {
    width: 10vw;
    height: 9vw;
    background: white;
    border: 0px;
    border-radius: 5px;
    font-size: 18px;
    font-weight: bold;
  }
  .toggle-button {
    background: #ADEC6E;
    border-width: 0px;
    border-radius: 5px;
    color: black;
  }
</style>
