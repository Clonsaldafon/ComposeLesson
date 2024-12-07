package ru.clonsaldafon.composelesson

import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.clonsaldafon.composelesson.ui.theme.ComposeLessonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLessonTheme {
                Scaffold(
                    topBar = {
                        MyOwnTopBar()
                    }
                ) { innerPadding ->
                    MyFirstScreen(
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MyFirstScreen(
    modifier: Modifier = Modifier
) {
    val grayModifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(20.dp))
        .background(Color(0xFFEFEFEF))
        .padding(
            horizontal = 20.dp,
            vertical = 16.dp
        )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp
            ),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Column(
            modifier = grayModifier,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            var video by remember {
                mutableStateOf(true)
            }

            Text(
                text = "Домофон",
                fontWeight = FontWeight.W600,
                fontSize = 22.sp,
                lineHeight = 24.sp
            )

            MyOwnToggleSwitch(
                title = "Видеозвонки",
                description = "Принимайте звонки с домофона",
                checked = video,
                onCheckedChange = {
                    video = it
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        Column(
            modifier = grayModifier
        ) {
            var notifications by remember {
                mutableStateOf(false)
            }

            MyOwnToggleSwitch(
                title = "Уведомления",
                description = "Информационные оповещения",
                checked = notifications,
                onCheckedChange = {
                    notifications = it
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        Column(
            modifier = grayModifier,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MyOwnInfoBlock(
                key = "SIP-аккаунт",
                value = "test",
                modifier = Modifier
                    .fillMaxWidth()
            )

            MyOwnInfoBlock(
                key = "Версия",
                value = "1.2.3(202206231",
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        MyOwnButton(
            text = "Выйти из аккаунта",
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyOwnTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = "Настройки",
                fontWeight = FontWeight.W700,
                fontSize = 28.sp,
                lineHeight = 36.sp
            )
        }
    )
}

@Composable
fun MyOwnInfoBlock(
    key: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = key,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )

        Text(
            text = value,
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            lineHeight = 18.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyOwnToggleSwitch(
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = title,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                lineHeight = 24.sp
            )

            Text(
                text = description,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                lineHeight = 18.sp
            )
        }

        CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedTrackColor = Color(0xFF1274FF),
                    uncheckedBorderColor = Color.Transparent,
                    uncheckedTrackColor = Color(0xFFCECECE),
                    uncheckedThumbColor = Color.White
                )
            )
        }
    }
}

@Composable
fun MyOwnButton(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(48.dp)
            .background(
                color = Color(0xFFD0EEFF),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(
                horizontal = 24.dp,
                vertical = 12.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color(0xFF0053DE),
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 24.sp
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun MyPreview() {
    MyFirstScreen()
}