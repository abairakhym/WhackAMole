package android.example.mywhackamole

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MenuActivity : AppCompatActivity() {

    private lateinit var textViewScore : TextView
    private var score : Int = 0
    private var maxScore : Int = 0
    private lateinit var sharedPreferences : SharedPreferences

    private val PREFS_FILE : String = "Score"
    private val PREF_NAME : String = "Value"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        textViewScore = findViewById(R.id.textViewMaxScore)
        sharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            score = bundle.getInt("Score")
            textViewScore.setText("$score")
        }
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
        textViewScore?.setText("Record value : " + maxScore)
    }

    fun onClickPlay(view: View) {
        val intent = Intent(this@MenuActivity, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        saveScore()
    }
}