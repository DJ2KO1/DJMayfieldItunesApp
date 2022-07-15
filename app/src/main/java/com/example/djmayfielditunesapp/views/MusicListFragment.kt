package com.example.djmayfielditunesapp.views

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.djmayfielditunesapp.data.MusicResponse
import com.example.djmayfielditunesapp.databinding.FragmentMusicListBinding
import com.example.djmayfielditunesapp.network.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MusicListFragment(
    private var term: String = "rock"
) : Fragment() {

    lateinit var binding: FragmentMusicListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMusicListBinding.inflate(layoutInflater)

        //Classic Image button
        binding.ivClassic.setOnClickListener {
            term = "classic"
            binding.ivClassic.setTextColor(Color.BLUE)
            binding.ivClassic.compoundDrawables[1]?.setTint(Color.BLUE)
            fetchMusicList(term)

            //reset rock/pop
            binding.ivRock.setTextColor(Color.GRAY)
            binding.ivRock.compoundDrawables[1]?.setTint(Color.GRAY)
            binding.ivPop.setTextColor(Color.GRAY)
            binding.ivPop.compoundDrawables[1]?.setTint(Color.GRAY)
        }

        //Pop Image Button
          binding.ivPop.setOnClickListener {
              term = "Pop"
              binding.ivPop.setTextColor(Color.BLUE)
              binding.ivPop.compoundDrawables[1]?.setTint(Color.BLUE)

              fetchMusicList(term)

              //reset rock/classic
              binding.ivRock.setTextColor(Color.GRAY)
              binding.ivRock.compoundDrawables[1]?.setTint(Color.GRAY)
              binding.ivClassic.setTextColor(Color.GRAY)
              binding.ivClassic.compoundDrawables[1]?.setTint(Color.GRAY)
          }

        //Rock Image Button
          binding.ivRock.setOnClickListener {
              term = "Rock"
              binding.ivRock.setTextColor(Color.parseColor("blue"))
              binding.ivRock.compoundDrawables[1]?.setTint(Color.BLUE)
              fetchMusicList(term)

              //reset pop/classic
              binding.ivPop.setTextColor(Color.GRAY)
              binding.ivPop.compoundDrawables[1]?.setTint(Color.GRAY)
              binding.ivClassic.setTextColor(Color.GRAY)
              binding.ivClassic.compoundDrawables[1]?.setTint(Color.GRAY)
          }

        return binding.root
    }

    private fun fetchMusicList(term: String){
        APIService.getRetrofitInstance()?.create(APIService::class.java)
            ?.getMusic(term)?.enqueue(object: Callback<MusicResponse> {
                override fun onResponse(
                    call: Call<MusicResponse>,
                    response: Response<MusicResponse>
                ) {
                    if (response.isSuccessful){
                        println(response.toString())
                        val musicAdapter = MusicAdapter(response.body()!!.results)
                        binding.rvMusicList.adapter = musicAdapter
                    }
                    else{
                        println(response.toString())
                    }
                }
                override fun onFailure(call: Call<MusicResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }
            })
    }
}