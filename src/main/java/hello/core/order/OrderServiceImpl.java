package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // final 이 붙은 애들로 생성자를 만들어줌 자동으로 의존관계주입도 가능
public class OrderServiceImpl implements OrderService {


//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); DIP, OCP 위반
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

//    @Autowired private MemberRepository memberRepository; // 필드 주입 방식
//    @Autowired private DiscountPolicy discountPolicy;

//    public void setMemberRepository(MemberRepository memberRepository) { // 필드 주입 방식도 결국 세터를 불러와야함
//        this.memberRepository = memberRepository;
//    }
//
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

//        @Autowired(required = false) // setter 주입 방식
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }
    private final MemberRepository memberRepository; // final 이 붙어있으면 누락 방지도 가능
    private final DiscountPolicy discountPolicy;

    @Autowired // 생성자 주입 방식
    public OrderServiceImpl(MemberRepository memberRepository, /*@Qualifier("mainDiscountPolicy")*/ DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy; // 빈에 똑같은 타입이 2가지일때 필드명이나 파라미터 명으로도 구분해서 불러오기 가능
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    
    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
