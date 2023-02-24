package com.example.chat_app

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var messageEditText: EditText
    private lateinit var sendButton: ImageButton
    private lateinit var messageRecyclerView: RecyclerView
    private lateinit var adapter: MessageAdapter
    private val messagesList = mutableListOf<Message>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        messageEditText = findViewById(R.id.editText)
        sendButton = findViewById(R.id.send_button)
        messageRecyclerView = findViewById(R.id.recyclerView)

        // Initialize RecyclerView and adapter
        adapter = MessageAdapter(messagesList)
        messageRecyclerView.adapter = adapter
        messageRecyclerView.layoutManager = LinearLayoutManager(this)

        // Set click listener on send button
        sendButton.setOnClickListener {
            val messageText = messageEditText.text.toString().trim()
            if (messageText.isNotEmpty()) {
                val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
                val message = Message(messageText, currentTime, true)
                messagesList.add(message)
                adapter.notifyItemInserted(messagesList.size - 1)
                messageRecyclerView.scrollToPosition(messagesList.size - 1)
                messageEditText.text.clear()

                // Show demo message after sending user message
                val demoMessage = Message("This is a demo message.", currentTime, false)
                messagesList.add(demoMessage)
                adapter.notifyItemInserted(messagesList.size - 1)
                messageRecyclerView.scrollToPosition(messagesList.size - 1)
            } else {
                Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
