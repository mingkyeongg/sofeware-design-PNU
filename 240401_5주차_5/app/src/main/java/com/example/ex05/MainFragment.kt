package com.example.ex05

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.ex05.OneFragment
import com.example.ex05.R

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val fragButton = view.findViewById<Button>(R.id.frag_btn)
        val fragmentManager = requireActivity().supportFragmentManager
        var onClicked = false
        fragButton.setOnClickListener{
            if (onClicked) {
                onClicked = false
                val transaction = fragmentManager.beginTransaction()
                val frameLayout = fragmentManager.findFragmentById(R.id.fragment_content)
                if(frameLayout != null) transaction.remove(frameLayout).commit()
            }
            else {
                onClicked = true
                val transaction = fragmentManager.beginTransaction()
                transaction.add(R.id.fragment_content, OneFragment()).commit()
            }
        }
        return view
    }
}


//class MainFragment : Fragment() {
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        var view = inflater.inflate(R.layout.fragment_main, container, false)
//        val fragButton = findViewById<Button>(R.id.frag_btn)
//        val FragmentManager: FragmentManager = supportFragmentManager
//        // TODO: fragment_main.xml에 있는 버튼을 가져와서 fragButton에 저장
//        val fragmentManager = requireActivity().supportFragmentManager
//        var onClicked = false
//        fragButton.setOnClickListener{
//            if (onClicked) {
//                onClicked = false
//                val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
//                val frameLayout = supportFragmentManager.findFragmentById(R.id.fragment_content)
//                transaction.remove(frameLayout!!).commit()
//                // TODO: FragmentManager를 사용하여 Fragment를 제거
//            }
//            else {
//                onClicked=true
//                val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
//                transaction.add(R.id.fragment_content, OneFragment()).commit()
//                // TODO: FragmentManager를 사용하여 Fragment를 추가
//            }
//        }
//        return view
//    }
//}