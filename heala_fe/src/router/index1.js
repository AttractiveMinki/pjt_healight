import PostDetail from "@/views/community/PostDetail";
import FollowExample from "@/views/FollowExample";
<<<<<<< HEAD
import HomeFeed from "@/views/home/HomeFeed";
=======
import CertifyImage from "@/views/challenge/my/CertifyImage";
import Result from "@/views/challenge/my/Result";
>>>>>>> 4440f5d2555547c89cdaee556fd246123af9a598

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