## Spring boot에서 ElasticSearch 사용해보기


## Docker 🐳
docker-compose 을 이용해 **Elasticsearch**, **Kibana** 설치

1. **Dockerfile** 생성
   * nori 형태소 분석기 추가하기 위해서
   * yml에 이미지로 넣어준다.
2. **docker-compose** 파일 생성
   * yml 파일 

####실행
```
# 실행 
# 데몬으로 띄우기 : 맨 뒤에 -d 붙여준다.
docker-compose -f elasticsearch.yml up

# 죽이기
docker-compose -f elasticsearch.yml down
```

## ES 환경 설정

1. build.gradle 에 관련 의존성 추가   
```java  
dependencies {
      implementation 'org.springframework.boot:spring-boot-starter-data-elasticsearch'
      implementation 'org.springframework.boot:spring-boot-starter-web'
    }
```

2. application.yml에 es 관련 설정 추가
```yaml  
elasticsearch:
  host: localhost
  port: 9200 
```

## 키바나

```http://localhost:5601/```
로 접속시 키바나가 열린다.
키바나로 데이터를 확인.