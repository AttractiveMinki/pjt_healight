<template>
  <div class="calendar">
    <h2 style="display: flex; justify-content: center; align-items: baseline">
      <a href="#" v-on:click="onClickYearPrev()" class="text-decoration-none" style="font-size:20px; margin: 1vw">작년</a>
      <a href="#" v-on:click="onClickPrev(currentMonth)" class="text-decoration-none" style="margin: 1vw">◀</a>
      {{currentYear}}년 {{currentMonth}}월
      <a href="#" v-on:click="onClickNext(currentMonth)" class="text-decoration-none" style="margin: 1vw">▶</a>
      <a href="#" v-on:click="onClickYearNext()" class="text-decoration-none" style="font-size:20px; margin: 1vw"> 내년</a>
    </h2>
    <table class="table table-hover" style="display: inline-block; justify-content: space-between">
      <el-row>
        <tr>
          <td v-for="(weekName, index) in weekNames" v-bind:key="index" class="padding-weekNames">
            <span v-if="index % 7 == 0" style="color: red">
              {{ weekName }}
            </span>
            <span v-else-if="index % 7 == 6" style="color: blue">
              {{ weekName }}
            </span>
            <span v-else>
              {{ weekName }}
            </span>
          </td>
        </tr>
        <tr v-for="(row, index) in currentCalendarMatrix" :key="index">
          <td v-for="(day, index2) in row" :key="index2" style="padding:3vw;">
            <span v-if="isToday(currentYear, currentMonth, day)" class="rounded">
              {{ day }}
              <!-- 운동한 날이라면 표시  -->
              <span v-if="day in recordDates" style="margin: 0px; width: 2px; height: 2px">
                ., d
              </span>
              <span v-else style="margin: 2px">
              </span>
            </span>
            <span v-else>
              <span v-if="index2 % 7 == 0" style="color: red">
                {{ day }}
              </span>
              <span v-else-if="index2 % 7 == 6" style="color: blue">
                {{ day }}
              </span>
              <span v-else>
                {{ day }}
              </span>

              <!-- 운동한 날이라면 표시  -->
              <span v-if="index2 % 7 == 3" style="margin: 0px; width: 2px; height: 2px">
                ., 
                <!-- 점이 있는 날을 클릭하면, 해당 일의 칼로리 섭취량을 보여준다. -->
              </span>
              <span v-else style="margin: 2px">
              </span>

            </span>
          </td>
        </tr>
      </el-row>
    </table>    
  </div>
</template>

<script>
import axios from 'axios';
import SERVER from "@/api/drf.js"

export default {
  name: 'DietCalendar',
  data () {
    return {
      weekNames: ["일", "월", "화", "수","목", "금", "토"],
      rootYear: 1904,
      rootDayOfWeekIndex: 5, // 2000년 1월 1일은 토요일
      currentYear: new Date().getFullYear(),
      currentMonth: new Date().getMonth()+1,
      currentDay: new Date().getDate(),
      currentMonthStartWeekIndex: null,
      currentCalendarMatrix: [],
      endOfDay: null,
      recordDates: [],
    }
  },
  mounted(){
    this.init()
    this.getMonthDietRecord()
  },
  methods: {
    init:function(){
      this.currentMonthStartWeekIndex = this.getStartWeek(this.currentYear, this.currentMonth);
      this.endOfDay = this.getEndOfDay(this.currentYear, this.currentMonth);
      this.initCalendar();
    },
    initCalendar:function(){
      this.currentCalendarMatrix = [];
      let day=1;
      for(let i=0; i<6; i++){
        let calendarRow = [];
        for(let j=0; j<7; j++){
          if(i==0 && j<this.currentMonthStartWeekIndex){
            calendarRow.push("");
          }
          else if(day<=this.endOfDay){
            calendarRow.push(day);
            day++;
          }
          else{
            calendarRow.push("");
          }
        }
        this.currentCalendarMatrix.push(calendarRow);
      }
    },
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
              console.log("unknown month " + month);
              return 0;
              // break;
        }
    },
    getStartWeek: function(targetYear, targetMonth){
      let year = this.rootYear;
      let month = 1;
      let sumOfDay = this.rootDayOfWeekIndex;
      for(;;){
        if(targetYear > year){
          for(let i=0; i<12; i++){
            sumOfDay += this.getEndOfDay(year, month+i);
          }
          year++;
        }
        else if(targetYear == year){
          if(targetMonth > month){
            sumOfDay += this.getEndOfDay(year, month);
            month++;
          }
          else if(targetMonth == month){
            return (sumOfDay)%7;
          }
        }
      }
    },
    onClickPrev: function(month){
      month--;
      if(month<=0){
        this.currentMonth = 12;
        this.currentYear -= 1;
      }
      else{
        this.currentMonth -= 1;
      }
      this.init();
    },
    onClickNext: function(month){
      month++;
      if(month>12){
        this.currentMonth = 1;
        this.currentYear += 1;
      }
      else{
        this.currentMonth += 1;
      }
      this.init();
    },
    onClickYearPrev: function(){
      this.currentYear -= 1;
      this.init();
    },
    onClickYearNext: function(){
      this.currentYear += 1;
      this.init();
    },
    isToday: function(year, month, day){
      let date = new Date();
      return year == date.getFullYear() && month == date.getMonth()+1 && day == date.getDate(); 
    },

    getMonthDietRecord: function () {
      let Month = ('0000' + this.currentYear).slice(-4) + '-' +  ('00' + this.currentMonth).slice(-2)
      axios.get(`${SERVER.URL}${SERVER.ROUTES.getMonthDietRecord}?userId=${localStorage.getItem('userId')}&month=${Month}`)
        .then((res) => {
          this.recordDates = res.data
          console.log(this.recordDates)
        })
        .catch((err) => {
          console.error(err)
        })
    },
    
  },
}
</script>

<style type="text/css" scoped>
  .rounded {
    -moz-border-radius: 20px; 
    border-radius: 20px;
    width: 100%;
    height: 100%;
    border:solid 0px #ffffff;
    background-color:#2b6bd1;
    /* padding:10px; */
    color:#ffffff;
  }
  .padding-weekNames {
    padding: calc(3vw + 3px);
  }
  .text-decoration-none {
    text-decoration: none;
    color: black;
  }
</style>