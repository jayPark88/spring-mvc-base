# base_project
- Spring boot Base Project

## Author Info
- jayPark8282

## 프로젝트 설명
모든 웹프로그램 back-end의 기초가 되는 base프로젝입니다.

## 디렉토리 구조
- ![](프로젝트 구조_base_project.png)

## base-module 구성
### sampleAPI

- Get, PUT, POST, DELETE API 통신 sample
- 예외 발생 sample
- 외부 연동 API sample
 
### swagger 설정

- openAPI 기반 
### logInterceptor 설정

- preHandle, postHandle, afterCompletion
### dataSource 설정

- H2기반
### JPA 설정

- 대소문자 구분 옵션, ddl-auto updatge 설정

## core-module 구성

### 메세지 국제화 설정
### GlobalException Handler
### LogInterceptor
### JPA Entity, Model Convert Interface

- Entity, Model 형 변환 시 
### 공통응답
### Retrofit2 외부 API연동 모듈
