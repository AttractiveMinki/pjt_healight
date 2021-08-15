<template>
  <div>
    <Navbar />
    <el-row>
      <el-col :span="24" v-for="(feed, idx) in feeds" :key="idx" class="community-inside ">
        <div @click="SetCurrentPageId(feed.id)">
          <router-link :to="{ name: 'WithDetail' , params: { id: feed.id } }" class="text-decoration-none">
            <el-col :span="18" class="margin-left-10">
              <div class="text-align-start text-title">{{ feed.title }}</div>
              <br>
              <div class="text-align-start text-content">{{ feed.introduction }}</div>
              <el-col class="display-flex justify-content-space-between"><span class="community-title">{{ feed.start_date  }}</span> <span class="fix-width"><font-awesome-icon :icon="['far', 'star']" class="padding-right"/>{{ feed.end_date  }}</span></el-col>
            </el-col>
            <el-col :span="6">
              {{ feed.image }}
              <el-image class="margin-left-10"
                style="width: 100%; height: 80px"
                :src="feed.image" 
                >
              </el-image>
            </el-col>
          </router-link>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Navbar from "@/components/my_page/Navbar"

export default {
  name: "Scrap",
  data: function () {
    return {
      feeds: {
        image: "",
        title: "",
        content: "",
        likes: "",
        created_at: "",
        user_id: "",
      },
    }
  },
  components: {
    Navbar,
  },
  methods: {
    // getScrapFeeds: function () {
    //   axios.get(`${SERVER.URL}${SERVER.ROUTES.getScrapFeeds}`)
    //     .then((res) => {
    //       feeds = res
    //   })
    //     .catch((err) => {
    //       alert("에러가 발생했습니다.")
    //       // console.log(`${SERVER.URL}${SERVER.ROUTES.checkEmail}${email}`)
    //       console.error(err.response.data)
    //   })
    // },
  },
  mounted() {
    // getScrapFeeds() {},
  },

}
</script>

<style scoped>

</style>