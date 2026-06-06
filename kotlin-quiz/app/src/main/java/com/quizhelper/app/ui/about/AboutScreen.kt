package com.quizhelper.app.ui.about

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.quizhelper.app.ui.components.BackButton
import com.quizhelper.app.ui.theme.*

@Composable
fun AboutScreen(navController: NavController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // 顶部渐变区域（二次元风格）
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Blue50)
                .padding(16.dp)
        ) {
            BackButton(text = "← 返回", onClick = { navController.popBackStack() })
        }

        // 主内容
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 大图标
            Text("📖✨", fontSize = 64.sp)
            Spacer(Modifier.height(8.dp))
            Text("墨 答", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Gray800)
            Spacer(Modifier.height(4.dp))
            Text("v2.1.0", fontSize = 14.sp, color = Gray400)
            Spacer(Modifier.height(6.dp))
            Text("优雅刷题，从容作答", fontSize = 15.sp, color = Gray500)
            Spacer(Modifier.height(24.dp))

            // 分隔装饰
            Text("✦ ✦ ✦", fontSize = 14.sp, color = Gray200)

            Spacer(Modifier.height(20.dp))

            // 信息列表
            AboutItem("👤", "作者", "littleboy")
            Spacer(Modifier.height(4.dp))

            AboutClickableItem("📧", "邮箱", "littleboy@example.com") {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:littleboy@example.com")
                }
                context.startActivity(intent)
            }
            Spacer(Modifier.height(4.dp))

            AboutClickableItem("🐙", "GitHub", "github.com/littleboy") {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/littleboy"))
                context.startActivity(intent)
            }
            Spacer(Modifier.height(4.dp))

            AboutClickableItem("📦", "项目地址", "github.com/littleboy/quiz-helper") {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/littleboy/quiz-helper"))
                context.startActivity(intent)
            }
            Spacer(Modifier.height(4.dp))

            AboutItem("📜", "开源协议", "MIT License")

            Spacer(Modifier.height(20.dp))
            Text("✦ ✦ ✦", fontSize = 14.sp, color = Gray200)
            Spacer(Modifier.height(20.dp))

            // 版权
            Text(
                "© 2026 littleboy",
                fontSize = 13.sp,
                color = Gray400
            )
            Text(
                "本软件仅供学习交流使用",
                fontSize = 12.sp,
                color = Gray300,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(Modifier.height(32.dp))
        }
    }
}

@Composable
private fun AboutItem(emoji: String, label: String, value: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        color = Gray50
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(emoji, fontSize = 18.sp)
            Spacer(Modifier.width(12.dp))
            Text(label, fontSize = 14.sp, color = Gray500, modifier = Modifier.width(70.dp))
            Spacer(Modifier.width(8.dp))
            Text(value, fontSize = 14.sp, color = Gray700, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
private fun AboutClickableItem(emoji: String, label: String, value: String, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(14.dp),
        color = Blue50
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(emoji, fontSize = 18.sp)
            Spacer(Modifier.width(12.dp))
            Text(label, fontSize = 14.sp, color = Gray500, modifier = Modifier.width(70.dp))
            Spacer(Modifier.width(8.dp))
            Text(value, fontSize = 14.sp, color = Blue600, fontWeight = FontWeight.Medium)
        }
    }
}
