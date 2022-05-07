package android.example.mywhackamole

import android.content.Intent
import android.example.mywhackamole.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var score = 0
    var imageArray = ArrayList <ImageView>()
    var handler = Handler(Looper.getMainLooper())
    var runnable = Runnable {  }

    private lateinit var mActivityMainBinding: ActivityMainBinding

    private lateinit var imageViewMole1 : ImageView
    private lateinit var imageViewMole2 : ImageView
    private lateinit var imageViewMole3 : ImageView
    private lateinit var imageViewMole4 : ImageView
    private lateinit var imageViewMole5 : ImageView
    private lateinit var imageViewMole6 : ImageView
    private lateinit var imageViewMole7 : ImageView
    private lateinit var imageViewMole8 : ImageView
    private lateinit var imageViewMole9 : ImageView

    private lateinit var textViewScore : TextView
    private lateinit var textViewTime : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        imageViewMole1 = findViewById(R.id.imageView)
        imageViewMole2 = findViewById(R.id.imageView2)
        imageViewMole3 = findViewById(R.id.imageView3)
        imageViewMole4 = findViewById(R.id.imageView4)
        imageViewMole5 = findViewById(R.id.imageView5)
        imageViewMole6 = findViewById(R.id.imageView6)
        imageViewMole7 = findViewById(R.id.imageView7)
        imageViewMole8 = findViewById(R.id.imageView8)
        imageViewMole9 = findViewById(R.id.imageView9)

        textViewTime = findViewById(R.id.timeText)
        textViewScore = findViewById(R.id.scoreText)

        //ImageArray
        imageArray.add(imageViewMole1)
        imageArray.add(imageViewMole2)
        imageArray.add(imageViewMole3)
        imageArray.add(imageViewMole4)
        imageArray.add(imageViewMole5)
        imageArray.add(imageViewMole6)
        imageArray.add(imageViewMole7)
        imageArray.add(imageViewMole8)
        imageArray.add(imageViewMole9)

        hideImages()

        //CountDown Timer
        object : CountDownTimer(15500,1000){
            override fun onTick(millisUntilFinished: Long) {
                textViewTime.text = "Time " + millisUntilFinished / 1000
            }

            override fun onFinish() {
                textViewTime.text = "Time: 0"
                handler.removeCallbacks(runnable)

                for(image in imageArray){
                    image.visibility = View.INVISIBLE
                }

                val intent = Intent(this@MainActivity, ResultActivity::class.java).also {
                    it.putExtra("Score", score)

                }
                startActivity(intent)
            }
        }.start()
    }
    fun hideImages(){
        runnable = object : Runnable{
            override fun run() {
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }

                val random = Random()
                val randomIndex = random.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable,500)
            }
        }
        handler.post(runnable)
    }

    fun increaseScore(view: View) {
        score = score + 1
        textViewScore.text = "Score $score"
    }
}