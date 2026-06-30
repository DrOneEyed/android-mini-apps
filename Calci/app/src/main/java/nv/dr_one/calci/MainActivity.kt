package nv.dr_one.calci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var calInput : TextView?=null
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calInput = findViewById(R.id.calInput)
    }

    fun onDigit(view: View){
        calInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun onClear(view: View){
        calInput?.text = ""
    }

    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            calInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View){
        calInput?.text?.let{
            if(lastNumeric && !isOperatorAdded(it.toString())){
                calInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }

    }

    fun onEqual(view: View){
        if(lastNumeric){
            var inputVal = calInput?.text.toString()
            var prefix = ""
            try{
                if(inputVal.startsWith("-")){
                    prefix = "-"
                    inputVal = inputVal.substring(1)
                }
                if(inputVal.contains("-")){
                    val splitValue = inputVal.split("-")
                    var n1 = splitValue[0]
                    var n2 = splitValue[1]
                    if(prefix.isNotEmpty()){
                        n1 = prefix + n1
                    }
                    calInput?.text = removeZeroAfterDot((n1.toDouble() - n2.toDouble()).toString())
                }
                else if(inputVal.contains("+")){
                    val splitValue = inputVal.split("+")
                    var n1 = splitValue[0]
                    var n2 = splitValue[1]
                    if(prefix.isNotEmpty()){
                        n1 = prefix + n1
                    }
                    calInput?.text = removeZeroAfterDot((n1.toDouble() + n2.toDouble()).toString())
                }
                else if(inputVal.contains("/")){
                    val splitValue = inputVal.split("/")
                    var n1 = splitValue[0]
                    var n2 = splitValue[1]
                    if(prefix.isNotEmpty()){
                        n1 = prefix + n1
                    }
                    calInput?.text = removeZeroAfterDot((n1.toDouble() / n2.toDouble()).toString())
                }
                else if(inputVal.contains("x")){
                    val splitValue = inputVal.split("x")
                    var n1 = splitValue[0]
                    var n2 = splitValue[1]
                    if(prefix.isNotEmpty()){
                        n1 = prefix + n1
                    }
                    calInput?.text = removeZeroAfterDot((n1.toDouble() * n2.toDouble()).toString())
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result : String) : String{
        var value = result;
        if(result.contains(".0")){
            value = result.substring(0, result.length-2)
        }
        return value
    }

    private fun isOperatorAdded(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("/")
                    || value.contains("x")
                    || value.contains("+")
                    || value.contains("-")
        }
    }

}