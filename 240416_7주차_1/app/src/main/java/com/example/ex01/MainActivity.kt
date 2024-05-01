package com.example.ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import java.io.File
import android.widget.Toast
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.widget.Button
import com.example.ex01.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var downloadId: Long = -1L
    private lateinit var downloadManager: DownloadManager

    //브로드캐스트 리시버 선언
    private val onDownloadComplete = object : BroadcastReceiver() {
        //리시버 onReceive 구현
        override fun onReceive(context: Context, intent: Intent) {
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.action)) {
                if (downloadId == id) {
                    val query: DownloadManager.Query = DownloadManager.Query()
                    query.setFilterById(id)
                    var cursor = downloadManager.query(query)
                    if (!cursor.moveToFirst()) {
                        return
                    }

                    var columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
                    var status = cursor.getInt(columnIndex)
                    if (status == DownloadManager.STATUS_SUCCESSFUL) {
                        Toast.makeText(context, "Download succeeded", Toast.LENGTH_SHORT).show()
                    } else if (status == DownloadManager.STATUS_FAILED) {
                        Toast.makeText(context, "Download failed", Toast.LENGTH_SHORT).show()
                    }
                }
            } else if (DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(intent.action)) {
                Toast.makeText(context, "Notification clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        var binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //다운로드 매니저 객체
        downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        //인텐트 필터 선언
        val intentFilter = IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        intentFilter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED)
        registerReceiver(onDownloadComplete, intentFilter)



        binding.downloadBtn.setOnClickListener {
            val file = File(
                Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS + "/"
                ), "dev_submit.mp4"
            )

            //다운받을 Url 주소
            val downloadUrl =
                "https://cse.pusan.ac.kr/sites/cse/download/201912_cse_newsletter_vol_29.pdf"
            val request = DownloadManager.Request(Uri.parse(downloadUrl))
                .setTitle("파일 다운로드") // 알림의 제목 설정
                .setDescription("파일을 다운로드 중입니다...") // 알림의 설명 설정
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED) // 알림 가시성 설정
                .setAllowedOverMetered(true) // 데이터 네트워크 사용 허용
                .setAllowedOverRoaming(true) // 로밍 중 다운로드 허용
                .setDestinationUri(Uri.fromFile(file)) // 파일 저장 경로 설정

            // 다운로드 요청을 다운로드 매니저에 추가
            downloadId = downloadManager.enqueue(request)
        }

        binding.cancelBtn.setOnClickListener {
            if (downloadId != -1L) { // 유효한 다운로드 ID가 있는지 확인
                val numberOfCancelled = downloadManager.remove(downloadId) // 다운로드 취소 실행
                if (numberOfCancelled > 0) { // 취소된 다운로드가 있으면
                    Toast.makeText(this, "Download canceled", Toast.LENGTH_SHORT).show()
                } else { // 취소할 다운로드가 없으면
                    Toast.makeText(this, "No download to cancel", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    //리시버와 인텐트 필터 연결 해제
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(onDownloadComplete)
    }
}