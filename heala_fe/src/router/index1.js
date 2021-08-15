import PostDetail from "@/views/community/PostDetail";
import FollowExample from "@/views/FollowExample";
import HomeFeed from "@/views/home/HomeFeed";
import CertifyImage from "@/views/challenge/my/CertifyImage";
import Result from "@/views/challenge/my/Result";
import CommunityNewMain from "@/views/community/CommunityNewMain";

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
    {
        path: "/community/main",
        name: "CommunityNewMain",
        component: CommunityNewMain,
    }
]