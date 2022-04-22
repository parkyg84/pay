package com.example.pay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//스피링부트 component 구성
//https://cbw1030.tistory.com/54

@Component
@Service
public class PayService implements IPayService{
    //https://javabydeveloper.com/tutorial-on-spring-autowired/
    //https://www.baeldung.com/jsf-spring-boot-controller-service-dao
    //https://advenoh.tistory.com/46   //외부 서비스 호출하기

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public String GetCustomer() {

        String custInfo;
        custInfo = restTemplate.getForObject("http://localhost:8082/CustomerApi/getCustInfo", String.class);

        //    public String getCustomerDetail(String customerId)
        //    {
        //        return getForObject("http://localhost:8082/customers/"+customerId, String.class);
        //    }

        //http://localhost:8082/CustomerApi/getCustInfo
        //여기서..외부 서비스 호출해서 가져오자!!
        return "GetCustomerInfo : " + custInfo;
    }


//  여기 다운르도 받아서..화면에서 호출 후... customer api 호출해보기!!
//  @Autowired
//  private payRepository payRepository;
//  public Optional<PayEntity> getPay(){
//  return "";
//  }



}












//
//
//
//import microservices.book.multiplication.domain.Multiplication;
//        import microservices.book.multiplication.domain.MultiplicationResultAttempt;
//        import microservices.book.multiplication.domain.User;
//        import microservices.book.multiplication.event.EventDispatcher;
//        import microservices.book.multiplication.event.MultiplicationSolvedEvent;
//        import microservices.book.multiplication.repository.MultiplicationResultAttemptRepository;
//        import microservices.book.multiplication.repository.UserRepository;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.stereotype.Service;
//        import org.springframework.util.Assert;
//
//        import javax.transaction.Transactional;
//        import java.util.List;
//        import java.util.Optional;
//
//@Service
//class MultiplicationServiceImpl implements MultiplicationService {
//
//    private RandomGeneratorService randomGeneratorService;
//    private MultiplicationResultAttemptRepository attemptRepository;
//    private UserRepository userRepository;
//    private EventDispatcher eventDispatcher;
//
//    @Autowired
//    public MultiplicationServiceImpl(final RandomGeneratorService randomGeneratorService,
//                                     final MultiplicationResultAttemptRepository attemptRepository,
//                                     final UserRepository userRepository,
//                                     final EventDispatcher eventDispatcher) {
//        this.randomGeneratorService = randomGeneratorService;
//        this.attemptRepository = attemptRepository;
//        this.userRepository = userRepository;
//        this.eventDispatcher = eventDispatcher;
//    }
//
//    @Override
//    public Multiplication createRandomMultiplication() {
//        int factorA = randomGeneratorService.generateRandomFactor();
//        int factorB = randomGeneratorService.generateRandomFactor();
//        return new Multiplication(factorA, factorB);
//    }
//
//    @Transactional
//    @Override
//    public MultiplicationResultAttempt checkAttempt(final MultiplicationResultAttempt attempt) {
//        // 해당 닉네임의 사용자가 존재하는지 확인
//        Optional<User> user = userRepository.findByAlias(attempt.getUser().getAlias());
//
//        // 조작된 답안을 방지
//        Assert.isTrue(!attempt.isCorrect(), "You can't send an attempt marked as correct!!");
//
//        // 답안을 채점
//        boolean isCorrect = attempt.getResultAttempt() ==
//                attempt.getMultiplication().getFactorA() *
//                        attempt.getMultiplication().getFactorB();
//
//        MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(
//                user.orElse(attempt.getUser()),
//                attempt.getMultiplication(),
//                attempt.getResultAttempt(),
//                isCorrect
//        );
//
//        // 답안을 저장
//        MultiplicationResultAttempt storedAttempt = attemptRepository.save(checkedAttempt);
//
//        // 이벤트로 결과를 전송
//        eventDispatcher.send(
//                new MultiplicationSolvedEvent(checkedAttempt.getId(),
//                        checkedAttempt.getUser().getId(),
//                        checkedAttempt.isCorrect())
//        );
//
//        return storedAttempt;
//    }
//
//    @Override
//    public List<MultiplicationResultAttempt> getStatsForUser(final String userAlias) {
//        return attemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias);
//    }
//
//    @Override
//    public MultiplicationResultAttempt getResultById(final Long resultId) {
//        return attemptRepository.findOne(resultId);
//    }
//
//
//}