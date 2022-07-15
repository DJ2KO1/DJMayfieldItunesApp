package com.example.djmayfielditunesapp.views

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.djmayfielditunesapp.data.MusicInfo
import com.example.djmayfielditunesapp.databinding.MusicListItemBinding

class MusicAdapter(
    private val list: List<MusicInfo>,
    val playSong: (MusicInfo) -> Unit,
): RecyclerView.Adapter<MusicAdapter.MusicInfoViewHolder>() {

//    val mp = MediaPlayer().apply {
//        setAudioAttributes(
//            AudioAttributes.Builder()
//                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                .setUsage(AudioAttributes.USAGE_MEDIA)
//                .build()
//        )
//    }

    inner class MusicInfoViewHolder(private val binding: MusicListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(musicInfo: MusicInfo){

            binding.tvCollectionName.text = musicInfo.collectionName
            binding.tvArtistName.text = musicInfo.artistName
            binding.tvTrackPrice.text = musicInfo.trackPrice.toString()

            Glide.with(binding.ivArtwork)
                .load(musicInfo.artworkUrl60)
                .into(binding.ivArtwork)

                binding.musicPreview.setOnClickListener() {
                    playSong(musicInfo)
                }
//                    mp.reset()
//                    mp.setDataSource(musicInfo.previewUrl)
//                    mp.prepare()
//                    if(mp.isPlaying){
//                        mp.stop()
//                        println("mp is stopped")
//                        mp.prepare()
//                    }
//                    mp.start()
//                    println("mp is starting")
//
//                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicInfoViewHolder {
        return MusicInfoViewHolder(
            MusicListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MusicInfoViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}