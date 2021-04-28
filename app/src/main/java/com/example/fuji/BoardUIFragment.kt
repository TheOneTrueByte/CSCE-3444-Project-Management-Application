package com.example.fuji

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.board_ui.*


class BoardUIFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.board_ui, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Grabs global variable from BoardsUIActivity and sets that text to the board title
        board_ui_title.setText((activity as BoardsUIActivity).BoardName)

        //set on click listener for back button
        view.findViewById<ImageView>(R.id.board_ui_back_button).setOnClickListener {
            activity?.finish()
        }
        view.findViewById<ImageView>(R.id.createCatButton).setOnClickListener {
            val transaction = (activity as BoardsUIActivity).supportFragmentManager.beginTransaction()
            transaction.replace(this.getId(), CreateCategoryFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}