package android.example.mywhackamole

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    var score : Int = 0
    var maxScore : Int = 0
    private lateinit var textViewNowScore : TextView
    private lateinit var textViewRecord : TextView
    private lateinit var sharedPreferences : SharedPreferences

    private val PREFS_FILE : String = "Score"
    private val PREF_NAME : String = "Value"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        textViewNowScore = findViewById(R.id.textViewNowScore)
        textViewRecord = findViewById(R.id.textViewRecord)
        sharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)

        maxScore = sharedPreferences.getInt(PREF_NAME,0)

        val bundle: Bundle? = intent.extras
        score = bundle!!?.getInt("Score")

        textViewNowScore.setText("Score : $score")
        if (score > maxScore) {
            saveScore()
        }
        getScore()
    }

    fun saveScore(){
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt(PREF_NAME, score)
        editor.apply()
    }

    fun getScore(){
        maxScore = sharedPreferences.getInt(PREF_NAME,0)
        textViewRecord?.setText("Record value : " + maxScore)
    }

    fun onClickPlayAgain(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun onClickMenu(view: View) {
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra("Score", maxScore)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        saveScore()
    }
}