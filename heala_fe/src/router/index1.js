import PostDetail from "@/views/community/PostDetail";
import FollowExample from "@/views/FollowExample";

export default [
    {
        path: "/community/post/:id",
        name: "PostDetail",
        component: PostDetail,
    },
    {
        path: "/followexample",
        name: "FollowExample",
        component: FollowExample,
    }
]