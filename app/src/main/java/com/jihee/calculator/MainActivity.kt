package com.jihee.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.jihee.calculator.databinding.ActivityMainBinding
import com.jihee.calculator.viewmodels.CalculatorViewModel
import pl.kremblewski.expressionevaluator.evaluate
import kotlin.math.exp

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<CalculatorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Binding객체 반환 / apply : 객체에 속성을 적용할 때 사용 / run : 객체에 함수를 적용할때? 대충 이런 너낌...
        DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main).run {
            bindingVM()
            setEventListener()
        }

    }

    //viewModel이랑 바인딩
    //이 생명주기(owner)가 살아있는 한 데이터(expr)의 변화가 생기면 나한테 알려줘 / observe -> 비동기식
    private fun ActivityMainBinding.bindingVM(){
        viewModel.expression.observe(this@MainActivity){ expr ->
            // viewResult.text = expression
            expressionStr = expr
        }
    }

    private fun ActivityMainBinding.setEventListener(){
        setNumberInputEventListener()
        setOperatorInputEventListener()
        setUtilInputEventListener()
    }

    //view에 대한 함수호출은 view에서만 가능 ex) 버튼 클릭
    private fun ActivityMainBinding.setNumberInputEventListener(){
        viewInputBtn0.setOnClickListener{
            numberInput(0)
        }
        viewInputBtn1.setOnClickListener{
            numberInput(1)
        }
        viewInputBtn2.setOnClickListener{
            numberInput(2)
        }
        viewInputBtn3.setOnClickListener{
            numberInput(3)
        }
        viewInputBtn4.setOnClickListener{
            numberInput(4)
        }
        viewInputBtn5.setOnClickListener{
            numberInput(5)
        }
        viewInputBtn6.setOnClickListener{
            numberInput(6)
        }
        viewInputBtn7.setOnClickListener{
            numberInput(7)
        }
        viewInputBtn8.setOnClickListener{
            numberInput(8)
        }
        viewInputBtn9.setOnClickListener{
            numberInput(9)
        }
    }

    private fun ActivityMainBinding.setOperatorInputEventListener(){
        viewPlusBtn.setOnClickListener{
            operatorInput("+")
        }
        viewMinusBtn.setOnClickListener{
            operatorInput("-")
        }
        viewMultiplyBtn.setOnClickListener{
            operatorInput("*")
        }
        viewDivideBtn.setOnClickListener{
            operatorInput("/")
        }
    }

    private fun ActivityMainBinding.setUtilInputEventListener(){
        viewClearBtn.setOnClickListener{
            viewModel.clear()
        }
        viewBracketBtn.setOnClickListener{}
        viewPercentBtn.setOnClickListener{}
        viewPlusMinusBtn.setOnClickListener{}
        viewPointBtn.setOnClickListener{}
        viewResultBtn.setOnClickListener{
            viewModel.getResult()
        }
    }
    private fun numberInput(num:Int){
        viewModel.numberInput(num)
    }
    private fun operatorInput(operator:String){
        viewModel.operatorInput(operator)
    }

}