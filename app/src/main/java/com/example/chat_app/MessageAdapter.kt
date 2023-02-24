package com.example.chat_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MessageAdapter(private val messagesList: List<Message>) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageTextView: TextView = itemView.findViewById(R.id.message_text_view)
        val timeTextView: TextView = itemView.findViewById(R.id.time_text_view)
        val messageBackground: View = itemView.findViewById(R.id.message_background)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val itemView = if (viewType == USER_MESSAGE_VIEW_TYPE) {
            LayoutInflater.from(parent.context).inflate(R.layout.bg_message, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.bg_message, parent, false)
        }
        return MessageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messagesList[position]
        holder.messageTextView.text = message.text
        holder.timeTextView.text = message.time

        if (message.isUser) {
            holder.messageBackground.setBackgroundResource(R.drawable.bg_message)
        } else {
            holder.messageBackground.setBackgroundResource(R.drawable.bg_message)
        }
    }

    override fun getItemCount() = messagesList.size

    override fun getItemViewType(position: Int): Int {
        return if (messagesList[position].isUser) {
            USER_MESSAGE_VIEW_TYPE
        } else {
            DEMO_MESSAGE_VIEW_TYPE
        }
    }

    companion object {
        const val USER_MESSAGE_VIEW_TYPE = 1
        const val DEMO_MESSAGE_VIEW_TYPE = 2
    }
}
