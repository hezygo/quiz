package com.quizhelper.app.ui.about

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.quizhelper.app.ui.components.BackButton
import com.quizhelper.app.ui.components.PrimaryButton
import com.quizhelper.app.ui.theme.*

@Composable
fun AboutScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        BackButton(text = "← 返回设置", onClick = { navController.popBackStack() })

        Spacer(Modifier.height(16.dp))

        // App icon & name
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("📖", fontSize = 48.sp)
                Spacer(Modifier.height(8.dp))
                Text(
                    "墨答",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Gray800
                )
                Text(
                    "v2.1.0",
                    fontSize = 14.sp,
                    color = Gray400
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "优雅刷题，从容作答",
                    fontSize = 14.sp,
                    color = Gray500,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // Author info
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(Modifier.padding(20.dp)) {
                Text("👤 作者", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Gray700)
                Spacer(Modifier.height(12.dp))
                InfoRow("作者", "littleboy")
                Spacer(Modifier.height(8.dp))
                InfoRow("邮箱", "littleboy@example.com")
                Spacer(Modifier.height(8.dp))
                InfoRow("GitHub", "github.com/littleboy")
            }
        }

        Spacer(Modifier.height(16.dp))

        // Links
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(Modifier.padding(20.dp)) {
                Text("🔗 相关链接", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Gray700)
                Spacer(Modifier.height(12.dp))
                InfoRow("GitHub", "github.com/littleboy/quiz-helper")
                Spacer(Modifier.height(8.dp))
                InfoRow("开源协议", "MIT License")
            }
        }

        Spacer(Modifier.height(16.dp))

        // Copyright
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "© 2026 littleboy. All rights reserved.",
                    fontSize = 13.sp,
                    color = Gray400,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    "本软件仅供学习交流使用",
                    fontSize = 12.sp,
                    color = Gray300,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, fontSize = 14.sp, color = Gray500)
        Text(value, fontSize = 14.sp, color = Gray700, fontWeight = FontWeight.Medium)
    }
}
