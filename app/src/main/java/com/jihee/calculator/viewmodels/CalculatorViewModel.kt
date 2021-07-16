package com.jihee.calculator.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.kremblewski.expressionevaluator.evaluate

class CalculatorViewModel : ViewModel() {
    //mutable : 변경가능한
    //_expression : viewModel 내부(private)에서 값 변경을 하기 위한 용도(캡슐화를 위해)
    private val _expression = MutableLiveData<String>()
    //expression : viewModel 외부에서 값 참조를 하기 위한 용도
    val expression: LiveData<String>
        get() = _expression

    fun numberInput(num: Int){
        _expression.value += num.toString()
    }

    fun operatorInput(operator: String){
        _expression.value += operator
    }

    fun getResult(){
        // *guard closure
        // null값일때 걍 리턴해
        _expression.value ?: return

        // value ?: "" -> value가 null이면 ""값을 리턴해줘
        _expression.value = evaluate(_expression.value ?: "").toString()
    }

    fun clear(){
        _expression.value = ""
    }
}
