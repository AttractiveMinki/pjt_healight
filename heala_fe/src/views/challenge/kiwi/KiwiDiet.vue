<template>
  <div>
    <ChallengeContainerKiwiDetail 
      category=1
    />
    <el-row v-if="dataLoaded">
      <el-col :span="24" class="community">
        <div style="text-align:start; margin: 2vh 2vw;">
          <div>
            <!-- challenge.content -->
            <!-- {{ challenges }} -->
            식단 챌린지입니다.
          </div>
          <div>
            <!-- challenge.point -->
            500point
          </div>
        </div>
        <el-row class="set-circle-line" style="margin-bottom: 40vw; font-size: 12px">
          <el-col :span="8" style="margin-top: 1vh;">
            <div id="circle" style="display: grid; align-items: center;">
              <!-- {{ mission.content }} 미션 내용 -->
              <!-- {{ challenges[0].mission.content }} -->
              <div class="challenge-content">{{ challenges[0].content }}</div>
              <span v-if="challenges[0].completeDate" class="complete"> Complete </span>
              <!-- v-if mission_user.comple_date 미션 완료 날짜가0 있다면 -->
              <!-- {{ challenges[0].mission_user.complete_date }} -->
            </div>
            <div>1</div>
            <div>{{ challenges[0].completeDate }}</div>
            <button v-if="challenges[0].completeDate == null" class="button-setting" @click="certifyMission(challenges[0].id)">달성 확인</button>
            <!-- <span v-else> 완료! </span> -->
          </el-col>
          <el-col :span="8" @click="setSelectedMissionId(1)">
            <div id="circle" style="display: grid; align-items: center">
              <div class="challenge-content">{{ challenges[1].content }}</div>
              <span v-if="challenges[1].completeDate" class="complete"> Complete </span>
            </div>
            <div>2</div>
            <div>{{ challenges[1].completeDate }}</div>
            <button v-if="challenges[1].completeDate == null" class="button-setting" @click="certifyMission(challenges[1].id)">달성 확인</button>
          </el-col>
          <el-col :span="8" style="margin-top: 2vh">
            <div id="circle" style="display: grid; align-items: center">
              <div class="challenge-content">{{ challenges[2].content }}</div>
              <span v-if="challenges[2].completeDate" class="complete"> Complete </span>
            </div>
            <div>3</div>
            <div>{{ challenges[2].completeDate }}</div>
            <button v-if="challenges[2].completeDate == null" class="button-setting" @click="certifyMission(challenges[2].id)">달성 확인</button>
          </el-col>
        </el-row>
        <el-row class="set-circle-line" style="margin-bottom: 40vw; font-size: 11px">
          <el-col :span="8" style="margin-top: 3vh">
            <div id="circle" style="display: grid; align-items: center">
              <div class="challenge-content">{{ challenges[5].content }}</div>
              <span v-if="challenges[5].completeDate" class="complete"> Complete </span>
            </div>
            <div>6</div>
            <div>{{ challenges[5].completeDate }}</div>
            <button v-if="challenges[5].completeDate == null" class="button-setting" @click="certifyMission(challenges[5].id)">달성 확인</button>
          </el-col>
          <el-col :span="8" style="margin-top: 1vh">
            <div id="circle" style="display: grid; align-items: center">
              <div class="challenge-content">{{ challenges[4].content }}</div>
              <span v-if="challenges[4].completeDate" class="complete"> Complete </span>
            </div>
            <div>5</div>
            <div>{{ challenges[4].completeDate }}</div>
            <button v-if="challenges[4].completeDate == null" class="button-setting" @click="certifyMission(challenges[4].id)">달성 확인</button>
          </el-col>
          <el-col :span="8">
            <div id="circle" style="display: grid; align-items: center">
              <div class="challenge-content">{{ challenges[3].content }}</div>
              <span v-if="challenges[3].completeDate" class="complete"> Complete </span>
            </div>
            <div>4</div>
            <div>{{ challenges[3].completeDate }}</div>
            <button v-if="challenges[3].completeDate == null" class="button-setting" @click="certifyMission(challenges[3].id)">달성 확인</button>
          </el-col>
        </el-row>
        <el-row class="set-circle-line" style="margin-bottom: 40vw; font-size: 11px">
          <el-col :span="8" style="margin-bottom: 15px">
            <div id="circle" style="display: grid; align-items: center">
              <div class="challenge-content">{{ challenges[6].content }}</div>
              <span v-if="challenges[6].completeDate" class="complete"> Complete </span>
            </div>
            <div>7</div>
            <div>{{ challenges[6].completeDate }}</div>
            <button v-if="challenges[6].completeDate == null" class="button-setting" @click="certifyMission(challenges[6].id)">달성 확인</button>
          </el-col>
          <el-col :span="8" style="margin-top: 1vh">
            <div id="circle" style="display: grid; align-items: center">
              <div class="challenge-content">{{ challenges[7].content }}</div>
              <span v-if="challenges[7].completeDate" class="complete"> Complete </span>
            </div>
            <div>8</div>
            <div>{{ challenges[7].completeDate }}</div>
            <button v-if="challenges[7].completeDate == null" class="button-setting" @click="certifyMission(challenges[7].id)">달성 확인</button>
          </el-col>
          <el-col :span="8" style="margin-top: 2vh">
            <div id="circle" style="display: grid; align-items: center">
              <div class="challenge-content">{{ challenges[8].content }}</div>
              <span v-if="challenges[8].completeDate" class="complete"> Complete </span>
            </div>
            <div>9</div>
            <div>{{ challenges[8].completeDate }}</div>
            <button v-if="challenges[8].completeDate == null" class="button-setting" @click="certifyMission(challenges[8].id)">달성 확인</button>
          </el-col>
        </el-row>
        <el-row class="set-circle-line" style="margin-bottom: 10vw; font-size: 11px">
          <el-col :span="8" style="margin-top: 3vh">
            <div id="circle" style="display: grid; align-items: center">
              <div class="challenge-content">{{ challenges[9].content }}</div>
              <span v-if="challenges[9].completeDate" class="complete"> Complete </span>
            </div>
            <div>10</div>
            <div>{{ challenges[9].completeDate }}</div>
            <button v-if="challenges[9].completeDate == null" class="button-setting" @click="certifyMission(challenges[9].id)">달성 확인</button>
          </el-col>
        </el-row>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import ChallengeContainerKiwiDetail from "@/components/challenge/kiwi/ChallengeContainerKiwiDetail"
