package com.example.test

import android.graphics.drawable.AnimatedImageDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //保证在播放动画的时候，不要进入
        window.addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        //主activity设置
        setContentView(R.layout.activity_main)

        //监听
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //media player 创建，并设置声音为bgm
        val mediaPlayer = MediaPlayer.create(this, R.raw.bgm)
        //自动循环
        mediaPlayer.isLooping = true

        //我们要找到动画view
        val imageView = findViewById<ImageView>(R.id.animated_webp_view)
        //拿到drawable
        val drawable = imageView.drawable;
        //检查drawable是不是AnimatedImageDrawable
        if(drawable is AnimatedImageDrawable)
        {
            //播放动画
            drawable.start()
            //播放声音
            mediaPlayer.start()
        }
    }
}