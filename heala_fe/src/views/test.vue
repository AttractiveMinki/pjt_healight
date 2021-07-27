<template>
  <div>
    <el-row>
      <el-col :xs="6">안녕하세요</el-col>
      <el-col :xs="6"><follow></follow></el-col>
      <el-col :xs="12"></el-col>
    </el-row>
  </div>
</template>

<script>
import Follow from '../components/my_page/Follow'

export default {
  name: 'test',
  components: { Follow }
}
</script>

<style scoped>

</style>