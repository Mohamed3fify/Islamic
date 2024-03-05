package com.example.islamic.ui.home.radio

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.islamic.R
import com.example.islamic.databinding.FragmentQuranBinding
import com.example.islamic.databinding.FragmentRadioBinding
import com.example.islamic.ui.api.ApiManager
import com.example.islamic.ui.api.model.sourcesResponse.Radio
import com.example.islamic.ui.api.model.sourcesResponse.RadiosChannels
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RadioFragment : Fragment() {
    lateinit var binding: FragmentRadioBinding
    private var mediaPlayer: MediaPlayer? = null
    private var currentPosition = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRadioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getRadioSources()
    }

    fun getRadioSources() {
        ApiManager
            .getServices()
            .getRadioSources()
            .enqueue(object : Callback<RadiosChannels> {
                override fun onResponse(
                    call: Call<RadiosChannels>,
                    response: Response<RadiosChannels>
                ) {
                    if (response.isSuccessful) {
                        initRadios(response.body()?.radios)
                    } else {
                        showErrorMessage("Internet must be enabled")
                    }
                }

                override fun onFailure(call: Call<RadiosChannels>, t: Throwable) {
                    showErrorMessage(t.message)
                }
            })
    }

    private fun initRadios(radios: List<Radio?>?) {
        mediaPlayer = MediaPlayer()
        binding.playBtn.setOnClickListener {
            if (!mediaPlayer!!.isPlaying) { // if not working
                mediaPlayer?.reset()
                playRadio(radios!![currentPosition])
            } else {
                mediaPlayer?.pause()
                binding.playBtn.setImageResource(R.drawable.on_off_button)
            }
        }
        binding.nextBtn.setOnClickListener {
            nextChanel(radios?.size)
            mediaPlayer?.reset()
            playRadio(radios!![currentPosition])
        }
        binding.backBtn.setOnClickListener {
            previousChannel(radios?.size)
            mediaPlayer?.reset()
            playRadio(radios!![currentPosition])
        }
    }

    private fun previousChannel(radiosSize: Int?) {
        currentPosition--
        if (currentPosition < 0) {
            currentPosition =
                radiosSize!! - 1 // get the last channel in radio = repeating the list again
        }
    }

    private fun nextChanel(radiosSize: Int?) {
        currentPosition++
        if (currentPosition >= radiosSize!!) {
            currentPosition = 0
        }
    }

    private fun playRadio(radio: Radio?) {
        binding.suraName.text = radio?.name
        mediaPlayer?.apply {
            setDataSource(radio?.url)
            prepare()
            start()
        }
        binding.playBtn.setImageResource(R.drawable.on_off_button)
    }

    private fun showErrorMessage(message: String?) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_LONG,
        )
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}