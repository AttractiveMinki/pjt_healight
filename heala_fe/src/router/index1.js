import PostDetail from "@/views/community/PostDetail";
import FollowExample from "@/views/FollowExample";
import HomeFeed from "@/views/home/HomeFeed";

export default [
    {
        path: "/community/post/:id",
        name: "PostDetail",
        component: PostDetail,
    },
    {
        path: "/home/feed",
        name: "HomeFeed",
        component: HomeFeed,
    },
    {
        path: "/followexample",
        name: "FollowExample",
        component: FollowExample,
    }
]