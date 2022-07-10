package rachman.forniandi.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import rachman.forniandi.simplecalculator.databinding.ActivityMainBinding
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    // Represent whether the lastly pressed key is numeric or not
    var lastNumeric: Boolean = false

    // If true, do not allow to add another DOT
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)
    }

    fun onDigit(view: View){
        binding.tvInput.append((view as Button).text)
        lastNumeric = true
        //lastDot = false
    }


    fun onOperator(view: View) {
        binding.tvInput.text?.let {

            if (lastNumeric && !isMathOperatorAdded(it.toString())){
                binding.tvInput.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    private fun isMathOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")){
            false
        }else{
            (value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
                    )
        }
    }

    fun onEqual(view: View) {
        if (lastNumeric){
            var tvValue = binding.tvInput.text.toString()
            var previx =""
            try {
                if (tvValue.startsWith("-")){
                    previx ="-"
                    tvValue = tvValue.substring(1)
                }
                when{
                    tvValue.contains("/")->{
                        val splitValue = tvValue.split("/")

                        var one = splitValue[0]
                        val two = splitValue[1]

                        if (previx.isNotEmpty()){
                            one = previx + one
                        }
                        //result
                        binding.tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                    }

                    tvValue.contains("*")->{
                        val splitValue = tvValue.split("*")

                        var one = splitValue[0]
                        val two= splitValue[1]

                        if (previx.isNotEmpty()){
                            one = previx + one
                        }
                        //result
                        binding.tvInput.text =removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                    }

                    tvValue.contains("+")->{
                        val splitValue = tvValue.split("+")

                        var one = splitValue[0]
                        val two= splitValue[1]

                        if (previx.isNotEmpty()){
                            one = previx + one
                        }
                        //result
                        binding.tvInput.text =removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                    }

                    tvValue.contains("-")->{
                        val splitValue = tvValue.split("-")

                        var one = splitValue[0]
                        val two= splitValue[1]

                        if (previx.isNotEmpty()){
                            one = previx + one
                        }
                        //result
                        binding.tvInput.text =removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                    }
                }
                /*if (tvValue.contains("-")){
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    val two= splitValue[1]

                    if (previx.isNotEmpty()){
                        one = previx + one
                    }
                    //result
                    binding.tvInput.text =removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }*/

            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result:String):String{
        var value = result
        if (result.contains(".0"))
            value = result.substring(0,result.length - 2)
        return value
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot){
            binding.tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onClear(view: View){
        binding.tvInput.text=""
        lastNumeric = false
        lastDot = false
        //binding.tvInput.text="0"
    }
}