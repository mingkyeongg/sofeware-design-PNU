package com.example.ex05

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DividerItemDecoration

class SubFragment : Fragment() {

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_sub, container, false)
//        val datas = mutableListOf<String>()
//        for (i in 1..20) {datas.add("Item $i")}
//
//        // TODO: 데이터 생성, item 개수는 20개
//        // TODO: findViewById를 이용하여 recyclerView 가져오기
//        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
//        // TODO: recyclerView의 layout manager, adapter, item decoration 설정
//
//        return view
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sub, container, false)
        val datas = mutableListOf<String>()
        for (i in 1..20) {
            datas.add("Item $i")
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = MyAdapter(datas)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        return view
    }



}