import axios from 'axios'
import SERVER from "@/api/drf.js"

export default {
  name: "KiwiHealth",
  data: function () {
    return {
      challenges: [],
      dataLoaded: false,
    }
  },
  components: {
    ChallengeContainerKiwiDetail,
  },
  methods: {
    getKiwiDietChallenge: function () {
      axios.get(`${SERVER.URL}${SERVER.ROUTES.getKiwiDietChallenge}` + this.$store.state.userId)
        .then((res) => {
          this.challenges = res.data
          this.dataLoaded = true
        })
        .catch((err) => {
          console.error(err.response.data)
        })
    },
    certifyMission: function (missionId) {
      // 주소 이상하고, 챌린지 성공 실패 기준을 몰라 보류중
      axios.get(`${SERVER.URL}${SERVER.ROUTES.getKiwiHealthChallenge}${this.$store.state.userId}/{missionId}?missionId=${missionId}`)
        .then((res) => {
          console.log(res)
          if (res.data == "") {
            alert('챌린지 실패')
          } else {
            alert('챌린지 성공!')
          }
          
          // 챌린지 성공!
        })
        .catch((err) => {
          console.log(err)
          // 챌린지 실패
        })
    },
  },
  created: function () {
    this.$store.commit("GET_USERID")
    this.getKiwiDietChallenge()
  },

}
</script>

<style scoped>
  #circle {
    border-radius: 50%;
    width: 25vw;
    height: 25vw;
    background: #EAEAEA;
    padding: 1vw;
  }
  .community {
    padding: 5px;
    /* margin-left: 2.5%; */
    border-radius: 5px;
    width: 100%;
    height: calc(90vh - 140px);
    border: 3px solid #ADEC6E;
    overflow : scroll;
  }
  .set-circle-line {
    display: flex;
    justify-content: space-between;
    height: 60px;
  }
  .button-setting {
    border-width: 0px;
    border-radius: 5px;
    background: #ADEC6E;
    color: white;
    width: 80%;
    cursor: pointer;
  }
  .complete {
    position: absolute;
    margin-top: 11vw;
    margin-left: 2vw;
    width: 20vw;
    color: red;
    font-weight: bold;
    border: red 3px solid;
    border-radius: 5px;
    transform: rotate(-40deg);
  }
  .challenge-content {
    font-weight: bold;
  }
</style>