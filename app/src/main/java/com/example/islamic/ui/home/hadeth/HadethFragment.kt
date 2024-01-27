package com.example.islamic.ui.home.hadeth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islamic.databinding.FragmentHadethBinding
import com.example.islamic.ui.Constant
import com.example.islamic.ui.HadithDetails.HadithDetailsActivity
import model.Hadith


class HadethFragment : Fragment() {
    private lateinit var viewBinding: FragmentHadethBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentHadethBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readHadithFile()
    }
  private fun readHadithFile(){
      val hadithList = mutableListOf<Hadith>()
      val inputStream = context?.assets?.open("ahadeeth.txt")
      val fileContent = inputStream?.bufferedReader().use { it?.readText() }
      val allAhadith = fileContent?.trim()?.split("#")
      allAhadith?.forEach { hadeth->
          val lines = hadeth.trim().split("\n")
          val title = lines[0]
          val newList = lines.toMutableList().apply { removeAt(0) }
          val content = newList.joinToString { "\n" }
          val hadith = Hadith(title, content)
          hadithList.add(hadith)
      }
      showHadithList(hadithList)

  }

    private fun showHadithList(hadithList: MutableList<Hadith>) {
        val adapter = HadithRecycleAdapter(hadithList)
        viewBinding.hadethRecycler.adapter = adapter
        adapter.onItemClickListener = HadithRecycleAdapter.OnItemClickListener{
             item, position ->
            startHadithDetailsScreen(item)
        }
    }

    private fun startHadithDetailsScreen(hadith: Hadith) {
        val intent = Intent(activity,HadithDetailsActivity::class.java)
        intent.putExtra(Constant.hadithExtra , hadith)
        startActivity(intent)

    }
}