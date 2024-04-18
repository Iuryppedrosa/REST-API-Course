package dev.iury.project.controllers;

import dev.iury.project.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    @Autowired
    private MathService mathService;

//    @GetMapping("/sum/{numberOne}/{numberTwo}")
//    public Double sum(@PathVariable Double numberOne, @PathVariable Double numberTwo){
//        return numberOne + numberTwo;
//    }

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable(value = "numberOne") String  numberOne, @PathVariable(value = "numberTwo") String numberTwo)
    {
        return mathService.sum(numberOne, numberTwo);
    }

    @GetMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(@PathVariable(value = "numberOne") String  numberOne, @PathVariable(value = "numberTwo") String numberTwo)
    {
        return mathService.subtraction(numberOne, numberTwo);
    }

    @GetMapping("/multiplo/{numberOne}/{numberTwo}")
    public Double multiplo(@PathVariable(value = "numberOne") String  numberOne, @PathVariable(value = "numberTwo") String numberTwo)
    {
        return mathService.multiplication(numberOne, numberTwo);
    }

    @GetMapping("/division/{numberOne}/{numberTwo}")
    public Double division(@PathVariable(value = "numberOne") String  numberOne, @PathVariable(value = "numberTwo") String numberTwo)
    {
        return mathService.division(numberOne, numberTwo);
    }

}
