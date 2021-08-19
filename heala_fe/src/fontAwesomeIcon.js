import Vue from "vue";

// 설치했던 fontawesome-svg-core와 vue-fontawesome
import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

// 한길 #@
import {
  faEllipsisV,
  faArrowLeft,
  faBookmark as fasBookmark,
  faStar as fasStar,
  faSearch,
  faCrown
} from "@fortawesome/free-solid-svg-icons";
import { 
  faBookmark as farBookmark,
  faStar as farStar,
  faComment as farComment,
  faEnvelope,
} from "@fortawesome/free-regular-svg-icons";

// 주엽 #@
// 설치했던 아이콘파일에서 필요한 아이콘만 불러옵니다.
import { faPlus, faHome, faUserFriends, faRunning, faUser, faImage } from "@fortawesome/free-solid-svg-icons"; //fas
import { faStar, faBell, faImages, faPlusSquare } from "@fortawesome/free-regular-svg-icons"; //far
// import { faStar } from "@fortawesome/free-regular-svg-icons"; //far

// 한길 #@
// 불러온 아이콘을 라이브러리에 담습니다.
library.add(faEllipsisV, faArrowLeft, fasBookmark, fasStar, faSearch, faCrown);
library.add(farBookmark, farStar, farComment, faEnvelope);

// 주엽 #@
// 불러온 아이콘을 라이브러리에 담습니다.
library.add(faPlus, faHome, faUserFriends, faRunning, faUser, faImage);
library.add(faStar, faBell, faImages, faPlusSquare);
// library.add(faStar);


// fontawesome아이콘을 Vue탬플릿에 사용할 수 있게 등록해 줍니다.
Vue.component("font-awesome-icon", FontAwesomeIcon);
