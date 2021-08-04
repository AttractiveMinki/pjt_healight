import Vue from "vue";

// 설치했던 fontawesome-svg-core와 vue-fontawesome
import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

// 한길 #@
import {
  faEllipsisV,
  faArrowLeft,
  faBookmark as fasBookmark,
  faStar as fasStar
} from "@fortawesome/free-solid-svg-icons";
import { 
  faBookmark as farBookmark,
  faStar as farStar,
  faComment as farComment
} from "@fortawesome/free-regular-svg-icons";

// 주엽 #@
// 설치했던 아이콘파일에서 필요한 아이콘만 불러옵니다.
import { faPlus } from "@fortawesome/free-solid-svg-icons"; //fas
import { faStar, faBell, faImages } from "@fortawesome/free-regular-svg-icons"; //far

// 한길 #@
// 불러온 아이콘을 라이브러리에 담습니다.
library.add(faEllipsisV, faArrowLeft, fasBookmark, fasStar);
library.add(farBookmark, farStar, farComment);

// 주엽 #@
// 불러온 아이콘을 라이브러리에 담습니다.
library.add(faPlus);
library.add(faStar, faBell, faImages);


// fontawesome아이콘을 Vue탬플릿에 사용할 수 있게 등록해 줍니다.
Vue.component("font-awesome-icon", FontAwesomeIcon);
