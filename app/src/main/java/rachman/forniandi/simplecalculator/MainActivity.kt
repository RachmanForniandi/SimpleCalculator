package rachman.forniandi.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import rachman.forniandi.simplecalculator.databinding.ActivityMainBinding

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
    }


    fun onOperator(view: View) {

    }

    fun onEqual(view: View) {

    }

    fun onDecimalPoint(view: View) {

    }

    fun onClear(view: View){
        //binding.tvInput.text=""
        binding.tvInput.text="0"
    }
}