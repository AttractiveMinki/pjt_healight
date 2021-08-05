<template>
  <div>
    <ChallengeContainerKiwiDetail 
      category=0
    />
    <el-row>
      <el-col :span="24" class="community">
        <div style="text-align:start; margin: 2vh 2vw;">
          <div>
            <!-- challenge.content -->
            운동 챌린지입니다.
          </div>
          <div>
            <!-- challenge.point -->
            500point
          </div>
        </div>
        <el-row class="set-circle-line" style="margin-bottom: 4vh; font-size: 11px" width="20px">
          <el-col :span="4" style="margin-top: 1vh">
            <div id="circle">
              <!-- {{ mission.content }} 미션 내용 -->
              <!-- {{ datas[0].mission.content }} -->
              <div>게시글 10개 쓰기</div>
              <!-- v-if mission_user.comple_date 미션 완료 날짜가0 있다면 -->
              <!-- {{ datas[0].mission_user.complete_date }} -->
              <div>21-08-02</div>
            </div>
            1
          </el-col>
          <el-col :span="4">
            <div id="circle">
              <div>댓글 10개 달기</div>
              <div></div>
            </div>
            2
          </el-col>
          <el-col :span="4" style="margin-top: 2vh">
            <div id="circle">

            </div>
            3
          </el-col>
          <el-col :span="4">
            <div id="circle">

            </div>
            4
          </el-col>
          <el-col :span="4" style="margin-top: 3vh">
            <div id="circle">

            </div>
            5
          </el-col>
        </el-row>
        <el-row class="set-circle-line" style="margin-bottom: 4vh; font-size: 11px">
          <el-col :span="4" style="margin-top: 3vh">
            <div id="circle">
              <!-- {{ mission.content }} 미션 내용 -->
              <!-- v-if mission_user.comple_date 미션 완료 날짜가0 있다면 -->
            </div>
            10
          </el-col>
          <el-col :span="4" style="margin-top: 2vh">
            <div id="circle">

            </div>
            9
          </el-col>
          <el-col :span="4">
            <div id="circle">

            </div>
            8
          </el-col>
          <el-col :span="4" style="margin-top: 2vh">
            <div id="circle">

            </div>
            7
          </el-col>
          <el-col :span="4" style="margin-bottom: 2vh">
            <div id="circle">

            </div>
            6
          </el-col>
        </el-row>
        <el-row class="set-circle-line" style="margin-bottom: 4vh; font-size: 11px">
          <el-col :span="4" style="margin-bottom: 15px">
            <div id="circle">
              <!-- {{ mission.content }} 미션 내용 -->
              <!-- v-if mission_user.comple_date 미션 완료 날짜가0 있다면 -->
            </div>
            11
          </el-col>
          <el-col :span="4" style="margin-top: 1vh">
            <div id="circle">

            </div>
            12
          </el-col>
          <el-col :span="4" style="margin-top: 2vh">
            <div id="circle">

            </div>
            13
          </el-col>
          <el-col :span="4" style="margin-top: 1vh">
            <div id="circle">

            </div>
            14
          </el-col>
          <el-col :span="4" style="margin-top: 3vh">
            <div id="circle">

            </div>
            15
          </el-col>
        </el-row>
        <el-row class="set-circle-line" style="margin-bottom: 4vh; font-size: 11px">
          <el-col :span="4" style="margin-top: 3vh">
            <div id="circle">
              <!-- {{ mission.content }} 미션 내용 -->
              <!-- v-if mission_user.comple_date 미션 완료 날짜가0 있다면 -->
            </div>
            20
          </el-col>
          <el-col :span="4" style="margin-top: 2vh">
            <div id="circle">

            </div>
            19
          </el-col>
          <el-col :span="4">
            <div id="circle">

            </div>
            18
          </el-col>
          <el-col :span="4" style="margin-top: 2vh">
            <div id="circle">

            </div>
            17
          </el-col>
          <el-col :span="4" style="margin-bottom: 2vh">
            <div id="circle">

            </div>
            16
          </el-col>
        </el-row>
        <el-row class="set-circle-line" style="margin-bottom: 4vh; font-size: 11px">
          <el-col :span="4" style="margin-bottom: 15px">
            <div id="circle">
              <!-- {{ mission.content }} 미션 내용 -->
              <!-- v-if mission_user.comple_date 미션 완료 날짜가0 있다면 -->
            </div>
            21
          </el-col>
          <el-col :span="4" style="margin-top: 1vh">
            <div id="circle">

            </div>
            22
          </el-col>
          <el-col :span="4" style="margin-top: 2vh">
            <div id="circle">

            </div>
            23
          </el-col>
          <el-col :span="4" style="margin-top: 1vh">
            <div id="circle">

            </div>
            24
          </el-col>
          <el-col :span="4" style="margin-top: 3vh">
            <div id="circle">

            </div>
            25
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
      datas: [],
    }
  },
  components: {
    ChallengeContainerKiwiDetail,
  },
  methods: {
    // 서버와 연결 아직 안 됨.. user_id가 아직 없는 문제 + CORS 에러 발생하는 듯.
    getKiwiHealthChallenge: function () {
      console.log('monted 실행')
      // console.log(this.$store.state.user_id)
      // axios.get(`${SERVER.URL}${SERVER.ROUTES.getKiwiHealthChallenge}${this.$store.state.user_id}`)
      axios.get(`${SERVER.URL}${SERVER.ROUTES.getKiwiHealthChallenge}1`)
        .then((res) => {
          this.datas = res
          alert('성공')
        })
        .catch((err) => {
          console.error(err.response.data)
          alert('실패')
        })
    },
  },
  mounted: function () {
    this.getKiwiHealthChallenge()
  },

}
</script>

<style scoped>
  #circle {
    border-radius: 50px;
    width: 50px;
    height: 50px;
    background: #ADEC6E;
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
</style>