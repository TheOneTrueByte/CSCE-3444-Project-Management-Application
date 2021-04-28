package com.example.fuji

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.fuji.models.Board
import kotlinx.android.synthetic.main.board_ui.*

class CreateCategoryFragment : Fragment(){
    //Start of firebase
    private val db = Firebase.firestore
    private fun addCategory(categoryName : String) {
        val newCategory = hashMapOf(
                "Title" to categoryName
        )
        val catName: String = categoryName
        val board : BoardsUIActivity = activity as BoardsUIActivity
        var boardName : String = board.BoardName!!
        db.collection("boards").document(boardName).collection(catName).document(catName).set(newCategory)
                .addOnSuccessListener {
                    Log.d(TAG, "DocumentSnapshot successfully added")
                    Toast.makeText(context, "Category Created", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener { e ->
                    Log.w(TAG, "Error adding category", e)
                    Toast.makeText(context, "Category Creation Failed", Toast.LENGTH_SHORT).show()
                }
    }
    private val TAG = "DocSnippets"
    ///////////////////////////////////END OF FIREBASE/////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.create_category_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val catNameInputView = view.findViewById<EditText>(R.id.create_cat_entry)

        view.findViewById<ImageView>(R.id.create_cat_back_button).setOnClickListener {
            //var board: BoardsActivity = activity as BoardsActivity
            //board.switchToBoardFragment()
            activity?.finish()
        }

        view.findViewById<Button>(R.id.create_board_button).setOnClickListener {
            val catNameInput = catNameInputView.text.toString().trim()

            if (catNameInput.isEmpty()) {
                Toast.makeText(context, "Please give the category a name first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (catNameInput.length > 20) {
                Toast.makeText(context, "Please give a board name of twenty characters or less", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // [FIREBASE ADD BOARD BEGIN]
            addCategory(catNameInput)
            // [FIREBASE ADD BOARD END]
           // val goToBoard : BoardsActivity = activity as BoardsActivity
           // goToBoard.switchToBoardFragment()
            activity?.finish()
        }
    }
}