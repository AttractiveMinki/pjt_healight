import PostDetail from "@/views/community/PostDetail";
import FollowExample from "@/views/FollowExample";
import CertifyImage from "@/views/challenge/my/CertifyImage";
import Result from "@/views/challenge/my/Result";

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
        path: "/challenge/:withChallengeId/image",
        name: "CertifyImage",
        component: CertifyImage,
    },
    {
        path: "/challenge/:withChallengeId/result",
        name: "Result",
        component: Result,
    },
]