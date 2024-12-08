package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.*;

@RestController // 현재 보고 있는 클래스를 API 진입 지점을 만들겠다 !
public class CalculatorController {

    @GetMapping("/add") // GET /add -> url 만들기
//    첫 번째 방법
//    public int addTwoNumbers(
//            @RequestParam int number1, // @RequestParam -> 주어지는 쿼리를 함수 파라미터에 넣는다.
//            @RequestParam int number2
//    ) {
//        return number1 + number2;
//    }

//    두 번째 방법 (dto)
    public int addTwoNumbers(CalculatorAddRequest request) {
        return request.getNumber1() + request.getNumber2();
    }

    @PostMapping("/multiply") // POST /multiply
    public int multiplyTwoNumber(@RequestBody CalculatorMultiplyRequest request) {
        return request.getNumber1() * request.getNumber2();
    }

}