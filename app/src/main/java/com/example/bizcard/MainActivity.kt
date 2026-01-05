package com.example.bizcard


import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bizcard.data.Project
import com.example.bizcard.ui.theme.BizCardTheme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BizCardTheme {
                Surface(
                    modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CreateBizCard()

                }
            }
        }
    }
}

@Composable
fun CreateBizCard() {
    val context = LocalContext.current
    val projectList = remember {
        loadProjectsFromAssets(context)
    }
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier.padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateCircleImage()
                HorizontalDivider(
                    modifier = Modifier.padding(5.dp),
                    thickness = 0.5.dp,
                )
                CreateInfo()
                Button(onClick = {
                    buttonClickedState.value = !buttonClickedState.value
                }) {
                    Text("Portfolio")
                }
                if (buttonClickedState.value) {
                    Content(projectList)
                }

            }
        }
    }
}

@Composable
fun CreateCircleImage(modifier: Modifier = Modifier, imageUrl: String? = null) {
    val resourceId = remember(imageUrl) {
        getDrawableResource(imageUrl)
    }

    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        shadowElevation = 4.dp
    ) {
        Image(
            painter = painterResource(id = resourceId),
            contentDescription = resourceId.toString(),
            modifier = Modifier
                .padding(5.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun CreateInfo() {
    Column {
        Text(
            text = "Ted Lasso",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "Head Soccer Coach at AFC Richmond",
        )
        Text(
            text = "@coachtedlasso", style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun Content(data: List<Project>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Surface(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = (2.dp), color = Color.LightGray)
        ) {
            Portfolio(data = data)
        }
    }
}

@Composable
fun Portfolio(data: List<Project>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(data) { item ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                shape = RectangleShape,
                colors = CardDefaults.cardColors(Color.Transparent)
            ) {
                Row(
                ) {
                    CreateCircleImage(
                        modifier = Modifier.size(100.dp),
                        imageUrl = item.imageUrl
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    ) {
                        Text(item.title, fontWeight = Bold)
                        Text(
                            item.subtitle,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

fun loadProjectsFromAssets(context: Context): List<Project> {
    return try {
        val jsonString = context.assets.open("assets.json").bufferedReader().use { it.readText() }
        val listType = object : TypeToken<List<Project>>() {}.type
        Gson().fromJson(jsonString, listType)
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }
}

fun getDrawableResource(name: String?): Int {
    return when (name) {
        "afc_richmond" -> R.drawable.afc_richmond
        "wichita_shockers" -> R.drawable.wichita_shockers
        else -> R.drawable.profile
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BizCardTheme {
        CreateBizCard()
    }
}