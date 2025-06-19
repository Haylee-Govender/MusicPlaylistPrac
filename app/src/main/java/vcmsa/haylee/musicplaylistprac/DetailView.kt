package vcmsa.haylee.musicplaylistprac

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast

class DetailView : AppCompatActivity() {
    // Declare variables for the buttons
    private lateinit var tvList: TextView
    private lateinit var btnDisplay: Button
    private lateinit var btnAverage: Button
    private lateinit var btnReturn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Initialize the buttons
        tvList = findViewById(R.id.tvList)
        btnDisplay = findViewById(R.id.btnDisplay)
        btnAverage = findViewById(R.id.btnAverage)
        btnReturn = findViewById(R.id.btnReturn)

        // Set click listeners for the buttons
        btnDisplay.setOnClickListener {
            displayAllItems()
        }
        btnAverage.setOnClickListener {
            displayAverageRating()
        }
        btnReturn.setOnClickListener {
            finish()
        }
    }

    // Function to display all items in the lists
    private fun displayAllItems() {
        val builder = StringBuilder()
        // Check if the lists are empty
        if (MainActivity.songTitle.isEmpty()) {
            tvList.text = "No songs to display."
            return
        }
        // Iterate through the lists and append the items to the builder
        for (i in MainActivity.songTitle.indices) {
            builder.append("Title: ${MainActivity.songTitle[i]}\n")
            builder.append("Artist Name: ${MainActivity.artistNames[i]}\n")
            builder.append("Rating: ${MainActivity.songRatings[i]}\n")
            builder.append("Comment: ${MainActivity.songComments[i]}\n\n")
            builder.append("---------------------------\n")
        }

        tvList.text = builder.toString()
        Log.i("DetailView", "Displayed all songs.")
        Toast.makeText(this, "Displayed all songs.", Toast.LENGTH_SHORT).show()
    }

       // Function to display the average rating
       private fun displayAverageRating() {
        val builder = StringBuilder()
        var totalRating = 0
        var count = 0
           // Iterate through the lists and calculate the average rating
        for (rating in MainActivity.songRatings) {
            totalRating += rating
            count++
        }
        val averageRating = totalRating / count
        builder.append("Average rating: $averageRating")
        Log.i("DetailView", "Displayed average rating.")
        Toast.makeText(this, "Displayed average rating.", Toast.LENGTH_SHORT).show()

           // Check if the lists are empty
        if (MainActivity.songRatings.isEmpty()) {
            tvList.text = "No songs to display."
            return
        }
           // Append the average ratinh to the builder
        tvList.text = builder.toString()
        Log.i("DetailView", "Displayed average rating.")
    }
}


