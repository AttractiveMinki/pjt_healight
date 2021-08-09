<template>
  <div>
    <!-- <div class="display-flex justify-content-space-between align-items">
      <span class="display-flex"><font-awesome-icon icon="arrow-left" class="padding-left cursor-pointer" style="margin: 2vw" @click="goBack()"/><span class="text-title">{{ username }}spartakim</span></span>
      
      <span style="margin: 2vw">
        <font-awesome-icon icon="ellipsis-v" style="margin: 2vw" />
        <font-awesome-icon :icon="['far', 'bell']" style="margin: 2vw" />
      </span>
    </div> -->
    <el-menu mode="horizontal">
      <el-row class="display-flex justify-content-space-between align-items">
        <el-col :span="18">
          <el-menu-item index="1" class="display-flex"><span class="display-flex align-items"><font-awesome-icon icon="arrow-left" class="padding-left cursor-pointer" style="margin: 2vw" @click="goBack()"/></span><span class="text-title padding-left">{{ username }}spartakim</span></el-menu-item>
        </el-col>
        <el-col :span="6" class="padding-right">
          <el-submenu index="2">
            <template slot="title"><font-awesome-icon icon="ellipsis-v" /></template>
              <el-menu-item index="2-1">
                <router-link :to="{ name: 'Scrap' }" class="text-decoration-none">저장한 게시글</router-link>
              </el-menu-item>
            <el-menu-item index="2-2">
              <router-link :to="{ name: 'ChangePassword' }" class="text-decoration-none">비밀번호 변경</router-link>
            </el-menu-item>
            <el-menu-item index="2-3" @click="logout">로그아웃</el-menu-item>
            <!-- <el-menu-item index="2-4">신고</el-menu-item> -->
          </el-submenu>
          <!-- <el-menu-item index="3">
            <font-awesome-icon :icon="['far', 'bell']" style="margin: 2vw" />
          </el-menu-item> -->
        </el-col>
      </el-row>
      

    </el-menu>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex"

export default {
  name: "Navbar",
  methods: {
    goBack: function () {
      this.$router.go(-1)
    },
    ...mapActions([
      'logout',
    ]),
  },
  computed: {
    ...mapState([
      "username",
    ])
  },
}
</script>

<style scoped>
  .display-flex {
    display: flex;
  }
  .justify-content-space-between {
    justify-content: space-between;
  }
  .align-items {
    align-items: center;
  }
  .padding-left {
    padding-left: 4%;
  }
  .padding-right {
    padding-right: 4%;
  }
  .cursor-pointer {
    cursor: pointer;
  }
  .text-title {
    font-weight: bold; 
    font-size: 25px;
    margin-bottom: 5px;
  }
  .text-decoration-none {
    text-decoration: none;
    color: #899399;
  }
</style>
