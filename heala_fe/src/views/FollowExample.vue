<template>
  <div>
    Test Page
    <follow :follow_id="follow_id" :InitialIsFollowing="isFollowing"></follow>
    {{ $store.state.isFollowingList }}
  </div>
</template>

<script>
import Follow from "@/components/my_page/Follow";
export default {
  name: "Test",
  data() {
    return {
      follow_id: 2,
      isFollowing: false,
    }
  },
  components: { Follow, },
};
</script>

<style></style>
