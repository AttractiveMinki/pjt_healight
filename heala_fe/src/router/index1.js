import PostDetail from "@/views/community/PostDetail";
import FollowExample from "@/views/FollowExample";
import CertifyImage from "@/views/challenge/my/CertifyImage";

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
    },
    {
        path: "/challenge/:id/image",
        name: "CertifyImage",
        component: CertifyImage,
    }
]