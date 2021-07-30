import Vue from "vue";


// 설치했던 fontawesome-svg-core와 vue-fontawesome
import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

// 주엽 #@
// 설치했던 아이콘파일에서 필요한 아이콘만 불러옵니다.
import { faPlus, faArrowLeft } from "@fortawesome/free-solid-svg-icons"; //fas
import { faStar } from "@fortawesome/free-regular-svg-icons"; //far

// 한길 #@
// 설치했던 아이콘파일에서 해당 아이콘만 불러옵니다.
import {
  faAngleDown,
  faLink,
  faRedo,
  faUndo,
  faUserSecret,
  faTrashAlt as fasTrashAlt,
} from "@fortawesome/free-solid-svg-icons";
import { faTrashAlt as farTrashAlt } from "@fortawesome/free-regular-svg-icons";

// 한길 #@
// 불러온 아이콘을 라이브러리에 담습니다.
library.add(farTrashAlt, fasTrashAlt);
library.add(faAngleDown, faLink, faRedo, faUndo, faUserSecret);

// 주엽 #@
// 불러온 아이콘을 라이브러리에 담습니다.
library.add(faPlus, faArrowLeft);
library.add(faStar);



// fontawesome아이콘을 Vue탬플릿에 사용할 수 있게 등록해 줍니다.
Vue.component("font-awesome-icon", FontAwesomeIcon);
