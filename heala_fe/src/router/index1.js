import PostDetail from "@/views/community/PostDetail";
import Test from "@/views/Test";

export default [
    {
        path: "/community/post/:id",
        name: "PostDetail",
        component: PostDetail,
    },
    {
        path: "/test",
        name: "Test",
        component: Test,
    }
]