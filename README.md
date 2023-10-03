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