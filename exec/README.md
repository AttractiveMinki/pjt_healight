## 포팅 메뉴얼



1. gitlab 소스 클론 이후 빌드 및 배포할 수 있는 작업 문서
   1. 사용한 JVM, 웹서버, WAS 제품 등의 종류와 설정값, 버전(IDE 버전 포함) 기재
   2. 빌드 시 사용되는 환경 변수 등의 주요 내용 상세 기재
   3. 배포 시 특이사항 기재
   4. 데이터베이스 접속 정보 등 프로젝트(ERD)에 활용되는 주요 계정 및 프로퍼티가 정의된 파일 목록
2. 프로젝트에서 사용하는 외부 서비스 정보 문서
   - 소셜 인증, 포톤 클라우드, 코드 컴파일 등에 활용된 '외부 서비스' 가입 및 활용에 필요한 정보





1. gitlab 소스 클론 이후 빌드 및 배포할 수 있는 작업 문서

   1. 사용한 JVM, 웹서버, WAS 제품 등의 종류와 설정값, 버전(IDE 버전 포함) 기재

      - Open Jdk 1.8, Nginx, Apache Tomcat, { sts 3.9.14, IntelliJ 2021.1.3 }

   2. 빌드 시 사용되는 환경 변수 등의 주요 내용 상세 기재

      변수 이름 : JAVA_HOME

      변수 값 : C:\Program Files\Zulu\zulu-8

   3. 배포 시 특이사항 기재

      - git clone을 하여 배포하지 않고, 빌드 파일만 서버에 직접 올려 배포하였습니다.
      - 프론트엔드 빌드 폴더: dist
      - 백엔드 빌드 파일: heala_be-0.0.1-SNAPSHOT.war
      - 배포 과정
         - 프론트엔드(dist), 백엔드 빌드(heala_be-0.0.1-SNAPSHOT.war)
         - docker start mariadb 로 DB 실행
         - var/www/html 에 dist 폴더 이동
         - sudo service nginx start 명령어 입력
         - java -jar heala_be-0.0.1-SNAPSHOT.war & 명령어 입력


   4. 데이터베이스 접속 정보 등 프로젝트(ERD)에 활용되는 주요 계정 및 프로퍼티가 정의된 파일 목록

      - application-api-key.properties
      - application-aws.properties
      - application-datasource.properties
      - application-jwt.properties
      - application.properties

2. 프로젝트에서 사용하는 외부 서비스 정보 문서

   - 소셜 인증, 포톤 클라우드, 코드 컴파일 등에 활용된 '외부 서비스' 가입 및 활용에 필요한 정보
     - 이미지 서버 S3. 공공 데이터 및 오픈 API이므로 인증 필요 없음.
     - 음식 검색: 공공데이터포털 데이터 활용
       - **식품의약품안전처_식품 영양성분 정보**
       - https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15057436
     - 이미지 검색: OCR 기술 (카카오 API 사용)
       - https://vision-api.kakao.com/

   


