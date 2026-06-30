package nv.temp.first

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.b1)
        val t = findViewById<TextView>(R.id.t1)
        val t2 = findViewById<TextView>(R.id.t2)
        var tc = 0
        btn1.setOnClickListener {
            t.text = "Hello Niv!"
            tc += 1
            t2.text = tc.toString()
            Toast.makeText(this, "Love You!", Toast.LENGTH_SHORT).show()
        }
    }
}