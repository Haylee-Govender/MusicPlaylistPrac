package vcmsa.haylee.musicplaylistprac

import android.content.Intent
import android.media.Rating
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat

import androidx.core.view.WindowInsetsCompat



class MainActivity : AppCompatActivity() {
    // Declare variables for the buttons
    private lateinit var btnAdd: Button
    private lateinit var btnNext: Button
    private lateinit var btnExit: Button
    // Declare variables for the lists
    companion object {
        val songTitle = mutableListOf<String>()
        val artistNames = mutableListOf<String>()
        val songRatings = mutableListOf<Int>()
        val songComments = mutableListOf<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Initialize the buttons
        btnAdd = findViewById(R.id.btnAdd)
        btnNext = findViewById(R.id.btnNext)
        btnExit = findViewById(R.id.btnExit)

        // Set click listeners for the buttons
        btnAdd.setOnClickListener {
            showAddDialog()
        }
        btnNext.setOnClickListener {
            startActivity(Intent(this, DetailView::class.java))
        }
        btnExit.setOnClickListener {
            finishAffinity()
        }
    }
    // Function to show the add dialog
    private fun showAddDialog() {
        // Inflate the layout for the dialog
        val dialogView = layoutInflater.inflate(R.layout.song_details, null)
        val edtTitle = dialogView.findViewById<EditText>(R.id.edtTitle)
        val edtName = dialogView.findViewById<EditText>(R.id.edtName)
        val edtRating = dialogView.findViewById<EditText>(R.id.edtRating)
        val edtComment = dialogView.findViewById<EditText>(R.id.edtComment)
        // Create the dialog and set the click listener for the positive button

        val dialog = AlertDialog.Builder(this)
            .setTitle("Add Song")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val name = edtName.text.toString()
                val title = edtTitle.text.toString()
                val ratingStr = edtRating.text.toString()
                val comment = edtComment.text.toString()

                // Validate the input and add the song to the lists
                try {
                    val rating = ratingStr.toInt()
                    // Validate the input fields
                    if (name.isBlank() || title.isBlank() || comment.isBlank() )  {

                        Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
                        return@setPositiveButton
                    }
                     // Validate the rating input
                    if (rating < 1 || rating > 5) {
                        Toast.makeText(this, "Rating must be between 1 and 5.", Toast.LENGTH_SHORT).show()
                        return@setPositiveButton
                    }

                    // Add the song to the lists
                    songTitle.add(title)
                    artistNames.add(name)
                    songRatings.add(rating)
                    songComments.add(comment)

                    Log.i("MainActivity", "Song added: $title ($name), Qty: $rating, Comment: $comment")
                    Toast.makeText(this, "Song added", Toast.LENGTH_SHORT).show()
                    // Clear the input fields
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Rating must be a number", Toast.LENGTH_SHORT).show()
                    Log.e("MainActivity", "Rating input error", e)
                }
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }
}

