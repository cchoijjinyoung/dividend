# dividend
## Redis window 설치
- wsl --install
- Ubuntu
- WslRegisterDistribution failed with error: 0x80370114
- 제어판 -> 프로그램 기능 -> 가상 머신 플랫폼 + Hyper-V 체크하기
- 컴퓨터 재부팅 후 wsl --install 했더니 해결

## Error
###/finance/dividend/{companyName}
- InvalidDefinitionException:model.Company = deserialize from object value
- 

## Builder 패턴
### Company, Dividend model 클래스
- @NoArgsConstructor 추가하면서 @Builder를 지워줬음. 어차피 멤버 변수가 많지 않기 때문에 효율적이진 않았음.

## 스프링부트와 레디스는 별도의 서버이다.

## 회원관리 API
- enum 클래스 "Authority" : 권한
- MemberEntity implements UserDetails(스프링 시큐리티 제공) : 스프링 시큐리티 기능을 사용하기 위해서 구현함
- private List<String> roles;
- UserDetails 구현 후 메서드 재정의 : getAuthorities()만 사용할 예정
- MemberRepository : findByUsername(username), existsByUsername(username)
- MemberService : implements UserDetailsService(스프링 시큐리티 제공)
- Auth 모델 클래스 추가
- Inner클래스로 로그인 시, 회원가입 시 클래스(SignIn.class, SignUp.class)를 따로 두었음.
- SignUp 클래스는 toEntity() 메서드를 정의함. (MemberEntity로 변환하는 기능)
- MemberService -> register(Auth.SighUp) : 만약 username 중복 시 RuntimeError throw, 아닐 시 MemberEntity 반환.
- -> register() -> password 같은 경우 민감한 정보이기 때문에 암호화 진행 -> AppConfig에 passwordEncoder를 빈으로 등록하여 return new BCryptPasswordEncoder()
- -> member.setPassword(this.passwordEncoder.encode(member.getPassword())));

## JWT
- echo '시크릿 키' | base64
- application.yml 에 jwt설정 등록
- TokenProvider
  - .yml파일에 정의한 jwt -> @Value("{spring.jwt.secret}") private String secretKey;
  - generateToken(username, roles), parseClaims(token) 정의
  - 