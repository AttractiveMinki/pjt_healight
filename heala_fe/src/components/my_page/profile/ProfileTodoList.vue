<template>
  <div>
    <div>
      <el-row style="padding: 4vw">
        <el-col :span="12" style="text-align: start;">
          <span>오늘의 할 일</span>
        </el-col>
        <router-link :to="{ name: 'ProfilePhysInfo' }" class="text-decoration-none">
          <el-col :span="12" style="text-align: end;" class="community-title">
            <i class="margin-left-10 el-icon-arrow-right"></i>
            <span>신체정보 등록</span>
          </el-col>
        </router-link>
      </el-row>

      <router-link :to="{ name: 'DietRecord' }" class="text-decoration-none">
        <el-row class="community-inside">
          <el-col :span="18">
              <div>식단 기록하기</div>
          </el-col>
          <el-col :span="6">
            <img class="image" :src="imageServer + `admin/default`" alt="diet">
          </el-col>
        </el-row>
      </router-link>

      <router-link :to="{ name: 'WeightCalender' }" class="text-decoration-none">
        <el-row class="community-inside">
          <el-col :span="18">
              <div>체중 기록하기</div>
          </el-col>
          <el-col :span="6">
            <img class="image" :src="imageServer + `admin/default`" alt="physic">
          </el-col>
        </el-row>
      </router-link>
    </div>
  </div>
</template>

<script>
import SERVER from "@/api/drf.js";

export default {
  name: "ProfileTodoList",
  data: () => {
    return {
      imageServer: SERVER.IMAGE_URL,
      user: {
        id: "",
        identity: "",
        image: "",
        name: "",
        exp: "",
        introduction: "",
        badge: [],
        follower: "",
        following: "",
        exercise: [],
        food: [],
        heart: [],
      },
      // 보고 있는 프로필 계정 주인의 id
      userId: "",
      
      // 로그인 한 내 아이디
      myId: "",
    };
  },
}
</script>

<style scoped>
.text-decoration-none {
  text-decoration: none;
  color: black;
}
.align-items-center {
  display: flex-inline;
  text-align: center;
  align-items: center;
}
.display-inline-grid {
  display: inline-grid;
  align-items: center;
}
.community-title {
  font-size: 11px;
  color: gray;
}
.community-inside {
  margin: 0px 5px 5px 5px;
  padding-left: 10vw;
  padding-top: 3vh;
  font-weight: bold;
  border-radius: 5px;
  height: 10vh;
  text-align: start;
  border: 1.5px solid darkgrey;
  box-sizing: border-box;
}
.image {
  width: 40px;
  height: 40px;
  position: relative;
  top: -8px;
}
</style>